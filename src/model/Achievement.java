package model;

import controller.Utils;

public class Achievement {

	private String name;
	private String gainedPoints;
	private String points;
	private String maxPoints;
	private String description;

	public Achievement(String name, String points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return Utils.checkLength(name);
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

}
