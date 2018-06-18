package hr.fer.zemris.java.tecaj_13.dao;

import java.util.List;

import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;

/**
 * Interface contains all method that DAO provider must implements
 * 
 * @author Mihael
 *
 */
public interface DAO {

	/**
	 * Dohvaća entry sa zadanim <code>id</code>-em. Ako takav entry ne postoji,
	 * vraća <code>null</code>.
	 * 
	 * @param id
	 *            ključ zapisa
	 * @return entry ili <code>null</code> ako entry ne postoji
	 * @throws DAOException
	 *             ako dođe do pogreške pri dohvatu podataka
	 */
	public BlogEntry getBlogEntry(Long id) throws DAOException;

	/**
	 * Method checks if there is user with given user name and password
	 * 
	 * @param user
	 *            - user name
	 * @param password
	 *            - password
	 * @return <code>true</code> if there is user with given user name and
	 *         password,otherwise <code>false</code>
	 */
	public boolean checkUser(String user, String password);

	/**
	 * Method returns user which has given user name
	 * 
	 * @param username
	 *            - user name
	 * @return user with given user name
	 */
	public BlogUser getUser(String username);

	/**
	 * Method returns list of registered users
	 * 
	 * @return {@link List} of registered users
	 */
	public List<BlogUser> getUsers();

	/**
	 * Method returns all entries made by given user
	 * 
	 * @param username
	 *            - user name
	 * @return {@link List} of {@link BlogEntry}s which owner is given user
	 */
	public List<BlogEntry> getBlogEntries(String username);

	/**
	 * Method adds comment to blog entry
	 * 
	 * @param comment
	 *            - comment
	 */
	public void addComment(BlogComment comment);

	/**
	 * Method updates entry
	 * 
	 * @param entry
	 *            - entry
	 */
	public void editEntry(BlogEntry entry);

	/**
	 * Method adds entry
	 * 
	 * @param entry
	 *            - entry
	 */
	public void addEntry(BlogEntry entry);

	/**
	 * Method adds user after registration
	 * 
	 * @param blogUser
	 *            - blog user
	 */
	public void addUser(BlogUser blogUser);

}