package fr.pizzeria.console;

import java.sql.SQLException;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import fr.pizzeria.dao.DaoProducer;
import fr.pizzeria.dao.IDaoFactory;
import fr.pizzeria.exception.DaoException;

@Configuration
@ComponentScan("fr.pizzeria")
public class PizzeriaAppSpringConfig {
	private static final String FILE_APLLICATION_PROP = "application";
	private static final String FILE_JDBC_PROP = "jdbc";
	private static final String PROPERTY_DAO_IMPL = "dao.impl";
	private static final String PROPERTY_DRIVER = "driver";
	private static final String PROPERTY_URL = "url";
	private static final String PROPERTY_USER = "user";
	private static final String PROPERTY_PASS = "pass";
	private static final String BASEURL_REST = "http://localhost:8080/pizzeria-admin-app/api/rest";

	@Autowired EntityManagerFactory emf;

	@Bean
	public Scanner getScanner() {
		return new Scanner(System.in);
	}

	@Bean
	public EntityManagerFactory geEntityManagerFactory() {
		return Persistence.createEntityManagerFactory("pizzeria-console-objet-java8");
	}

	@Bean
	public IDaoFactory getDaoFactory() {
		IDaoFactory dao = null;
		try {
			ResourceBundle bundle = ResourceBundle.getBundle(FILE_APLLICATION_PROP);
			int daoImpl = Integer.parseInt(bundle.getString(PROPERTY_DAO_IMPL));
			switch (daoImpl) {
				case 0:
					System.out.println("DAO : Mémoire");
					dao = DaoProducer.getDaoFactoryMemoire();
					break;
				case 1:
					System.out.println("DAO : Fichiers");
					dao = DaoProducer.getDaoFactoryFichier();
					break;
				case 2:
					System.out.println("DAO : JDBC");
					bundle = ResourceBundle.getBundle(FILE_JDBC_PROP);
					String driverConnection = bundle.getString(PROPERTY_DRIVER);
					String urlConnection = bundle.getString(PROPERTY_URL);
					String userConnection = bundle.getString(PROPERTY_USER);
					String passConnection = bundle.getString(PROPERTY_PASS).isEmpty() ? null : bundle.getString(PROPERTY_PASS);
					try {
						dao = DaoProducer.getDaoFactoryJdbc(driverConnection, urlConnection, userConnection, passConnection);
					} catch (SQLException e) {
						throw new DaoException("Erreur SQL : " + e.getMessage(), e);
					}
					break;
				case 3:
					System.out.println("DAO : JPA");
					Logger.getLogger("org.hibernate").setLevel(Level.SEVERE);
					dao = DaoProducer.getDaoFactoryJpa(emf);
					break;
				case 4:
					System.out.println("DAO : REST");
					dao = DaoProducer.getDaoFactoryRest(BASEURL_REST);
					break;
				default:
					System.err.println("Erreur: Le fichier " + FILE_APLLICATION_PROP + ".properties doit contenir la propriété \"" + PROPERTY_DAO_IMPL + "\" avec la valeur 0, 1, 2, 3 ou 4.");
					break;
			}
		} catch (DaoException e) {
			System.err.println(e.getMessage());
		} catch (MissingResourceException e) {
			System.err.println("Erreur: Le fichier " + FILE_APLLICATION_PROP + ".properties doit contenir la propriété \"" + PROPERTY_DAO_IMPL + "\" avec la valeur 0, 1, 2, 3 ou 4.");
		}
		return dao;
	}
}
