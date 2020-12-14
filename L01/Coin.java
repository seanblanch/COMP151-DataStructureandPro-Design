

//Sean Blanchard
//1/28/19
// Coin subclass that implements a Coin object.



public class Coin extends Money {
    public final static int[] DENOMINATION_VALUE = {1, 5, 10, 25, 50};
    public final static String[] DENOMINATION_NAME = {"PENNY", "NICKEL", "DIME", "QUARTER", "HALF_DOLLAR"};
    private static final int NUMBER_OF_DENOMINATIONS = 5;

    public Coin() {
        super(NUMBER_OF_DENOMINATIONS); // Grab number of denominations
    }


    public double getValue(){
        Double value = (double)DENOMINATION_VALUE[super.getDenomination()];
        return value;


    }

    public String toString(){
        return DENOMINATION_NAME[super.getDenomination()];
    }

}
