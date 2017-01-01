package model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {

	public static List<Achievement> readDataFromJsonFile(String fileName) {

		JSONParser parser = new JSONParser();
		try {
			JSONArray achievementsFromFile = (JSONArray) parser.parse(new FileReader(fileName));
			List<Achievement> achievementsList = new ArrayList<Achievement>();

			for (Object obj : achievementsFromFile) {
				JSONObject jsonObject = (JSONObject) obj;
				achievementsList.add(new Achievement(jsonObject.get("short"), jsonObject.get("name"),
						jsonObject.get("points"), jsonObject.get("maxPoints"), jsonObject.get("description")));
			}
			return achievementsList;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void writeDataToJsonFile(List<Achievement> achievements) {
		
		JSONArray achievementsToFile = new JSONArray();
		
		for(Achievement ach : achievements) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", ach.getName());
			jsonObject.put("points", ach.getPoints());
			achievementsToFile.add(jsonObject);	
		}
		
//		JSONObject obj = new JSONObject();
//		obj.put("Name", "crunchify.com");
//		obj.put("Author", "App Shah");
// 
//		JSONArray company = new JSONArray();
//		company.add("Compnay: eBay");
//		company.add("Compnay: Paypal");
//		company.add("Compnay: Google");
//		obj.put("Company List", company);
 
		// try-with-resources statement based on post comment below :)
		try (FileWriter file = new FileWriter("file1.txt")) {
			file.write(achievementsToFile.toJSONString());
			System.out.println("Successfully Copied JSON Object to File...");
			System.out.println("\nJSON Object: " + achievementsToFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Object[][] listToArray(List<Achievement> achievementsList) {

		Object[][] array = new Object[achievementsList.size()][];
		for (int i = 0; i < achievementsList.size(); i++) {
			array[i] = achievementsList.get(i).toObjectArray();
		}
		return array;
	}

}
