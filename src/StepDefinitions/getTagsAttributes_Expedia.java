package StepDefinitions;

import java.nio.file.Paths;
import java.util.TreeMap;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class getTagsAttributes_Expedia {
		
	public BaseMethods bm = new BaseMethods();
		
	public void CollectTagsAttributes () throws InterruptedException {
		//Purpose: get and record specific tags and their attributes into a Json file
		//   The Json file purpose is to be the data source for UI automation testing.
		//  findElement explanation: http://www.techbeamers.com/findelement-and-findelements-commands-example
							
		JSONObject store = new JSONObject();
		
		//String title = "TESTING";

		Thread.sleep(5000);
		System.out.println("> gathing the different tag elements");
			
		Document page = Jsoup.parse(bm.driver.getPageSource());
		Elements app = page.getAllElements();		// Master copy of the parsed pagesource
		Elements icons = app.select("md-icon");
		Elements selection = app.select("select");
		Elements inputs = app.select("input");
		Elements buttons = app.select("button");
		Elements ngRepeats = app.select("tr");
		Elements labels = app.select("label");
		Elements theads = app.select("tr.column-filters");
		//Elements lists = app.select("li");
		Elements anchors = app.select("a");

		
		///////////////////////////////////////////////////////////////
		/*
		System.out.println("extracting anchor attributes");
		if (!anchors.isEmpty()) {
			String tagName = "ANCHORS";
			JSONObject storageAnchor = new JSONObject();
			for (Element data : anchors) {
				JSONObject json = new JSONObject();
				
				String id = data.attr("id");
				String name = data.attr("name");
				String href = data.attr("href");
				String text = data.text();
				String strong = data.attr("strong");
				String className = data.attr("class");
				String label = data.attr("aria-label");
				String title = data.attr("title");
				
				
				
				//json.put("controller", title);
				if (!id.isEmpty()) json.put("ID", id);
				if (!name.isEmpty()) json.put("NAME", name);
				if (!href.isEmpty()) json.put("HREF", href);
				if (!label.isEmpty()) json.put("LABEL", label);
				if (!title.isEmpty()) json.put("TITLE", title);
				if (!text.isEmpty()) {
					json.put("TEXT", text);
				} else {
					json.put("TEXT", strong);
				}
				if (!className.isEmpty()) json.put("CLASS", className);
				
				String jsonTitle = null;
				if(!name.isEmpty()) {
					jsonTitle = name;
				} else {
					jsonTitle = text;
				}
				storageAnchor.put(jsonTitle, json);	
			}  // END OF for (Element data : icons) {
			store.put(tagName, storageAnchor);		
		}  // END OF if (!icons.isEmpty()) {
		*/
		///////////////////////////////////////////////////////////////
		/*
		System.out.println("extracting icons attributes");
		if (!icons.isEmpty()) {
			String tagName = "ICONS";
			JSONObject storageIcon = new JSONObject();
			for (Element data : icons) {
				JSONObject json = new JSONObject();
				
				String id = data.attr("id");
				String label = data.attr("aria-label");
				if (!label.isEmpty()) label = label.substring(label.indexOf('%') + 1, label.lastIndexOf('%')); 			
				String click = data.attr("ng-click");
				String type = data.attr("type");
				String source = data.attr("md-svg-src");
				
				//json.put("controller", title);
				if (!id.isEmpty()) json.put("ID", id);
				if (!click.isEmpty()) json.put("NGCLICK", click);
				if (!type.isEmpty()) json.put("TYPE", type);
				if (!source.isEmpty()) json.put("SRC", source);
				
				String jsonTitle = null;
				if(!label.isEmpty()) {
					jsonTitle = label;
				} 
				storageIcon.put(jsonTitle, json);	
			}  // END OF for (Element data : icons) {
			store.put(tagName, storageIcon);		
		}  // END OF if (!icons.isEmpty()) {
		*/
		///////////////////////////////////////////////////////////////
		
		System.out.println("extracting Select attributes");
		if (!icons.isEmpty()) {
			String tagName = "SELECT";
			JSONObject storageSelect = new JSONObject();
			for (Element data : selection) {
				JSONObject json = new JSONObject();
				
				String id = data.attr("id");
				Elements options = data.select("option");
				String text = "";
				String value = "";
				if (!options.isEmpty()) {
					TreeMap <String, String> tm = new TreeMap<String, String>();
					int cnt = 0;

					for (Element opt : options) {
						tm.put("OPTION" + cnt, opt.text());
						tm.put("VALUE" + cnt++, opt.attr("value"));
						//System.out.println("OPTION" + cnt + " --> " + opt.text());
						//System.out.println("VALUE" + cnt++ + " --> " + opt.attr("value"));
					}
					for (int i = 0; i < tm.size()/2; i++) {
						json.put("OPTION"+i, tm.get("OPTION"+i));
						json.put("VALUE"+i, tm.get("VALUE"+i));
					}
				}

				System.out.println(data);
				
				if (!id.isEmpty()) json.put("ID", id);
				if (!text.isEmpty()) json.put("TEXT", text);
				
				
				String jsonTitle = id;
				 
				storageSelect.put(jsonTitle, json);	
			}  // END OF for (Element data : icons) {
			store.put(tagName, storageSelect);		
		}  // END OF if (!icons.isEmpty()) {		
		
		///////////////////////////////////////////////////////////////
		System.out.println("extracting input attributes");
		if (!inputs.isEmpty()) {
			String tagName = "INPUT";
			JSONObject storageInput = new JSONObject();
			for (Element data : inputs) {
				JSONObject json = new JSONObject();
				if ((!data.attr("aria-hidden").equals("true")) && (!data.attr("type").equals("hidden"))) {
					String type = data.attr("type");
					String value = data.attr("value");
					String required = data.attr("aria-required");
					String inputName = data.attr("name");					
					String id = data.attr("id");
					String maxlength = (data.attr("maxlength").isEmpty() ? data.attr("data-max-range-length") : data.attr("maxlength"));
					String placeHolder = data.attr("placeholder");
					String inputClass = data.attr("class");
					String title = data.attr("title");
					System.out.println(data);
					
					if (!type.isEmpty()) json.put("TYPE", type);
					if (!value.isEmpty()) json.put("VALUE", value);
					if (!required.isEmpty()) json.put("REQUIRED", required.toString());
					if (!inputName.isEmpty()) json.put("NAME", inputName);	
					if (!id.isEmpty()) json.put("ID", id);
					if (!maxlength.isEmpty()) json.put("MAXLENGTH", maxlength);
					if (!placeHolder.isEmpty()) json.put("PLACEHOLDER", placeHolder);					
					if (!inputClass.isEmpty()) json.put("CLASS", data.attr("class"));
					if (!title.isEmpty()) json.put("TITLE", title);
										
					
										
					
					String jsonTitle = generateUniqueTagName4InputTag(json);
				/*	if (!value.isEmpty()) {
						jsonTitle = value;
					} else if (!placeHolder.isEmpty()) {
						jsonTitle = placeHolder;
					} else {
						jsonTitle = inputClass;
					} */
					storageInput.put(jsonTitle, json);	
				}
			}  //for (Element data : inputs) 
			store.put(tagName, storageInput);
		}  //if (!input.isEmpty())

		///////////////////////////////////////////////////////////////
		System.out.println("extracting button attributes");
		if (!buttons.isEmpty()) {
			String tagName = "BUTTON";
			JSONObject storageButton = new JSONObject();						
			for (Element data : buttons) {
				JSONObject json = new JSONObject();

				String type = data.attr("type");
				String id = data.attr("id");
				String text = data.text();
				
				System.out.println(data);	
				
				if(!type.isEmpty()) json.put("TYPE", type);
				if(!id.isEmpty()) json.put("ID", id);
				if(!text.isEmpty()) json.put("TEXT", text);
				
				String jsonTitle = text;

				storageButton.put(jsonTitle, json);		
			}  // END OF for (Element data : buttons)			
			store.put(tagName, storageButton);
			
		}		// END OF if (!buttons.isEmpty())


		///////////////////////////////////////////////////////////////
		/*
		System.out.println("extracting ng-repeater attributes");
		if (!ngRepeats.isEmpty()) {
			JSONArray arrRepeater = new JSONArray();
			String type = "REPEATER";
			String repeatText = null;
			for (Element data : ngRepeats) {
				JSONObject json = new JSONObject();
				
				String id = data.attr("id");
				String ngRepeat = data.attr("ng-repeat");
				String ngClick = data.attr("ng-click");
				String repeatTitle = data.attr("title");
				repeatText = data.text();
				String className = data.attr("class");
				//String dataLabel = data.attr("data-label");
				String label = (!data.attr("aria-label").isEmpty()) ? data.attr("aria-label") : data.attr("label");
				System.out.println(data);	
				
				if (!ngRepeat.isEmpty()) {
					json.put("ngRepeat", ngRepeat);
					if (!id.isEmpty()) json.put("ID", id);
					if (!ngClick.isEmpty()) json.put("NGCLICK", ngClick);
					if (!repeatTitle.isEmpty()) json.put("TITLE", repeatTitle);
					if (!repeatText.isEmpty()) json.put("TEXT", repeatText);
					if (!className.isEmpty()) json.put("CLASS", className);
					if (!label.isEmpty()) json.put("LABEL", label);
					if (!type.isEmpty()) json.put("TYPE", type);
					//storageInput.put(ngClick, json);
					arrRepeater.add(json);
				}  // END OF if (!ngRepeat.isEmpty())
			}
			//store.put(role, storageInput);
			store.put(type, arrRepeater);
			System.out.println("STOP");
		} // END OF for (Element data : ngRepeats) 
		*/
		///////////////////////////////////////////////////////////////////////////
		/*
		System.out.println("extracting label attributes");
		if (!labels.isEmpty()) {						
			JSONArray storageLabel = new JSONArray();
			String type = "LABEL";
			String label = null;
			for (Element data : labels) {							
				label = data.text();
				boolean noMatchFound = true;
				for (int i = 0; i < storageLabel.size(); i++) {
					String content = (String) storageLabel.get(i);
					if(content.equals(label)) {
						noMatchFound = false;
						break;
					}  // END OF  if(content.equals(label)) 
				} // END OF for (int i = 0; i < storageLabel.size(); i++)
				if (noMatchFound) storageLabel.add(label);
			}  // END OF for (Element data : labels)
			store.put(type, storageLabel);
		} // END OF if (!labels.isEmpty())
		*/
		///////////////////////////////////////////////////////////////////////////
		/*
		System.out.println("extracting column attributes");
		if (!theads.isEmpty()) {		
			String type = "FILTERCOLUMN";
			JSONObject storageFilter = new JSONObject();
			
			Elements value = theads.select("th");
			for(Element result : value) {
				JSONObject json = new JSONObject();
				String filterLabel = result.attr("data-label");
				String ngClass = result.attr("ng-class");
				String ngModel = result.select("input").attr("ng-model");
				String ngClick = result.select("button").attr("ng-click");
				String filterText = result.text();
				
				if(!filterLabel.isEmpty()) json.put("LABEL", filterLabel);
				if(!ngClass.isEmpty()) json.put("CLASS", ngClass);
				if(!ngModel.isEmpty()) json.put("NGMODEL", ngModel);
				if(!ngClick.isEmpty()) json.put("NGCLICK", ngClick);
				if(!filterText.isEmpty()) json.put("TEXT", filterText);
				
				String filterTitle = filterLabel;
				if (filterText.isEmpty()) {
					if(!filterLabel.isEmpty()) filterTitle = filterText;
				}
				storageFilter.put(filterTitle, json);
			}
			store.put(type, storageFilter);
		}
		*/
		///////////////////////////////////////////////////////////////////////////
		/*
		System.out.println("extracting lists attributes");
		if (!lists.isEmpty()) {
			String tagName = "LIST";
			JSONObject storageList = new JSONObject();
			for (Element data : lists) {
				JSONObject json = new JSONObject();
				
				String id = data.attr("id");
				Elements anchorList = data.getElementsByTag("a");
				String href = anchorList.attr("href");
				String dataLid = anchorList.attr("data-lid");
				String className = data.attr("class");
				String text = data.text();
				String alt = data.attr("alt");
				String source = data.attr("src");
				
				
				
				//json.put("controller", title);
				if (!id.isEmpty()) json.put("ID", id);
				if (!dataLid.isEmpty()) json.put("DATALID", dataLid);
				if (!text.isEmpty()) json.put("TEXT", text);
				if(!href.isEmpty()) json.put("HREF", href);
				if(!className.isEmpty()) json.put("CLASS", className);
				if(!alt.isEmpty()) json.put("ALT", alt);
				if(!source.isEmpty()) json.put("SRC", source);
				
				
				
				String jsonTitle = null;
				if(!text.isEmpty()) {
					jsonTitle = text;
				} 
				storageList.put(jsonTitle, json);	
			}  // END OF for (Element data : icons) {
			store.put(tagName, storageList);		
		}  // END OF if (!lists.isEmpty()) {
		*/
		///////////////////////////////////////////////////////////////////////////
		// Record tags and their attributes in a json file
		//String whereTo = bm.CONFIGPATH + "TESTJSON.json";
		//Path pathName = Paths.get(bm.CONFIGPATH + "TESTJSON.json");
		System.out.println("TITLE -->  " + bm.driver.getTitle());
		bm.putDataIntoNewFile(Paths.get(bm.CONFIGPATH + "records/" + bm.stripOutSpecialCharacters(bm.driver.getTitle()) + ".json"), store.toJSONString());	
		
	}  //  END OF CollectTagsAttributes	
	
	///////////////////////////////////////////////////////////////////////////
	public String generateUniqueTagName4InputTag(JSONObject jsonFile) {
		// Purpose: Need to create a unique tag name when entering the attributes into Json.
		// 
		String typeAbbreviation = null;
		String tagAbbr = "na";
		
		// Abbreviate the type into a three letter string
		switch (jsonFile.get("TYPE").toString().toLowerCase()) {
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
		if (jsonFile.containsKey("ID")) return typeAbbreviation + ((String) jsonFile.get("ID")).replaceAll("-", "").toUpperCase();
		
		return tagAbbr;
	}
	
	
	///////////////////////////////////////////////////////////////////////////
	public String removeDoubleQuotes(String arg) {
		if (arg.contains("\"")) {
			//for(int i = 0; i < arg.length(); i++) 
			
		}
		return arg;
				
	}
	

	
	
}  // END OF class getTagsAttributes
