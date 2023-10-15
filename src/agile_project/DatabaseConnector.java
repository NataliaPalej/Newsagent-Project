package agile_project;

import java.sql.SQLException;
import java.sql.*;

public class DatabaseConnector {
	
	private static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String JDBC_URL = "jdbc:mysql://localhost:3306/newsagentdb";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "";
	
	public static Connection getConnection() {
		try {
			Class.forName(DB_DRIVER);
			return DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
		}
		catch (SQLException e) {
			throw new RuntimeException("COULDN'T CONNECT TO DATABASE:\n " + e.getMessage(), e);
		}
		catch (ClassNotFoundException e) {
			throw new RuntimeException("Couldn't find the database driver:\n" + e.getMessage(), e);
		}
	}

	public void insertNewCustomer(String firstName, String lastName, String custAddress, String phoneNo) {
	}
	
	public void updateCustomer(String firstName, String lastName, String custAddress, String phoneNo) {
		
	}
	
	public void deleteCustomer(String firstName, String lastName, String custAddress, String phoneNo) {
		
	}
	
	public String readCustomer() {
		return null;	
	}
}