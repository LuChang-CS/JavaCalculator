package Expression;

import Expression.Exception.MathErrorException;
import Expression.Exception.SyntaxErrorException;

public class Function3D {

	String function;
	Calculate calculate;
	
	public Function3D(String function) throws SyntaxErrorException {
		this.function = function;
		calculate = new Calculate(function);
	}
	
	public Double calculateFunction(double x, double y) throws MathErrorException, SyntaxErrorException {
		return calculate.calculate(new Double(x), new Double(y));
	}
}
