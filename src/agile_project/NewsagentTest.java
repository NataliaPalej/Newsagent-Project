package agile_project;

import java.sql.SQLException;
import junit.framework.TestCase;

/**
 * !!! !!! !!! !!! !!! !!! NOTE !!! !!! !!! !!! !!! !!! !!! *
 *                                                          *
 * BEFORE RUNNING THE TESTS, CHECK CUSTOMERDETAILS DATABASE *
 * AND ENTER VALID CUST ID IN THE DELETE/UPDATE TEST METHOD *
 *                                                          *
 * !!! !!! !!! !!! !!! !!! NOTE !!! !!! !!! !!! !!! !!! !!! *
 */

public class NewsagentTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify newsagent constructors returns valid newsagent user
	 * Inputs: new Newsagent("newsagent", "Newsagent1", "newsagent")
	 * Output: Newsagent constructor works as expected
	 */
	public void testNewsagent001() throws NataliaException {
		try {
			Newsagent testNewsagent001 = new Newsagent("newsagent", "Newsagent1", "newsagent");
			assertEquals("newsagent", testNewsagent001.getUsername());
			assertEquals("Newsagent1", testNewsagent001.getPassword());
			assertEquals("newsagent", testNewsagent001.getRole());
		}catch (NataliaException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid username throws exception
	 * Inputs: new Newsagent("", "Newsagent1", "newsagent")
	 * Output: Invalid username. Username must be between 1-10 characters.
	 */
	@SuppressWarnings("unused")
	public void testNewsagent002() throws NataliaException {
		try {
			Newsagent testNewsagent002 = new Newsagent("", "Newsagent1", "newsagent");
			fail("Exception expected. Invalid attributes.");
		} catch (NataliaException e) {
			assertEquals("Invalid username. Username must be between 1-10 characters.", e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid password throws exception
	 * Inputs: new Newsagent("newsagent", "newsagent", "newsagent")
	 * Output: Invalid password. Password must be between 6-10 characters, include at least one digit and uppercase letter.
	 */
	@SuppressWarnings("unused")
	public void testNewsagent003() throws NataliaException {
		try {
			Newsagent testNewsagent003 = new Newsagent("newsagent", "newsagent", "newsagent");
			fail("Exception expected. Invalid attributes.");
		} catch (NataliaException e) {
			assertEquals("Invalid password. Password must be between 6-10 characters, include at least one digit and uppercase letter.", e.getMessage());
		}
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid role throws exception
	 * Inputs: new Newsagent("newsagent", "Newsagent1", "owner")
	 * Output: Invalid role. Available roles: driver/newsagent/admin.
	 */
	@SuppressWarnings("unused")
	public void testNewsagent004() throws NataliaException {
		try {
			Newsagent testNewsagent004 = new Newsagent("newsagent", "Newsagent1", "owner");
			fail("Exception expected. Invalid attributes.");
		} catch (NataliaException e) {
			assertEquals("Invalid role. Available roles: driver/newsagent/admin.", e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify newsagent can create new customer
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 111-111-1111
	 * Output: "Customer: " + this.getCustID() + " " + this.getFirstName() + " " + this.getLastName() + " was successfully created!"
	 */
	public void testCreateCustomer001() throws NataliaException {
		try {
			Newsagent customer = new Newsagent();
			customer.createCustomer();
	        // Retrieve custID that was auto generated
	        int autoGeneratedCustID = customer.getCustID();
	        // Check that cust was created 
	        assertTrue(autoGeneratedCustID > 0);
	        assertEquals("Test", customer.getFirstName());
	        assertEquals("Test", customer.getLastName());
	        assertEquals("Test", customer.getAddress());
	        assertEquals("111-111-1111", customer.getPhoneNo());
		}catch (NataliaException | SQLException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid firstName throws exception
	 * Inputs: firstName = "", lastName = Test, address = Test, phoneNo = 111-111-1111
	 * Output: Invalid first name. Name must be between 1-15 characters.
	 */
	public void testCreateCustomer002() throws NataliaException {
		try {
			Newsagent customer = new Newsagent();
			customer.createCustomer();
			fail("Exception expected. Invalid role");
		}catch (NataliaException | SQLException e) {
			assertEquals("Invalid first name. Name must be between 1-15 characters.",  e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify invalid lastName throws exception
	 * Inputs: firstName = Test, lastName = "", address = Test, phoneNo = 111-111-1111
	 * Output: Invalid last name. Surname must be between 1-15 characters.
	 */
	public void testCreateCustomer003() throws NataliaException {
		try {
			Newsagent customer = new Newsagent();
			customer.createCustomer();
			fail("Exception expected. Invalid role");
		}catch (NataliaException | SQLException e) {
			assertEquals("Invalid last name. Surname must be between 1-15 characters.",  e.getMessage());
		}
	}
	
	/**
	 * Test #4
	 * Objective: Verify invalid address throws exception
	 * Inputs: firstName = Test, lastName = Test, address = "", phoneNo = 111-111-1111
	 * Output: Invalid address. Address must be between 1-20 characters.
	 */
	public void testCreateCustomer004() throws NataliaException {
		try {
			Newsagent customer = new Newsagent();
			customer.createCustomer();
			fail("Exception expected. Invalid role");
		}catch (NataliaException | SQLException e) {
			assertEquals("Invalid address. Address must be between 1-20 characters.",  e.getMessage());
		}
	}
	
	/**
	 * Test #5
	 * Objective: Verify invalid phoneNo throws exception
	 * Inputs: firstName = Test, lastName = Test, address = Test, phoneNo = 1111111111
	 * Output: Invalid phone number. Number must be in format 111-222-3333.
	 */
	public void testCreateCustomer005() throws NataliaException {
		try {
			Newsagent customer = new Newsagent();
			customer.createCustomer();
			fail("Exception expected. Invalid role");
		}catch (NataliaException | SQLException e) {
			assertEquals("Invalid phone number. Number must be in format 111-222-3333.",  e.getMessage());
		}
	}
	
	/**
	 * Test #1
	 * Objective: Verify newsagent can update customer firstName
	 * Input: newFirstName = Test
	 * Output: First name updated successfully. New first name: newName
	 */
	public void testUpdateCustomer001() throws NataliaException {
		try {
			Newsagent updateCustomer = new Newsagent();

	        int custID = 15;
	        String newFirstName = "Test";
	        
	        updateCustomer.updateCustomer(custID);
	        // Retrieve the updated customer details from the database
	        Customer updatedFirstName = updateCustomer.getCustomer(custID);
	        String actualFirstName = updatedFirstName.getFirstName();

	        assertEquals(newFirstName, actualFirstName);
	    } catch (NataliaException | SQLException e) {
	        fail("Exception NOT expected.\n" + e.getMessage());
	    }
	}
	
	/**
	 * Test #2
	 * Objective: Verify newsagent can update customer lastName
	 * Input: newLastName = Test
	 * Output: Last name updated successfully. New last name: New Lastname 
	 */
	public void testUpdateCustomer002() throws NataliaException {
		try {
			Newsagent updateCustomer = new Newsagent();

	        int custID = 15;
	        String newLastname = "Test";
	        
	        updateCustomer.updateCustomer(custID);
	        
	        Customer updatedLastName = updateCustomer.getCustomer(custID);
	        String actualLastName = updatedLastName.getLastName();

	        assertEquals(newLastname, actualLastName);
	    } catch (NataliaException | SQLException e) {
	        fail("Exception NOT expected.\n" + e.getMessage());
	    }
	}
	
	/**
	 * Test #3
	 * Objective: Verify newsagent can update customer address
	 * Input: newAddress = Test
	 * Output:  Address updated successfully. New address: Address Updated
	 */
	public void testUpdateCustomer003() throws NataliaException {
		try {
			Newsagent updateCustomer = new Newsagent();

	        int custID = 15;
	        String newAddress = "Test";
	        
	        updateCustomer.updateCustomer(custID);

	        Customer updatedAddress = updateCustomer.getCustomer(custID);
	        String actualAddress = updatedAddress.getAddress();

	        assertEquals(newAddress, actualAddress);
	    } catch (NataliaException | SQLException e) {
	        fail("Exception NOT expected.\n" + e.getMessage());
	    }
	}
	
	/**
	 * Test #4
	 * Objective: Verify newsagent can update customer phoneNo
	 * Input: newPhoneNo = 000-000-0000
	 * Output: Phone number updated successfully. New phone number: 111-111-1111
	 */
	public void testUpdateCustomer004() throws NataliaException {
		try {
			Newsagent updateCustomer = new Newsagent();

	        int custID = 15;
	        String newPhoneNo = "000-000-0000";
	        
	        updateCustomer.updateCustomer(custID);

	        Customer updatedPhoneNo = updateCustomer.getCustomer(custID);
	        String actualPhoneNo = updatedPhoneNo.getPhoneNo();

	        assertEquals(newPhoneNo, actualPhoneNo);
	    } catch (NataliaException | SQLException e) {
	        fail("Exception NOT expected.\n" + e.getMessage());
	    }
	}
	
	// Update tests to verify customer can be deactivated??

	/**
	 * Test #1
	 * Objective: Verify customer can be successfully deleted
	 * Input: custID = 12
	 * Output: "Customer" + id + " has been successfully deleted."
	 * @throws SQLException 
	 */
	public void testDeleteCustomer001() throws NataliaException {
		try {
			Newsagent customer = new Newsagent();
			int custID = 12;
			customer.deleteCustomer(custID);
			assertEquals(false, customer.doesCustomerExist(custID));
		}catch (NataliaException | SQLException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid 1 < firstName <= 15
	 * Input: firstName = Test
	 * Output: true
	 */
	public void testIsValidName001() throws NataliaException {
		Newsagent isValidName = new Newsagent();
		String firstName = "Test";
		try {
			assertEquals(true, isValidName.isValidName(firstName));
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
	public void testIsValidName004() throws NataliaException {
		Newsagent testIsValidName004 = new Newsagent();
		String lastName = "TestTestTestTestTestTest";
		try {
			assertEquals(false, testIsValidName004.isValidName(lastName));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #1
	 * Objective: Verify valid 1 < lastName <= 15
	 * Input: firstName = Test
	 * Output: true
	 */
	public void testIsValidName005() throws NataliaException {
		Newsagent testIsValidName005 = new Newsagent();
		String lastName = "Test";
		try {
			assertEquals(true, testIsValidName005.isValidName(lastName));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify lastName < 1
	 * Input: firstName = ""
	 * Output: false
	 */
	public void testIsValidName006() throws NataliaException {
		Newsagent testIsValidName006 = new Newsagent();
		String lastName = "";
		try {
			assertEquals(false, testIsValidName006.isValidName(lastName));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #3
	 * Objective: Verify lastName > 16
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
		Newsagent testIsValidAddress003 = new Newsagent();
		String address = "TestTestTestTestTestTest";
		try {
			assertEquals(false, testIsValidAddress003.isValidAddress(address));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	/**
	 * Test #1
	 * Objective: Verify valid phoneNo is 12 digits and in format 111-111-1111
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
	
	/**
	 * Test #1
	 * Objective: Verify valid custID returns true
	 * Input: custID = 1
	 * Output: true
	 */
	public void testDoesCustomerExist001() throws NataliaException {
		Newsagent testDoesCustomerExist001 = new Newsagent();
		int id = 1;
		try {
			assertEquals(true, testDoesCustomerExist001.doesCustomerExist(id));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
	
	/**
	 * Test #2
	 * Objective: Verify invalid custID returns false
	 * Input: custID = 100
	 * Output: false
	 */
	public void testDoesCustomerExist002() throws NataliaException {
		Newsagent testDoesCustomerExist002 = new Newsagent();
		int id = 100;
		try {
			assertEquals(false, testDoesCustomerExist002.doesCustomerExist(id));
		} catch (Exception e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}
}
