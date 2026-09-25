/**
  *Takes the list of cards from DeckReader and analyzes them.
  *Input: The Array List of cards, containing both valid and invalid cards.
  *Output;
  *Total cost of all valid cards in the deck
  *Values to make histogram of cards in the deck
  *Number of invalid cards
  *Whether or not a deck is void
  *List of invalid cards
  */

import java.util.ArrayList;

public class DeckAnalyzer {

    private ArrayList<Card> cards;
    private int totalCost;
    private int[] histogram;
    private int invalidCount;
    private boolean isVoid;
    private ArrayList<Card> invalidCards;

    
    public DeckAnalyzer(ArrayList<Card> cards) {
        this.cards = cards;
        this.histogram = new int[7];   // costs 0–6
        this.invalidCards = new ArrayList<>();
        analyze();
    }

    private void analyze() {
        for (Card card : cards) {

            if (card.isValid()) { //Adds to total cost of deck if a card is valid
                totalCost += card.getCost();

                histogram[card.getCost()]++; //Adds a value to histogram if a card is valid 


            } else { //Counts the number of invalid cards and adds them to a list of invalid cards
                invalidCount++;
                invalidCards.add(card);
            }
        }

        isVoid = (invalidCount > 10 || cards.size() > 1000); //Condition for whether or not a deck is VOID
    }

    public int getTotalCost() {
        return totalCost;
    }

    public int[] getHistogram() {
        return histogram;
    }

    public int getInvalidCount() {
        return invalidCount;
    }

    public boolean isVoid() {
        return isVoid;
    }

    public ArrayList<Card> getInvalidCards() {
        return invalidCards;
    }
}
