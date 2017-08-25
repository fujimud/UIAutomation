package StepDefinitions;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

public class BaseMethods {

	public final String CONFIGPATH = "src/config/";
	public final String PATH2RECORDS = CONFIGPATH + "records/";
	public final String URLRECORDS = "urlRecords.json";
	public final String RECORDS4JSONURLS = CONFIGPATH + URLRECORDS;
	public final String LOGLOCATION = CONFIGPATH + "logs/";
	public final String LANDINGPAGENAMELINK = "LANDING;PAGENAME;LINK";
	
	public static WebDriver driver = null;	
	String JsonLocation = "";
	
	logDataInfo logCat = new logDataInfo();
	
	public BaseMethods() {
		
	}
  
	
		
	
	
	public enum MessageTypes {
		//Purpose: List of recognized message types
		SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, STEP, BUG
	}



	//*Selenium*////////////////////////////////////////////////////////////////////////
	public void clickLink(){
		//String slink = (String) getDataFromJsonFile(RECORDS4JSONURLS, "anchors", );
		
	}

	
	
	//////////////////////////////////////////////////////////////////////
	public void CollectTagsAttributes() {

	}
	
	
	//////////////////////////////////////////////////////////////////////
	public String getControllerFromSource() {
		//Purpose: Get a unique attribute to determine if the app is on the expected page
		//Programmed by: Dan Fujimura
		// 		Date: 1/20/17
		//		Modified date:
		//		Modified by: 
		// 		Project: Alpha
		
		String webPage = "T-Mobile.com";
		
		try {
			String slink = (String) getDataFromJsonFile(RECORDS4JSONURLS, LANDINGPAGENAMELINK,webPage);
			driver.navigate().to(slink);
			String pageTitle = driver.getTitle();
			//System.out.println("TITLE -->  " + driver.getTitle());
			if (pageTitle.equals(getDataFromJsonFile(RECORDS4JSONURLS, "landing,pageName", webPage))){
				System.out.println("TITLE --> " + pageTitle);
			} else {
				System.out.println("Match not found");
				LOGGER(MessageTypes.SEVERE, "Match not found for --> " + webPage);
			};
			
			
		} catch (IOException | ParseException e) {
			LOGGER(MessageTypes.SEVERE, e.getStackTrace().toString());
			e.printStackTrace();
		}

		
		//putDataIntoFile(Paths.get("src/config/records/SourceFile.txt"), driver.getPageSource());
		//LOGGER(MessageTypes.INFO, driver.getPageSource());
		
		driver.close();
		
		
		return "no data available";
	}
	

	
	
	///*Logging*//////////////////////////////////////////////////////////////////////////////	
	public class logDataInfo {
		// Purpose: log data into a log file
		// There are 5 different methods to make an entry.
		//  messageType = are the different available type of messages like error, step, debug.
		//  tag = can either be the method or class name
		//  message = the information notes to be added into the log.
		//  Programmed by: Dan Fujimura
		// 	Date: 1/20/17
		//	Modified date:
		//	Modified by: 
		// 	Project: Alpha
				
		
		String messageType;
		String tag;
		String message;
		
		public void debug(String tag, String message) {
			this.messageType = "DEBUG:";
			this.tag = tag;
			this.message = message;
			printLog();
		}
		
		public void step(String tag, String message) {
			this.messageType = "STEP:";
			this.tag = tag;
			this.message = message;
			printLog();
		}
		
		public void warning(String tag, String message) {
			this.messageType = "WARNING:";
			this.tag = tag;
			this.message = message;
			printLog();
		}
		
		public void info(String tag, String message) {
			this.messageType = "INFO:";
			this.tag = tag;
			this.message = message;
			printLog();
		}
		
		public void error(String tag, String message) {
			this.messageType = "ERROR:";
			this.tag = tag;
			this.message = message;
			printLog();
		}
		
