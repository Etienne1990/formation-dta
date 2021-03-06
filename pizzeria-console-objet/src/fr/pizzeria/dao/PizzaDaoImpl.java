package fr.pizzeria.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

/**
 * Implémentation de la DAO utilisant une {@link Map} pour les pizzas.
 */
public class PizzaDaoImpl implements IPizzaDao {

	/**
	 * La {@link Map} pour les pizzas.
	 */
	private Map<String, Pizza> pizzas;

	/**
	 * Constructeur. Initialise la {@link Map} de pizzas.
	 */
	public PizzaDaoImpl() {
		pizzas = new HashMap<String, Pizza>();
		pizzas.put("PEP", new Pizza("PEP", "Pépéroni", 12.50, CategoriePizza.VIANDE));
		pizzas.put("MAR", new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzas.put("REI", new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.put("FRO", new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.put("CAN", new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.put("SAV", new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.put("ORI", new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzas.put("IND", new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		pizzas.put("SAU", new Pizza("SAU", "La saumonetta", 15.50, CategoriePizza.POISSON));
	}

	@Override
	public List<Pizza> findAllPizzas() {
		List<Pizza> list = new ArrayList<Pizza>(pizzas.values());
		Collections.sort(list);
		return list;
	}

	@Override
	public void saveNewPizza(Pizza pizza) throws DaoException {
		if (pizzas.containsKey(pizza.getCode())) {
			throw new DaoException();
		}
		pizzas.put(pizza.getCode(), pizza);
	}

	@Override
	public void updatePizza(String codePizza, Pizza pizza) throws DaoException {
		if (!pizzas.containsKey(pizza.getCode())) {
			throw new DaoException();
		}
		pizzas.put(codePizza, pizza);
	}

	@Override
	public void deletePizza(String codePizza) throws DaoException {
		if (!pizzas.containsKey(codePizza)) {
			throw new DaoException();
		}
		pizzas.remove(codePizza);
	}
}
