package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Utils;
import model.Achievement;
import model.AchievementsCategory;
import model.Result;
import model.SpecificAchievement;
import javax.swing.JPanel;

public abstract class EditWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	protected Achievement achievement = null;
	protected List<SpecificAchievement> achievementsFromUserList;

	private JTextField fldNewPoints;
	protected JLabel lblSetGainedPoints;
	protected JPanel panel;
	protected List<JCheckBox> chckbxList = new ArrayList<JCheckBox>();

	public EditWindow(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {

		createWindow(nameOfAchievement, achievementsCategory);
		createLabels();
		gainAndChangePoints();
		removeAchievement();
		save(newPnt, nameOfAchievement);
	}

	public void createWindow(String nameOfAchievement, AchievementsCategory achievementsCategory) {

		for (int i = 0; i < achievementsCategory.getAchievementsList().size(); i++) {
			if (achievementsCategory.getAchievementsList().get(i).getName().equals(nameOfAchievement)) {
				achievement = achievementsCategory.getAchievementsList().get(i);
				achievementsFromUserList = achievement.getSpecyficAchievementsList();
				break;
			}
		}

		setSize(new Dimension(797, 407));
		setResizable(false);
		setTitle(achievementsCategory.getName());
		setVisible(true);
		getContentPane().setLayout(null);
		setLocation(new Point(500, 200));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	public void createLabels() {

		Utils.addPicture(getContentPane());
		
		JLabel lblAchievementsName = new JLabel(achievement.getName());
		lblAchievementsName.setHorizontalAlignment(SwingConstants.LEFT);
		lblAchievementsName.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAchievementsName.setBounds(65, 13, 702, 27);
		getContentPane().add(lblAchievementsName);

		JLabel lblAchievementDescription = new JLabel(achievement.getDescription());
		lblAchievementDescription.setVerticalAlignment(SwingConstants.TOP);
		lblAchievementDescription.setHorizontalAlignment(SwingConstants.LEFT);
		lblAchievementDescription.setBounds(68, 40, 702, 35);
		getContentPane().add(lblAchievementDescription);
	}

	public void gainAndChangePoints() {

		JLabel lblGainedPoints = new JLabel("Przyznane punkty:");
		lblGainedPoints.setBounds(613, 94, 112, 16);
		getContentPane().add(lblGainedPoints);

		lblSetGainedPoints = new JLabel(achievement.getGainedPoints());
		lblSetGainedPoints.setBounds(727, 94, 40, 16);
		getContentPane().add(lblSetGainedPoints);

		fldNewPoints = new JTextField();
		fldNewPoints.setText("0.0");
		fldNewPoints.setBounds(419, 90, 40, 25);
		getContentPane().add(fldNewPoints);
		fldNewPoints.setColumns(10);

		JLabel lblNewPoints = new JLabel("Zmie\u0144 ilo\u015B\u0107 punkt\u00F3w:");
		lblNewPoints.setBounds(295, 89, 126, 25);
		getContentPane().add(lblNewPoints);

		JButton btnOk = new JButton("Ok");
		btnOk.setBounds(460, 90, 56, 25);
		getContentPane().add(btnOk);
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lblSetGainedPoints.setText(fldNewPoints.getText());
			}
		});
	}

	public void save(JLabel newPnt, String nameOfAchievement) {

		JButton btnSave = new JButton("Zapisz");
		btnSave.setBounds(682, 334, 97, 25);
		getContentPane().add(btnSave);

		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				newPnt.setText(lblSetGainedPoints.getText() + " ");
				setVisible(false);
				Result.getInstance().addAchievementToResultList(nameOfAchievement, lblSetGainedPoints.getText());
				Result.getInstance().addSpecyficAchievByName(nameOfAchievement, achievementsFromUserList);
			}
		});

	}

	public void fillPanel(List<SpecificAchievement> list) {
		int j = 10;
		for (JCheckBox checkBox : chckbxList) {
			panel.remove(checkBox);
		}
		chckbxList = new ArrayList<JCheckBox>();
		for (int i = 0; i < list.size(); i++) {
			JCheckBox chckbx = new JCheckBox(list.get(i).getName() + "     Punkty: " + list.get(i).getPoints());
			chckbx.setBounds(12, j, 700, 22);
			panel.add(chckbx);
			chckbxList.add(chckbx);

			j += 25;
		}
		panel.setPreferredSize(new Dimension(745, list.size() * 35));
		panel.repaint();
		panel.revalidate();

	}

	public void removeAchievement() {
		JButton btnDelete = new JButton("Usu\u0144");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<JCheckBox> selectedChckbxList = new ArrayList<>();
				for (JCheckBox checkBox : chckbxList) {
					if (checkBox.isSelected()) {
						selectedChckbxList.add(checkBox);
					}
				}
				for (JCheckBox checkBox : selectedChckbxList) {
					lblSetGainedPoints.setText(Utils.round(Double.parseDouble(lblSetGainedPoints.getText())
							- Double.parseDouble(findSpecificAchievByName(checkBox.getText()).getPoints())));
					achievementsFromUserList.remove(findSpecificAchievByName(checkBox.getText()));
					panel.remove(checkBox);
					chckbxList.remove(checkBox);
					fillPanel(achievementsFromUserList);
				}
				selectedChckbxList = new ArrayList<>();
			}
		});
		btnDelete.setBounds(573, 334, 97, 25);
		getContentPane().add(btnDelete);

	}

	public SpecificAchievement findSpecificAchievByName(String name) {
		for (SpecificAchievement achiev : achievementsFromUserList) {
			if ((achiev.getName() + "     Punkty: " + achiev.getPoints()).equals(name)) {
				return achiev;
			}
		}
		return null;
	}
}
