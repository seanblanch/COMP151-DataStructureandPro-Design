/**
 * A class that implements a piggy bank full exception.
 *
 *
 * @version 1/22/2019
 * @updatedBy Sean Blanchard
 */
public class PiggyBankFullException  extends RuntimeException
{

    /**
     * Constructor for objects of class PiggyBankFullException
     */
    public PiggyBankFullException(String reason)
    {
        super(reason);
    }
}
