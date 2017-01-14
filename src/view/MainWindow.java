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
import model.AchievementsCategory;

import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Window;

import javax.swing.border.LineBorder;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<AchievementsCategory> achievementsCategory = Utils.readDataFromJsonFile();
	private Object[][] objects = Utils.listToArray(achievementsCategory);
	private List<Window> windows = createWindowsList();
	private int numberOfWindow;

	public MainWindow() {

		// create panel
		JPanel panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setPreferredSize(new Dimension(550, 400));
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.NORTH);

		// create labels
		JLabel lblCategoryOf = new JLabel("Kategorie osi\u0105gni\u0119\u0107");
		lblCategoryOf.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCategoryOf.setBounds(22, 23, 160, 27);
		panel.add(lblCategoryOf);

		JLabel lblGotPoints = new JLabel("Zdobyte punkty");
		lblGotPoints.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblGotPoints.setBounds(656, 23, 102, 27);
		panel.add(lblGotPoints);

		// create table
		JTable table1 = new JTable() {
			public String getToolTipText(MouseEvent e) {
				String tip = null;
				try {
					tip = objects[rowAtPoint(e.getPoint())][4].toString();
				} catch (RuntimeException e1) {
					e1.getMessage();
				}
				return tip;
			}
		};

		table1.setShowGrid(false);
		table1.setBorder(new LineBorder(SystemColor.control));
		table1.setBackground(SystemColor.control);
		table1.setPreferredScrollableViewportSize(new Dimension(450, 450));
		table1.setBounds(22, 61, 736, 192);

		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				numberOfWindow = table1.rowAtPoint(e.getPoint());
				openWindow(numberOfWindow);
			}
		});

		table1.setModel(new DefaultTableModel(objects, new String[] { "ID", "Osi¹gniêcie", "Punkty" }) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table1.getColumnModel().getColumn(1).setPreferredWidth(600);
		table1.getColumnModel().getColumn(2).setPreferredWidth(30);
		panel.add(table1);

		// create buttons
		JButton btnSaveToFile = new JButton("Zapisz raport do pliku");
		btnSaveToFile.setBounds(578, 348, 180, 25);
		panel.add(btnSaveToFile);

		JButton btnShowReport = new JButton("Wy\u015Bwietl raport");
		btnShowReport.setBounds(22, 348, 160, 25);
		panel.add(btnShowReport);

		btnSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Utils.writeDataToJsonFile(resultList);
			}
		});

		// create window parameters
		setSize(800, 450);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
		setLocation(new Point(200, 100));
	}

	public void openWindow(int i) {
		windows.get(i).setVisible(true);
	}

	public List<Window> createWindowsList() {

		List<Window> windows = new ArrayList<Window>();

		for (int i = 0; i < achievementsCategory.size(); i++) {
			windows.add(new CategoryWindow(achievementsCategory.get(i), this));
		}
		return windows;
	}

	public int getNumberOfWindow() {
		return numberOfWindow;
	}

	public void setNumberOfWindow(int numberOfWindow) {
		this.numberOfWindow = numberOfWindow;
	}


	
}
