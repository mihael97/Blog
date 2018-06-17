package hr.fer.zemris.java.tecaj_13.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import hr.fer.zemris.java.tecaj_13.dao.DAO;
import hr.fer.zemris.java.tecaj_13.dao.DAOException;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Class implements interface {@link DAO} and contains implementation of methods
 * provided by interface
 * 
 * @author Mihael
 *
 */
public class JPADAOImpl implements DAO {

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#getBlogEntry(java.lang.Long)
	 */
	@Override
	public BlogEntry getBlogEntry(Long id) throws DAOException {
		return JPAEMProvider.getEntityManager().find(BlogEntry.class, id);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#checkUser(java.lang.String,
	 *      java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public boolean checkUser(String user, String password) {
		Query query = JPAEMProvider.getEntityManager()
				.createQuery("SELECT a FROM BlogUser AS a WHERE a.nick=:z AND a.passwordHash=:o");
		query.setParameter("z", user);
		query.setParameter("o", password);
		List<BlogUser> users = query.getResultList();
		return (users != null && users.size() != 0);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#getUser(java.lang.String)
	 */
	@Override
	public BlogUser getUser(String userName) {
		@SuppressWarnings("unchecked")
		List<BlogUser> users = JPAEMProvider.getEntityManager()
				.createQuery("SELECT a FROM BlogUser AS a WHERE a.nick=:z").setParameter("z", userName).getResultList();
		return users != null && users.size() != 0 ? users.get(0) : null;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#getUsers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BlogUser> getUsers() {
		return JPAEMProvider.getEntityManager().createQuery("SELECT DISTINCT a FROM BlogUser AS a").getResultList();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#getBlogEntries(java.lang.Long)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<BlogEntry> getBlogEntries(Long id) {
		BlogUser user = JPAEMProvider.getEntityManager().find(BlogUser.class, id);
		return JPAEMProvider.getEntityManager().createQuery("select b from BlogEntry as b where b.creator=:creator")
				.setParameter("creator", user).getResultList();

	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#addComment(hr.fer.zemris.java.tecaj_13.model.BlogComment)
	 */
	@Override
	public void addComment(BlogComment comment) {
		JPAEMProvider.getEntityManager().persist(comment);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#editEntry(hr.fer.zemris.java.tecaj_13.model.BlogEntry)
	 */
	@Override
	public void editEntry(BlogEntry entry) {
		JPAEMProvider.getEntityManager().merge(entry);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#addEntry(hr.fer.zemris.java.tecaj_13.model.BlogEntry)
	 */
	@Override
	public void addEntry(BlogEntry entry) {
		JPAEMProvider.getEntityManager().persist(entry);
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see hr.fer.zemris.java.tecaj_13.dao.DAO#addUser(hr.fer.zemris.java.tecaj_13.model.BlogUser)
	 */
	@Override
	public void addUser(BlogUser blogUser) {
		JPAEMProvider.getEntityManager().persist(blogUser);
	}

}