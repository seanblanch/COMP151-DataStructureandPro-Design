import java.util.Scanner;

/**
 * The class recursively generates strings of n numbers
 *
 * @author Sean Blanchard
 * @version 2/19/2019
 */

public class StringsOfNumbers
{
    /**
     * bitString - a recursive function that generates strings of n bits
     *
     * @param n    the number of digits
     * @param str  one permutation of the string to be constructed and printed
     */
    private static void bitString(String str, int n)
    {
        // TODO Project #4
        // IMPLEMENT THIS RECURSIVE METHOD

        // STEP#1 Base case - the string is constructed so print it
        System.out.println("--> FOR DEBUGGING: At the start of the method: str is " + str + " and n is " + n);
        if ( n == 0 )
        {
            System.out.println("---> FOR DEBUGGING: Executing base case");
            System.out.println(str);
            return;
        }



        // STEP#2 recursive case - make two recursive calls:
        //                        one to append 0 and the second one to append 1

        System.out.println("----> FOR DEBUGGING: About to call itself to append '0': str is " + str +  " and n is " + n);
        bitString(str + "0", n - 1);
        System.out.println("----> FOR DEBUGGING: About to call itself to append '1': str is " + str +  " and n is " + n);
        bitString(str + "1", n - 1);

        //TODO done
    }

    /**
     * bitString - a recursive function that generates strings of n digits [0..k)
     *
     * @param n    the number of digits
     * @param k    k-1 should be the largest digit in the string str
     * @param str  one permutation of the string to be constructed and printed
     */
    private static void kString(String str, int n, int k)
    {
        // TODO Project #4
        // IMPLEMENT THIS RECURSIVE METHOD

        //System.out.println("--> FOR DEBUGGING: At the start of the method: str is " + str + " and n is " + n);
        if (n == 0)
        {
            //System.out.println("---> FOR DEBUGGING: Executing base case");
            System.out.println(str);
            return;
        }

        for (int i = 0 ; i < k; i ++)
        {
            kString(str + i, n - 1, k);
        }

        //TODO done
    }

    public static void main(String args[])
    {
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an integer value of n representing the number of digits in a string");
        int n = input.nextInt();
        System.out.println();
        System.out.println("Generating binary-Strings:");
        bitString("", n);
        System.out.println();
        System.out.println("Please enter an integer value k; strings of length n will be drown from 0..k-1");

        int k = input.nextInt();
        System.out.println("Generating k-Strings:");
        kString("", n, k);
    }
}
