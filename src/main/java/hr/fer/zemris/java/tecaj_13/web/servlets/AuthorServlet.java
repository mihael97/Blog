package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.util.Constants;

/**
 * Class represents {@link HttpServlet} that has implementation for
 * editing,creating and catching entries by owner
 * 
 * @author Mihael
 *
 */
@WebServlet("/servleti/author/*")
public class AuthorServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method accepts URL from request. After splitting,depending on number and type
	 * of parameters,it redirects operation to specific {@link HttpServlet}
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String extra = req.getPathInfo();

		String[] extraArray = extra.split("/");

		if (extraArray.length < 2) {
			req.setAttribute("errorMessage", "Number of aruments must be at least two!");
			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);

			return;
		}

		req.setAttribute("nickname", extraArray[1]);

		if (extraArray.length == 2) {
			List<BlogEntry> entries = DAOProvider.getDAO().getBlogEntries(extraArray[1]);
			req.setAttribute("entries", entries);

			req.getRequestDispatcher("/WEB-INF/pages/author.jsp").forward(req, resp);

			return;
		}

		if (extraArray[2].equals("new") || extraArray[2].equals("edit")) {
			if (req.getSession().getAttribute(Constants.NICK).equals(extraArray[1])) {
				if (extraArray[2].equals("new")) {
					req.getRequestDispatcher("/WEB-INF/pages/newentry.jsp").forward(req, resp);
				} else if (extraArray[2].equals("edit")) {
					req.setAttribute("entryID", extraArray[3]);
					req.getRequestDispatcher("/WEB-INF/pages/edit.jsp").forward(req, resp);
				}
			} else {
				req.getRequestDispatcher("/WEB-INF/pages/validationError.jsp").forward(req, resp);
			}
		} else {
			req.setAttribute("entryID", extraArray[2]);
			req.getRequestDispatcher("/servleti/catchentry").forward(req, resp);
		}
	}
}
