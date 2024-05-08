import java.lang.Math;
import java.util.*;

public class CardDeck {
	public int sequenceNumber=0;
	public String lastCardChoice="NO THIS IS NOT A CARD CHOICE";
	public String cards[]={"AHrts", "2Hrts", "3Hrts", "4Hrts", "5Hrts", "6Hrts",
                      "7Hrts", "8Hrts", "9Hrts", "10Hrts", "JHrts",
                      "QHrts", "KHrts",
                      "ADmnds", "2Dmnds", "3Dmnds", "4Dmnds", "5Dmnds",
                      "6Dmnds", "7Dmnds", "8Dmnds", "9Dmnds", "10Dmnds",
                      "JDmnds", "QDmnds", "KDmnds",
                      "ASpds", "2Spds", "3Spds", "4Spds", "5Spds", "6Spds",
                      "7Spds", "8Spds", "9Spds", "10Spds", "JSpds",
                      "QSpds", "KSpds",
                      "AClbs", "2Clbs", "3Clbs", "4Clbs", "5Clbs", "6Clbs",
                      "7Clbs", "8Clbs", "9Clbs", "10Clbs", "JClbs",
                      "QClbs", "KClbs"};
    public ArrayList<String> cardList;
    
    public CardDeck() {
      // Initialize list of remaining cards
      cardList=new ArrayList<String> (Arrays.asList(cards));
      
      // Shuffle them
      for (int i=0; i<100; i++) {
        // choose two random cards at random and swap them, 100 times
        int firstIndex=((int) (Math.random() * 52));
        int secondIndex=((int) (Math.random() * 52));
        String temp=(String) cardList.get(firstIndex);
        cardList.set(firstIndex, cardList.get(secondIndex));
        cardList.set(secondIndex, temp); 
      }
    }
    
    public String dealCard() {
    // Various reasons why the code in the try{} block could fail, so
    // we catch any exception and
    // then carry on as if nothing had happened!
    
      try {
        int cardChoiceIndex=(int)(Math.random() * cardList.size());
          lastCardChoice=cardList.get(cardChoiceIndex);
          sequenceNumber++;
          cardList.remove(cardChoiceIndex);
      }
      catch (Exception e) {
      // Don't do anything; pretend nothing happened!
      }
      return lastCardChoice;
    }
    
    public static void main(String[] args) {
      CardDeck d=new CardDeck();
      for (int i=0; i<52; i++) {
        System.out.println(d.dealCard());
      }
      System.out.println("Remaining cards: " + d.cardList.size());
    }
}