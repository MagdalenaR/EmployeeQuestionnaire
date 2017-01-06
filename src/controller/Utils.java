package controller;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import model.Achievement;

public class Utils {

	//wczytywac od parametru i, dla konkretnych okienek
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

	@SuppressWarnings("unchecked")
	public static void writeDataToJsonFile(List<Achievement> achievements) {

		JSONArray achievementsToFile = new JSONArray();

		for (Achievement ach : achievements) {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("name", ach.getName());
			jsonObject.put("points", ach.getPoints());
			achievementsToFile.add(jsonObject);
		}

		try (FileWriter file = new FileWriter("Report.json")) {
			file.write(achievementsToFile.toJSONString());
			System.out.println("JSON Object: " + achievementsToFile);
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
