package controller;

import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;
import model.User;
import model.Vaccine;

@WebServlet("/NewPatient")
public class NewPatient extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public NewPatient() {
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
		}
		else {
			request.getRequestDispatcher("/WEB-INF/NewPatient.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DbService db = new DbService();
		String vaccineName = request.getParameter("vaccineName");
		Vaccine vaccine = db.getVaccineFromName(vaccineName);
		String patientName = request.getParameter("patientName");

		if (patientName == "") {
			response.sendRedirect("NewPatient");
			return;
		}

		Date date = new Date();
		Patient patient = new Patient(patientName, vaccine, date, null);
		db.insertPatient(patient);
		db.subtractDoses(vaccine.getId());
		db.close();
		response.sendRedirect("ListPatients");
	}
}