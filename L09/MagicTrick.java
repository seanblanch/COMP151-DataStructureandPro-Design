package Lab09;

import java.util.*;

/**
 * MagicTrick is a program that will guess a number that user is thinking of.
 *
 * @author Sean Blanchard
 * @version 3/26/2019
 */
public class MagicTrick
{
    public final int NUM_OF_SEQUENCES = 5;
    public final int NUMBERS_PER_SEQUENCE = 16;
    private ArrayList<Integer>[] sequences;

    public MagicTrick()
    {
        // TODO Project 5

        //create this.sequences object
        this.sequences = new ArrayList[NUM_OF_SEQUENCES];
        //Create a bitmask
        int bitMask1 = 1;
        //Max number is 31 in sample run
        int maxNumber = 32;

        //using a for loop create ArrayList object for each slot and fill it with appropriate values
        for(int i = 0; i < NUM_OF_SEQUENCES; i++)
        {
            this.sequences[i] = new ArrayList<Integer>();

            for(int j = 1; j < maxNumber; j++)
            {
                //is value & mask equal to 1
                if(((j & bitMask1) >> i) ==1)
                {
                    this.sequences[i].add(j);
                }
            }
            //Set bitmask back to help fill each sequence with numbers
            bitMask1 = Integer.rotateLeft(bitMask1, 1);
        }


    }

    public void displaySequences()
    {
        // TODO Project 5

        for (int i = 0; i < NUM_OF_SEQUENCES; i++)
        {
            System.out.println("Sequence " + (i + 1) + ": " + this.sequences[i]);
        }
    }

    public void guessNumber(String[] answer)
    {
        // TODO Project 5
        int firstNum = 0;
        //iterator
        int guessedNumber = 0;
        for(int i = 0; i < answer.length; i++)
        {
            int seqNum = Integer.parseInt(answer[i]);
            //Iterate next element -> add to guess
            guessedNumber += this.sequences[seqNum - 1].get(firstNum);

        }
        System.out.println("Your number is " + guessedNumber + " :)");
    }

    public static void main(String[] args)
    {
        MagicTrick magic = new MagicTrick();
        System.out.println("Think of a number between 1 and 31\n");
        magic.displaySequences();

        System.out.println("\nList all the sequences that your number is in (ie. 1 3)");
        Scanner scan = new Scanner(System.in);
        magic.guessNumber(scan.nextLine().split(" "));
    }
}