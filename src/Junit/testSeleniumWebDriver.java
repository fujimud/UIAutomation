package Junit;

import java.util.Map.Entry;
import java.util.TreeMap;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import StepDefinitions.BaseMethods;
import StepDefinitions.getTagsAttributes;
import StepDefinitions.getTagsAttributes_BestBuy;
import StepDefinitions.getTagsAttributes_Expedia;

public class testSeleniumWebDriver {
	BaseMethods bm = new BaseMethods();
	getTagsAttributes g = new getTagsAttributes();
	getTagsAttributes_BestBuy gBB = new getTagsAttributes_BestBuy();
	getTagsAttributes_Expedia gE = new getTagsAttributes_Expedia();
	
	
	//@Before
	public void navigation2Website() {
		
		//bm.declareBrowserType("bestbuy");
		bm.declareBrowserType("expedia");    // illegal use of :
		//bm.declareBrowserType("amazon");   // illegal use of :
		//bm.declareBrowserType("yahoo");
		//bm.declareBrowserType("Tmobile");  // illegal use of |
		//bm.declareBrowserType("staples");  // illegal use of |
		//bm.declareBrowserType("uverse");
		//bm.declareBrowserType("google");
	}


	@Test 
	public void testCreatingUniqueTagName() {
		System.out.println("test create a unique tag name");
		JSONObject json = new JSONObject();
		//json.put("TEXT", "Cancel");
		//json.put("ID", "cancelItinEmailButtonAlertMenu");
		//json.put("TYPE", "button");
		json.put("TEXT", "Your notifications you have 1 notification");
		json.put("TYPE", "button");
		
		//json.put("REQUIRED", "true");
		//json.put("VALUE", "01//30//2017");
		//json.put("ID", "package-departing");
		//json.put("CLASS", "datepicker-trigger-input");
		//json.put("TYPE", "text");
		//json.put("MAXLENGTH", "26");
		//json.put("PLACEHOLDER", "mm//dd//yyyy");
		
		//System.out.println("Json file --> " + json);
		String typeAbbreviation = null;
		String tagAbbr = "na";
		
		switch (json.get("TYPE").toString().toLowerCase()) {
		case "checkbox":
			typeAbbreviation = "CKB_";
			break;
		case "text":
			typeAbbreviation = "TXT_";
			break;
		case "submit":
			typeAbbreviation = "SUB_";
			break;
		case "button":
			typeAbbreviation = "BTN_";
			break;
		case "radio":
			typeAbbreviation = "RAD_";
			break;
		default:
			typeAbbreviation = "";
		}
		if (json.containsKey("ID")) tagAbbr = typeAbbreviation + ((String) json.get("ID")).replaceAll("-", "").toUpperCase();
		if (json.containsKey("TEXT")) {
			
			System.out.println("SIZE --> " + json.get("TEXT").toString().length());
			tagAbbr = typeAbbreviation + ((String) json.get("TEXT")).replaceAll("-", "").toUpperCase();
		}
				
	 	System.out.println ("result -->" + tagAbbr + "<");
		 System.out.println("Finished");
	
	}

