package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/servleti/comment")
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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String message = req.getParameter("message");
		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(Long.parseLong(req.getParameter("entryID")));
		BlogUser creator = DAOProvider.getDAO().getUser(String.valueOf(req.getSession().getAttribute(Constants.NICK)));
		if (creator == null) {
			req.setAttribute("errorMessage",
					"For posting comments you must be logged in. Go to main page for logging in or creating account");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}
		BlogComment comment = new BlogComment();

		comment.setMessage(message);
		comment.setBlogEntry(entry);
		comment.setUsersEMail(creator.getEmail());
		comment.setPostedOn(new Date());

		DAOProvider.getDAO().addComment(comment);

		resp.sendRedirect(
				req.getContextPath() + "/servleti/author/" + entry.getCreator().getNick() + "/" + entry.getId());
	}
}
