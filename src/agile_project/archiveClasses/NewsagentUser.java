package agile_project.archiveClasses;

import java.sql.*;

import agile_project.DatabaseConnector;
import agile_project.NataliaException;

public class NewsagentUser extends DatabaseConnector {
	private int newsagentID;
	private String username, password, role;
	
	public NewsagentUser(int newsagentID, String username, String password, String role) throws NataliaException {
		this.newsagentID = newsagentID;
		this.username = username;
		this.password =  password;
		this.role = role;
	}
	
	public void insertNewCustomer(int customerID, String firstName, String lastName, String custAddress, String phoneNo) throws NataliaException, SQLException {
		Connection connection = null;
		PreparedStatement insertCustomer = null;
		
		try {
			connection = getConnection();
			
			// Defining SQL statement to insert new customer
			String insert = "INSERT INTO customerDetails (firstName, lastName, custAddress, phoneNo) VALUES (?, ?, ?, ?)";
			insertCustomer = connection.prepareStatement(insert);
			
			// Setting the parameters to insert cust to database
			insertCustomer.setString(1, firstName);
			insertCustomer.setString(2, lastName);
			insertCustomer.setString(3, custAddress);
			insertCustomer.setString(4, phoneNo);
			
			insertCustomer.executeUpdate();	
		}
		catch (SQLException e){
			throw new NataliaException("Couldn't INSERT into database: \n" + e.getMessage());
		}
		finally {
			if (insertCustomer != null) {
				insertCustomer.close();
			}
			if (connection != null) {
				connection.close();
			}
		}
	}
	
	public void updateNewCustomer() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public boolean deactivateCustomer() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public String readCustomerDetails() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public void printCustomerDetails() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public void printInvoice() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public void printDeliveryDocketReport() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	/**
	 * Verification methods 
	 * @return
	 * @throws NataliaException
	 */
	public boolean isValidName() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public boolean isValidCustAddress() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}
	
	public boolean isValidPhoneNo() throws NataliaException {
		throw new NataliaException("Method not yet implemented");
	}

}


