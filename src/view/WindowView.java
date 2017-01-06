package view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import model.Achievement;

public class WindowView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	public WindowView(List<Achievement> achievementsCategory, int i, List<Achievement> achievements, int start, int end) {
		createWindow();
		addLabels(achievementsCategory, i);
		addPanel(achievements, start, end);
		addButtons();
	}

	public void createWindow() {
		
		setSize(799, 590);
		getContentPane().setLayout(null);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
	}
	
	public void addButtons() {
		
		JButton btnMainWindow = new JButton("Strona g\u0142\u00F3wna");
		btnMainWindow.setBounds(337, 497, 120, 25);
		getContentPane().add(btnMainWindow);
		
		JButton btnPrevious = new JButton("Wstecz");
		btnPrevious.setBounds(12, 497, 100, 25);
		getContentPane().add(btnPrevious);
		
		JButton btnNext = new JButton("Dalej");
		btnNext.setBounds(673, 497, 100, 25);
		getContentPane().add(btnNext);
		
	}

	public void addLabels(List<Achievement> achievementsCategory, int i) {
		
		JLabel lblAchievementCategory = new JLabel(achievementsCategory.get(i).getName());
		lblAchievementCategory.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAchievementCategory.setBounds(259, 13, 282, 22);
		getContentPane().add(lblAchievementCategory);

		JLabel lblDescription = new JLabel(achievementsCategory.get(i).getDescription());
		lblDescription.setBounds(337, 48, 119, 16);
		getContentPane().add(lblDescription);
		
		JLabel lblPoints = new JLabel("Punkty");
		lblPoints.setBounds(681, 64, 44, 16);
		getContentPane().add(lblPoints);
		
		JLabel lblPoints2 = new JLabel("zdobyte / max");
		lblPoints2.setBounds(661, 81, 81, 16);
		getContentPane().add(lblPoints2);
	}

	public void addPanel(List<Achievement> achievements, int start, int end) {
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(730, (end-start)*44));
		panel.setLayout(null);
	
		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setBounds(12, 110, 758, 374);
		getContentPane().add(scrollPane);

		int j = 20;
		List<JRadioButton> rdbtnList = new ArrayList<JRadioButton>();
		List<JLabel> lblList = new ArrayList<JLabel>();
		List<JButton> btnList = new ArrayList<JButton>();
		
		for (int i = start; i < end+1; i++) {
			
			//osiagniecie
			JRadioButton rdbtn = new JRadioButton(achievements.get(i).getName());
			rdbtn.setBounds(20, j, 500, 25);
			panel.add(rdbtn);
			rdbtnList.add(rdbtn);
			
			//przycisk
			JButton btnEdit = new JButton();
			btnEdit.setIcon(new ImageIcon("edit.jpg"));
			btnEdit.setBounds(625, (j-5), 25, 25);
			panel.add(btnEdit);
			btnList.add(btnEdit);
			
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

		
//		Window window = new Window(achievements, this);
//		
//		rdbtnList.get(0).addActionListener(window);
//		btnList.get(0).addActionListener(window);
		
		rdbtnList.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtnList.get(0).isSelected()) {
					lblList.get(0).setText(achievements.get(0).getPoints());
					MainWindowView.resultList.add(new Achievement(rdbtnList.get(0).getText(), lblList.get(0).getText()));
				}
				else {
					lblList.get(0).setText("0.0");
					MainWindowView.resultList.remove(0);
				}
			}
		});
		
//		btnList.get(0).addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
		
	}

}
