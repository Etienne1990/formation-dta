package fr.pizzeria.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;

import fr.pizzeria.config.PizzaDaoSpringConfigJdbcTest;
import fr.pizzeria.dao.pizza.IPizzaDao;

@ContextConfiguration(classes = PizzaDaoSpringConfigJdbcTest.class)
public class PizzaDaoJdbcTemplateImplTest extends PizzaDaoTest {

	@Autowired
	public void setPizzaDao(@Qualifier("pizzaDaoJdbcTemplateImpl") IPizzaDao pizzaDao) {
		this.pizzaDao = pizzaDao;
	}
}
