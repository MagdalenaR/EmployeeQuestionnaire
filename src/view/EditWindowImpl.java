package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Calculations;
import controller.Utils;
import model.AchievementsCategory;
import model.SpecificAchievement;

public class EditWindowImpl extends EditWindow {

	private static final long serialVersionUID = 1L;

	public EditWindowImpl(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addAchievements(achievementsCategory);
	}

	private void addAchievements(AchievementsCategory achievementsCategory) {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(135, 125, 627, 185);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		if (!achievement.getSpecyficAchievementsList().isEmpty())
			fillPanel(achievement.getSpecyficAchievementsList());

		JButton btnAddTitles = new JButton("Dodaj");
		btnAddTitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JCheckBox chckbxManager = new JCheckBox("Kierownik");
				JCheckBox chckbxPerformer = new JCheckBox("Wykonawca");

				getContentPane().repaint();
				getContentPane().validate();

				chckbxManager.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						chckbxPerformer.setSelected(false);
						if (Utils.checkDoubleFormat(lblSetGainedPoints.getText())) {

							String achievementFromUser = JOptionPane.showInputDialog("Podaj nazwê:");
							if (achievementFromUser == null)
								achievementFromUser = "";
							if (!achievementFromUser.equals("")) {

								lblSetGainedPoints.setText(Calculations
										.implCalculationManager(Double.parseDouble(lblSetGainedPoints.getText())));
								achievementsFromUserList
										.add(new SpecificAchievement(achievementFromUser, Calculations.actualPoints));
								fillPanel(achievementsFromUserList);
							}

						} else
							JOptionPane.showMessageDialog(null, "Niepoprawny format podanych danych",
									"Niepoprawy format", JOptionPane.WARNING_MESSAGE);
						chckbxManager.setSelected(false);
					}
				});
				chckbxManager.setBounds(12, 180, 113, 25);
				getContentPane().add(chckbxManager);

				chckbxPerformer.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						chckbxManager.setSelected(false);
						if (Utils.checkDoubleFormat(lblSetGainedPoints.getText())) {

							String achievementFromUser = JOptionPane.showInputDialog("Podaj nazwê:");
							if (achievementFromUser == null)
								achievementFromUser = "";
							if (!achievementFromUser.equals("")) {

								lblSetGainedPoints.setText(Calculations
										.implCalculationPerformer(Double.parseDouble(lblSetGainedPoints.getText())));
								achievementsFromUserList
										.add(new SpecificAchievement(achievementFromUser, Calculations.actualPoints));
								fillPanel(achievementsFromUserList);
							}
						} else
							JOptionPane.showMessageDialog(null, "Niepoprawny format podanych danych",
									"Niepoprawy format", JOptionPane.WARNING_MESSAGE);
						chckbxPerformer.setSelected(false);
					}
				});
				chckbxPerformer.setBounds(12, 200, 113, 25);
				getContentPane().add(chckbxPerformer);

			}
		});
		btnAddTitles.setBounds(15, 125, 103, 25);
		getContentPane().add(btnAddTitles);
	}

}