		public void printLog() {
			System.out.println("Testing printLog");
			switch (messageType) {
			case "INFO:":
			case "DEBUG:":
				System.out.println(tag + " " + messageType + " " + message);
				break;
			default:
				System.err.println(tag + " " + messageType + " " + message);
			}
			
			// enter data into a log file
			LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
			String datestamp = currentDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
			String timestamp = currentDateTime.format(formatter) + "z ";
			
			Path filePath = Paths.get(LOGLOCATION + datestamp + "_log.log");
			if (!putDataIntoFile(filePath, timestamp + " " + tag + " " + "*" + messageType + "* " + message)) System.err.println("Could not enter text file"); 
			
		}	
 	}
	
	
	
	
	public void LOGGER(String stringType, String message) {
		try {
			System.out.println(MessageTypes.valueOf(stringType.toUpperCase()));	
			LOGGER(MessageTypes.valueOf(stringType.toUpperCase()), message);	
		} catch (IllegalArgumentException e) {
			LOGGER(MessageTypes.SEVERE, e.getMessage() + " \n FAILED to record data into the log");
		}
			
	}
	
	
	
	public void LOGGER (MessageTypes type, String message) {
		//Purpose: Log information from the test cases.
		// msgTypes: Enumeration that has the different message types available for use like SEVERE, WARNING, etc...
		// 			options: SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, STEP, BUG
		// determine date and time in UTC.
		
		LocalDateTime currentDateTime = LocalDateTime.now(ZoneOffset.UTC);
		String datestamp = currentDateTime.format(DateTimeFormatter.BASIC_ISO_DATE);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmSS");
		String timestamp = currentDateTime.format(formatter) + "z ";
		
		if (type.toString().equals("SEVERE") || type.toString().equals("WARNING")) {
			System.err.println(message);
		} else {
			System.out.println(message);
		}

		// Write data into a log
		Path filePath = Paths.get(LOGLOCATION + datestamp + "_log.log");
		if (!putDataIntoFile(filePath, timestamp + "*" + type + "* " + message)) System.err.println("Could not enter text file");
								
	}
	

	
	////*Files*///////////////////////////////////////////////////////////////////////////////
		
	public Boolean Check2CThatAFileExist(Path elementPath) {
		// Check to see if a file actually exist. If so then it return true, else false
		
		
		logCat.info("Check2CThatAFileExist", "> running Check2CThatAFileExist for " + elementPath);		
		if (Files.exists(elementPath)) {
			if (Files.isRegularFile(elementPath) & Files.isReadable(elementPath) & Files.isExecutable(elementPath)) {
				return true;
			} else  {
				logCat.error("Check2CThatAFileExist", "Check2CThatAFileExist: File exist but does not have permission for --> " + elementPath);
				return false;
			}
		}		
		logCat.error("Check2CThatAFileExist", "Check2CThatAFileExist: File does not exist for --> " + elementPath);		
		return false;
	}
	
	///////////////////////////////////////////////////////////////////////////
	

	public List<String> getDataFromFile(Path filePath) {
		//Purpose: Read a file and return it's findings
		// If file is not found it will return "file not found"
		// Note: using Java 8 to write this 
		// see: http://javarevisited.blogspot.com/2015/07/3-ways-to-read-file-line-by-line-in.html
		
		List<String> line = new ArrayList<String>();		
		try {
			Files.lines(filePath)
			.map(s -> s.trim())
			.filter(s -> !s.isEmpty())
			.forEach(line::add);
		} catch (IOException e) {
			//e.printStackTrace();			
			logCat.error("getDataFromFile", "getDataFromFile: could not find a path for " + e.getMessage().toString());
		}
		return line;
	}

///////////////////////////////////////////////////////////////////////////

