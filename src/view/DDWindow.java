package view;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import java.awt.ComponentOrientation;
import java.awt.Dimension;

import javax.swing.JPanel;

public class DDWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	public DDWindow() {
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
		scrollPane.setSize(758,448);
		getContentPane().add(scrollPane);
			
		
		JRadioButton rdbtn1 = new JRadioButton("New radio button");
		rdbtn1.setBounds(28, 71, 127, 25);
		panel.add(rdbtn1);
		
		JRadioButton rdbtn2 = new JRadioButton("New radio button");
		rdbtn2.setBounds(28, 29, 127, 25);
		panel.add(rdbtn2);
		
		JRadioButton rdbtn3 = new JRadioButton("New radio button");
		rdbtn3.setBounds(28, 111, 127, 25);
		panel.add(rdbtn3);
		
		
		setVisible(true);
	}
}
