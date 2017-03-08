package controller;

import javax.swing.JOptionPane;

public class Calculations {

	public static String actualPoints = "0.0";

	public static String ordinaryCalculation(boolean isNumberOfTeamMembersNeeded, double currentPoints,
			double pointsToAdd, String maxPoints) {

		actualPoints = "0.0";

		int numberOfTeamMembers = 1;
		if (isNumberOfTeamMembersNeeded) {

			String numberOfTeamMembersString = (String) JOptionPane.showInputDialog(null,
					"Podaj iloœæ cz³onków zespo³u:", "Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null,
					null);
			if (Utils.checkIntFormat(numberOfTeamMembersString))
				numberOfTeamMembers = Integer.parseInt(numberOfTeamMembersString.trim());
		}

		if (maxPoints.equals("")) {
			maxPoints = "1000.0";
		}

		double gainedPoints = currentPoints + (pointsToAdd / numberOfTeamMembers);
		if (Utils.checkDoubleFormat(maxPoints)) {
			if (gainedPoints > Double.parseDouble(maxPoints)) {
				gainedPoints = Double.parseDouble(maxPoints);
				actualPoints = Utils.round(gainedPoints - currentPoints);
			} else
				actualPoints = Utils.round(pointsToAdd / numberOfTeamMembers);
		}

		return Utils.round(gainedPoints);
	}

	public static String manageCalculationLess(double currentPoints, String maxPoints, int i) {

		actualPoints = "0.0";

		double pointsToAdd = 0.0;
		if (i == 0)
			pointsToAdd = 0.1;
		if (i == 2)
			pointsToAdd = 0.2;

		double gainedPoints = currentPoints + pointsToAdd;
		if (Utils.checkDoubleFormat(maxPoints)) {
			if (gainedPoints > Double.parseDouble(maxPoints)) {
				gainedPoints = Double.parseDouble(maxPoints);
				actualPoints = Utils.round(Double.parseDouble(maxPoints) - currentPoints);
			} else
				actualPoints = Utils.round(pointsToAdd);
		}

		return Utils.round(gainedPoints);
	}

	public static String manageCalculationMore(double currentPoints, String maxPoints, int i) {

		actualPoints = "0.0";

		double pointsToAdd = 0.0;
		if (i == 0)
			pointsToAdd = 0.2;
		if (i == 2)
			pointsToAdd = 0.5;

		double gainedPoints = currentPoints + pointsToAdd;
		if (Utils.checkDoubleFormat(maxPoints)) {
			if (gainedPoints > Double.parseDouble(maxPoints)) {
				gainedPoints = Double.parseDouble(maxPoints);
				actualPoints = Utils.round(Double.parseDouble(maxPoints) - currentPoints);
			} else
				actualPoints = Utils.round(pointsToAdd);
		}

		return Utils.round(gainedPoints);
	}

	public static String implCalculationManager(double currentPoints) {

		actualPoints = "0.0";

		String result = Double.toString(currentPoints);
		String amountString = (String) JOptionPane.showInputDialog(null, "Podaj dok³adn¹ kwotê:",
				"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (Utils.checkIntFormat(amountString)) {
			int amount = Integer.parseInt(amountString.trim());
			int points = amount / 10000;
			result = Utils.round(currentPoints + (points * 0.5));
			actualPoints = Utils.round(points * 0.5);
		} else
			JOptionPane.showMessageDialog(null, "Niepoprawny format podanej kwoty", "Niepoprawy format",
					JOptionPane.PLAIN_MESSAGE, null);
		return result;
	}

	public static String implCalculationPerformer(double currentPoints) {

		actualPoints = "0.0";

		String result = Double.toString(currentPoints);
		String quantityString = (String) JOptionPane.showInputDialog(null, "Podaj iloœæ wykonawców:",
				"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);
		String amountString = (String) JOptionPane.showInputDialog(null, "Podaj dok³adn¹ kwotê:",
				"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (Utils.checkIntFormat(quantityString) && Utils.checkIntFormat(amountString)) {
			int quantity = Integer.parseInt(quantityString);
			int amount = Integer.parseInt(amountString);
			int points = amount / 10000;
			result = Utils.round(currentPoints + (points * (0.5 / quantity)));
			actualPoints = Utils.round(points * (0.5 / quantity));
		} else
			JOptionPane.showMessageDialog(null, "Niepoprawny format podanych danych", "Niepoprawy format",
					JOptionPane.PLAIN_MESSAGE, null);
		return result;
	}

	public static String patternCalculation(double currentPoints, String maxPoints) {

		actualPoints = "0.0";

		String result = Double.toString(currentPoints);
		String hoursString = (String) JOptionPane.showInputDialog(null, "Podaj liczbê godzin zajêæ w roku:",
				"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);
		String pensumString = (String) JOptionPane.showInputDialog(null, "Podaj liczbê godzin pensum w roku:",
				"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);

		if (Utils.checkDoubleFormat(hoursString) && Utils.checkDoubleFormat(pensumString)) {
			double hours = Double.parseDouble(hoursString);
			double pensum = Double.parseDouble(pensumString);
			double pointsToAdd = 0.5 * (hours / pensum);
			double gainedPoints = currentPoints + pointsToAdd;
			if (gainedPoints > Double.parseDouble(maxPoints)) {
				gainedPoints = Double.parseDouble(maxPoints);
				result = Utils.round(gainedPoints);
				actualPoints = Utils.round(Double.parseDouble(maxPoints) - currentPoints);
			} else
				actualPoints = Utils.round(pointsToAdd);
		} else
			JOptionPane.showMessageDialog(null, "Niepoprawny format podanych danych", "Niepoprawy format",
					JOptionPane.PLAIN_MESSAGE, null);

		return result;
	}

	public static String publCalculation(double currentPoints, int pointsToAdd) {

		actualPoints = "0.0";

		String result = Double.toString(currentPoints);
		int numberOfTeamMembers;
		String numberOfTeamMembersString = (String) JOptionPane.showInputDialog(null,
				"Podaj iloœæ autorów publikacji z Instytutu:", "Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null,
				null, null);

		if (!numberOfTeamMembersString.equals("")) {

			if (Utils.checkIntFormat(numberOfTeamMembersString)) {
				numberOfTeamMembers = Integer.parseInt(numberOfTeamMembersString.trim());
				result = Utils.round(currentPoints + (pointsToAdd / numberOfTeamMembers));
				actualPoints = Utils.round(pointsToAdd / numberOfTeamMembers);
			} else
				JOptionPane.showMessageDialog(null, "Niepoprawny format podanych danych", "Niepoprawy format",
						JOptionPane.PLAIN_MESSAGE, null);
		}
		return result;
	}

}
