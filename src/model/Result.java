package model;

import java.util.ArrayList;
import java.util.List;

public class Result {

	private List<Achievement> resultList = null;
	private static Result instance = null;

	private Result() { 
		this.resultList = new ArrayList<Achievement>();
	}
	
	public static Result getInstance(){
		if (instance == null) {
			instance = new Result();
		}
		return instance;	
	}

	public void addAchievementToResultList(String name, String points) {
		resultList.add(new Achievement(name, points));
	}
}

// wywolanie
// Result.getInstance().addAchievementToResultList("Madzia", 777);