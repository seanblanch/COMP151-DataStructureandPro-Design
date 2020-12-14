/**
 * A class that implements a piggy bank full exception.
 *
 * @author Sean Blanchard
 * @version 1/22/2019
 */
public class PiggyBankEmptyException  extends RuntimeException
{

    /**
     * Constructor for objects of class PiggyBankEmptyException
     */
    public PiggyBankEmptyException(String reason)
    {
        super(reason);
    }
}
