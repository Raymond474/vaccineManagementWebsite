package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.Vaccine;

@WebServlet("/EditVaccine")
public class EditVaccine extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EditVaccine() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService db = new DbService();
		Vaccine entry = db.getVaccine(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("entry", entry);
		db.close();
		
		User loggedIn = (User) request.getServletContext().getAttribute("user");
		
		if (loggedIn == null) {
			request.getRequestDispatcher("/WEB-INF/Login.jsp").forward(request, response);
			return;
		}
		if (loggedIn.isAdminastrator()) {
			request.getRequestDispatcher("/WEB-INF/EditVaccine.jsp").forward(request, response);
		}
		else {
			request.getRequestDispatcher("/WEB-INF/Unathaurized.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService db = new DbService();
		int id = Integer.parseInt(request.getParameter("id"));
		String vaccineName = request.getParameter("vaccineName");
		int dosesRequired = Integer.parseInt(request.getParameter("dosesRequired"));
		int daysBetweenDoses;
		
		if (dosesRequired == 1) {
			daysBetweenDoses = 0;
		}
		else if (dosesRequired == 2 && request.getParameter("daysBetweenDoses") == "" || Integer.parseInt(request.getParameter("daysBetweenDoses")) == 0) {
			daysBetweenDoses = db.getVaccine(id).getDaysBetweenDoses();
		}
		else {
			daysBetweenDoses = Integer.parseInt(request.getParameter("daysBetweenDoses"));
		}
		
		db.updateVaccine(id, vaccineName, dosesRequired, daysBetweenDoses);
		db.close();

		response.sendRedirect("ListVaccines");
	}
}