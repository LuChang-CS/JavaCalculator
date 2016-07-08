package Expression.Exception;

public class MathErrorException extends java.lang.Exception {
	String message = "Math Error!";
	
	public String toString() {
		return message;
	}
}
