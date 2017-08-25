package Junit;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import StepDefinitions.BaseMethods;

public class testWebElement {
	BaseMethods bm = new BaseMethods();
	BaseMethods.logDataInfo logCat = bm.new logDataInfo();
	
	
	@Before
	public void navigation2Website() {
		
		//bm.declareBrowserType("bestbuy");
		//bm.declareBrowserType("expedia");
		//bm.declareBrowserType("amazon"); 
		//bm.declareBrowserType("yahoo");
		//bm.declareBrowserType("Tmobile");
		//bm.declareBrowserType("staples");
		//bm.declareBrowserType("uverse");
		bm.declareBrowserType("google");
	}
	
	//@Test
	public void testInnerClassLOG() {
		logCat.debug("testInnerClassLog", "D is working");
		logCat.step("testInnerClassLog", "S is working");
		logCat.info("testInnerClassLog", "I is working");
		logCat.warning("testInnerClassLog", "W is working");
		logCat.error("testInnerClassLog", "E is working");
	}
	
	//@Test
	public void testBestBuy() {
		System.out.println("testing BestBuy");
		List <WebElement> div = bm.driver.findElements(By.tagName("a"));
		System.out.println("SIZE --> " + div.size());
		
		for (WebElement we : div) {
			System.out.println("RESULT ID --> " + we.getAttribute("id"));
			System.out.println("RESULT TEXT --> " + we.getText());
			System.out.println("RESULT HREF --> " + we.getAttribute("href"));
			System.out.println("RESULT --> " + we.getAttribute(""));			
		}		
	}
	
	//@Test
	public void testGoogle() {
		System.out.println("testing testGoogle");
		List <WebElement> bttn = bm.driver.findElements(By.tagName("button"));
		System.out.println("SIZE --> " + bttn.size());
		
		for(WebElement we : bttn) {
			System.out.println("RESULT LABEL --> " + we.getAttribute("label"));
			System.out.println("RESULT ID --> " + we.getAttribute("id"));
			System.out.println("RESULT CLASS --> " + we.getAttribute("class"));
			System.out.println("RESULT TYPE --> " + we.getAttribute("type"));
			System.out.println("RESULT NAME --> " + we.getAttribute("name"));
			System.out.println("");
		}
		
		//List <WebElement> buttons = (List<WebElement>) bm.driver.findElement(By.tagName("button"));
		//String buttons = bm.driver.findElement(By.tagName("Button"));
		//System.out.println(bm.driver.get("button"));
		//String[] buttonText = new String[buttons.getSize()];
		//System.out.println(buttons);
		//for(WebElement data : buttons) {
		//	System.out.println(data.getAttribute("value"));
	//	}
		
	}
	
	
	@After 
	public void closeBrowser() {
		System.out.println("Done");
		bm.driver.close();	
	}

}
