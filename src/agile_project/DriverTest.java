package agile_project;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import agile_project.NataliaException;

import junit.framework.TestCase;

public class DriverTest extends TestCase {
//###################################################################################
//TESTS
//	choose area of delivery VALID/INVALID++
//	driver ID				VALID/INVALID++
//	READ dockets			VALID/INVALID
//	submit dockets			VALID/INVALID
//	print docket			VALID/INVALID
//	deduct stock on print	VALID/INVALID
//	errors are handled		

    /**
	 * Test 1#
	 * Objective: Verify Valid Area code returns True
	 * Input: int code = 1;
	 * Output: True
	 */
    public void testValidAreaCode() {
    	try {
    	Driver  driver  = new Driver ();
    	int code = 1;
    	assertTrue(driver.isValidAreaCode(code));
    	} catch (Exception e) {
        fail("Exception NOT expected.\\n:");
    	}
    }

    /**
	 * Test 2#
	 * Objective: Verify Invalid Area code returns false
	 * Input: int code = 0;
	 * Output: false
	 */
    public void testInvalidAreaCode() {
    	try {
    	Driver  driver  = new Driver (); // Replace YourTestClass with the actual class containing isValidAreaCode
    	int code = 0;
    	assertFalse(driver.isValidAreaCode(code));
    	} catch (Exception e) {
        fail("Unexpected exception: " + e.getMessage());
    	}
    }
    
//###################################################################################
    /**
	 * Test 1#
	 * Objective: Verify Valid Area code returns True
	 * Input: int code = 1;
	 * Output: True
	 */
    public void testValidDriverID() {
    	try {
    	Driver  driver  = new Driver ();
    	int driverID = 1;
    	assertTrue(driver.isValidDriverID(driverID));
    	} catch (Exception e) {
        fail("Exception NOT expected.\\n:");
    	}
    }

    /**
	 * Test 2#
	 * Objective: Verify Invalid Area code returns false
	 * Input: int code = 0;
	 * Output: false
	 */
    public void testInvalidDriverID() {
    	try {
    		Driver  driver  = new Driver ();
        	int driverID = -1;
        	assertFalse(driver.isValidDriverID(driverID));
        	} catch (Exception e) {
            fail("Exception NOT expected.\\n:");
        	}
        }
 //###################################################################################

    /**
	 * Test 1#
	 * Objective: Verify Submit Docket returns True
	 * Input: int areaCode = 1;
			  int orderID = 123;
	 * Output: True
	 */
    public void testSubmitDocketValid() throws NataliaException, SQLException {
        try {
            Driver driver = new Driver();
            int areaCode = 1;
            int orderID = 123;

            driver.submitDeliveryDocket(areaCode, orderID);
        } catch (NataliaException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    /**
  	 * Test 2#
  	 * Objective: Verify Submit Docket returns false
  	 * Input: int areaCode = -1;
  			  int orderID = 123;
  	 * Output: false
  	 */
      public void testSubmitDocketInvalid() throws NataliaException, SQLException {
          try {
              Driver driver = new Driver();
              int areaCode = -1;
              int orderID = 123;

              driver.submitDeliveryDocket(areaCode, orderID);
          } catch (NataliaException e) {
              fail("Unexpected exception: " + e.getMessage());
          }
      }
//###################################################################################

    // Test case for the updatePublicationStock method with a valid order ID
    public void testUpdatePublicationStockValidOrderID() throws NataliaException{
        try {
            Driver driver = new Driver();
            // Assuming an order ID exists in the database
            int orderID = 1;
            int updatedStock = driver.updatePublicationStock(orderID);
            // Add assertions as needed based on the expected behavior of updatePublicationStock
            assertTrue(updatedStock >= 0); // Assuming stock can't be negative
        } catch (NataliaException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Test case for the updatePublicationStock method with an invalid order ID
    public void testUpdatePublicationStockInvalidOrderID() throws NataliaException{
        try {
            Driver driver = new Driver();
            // Assuming an order ID doesn't exist in the database
            int orderID = -1;
            int updatedStock = driver.updatePublicationStock(orderID);
            assertEquals(-1, updatedStock); // Expecting -1 for an invalid order ID
        } catch (NataliaException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
 // Test case for the getDriverID and setDriverID methods
    public void testGetSetDriverID() throws NataliaException{
        try {
            Driver driver = new Driver();
            driver.setDriverID(42);
            assertEquals(42, driver.getDriverID());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Test case for the getUsername and setUsername methods
    public void testGetSetUsername() throws NataliaException{
        try {
            Driver driver = new Driver();
            driver.setUsername("testUser");
            assertEquals("testUser", driver.getUsername());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Test case for the getPassword and setPassword methods
    public void testGetSetPassword() throws NataliaException{
        try {
            Driver driver = new Driver();
            driver.setPassword("Test123");
            assertEquals("Test123", driver.getPassword());
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }



    // Test case for the updatePublicationStock method with a valid publication ID and stock
    public void testUpdatePublicationStockValidInput() throws NataliaException{
        try {
            Driver driver = new Driver();
            // Assuming a valid publication ID and stock values
            int publicationID = 1;
            int newStock = 10;
            driver.updatePublicationStock(publicationID, newStock);
            // Add assertions as needed based on the expected behavior of updatePublicationStock
            // You may want to query the database or assert the expected result
        } catch (NataliaException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }
    // Test case for the printDocketAndUpdateStock method with a valid area code
    public void testPrintDocketAndUpdateStockValidAreaCode() throws NataliaException{
        try {
            Driver driver = new Driver();
            // Assuming a valid area code exists in the database
            int areaCode = 1;
            driver.printDocketAndUpdateStock(areaCode);
            // Add assertions as needed based on the expected behavior of printDocketAndUpdateStock
        } catch (NataliaException e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }

    // Test case for the printDocketAndUpdateStock method with an invalid area code
    public void testPrintDocketAndUpdateStockInvalidAreaCode() {
        // Redirect System.out to capture the output
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));

        try {
            Driver driver = new Driver();
            // Assuming an invalid area code
            int areaCode = -1;
            driver.submitDeliveryDocket(areaCode, 123);

            // Get the captured output
            String capturedOutput = outputStream.toString().trim();

            // Add assertions to verify the expected behavior
            assertTrue(capturedOutput.contains("Invalid area code. Please enter a valid area code."));

        } catch (NataliaException | SQLException e) {
            fail("Unexpected exception: " + e.getMessage());
        } finally {
            // Reset System.out to its original PrintStream
            System.setOut(System.out);
        }
    }

    // Test case for the submitDeliveryDocket method with a valid area code and order ID
    public void testSubmitDeliveryDocketValidInput() throws NataliaException{
        try {
            Driver driver = new Driver();
            // Assuming valid inputs (area code and order ID)
            int areaCode = 1;
            int orderID = 123;
            driver.submitDeliveryDocket(areaCode, orderID);
            // Add assertions as needed based on the expected behavior of submitDeliveryDocket
        } catch (Exception e) {
            fail("Unexpected exception: " + e.getMessage());
        }
    }


}
