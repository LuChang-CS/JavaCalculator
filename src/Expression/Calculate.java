package Expression;

import java.text.DecimalFormat;
import java.util.EmptyStackException;
import java.util.Stack;
import java.util.StringTokenizer;

import Expression.Exception.*;
import Records.GlobalConst;

public class Calculate {
	
	private String standardExpression;
	
	public Calculate() {
		standardExpression = "";
	}
	
	public Calculate(String expression) throws SyntaxErrorException {
		standardExpression = getStantardExpression(expression);
		
		if (standardExpression == null) {
			throw new SyntaxErrorException();
		}
	}
	
	public static String format(Double number) {
		int i = 0;
		String format;
		
		double absNumber = Math.abs(number.doubleValue());
		if (absNumber >= 1E-12 && absNumber <= 1E12 - 1) {
			format = "0.000000000000";
			DecimalFormat decimalFormat = new DecimalFormat(format);
			String result = decimalFormat.format(number);
			int length = result.length();
			for (i = length - 1; i > 0; --i) {
				char c = result.charAt(i);
				if (c != '0') {
					if (c == '.') {
						return result.substring(0, i);
					}
					else {
						return result.substring(0, i + 1);
					}
				}
			}
			return "0";
		}
		else {
			if (number == 0 || number < Math.abs(1E-16)) {
				return "0";
			}
			String[] numberStrings = number.toString().split("E");
			String sigNumber = numberStrings[0];
			String exp = numberStrings[1];
			
			format = "0.000000000000";
			DecimalFormat decimalFormat = new DecimalFormat(format);
			String result = decimalFormat.format(new Double(sigNumber));
			int length = result.length();
			for (i = length - 1; i > 0; --i) {
				char c = result.charAt(i);
				if (c != '0') {
					if (c == '.') {
						return result.substring(0, i) + "E" + exp;
					}
					else {
						return result.substring(0, i + 1) + "E" + exp;
					}
				}
			}
			return "0";
		}
	}
	
	public Double calculate() throws MathErrorException, SyntaxErrorException, NumberFormatException, EmptyStackException {
		
		Stack<String> operatorStack = new Stack<String>();
		Stack<Double> numberStack = new Stack<Double>();
		
		String operator = "+-*/^()qsctla#";
		StringTokenizer parser = new StringTokenizer(standardExpression, operator, true);
		
		operatorStack.push("#");
		String token = parser.nextToken();
		String lastOperatorString = operatorStack.peek().toString();
		boolean numberFlag = false;
		
		char current = token.charAt(0);
		char prevOperator = lastOperatorString.charAt(0);
			
		while(prevOperator != '#' || current != '#') {
			if (token.length() == 1 && isExpressionOperator(current)) {
				if (current == '-' && !numberFlag && (prevOperator == '#' || prevOperator == '(')) {
					token = parser.nextToken();
					current = token.charAt(0);
					if (current == '#' || current == ')') {
						throw new SyntaxErrorException();
					}
					double number = Double.parseDouble(token);
					
					if (Double.isInfinite(number)) {
						throw new MathErrorException();
					}
					
					numberStack.push(new Double(0));
					operatorStack.push("-");
					token = Double.toString(number);
					current = token.charAt(0);
					continue;
				}
				numberFlag = false;
				
				int compareResult = comparePriority(current, prevOperator);
				if (compareResult > 0) {
					operatorStack.push(token);
					token = parser.nextToken();
					current = token.charAt(0);
				}
				else if (compareResult == 0) {
					operatorStack.pop();
					token = parser.nextToken();
					current = token.charAt(0);
				}
				else {
					String op = operatorStack.pop().toString();
					char operatorToCalc = op.charAt(0);
					numberFlag = true;

					if (operatorToCalc == '+' || operatorToCalc == '-' 
						|| operatorToCalc == '*' || operatorToCalc == '/' 
						|| operatorToCalc == '^') {
						
						double rightNumber = Double.parseDouble(numberStack.pop().toString());
						double leftNumber = Double.parseDouble(numberStack.pop().toString());
						Double res = operate(leftNumber, rightNumber, operatorToCalc);
						
						if (res == null || res.isInfinite() || res.isNaN()) {
							throw new MathErrorException();
						}
							
						numberStack.push(res);
					}
					else if (operatorToCalc == 'a' || operatorToCalc == 'c' 
						|| operatorToCalc == 'l' || operatorToCalc == 'q' 
						|| operatorToCalc == 's' || operatorToCalc == 't') {
						
						double number = Double.parseDouble(numberStack.pop().toString());
						Double res = operate(number, operatorToCalc, true);
						
						if (res == null || res.isInfinite() || res.isNaN()) {
							throw new MathErrorException();
						}
							
						numberStack.push(res);
					}
					else {
						throw new SyntaxErrorException();
					}
				}
			}
			else {
				numberFlag = true;
				if (token.equals("e")) numberStack.push(new Double(Math.E));
				else if (token.equals("π")) numberStack.push(new Double(Math.PI));
				else if (token.equals("r")) numberStack.push(new Double(Math.random()));
				else if (token.equals("n")) numberStack.push(new Double(GlobalConst.getAnswer().getAnswer()));
				else if (token.equals("m")) numberStack.push(new Double(GlobalConst.getMemory().read()));
				else numberStack.push(new Double(token));
				token = parser.nextToken();
				current = token.charAt(0);
			}
				
			lastOperatorString = operatorStack.peek().toString();
			prevOperator = lastOperatorString.charAt(0);
		}
		
		if (operatorStack.size() == 1 && numberStack.size() == 1) {
			return new Double(numberStack.pop().toString());	
		}
		else {
			throw new SyntaxErrorException();
		}
	}
	
