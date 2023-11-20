package agile_project;

import java.sql.SQLException;

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
			assertEquals(false, result);
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
	//	Input: "QwertyuiopasdfghjklzxcvbnmQwertyuiopasdfghjklQwert"
	//	Output: True

	public void testIsValidString002() throws RonanException {
		Publication stringTest002 = new Publication();
		try {
			boolean result = stringTest002.isValidString("QwertyuiopasdfghjklzxcvbnmQwertyuiopasdfghjklQwert");
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}

	//	TestNumber: 13
	//	Objective: Verify string is valid length
	//	Input: "QwertyuiopasdfghjklzxcvbnmQwertyuiopasdfghjklQwertyuiopasdfghjklzxcvbnm"
	//	Output: False

	public void testIsValidString003() throws RonanException {
		Publication stringTest003 = new Publication();
		try {
			boolean result = stringTest003.isValidString("QwertyuiopasdfghjklzxcvbnmQwertyuiopasdfghjklQwertyuiopasdfghjklzxcvbnm");
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected" + e.getMessage());
		}

	}

	//	TestNumber: 14
	//	Objective: Verify string is not blank
	//	Input: " "
	//	Output: False

	public void testIsValidString004() throws RonanException {
		Publication stringTest004 = new Publication();
		try {
			boolean result = stringTest004.isValidString(" ");
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected: " + e.getMessage());
		}

	}

	//	TestNumber: 15
	//	Objective: Verify string is not null
	//	Input: null
	//	Output: False

	public void testIsValidString005() throws RonanException {
		Publication stringTest005 = new Publication();
		try {
			boolean result = stringTest005.isValidString(null);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected: " + e.getMessage());
		}

	}

	//	TestNumber: 16
	//	Objective: Verify valid publication ID 
	//	Input: 1
	//	Output: 1st publication in database

	public void testGetPublicationById001() throws RonanException {
		Publication getPubByIdTest001 = new Publication();
		int pubId = 1;
		try {
			Publication result = getPubByIdTest001.getPublicationById(1);
			assertEquals(pubId, result.getId());
			assertEquals("Irish Independent", result.getTitle());
			assertEquals(100, result.getIssueNo());
			assertEquals("John Doe", result.getAuthor());
			assertEquals(2.5, result.getPrice());
			assertEquals(100, result.getStock());

		} catch (RonanException e) {
			fail("exception not expected: " + e.getMessage());
		}
	}

	//		TestNumber: 17
	//		Objective: Verify invalid publication ID < 1
	//		Input: 0
	//		Output: 1st publication in database

	public void testGetPublicationById002() throws RonanException {
		Publication getPubByIdTest002 = new Publication();
		int pubId = 0;
		try {
			Publication result = getPubByIdTest002.getPublicationById(0);
			fail("exception expected");

		} catch (RonanException e) {
			assertEquals("Publication with ID(" + pubId + ") NOT found.", e.getMessage());
		}
	}

	//		TestNumber: 18
	//		Objective: Verify invalid publication ID out of bounds
	//		Input: Integer.MAX_VALUE
	//		Output: 1st publication in database

	public void testGetPublicationById003() throws RonanException {
		Publication getPubByIdTest002 = new Publication();
		try {
			Publication result = getPubByIdTest002.getPublicationById(Integer.MAX_VALUE);
			fail("exception expected");
		} catch (RonanException e) {
			assertEquals("Publication with ID(0) NOT found.", e.getMessage());
		}
	}

	//	TestNumber: 19
	//	Objective: Verify Publication object is created
	//	Input: Publication(1, "Test", 1, "Test", 2.5, 5);
	//	Output: True


	public void testPublication001() throws RonanException {
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

	//	TestNumber: 20
	//	Objective: Verify Publication object is created in database
	//	Console Input: Test | 1 | Test | 1.99 | 1
	//	Output: Publication: 21, Test #1 was successfully created!

	public void testCreatePublication001() throws RonanException, SQLException {
		try {
			Publication createTest001 = new Publication();
			createTest001.createPublication();
			assertEquals(21, createTest001.getId());
			assertEquals("Test", createTest001.getTitle());
			assertEquals(1, createTest001.getIssueNo());
			assertEquals("Test", createTest001.getAuthor());
			assertEquals(1.99, createTest001.getPrice());
			assertEquals(1, createTest001.getStock());

		} catch (RonanException | SQLException e) {
			fail("exception not expected: " + e.getMessage());
		}
	}

	//	TestNumber: 21
	//	Objective: Update the title of first publication
	//	Console Input: Change title to "Test"
	//	Output: Returns publication with new values

	public void testUpdatePublication001() {
		try {
			Publication updateTest001 = new Publication();

			int publicationID = 1;
			String newTitle = "Test";

			updateTest001.updatePublication(publicationID);
			// Retrieve the updated customer details from the database
			Publication updatedTitle = updateTest001.getPublicationById(publicationID);
			String actualTitle = updatedTitle.getTitle();

			assertEquals(newTitle, actualTitle);
		} catch (RonanException | SQLException e) {
			fail("Exception NOT expected.\n" + e.getMessage());
		}
	}

	//	TestNumber: 22
	//	Objective: Delete the first publication
	//	Console Input: publicationID = 12
	//	Output: Returns publication with new values

	public void testDeleteCustomer001() throws RonanException {
		try {
			Publication deleteTest001 = new Publication();
			int publicationID = 12;
			deleteTest001.deletePublication(publicationID);
			deleteTest001.getPublicationById(publicationID);
			fail("exception expected");
		}catch (RonanException | SQLException e) {
			assertEquals("Publication with ID(0) NOT found.", e.getMessage());
		}
	}

}