	//@Test
	public void testGetTagsMethod() {
		try {
			gE.CollectTagsAttributes();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testGetSelectElements() {
		System.out.println("get Select elements and its attriubutes");
		Document page = Jsoup.parse(bm.driver.getPageSource());
		Elements app = page.getAllElements();
		Elements tag = app.select("select");
		
		for (Element data : tag) {
			System.out.println("---------------------------------------");
			
			if ((!data.attr("aria-hidden").equals("true")) && (!data.attr("type").equals("hidden"))) {
				System.out.println(data);
				System.out.println("ID -->  " + data.attr("id"));
				System.out.println("TEXT -->  " + data.text());
				Elements options = data.select("option");
				if (!options.isEmpty()) {
					int cnt = 0;
					
					TreeMap <String, String> tm = new TreeMap<String, String>();
					for (Element opt : options) {
						
						tm.put("OPTION" + cnt, opt.text());
						tm.put("VALUE" + cnt++, opt.attr("value"));
						//System.out.println("OPTION" + cnt + " --> " + opt.text());
						//System.out.println("VALUE" + cnt++ + " --> " + opt.attr("value"));
					}
					System.out.println("SIZE --> " + tm.size());
					System.out.println("TM --> " + tm);
					for (int i = 0; i < tm.size()/2; i++) {
						System.out.println(i + "> " + tm.get("OPTION"+i));
						System.out.println(i + "> " + tm.get("VALUE"+i));
					}
					
				}
				
			}
			
			
		}
	}
	//@Test
	public void testGetButton() {
		System.out.println("get button elements and its attriubutes");
		Document page = Jsoup.parse(bm.driver.getPageSource());
		Elements app = page.getAllElements();
		Elements tag = app.select("button");
		
		for (Element data : tag) {
			System.out.println("---------------------------------------");
			
			if ((!data.attr("aria-hidden").equals("true")) && (!data.attr("type").equals("hidden"))) {
				System.out.println(data);
				System.out.println("TYPE --> " + data.attr("type"));
				System.out.println("ID -->  " + data.attr("id"));
				System.out.println("TEXT -->  " + data.text());
			}
			
			
		}
	}
	
	//@Test
	public void testGetInput() {
		System.out.println("get Input elements and its attriubutes");
		Document page = Jsoup.parse(bm.driver.getPageSource());
		Elements app = page.getAllElements();
		Elements input = app.select("input");
		for (Element data : input) {
			
			//if (!data.attr("type").equals("hidden")) {		// Best Buy
			if ((!data.attr("aria-hidden").equals("true")) && (!data.attr("type").equals("hidden"))) {		// Expedia
				System.out.println("-----------------------------------");
				System.out.println(data);
				System.out.println("TYPE --> " + data.attr("type"));
				System.out.println("VALUE --> " + data.attr("value"));
				System.out.println("REQUIRED --> " + data.attr("aria-required"));
				System.out.println("NAME -->  " + data.attr("name"));
				System.out.println("ID --> " + data.attr("id"));
				String maxlength = (data.attr("maxlength").isEmpty() ? data.attr("data-max-range-length") : data.attr("maxlength"));
				System.out.println("MAXLENGTH --> " + maxlength);
				System.out.println("PLACEHOLDER --> " + data.attr("placeholder"));
				System.out.println("CLASS --> " + data.attr("class"));
				System.out.println("TITLE --> " + data.attr("title"));
				
				//arg = (arg.contains(":")) ? arg.replaceAll(":", "") : arg;
				
			}
			
		}
		
	}
	//@Test
	public void testGetDIVandCLASS() {
		System.out.println("Get div based on the class name");
		TreeMap<String, String> tm = new TreeMap();
		
		Document page = Jsoup.parse(bm.driver.getPageSource());
		Elements app = page.getAllElements();
		Elements div = app.select("div");
		for (Element data : div) {
			
			if (data.attr("class").contains("widget-iconHeadline")) {
				
				//System.out.println(data);
				tm.put("DATA", data.toString());
				if (!data.select("a").isEmpty()) {
					//System.out.println("ANCHOR --> " + data.select("a"));
					for (Element anchors : data.select("a")) {
						//System.out.println("HREF --> " + anchors.attr("href"));
						tm.put("HREF", anchors.attr("href"));
						//System.out.println("TEXT --> " + anchors.text());
						tm.put("TEXT", anchors.attr("text"));						
					}
				}
				
				//System.out.println("TEXT --> " + data.text());
				tm.put("TEXT_ALL", data.text());
				
				System.out.println("------------------------------");
				for (Entry<String, String> entry : tm.entrySet())
					System.out.println("Key --> " + entry.getKey() + "   Value --> " + entry.getValue());
		
				//break;
			}
			
		}
	//	for (Map.Entry<K, V> entry : myMap.entrySet()) {
	//	     System.out.println("Key: " + entry.getKey() + ". Value: " + entry.getValue());
	//	}
		

		 
	}
	
	//@Test
	public void testGetElementsFromWebPage() {
		System.out.println("get button elements from Best Buy");
		Document page = Jsoup.parse(bm.driver.getPageSource());
		Elements app = page.getAllElements();
		Elements buttons = app.select("button");
		//Elements anchors = app.select("a");
	//	System.out.println("HIDDEN -->  " + anchors.attr("type"));
		System.out.println("TAG --> " + buttons);
		for (Element data : buttons) {
			if (!data.attr("aria-hidden").equals("true")) {
				System.out.println("TAG -->  " + data);
			}
		}
		
		
	}
	
	//@Test
	public void testClickWeeklyAd() {
	//	try {
			//String slink = (String) bm.getDataFromJsonFile(bm.CONFIGPATH + "Best Buy Expert Service Unbeatable Price.json", "anchors;text;href", "Weekly Ad");
			//System.out.println("LINK --> " + slink);
			bm.driver.findElement(By.linkText("Weekly Ad")).click();
			try {
				g.CollectTagsAttributes();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			//Thread.sleep(5000);
			
			System.out.println(bm.driver.getTitle());
			
			
	//	} catch (IOException | ParseException | InterruptedException e) {
	//		// TODO Auto-generated catch block
	//		e.printStackTrace();
	//	} 
	}
	
	//@Test
	public void testWebDriverIsRecognized() {
		
		try {
			/*
			if (bm.clickPopupWindowByClassName("close")) {
				g.CollectTagsAttributes();
				System.out.println("clicked the close button for Best Buy popup window");				
				bm.driver.switchTo().activeElement();
				System.out.println("TITLE --> " + bm.driver.getTitle());
			}*/
			
			//System.out.println("Best Buy --> " bm.driver.findElement(By.className("class")))
			//if (bm.driver.findElement(By.className("class")).isDisplayed()) {
			//	System.out.println("checking for Best Buy popup window");
			//	bm.closePopupWindow();
			//}
			//Thread.sleep(5000);
			System.out.println("getting attributes from main page");
			g.CollectTagsAttributes();
		} catch (InterruptedException e) {
			System.err.println("this failed");
			e.printStackTrace();
		}
	}
	
	//@Test
	public void testSpecialCharacters() {
		
		System.out.println(bm.stripOutSpecialCharacters("first:Colon"));
		System.out.println(bm.stripOutSpecialCharacters("first:Colon:Another"));
		System.out.println(bm.stripOutSpecialCharacters("first|Pipe"));
		System.out.println(bm.stripOutSpecialCharacters("first|Pipe|Another"));
		System.out.println(bm.stripOutSpecialCharacters("first.Pipe.Another"));
		System.out.println(bm.stripOutSpecialCharacters("first.Pipe.Another"));
	}
	//@Test
	public void verifyClassVariable() {
		System.out.println("CONFIGPATH -> " + bm.CONFIGPATH);
		System.out.println("RECORDS4JSONURLS -> " + bm.RECORDS4JSONURLS);
		System.out.println("LOGLOCATION -> " + bm.LOGLOCATION);
	}
	
	//@Test
	public void testClassVariable() {
		System.out.println("TITLE --> " + g.removeDoubleQuotes("Enable \"Ok Google\""));
		
	}
	

	
	@After 
	public void closeBrowser() {
		bm.driver.close();	
	}
	
	
	
}
