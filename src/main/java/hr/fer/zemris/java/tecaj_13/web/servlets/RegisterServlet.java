package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
@WebServlet("/servleti/register")
public class RegisterServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method accepts registration parameters by URL parameters and checks if user
	 * can be registered.<br>
	 * If user with same user name doesn't exist,method will register new user.
	 * Otherwise,request is redirected to registration page with appropriate message
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nick = req.getParameter("nick");
		String email = req.getParameter("email");

		if (checkParameters(nick, email, req)) {
			BlogUser blogUser = new BlogUser();
			blogUser.setNick(nick);
			blogUser.setEmail(email);
			blogUser.setFirstName(req.getParameter("firstName"));
			blogUser.setLastName(req.getParameter("lastName"));
			blogUser.setPasswordHash(Util.hashPassword(req.getParameter("password")));

			System.out.println("\n\nFor user " + blogUser.getNick() + "=" + blogUser.getPasswordHash());

			DAOProvider.getDAO().addUser(blogUser);

			req.getRequestDispatcher("/WEB-INF/pages/index.jsp").forward(req, resp);
		} else { // error happened
			req.getRequestDispatcher("/WEB-INF/pages/register.jsp");
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

		boolean resposen = DAOProvider.getDAO().getUser(nick) == null;
		System.out.println(resposen);
		return resposen;
	}
}
