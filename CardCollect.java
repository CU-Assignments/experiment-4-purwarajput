import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Card {
    String symbol;
    String rank;

    public Card(String symbol, String rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    public void display() {
        System.out.println(rank + " of " + symbol);
    }
}

public class CardCollect {
    private static List<Card> deck = new ArrayList<>();

    public static void main(String[] args) {
        initializeDeck();

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter symbol (hearts, diamonds, clubs, spades) to search: ");
        String symbol = scanner.nextLine().toLowerCase();

        findCardsBySymbol(symbol);
    }

    private static void initializeDeck() {
        String[] symbols = {"hearts", "diamonds", "clubs", "spades"};
        String[] ranks = {"Ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King"};

        for (String symbol : symbols) {
            for (String rank : ranks) {
                deck.add(new Card(symbol, rank));
            }
        }
    }
    private static void findCardsBySymbol(String symbol) {
        boolean found = false;
        for (Card card : deck) {
            if (card.symbol.equalsIgnoreCase(symbol)) {
                card.display();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found for symbol: " + symbol);
        }
    }
}

