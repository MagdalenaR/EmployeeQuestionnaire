package model;

import java.util.List;

import controller.Utils;

public class Achievement {

	private String name;
	private String gainedPoints;
	private String points;
	private String maxPoints;
	private String description;
	private List<SpecificAchievement> specyficAchievementsList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPoints() {
		return points;
	}

	public void setPoints(String points) {
		this.points = points;
	}

	public String getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(String maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getDescription() {
		return Utils.checkLength(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getGainedPoints() {
		return gainedPoints;
	}

	public void setGainedPoints(String gainedPoints) {
		this.gainedPoints = gainedPoints;
	}

	public List<SpecificAchievement> getSpecyficAchievementsList() {
		return specyficAchievementsList;
	}

	public void setSpecyficAchievementsList(List<SpecificAchievement> list1) {
		this.specyficAchievementsList = list1;
	}

}
