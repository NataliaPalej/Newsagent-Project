package agile_project.JUnit;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.List;

import agile_project.Driver;
import agile_project.Exceptions.NataliaException;
import junit.framework.TestCase;

public class DriverTest extends TestCase {
//###################################################################################
//TESTS
//	choose area of delivery VALID/INVALID++
//	driver ID				VALID/INVALID++
//	READ dockets			VALID/INVALID
//	deduct 	 stock 			VALID/INVALID
//	increase stock 			VALID/INVALID
//	errors are handled		input missmatch!
//###################################################################################

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
	 * Objective: Verify Valid DriverID returns True
	 * Input: int code = 1;
	 * Output: True
	 */
    public void testValidDriverID() {
    	try {
    	Driver  driver  = new Driver ();
    	int driverID = 1;
    	assertTrue(driver.isValidAreaCode(driverID));
    	} catch (Exception e) {
        fail("Exception NOT expected.\\n:");
    	}
    }

    /**
	 * Test 2#
	 * Objective: Verify Invalid DriverID returns false
	 * Input: int code = 0;
	 * Output: false
	 */
    public void testInvalidDriverID() {
    	try {
    	Driver  driver  = new Driver (); // Replace YourTestClass with the actual class containing isValidAreaCode
    	int driverID = 0;
    	assertFalse(driver.isValidDriverID(driverID));
    	} catch (Exception e) {
        fail("Exception NOT expected.\\n:" + e.getMessage());
    	}
    }
    
 //###################################################################################

    /**
	 * Test 1#
	 * Objective: Verify Deduct Docket returns True
	 * Input: int areaCode = 1;
			  int orderID = 123;
	 * Output: True
	 */
//    public void testDeductDocketValid() throws NataliaException, SQLException {
//        try {
//            Driver driver = new Driver();
//            int currentStock = 1;
//            int booksDelivered = 1;
//            assertTrue( driver.deductStock(currentStock - booksDelivered));
//        } catch (NataliaException e) {
//            fail("Exception NOT expected.\\n:" + e.getMessage());
//        }
//    }
    /**
  	 * Test 2#
  	 * Objective: Verify Increse Docket returns false
  	 * Input: int areaCode = -1;
  			  int orderID = 123;
  	 * Output: false
  	 */
//      public void testDeductDocketInvalid() throws NataliaException, SQLException {
//    	  try {
//              Driver driver = new Driver();
//              int currentStock = 1;
//              int booksDelivered = -1;
//              assertTrue( driver.deductStock(currentStock - booksDelivered));
//          } catch (NataliaException e) {
//              fail("Exception NOT expected.\\n:" + e.getMessage());
//          }
//      }
//###################################################################################
}
