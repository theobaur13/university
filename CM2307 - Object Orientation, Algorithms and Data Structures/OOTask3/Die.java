public class Die {
    private int dieRoll;
    private RandomInterface r=new LinearCongruentialGenerator();

    public Die(){
        dieRoll = 0;
    }
    public void roll(){
        dieRoll = (int)(r.next() * 6) + 1;
    }
    public int getDieRoll(){
        return dieRoll;
    }
}
