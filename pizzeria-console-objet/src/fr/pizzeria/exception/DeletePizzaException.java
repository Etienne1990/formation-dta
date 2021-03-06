package fr.pizzeria.exception;

/**
 * Exception pour les probl�mes de suppression des pizzas.
 */
public class DeletePizzaException extends DaoException {

	private static final long serialVersionUID = 1L;

	public DeletePizzaException() {
		super();
	}

	public DeletePizzaException(String string, DaoException e) {
		super(string, e);
	}
}
