package fr.pizzeria.exception;

/**
 * Exception g�n�rique pour la DAO.
 */
public class DaoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DaoException() {
		super();
	}

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}
}
