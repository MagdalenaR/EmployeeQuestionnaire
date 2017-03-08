package model;

public class SpecificAchievement {
	
	private String name;
	private String points;
	
	public SpecificAchievement(String name, String points) {
		super();
		this.name = name;
		this.points = points;
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

}
