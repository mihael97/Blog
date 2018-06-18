package hr.fer.zemris.java.tecaj_13.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class impelments every comment which can be posted with {@link BlogEntry}<br>
 * Comments can be posted only by registered users
 * 
 * @author Mihael
 *
 */
@Entity
@Table(name = "blog_comments")
public class BlogComment {

	/**
	 * Comment's id
	 */
	private Long id;
	/**
	 * Entry where comments is posted
	 */
	private BlogEntry blogEntry;
	/**
	 * EMail of user that posted comment
	 */
	private String usersEMail;
	/**
	 * Comment message
	 */
	private String message;
	/**
	 * Date when comment is posted
	 */
	private Date postedOn;

	/**
	 * Method returns comment's id
	 * 
	 * @return comment's id
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Method sets comment's id
	 * 
	 * @param id
	 *            - new comment's id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method returns {@link BlogEntry} where comment is stored
	 * 
	 * @return {@link BlogEntry}
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public BlogEntry getBlogEntry() {
		return blogEntry;
	}

	/**
	 * Method sets {@link BlogEntry} where comment is posted
	 * 
	 * @param blogEntry
	 *            - {@link BlogEntry} where comments is posted
	 */
	public void setBlogEntry(BlogEntry blogEntry) {
		this.blogEntry = blogEntry;
	}

	/**
	 * Method sets email of comment's creator
	 * 
	 * @return email of comment's creator
	 */
	@Column(length = 100, nullable = false)
	public String getUsersEMail() {
		return usersEMail;
	}

	/**
	 * Method sets email of comment's creator
	 * 
	 * @param usersEMail
	 *            - email of comment's creator
	 */
	public void setUsersEMail(String usersEMail) {
		this.usersEMail = usersEMail;
	}

	/**
	 * Method returns comment message
	 * 
	 * @return comment message
	 */
	@Column(length = 4096, nullable = false)
	public String getMessage() {
		return message;
	}

	/**
	 * Method sets comment's message
	 * 
	 * @param message
	 *            - new comment's message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Method returns date when comment is posted
	 * 
	 * @return date when comment is posted
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getPostedOn() {
		return postedOn;
	}

	/**
	 * Method sets date when comment is posted
	 * 
	 * @param postedOn
	 *            - date when comment is posted
	 */
	public void setPostedOn(Date postedOn) {
		this.postedOn = postedOn;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/**
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlogComment other = (BlogComment) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}