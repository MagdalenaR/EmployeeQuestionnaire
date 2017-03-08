package model;

import java.util.List;

import controller.Utils;

public class AchievementsCategory {

	private String shortName;
	private String name;
	private String points;
	private String description;
	private List<Achievement> achievementsList;

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

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

	public String getDescription() {
		return Utils.checkLength(description);
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Achievement> getAchievementsList() {
		return achievementsList;
	}

	public void setAchievementsList(List<Achievement> achievementsList) {
		this.achievementsList = achievementsList;
	}

	public Object[] toObjectArray() {
		return new Object[] { getShortName(), getName(), getPoints() };
	}

}