	public Double calculate(Double x) throws MathErrorException, SyntaxErrorException, NumberFormatException, EmptyStackException {
		
		Stack<String> operatorStack = new Stack<String>();
		Stack<Double> numberStack = new Stack<Double>();
		
		String operator = "+-*/^()qsctla#";
		StringTokenizer parser = new StringTokenizer(standardExpression, operator, true);
		
		operatorStack.push("#");
		String token = parser.nextToken();
		String lastOperatorString = operatorStack.peek().toString();
		boolean numberFlag = false;
		
		char current = token.charAt(0);
		char prevOperator = lastOperatorString.charAt(0);
			
		while(prevOperator != '#' || current != '#') {
			if (token.length() == 1 && isFunctionOperator2D(current)) {
				if (current == 'x') {
					numberStack.push(x);
					token = parser.nextToken();
					current = token.charAt(0);
					numberFlag = true;
					continue;
				}
				
				if (current == '-' && !numberFlag && (prevOperator == '#' || prevOperator == '(')) {
					token = parser.nextToken();
					current = token.charAt(0);
					if (current == '#' || current == ')') {
						throw new SyntaxErrorException();
					}
					double number;
					if (token.equals("x")) {
						number = x;
					}
					else {
						number = Double.parseDouble(token);
					}
					
					if (Double.isInfinite(number)) {
						throw new MathErrorException();
					}
					
					numberStack.push(new Double(0));
					operatorStack.push("-");
					token = Double.toString(number);
					current = token.charAt(0);
					continue;
				}
				numberFlag = false;
				
				int compareResult = comparePriority(current, prevOperator);
				if (compareResult > 0) {
					operatorStack.push(token);
					token = parser.nextToken();
					current = token.charAt(0);
				}
				else if (compareResult == 0) {
					operatorStack.pop();
					token = parser.nextToken();
					current = token.charAt(0);
				}
				else {
					String op = operatorStack.pop().toString();
					char operatorToCalc = op.charAt(0);
					numberFlag = true;

					if (operatorToCalc == '+' || operatorToCalc == '-' 
						|| operatorToCalc == '*' || operatorToCalc == '/' 
						|| operatorToCalc == '^') {
							
						double rightNumber = Double.parseDouble(numberStack.pop().toString());
						double leftNumber = Double.parseDouble(numberStack.pop().toString());
						Double res = operate(leftNumber, rightNumber, operatorToCalc);
						
						if (res == null || res.isInfinite() || res.isNaN()) {
							throw new MathErrorException();
						}
							
						numberStack.push(res);
					}
					else if (operatorToCalc == 'a' || operatorToCalc == 'c' 
						|| operatorToCalc == 'l' || operatorToCalc == 'q' 
						|| operatorToCalc == 's' || operatorToCalc == 't') {
						
						double number = Double.parseDouble(numberStack.pop().toString());
						Double res = operate(number, operatorToCalc, false);
							
						if (res == null || res.isInfinite() || res.isNaN()) {
							throw new MathErrorException();
						}
								
						numberStack.push(res);
					}
					else {
						throw new SyntaxErrorException();
					}
				}
			}
			else {
				numberFlag = true;
				if (token.equals("e")) numberStack.push(new Double(Math.E));
				else if (token.equals("π")) numberStack.push(new Double(Math.PI));
				else if (token.equals("r")) numberStack.push(new Double(Math.random()));
				else numberStack.push(new Double(token));
				token = parser.nextToken();
				current = token.charAt(0);
			}
				
			lastOperatorString = operatorStack.peek().toString();
			prevOperator = lastOperatorString.charAt(0);
		}
		
		if (operatorStack.size() == 1 && numberStack.size() == 1) {
			return new Double(numberStack.pop().toString());	
		}
		else {
			throw new SyntaxErrorException();
		}
	}
	
