package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.Vaccine;

@WebServlet("/NewVaccine")
public class NewVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewVaccine() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		
		if (loggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
			return;
		}
		
		if (loggedIn.isAdminastrator()) {
			request.getRequestDispatcher("/WEB-INF/NewVaccine.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/Unathaurized.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String name = request.getParameter("vaccineName");
		String dosesRequiredString = request.getParameter("dosesRequired");
		String daysBetweenString = request.getParameter("daysBetweenDoses");

		if (name == "") {
			response.sendRedirect("NewVaccine");
			return;
		}

		int dosesRequired = Integer.parseInt(dosesRequiredString);
		int daysBetweenDoses = 0;

		if (dosesRequired == 2) {
			if (daysBetweenString == "") {
				response.sendRedirect("NewVaccine");
				return;
			}
			daysBetweenDoses = Integer.parseInt(daysBetweenString);
		}

		Vaccine entry = new Vaccine(name, dosesRequired, daysBetweenDoses);
		DbService db = new DbService();
		db.insertVaccine(entry);
		db.close();
		response.sendRedirect("ListVaccines");
	}

}
