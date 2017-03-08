package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Calculations;
import model.AchievementsCategory;
import model.SpecificAchievement;

public class EditWindowManage extends EditWindow {

	private static final long serialVersionUID = 1L;

	private List<String> nameOfLabelsList = fillNameOfLabels();

	public EditWindowManage(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt, int i) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addAchievements(i);
	}

	private void addAchievements(int i) {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(9, 182, 373 + 373 + 21, 128);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JLabel lblEng = new JLabel(nameOfLabelsList.get(i));
		lblEng.setBounds(115, 127, 267, 44);
		getContentPane().add(lblEng);

		if (!achievement.getSpecyficAchievementsList().isEmpty())
			fillPanel(achievement.getSpecyficAchievementsList());

		JButton btnAddEng = new JButton("Dodaj");
		btnAddEng.setBounds(12, 138, 97, 25);
		getContentPane().add(btnAddEng);
		btnAddEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String achievementFromUser = (String) JOptionPane.showInputDialog(null, "Podaj nazwê:",
						"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);

				if (achievementFromUser == null)
					achievementFromUser = "";
				if (!achievementFromUser.equals("")) {

					lblSetGainedPoints.setText(Calculations.manageCalculationLess(
							Double.parseDouble(lblSetGainedPoints.getText()), achievement.getMaxPoints(), i));
					achievementsFromUserList.add(new SpecificAchievement(achievementFromUser, Calculations.actualPoints));
					fillPanel(achievementsFromUserList);
				}
			}
		});

		JLabel lblMas = new JLabel(nameOfLabelsList.get(i + 1));
		lblMas.setBounds(500, 128, 267, 44);
		getContentPane().add(lblMas);

		if (!achievement.getSpecyficAchievementsList().isEmpty())
			fillPanel(achievement.getSpecyficAchievementsList());

		JButton btnAddMas = new JButton("Dodaj");
		btnAddMas.setBounds(394, 138, 97, 25);
		getContentPane().add(btnAddMas);
		btnAddMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String achievementFromUser = (String) JOptionPane.showInputDialog(null, "Podaj nazwê:",
						"Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null, null, null);

				if (achievementFromUser == null)
					achievementFromUser = "";
				if (!achievementFromUser.equals("")) {

					lblSetGainedPoints.setText(Calculations.manageCalculationMore(
							Double.parseDouble(lblSetGainedPoints.getText()), achievement.getMaxPoints(), i));
					achievementsFromUserList.add(new SpecificAchievement(achievementFromUser, Calculations.actualPoints));
					fillPanel(achievementsFromUserList);
				}
			}
		});

	}

	private List<String> fillNameOfLabels() {
		List<String> nameOfLabelsList = new ArrayList<String>();
		nameOfLabelsList.add("<html>Praca in¿ynierska lub licencjacka</html>");
		nameOfLabelsList.add("<html>Praca magisterska</html>");
		nameOfLabelsList.add("<html>Ukoñczenie kursu doskonal¹cego umiejêtnoœci dydaktyczne, zawodowe lub jêzykowe");
		nameOfLabelsList.add("<html>Ukoñczenie studiów podyplomowych</html>");

		return nameOfLabelsList;
	}

}
