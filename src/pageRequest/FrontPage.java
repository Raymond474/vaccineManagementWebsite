package pageRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/FrontPage")
public class FrontPage extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		
		if (loggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/FrontPage.jsp").forward(request, response);
		}
		
	}

}