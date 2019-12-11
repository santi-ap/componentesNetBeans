/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ulatina.santiTests;

/**
 *
 * @author santialfonso
 */
import com.ulatina.controllers.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceUtils {

	public enum FabricantBD {
		Oracle, MySQL
	};
	
	private static final String NAME_PERSISTENCE = "compEclipse";
	
	private static EntityManagerFactory entityManagerFactory = null;

	public static EntityManagerFactory getEntityManagerFactory()
			throws GlobalException {

		if (PersistenceUtils.entityManagerFactory == null
				|| !entityManagerFactory.isOpen()) {

			startEntityManagerFactory();

		}
		return entityManagerFactory;
	}

	public static void startEntityManagerFactory() throws GlobalException {

		startEntityManagerFactory(NAME_PERSISTENCE);

	}

	public static void startEntityManagerFactory(String persistenceUnit)
			throws GlobalException {
		if (entityManagerFactory == null) {
			try {
				entityManagerFactory = Persistence
						.createEntityManagerFactory(persistenceUnit);

			}
			catch (Exception e) {
				Logger.getLogger(Controller.class.getName()).log(
						Level.SEVERE, "error: ", e);
				throw new GlobalException(
						"Error al iniciar el EntityManagerFactory", e);
			}
		}
	}

	public static void stopEntityManagerFactory() throws GlobalException {

		if (entityManagerFactory != null) {
			if (entityManagerFactory.isOpen()) {
				try {
					entityManagerFactory.close();
				}
				catch (Exception e) {
					Logger.getLogger(Controller.class.getName()).log(
							Level.SEVERE, "error: ", e);
					throw new GlobalException(
							"Error al finalizar el EntityManagerFactory", e);
				}
			}
			entityManagerFactory = null;
		}
	}

	public static FabricantBD driverJDBC() {

		String driver = entityManagerFactory.getProperties()
				.get("javax.persistence.jdbc.driver").toString();

		if (driver.contains("oracle"))
			return FabricantBD.Oracle;
		else
			return FabricantBD.MySQL;
	}

	public static String userBD() {
		return entityManagerFactory.getProperties()
				.get("javax.persistence.jdbc.user").toString();
	}
}
