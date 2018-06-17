package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogComment;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.util.Constants;

/**
 * Class implements {@link HttpServlet} with functionality for adding comments
 * to entries
 * 
 * @author Mihael
 *
 */
public class CommentServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method receives parameters with URL and creates new comment
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("message");
		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(Long.parseLong(req.getParameter("entryID")));
		BlogUser creator = DAOProvider.getDAO().getUser(String.valueOf(req.getSession().getAttribute(Constants.NICK)));

		BlogComment comment = new BlogComment();

		comment.setMessage(message);
		comment.setBlogEntry(entry);
		comment.setUsersEMail(creator.getEmail());
		comment.setPostedOn(new Date());

		resp.sendRedirect(
				req.getContextPath() + "/servleti/author/" + entry.getCreator().getNick() + "/" + entry.getId());
	}
}
