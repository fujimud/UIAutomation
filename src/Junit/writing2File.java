package Junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import StepDefinitions.BaseMethods;
import StepDefinitions.BaseMethods.MessageTypes;

public class writing2File {
	BaseMethods bm = new BaseMethods();
	Boolean bResult = true;
	
	
	@Test
	public void testAddText() {
		
		bm.LOGGER(MessageTypes.STEP, "> Running testAddText");
		//System.out.println("> Running testAddText");
		Path p1 = Paths.get("src/config/records/testFileAddText.txt");

		//Pre-condition check: Confirm that file does not exist. If it does then the file is deleted, otherwise this test will fail.
		if (bm.Check2CThatAFileExist(p1)) {
			try {
				System.out.println("deleting file");
				Files.delete(p1);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		bResult = bm.putDataIntoFile(p1, "The cow jumped over the moon");
		List<String> items = bm.getDataFromFile(p1);
		for(String item : items) {
			System.out.println(item);
			assertEquals("The cow jumped over the moon", item);
		}
		System.out.println(System.lineSeparator());
	}

	@Test
	public void testIOException() {
		System.out.println("> Running testIOException");
		Path p2 = Paths.get("xxx");
		List<String> items = bm.getDataFromFile(p2);
		System.out.println(items);
		System.out.println(System.lineSeparator());
	}
	
	@Test
	public void testLogs() {
		System.out.println("> Running testLogs");
		bResult = bm.putDataIntoFile(Paths.get("src/config/records/logs.txt"), "The cow jumped over the moon");		
		assertEquals(true, bResult);
		System.out.println(System.lineSeparator());
	}	
	
	@Test
	public void testAddTextAgain() {
		System.out.println("> Running testAddTextAgain");
		bResult = bm.putDataIntoFile(Paths.get("src/config/records/testFileAddText.txt"), "And again, and again, and again");
		assertEquals(true, bResult);
		System.out.println(System.lineSeparator());
	}

	@Test
	public void testAddList() {
		System.out.println("> Running testAddList");
		List<String> items = new ArrayList<String>(); 
		items.add("one");
		items.add("two of another");
		items.add("three");
		items.add("four");
		items.add("Five and its the last");
		
		bResult = bm.putDataIntoFile(Paths.get("src/config/records/testFileAddListArray.txt"), items);
		assertEquals(true, bResult);
		System.out.println(System.lineSeparator());
	}
		
	@Test
	public void testAddArray() {
		System.out.println("> Running testAddArray");
		String[] items = {"A", "Bee", "Cee", "Dee", "Eee"};

		bResult = bm.putDataIntoFile(Paths.get("src/config/records/testFileAddArray.txt"), items);
		assertEquals(true, bResult);
		System.out.println(System.lineSeparator());
	}
	
	@Test
	public void testCheckFileExist() {
		System.out.println("> Running testCheckFileExist");
		Path p1  = Paths.get("src/config/records/testFileAddText.txt");
		bResult = bm.putDataIntoFile(p1, "The moon is blue");
		assertTrue(bm.Check2CThatAFileExist(p1));
		System.out.println(System.lineSeparator());
		
	}	
	
	@Test
	public void testCheckFileDoesNotExist() {
		System.out.println("> Running testCheckFileDoesNotExist");
		assertFalse(bm.Check2CThatAFileExist( Paths.get("src/config/records/FILEDOESNOTEXIST.txt")));
		System.out.println(System.lineSeparator());
	}	

	@Test
	public void testCheckDirectoryDoesNotExist() {
		System.out.println("> Running testCheckDirectoryDoesNotExist");
		assertFalse(bm.Check2CThatAFileExist( Paths.get("DIR/DOES/NOT/EXIST")));	
		System.out.println(System.lineSeparator());
	}	
	
	@Test
	public void testputDataIntoFile() {
		BaseMethods bm = new BaseMethods();
		Object oResult = null;
		try {
			System.out.println("TESTINGTestputDataInfoFile");
			oResult = bm.putDataIntoFile(Paths.get("src/config/java8textFile.json"), "The cow jumped over the moon");
			assertEquals(true, oResult);
		} finally {
			System.out.println("RESULT returned --> " + oResult);
			System.out.println("\n---------------------------------\n");
		}	
	}	
	

}
