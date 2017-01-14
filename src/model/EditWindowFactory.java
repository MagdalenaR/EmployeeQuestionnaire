package model;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import controller.Utils;
import view.EditWindow;
import view.EditWindowImpl;
import view.EditWindowManage;
import view.EditWindowOrdinary;
import view.EditWindowPattern;

public class EditWindowFactory {

	public EditWindow recognizeEditWindow(String category, String name, AchievementsCategory achievementsCategory,
			JLabel lblPnt) {

		if (category.equals("ORG") || category.equals("NB1") || category.equals("NB4") || category.equals("NB5")
				|| category.equals("NB4a") || category.equals("NB9")) {
			return new EditWindowOrdinary(name, achievementsCategory, lblPnt, 1);
		}
		if (category.equals("NB2") || category.equals("NB3") || category.equals("NB6") || category.equals("NB8")) {
			return new EditWindowOrdinary(name, achievementsCategory, lblPnt,
					Utils.checkIntFormat(JOptionPane.showInputDialog("Podaj iloœæ cz³onków zespo³u:")));
		}
		if (category.equals("DD")) {
			if (name.equals(achievementsCategory.getAchievementsList().get(9).getName())) {
				return new EditWindowManage(name, achievementsCategory, lblPnt, 0);
			}
			if (name.equals(achievementsCategory.getAchievementsList().get(10).getName())
					&& name.equals(achievementsCategory.getAchievementsList().get(11).getName())) {
				return new EditWindowPattern(name, achievementsCategory, lblPnt);
			}
			if (name.equals(achievementsCategory.getAchievementsList().get(12).getName())) {
				return new EditWindowManage(name, achievementsCategory, lblPnt, 1);
			} else
				return new EditWindowOrdinary(name, achievementsCategory, lblPnt, 1);
		}
		if (category.equals("NB7")) {
			return new EditWindowImpl(name, achievementsCategory, lblPnt);
		} else
			return null;
	}

}
