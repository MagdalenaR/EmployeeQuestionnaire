package view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import controller.Utils;
import model.AchievementsCategory;

public class EditWindowImpl extends EditWindow {
	
	private static final long serialVersionUID = 1L;
	private List<JLabel> lblList = new ArrayList<JLabel>();
	private List<String> achievementsFromUserList = new ArrayList<String>();
	
	public EditWindowImpl(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addAchievements();
	}

	public void addAchievements() {

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(147, 126, 620, 111);
		getContentPane().add(scrollPane);

		JPanel panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		//po przyznaniu punktow trzeba czyscic checkBozy
		JButton btnAddTitles = new JButton("Dodaj");
		btnAddTitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				JCheckBox chckbxKierownik = new JCheckBox("Kierownik");
				JCheckBox chckbxWykonawca = new JCheckBox("Wykonawca");
				
				chckbxKierownik.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						chckbxWykonawca.setSelected(false);
						int amount = Utils.checkIntFormat(JOptionPane.showInputDialog("Podaj dok³adn¹ kwotê:"));
					}
				});
				chckbxKierownik.setBounds(12, 132, 113, 25);
				getContentPane().add(chckbxKierownik);
				
				chckbxWykonawca.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						chckbxKierownik.setSelected(false);
						int quantity = Utils.checkIntFormat(JOptionPane.showInputDialog("Podaj iloœæ wykonawców:"));
						int amount = Utils.checkIntFormat(JOptionPane.showInputDialog("Podaj dok³adn¹ kwotê:"));
					}
				});
				chckbxWykonawca.setBounds(12, 162, 113, 25);
				getContentPane().add(chckbxWykonawca);

				String achievementFromUser = JOptionPane.showInputDialog("Podaj nazwê:");
				achievementsFromUserList.add(achievementFromUser);

				int j = 10;
				for (int i = 0; i < achievementsFromUserList.size(); i++) {
					JLabel lbl = new JLabel(achievementsFromUserList.get(i));
					lbl.setBounds(12, j, 565, 22);
					panel.add(lbl);
					lblList.add(lbl);
					j += 25;
				}
				panel.setPreferredSize(new Dimension(610, achievementsFromUserList.size() * 35));
				repaint();
				revalidate();
			}
		});
		btnAddTitles.setBounds(12, 93, 103, 25);
		getContentPane().add(btnAddTitles);
	}

}
