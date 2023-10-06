package model;

public class Vaccine{

    private int id;
    private String vaccineName;
    private int dosesRequired;
    private int daysBetweenDoses;
    private int totalDosesReceived;
    private int totalDosesLeft;
    
    public Vaccine(int id, String vaccineName, int dosesRequired, int daysBetweenDoses, int totalDosesReceived, int totalDosesLeft)
    {
        this.id = id;
        this.vaccineName = vaccineName;
        this.dosesRequired = dosesRequired;
        this.daysBetweenDoses = daysBetweenDoses;
        this.totalDosesReceived = totalDosesReceived;
        this.totalDosesLeft = totalDosesLeft;
    }
    
    //For a new vaccine
    public Vaccine(String vaccineName, int dosesRequired, int daysBetweenDoses)
    {
        this.vaccineName = vaccineName;
        this.dosesRequired = dosesRequired;
        this.daysBetweenDoses = daysBetweenDoses;
        this.totalDosesReceived = 0;
        this.totalDosesLeft = 0;
    }
    
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVaccineName() {
		return vaccineName;
	}

	public void setVaccineName(String vaccineName) {
		this.vaccineName = vaccineName;
	}

	public int getDosesRequired() {
		return dosesRequired;
	}

	public void setDosesRequired(int dosesRequired) {
		this.dosesRequired = dosesRequired;
	}

	public int getDaysBetweenDoses() {
		return daysBetweenDoses;
	}

	public void setDaysBetweenDoses(int daysBetweenDoses) {
		this.daysBetweenDoses = daysBetweenDoses;
	}

	public int getTotalDosesReceived() {
		return totalDosesReceived;
	}

	public void setTotalDosesReceived(int totalDosesReceived) {
		this.totalDosesReceived = totalDosesReceived;
	}

	public int getTotalDosesLeft() {
		return totalDosesLeft;
	}

	public void setTotalDosesLeft(int totalDosesLeft) {
		this.totalDosesLeft = totalDosesLeft;
	}
}
