package hr.fer.zemris.java.tecaj_13.dao;

/**
 * Class represents exception that can be thrown when we are working with
 * {@link DAO}
 * 
 * @author Mihael
 *
 */
public class DAOException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor creates exception with exception cause and description like
	 * parameters
	 * 
	 * @param message
	 *            - exception description
	 * @param cause
	 *            - exception cause
	 */
	public DAOException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor creates new exception with it's description
	 * 
	 * @param message
	 */
	public DAOException(String message) {
		super(message);
	}
}