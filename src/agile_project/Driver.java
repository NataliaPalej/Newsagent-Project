package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
	 * Won't return anything unless update todays date of orders in dataBase.!!!!!!!!
	 * Methods
	 * +docketCurrentDay
	 * +submitDeliveryDocket
	 */

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
			e.printStackTrace();
			throw new SQLException("Error executing SQL query: " + e.getMessage());
		}
	}


	private void deductStock() throws NataliaException, SQLException {
		Connection connection = getConnection();
		try {
			System.out.println("Enter Order ID:");
			int orderID = in.nextInt();

			// Check if the order exists and get the publication ID and quantity
			String getOrderQuery = "SELECT publicationID FROM orders WHERE orderID = ?";
			try (PreparedStatement getOrderStatement = connection.prepareStatement(getOrderQuery)) {
				getOrderStatement.setInt(1, orderID);
				ResultSet orderResultSet = getOrderStatement.executeQuery();

				if (orderResultSet.next()) {
					int publicationID = orderResultSet.getInt("publicationID");

					// Check current stock
					String getStockQuery = "SELECT stock FROM publications WHERE publicationID = ?";
					try (PreparedStatement getStockStatement = connection.prepareStatement(getStockQuery)) {
						getStockStatement.setInt(1, publicationID);
						ResultSet stockResultSet = getStockStatement.executeQuery();

						if (stockResultSet.next()) {
							int currentStock = stockResultSet.getInt("stock");

							// Update stock
							String updateStockQuery = "UPDATE publications SET stock = ? WHERE publicationID = ?";
							try (PreparedStatement updateStockStatement = connection.prepareStatement(updateStockQuery)) {
								updateStockStatement.setInt(1, currentStock - 1); // Deduct 1 from stock
								updateStockStatement.setInt(2, publicationID);
								int rowsUpdated = updateStockStatement.executeUpdate();

								if (rowsUpdated > 0) {
									System.out.println("Stock deducted successfully.");
								} else {
									System.out.println("Failed to deduct stock.");
								}
							}
						}
					}
				} else {
					System.out.println("Order not found.");
				}
			}
		} catch (Exception e) {
			throw new SQLException("Error deducting stock: " + e.getMessage());
		}
	}

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

	// Add other methods here
}
