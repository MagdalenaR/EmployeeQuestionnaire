package view;

import model.Achievement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

public class DDWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public DDWindow(List<Achievement> achievements) {

		setSize(799, 590);
		getContentPane().setLayout(null);

		JLabel lblAchievementCategory = new JLabel("Dodatkowe osi\u0105gni\u0119cia dydaktyczne");
		lblAchievementCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAchievementCategory.setBounds(259, 13, 282, 22);
		getContentPane().add(lblAchievementCategory);

		JLabel lblDescription = new JLabel("Wybierz osi¹gniêcia");
		lblDescription.setBounds(337, 48, 119, 16);
		getContentPane().add(lblDescription);

		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(730, 600));
		panel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane(panel);
		
		scrollPane.setBounds(12, 110, 758, 374);
		getContentPane().add(scrollPane);
		
		JLabel lblPoints = new JLabel("Punkty");
		lblPoints.setBounds(681, 64, 44, 16);
		getContentPane().add(lblPoints);
		
		JLabel lblPoints2 = new JLabel("zdobyte / max");
		lblPoints2.setBounds(661, 81, 81, 16);
		getContentPane().add(lblPoints2);
		
		JButton btnMainWindow = new JButton("Strona g\u0142\u00F3wna");
		btnMainWindow.setBounds(337, 497, 120, 25);
		getContentPane().add(btnMainWindow);
		
		JButton btnPrevious = new JButton("Wstecz");
		btnPrevious.setBounds(12, 497, 100, 25);
		getContentPane().add(btnPrevious);
		
		JButton btnNext = new JButton("Dalej");
		btnNext.setBounds(673, 497, 100, 25);
		getContentPane().add(btnNext);

		int j = 20;
		List<JRadioButton> rdbtnList = new ArrayList<JRadioButton>();
		List<JLabel> lblList = new ArrayList<JLabel>();
		
		for (int i = 0; i < achievements.size(); i++) {
			
			//osiagniecie
			JRadioButton rdbtn = new JRadioButton(achievements.get(i).getName());
			rdbtn.setBounds(20, j, 500, 25);
			panel.add(rdbtn);
			rdbtnList.add(rdbtn);
			
			//punkty zdobyte
			JLabel lblPnt = new JLabel("0.0");
			lblPnt.setBounds(665, j, 18, 16);
			panel.add(lblPnt);
			lblList.add(lblPnt);
			
			//ukosnik
			JLabel lblSlash = new JLabel("/");
			lblSlash.setBounds(687, j, 5, 16);
			panel.add(lblSlash);
			
			//punkty maksymalne
			JLabel lblPntMax = new JLabel(achievements.get(i).getPoints());
			lblPntMax.setBounds(695, j, 18, 16);
			panel.add(lblPntMax);
			
			j += 40;
		}

		rdbtnList.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnList.get(0).isSelected()) {
					lblList.get(0).setText(achievements.get(0).getPoints());
					MainWindow.resultList.add(new Achievement(rdbtnList.get(0).getText(), lblList.get(0).getText()));
				}
				else {
					lblList.get(0).setText("0.0");
					MainWindow.resultList.remove(0);
				}
			}
		});

		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
	}
}
