package model;

public class Achievement {
	
	private String shortName, name, points;

    public Achievement(Object shotName, Object name, Object points) {
        this.shortName = (String) shotName;
        this.name = (String) name;
        this.points = (String) points;
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
    
	public Object[] toObjectArray() {
    	return new Object[] {getShortName(), getName(), getPoints()};
    }

}
