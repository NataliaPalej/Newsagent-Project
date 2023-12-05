package agile_project.JUnit;

import junit.framework.TestCase;

import java.time.LocalDate;

import agile_project.Invoice;
import agile_project.Exceptions.NataliaException;

public class InvoiceTests extends TestCase {

    /**
     * Test #1
     * Objective: Verify that invoice ID is valid.
     * Input: 1.
     * Output: true.
     */
    public void testValidInvoiceID() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validInvoiceID(1));
    }

    /**
     * Test #2
     * Objective: Confirm the current system date is a valid invoice date.
     * Input: Current date.
     * Output: Current date.
     */
    public void testValidInvoiceDate() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validInvoiceDate(LocalDate.now()));
    }

    /**
     * Test #3
     * Objective: Verify that CustID is valid.
     * Input: 1.
     * Output: true.
     */
    public void testValidCustID() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validCustID(1));
    }

    /**
     * Test #4
     * Objective: Verify CustName is more than 3 symbols and less than 10
     * Input: John
     * Output: True
     */
    public void testValidCustName() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validCustName("John"));
    }

    /**
     * Test #5
     * Objective: Verify CustAddress is more than 3 symbols and less than 10
     * Input: Athlone 123, Westmeath
     * Output: True
     */
    public void testValidCustAddress() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validCustAddress("Athlone 123, Westmeath"));
    }

    /**
     * Test #6
     * Objective: Verify QuantityDelivered is a positive number
     * Input: 50
     * Output: True
     */
    public void testValidQuantityDelivered() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validQuantityDelivered(50));
    }

    /**
     * Test #7
     * Objective: Verify TotalValue is a positive number
     * Input: 1
     * Output: True
     */
    public void testValidTotalValue() throws NataliaException{
        Invoice invoice = new Invoice(null);
        assertTrue(invoice.validTotalValue(1));
    }
}
