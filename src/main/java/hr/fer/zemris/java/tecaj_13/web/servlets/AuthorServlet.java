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
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.util.Constants;

@WebServlet("/servleti/author/*")
public class AuthorServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String extra = req.getPathInfo();

		if (extra == null) {
			return;
		}

		String[] extraArray = extra.split("/");

		if (extraArray.length < 2) {
			// error
			return;
		}

		req.setAttribute("nickname", extraArray[1]);

		if (extraArray.length == 2) {
			BlogUser user=DAOProvider.getDAO().getUser(extraArray[1]);
			List<BlogEntry> entries = DAOProvider.getDAO().getBlogEntries(user.getId());
			req.setAttribute("entries", entries);

			req.getRequestDispatcher("/WEB-INF/pages/author.jsp");

			return;
		}

		if (extraArray[2].equals("new") || extraArray[2].equals("edit")) {
			if (req.getSession().getAttribute(Constants.NICK).equals(extraArray[1])) {
				if (extraArray[2].equals("new")) {
					req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/newentry.jsp");
				} else if (extraArray[2].equals("edit")) {
					req.getRequestDispatcher(req.getContextPath() + "/servleti/edit.jsp");
				}
			} else {
				req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/validationError.jsp");
			}
		} else {
			try {
				long entryId = Long.parseLong(extraArray[2]);
				
				BlogEntry entry = DAOProvider.getDAO().getBlogEntry(entryId);

				if (entry == null || !entry.getCreator().getNick().equals(extraArray[1])) {
					req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/validationError.jsp");
					return;
				}

				req.setAttribute("entry", entry);
				req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/entry.jsp").forward(req, resp);
			} catch (NumberFormatException e) {
				throw new RuntimeException();
			}
		}
	}
}
