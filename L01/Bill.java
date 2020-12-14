
//Sean Blanchard
//1/28/19
// Bill subclass that implements a Bill object.



public class Bill extends Money {
    public static final int NUMBER_OF_DENOMINATIONS = 7;
    public final static int[] DENOMINATION_VALUE = {1, 2, 5, 10, 20, 50, 100};
    public final static String[] DENOMINATION_NAME = {"WASHINGTON", "JEFFERSON", "LINCOLN", "HAMILTON", "JACKSON", "GRANT", "FRANKLIN"};


    public Bill() {

        super(NUMBER_OF_DENOMINATIONS);
    }

    public double getValue(){

        Double value = (double)DENOMINATION_VALUE[super.getDenomination()];
        return value;
    }

    public String toString(){
        return DENOMINATION_NAME[super.getDenomination()];
    }

}
