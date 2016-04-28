package fr.pizzeria.ihm.menu.option;

import fr.pizzeria.dao.IPizzaDao;
import fr.pizzeria.model.Pizza;

public class ListerPizzaOptionMenu extends OptionMenu {

	private static final String LISTER_PIZZAS_LIBELLE = "Lister les pizzas";

	public ListerPizzaOptionMenu(IPizzaDao pizzaDao) {
		super(LISTER_PIZZAS_LIBELLE, pizzaDao);
	}

	@Override
	public boolean execute() {
		Pizza[] pizzas = this.pizzaDao.findAllPizzas();
		for (Pizza p : pizzas) {
			System.out.println(p.getCode() + " -> " + p.getNom() + " (" + p.getPrix() + "�)");
		}
		System.out.println("------- " + Pizza.nbPizzas + " pizzas cr��es depuis l�initialisation du programme");
		System.out.println();
		return false;
	}
}