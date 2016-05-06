package fr.pizzeria.dao;

import java.util.List;

import fr.pizzeria.exception.DaoException;
import fr.pizzeria.model.Pizza;

/**
 * Interface de DAO pour la gestion des {@link Pizza}.
 */
public interface IPizzaDao {
	/**
	 * R�cupere la liste des pizzas.
	 * 
	 * @return Une {@link List}<{@link Pizza}>.
	 */
	List<Pizza> findAllPizzas();

	/**
	 * Sauvegarde une nouvelle pizza.
	 * 
	 * @param pizza La nouvelle pizza � sauvegarder.
	 * @throws DaoException
	 */
	void saveNewPizza(Pizza pizza) throws DaoException;

	/**
	 * Modifie une pizza.
	 * 
	 * @param codePizza Le code de la pizza � modifier.
	 * @param pizza La pizza modifi�e.
	 * @throws DaoException
	 */
	void updatePizza(String codePizza, Pizza pizza) throws DaoException;

	/**
	 * Supprime une pizza.
	 * 
	 * @param codePizza Le code de la pizza � supprimer.
	 * @throws DaoException
	 */
	void deletePizza(String codePizza) throws DaoException;
}
