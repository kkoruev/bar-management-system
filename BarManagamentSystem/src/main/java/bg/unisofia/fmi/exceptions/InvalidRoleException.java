package bg.unisofia.fmi.exceptions;

public class InvalidRoleException extends IllegalArgumentException {
	
	private static final long serialVersionUID = 1L;

	public InvalidRoleException() {
		super();
	}

	public InvalidRoleException(String message) {
		super(message);
	}
}
