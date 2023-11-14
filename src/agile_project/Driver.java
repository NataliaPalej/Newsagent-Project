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

    public Driver(String username, String password, String role) throws NataliaException {
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

            String query = "SELECT o.orderID, o.dateCreated, c.firstName AS custFirstName, c.lastName AS custLastName, o.title, o.orderType " +
                    "FROM orders AS o " +
                    "INNER JOIN customerdetails AS c ON o.custID = c.custID " +
                    "WHERE c.areaCode = ? AND DATE(o.dateCreated) = ?";

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
                        System.out.println("Date Created: " + resultSet.getString("dateCreated"));
                        System.out.println("Customer Name: " + resultSet.getString("custFirstName") + " " + resultSet.getString("custLastName"));
                        System.out.println("Title: " + resultSet.getString("title"));
                        System.out.println("Order Type: " + resultSet.getString("orderType"));
                        System.out.println("------------------------------");
                    }
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Error executing SQL query: " + e.getMessage());
        }
    }

    // Add other methods here
}
