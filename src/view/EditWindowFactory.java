package view;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import model.AchievementsCategory;

public class EditWindowFactory {

	public EditWindow recognizeEditWindow(String category, String name, AchievementsCategory achievementsCategory,
			JLabel lblPnt) {

		if (category.equals("ORG") || category.equals("NB5") || category.equals("NB4a") || category.equals("NB9")) {
			return new EditWindowRegular(name, achievementsCategory, lblPnt, false);
		}
		if (category.equals("NB2") || category.equals("NB3") || category.equals("NB6") || category.equals("NB8")) {
			return new EditWindowRegular(name, achievementsCategory, lblPnt, true);
		}
		if (category.equals("NB7")) {
			return new EditWindowImpl(name, achievementsCategory, lblPnt);
		}
		if (category.equals("NB4")) {
			if (name.equals(achievementsCategory.getAchievementsList().get(0).getName())) {
				SwingUtilities.invokeLater(new Runnable() {
					public void run() {
						new EditWindowPublication(name, achievementsCategory, lblPnt);
					}
				});
			} else
				return new EditWindowRegular(name, achievementsCategory, lblPnt, false);
		}
		if (category.equals("NB1")) {
			if (name.equals(achievementsCategory.getAchievementsList().get(0).getName())) {
				return new EditWindowTitle(name, achievementsCategory, lblPnt);
			} else
				return new EditWindowTitle(name, achievementsCategory, lblPnt);
		}
		if (category.equals("DD")) {
			if (name.equals(achievementsCategory.getAchievementsList().get(9).getName())) {
				return new EditWindowManage(name, achievementsCategory, lblPnt, 0);
			}
			if (name.equals(achievementsCategory.getAchievementsList().get(10).getName())
					|| name.equals(achievementsCategory.getAchievementsList().get(11).getName())) {
				return new EditWindowPattern(name, achievementsCategory, lblPnt);
			}
			if (name.equals(achievementsCategory.getAchievementsList().get(12).getName())) {
				return new EditWindowManage(name, achievementsCategory, lblPnt, 2);
			} else
				return new EditWindowRegular(name, achievementsCategory, lblPnt, false);
		} else
			return null;
	}

}
