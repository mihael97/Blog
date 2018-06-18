package hr.fer.zemris.java.tecaj_13.model;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Class represents every blog user <br>
 * Every blog user is described with attributes:
 * <ol>
 * <li>id</li>
 * <li>first and last name</li>
 * <li>password calculated as SHA-1 hash</li>
 * <li>nickname</li>
 * <li>email</li>
 * </ol>
 * <br>
 * 
 * One of attributes requirements is that <code>nickname</code> must be
 * unique,there cannot be two users with same nickname
 * 
 * @author Mihael
 *
 */
@Entity
@Table(name = "blog_users")
@Cacheable(true)
public class BlogUser {
	/**
	 * User's id
	 */
	private Long id;
	/**
	 * User's first name
	 */
	private String firstName;
	/**
	 * User's last name
	 */
	private String lastName;
	/**
	 * User's nickname
	 */
	private String nick;
	/**
	 * User's hashed password
	 */
	private String passwordHash;
	/**
	 * User's email address
	 */
	private String email;

	/**
	 * List of all {@link BlogEntry}'s made by user
	 */
	private List<BlogEntry> entries;

	/**
	 * Constructor for new blog user
	 * 
	 * @param id
	 *            - blog user's id
	 * @param firstName
	 *            - users's first name
	 * @param lastName
	 *            - users's last name
	 * @param nick
	 *            - users's nickname
	 * @param passwordHash
	 *            - users's password
	 * @param email
	 *            - users's email
	 */
	public BlogUser(Long id, String firstName, String lastName, String nick, String passwordHash, String email) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nick = nick;
		this.passwordHash = passwordHash;
		this.email = email;
	}

	/**
	 * Default constructor
	 */
	public BlogUser() {
	}

	/**
	 * Method returns user's id
	 * 
	 * @return user's id
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Method sets user's id
	 * 
	 * @param id
	 *            - new user's id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method returns user's first name
	 * 
	 * @return user's first name
	 */
	@Column(nullable = false)
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Method sets user's first name
	 * 
	 * @param firstName
	 *            - new user's first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Method returns user's last name
	 * 
	 * @return user's last name
	 */
	@Column(nullable = false)
	public String getLastName() {
		return lastName;
	}

	/**
	 * Method sets user's last name
	 * 
	 * @param lastName
	 *            - user's last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Method returns user's nickname
	 * 
	 * @return user's nickname
	 */
	@Column(nullable = false, unique = true)
	public String getNick() {
		return nick;
	}

	/**
	 * Method sets user's nickname
	 * 
	 * @param nick
	 *            - user's nickname
	 */
	public void setNick(String nick) {
		this.nick = nick;
	}

	/**
	 * Method returns user's password in hashed format
	 * 
	 * @return password in hashed format
	 */
	@Column(nullable = false)
	public String getPasswordHash() {
		return passwordHash;
	}

	/**
	 * Method sets hashed user's password
	 * 
	 * @param passwordHash
	 *            - new password
	 */
	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	/**
	 * Method returns current user's email
	 * 
	 * @return current stored email
	 */
	@Column(nullable = false)
	public String getEmail() {
		return email;
	}

	/**
	 * Method sets user's email address
	 * 
	 * @param email
	 *            - new users's email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Method returns list of {@link BlogEntry}s made by user
	 * 
	 * @return list of {@link BlogEntry}s
	 */
	@OneToMany(mappedBy = "creator", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	public List<BlogEntry> getEntries() {
		return entries;
	}

	/**
	 * Method sets list of {@link BlogEntry}'s created by user
	 * 
	 * @param entries
	 *            - entries
	 */
	public void setEntries(List<BlogEntry> entries) {
		this.entries = entries;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}

		return ((BlogUser) obj).id == id;
	}

}
