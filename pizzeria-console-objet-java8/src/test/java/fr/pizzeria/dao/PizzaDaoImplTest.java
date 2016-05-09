package fr.pizzeria.dao;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.exception.DeletePizzaException;
import fr.pizzeria.exception.SavePizzaException;
import fr.pizzeria.exception.UpdatePizzaException;
import fr.pizzeria.model.CategoriePizza;
import fr.pizzeria.model.Pizza;

public class PizzaDaoImplTest {
	private IPizzaDao dao;

	@Before
	public void setUp() {
		dao = new PizzaDaoImpl();
	}

	@Test
	public void testFindAllPizzas() {
		List<Pizza> pizzas = new ArrayList<Pizza>();
		pizzas.add(new Pizza("IND", "L'indienne", 14.00, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("ORI", "L'orientale", 13.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("FRO", "La 4 fromages", 12.00, CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("REI", "La Reine", 11.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("CAN", "La cannibale", 12.50, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("SAU", "La saumonetta", 15.50, CategoriePizza.POISSON));
		pizzas.add(new Pizza("SAV", "La savoyarde", 13.00, CategoriePizza.VIANDE));
		pizzas.add(new Pizza("MAR", "Margherita", 14.00, CategoriePizza.SANS_VIANDE));
		pizzas.add(new Pizza("PEP", "P�p�roni", 12.50, CategoriePizza.VIANDE));

		List<Pizza> resultat = dao.findAllPizzas();
		assertArrayEquals(pizzas.toArray(), resultat.toArray());
	}

	@Test
	public void testSaveNewPizzaCodeInexistant() throws DaoException {
		Pizza newPizza = new Pizza("CODE_INEXISTANT", "nom", 11, CategoriePizza.POISSON);
		dao.saveNewPizza(newPizza);
		List<Pizza> listPizzas = dao.findAllPizzas();
		assertTrue(listPizzas.contains(newPizza));
	}

	@Test(expected = SavePizzaException.class)
	public void testSaveNewPizzaCodeExistant() throws DaoException {
		Pizza newPizza = new Pizza("FRO", "nom", 11, CategoriePizza.POISSON);
		dao.saveNewPizza(newPizza);
	}

	@Test(expected = UpdatePizzaException.class)
	public void testUpdatePizzaCodeInexistant() throws DaoException {
		Pizza newPizza = new Pizza("CODE_INEXISTANT", "nom", 11, CategoriePizza.POISSON);
		dao.updatePizza("CODE_INEXISTANT", newPizza);
	}

	@Test
	public void testUpdatePizzaCodeExistant() throws DaoException {
		Pizza newPizza = new Pizza("FRO", "nom", 11, CategoriePizza.POISSON);
		dao.updatePizza("FRO", newPizza);
		List<Pizza> listPizzas = dao.findAllPizzas();
		Optional<Pizza> pizzaOptional = listPizzas.stream().filter(p -> p.getCode().equals("FRO")).findFirst();
		assertTrue(pizzaOptional.isPresent());
		assertTrue(pizzaOptional.get().equals(newPizza));
	}

	@Test(expected = DeletePizzaException.class)
	public void testDeletePizzaCodeInexistant() throws DaoException {
		dao.deletePizza("CODE_INEXISTANT");
	}

	@Test
	public void testDeletePizzaCodeExistant() throws DaoException {
		dao.deletePizza("FRO");
		List<Pizza> listPizzas = dao.findAllPizzas();
		Optional<Pizza> pizzaOptional = listPizzas.stream().filter(p -> p.getCode().equals("FRO")).findFirst();
		assertTrue(pizzaOptional.isPresent() == false);
	}
}