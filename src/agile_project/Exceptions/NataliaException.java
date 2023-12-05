package agile_project.Exceptions;

@SuppressWarnings("serial")
public class NataliaException extends Exception {
	String message;
	
	public NataliaException(String e) {
		message = e;
	}
	
	public String getMessage() {
		return message;
	}
}
