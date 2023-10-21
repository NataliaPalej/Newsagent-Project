import junit.framework.TestCase;

public class CustomerTest extends TestCase {
	
	//Test #: 1
	//Test Objective: To create a Customer Account
	//Inputs: custName = "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123"
	//Expected Output: Customer Object created with id = 0, "Jack Daniels", custAddr = "Athlone", custPhone = "087-123123123"
	
	public void testCustomer001() {
		
		//Create the Customer Object
		
		
		try {
			
			//Call method under test
			Customer custObj = new Customer("Jack Daniels", "Athlone", "087-123123123");
			
			// Use getters to check for object creation
			assertEquals(0, custObj.getId());
			assertEquals("Jack Daniels", custObj.getName());
			assertEquals("Athlone", custObj.getAddress());
			assertEquals("087-123123123", custObj.getPhoneNumber());
		}
		catch (CustomerExceptionHandler e) {
			fail("Exception not expected");
		}
		
	}
	
	//Test #: 2
	//Test Objective: To catch an invalid customer name
	//Inputs: custName = "J"
	//Expected Output: Exception Message: "Customer Name does not meet minimum length requirements"

	public void testValidateName001() {
			
		try {
				
			//Call method under test
			Customer.validateName("J");
			fail("Exception expected");
		}
		catch (CustomerExceptionHandler e) {
			assertEquals("Customer Name does not meet minimum length requirements", e.getMessage());	
		}
	}

}

		

