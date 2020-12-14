import java.util.Random;

/**
 * An abstract class that implements a Money object.
 *
 * @author Sean Blanchard
 * @version 1/22/2019
 */
public abstract class Money
{
    private int denomination;
    private boolean heads;
    public int BILL = 0;
    public int COIN = 1;
    private int currentValue;


    public Money(int numberOfDenominations)
    {
        //TODO Project3
       Random random = new Random();
       currentValue = random.nextInt(numberOfDenominations);
        if(numberOfDenominations == 7){
            denomination = BILL;
        } else {
            denomination = COIN;
        }
        heads = false;
      //  this.denomination;
        //TODO END

    }

    public int getDenomination()
    {
        //TODO Project3
        return denomination; // THIS IS A STUB
        //TODO END
    }

    public abstract double getValue();

    public void toss()
    {
        //TODO Project3
        Random random = new Random();
        heads = random.nextBoolean();
        //TODO END

    }

    public boolean isHeads()
    {
        //TODO Project3
        return heads; // THIS IS A STUB
        //TODO END
    }

    public abstract String toString();
}
