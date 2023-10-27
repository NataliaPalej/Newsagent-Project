package agile_project;

import junit.framework.TestCase;

public class AdminTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify that Admin object can be created with valid attributes
	 * Input: Admin admin = new User("admin", "Admin1", "admin");
	 * Output: Admin object is created without any exceptions.
	 */
	public void testAdmin001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify exception thrown when invalid username
	 * Input: Admin admin = new Admin(" ", "Admin1", "admin");
	 * Output: Exception thrown "Invalid user attributes."
	 */
	public void testAdmin002() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify admin can create new admin
	 * Input: createUser("test", "Test11", "admin")
	 * Output: "User: ID USERNAME was successfully created!" 
	 */
	public void testCreateUser001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify admin can create new driver
	 * Input: createUser("test", "Test11", "driver")
	 * Output: "User: ID USERNAME was successfully created!" 
	 */
	public void testCreateUser002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify admin can create new newsagent
	 * Input: createUser("newsagent", "Test11", "newsagent")
	 * Output: "User: ID USERNAME was successfully created!" 
	 */
	public void testCreateUser003() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify exception thrown when invalid role
	 * Input: createUser("newsagent", "Test11", "owner")
	 * Output: Invalid role. Available roles: admin/newsagent/driver.
	 */
	public void testCreateUser004() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #5
	 * Objective: Verify exception thrown when invalid password
	 * Input: createUser("newsagent", "test", "newsagent")
	 * Output: Invalid password. Password must be between 6-10 characters, include at least one uppercase letter and one digit.
	 */
	public void testCreateUser005() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #6
	 * Objective: Verify exception thrown when invalid username
	 * Input: createUser("n", "Test11", "newsagent")
	 * Output: Invalid username. Username must be between 1-10 characters.
	 */
	public void testCreateUser006() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify user can be successfully deleted
	 * Input: id = 13
	 * Output: User 13 has been successfully deleted.
	 */
	public void testDeleteUser001() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify admin can update username
	 * Input: id = 12
	 * Output: Username updated successfully. New username: + username
	 */
	public void testUpdateUser001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify admin can update password
	 * Input: id = 12
	 * Output: Password updated successfully. New password: + password
	 */
	public void testUpdateUser002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify admin can update role
	 * Input: id = 12
	 * Output: Role updated successfully. New role: + role
	 */
	public void testUpdateUser003() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid username < 10 and > 1
	 * Input: username = "admin"
	 * Output: true
	 */
	public void testIsValidUsername001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid name < 1 is false
	 * Input: username = "n"
	 * Output: false
	 */
	public void testIsValidUsername002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid name > 10 is false
	 * Input: username = "AdminAdminAdmin"
	 * Output: false
	 */
	public void testIsValidUsername003() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid password < 10 and > 6 and has at least one digit & upper case
	 * Input: password = "Admin1"
	 * Output: true
	 */
	public void testIsValidPassword001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid password < 6 is false
	 * Input: password = "Ad1"
	 * Output: false
	 */
	public void testIsValidPassword002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid password > 10
	 * Input: password = "AdminAdminAdmin1"
	 * Output: false
	 */
	public void testIsValidPassword003() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid password when no digit
	 * Input: password = "AdminA"
	 * Output: false
	 */
	public void testIsValidPassword004() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #5
	 * Objective: Verify invalid password when no upper case
	 * Input: password = "admin1"
	 * Output: false
	 */
	public void testIsValidPassword005() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid role = driver
	 * Input: role = "driver"
	 * Output: true
	 */
	public void testIsValidRole001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify valid role = newsagent
	 * Input: role = "newsagent"
	 * Output: true
	 */
	public void testIsValidRole002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify valid role = admin
	 * Input: role = "admin"
	 * Output: true
	 */
	public void testIsValidRole003() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid role is false
	 * Input: role = "owner"
	 * Output: false
	 */
	public void testIsValidRole004() {
		fail("Not yet implemented");
	}

}
