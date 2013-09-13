package exceptions;

@SuppressWarnings("serial")
public class ValidationException extends Throwable {
	private String field;

	public String getField() {
		return field;
	}

	public ValidationException(String field) {
		super();
		this.field = field;
	}
	
}
