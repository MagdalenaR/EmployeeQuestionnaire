package controller;

import model.Utils;
import view.MainWindow;

public class Main {

	public static void main(String[] args) {
		
		new MainWindow(Utils.readDataFromJsonFile());
	}

}
