package controller;

import view.MainWindowView;

public class Main {

	public static void main(String[] args) {

		new MainWindowView(Utils.listToArray(Utils.readDataFromJsonFile("achievements.json")));

	}

}
