package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.util.Constants;
import hr.fer.zemris.java.tecaj_13.util.Util;

/**
 * Class extends {@link HttpServlet} and provides functionality for login
 * 
 * @author Mihael
 *
 */
@WebServlet("/servleti/login")
public class LoginServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method receives login paramters by URL parameters and checks if user with
	 * given password and user name exists
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user = req.getParameter("nick");
		String password = req.getParameter("password");
		System.out.println("\n\n\nLogin for user " + user + " with password " + Util.hashPassword(password));

		if (req.getSession().getAttribute(Constants.NICK) != null) {// somebody is alredy registered
			req.setAttribute("errorMessage",
					"Other user is registered. Log out from registered user and then try again");

			req.getRequestDispatcher("/WEB-INF/pages/error.jsp").forward(req, resp);
			return;
		}

		if (DAOProvider.getDAO().checkUser(user, Util.hashPassword(password))) {
			addInSession(req, user, password);
		} else {
			req.setAttribute("nickname", user);
			req.setAttribute("loginError", "Invalid user name or password");
		}

		req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);

	}

	/**
	 * Method adds in <code>request session</code> informations about current active
	 * user
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param userName
	 *            - user name
	 * @param password
	 *            - password
	 */
	private void addInSession(HttpServletRequest req, String userName, String password) {
		BlogUser user = DAOProvider.getDAO().getUser(userName);

		req.getSession().setAttribute(Constants.ID, user.getId());
		req.getSession().setAttribute(Constants.FIRST_NAME, user.getFirstName());
		req.getSession().setAttribute(Constants.LAST_NAME, user.getLastName());
		req.getSession().setAttribute(Constants.NICK, user.getNick());
	}
}
