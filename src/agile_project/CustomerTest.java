package agile_project;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	/**
	 * Test #1
	 * Objective: Verify that a Customer object can be created with valid attributes
	 * Input: Customer customer = new Customer("Test", "Test", "Test", "Test", 1);
	 * Output: Customer object is created without any exceptions.
	 */
	public void testCustomer001() throws NataliaException {
		try {
			Customer customer = new Customer("Test", "Test", "Test", "Test", 1);	
	        assertEquals("Test", customer.getFirstName());
	        assertEquals("Test", customer.getLastName());
	        assertEquals("Test", customer.getAddress());
	        assertEquals("Test", customer.getPhoneNo());
	        assertEquals(1, customer.getAreaCode());
	    } catch (NataliaException e) {
	        fail("Exception NOT expected.\n" + e.getMessage());
	    }
	}
	
	/**
     * Test #2
     * Objective: Verify NataliaException is thrown when creating a Customer with invalid attributes
     * Input: Customer c1 = new Customer("", "Invalid", "Address", 123, 1);
     * Output: NataliaException throws "Invalid Customer attributes" message.
     */
	@SuppressWarnings("unused")
	public void testCustomer002() throws NataliaException {
		try {
	        Customer customer = new Customer("", "Invalid", "Address", "123", 1);
	        fail("Exception expected. Invalid attributes.");
	    } catch (NataliaException e) {
	        assertEquals("Invalid customer attributes", e.getMessage());
	    }
	}

	/**
	 * Test #1
	 * Objective: Verify customer details can be successfully retrieved 
	 * Input: custID = 21
	 * Output: Customer details have been retrieved
	 */
	public void testGetCustomerDetails001() throws NataliaException {
		try {
			int custID = 1;
			Customer customer = new Customer("Natalia", "Palej", "123 Main St", "555-123-4567", 1);
			Customer retrievedCustomer = customer.getCustomer(custID);
			assertEquals(custID, retrievedCustomer.getCustID());
		    assertEquals("Natalia", retrievedCustomer.getFirstName());
		    assertEquals("Palej", retrievedCustomer.getLastName());
		    assertEquals("123 Main St", retrievedCustomer.getAddress());
		    assertEquals("555-123-4567", retrievedCustomer.getPhoneNo());
		    assertEquals(1, retrievedCustomer.getAreaCode());
		} catch (NataliaException e) {
            fail("Exception NOT expected.\n" + e.getMessage());
        }
	}
	
	/**
	 * Test #1
	 * Objective: Verify exception raised with invalid ID
	 * Input: custID = 0
	 * Output: NataliaException throws "Customer with ID doesn't exist"
	 */
	@SuppressWarnings("unused")
	public void testGetCustomerDetails002() throws NataliaException {
		int custID = 0;
		try {
			Customer customer = new Customer("Natalia", "Palej", "123 Main St", "555-123-4567", 1);
			Customer getCustomerDetails = customer.getCustomer(custID);
			fail("Exception expected. Invalid customer ID.");
		} catch (NataliaException e) {
			assertEquals("Customer with " + custID + " NOT found.", e.getMessage());
        }
	}
	
	/**
	 * Test #1
	 * Objective: Verify all customers details have been successfully retrieved 
	 * Input: getAllCustomersDetails();
	 * Output: Customer details successfully retrieved 
	 */
	public void testGetAllCustomersDetails001() throws NataliaException {
		try {
			Customer customer = new Customer();
            String allCustomersDetails = customer.getAllCustomers();
            String expectedCustomerDetails = 
            		"Customer ID: 1\tFirst Name: Natalia\tLast Name: Palej\tAddress: 123 Main St\tPhone Number: 555-123-4567\tAdea Code: 1" + "\n" +
                    "Customer ID: 2\tFirst Name: Ivan\tLast Name: Lapickij\tAddress: 456 Elm St\tPhone Number: 555-234-5678\tAdea Code: 2" + "\n" +
                    "Customer ID: 3\tFirst Name: Ronan\tLast Name: Harris\tAddress: 789 Oak St\tPhone Number: 555-345-6789\tAdea Code: 3" + "\n" +
                    "Customer ID: 4\tFirst Name: Lilly\tLast Name: Lola\tAddress: 101 Pine St\tPhone Number: 555-456-7890\tAdea Code: 4" + "\n" +
                    "Customer ID: 5\tFirst Name: Kevin\tLast Name: Murphy\tAddress: 202 Maple St\tPhone Number: 555-567-8901\tAdea Code: 5" + "\n" +
                    "Customer ID: 6\tFirst Name: Doja\tLast Name: Cot\tAddress: 303 Cedar St\tPhone Number: 555-678-9012\tAdea Code: 6" + "\n" +
                    "Customer ID: 7\tFirst Name: Miley\tLast Name: Cyrus\tAddress: 404 Birch St\tPhone Number: 555-789-0123\tAdea Code: 7" + "\n" +
                    "Customer ID: 8\tFirst Name: Selena\tLast Name: Gomez\tAddress: 505 Redwood St\tPhone Number: 555-890-1234\tAdea Code: 8" + "\n" +
                    "Customer ID: 9\tFirst Name: Cardi\tLast Name: B\tAddress: 606 Sequoia St\tPhone Number: 555-901-2345\tAdea Code: 9" + "\n" +
                    "Customer ID: 10\tFirst Name: Brad\tLast Name: Pitt\tAddress: 707 Spruce St\tPhone Number: 555-012-3456\tAdea Code: 10" + "\n" +
                    "Customer ID: 11\tFirst Name: Bond\tLast Name: 007\tAddress: 008 Willow St\tPhone Number: 555-001-2345\tAdea Code: 11" + "\n" ;

            // Assert that the actual customer details match the expected customer details
            assertEquals(expectedCustomerDetails, allCustomersDetails);
        } catch (NataliaException e) {
            fail("Exception NOT expected.\n" + e.getMessage());
        }
	}
	
	public void testGetAllCustomersDetails002() throws NataliaException {
		/**
		 * Test #2
		 * Objective: Verify errors handled when list empty or not found
		 * Input: getAllCustomerDetails();
		 * Output: NataliaException throws "Customer database empty or not found."
		 */
		try {
			Customer customer = new Customer();
			customer.getAllCustomers();
			if (customer.getAllCustomers().isEmpty()) {
				fail("Exception expected.");
			}
		} catch (NataliaException e) {
			assertEquals("Customer database empty or not found.", e.getMessage());
		}
	}
}