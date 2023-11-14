package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Invoice {
    private Connection conn;

    public Invoice(Connection conn) {
        this.conn = conn;
    }

    public boolean validInvoiceID(int invoiceID) {
        return invoiceID > 0;
    }

    public boolean validInvoiceDate(LocalDate date) {
        return date.isEqual(LocalDate.now());
    }

    public boolean validCustID(int custID) {
        // You might want to check if custID exists in the database
        return custID > 0;
    }

    public boolean validCustName(String custName) {
        return custName.length() >= 3 && custName.length() <= 50;
    }

    public boolean validCustAddress(String custAddress) {
        return custAddress.length() >= 3 && custAddress.length() <= 50;
    }

    public boolean validQuantityDelivered(int quantityDelivered) {
        return quantityDelivered > 0;
    }

    public boolean validTotalValue(double totalValue) {
        return totalValue > 0;
    }
    public boolean NOTValidTotalValue(double totalValue) {
        if (totalValue <= 0) {
            System.out.println("Error: Total value must be greater than 0.");
            return false;
        }
        return true;
    }
 // Method to create a new invoice
    public void createInvoice(int custID, int publicationID, int orderID, double totalPrice, int totalQuantityDelivered, double totalValue)
            throws SQLException {
        String insertQuery = "INSERT INTO invoice (custID, publicationID, orderID, totalPrice, totalQuantityDelivered, totalValue) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery)) {
            preparedStatement.setInt(1, custID);
            preparedStatement.setInt(2, publicationID);
            preparedStatement.setInt(3, orderID);
            preparedStatement.setDouble(4, totalPrice);
            preparedStatement.setInt(5, totalQuantityDelivered);
            preparedStatement.setDouble(6, totalValue);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Invoice created successfully!");
            } else {
                System.out.println("Failed to create the invoice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Method to get invoice details
    public void readInvoice(int invoiceID) throws SQLException {
        String selectQuery = "SELECT * FROM invoice WHERE invoiceID = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, invoiceID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    displayInvoiceDetails(resultSet);
                } else {
                    System.out.println("Invoice not found.");
                }
            }
        }
    }

    // Method to update an existing invoice
    public void updateInvoice(int invoiceID, double newTotalPrice) throws SQLException {
        String updateQuery = "UPDATE invoice SET totalPrice = ? WHERE invoiceID = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(updateQuery)) {
            preparedStatement.setDouble(1, newTotalPrice);
            preparedStatement.setInt(2, invoiceID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Invoice updated successfully!");
            } else {
                System.out.println("Failed to update the invoice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to delete an existing invoice
    public void deleteInvoice(int invoiceID) throws SQLException {
        String deleteQuery = "DELETE FROM invoice WHERE invoiceID = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(deleteQuery)) {
            preparedStatement.setInt(1, invoiceID);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Invoice deleted successfully!");
            } else {
                System.out.println("Failed to delete the invoice.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility method to display invoice details
    private String displayInvoiceDetails(ResultSet resultSet) throws SQLException {
        StringBuilder details = new StringBuilder();
        details.append("Invoice details:\n");
        details.append("Invoice ID: ").append(resultSet.getInt("invoiceID")).append("\n");
        details.append("Customer ID: ").append(resultSet.getInt("custID")).append("\n");
        details.append("Publication ID: ").append(resultSet.getInt("publicationID")).append("\n");
        details.append("Order ID: ").append(resultSet.getInt("orderID")).append("\n");
        details.append("Total Price: ").append(resultSet.getDouble("totalPrice")).append("\n");
        details.append("Total Quantity Delivered: ").append(resultSet.getInt("totalQuantityDelivered")).append("\n");
        return details.toString();
    }
}
