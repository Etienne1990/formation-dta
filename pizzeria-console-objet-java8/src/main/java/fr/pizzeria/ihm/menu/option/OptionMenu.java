package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.Menu;

/**
 * Classe abstraite pour les Options du {@link Menu}.
 */
public abstract class OptionMenu {

	/**
	 * Le lib�ll� de l'{@link OptionMenu}.
	 */
	private String libelle;
	protected IPizzaDao pizzaDao;

	/**
	 * Constructeur.
	 * 
	 * @param libelle Le lib�ll� de l'{@link OptionMenu}.
	 */
	public OptionMenu(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * Constructeur.
	 * 
	 * @param libelle Le lib�ll� de l'{@link OptionMenu}.
	 * @param pizzaDao La DAO pour lespizzas.
	 */
	public OptionMenu(String libelle, IPizzaDao pizzaDao) {
		this(libelle);
		this.pizzaDao = pizzaDao;
	}

	/**
	 * Execute l'action.
	 * 
	 * @return {@code true} si l'action doit arr�ter l'execution de
	 *         l'application apr�s son execution.
	 * @throws DaoException
	 */
	public abstract boolean execute() throws DaoException;

	/**
	 * R�cup�re le lib�ll�.
	 * 
	 * @return Le lib�ll� de l'{@link OptionMenu}.
	 */
	public String getLibelle() {
		return libelle;
	}
}
