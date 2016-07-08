package Expression;

import Expression.Exception.*;

public class Expression {
	
	public static Double calculateExpression(String expression) throws MathErrorException, SyntaxErrorException {
		
		Calculate calculate = new Calculate(expression);
		return calculate.calculate();
	}
}
