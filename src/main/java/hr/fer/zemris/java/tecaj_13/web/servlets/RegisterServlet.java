package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import hr.fer.zemris.java.tecaj_13.dao.DAOProvider;
import hr.fer.zemris.java.tecaj_13.model.BlogUser;
import hr.fer.zemris.java.tecaj_13.util.Util;

/**
 * Class provides implementation for user registration<br>
 * Method accepts parameters via <code>POST request</code> from registration
 * page. If all parameters are valid, class registers user with given
 * values.<br>
 * Otherwise if some argument is not valid,class passes through all given
 * parameters,puts all invalid parameters(their appropriate messages) in
 * attributes map and redirects request to registration page where user is asked
 * to change parameters to valid values
 * 
 * @author Mihael
 *
 */
public class RegisterServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Email pattern
	 */
	private static final String EMAIL_PATTERN = "^[\\\\w!#$%&’*+/=?`{|}~^-]+(?:\\\\.[\\\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,6}$";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");

		if (checkParameters(nick, email, req)) {
			BlogUser blogUser = new BlogUser();
			blogUser.setNick(nick);
			blogUser.setEmail(email);
			blogUser.setFirstName(req.getParameter("firstName"));
			blogUser.setLastName(req.getParameter("lastName"));
			blogUser.setPasswordHash(Util.hashPassword(req.getParameter("password")));
		} else { // error happened
			req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/register.jsp");
		}
	}

	/**
	 * Method checks validation of parameters
	 * 
	 * @param nick
	 *            - nickname
	 * @param eMail
	 *            - email
	 * @param req
	 *            - request where we will store error description if some parameter
	 *            is not valid
	 * @return <code>true</code> if parameters are valid,otherwise
	 *         <code>false</code>
	 */
	private boolean checkParameters(String nick, String eMail, HttpServletRequest req) {

		return checkEmail(eMail, req) && checkNick(nick, req);
	}

	/**
	 * Method checks if nickname is valid<br>
	 * Nickname is valid if it is <code>null</code> or there is not registered user
	 * with same nickname
	 * 
	 * @param nick
	 *            - nickname
	 * @param req
	 *            - request
	 * @return <code>true</code> if nickname is valid,otherwise <code>false</code>
	 */
	private boolean checkNick(String nick, HttpServletRequest req) {
		if (nick == null) {
			return true;
		}

		return !DAOProvider.getDAO().checkNick(nick);
	}

	/**
	 * With pattern usage,method checks if given <code>email</code> is in valid form
	 * 
	 * @param eMail
	 *            - given email
	 * @param req
	 *            - request
	 * @return <code>true</code> if email is valid,otherwise <code>false</code>
	 */
	private boolean checkEmail(String eMail, HttpServletRequest req) {
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);

		if (!pattern.matcher(eMail).matches()) {
			req.setAttribute("errorEmail", "Email address in not in valid form!");
			return false;
		}

		return true;
	}
}
