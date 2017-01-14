package controller;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.regex.Pattern;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.AchievementsCategory;

public class Utils {

	public static List<AchievementsCategory> readDataFromJsonFile() {

		Gson gson = new Gson();

		try (Reader reader = new FileReader("example.json")) {

			List<AchievementsCategory> list = gson.fromJson(reader, new TypeToken<List<AchievementsCategory>>() {
			}.getType());
			return list;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Object[][] listToArray(List<AchievementsCategory> achievementsList) {

		Object[][] array = new Object[achievementsList.size()][];
		for (int i = 0; i < achievementsList.size(); i++) {
			array[i] = achievementsList.get(i).toObjectArray();
		}
		return array;
	}
	
	public static String checkLength(String text) {

		//return "<html>" + text + "</html>";
		return text;
	}
	
	public static int checkIntFormat(String numberOfTitles) {
		int number=0;
		if(numberOfTitles != null) {
			if(Pattern.matches("[0-9]",numberOfTitles)) {
				number = new Integer(numberOfTitles);
			}
		}
		return number;
	}
	
	public static double checkDoubleFormat(String numberString) {
		double number=0;
		if(numberString != null) {
			if(Pattern.matches("[0-9]{3}.[0-9]",numberString)) {
				number = new Double(numberString);
			}
		}
		return number;
	}

}
