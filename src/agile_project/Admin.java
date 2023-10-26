package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Admin extends DatabaseConnector {
	
	// Create connection to the database
	Connection connection = getConnection();

	private int adminID;
	private String username, password, role;
	
	public Admin(int adminID, String username, String password, String role) throws NataliaException {
		throw new NataliaException("Constructor not yet implemented");
	}
	
	public void createUser(String username, String password, String role) throws NataliaException, SQLException {
		connection = DatabaseConnector.getConnection();
		User user = new User(username, password, role);
		saveUserToDatabase(user);
	}
	
	public void deleteUser(int id) {
		connection = DatabaseConnector.getConnection();
	}
	
	public String readUser(int id) {
		connection = DatabaseConnector.getConnection();
		return null;
	}
	
	public void updateUser() {
		connection = DatabaseConnector.getConnection();
	}
	
	public void givePermission() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public void saveUserToDatabase(User user) throws NataliaException, SQLException {
		String query = "INSERT INTO userdetails VALUES (?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(query);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getRole());
		stmt.execute(query);
		stmt.executeUpdate();
		System.out.println("User : " + user + " was successfully created!");
	}

	/** 
	 * Validation methods
	 * @param username between 1-10 characters
	 * @param password between 6-10 characters + at least one number&upper case
	 * @param role: admin/newsagent/driver
	 */
	public boolean isValidUsername(String username) {
		if (username.length() >= 1 && username.length() <= 10) {
			return true;
		}
		return false;
	}
	
	public boolean isValidPassword(String password) {
		if (password.length() < 6 || password.length() > 10) {
			return false;
		} // Check if password contains at least one digit
		if (!password.matches(".*\\d.*")) {
			return false;
		} // Check if the password contains at least one uppercase letter
		if (!password.matches(".*[A-Z].*")) {
	        return false;
	    }
		return true;
	}
	
	public boolean isValidRole(String role) {
		if (role == "admin" || role == "driver" || role == "newsagent") {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Setters & Getters
	 * @return
	 */
	
	public int getAdminID() {
		return adminID;
	}

	public void setAdminID(int adminID) {
		this.adminID = adminID;
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
