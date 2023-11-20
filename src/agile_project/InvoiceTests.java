package agile_project;
import junit.framework.TestCase;

import agile_project.NataliaException;
import junit.framework.TestCase;

import java.time.LocalDate;

import org.junit.Test;

import static org.junit.Assert.*;
public class InvoiceTests extends TestCase{
	
	
	  /**
     * Test #1
     * Objective: Verify that invoice ID is valid.
     * Input: 1.
     * Output: true.
     */
	@Test
    public void testvalidInvoiceID() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.validInvoiceID(1));
    }
	 /**
     * Test #2
     * Objective: Confirm current system date is a valid invoice date.
     * Input: Current date.
     * Output: Current date
     */
    @Test
    public void testValidInvoiceDate() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.validInvoiceDate(LocalDate.now()));
    }
    /**
     * Test #3
     * Objective: Verify that CustID is valid.
     * Input: 1.
     * Output: true.
     */
	@Test
    public void testvalidCustID() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.validCustID(1));
    }
	 /**
     * Test #4
     * Objective: Verify CustName is more than 3 symbols and less than 10
     * Input: 
     * Output: 
     */
    @Test
    public void testvalidCustName() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.validCustName("John"));
    }
    /**
     * Test #5
     * Objective: Verify CustAddress is more than 3 symbols and less than 10
     * Input: Athlone 123, Westmeath
     * Output: True
     */
    
    @Test
    public void testvalidCustAddress() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.validcustAddress("Athlone 123, Westmeath"));
    }
    /**
     * Test #6
     * Objective: Verify QuantityDelivered is positive number
     * Input: 1
     * Output: True
     */
    @Test
    public void testvalidQuantityDelivered() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.validQuantityDelivered(50));
    }
    /**
     * Test #7
     * Objective: Verify TotalValue is positive number
     * Input: 1
     * Output: True
     */
    @Test
    public void testValidTotalValue() {
        Invoice invoice = new Invoice(0, null, 0, getName(), getName(), 0, 0);
        assertTrue(invoice.ValidTotalValue(1));
    }
}
