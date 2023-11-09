package agile_project.Archive;
import java.sql.Connection;
import java.sql.*;
import java.util.Scanner;

import agile_project.DatabaseConnector;
import agile_project.NataliaException;
import agile_project.User;

public class Application extends DatabaseConnector {
	
	static Connection connection = null;
	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) throws NataliaException {
		
		connection = DatabaseConnector.getConnection();
		
		System.out.println(" ---------------------------------------");
        System.out.println("| Welcome to the Newsagent Application! |");
        System.out.println(" --------------------------------------- ");
        System.out.println("\tLogin");
        System.out.print("\tEnter username:");
        String username = in.next();
        
        if (authenticateUsername(username)) {
			System.out.print("\tPassword: ");
			String password = in.next();
			if (authenticatePassword(username, password)) {
				// Validate role to give different options
				User authenticatedUser = getUser(username, password);
				
				System.out.println(" --------------------------------------- ");
				
				if(authenticatedUser.getRole().equalsIgnoreCase("admin")) {
					System.out.println();
					System.out.println("\tOPTIONS:\t");
					System.out.println("1. CREATE new user\n2. UPDATE existing user");
					System.out.println("3. GET user details\n4. DELETE user\n5. LOG OUT");
					int option = in.nextInt();

				    switch (option) {
				        case 1:
				            // Call createUser() from Admin class
				            break;
				        case 2:
				            // Call updateUser(int id) from Admin class
				            break;
				        case 3:
				            // Call getUser(int id) from Admin class
				            break;
				        case 4:
				            // Call deleteUser(int id) from Admin class
				            break;
				        case 5:
				        	// Log out option
				        default:
				            System.out.println("Invalid option.");
				            break;
				    }
				}
				else if(authenticatedUser.getRole().equalsIgnoreCase("newsagent")) {
					System.out.println("\tMENU:\t");
					System.out.println("1. CUSTOMER OPTIONS\n2. INVOICE OPTIONS");
					System.out.println("3. REPORTS\n4. LOG OUT");
					
					int menuOption = in.nextInt();
					
					switch (menuOption) {
			        case 1:
			        	System.out.println("\tCUSTOMER OPTIONS\t");
			        	System.out.println("1. CREATE new customer\n2. UPDATE existing customer");
						System.out.println("3. GET customer details\n4. GET *all* customers details\n");
						System.out.println("5. DELETE customer\n6. BACK");
						// Prompt to pick option
						// Swtich case based on the option
			            break;
			        case 2:
			            System.out.println("\tINVOICE OPTIONS\t");
			            System.out.println("1. GENERATE Invoice\\n2. BACK");
			            // Prompt to pick option
						// Swtich case based on the option
			            break;
			        case 3:
			        	System.out.println("\tREPORT OPTIONS\t");
			            System.out.println("1. GENERATE Delivery Report\n2. BACK");
			            // Prompt to pick option
						// Swtich case based on the option
			            break;
			        case 4:
			        	// Log out
			        default:
			            System.out.println("Invalid option.");
			            break;
					}
				}
				else if (authenticatedUser.getRole().equalsIgnoreCase("driver")) {
					// code for driver menu goes here
				}
			}
			// Error when password doesnt match
			else {
				System.out.println("Login failed. Incorrect password.");
			}
		}
        // Error when username doesnt match
		else {
			System.out.println("Username doesn't exist.");
		}
	
	}
	
	
	/**
	 * Validation methods
	 * authenticateUsername
	 * authenticatePassword
	 */
	private static boolean authenticateUsername(String username) {
		try {
			String query = "SELECT username FROM userdetails WHERE username = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			ResultSet resultSet = statement.executeQuery();
			// If there's a result, the username exists
			return resultSet.next(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean authenticatePassword(String username, String password) {
		try {
			String query = "SELECT username FROM userdetails WHERE username = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			// If matches database, the username and password are correct
			return resultSet.next(); 
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// Retrieve User from database
	private static User getUser(String username, String password) {
		User user = null;
		try {
			String query = "SELECT * FROM userdetails WHERE username = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				String role = resultSet.getString("role");
				user = new User(username, password, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
