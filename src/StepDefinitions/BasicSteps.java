package StepDefinitions;

import java.io.IOException;

import org.json.simple.parser.ParseException;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.When;
import cucumber.api.java8.En;


public class BasicSteps implements En {
	public static WebDriver driver = null;
	String username = System.getProperty("user.name");
	String browserName = "Chrome";
	BaseMethods bm = new BaseMethods();

/*	
	@Before
	public void SetupProcess() {
		System.out.println("Before all else");		
		switch (browserName.toLowerCase())  {
		case "firefox": 
			System.out.println("Firefox");
			System.setProperty("webdriver.gecko.driver", "/Users/fujimud/eclipse/java-neon/eclipse/dropins/geckodriver.exe");
		    driver = new FirefoxDriver();
			break;
		case "chrome":
			System.out.println("Chrome");
			System.setProperty("webdriver.chrome.driver", "/Users/" + username +"/eclipse/java-neon/eclipse/dropins/chromedriver_win32/chromedriver.exe");
			driver = new ChromeDriver();
			break;
		default: 
			System.out.println("unknown");
		}
		
	}
	
	
	@After
	public void ClosingTest() {
		System.out.println("After all has been completed");
		driver.close();
	}
*/	
	
	
	@When("^getting the password for \"([^\"]*)\"$")
    public void getPassword(String request) throws Throwable {
		try {
			bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, "accounts;username", request);
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
    }
    
    
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
	public BasicSteps() {
		Given("^able to read from a JSON file$", () -> {
			System.out.println("reading from a JSON file");	
		});
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		Given("^that a test is available$", () -> {
		    System.out.println("testing a new method");
		});
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

/*		When("^getting the password for \"([^\"]*)\"$", (String request) -> {
			try {
				bm.readFromJsonFile("accounts;username", request);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		});
*/		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		When("^testing readFromJsonFile using \"([^\"]*)\" and \"([^\"]*)\"$", (String jsonPath, String request) -> {
			try {
				bm.getDataFromJsonFile(bm.RECORDS4JSONURLS, jsonPath, request);
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
		});

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		When("^we retrieve and document the locators$", () -> {
			System.out.println("retrieve and document the locators");
		});

		Then("^all the data can be saved in Json$", () -> {
			System.out.println("Completed getting the data");
		});
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
	}
}
