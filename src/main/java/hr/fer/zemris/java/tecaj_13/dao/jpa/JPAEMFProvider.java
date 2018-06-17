package hr.fer.zemris.java.tecaj_13.dao.jpa;

import javax.persistence.EntityManagerFactory;

/**
 * JPAEM provider
 * 
 * @author Mihael
 *
 */
public class JPAEMFProvider {

	/**
	 * Reference to entity manager factory
	 */
	public static EntityManagerFactory emf;

	/**
	 * Method returns current entity manager factory
	 * 
	 * @return instance of entity manager factory
	 */
	public static EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * Methods sets active entity manager factory
	 * 
	 * @param emf
	 *            - factory
	 */
	public static void setEmf(EntityManagerFactory emf) {
		JPAEMFProvider.emf = emf;
	}
}