package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.Statement;

import agile_project.DatabaseConnector;

public class OrderPlay {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;

        do {
            System.out.println("Choose an option:");
            System.out.println("1. Create an order");
            System.out.println("2. Read orders");
            System.out.println("3. Update an order");
            System.out.println("4. Delete an order");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character
                switch (choice) {
                    case 1:
                        createOrder();
                        break;
                    case 2:
                        readOrders();
                        break;
                    case 3:
                        updateOrder();
                        break;
                    case 4:
                        deleteOrder();
                        break;
                    case 5:
                        System.out.println("Exiting... Thank you!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 5.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a valid choice.");
                scanner.next();
            }
        } while (choice != 5);

        scanner.close();
    }

    private static void createOrder() throws NataliaException {
        System.out.println("Creating an order...");

        try (Connection connection = DatabaseConnector.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter customer ID: ");
            int custID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter order type (daily, weekly, monthly): ");
            String orderType = scanner.nextLine();

            System.out.print("Enter title: ");
            String title = scanner.nextLine();

            System.out.print("Enter price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            Statement statement = connection.createStatement();
            String insertQuery = "INSERT INTO orders (dateCreated, custID, orderType, title, price) " +
                                 "VALUES (CURRENT_DATE, " + custID + ", '" + orderType + "', '" + title + "', " + price + ")";

            int rowsAffected = statement.executeUpdate(insertQuery);
            if (rowsAffected > 0) {
                System.out.println("Order created successfully.");
            } else {
                System.out.println("Failed to create the order.");
            }

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static void readOrders() throws NataliaException {
        System.out.println("Reading orders...");

        // Use DatabaseConnector to establish a connection
        try (Connection connection = DatabaseConnector.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM orders");

            // Process the result set
            while (resultSet.next()) {
                int orderID = resultSet.getInt("orderID");
                String dateCreated = resultSet.getString("dateCreated");
                int custID = resultSet.getInt("custID");
                String orderType = resultSet.getString("orderType");
                String title = resultSet.getString("title");
                double price = resultSet.getDouble("price");

                // Display fetched data (You can customize the output format)
                System.out.println("Order ID: " + orderID + ", Date: " + dateCreated + ", Cust ID: " + custID +
                        ", Type: " + orderType + ", Title: " + title + ", Price: " + price);
            }
        } catch (SQLException e) {
            // Handle any SQL errors
            e.printStackTrace();
        }
    }


    private static void updateOrder() throws NataliaException {
        System.out.println("Updating an order...");

        try (Connection connection = DatabaseConnector.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the Order ID to update: ");
            int orderID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            System.out.print("Enter new price: ");
            double newPrice = scanner.nextDouble();
            scanner.nextLine(); // Consume newline

            String updateQuery = "UPDATE orders SET price = ? WHERE orderID = ?";

            try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
                preparedStatement.setDouble(1, newPrice);
                preparedStatement.setInt(2, orderID);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    System.out.println("Order with ID " + orderID + " updated successfully.");
                } else {
                    System.out.println("No order found with ID " + orderID + ". Update failed.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static void deleteOrder() throws NataliaException {
        System.out.println("Deleting an order...");

        try (Connection connection = DatabaseConnector.getConnection()) {
            Scanner scanner = new Scanner(System.in);

            System.out.print("Enter the Order ID to delete: ");
            int orderID = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            Statement statement = connection.createStatement();
            String deleteQuery = "DELETE FROM orders WHERE orderID = " + orderID;

            int rowsAffected = statement.executeUpdate(deleteQuery);
            if (rowsAffected > 0) {
                System.out.println("Order with ID " + orderID + " deleted successfully.");
            } else {
                System.out.println("No order found with ID " + orderID + ". Deletion failed.");
            }

            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
