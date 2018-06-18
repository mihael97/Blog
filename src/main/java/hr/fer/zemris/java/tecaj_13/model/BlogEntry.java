package hr.fer.zemris.java.tecaj_13.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Class represents blog entry
 * 
 * @author Mihael
 *
 */
@NamedQueries({
		@NamedQuery(name = "BlogEntry.upit1", query = "select b from BlogComment as b where b.blogEntry=:be and b.postedOn>:when") })
@Entity
@Table(name = "blog_entries")
@Cacheable(true)
public class BlogEntry {

	/**
	 * Entry's id
	 */
	private Long id;
	/**
	 * List of {@link BlogComment}s
	 */
	private List<BlogComment> comments = new ArrayList<>();
	/**
	 * Date when entry was created
	 */
	private Date createdAt;
	/**
	 * Date when entry was last modified
	 */
	private Date lastModifiedAt;
	/**
	 * Entry's title
	 */
	private String title;
	/**
	 * Text stored in entry
	 */
	private String text;
	/**
	 * Entry's owner
	 */
	private BlogUser creator;

	/**
	 * Method returns entry's id<br>
	 * Also,id is <code>primary key</code> in database
	 * 
	 * @return entry's id
	 */
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	/**
	 * Method sets entry's id
	 * 
	 * @param id
	 *            - new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Method returns list of comments for entry
	 * 
	 * @return list of entry's comments
	 */
	@OneToMany(mappedBy = "blogEntry", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST, orphanRemoval = true)
	@OrderBy("postedOn")
	public List<BlogComment> getComments() {
		return comments;
	}

	/**
	 * Method sets comments about entry
	 * 
	 * @param comments
	 *            - new comments
	 */
	public void setComments(List<BlogComment> comments) {
		this.comments = comments;
	}

	/**
	 * Method returns date when entry was created
	 * 
	 * @return date when entry was created
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Method sets date when entry is created
	 * 
	 * @param createdAt
	 *            - new date
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Method returns date when entry was last modified
	 * 
	 * @return date when entry was last modified
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	public Date getLastModifiedAt() {
		return lastModifiedAt;
	}

	/**
	 * Method sets date when entry is last time modified
	 * 
	 * @param lastModifiedAt
	 *            - date of last modification
	 */
	public void setLastModifiedAt(Date lastModifiedAt) {
		this.lastModifiedAt = lastModifiedAt;
	}

	/**
	 * Method returns entry's title
	 * 
	 * @return entry's title
	 */
	@Column(length = 200, nullable = false)
	public String getTitle() {
		return title;
	}

	/**
	 * Method sets new entry's title
	 * 
	 * @param title
	 *            - entry's title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * Method returns text stored with entry
	 * 
	 * @return entry's text
	 */
	@Column(length = 4096, nullable = false)
	public String getText() {
		return text;
	}

	/**
	 * Method sets entry's text
	 * 
	 * @param text
	 *            - entry's text
	 */
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * Method returns entry's creator
	 * 
	 * @return entry's creator
	 */
	@ManyToOne
	@JoinColumn(nullable = false)
	public BlogUser getCreator() {
		return creator;
	}

	/**
	 * Method sets entry's creator
	 * 
	 * @param creator
	 *            - entry's creator
	 */
	public void setCreator(BlogUser creator) {
		this.creator = creator;
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
		BlogEntry other = (BlogEntry) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}