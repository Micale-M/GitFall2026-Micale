/**
  *Creates a differing reports based on if a deck is VOID or not.
  *Input: An analyzed deck and deck ID number
  *Output;
  *VOID Report
  *Non-VOID Report
  *Histogram
  *Number of invalid cards and why they are invalid
  */

import java.io.IOException;
import java.util.ArrayList;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;


public class DeckReportGenerator {

    private DeckAnalyzer analyzer;
    private int id;

    public DeckReportGenerator(DeckAnalyzer analyzer, int id) {
        this.analyzer = analyzer;
        this.id = id;
    }

    public void generateReport(String filename) { //Writes different reports based on if a deck is VOID or not
        if (analyzer.isVoid()) {
            writeVoidReport(filename);
        } else {
            writeNormalReport(filename);
        }
    }

    private void writeVoidReport(String filename) { //Writes VOID onto the pdf file and that is all
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.setFont(PDType1Font.HELVETICA_BOLD, 36);
            content.beginText();
            content.newLineAtOffset(220, 700);
            content.showText("VOID");
            content.endText();
            content.close();

            doc.save(filename);
        } catch (IOException e) {
            System.out.println("Error writing VOID report.");
        }
    }

    private void writeNormalReport(String filename) {
        try (PDDocument doc = new PDDocument()) {
            PDPage page = new PDPage();
            doc.addPage(page);

            PDPageContentStream content = new PDPageContentStream(doc, page);
            content.setFont(PDType1Font.HELVETICA, 14);

            int y = 700;

            content.beginText(); //Title for the report
            content.newLineAtOffset(50, y);
            content.setFont(PDType1Font.HELVETICA_BOLD, 20);
            content.showText("Spire Deck Report");
            content.endText();

            y -= 40;

            content.beginText(); //Deck ID for the deck
            content.newLineAtOffset(50, y);
            content.setFont(PDType1Font.HELVETICA, 14);
            content.showText("Deck ID: " + id);
            content.endText();

            y -= 30;

            content.beginText(); //Total cost of deck in energy
            content.newLineAtOffset(50, y);
            content.showText("Total Cost: " + analyzer.getTotalCost() + " energy");
            content.endText();

            y -= 40;

            content.beginText(); //Histogram of the number of cards that share the same cost
            content.newLineAtOffset(50, y);
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.showText("Histogram:");
            content.endText();

            y -= 30;

            writeHistogram(content, y);
            y -= 200;

            content.beginText(); //Number of invalid cards and reason for why they're invalid
            content.newLineAtOffset(50, y);
            content.setFont(PDType1Font.HELVETICA_BOLD, 16);
            content.showText("Invalid Cards:");
            content.endText();

            y -= 30;

            writeInvalidCards(content, y);

            content.close();
            doc.save(filename);

        } catch (IOException e) {
            System.out.println("Error writing normal report.");
        }
    }

    private void writeHistogram(PDPageContentStream content, int yStart) throws IOException {
        int[] hist = analyzer.getHistogram();
        int y = yStart;

        for (int cost = 0; cost <= 6; cost++) { //For loop to create the histogram
            content.beginText();
            content.newLineAtOffset(50, y);
            content.setFont(PDType1Font.HELVETICA, 14);

            content.showText(cost + " energy: " + hist[cost]);

            content.endText();
            y -= 25;
        }
    }

    private void writeInvalidCards(PDPageContentStream content, int yStart) throws IOException {
        ArrayList<Card> invalids = analyzer.getInvalidCards();
        int y = yStart;

        for (Card card : invalids) {//For loop to create the section about the invalid cards
            content.beginText();
            content.newLineAtOffset(50, y);
            content.setFont(PDType1Font.HELVETICA, 14);

            content.showText("- Name: " + card.getName() + " — Reason: " + card.getErrorMessage());

            content.endText();
            y -= 25;
        }
    }
}
