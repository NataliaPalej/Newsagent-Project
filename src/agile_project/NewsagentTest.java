package agile_project;

import junit.framework.TestCase;

public class NewsagentTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify newsagent constructors returns valid newsagent user
	 * Inputs: new Newsagent("newsagent", "Newsagent1", "newsagent")
	 * Output: Newsagent constructor works as expected
	 */
	public void testNewsagent001() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify newsagent can create new customer
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 111-111-1111
	 * Output: "Customer: " + this.getCustID() + " " + this.getFirstName() + " " + this.getLastName() + " was successfully created!"
	 */
	public void testCreateCustomer001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid firstName throws exception
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 111-111-1111
	 * Output: Invalid first name. Name must be between 1-15 characters.
	 */
	public void testCreateCustomer002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid lastName throws exception
	 * Inputs: firstName = Test, lastName = "", address = Test, phoneNo = 111-111-1111
	 * Output: Invalid last name. Surname must be between 1-15 characters.
	 */
	public void testCreateCustomer003() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid address throws exception
	 * Inputs: firstName = Test, lastName = Test, address = "", phoneNo = 111-111-1111
	 * Output: Invalid address. Address must be between 1-20 characters.
	 */
	public void testCreateCustomer004() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #5
	 * Objective: Verify invalid phoneNo throws exception
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 1111111111
	 * Output: Invalid phone number. Number must be in format 111-222-3333.
	 */
	public void testCreateCustomer005() {
		fail("Not yet implemented");
	}
	

	/**
	 * Test #1
	 * Objective: Verify newsagent can update customer firstName
	 * Input: 
	 * Output: 
	 */
	public void testUpdateCustomer001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify newsagent can update customer lastName
	 * Input: 
	 * Output: s
	 */
	public void testUpdateCustomer002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify newsagent can update customer address
	 * Input: 
	 * Output: 
	 */
	public void testUpdateCustomer003() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify newsagent can update customer phoneNo
	 * Input: 
	 * Output: 
	 */
	public void testUpdateCustomer004() {
		fail("Not yet implemented");
	}
	
	// Update tests to verify customer can be deactivated??

	/**
	 * Test #1
	 * Objective: Verify customer can be successfully deleted
	 * Input: custID = 15
	 * Output: "Customer" + id + " has been successfully deleted."
	 */
	public void testDeleteCustomer001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid custID throws exception
	 * Input: custID = 100
	 * Output: "Customer " + id + " doesn't exist."
	 */
	public void testDeleteCustomer002() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid 1 < firstName/lastName <= 15
	 * Input: firstName = Test
	 * Output: true
	 */
	public void testIsValidName001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify firstName < 1
	 * Input: firstName = ""
	 * Output: false
	 */
	public void testIsValidName002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify firstName > 16
	 * Input: firstName = "TestTestTestTestTestTest"
	 * Output: false
	 */
	public void testIsValidName003() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid 1 < address <= 20
	 * Input: address = "Test"
	 * Output: true
	 */
	public void testIsValidAddress001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid address < 1
	 * Input: address = "T"
	 * Output: false
	 */
	public void testIsValidAddress002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid address > 21
	 * Input: address = "TestTestTestTestTestTest"
	 * Output: false
	 */
	public void testIsValidAddress003() {
		fail("Not yet implemented");
	}

	/**
	 * Test #1
	 * Objective: Verify valid phoneNo == 12
	 * Input: phoneNo = 555-555-5555
	 * Output: true
	 */
	public void testIsValidPhoneNo001() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid phoneNo < 12
	 * Input: phoneNo = 555-111-222
	 * Output: false
	 */
	public void testIsValidPhoneNo002() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid phoneNo > 12
	 * Input: phoneNo = 555-1111-2222
	 * Output: false
	 */
	public void testIsValidPhoneNo003() {
		fail("Not yet implemented");
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid phoneNo format
	 * Input: phoneNo = 5551112222
	 * Output: false
	 */
	public void testIsValidPhoneNo004() {
		fail("Not yet implemented");
	}

}
