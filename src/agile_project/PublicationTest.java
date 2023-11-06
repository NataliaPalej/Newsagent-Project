package agile_project;

import junit.framework.TestCase;

public class PublicationTest extends TestCase {

//	TestNumber:1
//	Objective: Verify stock >= 0
//	Input: 10
//	Output: True
	
	public void testIsValidStock001() throws RonanException {
		Publication stockTest001 = new Publication();
		try {
			boolean result = stockTest001.isValidStock(10);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected");
		}

	}

//	TestNumber:2
//	Objective: Verify stock >= 0
//	Input: 0
//	Output: True
	
	public void testIsValidStock002() throws RonanException {
		Publication stockTest002 = new Publication();
		try {
			boolean result = stockTest002.isValidStock(0);
			assertEquals(true, result);
		} catch (RonanException e) {
			fail("exception not expected");
		}
	}
	
//	TestNumber:3
//	Objective: Verify invalid throws exception
//	Input: -5
//	Output: False
	
	public void testIsValidStock003() throws RonanException {
		Publication stockTest003 = new Publication();
		try {
			boolean result = stockTest003.isValidStock(-5);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception not expected");
		}
	}
	
//	TestNumber: 4
//	Objective: Verify Publication object is created
//	Input: Publication(1, "Test", 1, "Test", 2.5, 5);
//	Output: True
	

	public void testPublication() throws RonanException {
		try {
			Publication stockTest004 = new Publication("Test", 1, "Test", 2.5, 5);
			assertEquals(1, stockTest004.getId());
			assertEquals("Test", stockTest004.getTitle());
			assertEquals(1, stockTest004.getIssueNo());
			assertEquals("Test", stockTest004.getAuthor());
			assertEquals(2.5, stockTest004.getPrice());
			assertEquals(5, stockTest004.getStock());
		} catch (RonanException e) {
			fail("Exception not expected");
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
	
	public void testGetId() throws RonanException {
		
	}

	public void testSetId() {
		fail("Not yet implemented");
	}

	public void testGetTitle() {
		fail("Not yet implemented");
	}

	public void testSetTitle() {
		fail("Not yet implemented");
	}

	public void testGetIssueNo() {
		fail("Not yet implemented");
	}

	public void testSetIssueNo() {
		fail("Not yet implemented");
	}

	public void testGetAuthor() {
		fail("Not yet implemented");
	}

	public void testSetAuthor() {
		fail("Not yet implemented");
	}

	public void testGetPrice() {
		fail("Not yet implemented");
	}

	public void testSetPrice() {
		fail("Not yet implemented");
	}

	public void testGetStock() {
		fail("Not yet implemented");
	}

	public void testSetStock() {
		fail("Not yet implemented");
	}

	public void testObject() {
		fail("Not yet implemented");
	}

	public void testGetClass() {
		fail("Not yet implemented");
	}

	public void testHashCode() {
		fail("Not yet implemented");
	}

	public void testEquals() {
		fail("Not yet implemented");
	}

	public void testClone() {
		fail("Not yet implemented");
	}

	public void testToString() {
		fail("Not yet implemented");
	}

	public void testNotify() {
		fail("Not yet implemented");
	}

	public void testNotifyAll() {
		fail("Not yet implemented");
	}

	public void testWait() {
		fail("Not yet implemented");
	}

	public void testWaitLong() {
		fail("Not yet implemented");
	}

	public void testWaitLongInt() {
		fail("Not yet implemented");
	}

	public void testFinalize() {
		fail("Not yet implemented");
	}

}
