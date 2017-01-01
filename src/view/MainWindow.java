package view;

import javax.swing.JComponent;
import javax.swing.JFrame;

import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import controller.Utils;
import model.Achievement;

import java.awt.event.MouseAdapter;
import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainWindow extends JFrame {

	public static final List<Achievement> resultList = new ArrayList<Achievement>();
	private static final long serialVersionUID = 1L;
	private JTable table1;

	@SuppressWarnings("serial")
	public MainWindow(Object[][] objects) {

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

		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				java.awt.Point p = e.getPoint();
				int rowIndex = table1.rowAtPoint(p);
				if (rowIndex == 0) {
					new DDWindow(Utils.readDataFromJsonFile("educationalAchievements.json"));
				}
			}
		});

		table1.setModel(new DefaultTableModel(objects, new String[] { "ID", "Osi¹gniêcie", "Punkty" }) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table1.getColumnModel().getColumn(0).setPreferredWidth(30);
		table1.getColumnModel().getColumn(1).setPreferredWidth(450);
		table1.getColumnModel().getColumn(2).setPreferredWidth(30);

		JScrollPane pane = new JScrollPane(table1);
		getContentPane().add(pane, BorderLayout.CENTER);
		
		JButton btnZapisz = new JButton("Zapisz");
		btnZapisz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Utils.writeDataToJsonFile(resultList);
			}
		});
		pane.setRowHeaderView(btnZapisz);

		setSize(600, 400);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
		setVisible(true);
	}

}
