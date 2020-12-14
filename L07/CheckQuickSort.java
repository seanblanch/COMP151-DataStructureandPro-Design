

import java.util.Arrays;
import java.util.Random;

/**
 * Created by atb on 3/4/2019
 */
public class CheckQuickSort
{
    public static void main(String args[])
    {
        int[] arraySizes = {1, 2, 3, 4, 5, 6, 7, 8, 9, 39, 40, 41, 50, 72, 100};
        final int MAX_NUMBER = 99;

        Integer data[];
        Integer forTesting[];
        for (int i = 0; i < arraySizes.length; i++)
        {
            System.out.println("\n===> TEST array of size " + arraySizes[i]);
            Random generator = new Random(11);
            // create an array and fill it with random values between 0 and MAX_NUMBER
            data = new Integer[arraySizes[i]];
            for (int j = 0; j < data.length; j++)
            {
                data[j] = generator.nextInt(MAX_NUMBER + 1);
            }
            System.out.println("The original array is: ");
            System.out.println(Arrays.toString(data));
            // make a copy of the original array we will sort it and use it for comparing result
            forTesting = Arrays.copyOf(data, data.length);
            Arrays.sort(forTesting);
            SortArray.quickSort(data, data.length);
            System.out.println("The original array sorted with quickSort: ");
            System.out.println(Arrays.toString(data));
            System.out.println(Arrays.equals(data, forTesting) ? "  passes" : "   fails");
        }
    }
}
