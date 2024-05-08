import java.util.ArrayList;

// java threadSafeTest 10 5
// tests up to 10 threads, each dealing 5 cards

public class threadSafeTest {
    public static void main(String[] args) throws InterruptedException {
      // int maxThread = Integer.parseInt(args[0]);  //What thread to test up to
      // int numDeals = Integer.parseInt(args[1]);   //How many times to deal a cards

      int maxThread = 10;
      int numDeals = 5;

      for (int n = 1; n <= maxThread; n++) {      //For each thread
        CardDeck deck = new CardDeck();
        int threadNo = n;
        ArrayList<Thread> threads = new ArrayList<Thread>();

        for (int i = 0; i < threadNo; i++) {
          Thread t = new Thread(() -> {
            for (int j = 0; j < numDeals; j++){
              deck.dealCard();
            }
          });
          threads.add(t);
          t.start();
        }

        for (Thread t : threads) {
          t.join();
        }

      System.out.println();
      System.out.println("Trying with " + threadNo + " threads");
      System.out.println("Sequence Number: " + deck.sequenceNumber);
      
      if (deck.sequenceNumber != threadNo * numDeals) {
        System.out.println("ERROR: Sequence Number does not match number of threads");
      }
      else {
        System.out.println("Sequence Number at expected value for number of threads");}
      }
    }
  }