package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import model.Publication;

public class ParsePdfDocument {

	public List<Publication> publications = new ArrayList<Publication>();

	private String parsePdfToText(String fileName) throws IOException {

		File file = new File(fileName);
		PDFParser pdfParser = new PDFParser(new RandomAccessFile(file, "r"));

		pdfParser.parse();

		COSDocument cosDocument = pdfParser.getDocument();
		PDFTextStripper pdfStripper = new PDFTextStripper();
		PDDocument pdDocument = new PDDocument(cosDocument);

		pdfStripper.setStartPage(1);
		pdfStripper.setEndPage(pdDocument.getNumberOfPages());

		return pdfStripper.getText(pdDocument);
	}

	public void splitText(String fileName) throws IOException {

		String[] splitedPdfFile = parsePdfToText(fileName).split("\n");

		for (int i = 6; i < splitedPdfFile.length; i++) {
			if (!splitedPdfFile[i].equals("") && Character.isDigit(splitedPdfFile[i].charAt(0))) {

				boolean isLineCorrect = true;
				int counter = 0;
				String fullLine = splitedPdfFile[i];

				while (isLineCorrect && counter < 5) {

					isLineCorrect = !getDataFromLine(fullLine);
					if (!isLineCorrect) {
						break;
					} else {
						counter++;
					}
					try {
						fullLine = fullLine + " " + splitedPdfFile[i + 1];
						i++;
					} catch (IndexOutOfBoundsException e) {
						break;
					}
				}
			}
		}
	}

	private boolean getDataFromLine(String line) {

		String[] splitedLine = line.split(" ");
		String idString, pointsString, issnString, nameOfPublicaton = "";
		int points, id;

		try {
			idString = splitedLine[0];
			pointsString = splitedLine[splitedLine.length - 1];
			issnString = splitedLine[splitedLine.length - 2];

			for (int i = 1; i < splitedLine.length - 2; i++) {
				nameOfPublicaton = nameOfPublicaton + splitedLine[i] + " ";
			}

			points = Integer.parseInt(pointsString.trim());
			id = Integer.parseInt(idString.trim());

			if (!issnString.contains("-")) {
				return false;
			}

		} catch (Exception e) {
			return false;
		}

		publications.add(new Publication(id, issnString, points, nameOfPublicaton));

		return true;
	}

	public String[] arrayOfNames() {
		String[] array = new String[publications.size()];
		for (int i = 0; i < publications.size(); i++)
			array[i] = publications.get(i).getName();

		return array;
	}

	public int getPointsByName(String name) {
		int result = 0;
		for (int i = 0; i < publications.size(); i++) {
			if (publications.get(i).getName().equals(name)) {
				result = publications.get(i).getPoints();
			}
		}
		return result;
	}

}
