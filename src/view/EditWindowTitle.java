package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import model.AchievementsCategory;
import model.SpecificAchievement;

public class EditWindowTitle extends EditWindow {

	private static final long serialVersionUID = 1L;

	public EditWindowTitle(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addPoints(newPnt, nameOfAchievement);
	}

	private void addPoints(JLabel newPnt, String nameOfAchievement) {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 126, 755, 185);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		if (achievementsFromUserList.isEmpty()) {
			JButton btnAddTitles = new JButton("Dodaj");
			btnAddTitles.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					lblSetGainedPoints.setText(achievement.getPoints());
					achievementsFromUserList
							.add(new SpecificAchievement(achievement.getName(), achievement.getPoints()));
					fillPanel(achievementsFromUserList);
					btnAddTitles.setVisible(false);

				}
			});
			btnAddTitles.setBounds(12, 93, 103, 25);
			getContentPane().add(btnAddTitles);
		}
	}

}
