/**
 * 
 */
package agile_project;

import java.sql.*;
import java.util.Scanner;

public class Newsagent extends DatabaseConnector {
	
	private static Connection connection = DatabaseConnector.getConnection();

	private static Scanner in = new Scanner(System.in);
	
	public static void main(String[] args) {
		
		System.out.println("Welcome to Newsagent App!\n");
		System.out.print("Please log in\nUsername: ");
		String username = in.next();
		
		if (authenticateUsername(username)) {
			System.out.print("Password: ");
			String password = in.next();
			if (authenticatePassword(username, password)) {
				// Validate the role to give different options
				User authenticatedUser = getUser(username, password);
				if(authenticatedUser.getRole().equalsIgnoreCase("admin")) {
					System.out.println("OPTIONS:");
					System.out.println("1. CREATE new user.\n2. UPDATE existing user.");
					System.out.println("3. READ user details.\n4. DELETE user.");
					int option = in.nextInt();
					
					if(option == 1) {
						
					}
					else if (option == 2) {
						System.out.println("Enter the ID of the user you want to update: ");
						int userID = in.nextInt();
						try {
							authenticatedUser.updateUserAccount(userID);
						} catch (NataliaException e) {
							System.out.println(e.toString());
							System.out.println("User ID doesn't exist.");;
						}
					}
					else if (option == 3) {
						
					}
					else if (option == 4) { 
						
					}
				}
				else if(authenticatedUser.getRole().equalsIgnoreCase("newsagent")) {
					System.out.println("Show newsagent menu here");
				}
				else if (authenticatedUser.getRole().equalsIgnoreCase("driver")) {
					// code for driver menu goes here
				}
			}
			else {
				System.out.println("Login failed. Incorrect password.\nPlease try again.");
			}
		}
		else {
			System.out.println("Username doesn't exist. Try again.");
		}
	}
	
	// Check valid username
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
	
	// Check valid password
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
	
	// Retrive User from database
	private static User getUser(String username, String password) {
		User myUser = null;
		try {
			String query = "SELECT * FROM userdetails WHERE username = ? AND password = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				String role = resultSet.getString("role");
				myUser = new User(username, password, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myUser;
	}
	
	/**
	 *  Retrive User with ID
	 * @param id
	 * @return
	 * private static User getUser(int id) {
		User myUser = null;
		try {
			String query = "SELECT * FROM userdetails WHERE userID = ?";
			PreparedStatement statement = connection.prepareStatement(query);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()){
				String role = resultSet.getString("role");
				String username = resultSet.getString("username");
				String password = resultSet.getString("password");
				myUser = new User(id, username, password, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return myUser;
	}
	 */
}
