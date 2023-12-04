package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Scanner;

public class Order {
	private int orderID;
	private LocalDate orderDate;
	private String type;
	private int pubID;
	private String title;
	private double pubPrice;
	private int custID;
	private String custName;

	public Order(int orderID, LocalDate orderDate,String type,int pubID,String title,int custID,String custName) throws NataliaException{
		this.orderID = orderID;
		this.orderDate = orderDate;
		this.type = type;
		this.pubID = pubID;
		this.title = title;
		this.custID = custID;
		this.custName = custName;
	}
	public Order() {
		// TODO Auto-generated constructor stub
	}
	// CREATE Order
	public void createOrder() throws NataliaException {
		try (Connection connection = DatabaseConnector.getConnection()) {
			Scanner scanner = new Scanner(System.in);

			System.out.println("Enter order details:");
			System.out.print("Order Type (daily/weekly/monthly): ");
			String orderType = scanner.next();

			System.out.print("Title: ");
			String title = scanner.next();

			// Fetch the publicationID based on the entered title
			int publicationID = getPublicationID(connection, title);

			System.out.print("CustID: ");
			int custID = scanner.nextInt();

			// Insert new order into the 'orders' table
			String insertOrderQuery = "INSERT INTO orders (orderType, publicationID, custID, dateCreated) VALUES (?, ?, ?, CURDATE())";
			try (PreparedStatement preparedStatement = connection.prepareStatement(insertOrderQuery)) {
				// Set the values for the parameters
				preparedStatement.setString(1, orderType);
				preparedStatement.setInt(2, publicationID);
				preparedStatement.setInt(3, custID);

				// Execute the insertion query
				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Order successfully inserted!");
				} else {
					System.out.println("Failed to insert order.");
				}
			}
		} catch (SQLException e) {
			throw new NataliaException("Error creating order: " + e.getMessage());
		}
	}

	// READ Order
	public void readOrder() throws NataliaException {
		System.out.println("Reading orders...");

		try (Connection connection = DatabaseConnector.getConnection()) {
			Scanner scanner = new Scanner(System.in);
			System.out.print("Enter Area Code: ");
			int areaCodeInput = scanner.nextInt();

			String query = "SELECT o.orderID, o.dateCreated, c.firstName, c.lastName, c.areaCode, c.address, p.title as publicationTitle, p.issueNo " +
					"FROM orders o " +
					"JOIN customerdetails c ON o.custID = c.custID " +
					"JOIN publications p ON o.publicationID = p.publicationID " +
					"WHERE c.areaCode = ?";

			try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
				preparedStatement.setInt(1, areaCodeInput);

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					// Process the result set
					while (resultSet.next()) {
						int orderID = resultSet.getInt("orderID");
						String dateCreated = resultSet.getString("dateCreated");
						String custName = resultSet.getString("firstName") + " " + resultSet.getString("lastName");
						int areaCode = resultSet.getInt("areaCode");
						String address = resultSet.getString("address");
						String publicationTitle = resultSet.getString("publicationTitle");
						int issueNo = resultSet.getInt("issueNo");

						// Display fetched data (You can customize the output format)
						System.out.println("Order ID: " + orderID + ", Date: " + dateCreated + ", Customer Name: " + custName
								+ ", Area Code: " + areaCode + ", Address: " + address + ", Title: " + publicationTitle + ", Issue No.: " + issueNo);
						System.out.println("---------------------------------------------------------------------------------------------------------------");
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Handle the exception according to your application's needs
		}
	}

	// UPDATE Order (Assuming you want to update the price)
	public void updateOrder(int orderID, LocalDate newDate, int newCustID, String newType, String newTitle, double newPrice) throws NataliaException {
		try (Connection connection = DatabaseConnector.getConnection()) {
			String updateOrderQuery = "UPDATE orders SET dateCreated = ?, custID = ?, orderType = ?, title = ?, price = ? WHERE orderID = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(updateOrderQuery)) {
				preparedStatement.setDate(1, java.sql.Date.valueOf(newDate));
				preparedStatement.setInt(2, newCustID);
				preparedStatement.setString(3, newType);
				preparedStatement.setString(4, newTitle);
				preparedStatement.setDouble(5, newPrice);
				preparedStatement.setInt(6, orderID);

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Order updated successfully!");
				} else {
					System.out.println("Failed to update order. Order ID not found.");
				}
			}
		} catch (SQLException e) {
			throw new NataliaException("Error updating order: " + e.getMessage());
		}
	}


	// DELETE Order
	public void deleteOrder(int orderID) throws NataliaException {
		try (Connection connection = DatabaseConnector.getConnection()) {
			String deleteOrderQuery = "DELETE FROM orders WHERE orderID = ?";
			try (PreparedStatement preparedStatement = connection.prepareStatement(deleteOrderQuery)) {
				preparedStatement.setInt(1, orderID);

				int rowsAffected = preparedStatement.executeUpdate();

				if (rowsAffected > 0) {
					System.out.println("Order deleted successfully!");
				} else {
					System.out.println("Failed to delete order. Order ID not found.");
				}
			}
		} catch (SQLException e) {
			throw new NataliaException("Error deleting order: " + e.getMessage());
		}
	}

	private int getPublicationID(Connection connection, String title) throws SQLException {
		String query = "SELECT publicationID FROM publications WHERE title = ?";
		try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
			preparedStatement.setString(1, title);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				if (resultSet.next()) {
					return resultSet.getInt("publicationID");
				} else {
					throw new SQLException("Publication not found with title: " + title);
				}
			}
		}
	}


	public boolean validOrderID(int orderID) {
		return orderID > 0; // Assuming order IDs are positive integers
	}

	public boolean validOrderDate(LocalDate date) {
		return date.isEqual(LocalDate.now());
	}

	public boolean validType(String type) {
		return type.equals("daily") || type.equals("weekly") || type.equals("monthly");
	}

	public boolean validPubID(int pubID) {
		// Example: Validation logic could involve a database check
		// Return true for simplicity; actual validation requires a database lookup
		return pubID > 0 && pubID <= 1000; // Assuming publication IDs range from 1 to 1000
	}

	public boolean validTitle(String title) {
		// Example: Validation logic checks if the title length is within a specific range
		return title.length() >= 5 && title.length() <= 50; // Assuming title length constraints
	}

	public boolean validPubPrice(double pubPrice) {
		// Example: Validation logic ensures the price is within a reasonable range
		return pubPrice > 0 && pubPrice <= 10000; // Assuming prices range from 0 to 10000
	}

	public boolean validCustID(int custID) {
		// Example: Validation logic could involve a database check
		// Return true for simplicity; actual validation requires a database lookup
		return custID > 0 && custID <= 500; // Assuming customer IDs range from 1 to 500
	}

	public boolean validCustName(String custName) {
		// Example: Validation logic checks if the name length is within a specific range
		return custName.length() >= 3 && custName.length() <= 50; // Assuming name length constraints
	}

	// Other methods, constructor, or further functionality can be added here
}
