package model;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Utils {

	public static Object[][] readDataFromJsonFile() {

		JSONParser parser = new JSONParser();

		try {

			JSONArray achievementsFromFile = (JSONArray) parser.parse(new FileReader("achievements.json"));

			List<Achievement> achievementsList = new ArrayList<Achievement>();

			for (Object obj : achievementsFromFile) {
				JSONObject jsonObject = (JSONObject) obj;
				achievementsList.add(new Achievement(jsonObject.get("short"), jsonObject.get("name"),
						jsonObject.get("score"), jsonObject.get("description")));
			}
			return listToArray(achievementsList);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}

	public static Object[][] listToArray(List<Achievement> achievementsList) {

		Object[][] array = new Object[achievementsList.size()][];
		for (int i = 0; i < achievementsList.size(); i++) {
			array[i] = achievementsList.get(i).toObjectArray();
		}
		return array;
	}

}