	public Double calculate(Double x, Double y) throws MathErrorException, SyntaxErrorException, NumberFormatException, EmptyStackException {
		
		Stack<String> operatorStack = new Stack<String>();
		Stack<Double> numberStack = new Stack<Double>();
		
		String operator = "+-*/^()qsctla#";
		StringTokenizer parser = new StringTokenizer(standardExpression, operator, true);
		
		operatorStack.push("#");
		String token = parser.nextToken();
		String lastOperatorString = operatorStack.peek().toString();
		boolean numberFlag = false;
		
		char current = token.charAt(0);
		char prevOperator = lastOperatorString.charAt(0);
			
		while(prevOperator != '#' || current != '#') {
			if (token.length() == 1 && isFunctionOperator3D(current)) {
				if (current == 'x') {
					numberStack.push(x);
					token = parser.nextToken();
					current = token.charAt(0);
					numberFlag = true;
					continue;
				}
				else if (current == 'y') {
					numberStack.push(y);
					token = parser.nextToken();
					current = token.charAt(0);
					numberFlag = true;
					continue;
				}
				
				if (current == '-' && !numberFlag && (prevOperator == '#' || prevOperator == '(')) {
					token = parser.nextToken();
					current = token.charAt(0);
					if (current == '#' || current == ')') {
						throw new SyntaxErrorException();
					}

					double number;
					if (token.equals("x")) {
						number = x;
					}
					else if (token.equals("y")) {
						number = y;
					}
					else {
						number = Double.parseDouble(token);
					}
					
					if (Double.isInfinite(number)) {
						throw new MathErrorException();
					}
					
					if (Double.isInfinite(number)) {
						throw new MathErrorException();
					}
					
					numberStack.push(new Double(0));
					operatorStack.push("-");
					token = Double.toString(number);
					current = token.charAt(0);
					continue;
				}
				
				int compareResult = comparePriority(current, prevOperator);
				if (compareResult > 0) {
					operatorStack.push(token);
					token = parser.nextToken();
					current = token.charAt(0);
				}
				else if (compareResult == 0) {
					operatorStack.pop();
					token = parser.nextToken();
					current = token.charAt(0);
				}
				else {
					String op = operatorStack.pop().toString();
					char operatorToCalc = op.charAt(0);
					numberFlag = true;

					if (operatorToCalc == '+' || operatorToCalc == '-' 
						|| operatorToCalc == '*' || operatorToCalc == '/' 
						|| operatorToCalc == '^') {
							
						double rightNumber = Double.parseDouble(numberStack.pop().toString());
						double leftNumber = Double.parseDouble(numberStack.pop().toString());
						Double res = operate(leftNumber, rightNumber, operatorToCalc);
						
						if (res == null || res.isInfinite() || res.isNaN()) {
							throw new MathErrorException();
						}
							
						numberStack.push(res);
						
					}
					else if (operatorToCalc == 'a' || operatorToCalc == 'c' 
						|| operatorToCalc == 'l' || operatorToCalc == 'q' 
						|| operatorToCalc == 's' || operatorToCalc == 't') {
							
						double number = Double.parseDouble(numberStack.pop().toString());
						Double res = operate(number, operatorToCalc, false);
								
						if (res == null || res.isInfinite() || res.isNaN()) {
							throw new MathErrorException();
						}

						numberStack.push(res);
					}
					else {
						throw new SyntaxErrorException();
					}
				}
			}
			else {
				numberFlag = true;
				if (token.equals("e")) numberStack.push(new Double(Math.E));
				else if (token.equals("π")) numberStack.push(new Double(Math.PI));
				else if (token.equals("r")) numberStack.push(new Double(Math.random()));
				else numberStack.push(new Double(token));
				token = parser.nextToken();
				current = token.charAt(0);
			}
				
			lastOperatorString = operatorStack.peek().toString();
			prevOperator = lastOperatorString.charAt(0);
		}
		
		if (operatorStack.size() == 1 && numberStack.size() == 1) {
			return new Double(numberStack.pop().toString());	
		}
		else {
			throw new SyntaxErrorException();
		}
	}
	
