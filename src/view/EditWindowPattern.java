package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Utils;
import model.AchievementsCategory;

public class EditWindowPattern extends EditWindow {

	private static final long serialVersionUID = 1L;

	private List<JLabel> lblList = new ArrayList<JLabel>();
	private List<String> achievementsFromUserList = new ArrayList<String>();
	private double hours, pensum;
	
	public EditWindowPattern(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addAchievements();
	}
	
	public void addAchievements() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 126, 755, 111);
		getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JButton btnAddTitles = new JButton("Dodaj");
		btnAddTitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String achievementFromUser = JOptionPane.showInputDialog("Podaj nazwê:");
				achievementsFromUserList.add(achievementFromUser);
				hours = Utils.checkDoubleFormat(JOptionPane.showInputDialog("Podaj liczbê godzin zajêæ w roku:"));
				pensum = Utils.checkDoubleFormat(JOptionPane.showInputDialog("Podaj liczbê godzin pensum w roku:"));

				int j = 10;
				for (int i = 0; i < achievementsFromUserList.size(); i++) {
					JLabel lbl = new JLabel(achievementsFromUserList.get(i));
					lbl.setBounds(12, j, 700, 22);
					panel.add(lbl);
					lblList.add(lbl);
					j += 25;
				}
				panel.setPreferredSize(new Dimension(745, achievementsFromUserList.size() * 35));
				repaint();
				revalidate();
			}
		});
		btnAddTitles.setBounds(12, 93, 103, 25);
		getContentPane().add(btnAddTitles);
	}

}
