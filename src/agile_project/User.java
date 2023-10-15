package agile_project;

import java.sql.*;
import java.util.Scanner;

public class User extends DatabaseConnector {
	
	private static Scanner in = new Scanner(System.in);

	static final String userQUERY = "SELECT * FROM userdetails";
	static final String customerQUERY = "SELECT * FROM customerdetails";
	
	// Create a connection to the database
    Connection connection = getConnection();
	
	private String username, password, role;
	
	/** 
	 * User methods
	 * @param username between 0-10 characters
	 * @param password between 6-10 characters 
	 * @param role
	 */
	public User(String username, String password, String role) throws NataliaException {
		this.username = username;
        this.password = password;
        this.role = role;
	}
	
	public static User createUserAccount(String username, String password, String role) throws NataliaException {
		return new User(username, password, role);
	}
	
	public void updateUserAccount() throws NataliaException{
		throw new NataliaException("No method implemented yet");
	}
	
	@SuppressWarnings("resource")
	public void deleteUserAccount(String id) throws NataliaException{
		PreparedStatement deleteUser = null;
		ResultSet userToDelete = null;
		String selectUserQuery = "SELECT id, firstName, lastName FROM userdetails WHERE id = ?";
		
		try {
			deleteUser = connection.prepareStatement(selectUserQuery);
			deleteUser.setString(1, id);
			
			// Retrieve user details
			userToDelete = deleteUser.executeQuery();
			
	        if (userToDelete.next()) {
	            String userId = userToDelete.getString("id");
	            String userFirstName = userToDelete.getString("firstName");
	            String userLastName = userToDelete.getString("lastName");
	            
	            System.out.println("Are you sure you want to delete user " + userId + ": " + userFirstName + " " + userLastName + "?");
	            String answer = in.next();
	            
	            // Confirm you want to delete the user
	            if (answer.equalsIgnoreCase("yes") || (answer.equalsIgnoreCase("y"))) {
	            	String deleteQuery = "DELETE FROM userdetails WHERE id = ?";
	            	deleteUser = connection.prepareStatement(deleteQuery);
	            	deleteUser.setString(1, id);
	                deleteUser.executeUpdate();
	                System.out.println("User " + userId + " " + userFirstName + " " + userLastName + " deleted successfully.");
	            } else {
	            	System.out.println("Delete user operation canceled.");
	            }
	        }
	        else {
	        	throw new NataliaException("User with ID: " + id + " doesn't exist.");
	        }
	
		} catch (SQLException e) {
			throw new NataliaException("Error while deleting the user: " + e.getMessage());
		}
		
		finally {
			try {
				if (userToDelete != null) {
					userToDelete.close();
	            }
	            if (deleteUser != null) {
	                deleteUser.close();
	            }
	        } 
			catch (SQLException e) {
				throw new NataliaException("Error: + " + e.getMessage());
			}
		}
	}
	
	public String readUserAccount() throws NataliaException{
		return "User: " + username + "\tPassword: " + password + "\tRole: " + role;
	}
	
	
	/**
	 * Verification methods for username, password, role entry
	 * @throws NataliaException
	 */
	public boolean isValidUsername(String username) throws NataliaException {
		boolean result = false;
		if (username.length() < 15 && username.length() > 0)  {
			result = true;
		}
		return result;
	}
	
	public boolean isValidPassword(String password) throws NataliaException {
		boolean result = false;
		boolean hasUppercase = false;
		boolean hasDigit = false;
		
		try {
			if (password.length() < 6 || password.length() > 15) {
		        System.out.println("Password must be between 6 and 15 characters.");
		        result = false;
		    }
			
			// Check if password contains upper case and digit 
			for (char c : password.toCharArray()) {
		        if (Character.isUpperCase(c)) {
		            hasUppercase = true;
		        } else if (Character.isDigit(c)) {
		            hasDigit = true;
		        }

		        // If both conditions are met, no need to continue checking
		        if (hasUppercase && hasDigit) {
		        	result = true;
		        }
		    }
			
			if (!hasUppercase) {
				System.out.println("Password must contain at least one uppercase letter");
			}
			if(!hasDigit) {
				System.out.println("Password must contain at least one digit");
			}
			
			return result;	
		}
		catch (Exception e) {
			throw new NataliaException("Error while validating password: " + e.getMessage());
		}		
	}
	
	public boolean isValidRole(String role) throws NataliaException {
		String admin = "admin";
		String newsagent = "newsagent";
		String driver = "driver";
		try {
			if (admin.equals(role) || newsagent.equals(role) || driver.equals(role)) {
				return true;
			}
			return false;	
		}
		catch (Exception e) {
			throw new NataliaException("Role " + role + " not allowed.\nAvailable roles: admin/newsagent/driver");
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

}