	private String getStantardExpression(String expr) {
		
		expr = expr.toLowerCase();
		
		String standardExpression = "";
		int length = expr.length();
		
		for (int i = 0; i < length; ) {
			char c = expr.charAt(i);
			
			switch (c) {
			
			case ' ':
				++i;
				break;
				
			case '√':
				standardExpression += 'q';
				++i;
				break;
				
			case '×':
				standardExpression += '*';
				++i;
				break;
				
			case '÷':
				standardExpression += '/';
				++i;
				break;
			
			case 'a':
				char ac1 = expr.charAt(i + 1);
				char ac2 = expr.charAt(i + 2);
				
				if (ac1 == 'b' && ac2 == 's') {
					standardExpression += 'a';
					i += 3;
				}
				else if (ac1 == 'n' && ac2 == 's') {
					standardExpression += "n";
					i += 3;
				}
				else {
					return null;
				}
				break;
				
			case 'c':
				char cc1 = expr.charAt(i + 1);
				char cc2 = expr.charAt(i + 2);
				
				if (cc1 == 'o' && cc2 == 's') {
					standardExpression += 'c';
					i += 3;
				}
				else {
					return null;
				}
				break;
				
			case 'l':
				char lc1 = expr.charAt(i + 1);
				
				if (lc1 == 'n') {
					standardExpression += 'l';
					i += 2;
				}
				else {
					return null;
				}
				break;
				
			case 'r':
				char rc1 = expr.charAt(i + 1);
				char rc2 = expr.charAt(i + 2);
				char rc3 = expr.charAt(i + 3);
				
				if (rc1 == 'a' && rc2 == 'n' && rc3 == '#') {
					standardExpression += 'r';
					i += 4;
				}
				else {
					return null;
				}
				break;
			
			case 's':
				char sc1 = expr.charAt(i + 1);
				char sc2 = expr.charAt(i + 2);
				char sc3 = expr.charAt(i + 3);
				
				if (sc1 == 'i' && sc2 == 'n') {
					standardExpression += 's';
					i += 3;
				}
				else if (sc1 == 'q' && sc2 == 'r' && sc3 == 't') {
					standardExpression += 'q';
					i += 4;
				}
				else {
					return null;
				}
				break;
				
			case 't':
				char tc1 = expr.charAt(i + 1);
				char tc2 = expr.charAt(i + 2);
				
				if (tc1 == 'a' && tc2 == 'n') {
					standardExpression += 't';
					i += 3;
				}
				else {
					return null;
				}
				break;

			default:
				standardExpression += c;
				++i;
			}
		}
		standardExpression += "#";
		
		return standardExpression;
	}
	
