package agile_project;

import java.sql.*;
import java.util.Scanner;

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
	 * +createUser
	 * +updateUser
	 * +deleteUser
	 * +getUser
	 * +getAllUsers
	 * +generateDocketReport
	 * +generateInvoice
	 * +doesCustomerExist(int id) : boolean
	 */
	public void createCustomer() throws NataliaException, SQLException{
		
		System.out.println("* ------------------- *");
    	System.out.println("|   Create Customer   |");
    	System.out.println("* ------------------- *");
		
		System.out.print("Enter First Name: ");
		String firstName = in.next();
		if (!isValidName(firstName)) {
            throw new NataliaException("Invalid first name. Name must be between 1-15 characters.");
        }
		System.out.print("Enter Last Name: ");
		String lastName = in.next();
        if (!isValidName(lastName)) {
            throw new NataliaException("Invalid last name. Surname must be between 1-15 characters.");
        }
        System.out.print("Enter Address: ");
		String address = in.next();
        if (!isValidAddress(address)) {
            throw new NataliaException("Invalid address. Address must be between 1-20 characters.");
        }
        System.out.print("Enter Phone No (XXX-XXX-XXXX): ");
		String phoneNo = in.next();
        if (!isValidPhoneNo(phoneNo)) {
        	throw new NataliaException("Invalid phone number. Number must be in format 111-222-3333.");
        }
        System.out.print("Enter Area Code (1-12): ");
		int areaCode = in.nextInt();
        if (!isValidAreaCode(areaCode)) {
        	throw new NataliaException("Invalid Area Code. Number must be between 1 and 12.");
        }
        
        Connection connection = null;
        try {
            connection = DatabaseConnector.getConnection();
            String query = "INSERT INTO customerdetails (firstName, lastName, address, phoneNo, areaCode) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, address);
            stmt.setString(4, phoneNo);
            stmt.setInt(5, areaCode);
            stmt.executeUpdate();

            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if (generatedKeys.next()) {
            	int custID = generatedKeys.getInt(1);
                this.setCustID(custID);
                this.setFirstName(firstName);
                this.setLastName(lastName);
                this.setAddress(address);
                this.setPhoneNo(phoneNo);
                this.setAreaCode(areaCode);
                
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
	

	@SuppressWarnings("resource")
	public void updateCustomer(int id) throws NataliaException, SQLException {
		
		System.out.println("* ------------------- *");
    	System.out.println("|   Update Customer   |");
    	System.out.println("* ------------------- *");
		
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
	    	String custID = resultSet.getString("custID");
            String name = resultSet.getString("firstName");
            name += " " + resultSet.getString("lastName");
            
            System.out.println("Are you sure you want to update customer " + custID + ": " + name + "? (Y/N)");
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
                        successMessage = "First name updated successfully. New first name: ";
                        break;
                    case 2:
                        updateColumn = "lastName";
                        updatePrompt = "Enter new surname: ";
                        successMessage = "Last name updated successfully. New last name: ";
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
                    	break;
                    default:
                        System.out.println("Invalid option.");
                        return;
                }
                System.out.println(updatePrompt);
                // Allow for spaces in new input
                in.nextLine();
                String newValue = in.nextLine();

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
		
		System.out.println("* ------------------- *");
    	System.out.println("|   Delete Customer   |");
    	System.out.println("* ------------------- *");
		
		System.out.println("Are you sure you want to delete customer: " + id + "? (Y/N)");
		String answer = in.next();
		
		Connection connection = null;
		String query = "DELETE FROM customerdetails WHERE custID = ?";
		
		try {
			connection = DatabaseConnector.getConnection();
			if (answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("YES")){
				PreparedStatement stmt = connection.prepareStatement(query);
				stmt.setInt(1, id); // Set the value of the userID parameter
				int rowsDeleted = stmt.executeUpdate();
				// stmt.executeUpdate();  //Execute the delete query
	            
				if (rowsDeleted > 0) {
					System.out.println("Customer " + id + " has been successfully deleted.");
				}
			} else {
				System.out.println("Deletion cancelled.");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new NataliaException("Customer " + id + " doesn't exist.");
		} finally {
			connection.close();
		}
	}
	
	// Docket is generated based on date? -- SHOULD BE IN DOCKET CLASS ???
//	public void generateDocketReport() throws NataliaException {
//		Connection connection = null;
//	    PreparedStatement preparedStatement = null;
//	    ResultSet resultSet = null;
//	  
//	}
	
	// Invoice is generated based on customer ID - SHOULD BE IN INVOICES ???
//	public void generateInvoice(int id) throws NataliaException {
//		Connection connection = null;
//	    PreparedStatement preparedStatement = null;
//	    ResultSet resultSet = null;
//	    
//	    Invoice invoice = new Invoice();
//	    
//	    try {
//	        connection = DatabaseConnector.getConnection();
//	        String query = "SELECT * FROM invoice WHERE custID = ?";
//	        preparedStatement = connection.prepareStatement(query);
//	        preparedStatement.setInt(1, id);
//	        resultSet = preparedStatement.executeQuery();
//	        
//	        if (resultSet.next()) {
//	        	invoice.setInvoiceID(id);
//	        	invoice.setcustID(resultSet.getString("custID"));
//	        	invoice.setPublicationID(resultSet.getString("publicationID"));
//	        	invoice.setOrderID(resultSet.getString("orderID"));
//	        	invoice.setTotalPrice(resultSet.getString("totalPrice"));
//	        	invoice.setQuantityDelivered(resultSet.getString("totalQuantityDelivered"));
//			} else {
//				throw new NataliaException("Customer with " + id + " NOT found.");
//			} 
//	    } catch (SQLException e) {
//	        throw new NataliaException("Database error: " + e.getMessage());
//	    } finally {
//	        try {
//	            if (resultSet != null) resultSet.close();
//	            if (preparedStatement != null) preparedStatement.close();
//	            if (connection != null) connection.close();
//	        } catch (SQLException e) {
//	            throw new NataliaException("Error while closing database resources: " + e.getMessage());
//	        }
//	    }
//	}
	
	
	public boolean doesCustomerExist(int id) throws SQLException, NataliaException {
		Connection connection = null;
	    PreparedStatement preparedStatement = null;
	    ResultSet resultSet = null;
		
	    try {
	        connection = DatabaseConnector.getConnection();
	        String query = "SELECT * FROM customerdetails WHERE custID = ?";
	        preparedStatement = connection.prepareStatement(query);
	        preparedStatement.setInt(1, id);
	        resultSet = preparedStatement.executeQuery();
	        // If a record exists, resultSet.next() will be true.
	        return resultSet.next(); 
	    } catch (SQLException e) {
	        throw new NataliaException("Database error: " + e.getMessage());
	    } finally {
	        try {
	            if (resultSet != null) resultSet.close();
	            if (preparedStatement != null) preparedStatement.close();
	            if (connection != null) connection.close();
	        } catch (SQLException e) {
	            throw new NataliaException("Error while closing database resources: " + e.getMessage());
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
		if (name.length() <= 15 && name.length() > 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isValidAddress(String address) {
		if (address.length() <= 20 && address.length() > 1) {
			return true;
		}
		return false;
	}
	
	public boolean isValidPhoneNo(String phoneNo) {
		String pattern = "\\d{3}-\\d{3}-\\d{4}";
		return phoneNo.matches(pattern);
	}
	
	public boolean isValidUpdate(String updateColumn, String newValue) {
		// Validation logic for the updateColumn and newValue
	    if (updateColumn.equals("firstName")) {
	        return isValidName(newValue);
	    } else if (updateColumn.equals("lastName")) {
	        return isValidName(newValue);
	    } else if (updateColumn.equals("address")) {
	        return isValidAddress(newValue);
	    } else if (updateColumn.equals("phoneNo")) {
	    	return isValidPhoneNo(newValue);
	    }
	    return false;
	}
	
	private boolean isValidAreaCode(int areaCode) {
		if (areaCode <= 12 & areaCode >= 1) {
			return true;
		}
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
