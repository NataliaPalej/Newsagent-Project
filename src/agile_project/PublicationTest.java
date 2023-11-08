package agile_project;

import junit.framework.TestCase;

public class PublicationTest extends TestCase {

//	TestNumber: 1
//	Objective: Verify stock or issueNo >= 0
//	Input: 10
//	Output: True
	
	public void testIsValidInt001() throws RonanException {
		Publication intTest001 = new Publication();
		try {
			boolean result = intTest001.isValidInt(10);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}

//	TestNumber: 2
//	Objective: Verify stock or issueNo >= 0
//	Input: 0
//	Output: True
	
	public void testIsValidInt002() throws RonanException {
		Publication intTest002 = new Publication();
		try {
			boolean result = intTest002.isValidInt(0);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}
	}
	
//	TestNumber: 3
//	Objective: Verify invalid throws exception
//	Input: -5
//	Output: False
	
	public void testIsValidInt003() throws RonanException {
		Publication intTest003 = new Publication();
		try {
			boolean result = intTest003.isValidInt(-5);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}
	}
	
//	TestNumber: 4
//	Objective: Verify price >= 0.01 & price <= 999.99
//	Input: 2.99
//	Output: True
	
	public void testIsValidPrice001() throws RonanException {
		Publication priceTest001 = new Publication();
		try {
			boolean result = priceTest001.isValidPrice(10);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}

//	TestNumber: 5
//	Objective: Verify price >= 0.01 & price <= 999.99
//	Input: 0.01
//	Output: True
	
	public void testIsValidPrice002() throws RonanException {
		Publication priceTest002 = new Publication();
		try {
			boolean result = priceTest002.isValidPrice(0.01);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 6
//	Objective: Verify price >= 0.01 & price <= 999.99
//	Input: 999.99
//	Output: True
	
	public void testIsValidPrice003() throws RonanException {
		Publication priceTest003 = new Publication();
		try {
			boolean result = priceTest003.isValidPrice(999.99);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 7
//	Objective: Verify price >= 0.01 & price <= 999.99
//	Input: -5
//	Output: False
	
	public void testIsValidPrice004() throws RonanException {
		Publication priceTest004 = new Publication();
		try {
			boolean result = priceTest004.isValidPrice(-5);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 8
//	Objective: Verify price has 2 dec places max
//	Input: 14.001
//	Output: True
	
	public void testIsValidPrice005() throws RonanException {
		Publication priceTest005 = new Publication();
		try {
			boolean result = priceTest005.isValidPrice(14.001);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 9
//	Objective: Verify price has 2 dec places max
//	Input: 0.001
//	Output: False
	
	public void testIsValidPrice006() throws RonanException {
		Publication priceTest006 = new Publication();
		try {
			boolean result = priceTest006.isValidPrice(0.001);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 10
//	Objective: Verify price >= 0.01 & price <= 999.99
//	Input: 0
//	Output: False
	
	public void testIsValidPrice007() throws RonanException {
		Publication priceTest007 = new Publication();
		try {
			boolean result = priceTest007.isValidPrice(0);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 11
//	Objective: Verify string is valid length
//	Input: "Test"
//	Output: True
	
	public void testIsValidString001() throws RonanException {
		Publication stringTest001 = new Publication();
		try {
			boolean result = stringTest001.isValidString("Test");
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
//	TestNumber: 12
//	Objective: Verify string is valid length @ 50 characters
//	Input: "Test.............................................."
//	Output: True
	
	public void testIsValidString002() throws RonanException {
		Publication stringTest002 = new Publication();
		try {
			boolean result = stringTest002.isValidString("Test..............................................");
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
//	TestNumber: 
//	Objective: Verify Publication object is created
//	Input: Publication(1, "Test", 1, "Test", 2.5, 5);
//	Output: True
	

	public void testPublication() throws RonanException {
		try {
			Publication stockTest004 = new Publication("Test", 1, "Test", 2.5, 5);
			assertEquals("Test", stockTest004.getTitle());
			assertEquals(1, stockTest004.getIssueNo());
			assertEquals("Test", stockTest004.getAuthor());
			assertEquals(2.5, stockTest004.getPrice());
			assertEquals(5, stockTest004.getStock());
		} catch (RonanException e) {
			fail("Exception not expected" + e.getMessage());
		}
	}

	public void testCreateNewPublication() {
		fail("Not yet implemented");
	}

	public void testUpdatePublication() {
		fail("Not yet implemented");
	}

	public void testDeletePublication() {
		fail("Not yet implemented");
	}

	public void testReadPublication() {
		fail("Not yet implemented");
	}

	public void testUpdateStock() {
		fail("Not yet implemented");
	}
	

}
