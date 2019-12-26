package com.ipartek.formacion.supermercado.controller.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.log4j.Logger;

/**
 * Application Lifecycle Listener implementation class ConexionListener
 *
 */
@WebListener
public class ConexionListener implements ServletContextListener {

	private final static Logger LOG = Logger.getLogger(ConexionListener.class);

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
//		Connection con = ConnectionManager.getConnection();
//		try {
//			if (con.isClosed()) {
//				LOG.info("La conexion a la BBDD se ha cerrado con exito");
//			}
//		} catch (SQLException e) {
//			LOG.info("ERROR al cerrar la conexion con la BBDD");
//			e.printStackTrace();
//		}
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		LOG.info("La App se ha arrancado");
//		Connection con = ConnectionManager.getConnection();
//
//		try
//
//		{
//			if (con != null && con.isValid(0)) {
//				LOG.info("La conexion a la BBDD se ha realizado con exito");
//			}
//		} catch (SQLException e) {
//			LOG.info("ERROR al realizar la conexion con la BBDD");
//			ServletContext sc = sce.getServletContext();
//			sc.getRequestDispatcher("/error.jsp");
//			e.printStackTrace();
//		}
//
//	}

	}
}
