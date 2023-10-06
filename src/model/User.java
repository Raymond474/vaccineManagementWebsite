package model;

public class User {
	private boolean adminastrator;
	private String username;
	private String password;
	private String name;
	
	public User(boolean adminastrator, String username, String password, String name) {
		this.adminastrator = adminastrator;
		this.username = username;
		this.password = password;
		this.name = name;
	}
	
	public boolean isAdminastrator() {
		return adminastrator;
	}
	public void setAdminastrator(boolean adminastrator) {
		this.adminastrator = adminastrator;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}