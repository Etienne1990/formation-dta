package fr.pizzeria.ihm.menu.option;

import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;

public class SupprimerPizzaOptionMenu extends OptionMenu {

	/**
	 * Constante pour le {@link OptionMenu.libelle lib�ll�} de
	 * l'{@link OptionMenu}.
	 */
	private static final String SUPPRIMER_PIZZA_LIBELLE = "Supprimer une pizza";
	private Scanner scan;

	/**
	 * Constructeur.
	 * 
	 * @param pizzaDao La DAO pour les pizzas.
	 * @param scan Le {@link Scanner} pour la saisie utilisateur.
	 */
	public SupprimerPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scan) {
		super(SUPPRIMER_PIZZA_LIBELLE, pizzaDao);
		this.scan = scan;
	}

	@Override
	public boolean execute() throws DaoException {
		System.out.println("Suppression d�une pizza");
		new ListerPizzaOptionMenu(pizzaDao).execute();
		System.out.println("Veuillez choisir le code la pizza � modifier.");
		System.out.println("(99 pour abandonner).");
		String oldCode = scan.next();
		if (oldCode.equals("99")) {
			return false;
		}
		try {
			pizzaDao.deletePizza(oldCode);
		} catch (DaoException e) {
			throw new DeletePizzaException("Erreur : La pizza avec le code " + oldCode + " n'existe pas.", e);
		}
		System.out.println();
		return false;
	}
}
