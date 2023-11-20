package agile_project;

import junit.framework.TestCase;

import java.time.LocalDate;

public class OrderTests extends TestCase {

    /**
     * Test #
     * Objective: Verify that order ID is a valid positive number.
     * Input: 11.
     * Output: true.
     */
    public void testValidOrderID() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validOrderID(11));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Verify that order ID is not a valid negative number.
     * Input: -1.
     * Output: false.
     */
    public void testInvalidOrderID() throws NataliaException{
        Order order = new Order();
		assertFalse(order.validOrderID(-1));
    }

    /**
     * Test #
     * Objective: Confirm the current system date is a valid order date.
     * Input: Current date.
     * Output: Current date.
     */
    public void testValidDate() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validOrderDate(LocalDate.now()));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Ensure 'daily', 'weekly', and 'monthly' are considered valid order types.
     * Input: Strings 'daily', 'weekly', 'monthly'.
     * Output: Expects the validation method to return true for recognized order types.
     */
    public void testValidType() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validType("daily"));
            assertTrue(order.validType("weekly"));
            assertTrue(order.validType("monthly"));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Validate if an exception is thrown for an unrecognized order type.
     * Input: String 'invalidType'.
     * Output: Expects the validation method to throw 'NataliaException' for unrecognized order types.
     */
    public void testInvalidType() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            order.validType("invalidType");
            fail("Expected NataliaException");
        } catch (NataliaException e) {
            // Expected exception
            assertEquals("Invalid order type: invalidType", e.getMessage());
        }
    }

    /**
     * Test #
     * Objective: Verify if the provided publication ID is recognized as valid.
     * Input: Integer representing a publication ID (e.g., 5).
     * Output: Expects the method to return true for a valid publication ID (assumed within a defined range).
     */
    public void testValidPubID() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validPubID(5));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Validate if a title within a specific length is considered valid.
     * Input: String representing a title meeting the required length criteria.
     * Output: Expects the method to return true for a title within the defined length limit.
     */
    public void testValidTitle() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validTitle("Sample Title within Length"));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Confirm if an exception is thrown for a title that doesn't meet the length criterion.
     * Input: String representing a title shorter than the required length.
     * Output: Expects the method to throw 'NataliaException' for titles not meeting the length requirement.
     */
    public void testInvalidTitle() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            order.validTitle("Short");
            fail("Expected NataliaException");
        } catch (NataliaException e) {
            // Expected exception
            assertEquals("Invalid title length: Short", e.getMessage());
        }
    }

    /**
     * Test #
     * Objective: Verify if a provided price falls within a defined valid range.
     * Input: Double representing a price within the expected valid range (e.g., 50.0).
     * Output: Expects the method to return true for prices within the defined valid range.
     */
    public void testValidPrice() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validPubPrice(50.0));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Validate if an exception is thrown for a price outside the defined valid range.
     * Input: Double representing a price outside the valid range (e.g., -5.0).
     * Output: Expects the method to throw 'NataliaException' for prices outside the valid range.
     */
    public void testInvalidPrice() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            order.validPubPrice(-5.0);
            fail("Expected NataliaException");
        } catch (NataliaException e) {
            // Expected exception
            assertEquals("Invalid price: -5.0", e.getMessage());
        }
    }

    /**
     * Test #
     * Objective: Verify if the provided customer ID is recognized as valid.
     * Input: Integer representing a customer ID (e.g., 15).
     * Output: Expects the method to return true for a valid customer ID (assumed within a defined range).
     */
    public void testValidCustID() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validCustID(11));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }

    /**
     * Test #
     * Objective: Validate if a customer name within a specific length is considered valid.
     * Input: John.
     * Output: Expects the method to return true for a customer name within the defined length limit.
     */
    public void testValidCustName() throws NataliaException{
        try {
            Order order = new Order(0, null, null, 0, null, 0, 0, null);
            assertTrue(order.validCustName("John Doe"));
        } catch (NataliaException e) {
            fail("Exception Not expected");
        }
    }
}
