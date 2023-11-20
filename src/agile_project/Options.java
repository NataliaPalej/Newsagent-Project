package agile_project;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Options extends DatabaseConnector{
	
	static Connection connection = null;
	private static Scanner in = new Scanner(System.in);
	
	public Options() {
	}
	
	static void loginScreen() throws NataliaException, SQLException, RonanException{
		connection = DatabaseConnector.getConnection();
		
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
				
				if(authenticatedUser.getRole().equalsIgnoreCase("admin")) {
					Options admin = new Options();
					admin.adminOptions();
				}
				if (authenticatedUser.getRole().equalsIgnoreCase("newsagent")) {
					Options newsagent = new Options();
					newsagent.newsagentOptions();
				}
				if (authenticatedUser.getRole().equalsIgnoreCase("driver")) {
					Options driver = new Options();
					driver.driverOptions();
				}
				
			} else {
				System.out.println("Invalid password.");
			}
		} else {
			System.out.println("Invalid username.");
		}
	}
	
	private void adminOptions() throws NataliaException, SQLException, RonanException {
		Admin admin = new Admin();
		
		System.out.println();
		System.out.println("\n\tADMIN OPTIONS:\t");
		System.out.println("1. CREATE new user\n2. UPDATE existing user");
		System.out.println("3. GET user details\n4. DELETE user\n5. LOG OUT");
		int option = in.nextInt();

		switch (option) {
		case 1:
			admin.createUser();
			adminOptions();
			break;
		case 2:
			System.out.println("Enter User ID: ");
			int userToUpdate = in.nextInt();
			admin.updateUser(userToUpdate);
			adminOptions();
			break;
		case 3:
			System.out.println("Enter User ID: ");
			int userID = in.nextInt();
			admin.getUser(userID);
			adminOptions();
			break;
		case 4:
			System.out.println("Enter User ID: ");
			int userToDelete = in.nextInt();
			admin.deleteUser(userToDelete);
			adminOptions();
			break;
		case 5:
			logOut();
			break;
		default:
			System.out.println("Invalid option.");
			break;
		}
	}
	
	private void newsagentOptions() throws NataliaException, SQLException, RonanException {
		Newsagent newsagent = new Newsagent();
		
		System.out.println("\n\tNEWSAGENT MENU:\t");
		System.out.println("1. CUSTOMER OPTIONS\n2. INVOICE OPTIONS");
		System.out.println("3. REPORTS\n4. PUBLICATIONS\n5. LOG OUT");

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
				newsagentOptions();
			} else if (option == 2) {
				System.out.println("Enter Customer ID:");
				int custID = in.nextInt();
				newsagent.updateCustomer(custID);
				newsagentOptions();
			} else if (option == 3) {
				System.out.println("Enter Customer ID:");
				int custID = in.nextInt();
				newsagent.getCustomer(custID);
				newsagentOptions();
			} else if (option == 4) {
				newsagent.getAllCustomers();
				newsagentOptions();
			} else if (option == 5) {
				System.out.println("Enter Customer ID:");
				int custID = in.nextInt();
				newsagent.deleteCustomer(custID);
				newsagentOptions();
			} else if (option == 6) {
				newsagentOptions();
			} else {
				System.out.println("Invalid option selected.");
			}
			break;
		case 2:
			System.out.println("\tINVOICE OPTIONS\t");
			System.out.println("1. GENERATE Invoice\n2. BACK");
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
			System.out.println("\tPUBLICATIONS OPTIONS\t");
			System.out.println("1. CREATE Publication\n2.UPDATE Publication\n3.PRINT Publication\n4.DELETE Publication"
					+ "\n5.PRINT *all* Publications\n6.BACK");
			// Prompt to pick the option
			// Ifs or switch for each options and appropiate methods within it 
			int pubMethodOption = in.nextInt();
			Publication pubOption = new Publication();
			pubOption.publicationDecision(pubMethodOption);
			newsagentOptions();
			break;
		case 5:
			logOut();
			break;
		default:
			System.out.println("Invalid option.");
			break;
		}
	}
	
	private void driverOptions() throws NataliaException, SQLException {
		//Driver driver = new Driver();
		// code for driver menu goes here
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
	private void logOut() throws NataliaException, SQLException, RonanException {
		Connection connection = null;
		User authenticatedUser = null;

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
		loginScreen();
	}
}