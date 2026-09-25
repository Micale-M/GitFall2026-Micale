/**
  * Reads a deck file and returns a list of Card objects.
  * Input: Deck in the form of a text file where each card follows the format <card name>:<cost> per line
  * Output: Array List of Cards
  * Steps: Opens file, reads file line by line, validates name, validates cost.
  * Within the last two steps we check if a card is invalid or not.
  */

import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DeckReader {
	public ArrayList<Card> readDeck(String filename) {
        ArrayList<Card> cards = new ArrayList<>();

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file); //opens file

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim(); //reads file line by line

                if (line.isEmpty()) {
                    cards.add(new Card("", -1, false, "Empty line")); //checks if the line is empty
                    continue;
                }

                if (!line.contains(":")) { //checks if the format contains :
                    cards.add(new Card(line, -1, false, "Missing ':' separator")); 
                    continue;
                }

                String[] parts = line.split(":"); //splits the line into name and cost

                if (parts.length != 2) { 
                    cards.add(new Card(line, -1, false, "Invalid format"));
                    continue;
                }

                String name = parts[0].trim();
                String costStr = parts[1].trim();

                
                if (name.isEmpty()) { //checks if name is empty
                    cards.add(new Card(name, -1, false, "Card name is empty"));
                    continue;
                }

                
                int cost;
                try { 
                    cost = Integer.parseInt(costStr);
                } catch (NumberFormatException e) { //checks if cost is an integer
                    cards.add(new Card(name, -1, false, "Cost is not an integer"));
                    continue;
                }

                if (cost < 0 || cost > 6) { //checks if cost is between 0-6
                    cards.add(new Card(name, cost, false, "Cost out of range (0–6)"));
                    continue;
                }

                //if all the previous statements meet our criteria, then the card is valid
                cards.add(new Card(name, cost, true, ""));
            }

            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }

        return cards;
    }
}