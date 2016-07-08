package Expression;

import Expression.Exception.MathErrorException;
import Expression.Exception.SyntaxErrorException;

public class Function2D {

	String function;
	Calculate calculate;
	
	public Function2D(String function) throws SyntaxErrorException {
		this.function = function;
		calculate = new Calculate(function);
	}
	
	public Double calculateFunction(double x) throws MathErrorException, SyntaxErrorException {
		return calculate.calculate(new Double(x));
	}
}
