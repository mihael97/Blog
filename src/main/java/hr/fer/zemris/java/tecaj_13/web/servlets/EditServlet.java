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
 * Class provides implementation for {@link BlogEntry} editing
 * 
 * @author Mihael
 *
 */
@WebServlet("/servleti/edit")
public class EditServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method after splitting parameters form URL,updates entry
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long entryID = Long.parseLong(req.getParameter("entryID"));
		String text = req.getParameter("text");
		String title = req.getParameter("title");

		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(entryID);

		entry.setText(text);
		entry.setTitle(title);
		entry.setLastModifiedAt(new Date());

		DAOProvider.getDAO().editEntry(entry);

		resp.sendRedirect(
				req.getContextPath() + "/servleti/author/" + (String) req.getSession().getAttribute(Constants.NICK));
	}
}
