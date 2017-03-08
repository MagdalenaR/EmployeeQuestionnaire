package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Calculations;
import model.AchievementsCategory;
import model.SpecificAchievement;

public class EditWindowRegular extends EditWindow {

	private static final long serialVersionUID = 1L;

	public EditWindowRegular(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt,
			boolean isNumberOfTeamMembersNeeded) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addAchievements(isNumberOfTeamMembersNeeded, nameOfAchievement);

	}

	private void addAchievements(boolean isNumberOfTeamMembersNeeded, String nameOfAchievement) {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 126, 755, 185);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		if (!achievement.getSpecyficAchievementsList().isEmpty())
			fillPanel(achievement.getSpecyficAchievementsList());

		JButton btnAddTitles = new JButton("Dodaj");
		btnAddTitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String achievementFromUser = (String) JOptionPane.showInputDialog(null, "Podaj nazwę:",
						"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);

				if (achievementFromUser == null)
					achievementFromUser = "";
				if (!achievementFromUser.equals("")) {

					lblSetGainedPoints.setText(Calculations.ordinaryCalculation(isNumberOfTeamMembersNeeded,
							Double.parseDouble(lblSetGainedPoints.getText()),
							Double.parseDouble(achievement.getPoints()), achievement.getMaxPoints()));

					achievementsFromUserList
							.add(new SpecificAchievement(achievementFromUser, Calculations.actualPoints));

					fillPanel(achievementsFromUserList);
				}
			}
		});
		btnAddTitles.setBounds(12, 93, 103, 25);
		getContentPane().add(btnAddTitles);
	}

}