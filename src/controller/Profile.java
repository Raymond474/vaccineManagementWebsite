package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/Profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		
		if (loggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/Profile.jsp").forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DbService db = new DbService();
		User user = (User) getServletContext().getAttribute("user");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		db.updateUser(name, password, user.getUsername());
		user.setPassword(password);
		user.setName(name);
		getServletContext().setAttribute("user", user);
		
		db.close();
		response.sendRedirect("FrontPage");
	}

}
