package fr.pizzeria.ihm.menu.option;

import java.util.Arrays;
import java.util.Scanner;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.exception.CategoriePizzaException;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Option pour ajouter une nouvelle pizza.
 */
public class AjouterPizzaOptionMenu extends OptionMenu {

	/**
	 * Constante pour le {@link OptionMenu.libelle lib�ll�} de
	 * l'{@link OptionMenu}.
	 */
	private static final String AJOUTER_PIZZA_LIBELLE = "Ajouter une pizza";
	private Scanner scan;

	/**
	 * Constructeur.
	 * 
	 * @param pizzaDao La DAO pour les pizzas.
	 * @param scan Le {@link Scanner} pour la saisie utilisateur.
	 */
	public AjouterPizzaOptionMenu(IPizzaDao pizzaDao, Scanner scan) {
		super(AJOUTER_PIZZA_LIBELLE, pizzaDao);
		this.scan = scan;
	}

	@Override
	public boolean execute() throws DaoException {
		System.out.println("Ajout d�une nouvelle pizza");
		System.out.println("Veuillez saisir le code");
		String code = scan.next();
		System.out.println("Veuillez saisir le nom (sans espace)");
		String name = scan.next();
		System.out.println("Veuillez saisir le prix");
		double price = scan.nextDouble();
		System.out.println("Veuillez saisir la cat�gorie : " + Arrays.toString(CategoriePizza.values()));
		String categorieString = scan.next();
		try {
			CategoriePizza categorie = CategoriePizza.valueOf(categorieString.toUpperCase());
			pizzaDao.saveNewPizza(new Pizza(code, name, price, categorie));
		} catch (DaoException e) {
			throw new SavePizzaException("Erreur : La pizza avec le code " + code + " existe d�j�.", e);
		} catch (IllegalArgumentException e) {
			throw new CategoriePizzaException(
					"Erreur de saisie : La cat�gorie \"" + categorieString + "\" n'existe pas.", e);
		}
		System.out.println();
		return false;
	}
}
