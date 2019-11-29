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

import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Web application lifecycle listener. Filtro encargado de inicializar el
 * EntityManager no es necesario su uso, pero por velocidad se deberia usar.
 */
public class EntityListener implements ServletContextListener, ServletRequestListener {
 
	/**
	 * nombre del entity manager injectado en el request del usuario
	 */
	public static final String nombreEm = "em";

	/**
	 * Contexto de la aplicacion inicializada
	 * 
	 * @param sce
	 *            parametros de inicio
	 */
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("Inicializando parametros de JPA");
		JpaManager.initEntityManagerFactory();
	}

	/**
	 * Contesto de aplicacion finalizada
	 *      
	 * @param sce
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("Terminando parametros de JPA");
		if (JpaManager.hbSessionFactory == null) {
			System.out.println("hibernate session factory null");
		} else {
			if (JpaManager.hbSessionFactory.isClosed()) {
				System.out.println("factory cerrado");
			} else {
				System.out.println("Parametros de estadisticas");
				System.out.println("tostring //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.toString());
				System.out.println("getQueryExecutionMaxTimeQueryString //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryExecutionMaxTimeQueryString());
				System.out.println("getEntityLoadCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getEntityLoadCount());
				System.out.println("getConnectCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getConnectCount());
				System.out.println("getQueryCacheHitCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryCacheHitCount());
				System.out.println("getQueryCacheMissCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryCacheMissCount());
				System.out.println("getQueryCacheMissCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryCacheMissCount());
				System.out.println("getQueryCachePutCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryCachePutCount());
				System.out.println("getQueryExecutionCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryExecutionCount());
				System.out.println("getQueryExecutionMaxTime //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getQueryExecutionMaxTime());
				System.out.println("getSecondLevelCacheHitCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getSecondLevelCacheHitCount());
				System.out.println("getSecondLevelCacheMissCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getSecondLevelCacheMissCount());
				System.out.println("getSecondLevelCachePutCount //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.getSecondLevelCachePutCount());
				System.out.println("isStatisticsEnabled //"
						+ JpaManager.hbSessionFactory.getStatistics()
								.isStatisticsEnabled());

				JpaManager.hbSessionFactory.getStatistics().logSummary();
			}
		}
		JpaManager.EndEntityManagerFactory();
	}

	/**
	 * 
	 * Request destruido, EntityManager se cierra
	 * 
	 * @param sre
	 */

	public void requestDestroyed(ServletRequestEvent sre) {
		EntityManager em = (EntityManager) sre.getServletRequest()
				.getAttribute(EntityListener.nombreEm);

		if (em != null && !em.isOpen()) {
			em.close();
			em = null;
			sre.getServletRequest().removeAttribute(EntityListener.nombreEm);
			sre.getServletRequest().removeAttribute("realem");
		}
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	/*
	 * public void requestInitialized(ServletRequestEvent sre) { EntityManager
	 * realem = JpaManager.getServerEntityManager();
	 * sre.getServletRequest().setAttribute("realem", realem);
	 * sre.getServletRequest().setAttribute(EntityListener.nombreEm, realem); }
	 */
}