	public Boolean putDataIntoFile(Path filePath, List<String> listArrayData) {
		// 	write to file from a List array variable
		String fileDummy = null;
		String[] arrayDummy = new String[0];
		
		return putDataIntoFile(filePath, listArrayData, arrayDummy, fileDummy);
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public Boolean putDataIntoFile(Path filePath, String txtFile) {
		// 	write to file from a single string
		List<String> dummyListArray = new ArrayList<String>();
		String[] arrayDummy = new String[0];

		return putDataIntoFile(filePath, dummyListArray, arrayDummy, txtFile);
	}

    /////////////////////////////////////////////////////////////////////////
	
	public Boolean putDataIntoFile(Path filePath, String[] arrayData) {
		// write to file from an array
		List<String> dummyListArray = new ArrayList<>();
		String textDummy = null;

		return putDataIntoFile(filePath, dummyListArray, arrayData, textDummy);
	}
	
	/////////////////////////////////////////////////////////////////////////
	public Boolean putDataIntoFile(Path filePath, List<String> listArrayData, String[] arrayData, String txtData)  {
	//public Boolean putDataIntoFile(String fileLocation, String fileName, String txtData, boolean addTimeStamp)  {
		//Purpose: Write data into a text file using Java 8
		// This supports for following data sources: String, List array, array by Overload methods 
		// It assumes the data provided are all strings
		// Support files:  text, json
								
		Charset charset = Charset.forName("UTF-8");
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset, StandardOpenOption.CREATE, StandardOpenOption.APPEND)) {
		    if (txtData != null) {
		    	writer.write(txtData);
		    	writer.newLine();
		    	
		    } else {
		    	if (arrayData.length != 0) {
		    		listArrayData = Arrays.asList(arrayData);
		    	}
		    	
		    	listArrayData.stream()
		    	.collect(Collectors.toList()).forEach(arg0 -> {
					try {
						writer.write(arg0);
						writer.newLine();
					} catch (IOException e) {
						e.printStackTrace();
						logCat.error("putDataIntoFile", "putDataIntoFile: IOException " + e.toString());
					}
				});
		    }
			
			
		} catch (IOException x) {
		    logCat.error("putDataIntoFile", "putDataIntoFile: IOException " + x.toString());
		    return false;
		}
		
		return true;
		
	}
	
	/////////////////////////////////////////////////////////////////////////
	public Boolean putDataIntoNewFile(Path filePath, List<String> listArrayData) {
		// 	write to file from a List array variable
		String fileDummy = null;
		String[] arrayDummy = new String[0];
		
		return putDataIntoNewFile(filePath, listArrayData, arrayDummy, fileDummy);
	}
	
	/////////////////////////////////////////////////////////////////////////
	
	public Boolean putDataIntoNewFile(Path filePath, String txtFile) {
		// 	write to file from a single string
		List<String> dummyListArray = new ArrayList<String>();
		String[] arrayDummy = new String[0];

		return putDataIntoNewFile(filePath, dummyListArray, arrayDummy, txtFile);
	}

    /////////////////////////////////////////////////////////////////////////
	
	public Boolean putDataIntoNewFile(Path filePath, String[] arrayData) {
		// write to file from an array
		List<String> dummyListArray = new ArrayList<>();
		String textDummy = null;

		return putDataIntoNewFile(filePath, dummyListArray, arrayData, textDummy);
	}	
	
	
	/////////////////////////////////////////////////////////////////////////
	public Boolean putDataIntoNewFile(Path filePath, List<String> listArrayData, String[] arrayData, String txtData)  {
	//public Boolean putDataIntoNewFile(String fileLocation, String fileName, String txtData, boolean addTimeStamp)  {
		//Purpose: Write data into a text file using Java 8
		// This supports for following data sources: String, List array, array by Overload methods 
		// It assumes the data provided are all strings
		// Support files:  text, json
								
		Charset charset = Charset.forName("UTF-8");
		try (BufferedWriter writer = Files.newBufferedWriter(filePath, charset, StandardOpenOption.CREATE)) {
		    if (txtData != null) {
		    	writer.write(txtData);
		    	writer.newLine();
		    	
		    } else {
		    	if (arrayData.length != 0) {
		    		listArrayData = Arrays.asList(arrayData);
		    	}
		    	
		    	listArrayData.stream()
		    	.collect(Collectors.toList()).forEach(arg0 -> {
					try {
						writer.write(arg0);
						writer.newLine();
					} catch (IOException e) {
						e.printStackTrace();
						logCat.error("putDataIntoNewFile", "putDataIntoNewFile: IOException " + e.toString());
					}
				});
		    }
					
		} catch (IOException x) {
			logCat.error("putDataIntoNewFile", "putDataIntoNewFile: IOException " + x.toString());
		    return false;
		}
		
		return true;
		
	}	



	
	
