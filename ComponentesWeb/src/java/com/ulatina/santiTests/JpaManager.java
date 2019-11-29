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
import org.hibernate.SessionFactory;


/**
 * Clase encargada de manipular el entitymanager factory
 */
public class JpaManager {
	/**
	 * Factory de hibernate para recoger estadisticas de la aplicacion
	 */
	protected static SessionFactory hbSessionFactory = null;  


	/**
	 * inicializa el entityManagerFactory   
	 */
	public static void initEntityManagerFactory() {
		try {
			PersistenceUtils.startEntityManagerFactory("compEclipse");
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Finaliza el entityManagerFactory
	 */
	public static void EndEntityManagerFactory() {
		try {
			PersistenceUtils.stopEntityManagerFactory();
		} catch (GlobalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (hbSessionFactory != null && !hbSessionFactory.isClosed()) {
			System.out
					.println("////////////Estadisticas de HIBERNATE///////////");
			hbSessionFactory.getStatistics().logSummary();
		}
	}
}
