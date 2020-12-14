


import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


/**
 * A class for testing countingSort method
 *
 * @author Sean Blanchard
 * @version 3/11/2019
 */


public class CheckBinaryRadixSort {

    public static void main(String args[])
    {
        int arraySize = 0;
        int trials = 0;
        int maxNumber = 0;
        boolean invalidInput;

        // get input
        do
        {
            try
            {
                invalidInput = false;
                Scanner keyboard = new Scanner(System.in);
                System.out.println("What size array should be used?" +
                        "\n It should be an integer value greater than or equal to 1.");
                arraySize = keyboard.nextInt();

                System.out.println("How many arrays should be used (number of trials)?" +
                        "\n It should be an integer value greater than or equal to 1.");
                trials = keyboard.nextInt();

                System.out.println("What maximum number should be generated?" +
                        "\n It should be an integer value greater than or equal to 1.");
                maxNumber = keyboard.nextInt();
            }
            catch(InputMismatchException ime)
            {
                System.out.println("Could not convert input to an integer");
                invalidInput = true;
            }
            catch(Exception e)
            {
                System.out.println("There was an error with System.in");
                System.out.println(e.getMessage());
                invalidInput = true;
            }
        }while (invalidInput);

        // start testing
        Integer[] data;
        Integer[] forTesting;
        for(int i = 1; i <= trials; i++)
        {
            System.out.println("\nTRIAL #" + i);
            Random generator = new Random();
            // create an array and fill it with random values between 0 and MAX_RANDOM exclusive
            data = new Integer[arraySize];
            for(int j = 0; j < arraySize; j++)
            {
                data[j] = generator.nextInt(maxNumber + 1);
            }
            System.out.println("The original array is: ");
            System.out.println(Arrays.toString(data));
            // make a copy of the original array we will sort it and use it for comparing result
            forTesting = Arrays.copyOf(data, arraySize);
            Arrays.sort(forTesting);
            SortArray.binaryRadixSort(data, maxNumber);
            System.out.println("The original array sorted with countingSort: ");
            System.out.println(Arrays.toString(data));
            System.out.println(Arrays.equals(data, forTesting)?"  passes":"   fails");
        } //end for
    }

}
