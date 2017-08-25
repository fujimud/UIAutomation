package Junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.junit.Test;
import org.testng.Assert;

import StepDefinitions.BaseMethods;

public class readFromJsonFile {
	Object oResult = null;
	
	@Test
	public void test() {
		BaseMethods bm = new BaseMethods();				

		try {
			System.out.println("TESTING");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "LANDING;PAGENAME;LINK","yahoo.com");
			assertEquals("https://www.yahoo.com/", oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}	
	}

	@Test
	public void testGetDataFromJson() {
		BaseMethods bm = new BaseMethods();	
		String sResult = null;
		System.out.println("Test to get data from Best Buy");
		try {
			sResult = (String) bm.getDataFromJsonFile(bm.CONFIGPATH + "Best Buy Expert Service Unbeatable Price.json", "ANCHORS;Weekly Ad;TEXT", "Weekly Ad");
			Assert.assertEquals(sResult, "Weekly Ad");
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + sResult);
			System.out.println("\n---------------------------------\n");
		}		
	}
	
	@Test
	public void testA() {
		BaseMethods bm = new BaseMethods();				
		try {
			System.out.println("TESTING A");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "LANDINGA;PAGENAME;LINK","yahoo.com");
			//assertNotNull(oResult);
			//assertEquals(null, oResult);
			assertNull(oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}
						
	}	

	@Test
	public void testB() {
		BaseMethods bm = new BaseMethods();				
		try {
			System.out.println("TESTING B");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "LANDING;PAGENAMEB;LINK","yahoo.com");
			assertNull(oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}			
	}
	
	@Test
	public void testC() {
		BaseMethods bm = new BaseMethods();						
		try {
			System.out.println("TESTING C");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "LANDING;PAGENAME;LINKC","yahoo.com");
			//assertEquals("https://www.yahoo.com/", oResult);
			//assertNotNull(oResult);
			assertNull(oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}	
	}

	@Test
	public void testD() {
		BaseMethods bm = new BaseMethods();				
		try {
			System.out.println("TESTING D");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, bm.LANDINGPAGENAMELINK,"yahoo.comD");
			//assertEquals("https://www.yahoo.com/", oResult);
			//assertNotNull(oResult);
			assertNull(oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}			
	}

	@Test
	public void testE() {
		BaseMethods bm = new BaseMethods();				
		try {
			System.out.println("TESTING E");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "ACCOUNTS;USERNAME;PASSWORD","dfujimu");
			assertEquals("xsMonkey2016", oResult);
			assertNotNull(oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}			
	}	
	
	@Test
	public void testGetEnvironment4Staples() {
		BaseMethods bm = new  BaseMethods();
		Object oResult = null;
		try {
			System.out.println("testGetPathFromJson");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "LANDING;PAGENAME;ENVIRONMENT","Staples");
			assertEquals("production", oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}	
	}

	@Test
	public void testGetPathFromJsonThatFAILS() {
		BaseMethods bm = new  BaseMethods();
		Object oResult = null;
		try {
			System.out.println("testGetPathFromJson");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "PATHLOCATIONS;BASEPATHSSSS","");
			assertNull(oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}	
	}	
	
	@Test
	public void testGetPageName4Tmobile() {
		BaseMethods bm = new  BaseMethods();
		Object oResult = null;
		try {
			System.out.println("testGetPathName4Tmobile");
			oResult = bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "LANDING;PAGENAME","T-Mobile.com");
			assertEquals("T-Mobile.com", oResult);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}	
	}		

}
