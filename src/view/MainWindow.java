package view;

import javax.swing.JFrame;

import javax.swing.JTable;

import java.awt.BorderLayout;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable table1;

	@SuppressWarnings("serial")
	public MainWindow(Object[][] objects) {

		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);

		table1 = new JTable();
		table1.setModel(new DefaultTableModel(objects, new String[] { "Short name", "Name", "Points" }) {

			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		
		table1.getColumnModel().getColumn(0).setPreferredWidth(50);
		table1.getColumnModel().getColumn(1).setPreferredWidth(600);
		table1.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		panel.add(table1);

		setSize(700, 500);
		setVisible(true);
	}

}
