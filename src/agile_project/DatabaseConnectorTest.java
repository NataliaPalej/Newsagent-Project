package agile_project;

import java.sql.Connection;

import junit.framework.TestCase;

public class DatabaseConnectorTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify valid connection with the database
	 * Input: getConnection();
	 * Output: Connection is valid and working
	 */
	public void testGetConnection001() throws NataliaException {
		Connection connection = DatabaseConnector.getConnection();
        assertNotNull("Connection is not null", connection);
	}
	
	/**
	 * Test #2
	 * Objective: Verify exception thrown when invalid DB_DRIVER
	 * Input: DB_DRIVER = "invalid_driver"
	 * Output: Exception thrown "Invalid DB DRIVER."
	 */
	public void testGetConnection002() throws NataliaException {
		try {
			DatabaseConnector.setDB_DRIVER("invalid_driver");
	        Connection connection = DatabaseConnector.getConnection();
            fail("Expected exception.");
        } catch (NataliaException e) {
            assertEquals("Invalid DB DRIVER.", e.getMessage());
        }
	}
	
	/**
	 * Test #3
	 * Objective: Verify exception thrown when invalid database JDBC URL
	 * Input: JDBC_URL = "jdbc:mysql://localhost:3306/invalid_entry"
	 * Output: Exception thrown "Invalid JDBC URL."
	 */
	public void testGetConnection003() throws NataliaException {
		try {
			// Set DB DRIVER to its original value
			DatabaseConnector.setDB_DRIVER("com.mysql.cj.jdbc.Driver");
			DatabaseConnector.setJDBC_URL("jdbc:mysql://localhost:3306/invalid_entry");
			Connection connection = DatabaseConnector.getConnection();
            fail("Expected exception.");
        } catch (NataliaException e) {
            assertEquals("Invalid JDBC URL.", e.getMessage());
        }
	}
	
	/**
	 * Test #4
	 * Objective: Verify exception thrown when invalid database username
	 * Input: USERNAME="invalid_username"
	 * Output: Exception thrown "Invalid database USERNAME."
	 */
	public void testGetConnection004() throws NataliaException {
		try {
			// Set JDBC_URL to its original value
			DatabaseConnector.setJDBC_URL("jdbc:mysql://localhost:3306/newsagentdb");
			DatabaseConnector.setUSERNAME("invalid_username");
			Connection connection = DatabaseConnector.getConnection();;
            fail("Expected exception.");
        } catch (NataliaException e) {
            assertEquals("Invalid database USERNAME.", e.getMessage());
        }
	}
}
