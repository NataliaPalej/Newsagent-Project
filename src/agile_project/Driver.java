package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Driver extends DatabaseConnector {
	// Method to submit the delivery docket
	// Method to submit the delivery docket
	public void submitDeliveryDocket() throws NataliaException {
		try {
			// Get today's date
			LocalDate localDateNow = LocalDate.now();

			// Update the status of delivered orders in the orders table
			String updateQuery = "UPDATE orders SET status = 'delivered' WHERE DATE(dateCreated) = ?";

			try (Connection connection = getConnection();
					PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

				updateStatement.setString(1, localDateNow.toString());
				int updatedRows = updateStatement.executeUpdate();

				System.out.println(updatedRows + " orders marked as delivered.");
			}
		} catch (SQLException e) {
			System.out.println("Error submitting delivery docket: " + e.getMessage());
		}
	}
	static Scanner in = new Scanner(System.in);

	private int driverID;
	private String username, password, role;

	public Driver(int driverID, String username, String password, String role) throws NataliaException {
		if (driverID < 0) {
			throw new NataliaException("Invalid ID. ID must be greater than 0.");
		}
		if (username.isEmpty() || username.length() < 1 || username.length() > 10) {
			throw new NataliaException("Invalid username. Username must be between 1-10 characters.");
		}
		if (password.isEmpty() || password.length() < 6 || password.length() > 10 || !password.matches(".*\\d.*")) {
			throw new NataliaException("Invalid password. Password must be between 6-10 characters, include at least one digit and uppercase letter.");
		}
		if (role.equalsIgnoreCase("driver") || role.equalsIgnoreCase("newsagent") || role.equalsIgnoreCase("admin")) {
			this.username = username;
			this.password = password;
			this.role = role;
		} else {
			throw new NataliaException("Invalid role. Available roles: driver/newsagent/admin.");
		}
	}

	public Driver() {

	}

	/**
	 * Methods
	 * +docketCurrentDay -> this also counts how many books to be delivered per areaCode
	 * +submitDeliveryDocket
	 */

	// Modify the method to count books delivered per title
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

            // Use a Map to count books delivered per title
            Map<String, Integer> booksDeliveredCount = new HashMap<>();

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
                        String publicationTitle = resultSet.getString("publicationTitle");
                        System.out.println("Title: " + publicationTitle);
                        System.out.println("Publication Issue No.: " + resultSet.getString("publicationIssueNo"));
                        System.out.println("------------------------------");

                        // Update the count for the current title in the Map
                        booksDeliveredCount.put(publicationTitle, booksDeliveredCount.getOrDefault(publicationTitle, 0) + 1);
                    }
                }
            }

            // Print the count for each title
            System.out.println("Books Delivered Count:");
            for (Map.Entry<String, Integer> entry : booksDeliveredCount.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Error executing SQL query: " + e.getMessage());
        }
    }
	
	// Deduct stock quantity by book's title
	void deductStock() throws NataliaException, SQLException {
	    Connection connection = getConnection();

	    try {
	        System.out.println("Enter Book Title: ");
	        String bookTitle = in.nextLine();

	        // Check if the book exists and get the publication ID and quantity
	        String getPublicationQuery = "SELECT publicationID, stock FROM publications WHERE title = ?";
	        try (PreparedStatement getPublicationStatement = connection.prepareStatement(getPublicationQuery)) {
	            getPublicationStatement.setString(1, bookTitle);
	            ResultSet publicationResultSet = getPublicationStatement.executeQuery();

	            if (publicationResultSet.next()) {
	                int publicationID = publicationResultSet.getInt("publicationID");
	                int currentStock = publicationResultSet.getInt("stock");

	                if (isValidStock(currentStock)) {
	                    System.out.println("Enter how many books were delivered: ");
	                    int booksDelivered = in.nextInt();

	                    // Update stock
	                    String updateStockQuery = "UPDATE publications SET stock = ? WHERE publicationID = ?";
	                    try (PreparedStatement updateStockStatement = connection.prepareStatement(updateStockQuery)) {
	                        updateStockStatement.setInt(1, currentStock - booksDelivered);
	                        updateStockStatement.setInt(2, publicationID);
	                        int rowsUpdated = updateStockStatement.executeUpdate();

	                        if (rowsUpdated > 0) {
	                            System.out.println("Stock deducted successfully. Current stock for " + bookTitle + ": " + (currentStock - booksDelivered));
	                        } else {
	                            System.out.println("Failed to deduct stock.");
	                        }
	                    } catch (SQLException e) {
	                        System.out.println("Error while deducting stock.\n" + e.getMessage());
	                    }
	                } else {
	                    System.out.println("Book: " + bookTitle + " is OUT OF STOCK.");
	                }
	            } else {
	                System.out.println("Book title not found.");
	            }
	        }
	    } catch (SQLException e) {
	        throw new SQLException("Error deducting stock: " + e.getMessage());
	    }
	}

	
	/**
	 * VALIDATION METHODS
	 * isValidStock
	 */
	private boolean isValidStock(int stock) {
		if (stock > 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * GETTERS AND SETTERS
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
}
