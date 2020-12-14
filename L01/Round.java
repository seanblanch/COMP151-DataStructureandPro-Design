import org.omg.CORBA.CODESET_INCOMPATIBLE;

/**
 * A client class that implements a piggy bank functionality.
 *
 * @author Sean Blanchard
 * @version 1/22/2019
 */
public class Round
{
    private PiggyBank myPiggyBank; //commented out so the class compiles for now

    public Round(int numberOfMonies, int capacity)
    {

        //TODO Project3
        this.myPiggyBank = new PiggyBank(numberOfMonies, capacity);

    }

    public void addTwoMonies()
{
    //TODO Project3

}


    public int run()
    {
        //TODO Project3
        return 0; // THIS IS A STUB
    }

 }