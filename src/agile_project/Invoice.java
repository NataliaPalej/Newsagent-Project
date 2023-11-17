package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void createInvoice(int custID, double totalPrice)
            throws SQLException {
    String insertQuery = "INSERT INTO invoices (custID, totalPrice) VALUES (?, ?)";

    try (PreparedStatement preparedStatement = conn.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
        preparedStatement.setInt(1, custID);
        preparedStatement.setDouble(2, totalPrice);

        int rowsAffected = preparedStatement.executeUpdate();

        if (rowsAffected > 0) {
            System.out.println("Invoice created successfully!");

            // Retrieve the generated keys (in this case, the auto-generated invoiceID)
            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int generatedInvoiceID = generatedKeys.getInt(1);

                    // Fetch and display the inserted data
                    readInvoice(generatedInvoiceID);
                }
            }
        } else {
            System.out.println("Failed to create the invoice.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}



 // Method to read invoice details
    public void readInvoice(int invoiceID) throws SQLException {
        String selectQuery = "SELECT * FROM invoices WHERE invoiceID = ?";

        try (PreparedStatement preparedStatement = conn.prepareStatement(selectQuery)) {
            preparedStatement.setInt(1, invoiceID);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    displayInvoiceDetails(resultSet);
                } else {
                    System.out.println("Invoice not found.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   



    // Method to update an existing invoice
    public void updateInvoice(int invoiceID, double newTotalPrice) throws SQLException {
        String updateQuery = "UPDATE invoices SET totalPrice = ? WHERE invoiceID = ?";

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
        String deleteQuery = "DELETE FROM invoices WHERE invoiceID = ?";

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
    private void displayInvoiceDetails(ResultSet resultSet) throws SQLException {
        int invoiceID = resultSet.getInt("invoiceID");
        int custID = resultSet.getInt("custID");
        double totalPrice = resultSet.getDouble("totalPrice");

        System.out.println("Invoice ID: " + invoiceID);
        System.out.println("Customer ID: " + custID);
        System.out.println("Total Price: " + totalPrice);
    }

    }
