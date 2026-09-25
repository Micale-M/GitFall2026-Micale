/**	
  *Prompts the user to input the name of a file that contains the card names and their respective
  *energy costs. Then creates a report that tallies the total energy cost and an energy
  *cost histogram for the deck and output the report in a .pdf file.
  */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.LinkedList; 

//port ValidCardNames;
//port DeckIDGenerator;
//port DeckReportGenerator;


public class SpireMain {

    public static void main(String[] args) {

	ValidCardNames.loadFromCSV("valid_cards.csv"); //Loads list of valid card names

        Scanner input = new Scanner(System.in);
        System.out.print("Enter deck filename: ");
        String deckFile = input.nextLine();

        
        DeckReader reader = new DeckReader(); //Reads deck
        ArrayList<Card> cards = reader.readDeck(deckFile);

        DeckAnalyzer analyzer = new DeckAnalyzer(cards); //Analyzes deck

        DeckIDGenerator idGen = new DeckIDGenerator(); //Generates the unique 9-digit ID
        int id = idGen.generateUniqueID();

        String filename; //Creates filename correctly
        if (analyzer.isVoid()) {
            filename = "SpireDeck " + id + "(VOID).pdf";
        } else {
            filename = "SpireDeck " + id + ".pdf";
        }

        DeckReportGenerator reportGen = new DeckReportGenerator(analyzer, id); //Generates report
        reportGen.generateReport(filename);

        System.out.println("Report generated: " + filename); //Confirms if a file was created or not
    }

     public static void processDeck(String deckPath, String outputFolder) {//A way for the GUI to specify what file is selected and which file the report should end up in.

    	ValidCardNames.loadFromCSV("valid_cards.csv");

   	DeckReader reader = new DeckReader();
        ArrayList<Card> cards = reader.readDeck(deckPath);

        DeckAnalyzer analyzer = new DeckAnalyzer(cards);

        DeckIDGenerator idGen = new DeckIDGenerator();
        int id = idGen.generateUniqueID();

         String filename;
        if (analyzer.isVoid()) {
           filename = outputFolder + "/SpireDeck " + id + "(VOID).pdf";
        } else {
          filename = outputFolder + "/SpireDeck " + id + ".pdf";
       }

         DeckReportGenerator reportGen = new DeckReportGenerator(analyzer, id);
         reportGen.generateReport(filename);
	}

}
