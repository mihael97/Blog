package hr.fer.zemris.java.tecaj_13.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class has method for redirecting to main page
 * 
 * @author Mihael
 *
 */
@WebServlet("/servleti/main")
public class MainServlet extends HttpServlet {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Method redirects request to main page
	 * 
	 * @param req
	 *            - {@link HttpServletRequest}
	 * @param resp
	 *            - {@link HttpServletResponse}
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher(req.getContextPath() + "/WEB-INF/pages/index.jsp");
	}
}
