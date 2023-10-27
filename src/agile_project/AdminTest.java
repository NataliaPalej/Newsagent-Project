package agile_project;

import java.sql.SQLException;

import agile_project.FINISHED.User;
import junit.framework.TestCase;

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
	 * Input: Admin admin = new Admin(" ", "Admin1", "admin");
	 * Output: Exception thrown "Invalid user attributes."
	 */
	public void testAdmin002() throws NataliaException{
		try {
			Admin admin = new Admin(" ", "Admin1", "admin");
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
	        System.out.println(admin.getUsername() + "\n" + admin.getID());
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
	 */
	public void testCreateUser002() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify admin can create new newsagent
	 * Input: createUser("newsagent", "Test11", "newsagent")
	 * Output: "User: ID USERNAME was successfully created!" 
	 */
	public void testCreateUser003() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify exception thrown when invalid role
	 * Input: createUser("newsagent", "Test11", "owner")
	 * Output: Invalid role. Available roles: admin/newsagent/driver.
	 */
	public void testCreateUser004() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #5
	 * Objective: Verify exception thrown when invalid password
	 * Input: createUser("newsagent", "test", "newsagent")
	 * Output: Invalid password. Password must be between 6-10 characters, include at least one uppercase letter and one digit.
	 */
	public void testCreateUser005() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #6
	 * Objective: Verify exception thrown when invalid username
	 * Input: createUser("n", "Test11", "newsagent")
	 * Output: Invalid username. Username must be between 1-10 characters.
	 */
	public void testCreateUser006() throws NataliaException{
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify user can be successfully deleted
	 * Input: id = 13
	 * Output: User 13 has been successfully deleted.
	 */
	public void testDeleteUser001() throws NataliaException{
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify admin can update username
	 * Input: id = 12
	 * Output: Username updated successfully. New username: + username
	 */
	public void testUpdateUser001() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify admin can update password
	 * Input: id = 12
	 * Output: Password updated successfully. New password: + password
	 */
	public void testUpdateUser002() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify admin can update role
	 * Input: id = 12
	 * Output: Role updated successfully. New role: + role
	 */
	public void testUpdateUser003() throws NataliaException{
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid username < 10 and > 1
	 * Input: username = "admin"
	 * Output: true
	 */
	public void testIsValidUsername001() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid name < 1 is false
	 * Input: username = "n"
	 * Output: false
	 */
	public void testIsValidUsername002() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid name > 10 is false
	 * Input: username = "AdminAdminAdmin"
	 * Output: false
	 */
	public void testIsValidUsername003() throws NataliaException{
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid password < 10 and > 6 and has at least one digit & upper case
	 * Input: password = "Admin1"
	 * Output: true
	 */
	public void testIsValidPassword001() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid password < 6 is false
	 * Input: password = "Ad1"
	 * Output: false
	 */
	public void testIsValidPassword002() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid password > 10
	 * Input: password = "AdminAdminAdmin1"
	 * Output: false
	 */
	public void testIsValidPassword003() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid password when no digit
	 * Input: password = "AdminA"
	 * Output: false
	 */
	public void testIsValidPassword004() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #5
	 * Objective: Verify invalid password when no upper case
	 * Input: password = "admin1"
	 * Output: false
	 */
	public void testIsValidPassword005() throws NataliaException{
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid role = driver
	 * Input: role = "driver"
	 * Output: true
	 */
	public void testIsValidRole001() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify valid role = newsagent
	 * Input: role = "newsagent"
	 * Output: true
	 */
	public void testIsValidRole002() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify valid role = admin
	 * Input: role = "admin"
	 * Output: true
	 */
	public void testIsValidRole003() throws NataliaException{
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid role is false
	 * Input: role = "owner"
	 * Output: false
	 */
	public void testIsValidRole004() throws NataliaException{
		fail("Not yet implemented");
	}

}
