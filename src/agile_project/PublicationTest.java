package agile_project;

import junit.framework.TestCase;

public class PublicationTest extends TestCase {

//	TestNumber:1
//	Objective: Verify type(daily) is assigned to order
//	Input: daily
//	Output: True

	@Test
    public void testcase001() throws RonanException{
        Publication type = new Publication("daily","date","type");
        try {
        	
        	assertEquals(true,result);
        	
        }catch(RonanException e){
        	fail("exception not expected");
        }
       
	}

}
