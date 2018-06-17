package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogEntry;
import hr.fer.zemris.java.tecaj_13.util.Constants;

@WebServlet("/servleti/edit")
public class EditServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Long entryID = Long.parseLong(req.getParameter("entryID"));
		String text = req.getParameter("text");
		String title = req.getParameter("title");

		BlogEntry entry = DAOProvider.getDAO().getBlogEntry(entryID);

		entry.setText(text);
		entry.setTitle(title);

		DAOProvider.getDAO().editEntry(entry);

		resp.sendRedirect(
				req.getContextPath() + "/servleti/author/" + (String) req.getSession().getAttribute(Constants.NICK));
	}
}
