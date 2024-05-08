import java.util.HashSet;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DieGame extends Game{
    private Die die;
    private HashSet<Integer> numbersRolled = new HashSet<Integer>();
    private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
    public void initialise() throws Exception {
        die = new Die();
    }
    public void play() throws Exception {
        for (int i=0; i<2; i++) {
            System.out.println("Hit <RETURN> to roll the die");
            br.readLine();
            die.roll();
            System.out.println("You rolled " + die.getDieRoll());
            numbersRolled.add(die.getDieRoll());
        }
        System.out.println("Numbers rolled: " + numbersRolled);
    }
    public void declareWinner() throws Exception {
        if (numbersRolled.contains(1)) {
            System.out.println("You won!");
        }
        else System.out.println("You lost!");
    }
}
