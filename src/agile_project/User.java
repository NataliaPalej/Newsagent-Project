package agile_project;

import java.sql.*;
import java.util.Scanner;

public class User extends DatabaseConnector {
	
	private static Scanner in = new Scanner(System.in);

	// Get user deatils from userDetails table
	static final String userQUERY = "SELECT * FROM userdetails";
	// Get customer details from customerDetails table 
	static final String customerQUERY = "SELECT * FROM customerdetails";
	
	// Create a connection to the database
    Connection connection = getConnection();
    
    // Select details for user
    String selectUserQuery = "SELECT * FROM userdetails WHERE id = ?";
    
    // Select details for customer
    String selectCustomerQuery = "SELECT * FROM customerdetails WHERE id = ?";
	
	private String username, password, role;
	
	// User constructor 
	public User(String username, String password, String role) throws NataliaException {
		this.username = username;
        this.password = password;
        this.role = role;
	}
	
	/** 
	 * User methods
	 * @param username between 1-10 characters
	 * @param password between 6-10 characters 
	 * @param role : admin/newsagent/driver
	 */
	public static User createUserAccount(String username, String password, String role) throws NataliaException {
		return new User(username, password, role);
	}
	
	@SuppressWarnings("resource")
	public void updateUserAccount(String id) throws NataliaException{
		PreparedStatement updateUserStatement = null;
		ResultSet userDataResultSet = null;
		
		try {
			updateUserStatement = connection.prepareStatement(selectUserQuery);
			updateUserStatement.setString(1, id);
			
			// Retrieve user details
			userDataResultSet  = updateUserStatement.executeQuery();
			
			if (userDataResultSet.next()) {
				String userID = userDataResultSet .getString("id");
	            String username = userDataResultSet .getString("username");

	            System.out.println("Are you sure you want to update user " + userID + ": " + username + "?");
	            String answer = in.next();
	            
	            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
	            	System.out.println("OPTIONS:\n1. UPDATE Username\n2. UPDATE Password\n3. Update Role");
	            	System.out.println("Enter option [1/2/3]: ");
	            	int option = in.nextInt();
	            	
	                String updateColumn = "";
                    String updatePrompt = "";
                    String successMessage = "";
                    
                    if (option == 1) {
                        updateColumn = "username";
                        updatePrompt = "Enter new username: ";
                        successMessage = "Username updated successfully. New username: ";
                    } else if (option == 2) {
                        updateColumn = "password";
                        updatePrompt = "Enter new password: ";
                        successMessage = "Password updated successfully. New password: ";
                    } else if (option == 3) {
                        updateColumn = "role";
                        updatePrompt = "Enter new role: ";
                        successMessage = "Role updated successfully. New role: ";
                    } else {
                        System.out.println("Invalid option.");
                        return;
                    }

                    System.out.println(updatePrompt);
                    String newValue = in.next();
                    
                    if (isValidUpdate(updateColumn, newValue)) {
                        String updateQuery = "UPDATE userdetails SET " + updateColumn + " = ? WHERE id = ?";
                        updateUserStatement = connection.prepareStatement(updateQuery);
                        updateUserStatement.setString(1, newValue);
                        updateUserStatement.setString(2, id);
                        updateUserStatement.executeUpdate();
                        System.out.println(successMessage + newValue);
                    }   		    
	            }
			}
		}
		catch (Exception e) {
			throw new NataliaException("Error when updating user: " + e.getMessage());
		} finally {
	        try {
	            if (userDataResultSet != null) {
	                userDataResultSet.close();
	            }
	            if (updateUserStatement != null) {
	                updateUserStatement.close();
	            }
	        } catch (SQLException e) {
	            throw new NataliaException("Error: " + e.getMessage());
	        }
		}
	}
	
	@SuppressWarnings("resource")
	public void deleteUserAccount(String id) throws NataliaException{
		PreparedStatement deleteUserStatement = null;
		ResultSet userDataResultSet = null;
		
		try {
			deleteUserStatement = connection.prepareStatement(selectUserQuery);
			deleteUserStatement.setString(1, id);
			
			// Retrieve user details
			userDataResultSet = deleteUserStatement.executeQuery();
			
	        if (userDataResultSet.next()) {
	            String userID = userDataResultSet.getString("id");
	            String username = userDataResultSet.getString("username");
	            
	            System.out.println("Are you sure you want to delete user " + userID + ": " + username + "?");
	            String answer = in.next();
	            
	            // Confirm you want to delete the user
	            if (answer.equalsIgnoreCase("yes") || (answer.equalsIgnoreCase("y"))) {
	            	String deleteQuery = "DELETE FROM userdetails WHERE id = ?";
	            	deleteUserStatement = connection.prepareStatement(deleteQuery);
	            	deleteUserStatement.setString(1, id);
	            	deleteUserStatement.executeUpdate();
	                System.out.println("User " + userID + " " + username + " deleted successfully.");
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
				if (userDataResultSet != null) {
					userDataResultSet.close();
	            }
	            if (deleteUserStatement != null) {
	            	deleteUserStatement.close();
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
	private boolean isValidUsername(String username) throws NataliaException {
		boolean result = false;
		if (username.length() < 15 && username.length() > 0)  {
			result = true;
		}
		return result;
	}
	
	private boolean isValidPassword(String password) throws NataliaException {
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
	
	private boolean isValidRole(String role) throws NataliaException {
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
	
	private boolean isValidUpdate(String columnName, String value) throws NataliaException {
	    if (columnName.equals("username")) {
	        return isValidUsername(value);
	    } else if (columnName.equals("password")) {
	        return isValidPassword(value);
	    } else if (columnName.equals("role")) {
	        return isValidRole(value);
	    }
	    return false;
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
