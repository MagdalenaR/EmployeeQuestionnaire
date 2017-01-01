package model;

public class Achievement {

	private String shortName, name, points, maxPoints, description;

	public Achievement(Object shotName, Object name, Object points, Object maxPoints, Object description) {
		this.shortName = (String) shotName;
		this.name = (String) name;
		this.points = (String) points;
		this.maxPoints = (String) maxPoints;
		this.description = (String) description;
	}

	public Achievement(String name, String points) {
		this.name = name;
		this.points = points;
	}

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

	public String getMaxPoints() {
		return maxPoints;
	}

	public void setMaxPoints(String maxPoints) {
		this.maxPoints = maxPoints;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object[] toObjectArray() {
		return new Object[] { getShortName(), getName(), getPoints(), getMaxPoints(), getDescription() };
	}

}
