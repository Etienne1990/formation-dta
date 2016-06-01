package fr.pizzeria.ihm.menu.option.pizza;

import java.util.Scanner;

import fr.pizzeria.dao.IDaoFactory;
import fr.pizzeria.dao.pizza.IPizzaDao;
import fr.pizzeria.dao.pizza.PizzaDaoFichierImpl;
import fr.pizzeria.dao.pizza.PizzaDaoJdbcImpl;
import fr.pizzeria.dao.pizza.PizzaDaoJdbcTemplateImpl;
import fr.pizzeria.dao.pizza.PizzaDaoJpaImpl;
import fr.pizzeria.exception.DaoException;
import fr.pizzeria.ihm.menu.option.OptionMenu;

public class ImporterPizzaOptionMenu extends OptionMenu {

	/**
	 * Constante pour le {@link OptionMenu.libelle libéllé} de l'{@link OptionMenu}.
	 */
	private static final String IMPORTER_PIZZA_LIBELLE = "(Base de données) Importer les données";

	/**
	 * Constructeur.
	 * 
	 * @param dao La DAO Factory.
	 * @param scan Le {@link Scanner} pour la saisie utilisateur.
	 */
	public ImporterPizzaOptionMenu(IDaoFactory dao) {
		super(IMPORTER_PIZZA_LIBELLE, dao);
	}

	@Override
	public boolean execute() throws DaoException {
		IPizzaDao pizzaDao = dao.getPizzaDao();
		if (pizzaDao instanceof PizzaDaoJdbcImpl || pizzaDao instanceof PizzaDaoJdbcTemplateImpl || pizzaDao instanceof PizzaDaoJpaImpl) {
			pizzaDao.importFromFiles(new PizzaDaoFichierImpl(), 3);
		} else {
			System.err.println("Veuillez configurer l'application avec une implémentation base de données.");
		}
		System.out.println();
		return false;
	}
}