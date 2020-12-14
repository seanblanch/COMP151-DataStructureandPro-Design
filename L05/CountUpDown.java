

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The class recursively counts down and up from the number
 * given by the user.
 *
 * @author Sean Blanchard
 * @version 2/19/2019
 */

public class CountUpDown
{
    /**
     * countUp - a recursive function that counts up from 1 to n
     *
     * @param n    the integer value to count up to
     */
    private static void countUp(int n)
    {
        // TODO PRELAB
        // IMPLEMENT THIS RECURSIVE METHOD
        if (n >= 1)
        {
            countUp(n - 1);
            System.out.println(n);
        }

    }

    /**
     * countDown - a recursive function that counts down from n to 1
     *
     * @param n    the integer value to count down from
     */
    private static void countDown(int n)
    {
        // TODO PRELAB
        // IMPLEMENT THIS RECURSIVE METHOD

        System.out.println(n);
        if (n > 1)
        {
            countDown(n - 1);
        }


    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        int n = 10;        //default value is 10
        try
        {
            System.out.println("Please enter an integer value greater than 0");
            n = input.nextInt();
            if (n <= 0)
            {
                n = 10;
                throw new Exception();
            }

        }
        catch(InputMismatchException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println("Will use 10 as the default value");
        }
        catch(Exception e)
        {
            System.out.println("There was an error with the input value");
            System.out.println("Will use 10 as the default value");
        }

        System.out.println();
        System.out.println("Should count down to 1");
        countDown(n);

        System.out.println();
        System.out.println("Should count up from 1");
        countUp(n);
    }
}

//    Please enter an integer value greater than 0
//        5
//
//        Should count down to 1
//        5
//        4
//        3
//        2
//        1
//
//        Should count up from 1
//        1
//        2
//        3
//        4
//        5