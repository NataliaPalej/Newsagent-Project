package agile_project.GUI;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import agile_project.Admin;
import agile_project.DatabaseConnector;
import agile_project.Driver;
import agile_project.Invoice;
import agile_project.Newsagent;
import agile_project.Order;
import agile_project.Publication;
import agile_project.User;
import agile_project.Exceptions.NataliaException;
import agile_project.Exceptions.RonanException;

public class Options extends DatabaseConnector {

    static Connection connection = null;
    private static Scanner in = new Scanner(System.in);

    public Options() {
    }

    static void loginScreen() throws NataliaException, SQLException, RonanException {
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

                if (authenticatedUser.getRole().equalsIgnoreCase("admin")) {
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
                loginScreen();
            }
        } else {
            System.out.println("Invalid username.");
            loginScreen();
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
        Invoice invoice = new Invoice(connection);


        System.out.println("\n\tNEWSAGENT MENU:\t");
        System.out.println("1. CUSTOMER OPTIONS\n2. ORDER OPTIONS");
        System.out.println("3. PUBLICATIONS\n4. INVOICE OPTIONS");
        System.out.println("5. LOG OUT");


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
            /**
            * ORDER options
            **/
            case 2:
                System.out.println("\tORDER OPTIONS\t");
                System.out.println("1. CREATE Order\n2. GET *all* Orders\n3. UPDATE Order\n4. DELETE Order\n5. BACK");
                Order order = new Order();
                int orderOption = in.nextInt();

                switch (orderOption) {
                    // CREATE Order
                    case 1:
                        order.createOrder();
                        newsagentOptions();
                        break;

                    // READ Order
                    case 2:
                        order.readOrder();
                        newsagentOptions();
                        break;

                     // UPDATE Order
                    case 3:
                        System.out.println("Enter Order ID: ");
                        int orderIDToUpdate = in.nextInt();

                        // Gather new values for the fields you want to update
                        System.out.println("Enter new Date (YYYY-MM-DD): ");
                        String newDateString = in.next();
                        LocalDate newDate = LocalDate.parse(newDateString);

                        System.out.println("Enter new Cust ID: ");
                        int newCustID = in.nextInt();

                        System.out.println("Enter new Order Type (daily/weekly/monthly): ");
                        String newType = in.next();

                        System.out.println("Enter new Title: ");
                        String newTitle = in.next();

                        // Call the updateOrder method with the new values
                        order.updateOrder(orderIDToUpdate, newDate, newCustID, newType, newTitle);
                        newsagentOptions();
                        break;

                    // DELETE Order
                    case 4:
                        System.out.println("Enter Order ID: ");
                        int orderIDToDelete = in.nextInt();
                        order.deleteOrder(orderIDToDelete);
                        newsagentOptions();
                        break;

                    // BACK
                    case 5:
                        newsagentOptions();
                        break;

                    default:
                        System.out.println("Invalid option.");
                        break;
                }
                break;
            /**
            * PUBLICATION options
            **/
            case 3:
            	System.out.println("\tPUBLICATION OPTIONS\t");
                System.out.println("1. CREATE Publication\n2. UPDATE Publication\n3. PRINT Publication\n4. DELETE Publication\n5. PRINT *all*\n6. BACK");
    			int pubMethodOption = in.nextInt();
    			Publication pubOption = new Publication();
    			
    			
    			pubOption.publicationDecision(pubMethodOption);
    			newsagentOptions();
    			break;
    		/**
    	    * INVOICE options
    	    **/
            case 4:
                System.out.println("\tINVOICE OPTIONS\t");
                System.out.println("1. CREATE Invoice\n2. READ Invoice\n3. UPDATE Invoice\n4. DELETE Invoice\n5. BACK");

                int invoiceOption = in.nextInt();
              

                switch (invoiceOption) {
                    // CREATE Invoice
                    case 1:
                        // Implement the logic to create a new Invoice
                        System.out.println("Enter Customer ID: ");
                        int custID = in.nextInt();

                        System.out.println("Enter Total Price: ");
                        double totalPrice = in.nextDouble();

                        invoice.createInvoice(custID, totalPrice);
                        newsagentOptions();
                        break;

                    // READ Invoice
                    case 2:
                        // Implement the logic to read Invoices
                        System.out.println("Enter Invoice ID: ");
                        int readInvoiceID = in.nextInt();
                        invoice.readInvoice(readInvoiceID);
                        newsagentOptions();
                        break;

                    // UPDATE Invoice
                    case 3:
                        // Implement the logic to update an Invoice
                        System.out.println("Enter Invoice ID: ");
                        int updateInvoiceID = in.nextInt();

                        System.out.println("Enter new Total Price: ");
                        double newTotalPrice = in.nextDouble();

                        invoice.updateInvoice(updateInvoiceID, newTotalPrice);
                        newsagentOptions();
                        break;

                    // DELETE Invoice
                    case 4:
                        // Implement the logic to delete an Invoice
                        System.out.println("Enter Invoice ID: ");
                        int deleteInvoiceID = in.nextInt();
                        invoice.deleteInvoice(deleteInvoiceID);
                        newsagentOptions();
                        break;

                    // BACK
                    case 5:
                        newsagentOptions();
                        break;

                    default:
                        System.out.println("Invalid option.");
                        break;
                }
            /**
         	* LOGOUT
         	**/
            case 5:
                logOut();
                break;
            
            default:
                System.out.println("Invalid option.");
                break;
        }
    }

    private void driverOptions() throws NataliaException, SQLException, RonanException {
        Driver driver = new Driver();
        LocalDate localDateNow = LocalDate.now();

        System.out.println("\n\tDriver MENU:\t");
        System.out.println("1. READ Delivery Docket");
        System.out.println("2. UPDATE Publications Stock");
        System.out.println("3. RETURN Publications");
        System.out.println("4. LOG OUT");

        int menuOption = in.nextInt();

        switch (menuOption) {
            case 1:
                // READ DOCKET
                System.out.println("* ------------------ *");
                System.out.println("|  Today's " + localDateNow + " Delivery Docket |");
                System.out.println("* ------------------ *");

                System.out.println("Enter Area Code:");
                int areaCode = in.nextInt();
                // Fetch and display the delivery docket for the specified area code
                driver.docketCurrentDay(areaCode);
                
                break;
   
            case 2:
                // Deduct Publications from stock
            	driver.deductStock();
                break;
            case 3:
            	// Return Publications back to stock
            	driver.increaseStock();
                break;

            case 4:
                logOut();
                break;

            default:
                System.out.println("Invalid option.");
                break;
        }
        driverOptions();
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
    
    // Publication decisions
    public void publicationDecision(int option) throws RonanException, SQLException, NataliaException {
    	Publication publication = new Publication();
		switch(option) {
		case 1:
			publication.createPublication();
			break;
		case 2:
			System.out.println("Enter Publication ID:");
			int publicationIdUpdate = in.nextInt();
			publication.updatePublication(publicationIdUpdate);
			break;
		case 3:
			System.out.println("Enter Publication ID:");
			int publicationIdGet = in.nextInt();
			publication.getPublicationById(publicationIdGet);
			break;
		case 4:
			System.out.println("Enter Publication ID:");
			int publicationIdDelete = in.nextInt();
			publication.deletePublication(publicationIdDelete);
			break;
		case 5:
			publication.getAllPublications();
			break;
		case 6:
			break;
		default:
			System.out.println("Invalid option selected.");
		}
	}
    
}