////*JSON*/////////////////////////////////////////////////////////////////////////////////////
	
	public Object getDataFromJsonFile(String pathName, String JsonPath, String findAMatch) throws FileNotFoundException, IOException, ParseException {
		//Purpose: Receive a json request found in the JsonPath string. Find the data an return it's value
		//	Since all JSON files are not the same, it will need to determine if it's either a JSON Object or JSON Array.
		//	Then it will get the data and if JsonPath is not complete then this will again check for an object or array. 
		// 	Once the final result is retrieved it will be returned.
		// 	If null or it failed to find a valid result then they will be handled appropriately.
		//Parameters:
		//	jsonPath is the nested path for JSON. Use ; as the delimiter. Example: "landing;pageName;link"
		//     The last element is reserved for getting the attribute. So "link" it will try to get the attribute for Link if it exist.
		// 	findAMatch is the argument used to find a specific attribute like for example: "google"
		//  Any failed attempt to find a match will return a null. 
		//  Modification: 12/13/2016 - changed name from readFromJsonFile to getDataFromJsonFile
		//  The element name are case sensitive.  
		//  	Any tag names found in JSON needs to be in capital letters
		//  	Any names that are retried from text, label, titles, etc are in their natural format
		Boolean matchFound = false;
		
		Object obj = new JSONParser().parse(new FileReader(pathName));		
		Object result = null;
		
		if (pathName.contains(URLRECORDS)) findAMatch = interpretPageTitleIntoPageName(findAMatch);
		
		//String[] path = JsonPath.toUpperCase().split(";");
		String[] path = JsonPath.split(";");
		int index = 0; 
		
		logCat.info("getDataFromJsonFile", "> running getDataFromJsonFile");
		for(String p: path) {
			System.out.println("Searching for " + p);
			
			// if path(JsonPath) is at the end of it's index then it needs to get it's attribute and return the data
			if(path.length -1 == index) {				
				if (obj instanceof JSONObject) {
					JSONObject jo = (JSONObject) obj;
					return (Object) jo.get(path[path.length -1]);	
				} else {
					JSONArray ja = (JSONArray) obj;
					for (Object o : ja) {
						JSONObject jo = (JSONObject) o;
						String attribute = (String) jo.get(path[path.length -1]);
						if (attribute != null) {
							if (attribute.equals(findAMatch)) {
								return (Object) attribute;
							}
						}
					}
				}				
			}
			index++;
			
			//  get data from a JSON Object
			if (obj instanceof JSONObject) {			
				System.out.println("this is an JSON Object");				
				JSONObject jo = (JSONObject) obj;
				obj = (Object) jo.get(p);
				if (obj == null) {
					logCat.error("getDataFromJsonFile", "No match was found in Json for --> " + p);
					return null;					
				}
				System.out.println("Object is -> " + obj);
				
			// get data from a Json Array
			} else {		
				System.out.println("this is an JSON Array");
				JSONArray ja = (JSONArray) obj;
				
				System.out.println("find match -->  " + findAMatch);
				for (Object o : ja) {
					JSONObject jo = (JSONObject) o;
					System.out.println("Array is -->  " + jo);									
					String elementAttribute = (String) jo.get(p);
					
					System.out.println(p + " --> " + elementAttribute);
					if (elementAttribute == null) {
						logCat.error("getDataFromJsonFile",  "getDataFromJsonFile: No match was found in array --> " + p);
						break;
					}
					
					if(elementAttribute.toUpperCase().equals(findAMatch.toUpperCase())) {
						logCat.info("getDataFromJsonFile", "getDataFromJsonFile: Match found --> " + elementAttribute);
						matchFound = true;						
						obj = (Object) jo;
						break;
					} 
						
					System.out.println("next");
				}  //for (Object o : ja)
				if (!matchFound) {
					logCat.error("getDataFromJsonFile", "getDataFromJsonFile: No match was found in the array for --> " + findAMatch);
					return null;
				}
			}  // if (obj instanceof JSONObject)
		}  // for(String p: path)	
		if (!matchFound) {
			logCat.error("getDataFromJsonFile",  "No match was found in Json for --> " + findAMatch);
			return null;
		}
		return result;
	}

	
