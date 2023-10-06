package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Patient;

@WebServlet("/Received")
public class Received extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Received() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String query = request.getQueryString();
		int id = Integer.parseInt(query.substring(3));
		DbService db = new DbService();
		Patient patient = db.getPatient(id);
		db.subtractDoses(patient.getVaccine().getId());
		db.updatePatientSecondDate(id);
		response.sendRedirect("ListPatients");
	}
}