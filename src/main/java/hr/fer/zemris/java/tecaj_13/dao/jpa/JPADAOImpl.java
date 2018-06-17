package hr.fer.zemris.java.tecaj_13.dao.jpa;

import java.util.List;

import javax.persistence.Query;

import hr.fer.zemris.java.tecaj_13.dao.DAO;
import hr.fer.zemris.java.tecaj_13.dao.DAOException;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

public class JPADAOImpl implements DAO {

	@Override
	public BlogEntry getBlogEntry(Long id) throws DAOException {
		return JPAEMProvider.getEntityManager().find(BlogEntry.class, id);
	}

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

	@Override
	public BlogUser getUser(String userName) {
		@SuppressWarnings("unchecked")
		List<BlogUser> users = JPAEMProvider.getEntityManager()
				.createQuery("SELECT a FROM BlogUser AS a WHERE a.nick=:z").setParameter("z", userName).getResultList();
		return users != null && users.size() != 0 ? users.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogUser> getUsers() {
		return JPAEMProvider.getEntityManager().createQuery("SELECT DISTINCT a FROM BlogUser AS a").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BlogEntry> getBlogEntries(Long id) {
		try {
			BlogUser user = JPAEMProvider.getEntityManager().find(BlogUser.class, id);
			return (user != null)
					? JPAEMProvider.getEntityManager()
							.createQuery("SELECT DISTINCT a FROM BlogEntry AS a WHERE a.creator=:o")
							.setParameter("o", user).getResultList()
					: null;
		} catch (Exception ex) {
			throw new DAOException("An error has occurred while obtaining the list of entries.", ex);
		}
	}

	@Override
	public void addComment(BlogComment comment) {
		JPAEMProvider.getEntityManager().persist(comment);
	}

	@Override
	public void editEntry(BlogEntry entry) {
		JPAEMProvider.getEntityManager().merge(entry);
	}

	@Override
	public void addEntry(BlogEntry entry) {
		JPAEMProvider.getEntityManager().persist(entry);
	}

	@Override
	public void addUser(BlogUser blogUser) {
		JPAEMProvider.getEntityManager().persist(blogUser);
	}

}