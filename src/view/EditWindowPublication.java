package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.Calculations;
import controller.ParsePdfDocument;
import model.AchievementsCategory;
import model.SpecificAchievement;

public class EditWindowPublication extends EditWindow {

	private static final long serialVersionUID = 1L;

	private String[] arrays;
	private String[] defaultValues;
	private JList<String> jList;
	private ParsePdfDocument pdfDocument = new ParsePdfDocument();

	public EditWindowPublication(String nameOfAchievement, AchievementsCategory achievementsCategory, JLabel newPnt) {
		super(nameOfAchievement, achievementsCategory, newPnt);
		createButton();
	}

	private void init() {

		arrays = pdfDocument.arrayOfNames();
		defaultValues = arrays;
		jList = createJList();

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 126, 755, 67);
		getContentPane().add(scrollPane);

		panel = new JPanel();
		scrollPane.setViewportView(panel);
		panel.setLayout(null);

		JScrollPane scrollPane2 = new JScrollPane(jList);
		scrollPane2.setBounds(12, 200, 755, 113);
		getContentPane().add(scrollPane2);
		getContentPane().add(createTextField());

	}

	private void createButton() {
		JButton btnAddTitles = new JButton("Wczytaj listê czasopism");
		btnAddTitles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				JFileChooser chooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF files", "pdf");
				chooser.setFileFilter(filter);
				chooser.setVisible(true);
				File workingDirectory = new File(System.getProperty("user.dir"));
				chooser.setCurrentDirectory(workingDirectory);

				int returnVal = chooser.showOpenDialog(getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					String filePath = chooser.getSelectedFile().getAbsolutePath();
					try {
						JOptionPane.showMessageDialog(null, "Rozpocznij przetwarzanie pliku...", "Przetwarzanie pliku",
								JOptionPane.PLAIN_MESSAGE, null);
						pdfDocument.splitText(filePath);
						JOptionPane.showMessageDialog(null, "Przetwarzanie pliku zakoñczone!", "Przetwarzanie pliku",
								JOptionPane.PLAIN_MESSAGE, null);
						init();
						getContentPane().repaint();
						getContentPane().validate();

					} catch (IOException e1) {
						e1.printStackTrace();
						JOptionPane.showMessageDialog(null, "Przetwarzanie pliku nie powiod³o siê!",
								"Przetwarzanie pliku", JOptionPane.PLAIN_MESSAGE, null);
					}
				}

				JButton btnDodaj = new JButton("Dodaj");
				btnDodaj.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						String achievementFromUser = (String) JOptionPane.showInputDialog(null,
								"Podaj nazwê publikacji:", "Dodatkowe informacje", JOptionPane.PLAIN_MESSAGE, null,
								null, null);

						if (achievementFromUser == null)
							achievementFromUser = "";
						if (!achievementFromUser.equals("")) {

							lblSetGainedPoints.setText(
									Calculations.publCalculation(Double.parseDouble(lblSetGainedPoints.getText()),
											pdfDocument.getPointsByName(jList.getSelectedValue())));

							achievement.getSpecyficAchievementsList()
									.add(new SpecificAchievement(jList.getSelectedValue(), Calculations.actualPoints));

							fillPanel(achievementsFromUserList);
						}
					}
				});
				btnDodaj.setBounds(200, 93, 90, 25);
				getContentPane().add(btnDodaj);

			}
		});
		btnAddTitles.setBounds(12, 93, 180, 25);
		getContentPane().add(btnAddTitles);
	}

	private JTextField createTextField() {

		JLabel lblSzukaj = new JLabel("Szukaj: ");
		lblSzukaj.setBounds(12, 325, 56, 16);
		getContentPane().add(lblSzukaj);

		final JTextField field = new JTextField(defaultValues.length);
		field.setBounds(68, 322, 460, 22);
		field.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				filter();
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				filter();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
			}

			private void filter() {
				String filter = field.getText();
				filterModel((DefaultListModel<String>) jList.getModel(), filter);
			}
		});
		return field;
	}

	private JList<String> createJList() {
		JList<String> list = new JList<String>(createDefaultListModel());
		list.setVisibleRowCount(4);
		return list;
	}

	private ListModel<String> createDefaultListModel() {
		DefaultListModel<String> model = new DefaultListModel<>();
		for (String s : defaultValues) {
			model.addElement(s);
		}
		return model;
	}

	public void filterModel(DefaultListModel<String> model, String filter) {
		for (String s : defaultValues) {
			if (s != null) {
				if (!s.toLowerCase().contains(filter.toLowerCase())) {
					if (model.contains(s)) {
						model.removeElement(s);
					}
				} else {
					if (!model.contains(s)) {
						model.addElement(s);
					}
				}
			}
		}
	}

}
