package agile_project;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import agile_project.FINISHED.Customer;
import agile_project.FINISHED.DatabaseConnector;

public class Newsagent extends Customer {
	
	static Scanner in = new Scanner(System.in);
	
	private int newsagentID;
	private String username, password, role;
	
	public Newsagent(String username, String password, String role) throws NataliaException {
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
		}
		else {
			throw new NataliaException("Invalid role. Available roles: driver/newsagent/admin.");
		}
    }
	
	public Newsagent() {
		
	}
	
	
	/**
	 * Methods
	 * createUser
	 * updateUser
	 * deleteUser
	 * getUser
	 * getAllUsers
	 * generateDocketReport
	 * generateInvoice
	 * @throws SQLException 
	 */
	public void createCustomer(String firstName, String lastName, String address, String phoneNo) throws NataliaException, SQLException{
		if (!isValidName(firstName)) {
            throw new NataliaException("Invalid first name. Name must be between 1-15 characters.");
        }
        if (!isValidName(lastName)) {
            throw new NataliaException("Invalid last name. Surname must be between 1-15 characters.");
        }
        if (!isValidAddress(address)) {
            throw new NataliaException("Invalid address. Address must be between 1-20 characters.");
        }
        if (!isValidPhoneNo(phoneNo)) {
        	throw new NataliaException("Invalid phone number. Number must be in format 111-222-3333");
        }
        
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String query = "INSERT INTO customerdetails (firstName, lastName, address, phoneNo) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.setString(4, phoneNo);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
            	int custID = generatedKeys.getInt(1);
                this.setCustID(custID);
                this.setFirstName(firstName);
                this.setLastName(lastName);
                this.setAddress(address);
                this.setPhoneNo(phoneNo);
                
                System.out.println("Customer: " + this.getCustID() + " " + this.getFirstName() + " " + this.getLastName() + " was successfully created!");
            }
        } catch (SQLException e) {
            throw new NataliaException("Couldn't create Customer.\n" + e.getMessage());
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
	}
	
	public void updateCustomer(int id) throws NataliaException, SQLException {
		String query = "SELECT * FROM customerdetails WHERE custID = ?";
	    Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
	    	connection = DatabaseConnector.getConnection();
	    	preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        resultSet = preparedStatement.executeQuery();
	    	
	    if (resultSet.next()) {
	    	String custID = resultSet.getString("userID");
            String oldName = resultSet.getString("firstName");
            oldName += " " + resultSet.getString("lastName");
            
            System.out.println("Are you sure you want to update customer " + custID + ": " + oldName + "? (Y/N)");
            String answer = in.next();
            
            if (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("y")) {
                System.out.println("OPTIONS:\n1. UPDATE first name\n2. UPDATE last name\n3. UPDATE address\n4. UPDATE phone number");
                System.out.print("Enter option [1/2/3/4]: ");
                int option = in.nextInt();

                String updateColumn = "";
                String updatePrompt = "";
                String successMessage = "";

                switch (option) {
                    case 1:
                        updateColumn = "firstName";
                        updatePrompt = "Enter new name: ";
                        successMessage = "Name updated successfully. New name: ";
                        break;
                    case 2:
                        updateColumn = "lastName";
                        updatePrompt = "Enter new surname: ";
                        successMessage = "Password updated successfully. New password: ";
                        break;
                    case 3:
                        updateColumn = "address";
                        updatePrompt = "Enter new address: ";
                        successMessage = "Address updated successfully. New address: ";
                        break;
                    case 4:
                    	updateColumn = "phoneNo";
                    	updatePrompt = "Enter new phone number: ";
                    	successMessage = "Phone number updated successfully. New phone number: ";
                    default:
                        System.out.println("Invalid option.");
                        return;
                }
                System.out.println(updatePrompt);
                String newValue = in.next();
                
                if (isValidUpdate(updateColumn, newValue)) {
                	String updateQuery = "UPDATE customerdetails SET " + updateColumn + " = ? WHERE custID = ?";
                	preparedStatement = connection.prepareStatement(updateQuery);
                    preparedStatement.setString(1, newValue);
                    preparedStatement.setInt(2, id);
                    preparedStatement.executeUpdate();
                    System.out.println(successMessage + newValue);
                    }
                }
            }
	    }
	    catch (SQLException e) {
	        throw new NataliaException("Database error: " + e.getMessage());
	    } finally {
	        try {
	            if (resultSet != null) {
	                resultSet.close();
	            }
	            if (preparedStatement != null) {
	                preparedStatement.close();
	            }
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources: " + e.getMessage());
	        }
	        if (connection != null) {
	            connection.close();
	        }
	    }
	}
	
	public void deleteCustomer(int id) throws NataliaException, SQLException {
		System.out.println("Are you sure you want to delete customer: " + id + "?");
		String answer = in.next();
		
		Connection connection = null;
		String query = "DELETE FROM customerdetails WHERE custID = ?";
		
		try {
			connection = DatabaseConnector.getConnection();
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("YES")){
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, id); // Set the value of the userID parameter
	            stmt.executeUpdate(); // Execute the delete query
				System.out.println("Customer" + id + " has been successfully deleted.");
			} else {
				System.out.println("Deletion cancelled.");
			}
		} catch (SQLException e) {
			throw new NataliaException("Customer " + id + " doesn't exist.");
		} finally {
			connection.close();
		}
	}
	
	public Customer getCustomer(int id) throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    String query = "SELECT * FROM customerdetails WHERE custID = ?";
	    Customer customer = new Customer();
		
		try {
			connection = DatabaseConnector.getConnection();

			preparedStatement = connection.prepareStatement(query);
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				customer.setCustID(id);
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setAddress(resultSet.getString("address"));
				customer.setPhoneNo(resultSet.getString("phoneNo"));
			} else {
				throw new NataliaException("Customer with " + id + " NOT found.");
			}
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
		return customer;
	}
	
	public String getAllCustomers() throws NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
	    
	    try {
			connection = DatabaseConnector.getConnection();
			
			String query = "SELECT * FROM customerdetails ORDER BY custID ASC";
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();
			String getCustomers = "";
			
			while (resultSet.next()) {
				String custID = resultSet.getString("custID");
				String firstName = resultSet.getString("firstName");
				String lastName = resultSet.getString("lastName");
				String address = resultSet.getString("address");
				String phoneNo = resultSet.getString("phoneNo");
 
	            getCustomers += "Customer ID: " + custID + "\tFirst Name: " + firstName + "\tLast Name: " 
	            				+ lastName + "\tAddress: " + address + "\tPhoneNo: " + phoneNo + "\n";
			}
			if (getCustomers.length() == 0) {
				throw new NataliaException("Customers database empty or not found.");
			}
			return getCustomers.toString();
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
	 * Validation methods
	 * +isValidName(String name) : boolean [0-15]
	 * +isValidAddress(String address) : boolean [0-20]
	 * +isValidPhoneNo(String phoneNo) : boolean [12]
	 * +isValidUpdate(String updateColumn, String newValue) : boolean
	 */
	
	public boolean isValidName(String name) {
		if (firstName.length() <= 15 && firstName.length() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isValidAddress(String address) {
		if (address.length() <= 20 && address.length() > 0) {
			return true;
		}
		return false;
	}
	
	public boolean isValidPhoneNo(String phoneNo) {
		String pattern = "\\d{3}-\\d{3}-\\d{4}";
		return phoneNo.matches(pattern);
	}
	
	public boolean isValidUpdate(String updateColumn, String newValue) {
		return false;
	}
	
	
	/**
	 * Getters and Setters
	 * @return
	 */
	public int getNewsagentID() {
		return newsagentID;
	}
	public void setNewsagentID(int newsagentID) {
		this.newsagentID = newsagentID;
	}

}