	private int outStackPriority(char c) {
		
		switch (c) {
		
		case '#':
			return -1;

		case '(':
			return 10;
		case ')':
			return 1;
		
		case '+':
		case '-':
			return 2;
			
		case '*':
		case '/':
			return 4;
			
		case '^':
			return 7;
		
		case 'a':
		case 'c':
		case 'l':
		case 'q':
		case 's':
		case 't':
			return 9;

		default:
			return 0;
		}
	}
	
	private int inStackPriority(char c) {
		
		switch (c) {
		
		case '#':
			return -1;
		
		case '(':
			return 0;
		case ')':
			return 9;
		
		case '+':
		case '-':
			return 3;
			
		case '*':
		case '/':
			return 5;
			
		case '^':
			return 6;
		
		case 'a':
		case 'c':
		case 'l':
		case 'q':
		case 's':
		case 't':
			return 8;

		default:
			return 0;
		}
	}
	
	private int comparePriority(char outStackOperator, char inStackOperator) throws SyntaxErrorException {
		
		if (inStackOperator == '(' && outStackOperator == ')')
			return 0;
		if (inStackOperator == ')') {
			if (outStackOperator == '(' || outStackOperator == '^' 
				|| outStackOperator == 'a' || outStackOperator == 'c' 
				|| outStackOperator == 'l' || outStackOperator == 'q' 
				|| outStackOperator == 's' || outStackOperator == 't') {
				
				throw new SyntaxErrorException();
			}
		}
		if (inStackOperator == '#' && outStackOperator == ')')
			throw new SyntaxErrorException();
		
		return outStackPriority(outStackOperator) - inStackPriority(inStackOperator);
	}
	
	private boolean isExpressionOperator(char c) {
		
		 return (c =='+' || c =='-' || c =='*' || c =='/' || c =='^' 
				 || c == 'a' || c =='c' || c =='l' || c =='q' || c == 's' 
				 || c == 't' || c=='(' || c ==')' || c =='#');
	}
	
	private boolean isFunctionOperator2D(char c) {
		
		return isExpressionOperator(c) || c == 'x';
	}
	
	private boolean isFunctionOperator3D(char c) {
		
		return isFunctionOperator2D(c) || c == 'y';
	}
	
	private Double operate(double leftNumber, double rightNumber, char operator) throws MathErrorException {
		
		try {
			if (operator == '+') return Double.valueOf(leftNumber + rightNumber);
			else if (operator == '-') return Double.valueOf(leftNumber - rightNumber);
			else if (operator == '*') return Double.valueOf(leftNumber * rightNumber);
			else if (operator == '/') {
				if (rightNumber < Math.abs(1E-10)) throw new MathErrorException();
				else return Double.valueOf(leftNumber / rightNumber);
			}
			else {
				if (leftNumber < Math.abs(1E-10) && rightNumber < Math.abs(1E-10)) throw new MathErrorException();
				return Double.valueOf(Math.pow(leftNumber, rightNumber));
			}
		}
		catch (Exception e) {
			return null;
		}
	}
	
	private Double operate(double number, char operator, boolean isReg) throws MathErrorException  {
		
		try {
			if (operator == 'a') return Double.valueOf(Math.abs(number));
			else if (operator == 'c') return Double.valueOf(Math.cos(isReg ? number * Math.PI / 180.0 : number));
			else if (operator == 'l') {
				if (number < Math.abs(1E-10)) throw new MathErrorException();
				else return Double.valueOf(Math.log(number));
			}
			else if (operator == 'q') return Double.valueOf(Math.sqrt(number));
			else if (operator == 's') return Double.valueOf(Math.sin(isReg ? number * Math.PI / 180.0 : number));
			else {
				if ((Math.abs(number - 90) % 180) < Math.abs(1E-10)) throw new MathErrorException();
				return Double.valueOf(Math.tan(isReg ? number * Math.PI / 180.0 : number));
			}
		}
		catch (Exception e) {
			return null;
		}
	}
}
