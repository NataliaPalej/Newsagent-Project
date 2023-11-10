package agile_project;

import org.junit.Test;

import agile_project.NataliaException;
import junit.framework.TestCase;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class OrderTests extends TestCase{

    /**
     * Test #
     * Objective: Verify that order ID is valid positive number.
     * Input: 11.
     * Output: true.
     */
	@Test
    public void testValidOrderID() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validOrderID(11));
    }
	/**
     * Test #
     * Objective: Verify that order ID is valid positive number.
     * Input: 11.
     * Output: true.
     */
	@Test
    public void testInValidOrderID() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertFalse(order.validOrderID(-1));
    }

    /**
     * Test #
     * Objective: Confirm current system date is a valid order date.
     * Input: Current date.
     * Output: Current date
     */
    @Test
    public void testValidDate() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validOrderDate(LocalDate.now()));
    }

    /**
     * Test #
     * Objective: Ensure 'daily', 'weekly', and 'monthly' are considered valid order types.
     * Input: Strings 'daily', 'weekly', 'monthly'.
     * Output: Expects the validation method to return true for recognized order types.
     */
    @Test
    public void testValidType() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validType("daily"));
        assertTrue(order.validType("weekly"));
        assertTrue(order.validType("monthly"));
    }

    /**
     * Test #
     * Objective: Validate if an exception is thrown for an unrecognized order type.
     * Input: String 'invalidType'.
     * Output: Expects the validation method to throw 'codeException' for unrecognized order types.
     */
    @Test
    public void testInvalidType() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertThrows(NataliaException.class, () -> order.validType("invalidType"));
    }

    /**
     * Test #
     * Objective: Verify if the provided publication ID is recognized as valid.
     * Input: Integer representing a publication ID (e.g., 5).
     * Output: Expects the method to return true for a valid publication ID (assumed within a defined range).
     */
    @Test
    public void testValidPubID() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validPubID(5)); // Assuming the publication IDs exist within a range in the database
    }

    /**
     * Test #
     * Objective: Validate if a title within a specific length is considered valid.
     * Input: String representing a title meeting the required length criteria.
     * Output: Expects the method to return true for a title within the defined length limit.
     */
    @Test
    public void testValidTitle() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validTitle("Sample Title within Length"));
    }

    /**
     * Test #
     * Objective: Confirm if an exception is thrown for a title that doesn't meet the length criterion.
     * Input: String representing a title shorter than the required length.
     * Output: Expects the method to throw 'codeException' for titles not meeting the length requirement.
     */
    @Test
    public void testInvalidTitle() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertThrows(NataliaException.class, () -> order.validTitle("Short"));
    }

    /**
     * Test #
     * Objective: Verify if a provided price falls within a defined valid range.
     * Input: Double representing a price within the expected valid range (e.g., 50.0).
     * Output: Expects the method to return true for prices within the defined valid range.
     */
    @Test
    public void testValidPrice() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validPubPrice(50.0));
    }

    /**
     * Test #
     * Objective: Validate if an exception is thrown for a price outside the defined valid range.
     * Input: Double representing a price outside the valid range (e.g., -5.0).
     * Output: Expects the method to throw 'codeException' for prices outside the valid range.
     */
    @Test
    public void testInvalidPrice() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertThrows(NataliaException.class, () -> order.validPubPrice(-5.0));
    }
    /**
     * Test #
     * Objective: Verify if the provided customer ID is recognized as valid.
     * Input: Integer representing a customer ID (e.g., 15).
     * Output: Expects the method to return true for a valid customer ID (assumed within a defined range).
     */
    @Test
    public void testValidCustID() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validCustID(11)); // Assuming the customer IDs exist within a range in the database
    }

    /**
     * Test #
     * Objective: Validate if a customer name within a specific length is considered valid.
     * Input: John.
     * Output: Expects the method to return true for a customer name within the defined length limit.
     */
    @Test
    public void testValidCustName() {
        Order order = new Order(0, null, null, 0, null, 0, 0, null);
        assertTrue(order.validCustName("John Doe"));
    }

}
