package model;

import java.util.List;

import controller.Utils;

public class Result {

	private List<AchievementsCategory> achievementsCategory;
	private static Result instance = null;

	private Result() {

	}

	public static Result getInstance() {
		if (instance == null) {
			instance = new Result();
		}
		return instance;
	}

	public void setFileToRead(String fileName) {
		this.achievementsCategory = Utils.readDataFromJsonFile(fileName);
	}

	public void addAchievementToResultList(String name, String points) {
		setPointsByName(name, points);
	}

	public List<AchievementsCategory> getAchievementsCategory() {
		return achievementsCategory;
	}

	public void setPointsByName(String name, String points) {
		for (int i = 0; i < achievementsCategory.size(); i++) {
			for (int j = 0; j < achievementsCategory.get(i).getAchievementsList().size(); j++) {
				if (name.equals(achievementsCategory.get(i).getAchievementsList().get(j).getName())) {
					achievementsCategory.get(i).getAchievementsList().get(j).setGainedPoints(points);
					double sum = Double.parseDouble(achievementsCategory.get(i).getPoints())
							+ Double.parseDouble(achievementsCategory.get(i).getAchievementsList().get(j)
									.getGainedPoints().replace(",", "."));
					achievementsCategory.get(i).setPoints(Double.toString(sum));
				}
			}
		}
	}

	public void addSpecyficAchievByName(String name, List<SpecificAchievement> list1) {
		for (int i = 0; i < achievementsCategory.size(); i++) {
			for (int j = 0; j < achievementsCategory.get(i).getAchievementsList().size(); j++) {
				if (name.equals(achievementsCategory.get(i).getAchievementsList().get(j).getName())) {
					achievementsCategory.get(i).getAchievementsList().get(j).setSpecyficAchievementsList(list1);
				}
			}
		}
	}
}
