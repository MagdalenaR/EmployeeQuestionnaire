package view;

import javax.swing.JFrame;

import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import controller.Utils;
import model.Achievement;

import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class MainWindowView extends JFrame {

	public static final List<Achievement> resultList = new ArrayList<Achievement>();
	private static final long serialVersionUID = 1L;
	private JTable table1;

	@SuppressWarnings("serial")
	public MainWindowView(Object[][] objects) {

		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setPreferredSize(new Dimension(550, 400));
		panel.setLayout(null);

		table1 = new JTable() {

			// Implement table cell tool tips.
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				java.awt.Point p = e.getPoint();
				int rowIndex = rowAtPoint(p);

				try {
					tip = objects[rowIndex][4].toString();
				} catch (RuntimeException e1) {
					// catch null pointer exception if mouse is over an empty
					// line
				}
				return tip;
			}
		};
		table1.setShowGrid(false);
		table1.setBorder(new LineBorder(SystemColor.control));
		table1.setBackground(SystemColor.control);
		table1.setPreferredScrollableViewportSize(new Dimension(450, 450));
		table1.setBounds(22, 61, 736, 192);
		panel.add(table1);

		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = table1.rowAtPoint(p);
				if (rowIndex == 0) {
					new DDWindowView(Utils.readDataFromJsonFile("achievements.json"), Utils.readDataFromJsonFile("educationalAchievements.json"));
				}
				if (rowIndex == 1) {
					new NB1WindowView(Utils.readDataFromJsonFile("achievements.json"), Utils.readDataFromJsonFile("educationalAchievements.json"));
				}
			}
		});

		table1.setModel(new DefaultTableModel(objects, new String[] { "ID", "Osi¹gniêcie", "Punkty" }) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		JButton btnSaveToFile = new JButton("Zapisz raport do pliku");
		btnSaveToFile.setBounds(578, 348, 180, 25);
		panel.add(btnSaveToFile);

		JButton btnShowReport = new JButton("Wy\u015Bwietl raport");
		btnShowReport.setBounds(22, 348, 160, 25);
		panel.add(btnShowReport);

		JLabel lblCategoryOf = new JLabel("Kategorie osi\u0105gni\u0119\u0107");
		lblCategoryOf.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCategoryOf.setBounds(22, 23, 160, 27);
		panel.add(lblCategoryOf);

		JLabel lblGotPoints = new JLabel("Zdobyte punkty");
		lblGotPoints.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGotPoints.setBounds(656, 23, 102, 27);
		panel.add(lblGotPoints);
		
		btnSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utils.writeDataToJsonFile(resultList);
			}
		});

		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table1.getColumnModel().getColumn(1).setPreferredWidth(600);
		table1.getColumnModel().getColumn(2).setPreferredWidth(30);

		setSize(800, 450);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
	}
}
