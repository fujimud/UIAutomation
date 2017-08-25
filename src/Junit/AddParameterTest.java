package Junit;


import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import StepDefinitions.BaseMethods;


@RunWith(Parameterized.class)
public class AddParameterTest {
	
	private String expectedResult;
	private String JsonPath;
	
	@Before
	public void initialize() {
		
		BaseMethods bm = new BaseMethods();
		//BaseMethods = new  BaseMethods();
		//readFromJsonFile rf = new readFromJsonFile();
	}

	public AddParameterTest(String JsonPath, String expectedResult) {
		this.JsonPath = JsonPath;
		this.expectedResult = expectedResult;
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> testResult() {
		return Arrays.asList(new Object[][] {
			{"landing;pageName;link$yahoo.com"},
			{"landingA;pageName;link$yahoo.com"},
			{"landing;pageNameB;link$yahoo.com"},
			{"landing;pageName;linkC$yahoo.com"},
			{"landing;pageName;link$yahooD.com"}
		});
	}
	
	@Test
	public void testReadFromJsonFile() {
			BaseMethods bm = new BaseMethods();
			System.out.println("JSON PATH is -->  " + JsonPath);
			System.out.println("Expected Result -->  " + expectedResult);
			
			try {
				assertNotNull(bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, JsonPath, expectedResult));
			} catch (IOException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
	}
}
