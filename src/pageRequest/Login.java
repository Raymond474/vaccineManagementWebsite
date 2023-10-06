package pageRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DbService;
import model.User;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		if (loggedIn != null) {
			request.getRequestDispatcher("/WEB-INF/FrontPage.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService db = new DbService();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String adminastratorString = request.getParameter("adminastrator");
		boolean adminastrator = false;
		
		if (adminastratorString != null) {
			adminastrator = true;
		}
		
		User user = new User(adminastrator, username, password, name);
		Boolean passed = db.doesUserExist(user);
		
		if (!passed) {
			response.sendRedirect("Login");
			return;
		}
		
		getServletContext().setAttribute("user", user);//request
		db.close();
		response.sendRedirect("FrontPage");
	}
}