package controller;

import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import model.AchievementsCategory;
import model.Result;

public class Utils {

	private static Gson gson = new Gson();

	public static List<AchievementsCategory> readDataFromJsonFile(String fileName) {

		try (Reader reader = new FileReader(fileName)) {

			List<AchievementsCategory> list = gson.fromJson(reader, new TypeToken<List<AchievementsCategory>>() {
			}.getType());
			return list;

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void writeDataToJsonFile(String fileName) {

		try (FileWriter writer = new FileWriter("reports\\" + fileName + ".json")) {

			gson.toJson(Result.getInstance().getAchievementsCategory(), writer);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Object[][] listToArray(List<AchievementsCategory> achievementsList) {

		Object[][] array = new Object[achievementsList.size()][];
		for (int i = 0; i < achievementsList.size(); i++) {
			array[i] = achievementsList.get(i).toObjectArray();
		}
		return array;
	}

	public static String checkLength(String text) {
		return "<html>" + text + "</html>";
	}

	public static boolean checkIntFormat(String numberString) {
		boolean isFormatRight = false;
		numberString = numberString.trim();
		if (numberString != null) {
			try {
				Integer.parseInt(numberString);
				isFormatRight = true;
			} catch (NumberFormatException e) {
				e.getMessage();
			}
		}
		return isFormatRight;
	}

	public static boolean checkDoubleFormat(String numberString) {
		boolean isFormatRight = false;
		numberString = numberString.replace(',', '.');
		if (numberString != null) {
			try {
				Double.parseDouble(numberString);
				return true;
			} catch (NumberFormatException e) {
				e.getMessage();
			}
		}
		return isFormatRight;
	}

	public static String round(double numberDouble) {
		return String.format("%.1f%n", numberDouble).replace(",", ".");
	}

	public static void addPicture(Container container) {

		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("pictures\\"+"logo-P£3.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		container.add(picLabel);
		picLabel.setBounds(12, 13, 44, 62);
	}

}
