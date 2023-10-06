package pageRequest;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.DbService;
import model.User;

@WebServlet("/ListPatients")
public class ListPatients extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService db = new DbService();
		request.setAttribute("patients", db.getPatients());
		db.close();
		
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		
		if (loggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/ListPatients.jsp").forward(request, response);
		}
		
	}

}