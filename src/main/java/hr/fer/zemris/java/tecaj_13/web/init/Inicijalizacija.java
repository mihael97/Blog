package hr.fer.zemris.java.tecaj_13.web.init;

import hr.fer.zemris.java.tecaj_13.dao.jpa.JPAEMFProvider;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Class represents {@link ServletContextListener} which listens when
 * application is starting.<br>
 * After starting moment,class creates new {@link EntityManagerFactory}
 * 
 * @author Mihael
 *
 */
@WebListener
public class Inicijalizacija implements ServletContextListener {

	/**
	 * During application starting,method creates new {@link EntityManagerFactory}
	 * 
	 * @param sce
	 *            - servlet context event
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("\n\n\nHAHAHA\n\n");
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("baza.podataka.za.blog");

		System.out.println("\n\n\nHAHAHA\n\n");
		sce.getServletContext().setAttribute("my.application.emf", emf);
		JPAEMFProvider.setEmf(emf);
	}

	/**
	 * During application closing,method closes current opened
	 * {@link EntityManagerFactory}
	 * 
	 * @param sce
	 *            - servlet context event
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		JPAEMFProvider.setEmf(null);
		EntityManagerFactory emf = (EntityManagerFactory) sce.getServletContext().getAttribute("my.application.emf");
		if (emf != null) {
			emf.close();
		}
	}
}