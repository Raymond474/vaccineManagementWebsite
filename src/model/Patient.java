package model;

import java.util.Date;

public class Patient {

	private int id;
	private String name;
	private Vaccine vaccine;
	private String vaccineName;
	private Date firstDose;
	private Date secondDose;

	public Patient(int id, String name, Vaccine vaccine, Date firstDose, Date secondDose) {
		this.id = id;
		this.name = name;
		this.vaccine = vaccine;
		this.vaccineName = vaccine.getVaccineName();
		this.firstDose = firstDose;
		this.secondDose = secondDose;
	}
	
	//For new patient
	public Patient(String name, Vaccine vaccine, Date firstDose, Date secondDose) {
		this.name = name;
		this.vaccine = vaccine;
		this.vaccineName = vaccine.getVaccineName();
		this.firstDose = firstDose;
		this.secondDose = secondDose;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public Date getFirstDose() {
		return firstDose;
	}

	public void setFirstDose(Date firstDose) {
		this.firstDose = firstDose;
	}

	public Date getSecondDose() {
		return secondDose;
	}

	public void setSecondDose(Date secondDose) {
		this.secondDose = secondDose;
	}

	public Vaccine getVaccine() {
		return vaccine;
	}
}