package Lab09;
import java.util.*;

/**
 *
 * @author Sean Blanchard
 * @version 3/26/2019
 */
public class MatchingGame
{
    private ArrayList<Integer> theNumbers;
    private final int MAX_NUMBER_OF_SHUFFLES = 5;
    private final int MIN_NUMBER = 10;
    private final int MAX_NUMBER = 99;
    private Random generator;

    public MatchingGame(int numberAmount)
    {
        this.theNumbers = new ArrayList<>();
        initializeList(numberAmount);
    }

    /**
     * Initialize the list with the count of random 2 digit numbers.
     *
     */
    private void initializeList(int numberAmount)
    {
        // TODO Project 6a
        this.generator = new Random(11);
        ListIterator<Integer> iter1 = this.theNumbers.listIterator();

        // generate the numbers and add them to theNumbers using iterator

        for(int i = 0; i < numberAmount; i++)
        {
            //generates numbers - two digit integers between 10 and 99
            int randNumber = generator.nextInt((99 - 10) + 1) + 10;
            iter1.add(randNumber);
        }

    }

    /**
     * See whether two numbers are removable.
     * @param first the first 2 digit integer value
     * @param second the second 2 digit integer value
     * @return true if the first and second match
     */
    private boolean removablePair(Integer first, Integer second)
    {
        // TODO Project 6c

        // implement this method
        String firstNumber = String.valueOf(first);
        String secondNumber = String.valueOf(second);

        boolean removed = false;
        //If the first digit of first number is equal to first digit of second number
        if(firstNumber.charAt(0) == secondNumber.charAt(0))
        {
            //return true;
            removed = true;
        }
        //If the first digit of first number is equal to the second digit of second number
        else if (firstNumber.charAt(0) == secondNumber.charAt(1))
        {
            //return true
            removed = true;
        }
        //If the second digit of first number is equal to the first digit of the second number
        else if (firstNumber.charAt(1) == secondNumber.charAt(0))
        {
            //return true
            removed = true;
        }
        //If the second digit of first number is equal to the second digit of the second number
        else if ( firstNumber.charAt(1) == secondNumber.charAt(1))
        {
            //return true
            removed = true;
        }

        //if removed = true;
        if(removed)
        {
            //State what first and second numbers were removed
            System.out.println("  Removed : " + first + "  " + second);
            return true;
        }
        //Nothing was removed
        return false;
    }

    /**
     * Implements one pass when called by play method
     * Scans over the list and removes any pairs of values that are removable.
     * @return true if any pair of integers was removed
     */
    private boolean scanAndRemovePairs()
    {
        // TODO Project 6d
        boolean removedAPair = false;
        ListIterator<Integer> scan = this.theNumbers.listIterator();

        Integer first = null;
        Integer second = null;

        // implement the method
        // this method calls helper method removablePair to see if there is a match

        if(scan.hasNext())
        {
            first = scan.next();
        }


        while (scan.hasNext())
        {
            second = scan.next();

            //Scans over the list and removes any pairs of values that are removable.


            if(removablePair(first, second))
            {
                scan.remove();
                if(scan.hasPrevious())
                {
                    scan.previous();
                    scan.remove();
                    removedAPair = true;
                }
                if(scan.hasNext())
                {
                    first = scan.next();
                    continue;
                }
            }
            first = second;
        }
        return removedAPair;
    }

    private void displayTheNumbers()
    {
        // TODO Project 6b

        // using an instance of Iterator display the content of theNumbers
        ListIterator<Integer> iter2 = this.theNumbers.listIterator();
        // notify the user if the list is empty
        if(this.theNumbers.isEmpty())
        {
            System.out.println("The list is empty.");
        }
        // If the iterator is not empty
        while (iter2.hasNext())
        {
            //print the next number
            System.out.print(iter2.next() + " ");
        }
        System.out.println();
    }

    public void play()
    {
        int pass = 0;
        int numberOfShuffles = 0;
        boolean repeat;

        System.out.println("Starting with: ");
        displayTheNumbers();

        do
        {
            repeat = false;
            while (scanAndRemovePairs())
            {
                pass++;
                System.out.println("The list after pass #" + pass);
                displayTheNumbers();
            }
            System.out.println("No more pairs to remove.");
            // do we have at least 3 numbers in the list?
            if (this.theNumbers.size() > 2)
            {
                if (numberOfShuffles < MAX_NUMBER_OF_SHUFFLES)
                {
                    numberOfShuffles++;
                    System.out.println("Shuffling the numbers.");
                    Collections.shuffle(this.theNumbers, this.generator);
                    System.out.println("The list after shuffling #" + numberOfShuffles);
                    displayTheNumbers();
                    repeat = true;
                }
            }
        }while(repeat);

        if (this.theNumbers.isEmpty())
        {
            System.out.println("\n*** Winner!!! ***");
        }
        else
        {
            System.out.println("\n*** Better luck next time! ***");
        }
    }

    public static void main(String[] args)
    {
        final int MIN_NUMBER_OF_ELEMENTS = 10;
        Scanner scan = new Scanner(System.in);
        int numberAmount;

        do
        {
            System.out.println("How many numbers (no less than " + MIN_NUMBER_OF_ELEMENTS + ")?");
            numberAmount = scan.nextInt();
        }while(numberAmount < MIN_NUMBER_OF_ELEMENTS);

        MatchingGame game = new MatchingGame(numberAmount);
        game.play();
    }
}
