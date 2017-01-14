package view;

import javax.swing.JFrame;

import model.Achievement;
import model.AchievementsCategory;

import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;

public class EditWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private Achievement achievement = null;
	private JTextField fldNewPoints;

	public EditWindow(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {

		createWindow(nameOfAchievement, achievementsCategory);
		createLabels();
		gainAndChangePoints();
		save(newPnt);
	}

	public void createWindow(String nameOfAchievement, AchievementsCategory achievementsCategory) {

		for (int i = 0; i < achievementsCategory.getAchievementsList().size(); i++) {
			if (achievementsCategory.getAchievementsList().get(i).getName().equals(nameOfAchievement)) {
				achievement = achievementsCategory.getAchievementsList().get(i);
				break;
			}
		}

		// ustawienia okienka
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setSize(new Dimension(797, 335));
		setTitle(achievementsCategory.getName());
		setVisible(true);
		setLocation(new Point(500, 200));
	}

	public void createLabels() {

		JLabel lblAchievementsName = new JLabel(achievement.getName());
		lblAchievementsName.setHorizontalAlignment(SwingConstants.CENTER);
		lblAchievementsName.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblAchievementsName.setBounds(12, 13, 755, 16);
		getContentPane().add(lblAchievementsName);

		JLabel lblAchievementDescription = new JLabel(achievement.getDescription());
		lblAchievementDescription.setHorizontalAlignment(SwingConstants.CENTER);
		lblAchievementDescription.setBounds(12, 42, 755, 16);
		getContentPane().add(lblAchievementDescription);
	}

	public void gainAndChangePoints() {

		// add gained point
		JLabel lblGainedPoints = new JLabel("Przyznane punkty:");
		lblGainedPoints.setBounds(616, 97, 112, 16);
		getContentPane().add(lblGainedPoints);

		JLabel lblSetGainedPoints = new JLabel(achievement.getGainedPoints());
		lblSetGainedPoints.setBounds(727, 97, 40, 16);
		getContentPane().add(lblSetGainedPoints);

		// change points
		fldNewPoints = new JTextField();
		fldNewPoints.setText("0.0");
		fldNewPoints.setBounds(366, 93, 40, 25);
		getContentPane().add(fldNewPoints);
		fldNewPoints.setColumns(10);

		JLabel lblNewPoints = new JLabel("Zmie\u0144 ilo\u015B\u0107 punkt\u00F3w:");
		lblNewPoints.setBounds(243, 93, 126, 25);
		getContentPane().add(lblNewPoints);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(407, 93, 56, 25);
		getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSetGainedPoints.setText(fldNewPoints.getText());
			}
		});
	}

	public void save(JLabel newPnt) {

		JButton btnSave = new JButton("Zapisz");
		btnSave.setBounds(670, 250, 97, 25);
		getContentPane().add(btnSave);	
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPnt.setText(fldNewPoints.getText());
				setVisible(false);
			}
		});
		
	}
}
