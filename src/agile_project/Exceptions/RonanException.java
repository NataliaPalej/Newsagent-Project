package agile_project.Exceptions;

public class RonanException extends Exception{

	String message;
	
	public RonanException(String e) {
		message = e;
	}
	
	public String getMessage() {
		return message;
	}
}
