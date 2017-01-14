package view;

import model.AchievementsCategory;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;

public class EditWindowManage extends EditWindow {

	private static final long serialVersionUID = 1L;

	private List<JLabel> lblEngList = new ArrayList<JLabel>();
	private List<JLabel> lblMasList = new ArrayList<JLabel>();
	private List<String> achievementsFromUserEngList = new ArrayList<String>();
	private List<String> achievementsFromUserMasList = new ArrayList<String>();
	private String[][] tmp = new String[][] { { "<html>Praca in¿ynierska<br>lub licencjacka</html>", "0.1" },
			{ "<html>Praca magisterska</html>", "0.2" },
			{ "<html>Ukoñczenie studiów<br>podyplomowych</html>", "0.5" },
			{ "<html>Ukoñczenie kursu<br>doskonal¹cego umiejêtnoœci<br>dydaktyczne, zawodowe<br>lub jêzykowe",
					"0.2" } };

	public EditWindowManage(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt, int i) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		addAchievements(i);
	}

	public void addAchievements(int i) {

		// engineer
		JLabel lblEng = new JLabel(tmp[i][0]);
		lblEng.setBounds(12, 143, 106, 16);
		getContentPane().add(lblEng);

		JScrollPane scrollPaneEng = new JScrollPane();
		scrollPaneEng.setBounds(228, 126, 539, 50);
		getContentPane().add(scrollPaneEng);

		JPanel panelEng = new JPanel();
		scrollPaneEng.setViewportView(panelEng);
		panelEng.setLayout(null);

		JButton btnAddEng = new JButton("Dodaj");
		btnAddEng.setBounds(130, 139, 75, 25);
		getContentPane().add(btnAddEng);
		btnAddEng.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String achievementFromUser = JOptionPane.showInputDialog("Podaj nazwê:");
				achievementsFromUserEngList.add(achievementFromUser);

				int j = 10;
				for (int i = 0; i < achievementsFromUserEngList.size(); i++) {
					JLabel lbl = new JLabel(achievementsFromUserEngList.get(i));
					lbl.setBounds(12, j, 700, 22);
					panelEng.add(lbl);
					lblEngList.add(lbl);
					j += 25;
				}
				panelEng.setPreferredSize(new Dimension(745, achievementsFromUserEngList.size() * 35));
				repaint();
				revalidate();
			}
		});

		// master
		JLabel lblMas = new JLabel(tmp[i+1][0]);
		lblMas.setBounds(12, 204, 106, 16);
		getContentPane().add(lblMas);

		JScrollPane scrollPaneMas = new JScrollPane();
		scrollPaneMas.setBounds(228, 188, 539, 49);
		getContentPane().add(scrollPaneMas);

		JPanel panelMas = new JPanel();
		scrollPaneMas.setViewportView(panelMas);
		panelMas.setLayout(null);

		JButton btnAddMas = new JButton("Dodaj");
		btnAddMas.setBounds(130, 200, 75, 25);
		getContentPane().add(btnAddMas);
		btnAddMas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String achievementFromUser = JOptionPane.showInputDialog("Podaj nazwê:");
				achievementsFromUserMasList.add(achievementFromUser);

				int j = 10;
				for (int i = 0; i < achievementsFromUserMasList.size(); i++) {
					JLabel lbl = new JLabel(achievementsFromUserMasList.get(i));
					lbl.setBounds(12, j, 700, 22);
					panelMas.add(lbl);
					lblMasList.add(lbl);
					j += 25;
				}
				panelMas.setPreferredSize(new Dimension(745, achievementsFromUserMasList.size() * 35));
				repaint();
				revalidate();
			}
		});

	}

}
