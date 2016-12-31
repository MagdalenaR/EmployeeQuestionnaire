package view;

import model.Achievement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DDWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public DDWindow(List<Achievement> achievements) {
		getContentPane().setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);

		setSize(800, 600);
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

		scrollPane.setBounds(12, 92, 300, 448);
		scrollPane.setSize(758, 448);
		getContentPane().add(scrollPane);

		List<JRadioButton> rdbtnList = new ArrayList<JRadioButton>();
		int j = 30;
		for (int i = 0; i < achievements.size(); i++) {
			JRadioButton rdbtn = new JRadioButton(achievements.get(i).getName());
			rdbtn.setBounds(30, j, 500, 25);
			panel.add(rdbtn);
			rdbtnList.add(rdbtn);
			j += 40;
		}

		rdbtnList.get(0).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});

		setVisible(true);
	}

}
