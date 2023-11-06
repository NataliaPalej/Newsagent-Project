package agile_project.FINISHED;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import agile_project.NataliaException;

public class Admin extends User {
	private int adminID;
	
	static Scanner in = new Scanner(System.in);
	
	/**
	 * Constructors
	 * Admin(String username, String password, String role)
	 * Admin()
	 */
	
	public Admin(String username, String password, String role) throws NataliaException {
        super(username, password, role);
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
	
	public Admin() {
	}
	
	/**
	 * Methods
	 * createUser
	 * deleteUser
	 * updateUser
	 * getUser
	 * doesUserExist
	 */

	public void createUser(String username, String password, String role) throws NataliaException, SQLException {
        if (!isValidUsername(username)) {
            throw new NataliaException("Invalid username. Username must be between 1-10 characters.");
        }
        if (!isValidPassword(password)) {
            throw new NataliaException("Invalid password. Password must be between 6-10 characters, include at least one uppercase letter and one digit.");
        }
        if (!isValidRole(role)) {
            throw new NataliaException("Invalid role. Available roles: admin/newsagent/driver.");
        }
        
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String query = "INSERT INTO userdetails (username, password, role) VALUES (?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, role);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
            	int userID = generatedKeys.getInt(1);
                this.setID(userID);
                this.setUsername(username);
                this.setPassword(password);
                this.setRole(role);
                
                System.out.println("User: " + this.getID() + " " + this.getUsername() + " was successfully created!");
            }
        } catch (SQLException e) {
            throw new NataliaException("Couldn't create User.\n" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
    }
	
	public void deleteUser(int id) throws NataliaException, SQLException {
		
		System.out.println("Are you sure you want to delete user: " + id + "?");
		String answer = in.next();
		
		Connection connection = null;
		String query = "DELETE FROM userdetails WHERE userID = ?";
		
		try {
			connection = DatabaseConnector.getConnection();
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("YES")){
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, id); // Set the value of the userID parameter
	            stmt.executeUpdate(); // Execute the delete query
				System.out.println("User " + id + " has been successfully deleted.");
			} else {
				System.out.println("Deletion cancelled.");
			}
		} catch (SQLException e) {
			throw new NataliaException("Couldn't delete user");
		} finally {
			connection.close();
		}
	}
	
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
	
	@SuppressWarnings("resource")
	public void updateUser(int id) throws NataliaException, SQLException {
		String query = "SELECT * FROM userdetails WHERE userID = ?";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	    	connection = DatabaseConnector.getConnection();
	    	preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        resultSet = preparedStatement.executeQuery();
	    	
	    if (resultSet.next()) {
	    	String userID = resultSet.getString("userID");
            String username = resultSet.getString("username");
            
            System.out.println("Are you sure you want to update user " + userID + ": " + username + "? (Y/N)");
            String answer = in.next();
            
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                System.out.println("OPTIONS:\n1. UPDATE username\n2. UPDATE password\n3. UPDATE role");
                System.out.print("Enter option [1/2/3]: ");
                int option = in.nextInt();

                String updateColumn = "";
                String updatePrompt = "";
                String successMessage = "";

                switch (option) {
                    case 1:
                        updateColumn = "username";
                        updatePrompt = "Enter new username: ";
                        successMessage = "Username updated successfully. New username: ";
                        break;
                    case 2:
                        updateColumn = "password";
                        updatePrompt = "Enter new password: ";
                        successMessage = "Password updated successfully. New password: ";
                        break;
                    case 3:
                        updateColumn = "role";
                        updatePrompt = "Enter new role: ";
                        successMessage = "Role updated successfully. New role: ";
                        break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }
                System.out.println(updatePrompt);
                String newValue = in.next();
                
                if (isValidUpdate(updateColumn, newValue)) {
                	String updateQuery = "UPDATE userdetails SET " + updateColumn + " = ? WHERE userID = ?";
                	preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newValue);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                    System.out.println(successMessage + newValue);
                    }
                }
            }
	    }
	    catch (SQLException e) {
	        throw new NataliaException("Database error: " + e.getMessage());
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources: " + e.getMessage());
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}
	
	public boolean doesUserExist(int userID) throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
		
	    try {
	        connection = DatabaseConnector.getConnection();
	        String query = "SELECT * FROM userdetails WHERE userID = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, userID);
	        resultSet = preparedStatement.executeQuery();
	        // If a record exists, resultSet.next() will be true.
	        return resultSet.next(); 
	    } catch (SQLException e) {
	        throw new NataliaException("Database error: " + e.getMessage());
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources: " + e.getMessage());
	        }
	    }
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
			System.out.println("Password length must be between 6-10 characters.");
			return false;
		} // Check if password contains at least one digit
		if (!password.matches(".*\\d.*")) {
			System.out.println("Password must contain at least one digit.");
			return false;
		} // Check if the password contains at least one uppercase letter
		if (!password.matches(".*[A-Z].*")) {
			System.out.println("Password must contain at least one uppercase letter.");
	        return false;
	    }
		return true;
	}
	
	public boolean isValidRole(String role) {
		if (role.equalsIgnoreCase("admin") || role.equalsIgnoreCase("driver") || role.equalsIgnoreCase("newsagent")) {
			return true;
		} else {
			return false;
		}
	}
	
	private boolean isValidUpdate(String updateColumn, String newValue) {
	    // Validation logic for the updateColumn and newValue
	    if (updateColumn.equals("username")) {
	        return isValidUsername(newValue);
	    } else if (updateColumn.equals("password")) {
	        return isValidPassword(newValue);
	    } else if (updateColumn.equals("role")) {
	        return isValidRole(newValue);
	    }
	    return false;
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
}
