package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import model.AchievementsCategory;
import model.EditWindowFactory;

public class CategoryWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	List<JRadioButton> rdbtnList = new ArrayList<JRadioButton>();
	List<JLabel> lblList = new ArrayList<JLabel>();
	List<JButton> btnList = new ArrayList<JButton>();
	CategoryWindow categoryWindow;
	EditWindowFactory editWindowFactory = new EditWindowFactory();

	public CategoryWindow(AchievementsCategory achievementsCategory, MainWindow mainWindow) {
		categoryWindow = this;
		createWindow(achievementsCategory);
		addLabels(achievementsCategory);
		addPanel(achievementsCategory);
		addButtons(mainWindow);

	}

	public void createWindow(AchievementsCategory achievementsCategory) {

		setSize(799, 590);
		setTitle(achievementsCategory.getName());
		getContentPane().setLayout(null);
		setLocation(new Point(400, 200));
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}

	public void addButtons(MainWindow mainWindow) {

		JButton btnMainWindow = new JButton("Strona g\u0142\u00F3wna");
		btnMainWindow.setBounds(337, 497, 120, 25);
		getContentPane().add(btnMainWindow);

		btnMainWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryWindow.setVisible(false);
				mainWindow.setVisible(true);
			}
		});

		JButton btnPrevious = new JButton("Wstecz");
		btnPrevious.setBounds(12, 497, 100, 25);
		getContentPane().add(btnPrevious);

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryWindow.setVisible(false);
				if (mainWindow.getNumberOfWindow() == 0)
					mainWindow.setNumberOfWindow(11);
				else
					mainWindow.setNumberOfWindow(mainWindow.getNumberOfWindow() - 1);
				mainWindow.openWindow(mainWindow.getNumberOfWindow());
			}
		});

		JButton btnNext = new JButton("Dalej");
		btnNext.setBounds(673, 497, 100, 25);
		getContentPane().add(btnNext);
		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryWindow.setVisible(false);
				if (mainWindow.getNumberOfWindow() == 11)
					mainWindow.setNumberOfWindow(0);
				else
					mainWindow.setNumberOfWindow(mainWindow.getNumberOfWindow() + 1);
				mainWindow.openWindow(mainWindow.getNumberOfWindow());
			}
		});

	}

	public void addLabels(AchievementsCategory achievementsCategory) {

		JLabel lblAchievementCategory = new JLabel(achievementsCategory.getName());
		lblAchievementCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAchievementCategory.setBounds(12, 13, 758, 22);
		getContentPane().add(lblAchievementCategory);

		JLabel lblDescription = new JLabel(achievementsCategory.getDescription());
		lblDescription.setBounds(12, 40, 601, 22);
		getContentPane().add(lblDescription);

		JLabel lblPoints = new JLabel("Punkty");
		lblPoints.setBounds(685, 64, 44, 16);
		getContentPane().add(lblPoints);

		JLabel lblPoints2 = new JLabel("zdobyte / max");
		lblPoints2.setBounds(665, 81, 81, 16);
		getContentPane().add(lblPoints2);
	}

	public void addPanel(AchievementsCategory achievementsCategory) {

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(730, ((achievementsCategory.getAchievementsList().size()) * 40) + 60));
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(12, 110, 758, 374);
		getContentPane().add(scrollPane);

		int j = 20;
		for (int i = 0; i < achievementsCategory.getAchievementsList().size(); i++) {

			// punkty zdobyte
			JLabel lblPnt = new JLabel("0.0");
			lblPnt.setBounds(675, j, 25, 16);
			panel.add(lblPnt);
			lblList.add(lblPnt);

			// ukosnik
			JLabel lblSlash = new JLabel("/");
			lblSlash.setBounds(698, j, 5, 16);
			panel.add(lblSlash);

			// punkty maksymalne
			JLabel lblPntMax = new JLabel(achievementsCategory.getAchievementsList().get(i).getPoints());
			lblPntMax.setBounds(705, j, 25, 16);
			panel.add(lblPntMax);

			// osiagniecie
			JRadioButton rdbtn = new JRadioButton(achievementsCategory.getAchievementsList().get(i).getName());
			rdbtn.setBounds(20, j, 600, 25);
			panel.add(rdbtn);
			rdbtnList.add(rdbtn);
			rdbtnList.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == rdbtn) {
						if (rdbtn.isSelected()) {
							editWindowFactory.recognizeEditWindow(achievementsCategory.getShortName(), rdbtn.getText(), achievementsCategory, lblPnt);
							 // Result.getInstance().addAchievementToResultList(rdbtn.getText(), lblPnt.getText());
						} else {
							lblPnt.setText("0.0");
							// MainWindowView.resultList.remove(rdbtn.getText());
						}
					}
				}
			});

			j += 40;
		}
	}

}
