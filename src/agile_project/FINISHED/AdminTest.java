package agile_project.FINISHED;

import java.sql.SQLException;

import agile_project.NataliaException;
import junit.framework.TestCase;


/**
 * !!! !!! !!! !!! !!! !!! NOTE !!! !!! !!! !!! !!! !!! *
 *                                                      *
 * BEFORE RUNNING THE TESTS, CHECK USERDETAILS DATABASE *
 * AND ENTER VALID USER ID IN THE DELETE TEST METHOD    *
 *                                                      *
 * !!! !!! !!! !!! !!! !!! NOTE !!! !!! !!! !!! !!! !!! *
 */


public class AdminTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify that Admin object can be created with valid attributes
	 * Input: Admin admin = new User("admin", "Admin1", "admin");
	 * Output: Admin object is created without any exceptions.
	 */
	public void testAdmin001() throws NataliaException{
		try {
			Admin admin = new Admin("admin", "Admin1", "admin");
			assertEquals("admin", admin.getUsername());
			assertEquals("Admin1", admin.getPassword());
			assertEquals("admin", admin.getRole());
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/** 
	 * Test #2
	 * Objective: Verify exception thrown when invalid attributes
	 * Input: Admin admin = new Admin("", "Admin1", "admin");
	 * Output: Exception thrown "Invalid user attributes."
	 */
	public void testAdmin002() throws NataliaException{
		try {
			Admin admin = new Admin("", "Admin1", "admin");
			fail("Exception expected. Invalid username.");
		} catch (NataliaException e) {
			assertEquals("Invalid user attributes.", e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify admin can create new admin
	 * Input: createUser("test", "Test11", "admin")
	 * Output: "User: ID USERNAME was successfully created!" 
	 * @throws SQLException 
	 */
	public void testCreateUser001() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
	        admin.createUser("test", "Test11", "admin");
	        admin.setID(1);
	        String userCreated = "User: " + admin.getID() + " " + admin.getUsername() + " was successfully created!";
	        String expectedUser = "User: 1 test was successfully created!";
	        assertEquals(expectedUser, userCreated);
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify admin can create new driver
	 * Input: createUser("test", "Test11", "driver")
	 * Output: "User: ID USERNAME was successfully created!" 
	 * @throws SQLException 
	 */
	public void testCreateUser002() throws NataliaException, SQLException{
		try {
			Admin driver = new Admin();
			driver.createUser("test", "Test11", "driver");
			driver.setID(1);
	        String userCreated = "User: " + driver.getID() + " " + driver.getUsername() + " was successfully created!";
	        String expectedUser = "User: 1 test was successfully created!";
	        assertEquals(expectedUser, userCreated);
		}catch (NataliaException e) {
			fail("Exception NOT expected. Invalid role");
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify admin can create new newsagent
	 * Input: createUser("newsagent", "Newsagent1", "newsagent")
	 * Output: "User: ID USERNAME was successfully created!" 
	 * @throws SQLException 
	 */
	public void testCreateUser003() throws NataliaException, SQLException{
		try {
			Admin newsagent = new Admin();
			newsagent.createUser("newsagent", "Newsagent1", "newsagent");
			newsagent.setID(1);
	        String userCreated = "User: " + newsagent.getID() + " " + newsagent.getUsername() + " was successfully created!";
	        String expectedUser = "User: 1 newsagent was successfully created!";
	        assertEquals(expectedUser, userCreated);
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #4
	 * Objective: Verify exception thrown when invalid role
	 * Input: createUser("newsagent", "Test11", "owner")
	 * Output: Invalid role. Available roles: admin/newsagent/driver.
	 * @throws SQLException 
	 */
	public void testCreateUser004() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			admin.createUser("newsagent", "Test11", "owner");
			fail("Exception expected. Invalid role");
		}catch (NataliaException e) {
			assertEquals("Invalid role. Available roles: admin/newsagent/driver.",  e.getMessage());
		}
	}
	
	/**
	 * Test #5
	 * Objective: Verify exception thrown when invalid password
	 * Input: createUser("newsagent", "test", "newsagent")
	 * Output: Invalid password. Password must be between 6-10 characters, include at least one uppercase letter and one digit.
	 * @throws SQLException 
	 */
	public void testCreateUser005() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			admin.createUser("newsagent", "test", "newsagent");
			fail("Exception expected. Invalid password");
		}catch (NataliaException e) {
			assertEquals("Invalid password. Password must be between 6-10 characters, include at least one uppercase letter and one digit.",  e.getMessage());
		}
	}
	
	/**
	 * Test #6
	 * Objective: Verify exception thrown when invalid username
	 * Input: createUser("n", "Test11", "newsagent")
	 * Output: Invalid username. Username must be between 1-10 characters.
	 */
	public void testCreateUser006() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			admin.createUser("", "Test11", "newsagent");
			fail("Exception expected. Invalid username");
		}catch (NataliaException e) {
			assertEquals("Invalid username. Username must be between 1-10 characters.",  e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify user can be successfully deleted
	 * Input: id = 35
	 * Output: User 35 has been successfully deleted.
	 */
	public void testDeleteUser001() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			int userID = 51;
			admin.deleteUser(userID);
			String expectedMessage = "User 51 has been successfully deleted.";
			String actualMessage = "User " + userID + " has been successfully deleted.";
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify admin can update username
	 * Input: id = 14
	 * Output: Username updated successfully. New username: + username
	 */
	public void testUpdateUser001() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			
			int userID = 14;
			User user14 = admin.getUser(userID);
	        //String oldUsername = user14.getUsername();

			admin.updateUser(userID);
			String newUsername = "Driver14";
			//System.out.println("OLD Username: " + oldUsername);
			String expectedMessage = "Username updated successfully. New username: Driver14";
			String actualMessage = "Username updated successfully. New username: " + newUsername;
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify admin can update password
	 * Input: id = 36
	 * Output: Password updated successfully. New password: + password
	 */
	public void testUpdateUser002() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			
			int userID = 14;
			User user14 = admin.getUser(userID);
			//String oldPassword = user36.getPassword();
			
			admin.updateUser(userID);
			String newPassword = "Driver14";
			//System.out.println("OLD Password: " + oldPassword);
			String expectedMessage = "Password updated successfully. New password: Driver14";
			String actualMessage = "Password updated successfully. New password: " + newPassword;
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify admin can update role
	 * Input: id = 14
	 * Output: Role updated successfully. New role: + role
	 */
	public void testUpdateUser003() throws NataliaException, SQLException{
		try {
			Admin admin = new Admin();
			int userID = 14;
			
			User user36 = admin.getUser(userID);
	        //String oldRole = user36.getRole();

			admin.updateUser(userID);
			String newRole = "admin";
			//System.out.println("OLD Role: " + oldRole);
			String expectedMessage = "Role updated successfully. New role: admin";
			String actualMessage = "Role updated successfully. New role: " + newRole;
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid username < 10 and > 1
	 * Input: username = "admin"
	 * Output: true
	 */
	public void testIsValidUsername001() throws NataliaException {
		try {
			Admin admin = new Admin();
			String username = "admin";
			assertEquals(true, admin.isValidUsername(username));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid name < 1 is false
	 * Input: username = ""
	 * Output: false
	 */
	public void testIsValidUsername002() throws NataliaException{
		Admin admin = new Admin();
		String username = "";
		try {	
			assertEquals(false, admin.isValidUsername(username));
		} catch (Exception e) {
			fail("Exception NOT expected");
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid name > 10 is false
	 * Input: username = "AdminAdminAdmin"
	 * Output: false
	 */
	public void testIsValidUsername003() throws NataliaException{
		Admin admin = new Admin();
		String username = "AdminAdminAdmin";
		try {
			assertEquals(false, admin.isValidUsername(username));		
		} catch (Exception e) {
			fail("Exception NOT expected.");
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid password < 10 and > 6 and has at least one digit & upper case
	 * Input: password = "Admin1"
	 * Output: true
	 */
	public void testIsValidPassword001() throws NataliaException{
		Admin admin = new Admin();
		String password = "Admin1";
		try {
			assertEquals(true, admin.isValidPassword(password));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid password < 6 is false
	 * Input: password = "Ad1"
	 * Output: false
	 */
	public void testIsValidPassword002() throws NataliaException{
		Admin admin = new Admin();
		String password = "Ad1";
		try {
			assertEquals(false, admin.isValidPassword(password));
		} catch (Exception e) {
			fail("Exception NOT expected.");
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid password > 10
	 * Input: password = "AdminAdminAdmin1"
	 * Output: false
	 */
	public void testIsValidPassword003() throws NataliaException{
		Admin admin = new Admin();
		String password = "AdminAdminAdmin1";
		try {
			assertEquals(false, admin.isValidPassword(password));
		} catch (Exception e) {
			fail("Exception NOT expected.");
		}
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid password when no digit
	 * Input: password = "AdminA"
	 * Output: false
	 */
	public void testIsValidPassword004() throws NataliaException{
		Admin admin = new Admin();
		String password = "AdminA";
		try {
			assertEquals(false, admin.isValidPassword(password));
		} catch (Exception e) {
			fail("Exception NOT expected.");
		}
	}
	
	/**
	 * Test #5
	 * Objective: Verify invalid password when no upper case
	 * Input: password = "admin1"
	 * Output: false
	 */
	public void testIsValidPassword005() throws NataliaException{
		Admin admin = new Admin();
		String password = "admin1";
		try {
			assertEquals(false, admin.isValidPassword(password));
		} catch (Exception e) {
			fail("Exception NOT expected.");
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid role = driver
	 * Input: role = "driver"
	 * Output: true
	 */
	public void testIsValidRole001() throws NataliaException{
		Admin admin = new Admin();
		String role = "driver";
		try {
			assertEquals(true, admin.isValidRole(role));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify valid role = newsagent
	 * Input: role = "newsagent"
	 * Output: true
	 */
	public void testIsValidRole002() throws NataliaException{
		Admin admin = new Admin();
		String role = "newsagent";
		try {
			assertEquals(true, admin.isValidRole(role));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify valid role = admin
	 * Input: role = "admin"
	 * Output: true
	 */
	public void testIsValidRole003() throws NataliaException{
		Admin admin = new Admin();
		String role = "admin";
		try {
			assertEquals(true, admin.isValidRole(role));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid role is false
	 * Input: role = "owner"
	 * Output: false
	 */
	public void testIsValidRole004() throws NataliaException{
		Admin admin = new Admin();
		String role = "owner";
		try {
			assertEquals(false, admin.isValidRole(role));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
}
