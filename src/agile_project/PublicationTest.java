package agile_project;

import junit.framework.TestCase;

public class PublicationTest extends TestCase {

	public void testPublication() {
		fail("Not yet implemented");
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

//	TestNumber:1
//	Objective: Verify stock > 0
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
//	Objective: Verify stock = 0
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
//	Objective: Verify stock < 0
//	Input: -5
//	Output: False
	
	public void testIsValidStock003() throws RonanException {
		Publication stockTest003 = new Publication();
		try {
			boolean result = stockTest003.isValidStock(-5);
			assertEquals(false, result);
		} catch (RonanException e) {
			fail("exception expected");
		}
	}
	
	public void testGetId() {
		fail("Not yet implemented");
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

	public void testGetQuantity() {
		fail("Not yet implemented");
	}

	public void testSetQuantity() {
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
