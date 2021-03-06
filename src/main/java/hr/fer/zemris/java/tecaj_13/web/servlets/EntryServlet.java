package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.util.Constants;

/**
 * Class provides implementation for entry creating
 * 
 * @author Mihael
 *
 */
@WebServlet("/servleti/newentry")
public class EntryServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method splits parameters from received request and creates new entry
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * 
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String title = req.getParameter("title");
		String text = req.getParameter("text");
		String nick = (String) req.getSession().getAttribute(Constants.NICK);

		BlogEntry entry = new BlogEntry();

		entry.setCreator(DAOProvider.getDAO().getUser(nick));
		entry.setText(text);
		entry.setTitle(title);
		entry.setCreatedAt(new Date());
		entry.setLastModifiedAt(new Date());

		DAOProvider.getDAO().addEntry(entry);

		resp.sendRedirect(req.getContextPath() + "/servleti/author/" + nick);
	}
}
