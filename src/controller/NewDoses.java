package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;

@WebServlet("/NewDoses")
public class NewDoses extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewDoses() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService db = new DbService();
		request.setAttribute("entries", db.getVaccines());
		db.close();
		
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		
		if (loggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
			return;
		}
		if (loggedIn.isAdminastrator()) {
			request.getRequestDispatcher("/WEB-INF/NewDoses.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/Unathaurized.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String vaccineName = request.getParameter("vaccineName");
		String dosesRecievedString = request.getParameter("dosesRecieved");

		if (dosesRecievedString == "") {
			response.sendRedirect("NewDoses");
			return;
		}

		int dosesRecieved = Integer.parseInt(dosesRecievedString);

		DbService db = new DbService();
		db.newDoses(vaccineName, dosesRecieved);
		db.close();
		response.sendRedirect("ListVaccines");
	}

}
