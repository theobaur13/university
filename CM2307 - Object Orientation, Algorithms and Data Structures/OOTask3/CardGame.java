import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class CardGame extends Game{
    private ThreadSafeCardDeck deck;
    private HashSet<String> cardsChosen=new HashSet<String>();
    private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public void initialise() throws Exception {
        deck = new ThreadSafeCardDeck();
    }
    public void play() throws Exception {
        for (int i=0; i<2; i++) {
            System.out.println("Hit <RETURN> to choose a card");
            br.readLine();
            String card = deck.dealCard();
            System.out.println("You chose " + card);
            cardsChosen.add(card);
        }

        System.out.println("Cards chosen: " + cardsChosen);
        System.out.println("Remaining cards: " + deck.getCardList());
    }
    public void declareWinner() throws Exception {
        System.out.println("Cards chosen: " + cardsChosen);
        if (cardsChosen.contains("AHrts") || cardsChosen.contains("ADmnds") ||
            cardsChosen.contains("ASpds") || cardsChosen.contains("AClbs")) {
          System.out.println("You won!");
        }
        else System.out.println("You lost!");
    }
}

