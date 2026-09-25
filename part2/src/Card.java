/**
  *Defines the characterisitcs of a card.
  *Input: Name, Cost, Validity, Reason for being invalid.
  */

public class Card {
    private String name;
    private int cost;
    private boolean valid;
    private String errorMessage;

    public Card(String name, int cost, boolean valid, String errorMessage) {
        this.name = name;
        this.cost = cost;
        this.valid = valid;
        this.errorMessage = errorMessage;
        if (!ValidCardNames.isValidName(name)) {//Checks if a card is valid or not
          valid = false;
          errorMessage = "Invalid card name";
    return;
}

    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public boolean isValid() {
        return valid;
    }

    public String getErrorMessage() {//Custom feature to, tell user why a card was invalid
        return errorMessage;
    }
}
