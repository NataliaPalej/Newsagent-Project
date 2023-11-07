package agile_project;
import java.sql.*;

public class User {
	
	private int userID;
	protected String username;
	protected String password;
	protected String role;
	
	/**
	 * Constructors
	 * User(String username, String password, String role)
	 * User()
	 */
	public User(String username, String password, String role) throws NataliaException {
		if (username.isEmpty() || username.length() < 1 || username.length() > 10 || password.isEmpty() || password.length() < 6 || password.length() > 10 || !password.matches(".*\\d.*")) {
		    throw new NataliaException("Invalid user attributes.");
		}
		if (role.equalsIgnoreCase("driver") || role.equalsIgnoreCase("newsagent") || role.equalsIgnoreCase("admin")) {
			this.username = username;
		    this.password = password;
		    this.role = role;
		}
		else {
			throw new NataliaException("Invalid role. Available roles: driver/newsagent/admin.");
		}
	}
	
	public User() {
		
	}
	
	/**
	 * Methods
	 * getUser(int id)
	 * getAllUsers()
	 * 
	 */
	public User getUser(int id) throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    String query = "SELECT * FROM userdetails WHERE userID = ?";
	    User user = new User();
	    
		try {
			connection = DatabaseConnector.getConnection();
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				user.setID(id);
	            user.setUsername(resultSet.getString("username"));
	            user.setPassword(resultSet.getString("password"));
	            user.setRole(resultSet.getString("role"));
			} else {
				throw new NataliaException("User with " + id + " NOT found.");
			}
		} catch (SQLException error) {
			throw new NataliaException("Database error.\n" + error.getMessage());
		} finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
	        }
		}
		return user;
	}
	
	public String getAllUsers() throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
			connection = DatabaseConnector.getConnection();
			String query = "SELECT * FROM userdetails ORDER BY userID ASC";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			String usersDetails = "";
			
			while (resultSet.next()) {
				String userID = resultSet.getString("userID");
	            String username = resultSet.getString("username");
	            String password = resultSet.getString("password");
	            String role = resultSet.getString("role");
	            
	            usersDetails += "User ID: " + userID + "\tUsername: " + username +
	                    "\tPassword: " + password + "\tRole: " + role + "\n";
			}
			if (usersDetails.length() == 0) {
				throw new NataliaException("Users database empty or not found.");
			}
			return usersDetails.toString();
		} catch (SQLException error) {
			throw new NataliaException("Database error.\n" + error.getMessage());
		} finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
	        }
		}	
	}
	
	
	/**
	 * Getters and setters
	 */
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
	
	public int getID() {
		return userID;
	}
	
	public void setID(int userID) {
		this.userID = userID;
	}
}
