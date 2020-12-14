import java.util.*;

/**
 * @author Sean Blanchard
 * @version 1/22/2019
 */
public class CountHeadsGame
{
    public static void main(String[] args)
    {
        int numberOfMonies;
        int capacity;
        int maxRounds;

        Scanner scan = new Scanner(System.in);
        do
        {
            System.out.println("\nHow many coins/bills can fit in your piggy bank?");
            capacity = scan.nextInt();
            System.out.println("How many coins/bills should the computer put in your piggy bank?");
            numberOfMonies = scan.nextInt();
            System.out.println("How many rounds should we play?");
            maxRounds = scan.nextInt();

        } while (!(numberOfMonies > 0 && numberOfMonies <= capacity && maxRounds > 0));

        int userHeadsCount = 0;
        int computerHeadsCount = 0;
        Random random = new Random();
        boolean computersTurn = random.nextBoolean();
        int roundResult;
        Round round;

        for (int numberOfRounds = 1; numberOfRounds <= maxRounds; numberOfRounds++)
        {
            try
            {
                System.out.println("\n******* Round #" + numberOfRounds + " --> " + (computersTurn ? "COMPUTER" : "USER") + "'S TURN *******");
                round = new Round(numberOfMonies, capacity);

                roundResult = round.run();

                if (computersTurn)
                {
                    computerHeadsCount += roundResult;
                    computersTurn = false;
                }
                else
                {
                    userHeadsCount += roundResult;
                    computersTurn = true;
                }
            } catch (PiggyBankEmptyException pbee)
            {
                System.out.println(pbee.getMessage());
            }
        }

        System.out.println("\n***GAME OVER***\n");
        System.out.println("computerHeadsCount = " + computerHeadsCount);
        System.out.println("userHeadsCount = " + userHeadsCount);
        if (userHeadsCount == computerHeadsCount)
            System.out.println("\nIt's a tie!!!");
        else if (userHeadsCount > computerHeadsCount)
            System.out.println("\nUser wins!!!");
        else
            System.out.println("\nComputer wins!!!");
    }
}
