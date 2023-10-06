package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Patient;
import model.User;
import model.Vaccine;

public class DbService {

	private String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu28";

	private String username = "cs3220stu28";

	private String password = "*********";//password goes here

	private Connection connection;

	public DbService() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Vaccine> getVaccines() {
		List<Vaccine> entries = new ArrayList<Vaccine>();

		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from vaccines");

			while (rs.next()) {
				Vaccine vaccine = new Vaccine(rs.getInt(1), rs.getString("name"), rs.getInt("doses_required"),
						rs.getInt("days_between_doses"), rs.getInt("doses_received"), rs.getInt("doses_left"));
				entries.add(vaccine);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return entries;
	}

	public List<Patient> getPatients() {
		List<Patient> patients = new ArrayList<Patient>();

		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from patients");

			while (rs.next()) {
				Patient patient = new Patient(rs.getInt(1), rs.getString("name"), getVaccine(rs.getInt("vaccine_id")),
						rs.getDate("first_dose_date"), rs.getDate("second_dose_date"));
				patients.add(patient);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patients;
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<User>();

		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from users");

			while (rs.next()) {
				User user = new User(rs.getBoolean("adminastrator"), rs.getString("username"), rs.getString("password"),
						rs.getString("name"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public void insertUser(User user) {
		try {
			String sql = "insert into users (adminastrator, username, password, name) values (?, ?, ?, ?)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setBoolean(1, user.isAdminastrator());
			pstmt.setString(2, user.getUsername());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public boolean doesUserExist(User user) {
		try {
			Statement smt = connection.createStatement();
			int admin = 0;
			if (user.isAdminastrator()) {
				admin = 1;
			}
			
			ResultSet rs = smt.executeQuery("select * from users where username = '" + user.getUsername() + "' and password = '" + 
			user.getPassword() + "' and name = '" + user.getName() + "' and adminastrator = " + admin);

			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	public void updateUser(String name, String password, String username) {
		try {
			String sql = "update users set name = ?, password = ? where username = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, password);
			pstmt.setString(3, username);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public Vaccine getVaccine(int id) {
		Vaccine vaccine = null;

		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from vaccines where id = " + id);

			while (rs.next()) {
				vaccine = new Vaccine(rs.getInt(1), rs.getString("name"), rs.getInt("doses_required"),
						rs.getInt("days_between_doses"), rs.getInt("doses_received"), rs.getInt("doses_left"));
				return vaccine;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vaccine;
	}

	public Patient getPatient(int id) {
		Patient patient = null;

		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from patients where id = " + id);

			while (rs.next()) {
				Vaccine vaccine = getVaccine(rs.getInt("vaccine_id"));
				patient = new Patient(rs.getString("name"), vaccine, rs.getDate("first_dose_date"),
						rs.getDate("second_dose_date"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return patient;
	}

	public Vaccine getVaccineFromName(String name) {
		Vaccine vaccine = null;

		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from vaccines where name = '" + name + "'");

			while (rs.next()) {
				vaccine = new Vaccine(rs.getInt(1), rs.getString("name"), rs.getInt("doses_required"),
						rs.getInt("days_between_doses"), rs.getInt("doses_received"), rs.getInt("doses_left"));
				return vaccine;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return vaccine;
	}

	public void updateVaccine(int id, String vaccineName, int dosesRequired, int daysBetweenDoses) {
		try {
			String sql = "update vaccines set name = ?, doses_required = ?, days_between_doses = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vaccineName);
			pstmt.setInt(2, dosesRequired);
			pstmt.setInt(3, daysBetweenDoses);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updatePatientSecondDate(int id) {
		try {
			String sql = "update patients set second_dose_date = ? where id = ?";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			pstmt.setDate(1, date);
			pstmt.setInt(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void newDoses(String name, int dosesReceived) {
		try {
			Statement smt = connection.createStatement();
			ResultSet rs = smt.executeQuery("select * from vaccines where name = '" + name + "'");

			while (rs.next()) {
				int totalDosesReceived = rs.getInt("doses_received");
				totalDosesReceived += dosesReceived;
				int totalDosesLeft = rs.getInt("doses_left");
				totalDosesLeft += dosesReceived;

				String sql = "update vaccines set doses_received = ?, doses_left = ? where name = ?";
				PreparedStatement pstmt = connection.prepareStatement(sql);
				pstmt.setInt(1, totalDosesReceived);
				pstmt.setInt(2, totalDosesLeft);
				pstmt.setString(3, name);
				pstmt.executeUpdate();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void subtractDoses(int id) {
		try {
			String sql = "update vaccines set doses_left = ? where id = " + id;
			PreparedStatement pstmt = connection.prepareStatement(sql);
			Vaccine vaccine = getVaccine(id);
			pstmt.setInt(1, vaccine.getTotalDosesLeft() - 1);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertVaccine(Vaccine vaccine) {
		try {
			String sql = "insert into vaccines (name, doses_required, days_between_doses, doses_received, doses_left) values (?, ?, ?, 0, 0)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, vaccine.getVaccineName());
			pstmt.setInt(2, vaccine.getDosesRequired());
			pstmt.setInt(3, vaccine.getDaysBetweenDoses());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void insertPatient(Patient patient) {
		try {
			String sql = "insert into patients (name, vaccine_id, first_dose_date, second_dose_date) values (?, ?, ?, null)";
			PreparedStatement pstmt = connection.prepareStatement(sql);
			pstmt.setString(1, patient.getName());
			pstmt.setInt(2, patient.getVaccine().getId());
			java.sql.Date date = new java.sql.Date(System.currentTimeMillis());
			pstmt.setDate(3, date);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
