package Expression.Exception;

public class SyntaxErrorException extends java.lang.Exception {
	String message = "Syntax Error!";
	
	public String toString() {
		return message;
	}
}
