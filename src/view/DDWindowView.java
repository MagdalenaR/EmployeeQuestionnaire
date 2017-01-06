package view;

import model.Achievement;

import java.util.List;


public class DDWindowView extends WindowView {

	private static final long serialVersionUID = 1L;
	
	private static int I_CONST = 0;
	private static int START = 0;
	private static int END = 18;

	public DDWindowView(List<Achievement> achievementsCategory, List<Achievement> achievements) {
		super(achievementsCategory, I_CONST, achievements, START, END);	
	}
	
}