package view;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.SystemColor;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import controller.GeneratePdfDocument;
import controller.Utils;
import model.AchievementsCategory;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private List<AchievementsCategory> achievementsCategory;
	private Object[][] objects;
	private List<Window> windows;
	private int numberOfWindow;
	private MainWindow mainWindow = this;
	private List<JLabel> lblList = new ArrayList<JLabel>();
	private GeneratePdfDocument pdfDocument = new GeneratePdfDocument();
	private JPanel panel;

	public MainWindow(String filename, String nameOfEmployee, String yearOfQuestionnaire) {

		achievementsCategory = Utils.readDataFromJsonFile(filename);
		objects = Utils.listToArray(achievementsCategory);
				
		createWindow();
		createPanel();
		createLabels(nameOfEmployee, yearOfQuestionnaire);
		createTable();
		
		windows = createWindowsList();
		
		createButtons(nameOfEmployee, yearOfQuestionnaire);
	}

	private void createWindow() {
		
		setSize(800, 450);
		confirmCloseOperation();
		setResizable(false);
		setVisible(true);
		setLocation(new Point(200, 100));
		setTitle("Punktator");
	}

	private void createPanel() {

		panel = new JPanel();
		panel.setBackground(SystemColor.control);
		panel.setPreferredSize(new Dimension(550, 412));
		panel.setLayout(null);
		getContentPane().add(panel, BorderLayout.NORTH);
		Utils.addPicture(panel);
	}

	private void createLabels(String nameOfEmployee, String yearOfQuestionnaire) {

		JLabel lblUser = new JLabel("U¿ytkownik: " + nameOfEmployee);
		lblUser.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUser.setBounds(418, 11, 352, 14);
		panel.add(lblUser);

		JLabel lblName = new JLabel("Ankieta za rok: " + yearOfQuestionnaire);
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblName.setBounds(418, 33, 352, 14);
		panel.add(lblName);

		JLabel lblCategoryOf = new JLabel("Wybierz kategori\u0119:");
		lblCategoryOf.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoryOf.setBounds(66, 48, 160, 27);
		panel.add(lblCategoryOf);

		JLabel lblGotPoints = new JLabel("Zdobyte punkty");
		lblGotPoints.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblGotPoints.setBounds(668, 80, 102, 27);
		panel.add(lblGotPoints);

		int j = 114;
		for (int i = 0; i < objects.length; i++) {
			JLabel lblNewLabel = new JLabel(achievementsCategory.get(i).getPoints());
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(668, j, 89, 16);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblList.add(lblNewLabel);
			panel.add(lblNewLabel);
			j += 16;
		}
	}

	private void createTable() {

		JTable table1 = new JTable() {

			private static final long serialVersionUID = 1L;

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
		table1.setBounds(12, 114, 655, 192);

		table1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				numberOfWindow = table1.rowAtPoint(e.getPoint());
				openWindow(numberOfWindow);
			}
		});

		table1.setModel(new DefaultTableModel(objects, new String[] { "ID", "Osi¹gniêcie" }) {

			private static final long serialVersionUID = 1L;

			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		table1.getColumnModel().getColumn(0).setPreferredWidth(57);
		table1.getColumnModel().getColumn(1).setPreferredWidth(600);
		panel.add(table1);
	}

	private void createButtons(String nameOfEmployee, String yearOfQuestionnaire) {

		JButton btnSaveToFile = new JButton("Zapisz raport do pliku");
		btnSaveToFile.setBounds(602, 375, 180, 25);
		panel.add(btnSaveToFile);
		btnSaveToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				Object[] options = { "Skrócony", "Pe³ny", "Wyjœcie" };
				int n = JOptionPane.showOptionDialog(getContentPane(), "Jaki raport chcesz wygenerowaæ?", "Raport",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[2]);

				switch (n) {
				case 0: 
					generateReport(nameOfEmployee, yearOfQuestionnaire, "RaportSkrócony");
					break;
				case 1: 
					generateReport(nameOfEmployee, yearOfQuestionnaire, "RaportPe³ny");
					break;
				}

			}
		});

		JButton btnShowReport = new JButton("Wy\u015Bwietl raport");
		btnShowReport.setBounds(430, 375, 160, 25);
		panel.add(btnShowReport);

		btnShowReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				generateReport(nameOfEmployee, yearOfQuestionnaire, "Podgl¹dRaportu");
			}
		});

		JButton btnPrevious = new JButton("Wstecz");
		btnPrevious.setBounds(12, 376, 89, 23);
		panel.add(btnPrevious);

		btnPrevious.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainWindow.setVisible(false);
				new StartWindow();
			}
		});

	}

	private void generateReport(String nameOfEmployee, String yearOfQuestionnaire, String reportType) {

		String fileName = nameOfEmployee + "_" + yearOfQuestionnaire + "_" + reportType;
		try {
			pdfDocument.generateShortReport(fileName);
			Desktop.getDesktop().open(new File("reports\\" + fileName + ".pdf"));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void confirmCloseOperation() {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt) {

				Object[] options = { "Tak", "Nie" };
				int n = JOptionPane.showOptionDialog(getContentPane(),
						"Niezapisane dane zostan¹ utracone. Czy na pewno chcesz wyjœæ?", "Punktator",
						JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

				switch (n) {
				case 0:
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					break;

				}
			}
		});
	}

	public void openWindow(int i) {
		windows.get(i).setVisible(true);
		mainWindow.setVisible(false);
	}

	public List<Window> createWindowsList() {

		List<Window> windows = new ArrayList<Window>();

		for (int i = 0; i < achievementsCategory.size(); i++) {
			windows.add(new CategoryWindow(achievementsCategory.get(i), this, lblList.get(i)));
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
