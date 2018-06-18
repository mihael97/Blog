package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;

/**
 * Class extends {@link HttpServlet} and provides implementation for catching
 * entries from database
 * 
 * @author Mihael
 *
 */
@WebServlet("/servleti/catchentry")
public class EntryCatchingServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method receives nickname and entry id attribute via request and dispatches
	 * request to page with entry. If entry doesn't exist or some of the parameters
	 * are invalid, error page will be shown
	 * 
	 * @param req
	 *            - request
	 * @param resp
	 *            - response
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			long entryId = Long.parseLong(String.valueOf(req.getAttribute("entryID")));

			BlogEntry entry = DAOProvider.getDAO().getBlogEntry(entryId);

			if (entry == null || !entry.getCreator().getNick().equals(String.valueOf(req.getAttribute("nickname")))) {
				req.getRequestDispatcher("/WEB-INF/pages/validationError.jsp").forward(req, resp);
				return;
			}

			req.setAttribute("entry", entry);
			req.getRequestDispatcher("/WEB-INF/pages/entry.jsp").forward(req, resp);
		} catch (NumberFormatException e) {
			req.setAttribute("errorMessage", "Entry number doesn't exist or it is not parsable!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
		}
	}
}
