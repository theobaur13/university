import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws Exception {
        System.out.print("Card (c) or Die (d) game? ");
        String ans=br.readLine();
        if (ans.equals("c")) {
            CardGame cardGame = new CardGame();
            cardGame.initialise();
            cardGame.play();
            cardGame.declareWinner();
        }
        else if (ans.equals("d")) {
            DieGame dieGame = new DieGame();
            dieGame.initialise();
            dieGame.play();
            dieGame.declareWinner();
        }

        else {
            System.out.println("Input not understood");
        }
    }
}
