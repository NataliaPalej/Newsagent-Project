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
		User user = new User(username, password, role);
		saveUserToDatabase(user);
	}
	
	public void deleteUser(int id) {
		
	}
	
	public String readUser(int id) {
		return null;
	}
	
	public void updateUser() {
		
	}
	
	public void givePermission() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public void saveUserToDatabase(User user) throws NataliaException, SQLException {
		String sql = "INSERT INTO newsagentdb VALUES (?, ?, ?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setString(1, user.getUsername());
		stmt.setString(2, user.getPassword());
		stmt.setString(3, user.getRole());
		stmt.execute(sql);
		stmt.executeUpdate();
		System.out.println("User : " + user + " was successfully created!");
	}
}
