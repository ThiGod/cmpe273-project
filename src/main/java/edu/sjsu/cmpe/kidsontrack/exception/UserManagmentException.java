package edu.sjsu.cmpe.kidsontrack.exception;

/**
 * @author Lei Zhang
 *
 */
public class UserManagmentException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public UserManagmentException() {
		super();
	}

	public UserManagmentException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public UserManagmentException(final String message) {
		super(message);
	}

	public UserManagmentException(final Throwable cause) {
		super(cause);
	}



}
