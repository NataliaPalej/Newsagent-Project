package agile_project;

import java.sql.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Customer {
	
	private int customerID;
	private String firstName, lastName, address, phoneNo;
	
	public Customer(String firstName, String lastName, String address, String phoneNo) throws NataliaException {
		if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()) {
            throw new NataliaException("Invalid customer attributes");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNo = phoneNo;
	}
	
	public Customer() {
	}
	
	// Get customer by ID
	public String getCustomerDetails(int id) throws NataliaException {
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
		
		try {
			connection = DatabaseConnector.getConnection();
			
			String query = "SELECT * FROM customerdetails WHERE custID = ?";
			// Prep query to be executed
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			// Execute query
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				 // Retrieve customer details from the result set
	            String firstName = resultSet.getString("firstName");
	            String lastName = resultSet.getString("lastName");
	            String address = resultSet.getString("address");
	            String phoneNo = resultSet.getString("phoneNo");
	            
	            // Return customer details
	            return "Customer ID: " + id + "\nFirst Name: " + firstName + "\nLast Name: " + lastName + "\n" +
	                    "Address: " + address + "\nPhone Number: " + phoneNo;
			} else {
				throw new NataliaException("Customer with " + id + " NOT found.");
			}
		} catch (SQLException error) {
			throw new NataliaException("Database error.\n" + error.getMessage());
		} finally {
	        // Close the database resources
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            // Handle resource closing exceptions
	            throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
	        }
		}
	}
	
	// Get all customer details 
	public String getAllCustomersDetails() throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
			connection = DatabaseConnector.getConnection();
			
			String query = "SELECT * FROM customerdetails ORDER BY custID ASC";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			String allCustomersDetails = "";
			
			while (resultSet.next()) {
				// Retrieve customer details from the result set
				String custID = resultSet.getString("custID");
	            String firstName = resultSet.getString("firstName");
	            String lastName = resultSet.getString("lastName");
	            String address = resultSet.getString("address");
	            String phoneNo = resultSet.getString("phoneNo");
	            
	            allCustomersDetails += "Customer ID: " + custID + "\tFirst Name: " + firstName +
	                    "\tLast Name: " + lastName + "\tAddress: " + address + "\tPhone Number: " + phoneNo + "\n";
			}
			if (allCustomersDetails.length() == 0) {
				throw new NataliaException("Customer database empty or not found.");
			}
			return allCustomersDetails.toString();
		} catch (SQLException error) {
			throw new NataliaException("Database error.\n" + error.getMessage());
		} finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
	        }
		}
	}
	
	public String getCustomerOrder(int id) throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    StringBuilder customerOrder = new StringBuilder();
	    
	    try {
	    	connection = DatabaseConnector.getConnection();
			
	    	String query = "SELECT c.firstName, c.lastName, c.address, o.title, o.orderType " +
	                "FROM customerdetails c " +
	                "INNER JOIN orders o ON c.custID = o.custID " +
	                "WHERE c.custID = ?";
	    	
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
	            // Retrieve customer and order details
	            String firstName = resultSet.getString("firstName");
	            String lastName = resultSet.getString("lastName");
	            String address = resultSet.getString("address");
	            String title = resultSet.getString("title");
	            String orderType = resultSet.getString("orderType");

	            // Build the result as a string
	            customerOrder.append("Customer ").append(firstName).append(" ").append(lastName).append(" order details:\n")
	                    .append("Address: ").append(address).append("\n")
	                    .append("Publication Title: ").append(title).append("\n")
	                    .append("Order Type: ").append(orderType).append("\n");
			} else {
				throw new NataliaException("Customer with " + id + " NOT found.");
			}
			return customerOrder.toString();
	    } catch (SQLException error) {
			throw new NataliaException("Database error.\n" + error.getMessage());
		} finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources.\n" + e.getMessage());
	        }
		} 
	}
	
	
	/**
	 * Validation Methods
	 */
	// EMPTY //
	
	
	/**
	 * Extra method to remove everything from database - needed for testing purposes?
	 * @throws SQLException
	 * @throws NataliaException 
	 */
//	public void removeAllCustomersFromDatabase() throws SQLException, NataliaException {
//		Connection connection = null;
//		java.sql.Statement statement = null;
//	    try {
//	    	connection = DatabaseConnector.getConnection();
//	    	statement = connection.createStatement();
//	    	String deleteInvoice = "DELETE FROM invoice";
//	    	String deleteOrder = "DELETE FROM orders";
//	        String deleteQuery = "DELETE FROM customerdetails";
//	        statement.executeUpdate(deleteInvoice);
//	        statement.executeUpdate(deleteOrder);
//	        statement.executeUpdate(deleteQuery);
//	    } catch (SQLException e) {
//	        throw new NataliaException("Couldn't remove database.\n" + e.getMessage());
//	    } finally {
//	        if (statement != null) {
//	            statement.close();
//	        }
//	        if (connection != null) {
//	            connection.close();
//	        }
//	    }
//	}
	
	
	/**
	 * Getters & Setters
	 * @return
	 */
	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
}
