package agile_project;

import java.sql.*;
import java.util.Scanner;

public class Application1 extends DatabaseConnector {

	static Connection connection = null;
	private static Scanner in = new Scanner(System.in);

	static Admin admin = new Admin();
	// Driver driver = new Driver();
	static Newsagent newsagent = new Newsagent();
	// Needed for logout
	static User authenticatedUser = null;

	public static void main(String[] args) throws NataliaException, SQLException {

		connection = DatabaseConnector.getConnection();

		while (true) {
			System.out.println("*---------------------------------------*");
			System.out.println("| Welcome to the Newsagent Application! |");
			System.out.println("*---------------------------------------*");
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

					if (authenticatedUser.getRole().equalsIgnoreCase("admin")) {
						System.out.println();
						System.out.println("\tOPTIONS:\t");
						System.out.println("1. CREATE new user\n2. UPDATE existing user");
						System.out.println("3. GET user details\n4. DELETE user\n5. LOG OUT");
						int option = in.nextInt();

						switch (option) {
						case 1:
							admin.createUser();
							break;
						case 2:
							System.out.println("Enter User ID: ");
							int userToUpdate = in.nextInt();
							admin.updateUser(userToUpdate);
							break;
						case 3:
							System.out.println("Enter User ID: ");
							int userID = in.nextInt();
							admin.getUser(userID);
							break;
						case 4:
							System.out.println("Enter User ID: ");
							int userToDelete = in.nextInt();
							admin.deleteUser(userToDelete);
							break;
						case 5:
							logOut();
						default:
							System.out.println("Invalid option.");
							break;
						}
					} else if (authenticatedUser.getRole().equalsIgnoreCase("newsagent")) {
						System.out.println("\tMENU:\t");
						System.out.println("1. CUSTOMER OPTIONS\n2. INVOICE OPTIONS");
						System.out.println("3. REPORTS\n4. LOG OUT");

						int menuOption = in.nextInt();

						switch (menuOption) {
						case 1:
							System.out.println("* ------------------ *");
							System.out.println("|  CUSTOMER OPTIONS  |");
							System.out.println("* ------------------ *");
							System.out.println("1. CREATE new customer\n2. UPDATE existing customer");
							System.out.println("3. GET customer details\n4. GET *all* customers details");
							System.out.println("5. DELETE customer\n6. BACK");
							int option = in.nextInt();
							if (option == 1) {
								newsagent.createCustomer();
							} else if (option == 2) {
								System.out.println("Enter Customer ID:");
								int custID = in.nextInt();
								newsagent.updateCustomer(custID);
							} else if (option == 3) {
								System.out.println("Enter Customer ID:");
								int custID = in.nextInt();
								newsagent.getCustomer(custID);
							} else if (option == 4) {
								newsagent.getAllCustomers();
							} else if (option == 5) {
								System.out.println("Enter Customer ID:");
								int custID = in.nextInt();
								newsagent.deleteCustomer(custID);
							} else if (option == 6) {

							} else {
								System.out.println("Invalid option selected.");
							}
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
							logOut();
							break;
						default:
							System.out.println("Invalid option.");
							break;
						}
					} else if (authenticatedUser.getRole().equalsIgnoreCase("driver")) {
						// code for driver menu goes here
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
		}
	}

	/**
	 * Validation methods authenticateUsername authenticatePassword
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
			if (resultSet.next()) {
				String role = resultSet.getString("role");
				user = new User(username, password, role);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@SuppressWarnings("unused")
	public static void logOut() throws NataliaException, SQLException {
		Connection connection = null;

		System.out.println("Logging out...\nYou're logged out!\n\n");
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (SQLException e) {
			throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
		}
		authenticatedUser = null;
		// Return to the login screen
		main(new String[] {});

	}
}
