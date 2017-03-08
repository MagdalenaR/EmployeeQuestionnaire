package view;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Utils;
import model.Result;

public class StartWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private StartWindow startWindow = this;

	public StartWindow() {
		addLables();
		addButtons();
		addPicture();
		createWindow();
	}

	public void createWindow() {
		setVisible(true);
		setSize(633, 300);
		setTitle("Punktator");
		setResizable(false);
		setLocation(new Point(200, 100));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void addLables() {

		JLabel lblWindowName = new JLabel("Punktator");
		lblWindowName.setHorizontalAlignment(SwingConstants.CENTER);
		lblWindowName.setBounds(168, 22, 435, 30);
		lblWindowName.setFont(new Font("Tahoma", Font.BOLD, 14));
		getContentPane().add(lblWindowName);

		JLabel lblStart = new JLabel("<html>Rozpocznij uzupe\u0142nianie ankiety od pocz\u0105tku</html>");
		lblStart.setHorizontalAlignment(SwingConstants.CENTER);
		lblStart.setBounds(168, 77, 445, 25);
		getContentPane().add(lblStart);

		JLabel lblEdit = new JLabel("<html>Edytuj istniej¹c¹ ankietê</html>");
		lblEdit.setBounds(168, 164, 435, 25);
		lblEdit.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblEdit);
	}

	public void addButtons() {
		JButton btnStart = new JButton("Rozpocznij");
		btnStart.setBounds(337, 115, 100, 25);
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String nameOfEmployee = addNameOfEmployee();
				String yeraOfQuestionnaire = Integer.toString(addYearOfQuestionnaire());

				new MainWindow("questionnaire.json", nameOfEmployee, yeraOfQuestionnaire);
				Result.getInstance().setFileToRead("questionnaire.json");
				startWindow.setVisible(false);
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnStart);

		JButton btnEdit = new JButton("Edytuj");
		btnEdit.setBounds(337, 202, 100, 25);
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
				chooser.setFileFilter(filter);
				chooser.setVisible(true);
				File workingDirectory = new File(System.getProperty("user.dir"));
				chooser.setCurrentDirectory(workingDirectory);

				int returnVal = chooser.showOpenDialog(getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String fileName = chooser.getSelectedFile().getName();
					String filePath = chooser.getSelectedFile().getAbsolutePath();
					Result.getInstance().setFileToRead(filePath);
					new MainWindow(filePath, fileName.split("_")[0], fileName.split("_")[1]);
				}
				startWindow.setVisible(false);
			}
		});
		
		getContentPane().add(btnEdit);
	}

	private void addPicture() {
		
		BufferedImage myPicture = null;
		try {
			myPicture = ImageIO.read(new File("pictures\\"+"logo-P£2.jpg"));
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		picLabel.setBounds(15, 17, 146, 230);
		getContentPane().add(picLabel);
		
	}
	
	public String addNameOfEmployee() {
		String nameOfEmployee = "";
		while (nameOfEmployee.equals("")) {
			nameOfEmployee = (String) JOptionPane.showInputDialog(null, "Podaj imiê i nazwisko:", "Punktator",
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (nameOfEmployee == null) {
				nameOfEmployee = "";
			}
		}
		return nameOfEmployee;
	}

	public int addYearOfQuestionnaire() {
		String yearString = "";
		int year = 0;
		while (yearString == "" || !Utils.checkIntFormat(yearString)) {
			yearString = (String) JOptionPane.showInputDialog(null, "Podaj rok:", "Punktator",
					JOptionPane.PLAIN_MESSAGE, null, null, null);
			if (yearString == null) {
				yearString = "";
			}
			if (Utils.checkIntFormat(yearString))
				year = Integer.parseInt(yearString);
			else
				JOptionPane.showMessageDialog(null, "Niepoprawny format podanego roku", "Niepoprawy format",
						JOptionPane.PLAIN_MESSAGE, null);
		}
		return year;
	}
}
