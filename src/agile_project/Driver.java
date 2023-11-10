package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

public class Driver extends DatabaseConnector {

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
     * Methods
     * +docketCurrentDay
     * +submitDeliveryDocket
     */

    public void docketCurrentDay() throws NataliaException, SQLException {
        try {
            int driverId = getDriverId();

            String query = "SELECT o.orderID, o.dateCreated, c.firstName AS custFirstName, c.lastName AS custLastName, o.title, o.orderType " +
                    "FROM orders AS o " +
                    "INNER JOIN customerdetails AS c ON o.custID = c.custID " +
                    "WHERE c.areaCode = ? AND o.dateCreated = ?";

            // Get today's date
            LocalDate localDateNow = LocalDate.now();

            // Create a PreparedStatement
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(query)) {

                statement.setInt(1, driverId); // Assuming driverId is the area code for the driver
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
// Get driverID on login
    private int getDriverId() {
  
        return 1;
    }

    // Add other methods here
}
