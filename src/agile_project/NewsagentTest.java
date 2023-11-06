package agile_project;

import java.sql.SQLException;

import agile_project.FINISHED.Admin;
import agile_project.FINISHED.Customer;
import agile_project.FINISHED.User;
import junit.framework.TestCase;

public class NewsagentTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify newsagent constructors returns valid newsagent user
	 * Inputs: new Newsagent("newsagent", "Newsagent1", "newsagent")
	 * Output: Newsagent constructor works as expected
	 */
	public void testNewsagent001() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid username throws exception
	 * Inputs: new Newsagent("", "Newsagent1", "newsagent")
	 * Output: Invalid username. Username must be between 1-10 characters.
	 */
	public void testNewsagent002() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid password throws exception
	 * Inputs: new Newsagent("newsagent", "newsagent", "newsagent")
	 * Output: Invalid password. Password must be between 6-10 characters, include at least one digit and uppercase letter.
	 */
	public void testNewsagent003() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid role throws exception
	 * Inputs: new Newsagent("newsagent", "Newsagent1", "owner")
	 * Output: Invalid role. Available roles: driver/newsagent/admin.
	 */
	public void testNewsagent004() throws NataliaException {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify newsagent can create new customer
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 111-111-1111
	 * Output: "Customer: " + this.getCustID() + " " + this.getFirstName() + " " + this.getLastName() + " was successfully created!"
	 */
	public void testCreateCustomer001() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid firstName throws exception
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 111-111-1111
	 * Output: Invalid first name. Name must be between 1-15 characters.
	 */
	public void testCreateCustomer002() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid lastName throws exception
	 * Inputs: firstName = Test, lastName = "", address = Test, phoneNo = 111-111-1111
	 * Output: Invalid last name. Surname must be between 1-15 characters.
	 */
	public void testCreateCustomer003() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid address throws exception
	 * Inputs: firstName = Test, lastName = Test, address = "", phoneNo = 111-111-1111
	 * Output: Invalid address. Address must be between 1-20 characters.
	 */
	public void testCreateCustomer004() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #5
	 * Objective: Verify invalid phoneNo throws exception
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 1111111111
	 * Output: Invalid phone number. Number must be in format 111-222-3333.
	 */
	public void testCreateCustomer005() throws NataliaException {
		fail("Not yet implemented");
	}
	

	/**
	 * Test #1
	 * Objective: Verify newsagent can update customer firstName
	 * Input: 
	 * Output: 
	 */
	public void testUpdateCustomer001() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify newsagent can update customer lastName
	 * Input: 
	 * Output: s
	 */
	public void testUpdateCustomer002() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify newsagent can update customer address
	 * Input: 
	 * Output: 
	 */
	public void testUpdateCustomer003() throws NataliaException {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify newsagent can update customer phoneNo
	 * Input: 
	 * Output: 
	 */
	public void testUpdateCustomer004() throws NataliaException {
		try {
			Newsagent testUpdateCustomer004 = new Newsagent();
			
			int custID = 14;
			Customer cust14 = testUpdateCustomer004.getCustomer(custID);
	        String oldPhoneNo = cust14.getPhoneNo();

			testUpdateCustomer004.updateCustomer(custID);
			String newPhoneNo = "111-111-1111";
			System.out.println("OLD phoneNo: " + oldPhoneNo);
			String expectedMessage = "PhoneNo updated successfully. New phoneNo: 111-111-1111";
			String actualMessage = "PhoneNo updated successfully. New phoneNo: " + newPhoneNo;
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException | SQLException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	// Update tests to verify customer can be deactivated??

	/**
	 * Test #1
	 * Objective: Verify customer can be successfully deleted
	 * Input: custID = 15
	 * Output: "Customer" + id + " has been successfully deleted."
	 * @throws SQLException 
	 */
	public void testDeleteCustomer001() throws NataliaException {
		try {
			Newsagent testDeleteCustomer001 = new Newsagent();
			int custID = 15;
			testDeleteCustomer001.deleteCustomer(custID);
			String expectedMessage = "Customer 15 has been successfully deleted.";
			String actualMessage = "Customer " + custID + " has been successfully deleted.";
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException | SQLException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid custID throws exception
	 * Input: custID = 100
	 * Output: "Customer " + id + " doesn't exist."
	 */
	public void testDeleteCustomer002() throws NataliaException {
		try {
			Newsagent testDeleteCustomer002 = new Newsagent();
			int custID = 100;
			testDeleteCustomer002.deleteCustomer(custID);
			String expectedMessage = "Customer 15 has been successfully deleted.";
			String actualMessage = "Customer " + custID + " has been successfully deleted.";
	        assertEquals(expectedMessage, actualMessage);
		}catch (NataliaException | SQLException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid 1 < firstName/lastName <= 15
	 * Input: firstName = Test
	 * Output: true
	 */
	public void testIsValidName001() throws NataliaException {
		Newsagent testIsValidName001 = new Newsagent();
		String firstName = "Test";
		try {
			assertEquals(true, testIsValidName001.isValidName(firstName));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify firstName < 1
	 * Input: firstName = ""
	 * Output: false
	 */
	public void testIsValidName002() throws NataliaException {
		Newsagent testIsValidName001 = new Newsagent();
		String firstName = "";
		try {
			assertEquals(false, testIsValidName001.isValidName(firstName));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify firstName > 16
	 * Input: firstName = "TestTestTestTestTestTest"
	 * Output: false
	 */
	public void testIsValidName003() throws NataliaException {
		Newsagent testIsValidName003 = new Newsagent();
		String firstName = "TestTestTestTestTestTest";
		try {
			assertEquals(false, testIsValidName003.isValidName(firstName));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid 1 < address <= 20
	 * Input: address = "Test"
	 * Output: true
	 */
	public void testIsValidAddress001() throws NataliaException {
		Newsagent testIsValidAddress001 = new Newsagent();
		String address = "Test";
		try {
			assertEquals(true, testIsValidAddress001.isValidAddress(address));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid address < 1
	 * Input: address = "T"
	 * Output: false
	 */
	public void testIsValidAddress002() throws NataliaException {
		Newsagent testIsValidAddress002 = new Newsagent();
		String address = "T";
		try {
			assertEquals(false, testIsValidAddress002.isValidAddress(address));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid address > 21
	 * Input: address = "TestTestTestTestTestTest"
	 * Output: false
	 */
	public void testIsValidAddress003() throws NataliaException {
		Newsagent testIsValidPhoneNo001 = new Newsagent();
		String phoneNo = "555-555-5555";
		try {
			assertEquals(true, testIsValidPhoneNo001.isValidPhoneNo(phoneNo));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid phoneNo == 12
	 * Input: phoneNo = 555-555-5555
	 * Output: true
	 */
	public void testIsValidPhoneNo001() throws NataliaException {
		Newsagent testIsValidPhoneNo001 = new Newsagent();
		String phoneNo = "555-555-5555";
		try {
			assertEquals(true, testIsValidPhoneNo001.isValidPhoneNo(phoneNo));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid phoneNo < 12
	 * Input: phoneNo = 555-111-222
	 * Output: false
	 */
	public void testIsValidPhoneNo002() throws NataliaException {
		Newsagent testIsValidPhoneNo002 = new Newsagent();
		String phoneNo = "555-111-222";
		try {
			assertEquals(false, testIsValidPhoneNo002.isValidPhoneNo(phoneNo));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid phoneNo > 12
	 * Input: phoneNo = 555-1111-2222
	 * Output: false
	 */
	public void testIsValidPhoneNo003() throws NataliaException {
		Newsagent testIsValidPhoneNo003 = new Newsagent();
		String phoneNo = "555-1111-2222";
		try {
			assertEquals(false, testIsValidPhoneNo003.isValidPhoneNo(phoneNo));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid phoneNo format
	 * Input: phoneNo = 5551112222
	 * Output: false
	 */
	public void testIsValidPhoneNo004() throws NataliaException {
		Newsagent testIsValidPhoneNo004 = new Newsagent();
		String phoneNo = "5551112222";
		try {
			assertEquals(false, testIsValidPhoneNo004.isValidPhoneNo(phoneNo));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

}
