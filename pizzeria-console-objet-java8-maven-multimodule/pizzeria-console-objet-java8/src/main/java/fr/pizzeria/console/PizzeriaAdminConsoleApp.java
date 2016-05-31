package fr.pizzeria.console;

import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.pizzeria.dao.pizza.PizzaDaoImpl;
import fr.pizzeria.ihm.menu.Menu;

/**
 * Classe principale de l'application.
 */
public class PizzeriaAdminConsoleApp {
	private PizzeriaAdminConsoleApp() {}

	/**
	 * Methode d'entrée. Initialise le {@link Menu}, le {@link Scanner} et la {@link PizzaDaoImpl DAO}.
	 * 
	 * @param args Les aguments du programme.
	 */
	public static void main(String[] args) {
		Logger.getLogger("org").setLevel(Level.SEVERE);
		Locale.setDefault(Locale.FRENCH);
		try (AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PizzeriaAppSpringConfig.class)) {
			Menu menu = context.getBean(Menu.class);
			menu.afficher();
		}
	}
}