//////////////////////////////////////////////////////////////////////
	public String interpretPageTitleIntoPageName(String name) {
		//Purpose: interpret different pagenames to the accepted format found in the Json file
		switch (name.toLowerCase()) {
			case "bestbuy":
			case "best buy":
			case "bestbuy.com":
			case "www.bestbuy.com":
				return "BestBuy";
			case "google":
			case "google.com":
			case "www.google.com":
				return "google";
			case "t-mobile":
			case "tmobile":
			case "t-mobile.com":
			case "www.t-mobile.com":
				return "T-Mobile.com";
			case "yahoo":
			case "yahoo.com":
			case "www.yahoo.com":
				return "Yahoo.com";
			case "amazon":
			case "amazon.com":
			case "www.amazon.com":	
				return "Amazon";
			case "staples":
			case "staple":
			case "staples.com":
			case "www.staples.com":
				return "Staples";
			case "expedia":
			case "expedia.com":
			case "www.expedia.com":
				return "Expedia";
			case "u-verse":
			case "uverse":
			case "uverse.com":
			case "www.uverse.com":
				return "Uverse";
			default:
				logCat.info("interpretPageTitleIntoPageName", name + " has no varition available");
				return name;
		}
	}
	/////////////////////////////////////////////////////////////////////////////////////////	
		public void declareBrowserType(String webPage) {
			//timeout method: http://www.software-testing-tutorials-automation.com/2015/02/how-to-wait-for-page-to-loadready-in.html
			Dimension d = new Dimension(500,600);
			
			String username = System.getProperty("user.name");
			System.setProperty("webdriver.chrome.driver", "/Users/" + username +"/eclipse/java-neon/eclipse/dropins/chromedriver_win32/chromedriver.exe");
			this.driver = new ChromeDriver();
			this.driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
			this.driver.manage().window().setSize(d);
			
			//String webPage = "google";
			try {
				String slink = (String) getDataFromJsonFile(RECORDS4JSONURLS, LANDINGPAGENAMELINK, webPage);
				this.driver.navigate().to(slink);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
			
		}
	
	/////////////////////////////////////////////////////////////////////////////////////////
		public String stripOutSpecialCharacters(String arg) {
			// Purpose strip off special characters. If none are found then the method will return the string
			
			logCat.info("stripOutSpecialCharacters", "removing special characters --> " + arg);
			arg = (arg.contains(":")) ? arg.replaceAll(":", "") : arg;
			arg = (arg.contains("|")) ? arg.replaceAll("\\|", "") : arg;
			arg = (arg.contains(".")) ? arg.replaceAll("\\.", "") : arg;
			return arg;
		}

		/////////////////////////////////////////////////////////////////////////////////////////
		public boolean clickPopupWindowByClassName(String className){
			// Purpose click popup window
			try {
				logCat.info("clickPopupWindowByClassName", "Click popup window close button");
				driver.findElement(By.className(className)).click();
				return true;
			} catch (ElementNotFoundException e) {
				logCat.warning("clickPopupWindowByClassName", "popup window not found");
				return false;
			}
		}
		
}
