package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Driver extends DatabaseConnector {
	
	static Scanner in = new Scanner(System.in);

	private int driverID;
	private String username, password, role;
	/**
	 * Constructors
	 * Driver(String username, String password, String role)
	 * Driver()
	 */
	public Driver(String username, String password, String role) throws NataliaException {
		if (username.isEmpty() || username.length() < 1 || username.length() > 10) {
			throw new NataliaException("Invalid username. Username must be between 1-10 characters.");
		}
		if (password.isEmpty() || password.length() < 6 || password.length() > 10 || !password.matches(".*\\d.*")) {
			throw new NataliaException("Invalid password. Password must be between 6-10 characters, include at least one digit and uppercase letter.");
		}
		if (role.equalsIgnoreCase("driver")) {
			this.username = username;
			this.password = password;
			this.role = role;
		} else {
			throw new NataliaException("Invalid role. Available roles: driver/newsagent/admin.");
		}
	}
//##############################################################################
	public Driver() {

	}
	
	/**
	 * Methods
	 * +docketCurrentDay
	 * +submitDeliveryDocket
	 * +updatePublicationStock
	 * +printDocketAndUpdateStock
	 */
//##############################################################################

	public void docketCurrentDay(int areaCode) throws NataliaException, SQLException {
		try {
			// Get today's date
			LocalDate localDateNow = LocalDate.now();

			String query = "SELECT \r\n"
					+ "    o.orderID,\r\n"
					+ "    o.dateCreated,\r\n"
					+ "    c.firstName,\r\n"
					+ "    c.lastName,\r\n"
					+ "    c.areaCode,\r\n"
					+ "    c.address,\r\n"
					+ "    p.title AS publicationTitle,\r\n"
					+ "    p.issueNo AS publicationIssueNo\r\n"
					+ "FROM \r\n"
					+ "    orders o\r\n"
					+ "INNER JOIN \r\n"
					+ "    customerdetails c ON o.custID = c.custID\r\n"
					+ "INNER JOIN \r\n"
					+ "    publications p ON o.publicationID = p.publicationID\r\n"
					+ "WHERE \r\n"
					+ "    c.areaCode = ?\r\n"
					+ "    AND o.dateCreated = ?\r\n"
					+ "ORDER BY \r\n"
					+ "    o.orderID;";

			// Create a PreparedStatement
			try (Connection connection = getConnection();
					PreparedStatement statement = connection.prepareStatement(query)) {

				statement.setInt(1, areaCode);
				statement.setString(2, localDateNow.toString());

				// Execute the query
				try (ResultSet resultSet = statement.executeQuery()) {
					// Process the results
					while (resultSet.next()) {
						// Extract and display the information
						System.out.println("Order ID: " + resultSet.getInt("orderID"));
						System.out.println("Date: " + resultSet.getString("dateCreated"));
						System.out.println("Customer Name: " + resultSet.getString("firstName") + " " + resultSet.getString("lastName"));
						System.out.println("Address: " + resultSet.getString("address"));
						System.out.println("Title: " + resultSet.getString("publicationTitle"));
						System.out.println("Publication Issue No.: " + resultSet.getString("publicationIssueNo"));
						System.out.println("------------------------------");
					}
				}
			}
		} catch (SQLException e) {
			throw new SQLException("Error executing SQL query: " + e.getMessage());
		}
	}
//##############################################################################
    public void printDocketAndUpdateStock(int areaCode) throws NataliaException {
        try {
            // Get today's date
            LocalDate localDateNow = LocalDate.now();

            String query = "SELECT p.publicationID, p.title, p.stock " +
                    "FROM orders o " +
                    "INNER JOIN customerdetails c ON o.custID = c.custID " +
                    "INNER JOIN publications p ON o.publicationID = p.publicationID " +
                    "WHERE c.areaCode = ? AND o.dateCreated = ?";

            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, areaCode);
                statement.setString(2, localDateNow.toString());

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int publicationID = resultSet.getInt("publicationID");
                        String title = resultSet.getString("title");
                        int stock = resultSet.getInt("stock");

                        System.out.println("Publication ID: " + publicationID);
                        System.out.println("Title: " + title);
                        System.out.println("Stock before update: " + stock);

                        // Update the stock in the database
                        if (stock > 0) {
                            updatePublicationStock(publicationID, stock - 1);
                            System.out.println("Stock updated to: " + (stock - 1));
                        } else {
                            System.out.println("Out of stock: " + title);
                        }

                        System.out.println("------------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            throw new NataliaException("Error printing docket and updating stock: " + e.getMessage());
        }
    }
//##############################################################################
    public void updatePublicationStock(int publicationID, int newStock) throws NataliaException {
        try {
            String updateQuery = "UPDATE publications SET stock = ? WHERE publicationID = ?";

            try (Connection connection = getConnection();
                 PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

                updateStatement.setInt(1, newStock);
                updateStatement.setInt(2, publicationID);

                updateStatement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new NataliaException("Error updating publication stock: " + e.getMessage());
        }
    }
 //##############################################################################

    public void submitDeliveryDocket(int areaCodeSubmit, int orderID) throws NataliaException, SQLException {
        // Validate area code before submitting the delivery docket
        if (isValidAreaCode(areaCodeSubmit)) {
            // Display order details based on the area code
            docketCurrentDay(areaCodeSubmit);

            // Update the publication stock associated with the given order ID
            int updatedStock = updatePublicationStock(orderID);

            System.out.println("Delivery docket submitted successfully!");
            System.out.println("Updated Publication Stock: " + updatedStock);
        } else {
            System.out.println("Invalid area code. Please enter a valid area code.");
        }
    }
//##############################################################################
    public int updatePublicationStock(int orderID) throws NataliaException {
        try {
            String query = "SELECT p.publicationID, p.stock " +
                    "FROM orders o " +
                    "INNER JOIN publications p ON o.publicationID = p.publicationID " +
                    "WHERE o.orderID = ?";

            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, orderID);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int publicationID = resultSet.getInt("publicationID");
                        int stock = resultSet.getInt("stock");

                        // Update the stock in the database
                        updatePublicationStock(publicationID, stock + 1);

                        return stock + 1; // Return the updated stock
                    } else {
                        System.out.println("Order ID not found.");
                        return -1; // Indicate that order ID was not found
                    }
                }
            }
        } catch (SQLException e) {
            throw new NataliaException("Error updating publication stock: " + e.getMessage());
        }
    }
//##############################################################################
	/** 
	 * Validation methods
	 * @param int driverID >0;
	 * @param password between 6-10 characters + at least one number&upper case
	 * @param role: admin/newsagent/driver
	 */
    public boolean isValidAreaCode(int areaCode) {
        // Assuming each driver is assigned an area code from 1 to 12
        return areaCode >= 1 && areaCode <= 12;
    }
    public boolean isValidDriverID(int driverID) {
        // Assuming each driver have assigned id from 1 to 12
        return driverID >= 1 && driverID <= 12;
    }
	public boolean isValidName(String name) {
		if (name.length() <= 15 && name.length() > 1) {
			return true;
		} else {
			return false;
		}
	}
	public boolean isValidAddress(String address) {
		if (address.length() <= 20 && address.length() > 1) {
			return true;
		}
		return false;
	}
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
		if (role.equalsIgnoreCase("driver")) {
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
	 */
	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

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

}//    end of class
