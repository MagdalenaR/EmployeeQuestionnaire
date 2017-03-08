package controller;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

import model.Result;

public class GeneratePdfDocument {

	private int headerLineCharactersNumber = 70;
	private int contentLineCharactersNumber = 85;
	private int headerFontSize = 14;
	private int contentFontSize = 12;
	private int linesOnPage = 26;
	private int lines;

	private PDDocument document;
	private PDPage newPage;
	private PDPageContentStream pageContent;

	private PDType0Font font;
	private PDType0Font fontBold;
	private PDType0Font fontItalic;

	public void generateFullReport(String fileName) throws IOException {

		createPageAndAddEmpoloyeeInfo(fileName);

		for (int i = 0; i < Result.getInstance().getAchievementsCategory().size(); i++) {

			if (lines > linesOnPage - 3) {
				pageContent.endText();
				pageContent.close();

				createNewPage();
			}

			pageContent.setFont(fontBold, headerFontSize);
			pageContent.setLeading(30.0f);
			String lineCategory = i + 1 + ". " + Result.getInstance().getAchievementsCategory().get(i).getName().trim()
					+ " " + Result.getInstance().getAchievementsCategory().get(i).getPoints().trim();
			addTextWithLineSplit(lineCategory, headerLineCharactersNumber);

			for (int j = 0; j < Result.getInstance().getAchievementsCategory().get(i).getAchievementsList()
					.size(); j++) {

				if (lines > linesOnPage) {
					pageContent.endText();
					pageContent.close();

					createNewPage();
				}

				pageContent.setFont(font, contentFontSize);
				pageContent.setLeading(25.0f);

				String lineContent = j + 1 + ". "
						+ Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j).getName()
								.trim()
						+ " " + Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
								.getGainedPoints().trim();
				addTextWithLineSplit(lineContent, contentLineCharactersNumber);

				for (int k = 0; k < Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
						.getSpecyficAchievementsList().size(); k++) {

					if (lines > linesOnPage) {
						pageContent.endText();
						pageContent.close();

						createNewPage();
					}

					pageContent.setFont(fontItalic, contentFontSize);

					String lineContent2 = "   " + (k + 1) + ". "
							+ Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
									.getSpecyficAchievementsList().get(k).getName().trim()
							+ " " + Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
									.getSpecyficAchievementsList().get(k).getPoints().trim();
					addTextWithLineSplit(lineContent2, contentLineCharactersNumber);
				}
			}
			pageContent.newLine();
			lines++;
		}

		pageContent.endText();
		pageContent.close();
		document.save(new File("reports\\" + fileName + ".pdf"));
		document.close();
	}

	public void generateShortReport(String fileName) throws IOException {

		createPageAndAddEmpoloyeeInfo(fileName);

		for (int i = 0; i < Result.getInstance().getAchievementsCategory().size(); i++) {

			if (!Result.getInstance().getAchievementsCategory().get(i).getPoints().trim().equals("0.0")) {

				if (lines > linesOnPage - 3) {
					pageContent.endText();
					pageContent.close();

					createNewPage();
				}

				pageContent.setFont(fontBold, headerFontSize);
				pageContent.setLeading(30.0f);

				String line = Result.getInstance().getAchievementsCategory().get(i).getName().trim() + " "
						+ Result.getInstance().getAchievementsCategory().get(i).getPoints().trim();
				addTextWithLineSplit(line, headerLineCharactersNumber);

				for (int j = 0; j < Result.getInstance().getAchievementsCategory().get(i).getAchievementsList()
						.size(); j++) {

					if (!Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
							.getGainedPoints().trim().equals("0.0")) {

						if (lines > linesOnPage) {
							pageContent.endText();
							pageContent.close();

							createNewPage();
						}

						pageContent.setFont(font, contentFontSize);
						pageContent.setLeading(25.0f);

						String contentLine = Result.getInstance().getAchievementsCategory().get(i).getAchievementsList()
								.get(j).getName().trim() + " "
								+ Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
										.getGainedPoints().trim();
						addTextWithLineSplit(contentLine, contentLineCharactersNumber);

						for (int k = 0; k < Result.getInstance().getAchievementsCategory().get(i).getAchievementsList()
								.get(j).getSpecyficAchievementsList().size(); k++) {

							if (!Result.getInstance().getAchievementsCategory().get(i).getAchievementsList().get(j)
									.getSpecyficAchievementsList().isEmpty()) {

								if (lines > linesOnPage) {
									pageContent.endText();
									pageContent.close();

									createNewPage();
								}

								pageContent.setFont(fontItalic, contentFontSize);

								String contentLine2 = "   " + (k + 1) + ". "
										+ Result.getInstance().getAchievementsCategory().get(i).getAchievementsList()
												.get(j).getSpecyficAchievementsList().get(k).getName().trim()
										+ " "
										+ Result.getInstance().getAchievementsCategory().get(i).getAchievementsList()
												.get(j).getSpecyficAchievementsList().get(k).getPoints().trim();

								addTextWithLineSplit(contentLine2, contentLineCharactersNumber);
							}
						}

						pageContent.newLine();
						lines++;
					}
				}
			}
		}
		pageContent.endText();
		pageContent.close();
		document.save(new File("reports\\" + fileName + ".pdf"));
		document.close();

	}

	private void createNewPage() throws IOException {

		lines = 0;

		newPage = new PDPage();
		document.addPage(newPage);
		pageContent = new PDPageContentStream(document, newPage);

		pageContent.setLineWidth(50.0f);

		pageContent.beginText();
		font = PDType0Font.load(document, new File("fonts\\"+"OpenSans-Regular.ttf"));
		fontBold = PDType0Font.load(document, new File("fonts\\"+"OpenSans-Bold.ttf"));
		fontItalic = PDType0Font.load(document, new File("fonts\\"+"OpenSans-Italic.ttf"));
		pageContent.newLineAtOffset(40, 730);
	}

	private void createPageAndAddEmpoloyeeInfo(String fileName) throws IOException {

		document = new PDDocument();

		createNewPage();

		pageContent.setFont(fontItalic, headerFontSize);
		pageContent.setLeading(20.0f);

		String[] splitFileName = fileName.split("_");

		pageContent.showText("Pracownik akademicki: " + splitFileName[0]);
		pageContent.newLine();
		lines++;
		pageContent.showText("Ankieta za rok: " + splitFileName[1]);
		pageContent.newLine();
		lines++;
		pageContent.newLine();
		lines++;

	}

	private void addTextWithLineSplit(String line, int charsInLine) throws IOException {

		String[] words = line.split(" ");
		String line1 = words[0];
		int i = 1;

		while (line1.length() < charsInLine && i < words.length) {
			String tmp = line1;
			line1 = line1 + " " + words[i];
			if (line1.length() > charsInLine) {
				line1 = tmp;
				break;
			}
			i++;
		}

		pageContent.showText(line1);
		pageContent.newLine();
		lines++;

		if (i < words.length) {
			String line2 = "";
			for (int j = i; j < words.length; j++) {
				line2 = line2 + " " + words[j];
			}

			pageContent.showText(line2);
			pageContent.newLine();
			lines++;
		}
	}

}