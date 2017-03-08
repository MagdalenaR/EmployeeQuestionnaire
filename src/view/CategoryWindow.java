package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import controller.Utils;
import model.AchievementsCategory;

public class CategoryWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<JRadioButton> rdbtnList = new ArrayList<JRadioButton>();
	private List<JLabel> lblList = new ArrayList<JLabel>();
	private EditWindowFactory editWindowFactory = new EditWindowFactory();
	private CategoryWindow categoryWindow;

	public CategoryWindow(AchievementsCategory achievementsCategory, MainWindow mainWindow, JLabel lbl) {

		categoryWindow = this;
		createWindow(mainWindow, achievementsCategory);
		addLabels(achievementsCategory);
		addPanel(achievementsCategory);
		addButtons(mainWindow, achievementsCategory, lbl);

	}

	private void createWindow(MainWindow mainWindow, AchievementsCategory achievementsCategory) {

		setSize(799, 590);
		setResizable(false);
		setTitle(achievementsCategory.getName());
		getContentPane().setLayout(null);
		setLocation(new Point(400, 200));

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {
				categoryWindow.setVisible(false);
				mainWindow.setVisible(true);
			}
		});

	}

	private void addButtons(MainWindow mainWindow, AchievementsCategory achievementsCategory, JLabel lbl) {

		JButton btnMainWindow = new JButton("Strona g\u0142\u00F3wna");
		btnMainWindow.setBounds(334, 517, 120, 25);
		getContentPane().add(btnMainWindow);

		btnMainWindow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryWindow.setVisible(false);
				sumPoints(achievementsCategory, lbl);
				mainWindow.setVisible(true);
			}
		});

		JButton btnPrevious = new JButton("Wstecz");
		btnPrevious.setBounds(12, 517, 100, 25);
		getContentPane().add(btnPrevious);

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryWindow.setVisible(false);
				sumPoints(achievementsCategory, lbl);
				if (mainWindow.getNumberOfWindow() == 0)
					mainWindow.setNumberOfWindow(11);
				else
					mainWindow.setNumberOfWindow(mainWindow.getNumberOfWindow() - 1);
				mainWindow.openWindow(mainWindow.getNumberOfWindow());
			}
		});

		JButton btnNext = new JButton("Dalej");
		btnNext.setBounds(681, 517, 100, 25);
		getContentPane().add(btnNext);

		btnNext.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				categoryWindow.setVisible(false);
				sumPoints(achievementsCategory, lbl);
				if (mainWindow.getNumberOfWindow() == 11)
					mainWindow.setNumberOfWindow(0);
				else
					mainWindow.setNumberOfWindow(mainWindow.getNumberOfWindow() + 1);
				mainWindow.openWindow(mainWindow.getNumberOfWindow());
			}
		});
	}

	private void addLabels(AchievementsCategory achievementsCategory) {

		JLabel lblAchievementCategory = new JLabel(achievementsCategory.getName());
		lblAchievementCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAchievementCategory.setBounds(66, 13, 704, 22);
		getContentPane().add(lblAchievementCategory);

		JLabel lblDescription = new JLabel(achievementsCategory.getDescription());
		lblDescription.setVerticalAlignment(SwingConstants.TOP);
		lblDescription.setBounds(68, 40, 545, 47);
		getContentPane().add(lblDescription);

		JLabel lblPoints = new JLabel("Punkty");
		lblPoints.setBounds(685, 64, 44, 16);
		getContentPane().add(lblPoints);

		JLabel lblPoints2 = new JLabel("zdobyte / max");
		lblPoints2.setBounds(665, 81, 81, 16);
		getContentPane().add(lblPoints2);
	}

	private void addPanel(AchievementsCategory achievementsCategory) {

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(730, 65));
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(12, 110, 769, 385);
		getContentPane().add(scrollPane);

		Utils.addPicture(getContentPane());

		int j = 20;
		for (int i = 0; i < achievementsCategory.getAchievementsList().size(); i++) {

			JLabel lblPnt = new JLabel(achievementsCategory.getAchievementsList().get(i).getGainedPoints() + " ");
			lblPnt.setBounds(670, j, 27, 16);
			lblPnt.setHorizontalAlignment(SwingConstants.RIGHT);
			panel.add(lblPnt);
			lblList.add(lblPnt);

			if (!achievementsCategory.getAchievementsList().get(i).getMaxPoints().equals("")) {
				JLabel lblSlash = new JLabel("/");
				lblSlash.setBounds(698, j, 5, 16);
				panel.add(lblSlash);
			}

			JLabel lblPntMax = new JLabel(achievementsCategory.getAchievementsList().get(i).getMaxPoints());
			lblPntMax.setBounds(705, j, 25, 16);
			panel.add(lblPntMax);

			JRadioButton rdbtn = new JRadioButton(achievementsCategory.getAchievementsList().get(i).getName());
			if (achievementsCategory.getAchievementsList().get(i).getGainedPoints().equals("0.0"))
				rdbtn.setSelected(false);

			rdbtn.setBounds(20, j, 640, 25);
			panel.add(rdbtn);
			rdbtnList.add(rdbtn);
			rdbtnList.get(i).addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (e.getSource() == rdbtn) {
						if (rdbtn.isSelected()) {
							editWindowFactory.recognizeEditWindow(achievementsCategory.getShortName(), rdbtn.getText(),
									achievementsCategory, lblPnt);
						} else {
							lblPnt.setText("0.0");
						}
						rdbtn.setSelected(false);
					}
				}
			});
			j += 40;
		}
	}

	private void sumPoints(AchievementsCategory achievementsCategory, JLabel lbl) {
		double sum = 0.0;
		for (int i = 0; i < lblList.size(); i++) {
			sum += Double.parseDouble(lblList.get(i).getText());
		}
		achievementsCategory.setPoints(Double.toString(sum));
		lbl.setText(Double.toString(sum));
	}

}
