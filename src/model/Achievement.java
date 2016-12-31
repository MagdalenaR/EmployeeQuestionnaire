package model;

public class Achievement {
	
	private String shortName, name, points, description;

    public Achievement(Object shotName, Object name, Object points, Object description) {
        this.shortName = (String) shotName;
        this.name = (String) name;
        this.points = (String) points;
        this.description = (String) description;
    }

    public Achievement(String name, String points, String description) {
		this.name = name;
		this.points = points;
		this.description = description;
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
     
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Object[] toObjectArray() {
    	return new Object[] {getShortName(), getName(), getPoints(), getDescription()};
    }

}
