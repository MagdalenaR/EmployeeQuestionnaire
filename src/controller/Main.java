package controller;

import view.MainWindow;

public class Main {

	public static void main(String[] args) {

		new MainWindow(Utils.listToArray(Utils.readDataFromJsonFile("achievements.json")));

	}

}
