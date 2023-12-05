package agile_project;

import java.sql.*;
import java.sql.SQLException;

import agile_project.Exceptions.NataliaException;

public class Customer {
	
	protected int custID, areaCode;
	protected String firstName, lastName, address;
	protected String phoneNo;
	
	/**
	 * Constructors
	 * Customer(String firstName, String lastName, String address, String phoneNo)
	 * Customer()
	 */
	public Customer(String firstName, String lastName, String address, String phoneNo, int areaCode) throws NataliaException {
		if (firstName.isEmpty() || lastName.isEmpty() || address.isEmpty()) {
            throw new NataliaException("Invalid customer attributes");
		}
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNo = phoneNo;
		this.areaCode = areaCode;	
		}
	
	public Customer() {
	}
	

	/**
	 * Methods
	 * getCustomerDetails(int id)
	 * getAllCustomersDetails
	 * getCustomerOrder(int id)
	 */
	public Customer getCustomer(int id) throws NataliaException {
		
		System.out.println("* ------------------------- *");
    	System.out.println("|  Print Customer Details   |");
    	System.out.println("* ------------------------- *");
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    Customer customer = new Customer();
		
		try {
			connection = DatabaseConnector.getConnection();
			
			String query = "SELECT * FROM customerdetails WHERE custID = ?";
			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				// Retrieve customer details from the result set
				customer.setCustID(id);
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhoneNo(resultSet.getString("phoneNo"));
				customer.setAreaCode(resultSet.getInt("areaCode"));
	            
	            System.out.println("Customer ID: " + customer.getCustID() + "\nFirst Name: " + customer.getFirstName() + "\nLast Name: " 
	            + customer.getLastName() + "\n" + "Address: " + customer.getAddress() + "\nPhone Number: " + customer.getPhoneNo() + customer.getAreaCode());
			} else {
				throw new NataliaException("Customer with " + customer.getCustID() + " NOT found.");
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
		return customer;
	}
	
	public String getAllCustomers() throws NataliaException {
		
		System.out.println("* ----------------------------- *");
    	System.out.println("|  Print All Customers Details  |");
    	System.out.println("* ----------------------------- *");
		
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
			connection = DatabaseConnector.getConnection();
			
			String query = "SELECT * FROM customerdetails ORDER BY custID";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			
			String allCustomersDetails = "";
			
			while (resultSet.next()) {
				String custID = resultSet.getString("custID");
	            String firstName = resultSet.getString("firstName");
	            String lastName = resultSet.getString("lastName");
	            String address = resultSet.getString("address");
	            String phoneNo = resultSet.getString("phoneNo");
	            String areaCode = resultSet.getString("areaCode");
	            
	            String formattedCustID = String.format("%-8s", custID);
	            String formattedFirstName = String.format("%-15s", firstName);
	            String formattedLastName = String.format("%-15s", lastName);
	            String formattedAddress = String.format("%-20s", address);
	            String formattedPhoneNo = String.format("%-15s", phoneNo);
	            String formattedAreaCode = String.format("%-15s", areaCode);
	            
	            allCustomersDetails += "Customer ID: " + custID +
	                    "\tFirst Name: " + firstName +
	                    "\tLast Name: " + lastName +
	                    "\tAddress: " + address +
	                    "\tPhone Number: " + phoneNo +
	                    "\tArea Code: " + areaCode + "\n";
	            
	            System.out.println("Customer ID: " + formattedCustID +
	                    "First Name: " + formattedFirstName +
	                    "Last Name: " + formattedLastName +
	                    "Address: " + formattedAddress +
	                    "Phone Number: " + formattedPhoneNo +
	                    "Area Code: " + formattedAreaCode + "\n");
			}
			return allCustomersDetails;
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
	            String firstName = resultSet.getString("firstName");
	            String lastName = resultSet.getString("lastName");
	            String address = resultSet.getString("address");
	            String title = resultSet.getString("title");
	            String orderType = resultSet.getString("orderType");

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
	public int getCustID() {
		return custID;
	}

	public void setCustID(int custID) {
		this.custID = custID;
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

	public int getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(int areaCode) {
		this.areaCode = areaCode;
	}
}
