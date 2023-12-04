package agile_project;
import junit.framework.TestCase;

public class UserTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify that User object can be created with valid attributes
	 * Input: User user = new User("admin", "Admin1", "admin");
	 * Output: User object is created without any exceptions.
	 */
	public void testUser001() throws NataliaException {
		try {
			User user = new User("admin1", "Admin1", "admin");
			assertEquals("admin1", user.getUsername());
			assertEquals("Admin1", user.getPassword());
			assertEquals("admin", user.getRole());
		} catch (NataliaException e) {
			fail("Exception NOT expected.\n " + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify exception thrown when invalid username
	 * Input: User user = new User(" ", "Admin1", "admin");
	 * Output: Exception thrown "Invalid user attributes."
	 */
	@SuppressWarnings("unused")
	public void testUser002() throws NataliaException {
		try {
			User user = new User(" ", "f", "owner");
			fail("Exception expected. Invalid attributes.");
		} catch (NataliaException e) {
			assertEquals("Invalid user attributes.", e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify exception thrown when invalid role
	 * Input: User user = new User("admin", "Admin1", "owner");
	 * Output: Exception thrown "Invalid user attributes."
	 */
	@SuppressWarnings("unused")
	public void testUser003() throws NataliaException {
		try {
			User user = new User("admin", "Admin1", "owner");
			fail("Exception expected. Invalid attributes.");
		} catch (NataliaException e) {
			assertEquals("Invalid role. Available roles: driver/newsagent/admin.", e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify user details are successfully retrieved
	 * Input: userID = 1
	 * Output: User details have been retrieved
	 */
	public void testGetUser() throws NataliaException {
		try {
			int userID = 1;
			User user = new User();
			User retrievedUser = user.getUser(userID);
			assertEquals(userID, retrievedUser.getID());
		    assertEquals("driver1", retrievedUser.getUsername());
		    assertEquals("Driver1", retrievedUser.getPassword());
		    assertEquals("driver", retrievedUser.getRole());
		} catch (NataliaException e) {
            fail("Exception NOT expected.\n" + e.getMessage());
        }
	}

	/**
	 * Test #1
	 * Objective: Verify all users details are successfully retrieved 
	 * Input: getALlUsers();
	 * Output: Users details have been retrieved
	 */
	public void testGetAllUsers() throws NataliaException {
		try {
			User user = new User();
			String getAllUsers = user.getAllUsers();
			String expectedUsers = 
        		"User ID: 1\tUsername: driver1\tPassword: Driver1\tRole: driver\n" +
        		"User ID: 2\tUsername: driver2\tPassword: Driver2\tRole: driver\n" +
        		"User ID: 3\tUsername: driver3\tPassword: Driver3\tRole: driver\n" +
        		"User ID: 4\tUsername: driver4\tPassword: Driver4\tRole: driver\n" +
        		"User ID: 5\tUsername: driver5\tPassword: Driver5\tRole: driver\n" +
        		"User ID: 6\tUsername: driver6\tPassword: Driver6\tRole: driver\n" +
        		"User ID: 7\tUsername: driver7\tPassword: Driver7\tRole: driver\n" +
        		"User ID: 8\tUsername: driver8\tPassword: Driver8\tRole: driver\n" +
        		"User ID: 9\tUsername: driver9\tPassword: Driver9\tRole: driver\n" +
        		"User ID: 10\tUsername: driver10\tPassword: Driver10\tRole: driver\n" +
        		"User ID: 11\tUsername: driver11\tPassword: Driver11\tRole: driver\n" +
        		"User ID: 12\tUsername: driver12\tPassword: Driver12\tRole: driver\n" +
        		"User ID: 13\tUsername: admin1\tPassword: Admin1\tRole: admin\n" +
        		"User ID: 14\tUsername: newsagent1\tPassword: Newsagent1\tRole: newsagent" + "\n";
        assertEquals(expectedUsers, getAllUsers);
        } catch (NataliaException e) {
        	fail("Exception NOT expected.\n" + e.getMessage());
        	}
		}
	}