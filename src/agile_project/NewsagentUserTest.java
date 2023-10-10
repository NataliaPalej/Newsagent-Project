package agile_project;

import junit.framework.TestCase;

public class NewsagentUserTest extends TestCase {

	public void testCreateNewCustomer01() {
		/** 
		 * Test #1
		 * Objective: Verify customer created successfully
		 * Input: customerID=1, firstName=John, lastName=Kennedy, custAddress=Athlone, phoneNo=0897481234
		 * Output: Customer 1 John Kennedy was created successfully
		 */
		fail("Not yet implemented");
	}
	
	public void testCreateNewCustomer02() {
		/** 
		 * Test #2
		 * Objective: Verify invalid values are handled 
		 * Input: customerID="Marek"
		 * Output: Invalid value was entered. New Customer format: customerID (numbers only), firstName, lastName, address, phoneNo
		 */
		fail("Not yet implemented");
	}

	public void testUpdateNewCustomer01() {
		/** 
		 * Test #1
		 * Objective: Verify address have been updated successfully
		 * Input: custAddress=Athlone, newAddress=Roscommon
		 * Output: custAddress=Roscommon
		 */
		fail("Not yet implemented");
	}
	
	public void testUpdateNewCustomer02() {
		/** 
		 * Test #2
		 * Objective: Verify first name have been updated successfully
		 * Input: firstName=John, firstName=Patrick
		 * Output: firstName=Patrick
		 */
		fail("Not yet implemented");
	}
	
	public void testUpdateNewCustomer03() {
		/** 
		 * Test #3
		 * Objective: Verify last name have been updated successfully
		 * Input: lastName=Kennedy, newLastName=Monroy
		 * Output: lastName=Monroy
		 */
		fail("Not yet implemented");
	}
	
	public void testUpdateNewCustomer04() {
		/** 
		 * Test #4
		 * Objective: Verify phoneNo have been updated successfully
		 * Input: phoneNo=0897481234, newPhoneNo=0123456789
		 * Output: phoneNo=0123456789
		 */
		fail("Not yet implemented");
	}
	
	public void testUpdateNewCustomer05() {
		/** 
		 * Test #5
		 * Objective: Verify customer was successfully updated
		 * Input: firstName=John, lastName=Monroy, custAddress=Athlone, phoneNo=0897481234
		 * Output: Customer 1 John Monroy has been successfully updated
		 */
		fail("Not yet implemented");
	}

	public void testDeactivateCustomer01() {
		/** 
		 * Test #1
		 * Objective: Verify customer account was successfully deactivated 
		 * Input: accStatus = Inactive
		 * Output: true
		 */
		fail("Not yet implemented");
	}
	
	public void testDeactivateCustomer02() {
		/** 
		 * Test #2
		 * Objective: Verify customer account is not deactivated
		 * Input: accStatus = Active
		 * Output: false
		 */
		fail("Not yet implemented");
	}

	public void testReadCustomerDetails01() {
		/** 
		 * Test #1
		 * Objective: Verify customer details are displayed 
		 * Input: John Kennedy
		 * Output: ID: 1, Name: John, Surname: Kennedy, Address: Athlone, PhoneNo: 0897481234
		 */
		fail("Not yet implemented");
	}
	
	public void testReadCustomerDetails02() {
		/** 
		 * Test #2
		 * Objective: Verify if customer doesnt exist exception is thrown
		 * Input: Kate Kennedy
		 * Output: Customer Kate Kennedy does not exist in database
		 */
		fail("Not yet implemented");
	}

	public void testPrintCustomerDetails01() {
		/** 
		 * Test #1
		 * Objective: Verify customer details are printed successfully  
		 * Input: 1
		 * Output: 
		 * --------------------------------------------------
		 *| ID |  Name  |  Surname  |  Address  |   PhoneNo  |
		 * --------------------------------------------------
		 *| 1  |  John  |  Kennedy  |  Athlone  | 0897481234 |
		 * --------------------------------------------------
		 */
		fail("Not yet implemented");
	}
	
	public void testPrintCustomerDetails02() {
		/** 
		 * Test #2
		 * Objective: Verify exception thrown if customer doesnt exist  
		 * Input: 97
		 * Output: Customer ID 97 does not exist in database
		 */
		fail("Not yet implemented");
	}

	public void testGenerateInvoice01() {
		/** 
		 * Test #1
		 * Objective: Verify invoice report is generated successfully 
		 * Input: 1
		 * Outpit: 
		 * -----------------------------------------------------------------------------------
		 *|  Date    |  Name  |  Surname  |   Address   |   PhoneNo  |   Publication   |  Price |
		 * -----------------------------------------------------------------------------------
		 *| 15/10/23 |  John  |  Kennedy  |  2 Athlone  | 0897481234 | Westmeath News  |  4.99  |
		 * -----------------------------------------------------------------------------------
		 *| 16/10/23 |  John  |  Kennedy  |  2 Athlone  | 0897481234 | Westmeath News  |  4.99  |
		 * -----------------------------------------------------------------------------------
		 *| 17/10/23 |  John  |  Kennedy  |  2 Athlone  | 0897481234 | Westmeath News  |  4.99  |
		 * -----------------------------------------------------------------------------------
		 * 																	Total	 | 14.97  |
		 * -----------------------------------------------------------------------------------
		 */
		fail("Not yet implemented");
	}

	public void testGenerateDeliveryDocketReport01() {
		/** 
		 * Test #1
		 * Objective: Verify delivery docket report is generated successfully 
		 * Input: 1
		 * Output:
		 * ------------------------------------------------------------------------------------------------------
		 *|  Date    |  Name  |  Surname  |   Address  |   PhoneNo  |   Publication   | Delivered |     Notes    |
		 * ------------------------------------------------------------------------------------------------------
		 *| 15/10/23 |  John  |  Kennedy  | 2 Athlone  | 0897481234 | Westmeath News  |  YES/NO   |     N/A      |
		 * ------------------------------------------------------------------------------------------------------
		 *| 15/10/23 |  Mary  |   Molly   | 2 Athlone  | 0123456789 | Westmeath News  |  YES/NO   |     N/A      |
		 * ------------------------------------------------------------------------------------------------------
		 *| 15/10/23 |  Mary  |   Molly   | 2 Athlone  | 0123456789 |   Makeup Times  |  YES/NO   |     N/A      |
		 * ------------------------------------------------------------------------------------------------------
		 *| 15/10/23 |  Mary  |   Molly   | 2 Athlone  | 0123456789 |    PC World     |  YES/NO   | Out of Stock |
		 * ------------------------------------------------------------------------------------------------------
		 */
		fail("Not yet implemented");
	}
	

}
