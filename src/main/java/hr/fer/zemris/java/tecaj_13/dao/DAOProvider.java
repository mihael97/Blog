package hr.fer.zemris.java.tecaj_13.dao;

import hr.fer.zemris.java.tecaj_13.dao.jpa.JPADAOImpl;

/**
 * Class represents DAO provider
 * 
 * @author Mihael
 *
 */
public class DAOProvider {

	/**
	 * DAO provider
	 */
	private static DAO dao = new JPADAOImpl();

	/**
	 * Method returns DAO provider(in our case {@link JPADAOImpl})
	 * 
	 * @return DAO provider
	 */
	public static DAO getDAO() {
		return dao;
	}

}