import java.util.Arrays;

/**
 *  Write a recursive method that returns the smallest integer in an array of integers.
 *
 * @author Sean Blanchard
 * @version 2/19/2019
 */
public class MinAndSumInArray
{
    /** Task: Recursively finds the smallest value in an array of integers.
     * @param data an integer array
     * @param first the location to start looking for the minimum
     * @param last the location to stop looking for the minimum
     * first <= last
     */
    public static int minimum(int[] data, int first, int last)
    {
        // TODO Project #3a

        // If you divide the array into two pieces - halves, for example - and find the smallest integer
        // in each of the pieces, the smallest integer in the entire array will be the smaller
        // of these two integers. Since you will be searching a portion of the array - for example, the elements
        // array[first] through array[last] - it will be convenient for your method to have three parameters:
        // the array and two indices, first and last.
        // NOTE: You can refer to the method displayArray in Segment 7.18 in the textbook for the inspiration


        // IMPLEMENT THE METHOD

        // STEP #1 validate the input, if incorrect throw BadArgumentsForMinimumException with appropriate message
        if(data == null || data.length == 0 || first < 0 || last > data.length - 1 || first > last)
        {
            throw new BadArgumentsForMinimumException("Error in arguments");
        }


        // STEP #2 base case: first and last are the same

        if (first == last)
        {
            return data[first];
        }



        // STEP #3 recursive case

        // recursively find minimum in each half
        int mid = (first + last) / 2;


        // set result to the smallest of the two
        return Math.min(minimum(data, first, mid), minimum(data, mid + 1, last));

       // return result;

        //TODO done
    } // end minimum

    // TODO Project #3b #3c #3d #3e
    // Write four different recursive methods that each compute the sum of the integers in an array of integers
    // where the elements in all solutions are added in the order of their position in the array.
    // Model your methods after the displayArray methods given in Segments 7.15 through 7.18
    //
    // #2b sumArrayStartWithFirst – add all the elements considering the first element and recursively considering the rest
    // #2c sumArrayStartWithLast – add all the elements considering the last element and recursively consider the rest
    // #2d sumArraySplitInTwo – “divide and conquer” solution – divide the array in two pieces and process
    //                           each of the pieces separately (see the minimum description above). In this solution
    //                           computed middle element must be included in the left “half” of the array
    // #2e sumArraySplitInTwoAddMid – “divide and conquer” solution – divide the array in two pieces and process
    //                           each of the pieces separately. In this solution however, the middle element should
    //                           be excluded from both “halves”, instead you recursively add elements in the left half,
    //                           add the middle element, and add recursively elements from the right half.
    //

    public static int sumArrayStartWithFirst(int[] array, int first, int last)
    {
        // TODO Project #3b
        //#2b sumArrayStartWithFirst – add all the elements considering the first element and recursively considering the rest

        if (first == last)
        {
            return array[first];
        }

            return array[first] + sumArrayStartWithFirst(array, first + 1, last);

    //TODO done
    } // end sumArrayStartWithFirst

    public static int sumArrayStartWithLast(int[] array, int first, int last)
    {
        // TODO Project #3c

       // #2c sumArrayStartWithLast – add all the elements considering the last element and recursively consider the rest

        if (first == last)
        {
            return array[first];
        }



        return array[last] + sumArrayStartWithFirst(array, first, last - 1);
    //TODO done
    } // end sumArrayStartWithLast

    public static int sumArraySplitInTwo(int[] array, int first, int last)
    {
        // TODO Project #3d

        // #2d sumArraySplitInTwo – “divide and conquer” solution – divide the array in two pieces and process
        //                           each of the pieces separately (see the minimum description above). In this solution
        //                           computed middle element must be included in the left “half” of the array

        if (first == last)
        {
            return array[first];
        }

        // recursively find minimum in each half
        int mid = (first + last) / 2;

        return (sumArraySplitInTwo(array, first, mid) + sumArraySplitInTwo(array, mid + 1, last));
        //TODO done
    } // end sumArraySplitInTwo

    public static int sumArraySplitInTwoAddMid(int[] array, int first, int last)
    {
        // TODO Project #3e

        // #2e sumArraySplitInTwoAddMid – “divide and conquer” solution – divide the array in two pieces and process
        //                           each of the pieces separately. In this solution however, the middle element should
        //                           be excluded from both “halves”, instead you recursively add elements in the left half,
        //                           add the middle element, and add recursively elements from the right half.
        //


        //Recursive

        int mid = first + ( last - first ) / 2;

        if (first == last)
        {
            return array[first];
        }
        if(mid == first)
        {
            return array[mid] + sumArraySplitInTwoAddMid(array, mid + 1, last);
        }
        return sumArraySplitInTwoAddMid(array, first, mid - 1) + array[mid] + sumArraySplitInTwoAddMid(array, mid + 1, last);
    //TODO done
    } // end sumArraySplitInTwoAddMid

    public static void main(String args[])
    {
        MinAndSumInArray tester = new MinAndSumInArray();
        tester.testForExceptions();
        tester.testGoodValues();
    }

    public void testForExceptions()
    {
        System.out.println("TESTING TO SEE IF IT CATCHES BAD ARGUMENTS");

        MinAndSumInArray rm = new MinAndSumInArray();
        System.out.println("Trying a null array");
        int array[] = null;
        try
        {
            rm.minimum(array, 0, 0);
            System.out.println("*** Failed test (did not throw exception)");
        }
        catch(BadArgumentsForMinimumException e)
        {
            System.out.println("    Passed test");
        }


        System.out.println("Trying an array of size 0");
        array = new int[0];
        try
        {
            rm.minimum(array, 0, 0);
            System.out.println("*** Failed test (did not throw exception)");
        }
        catch(BadArgumentsForMinimumException e)
        {
            System.out.println("    Passed test");
        }

        System.out.println("Trying from less than 0");
        array = new int[5];
        try
        {
            rm.minimum(array, -1, 0);
            System.out.println("*** Failed test (did not throw exception)");
        }
        catch(BadArgumentsForMinimumException e)
        {
            System.out.println("    Passed test");
        }


        System.out.println("Trying to greater than size");
        try
        {
            rm.minimum(array, 0, 5);
            System.out.println("*** Failed test (did not throw exception)");
        }
        catch(BadArgumentsForMinimumException e)
        {
            System.out.println("    Passed test");
        }


        System.out.println("Trying from > to");
        try
        {
            rm.minimum(array, 1, 0);
            System.out.println("*** Failed test (did not throw exception)");
        }
        catch(BadArgumentsForMinimumException e)
        {
            System.out.println("    Passed test");
        }

        System.out.println();
        System.out.println();

    }


    public void testGoodValues()
    {
        System.out.println("TESTING TO SEE IF IT GETS THE CORRECT RESULTS");

        MinAndSumInArray rm = new MinAndSumInArray();

        int array[] = {4};
        System.out.println("Trying an array of size 1: " + Arrays.toString(array));
        int smallest = rm.minimum(array, 0, 0);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 4)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 4");
        int sum1 = rm.sumArrayStartWithFirst(array, 0, 0);
        System.out.println("sum method1 = " + sum1);
        int sum2 = rm.sumArrayStartWithLast(array, 0, 0);
        System.out.println("sum method2 = " + sum2);
        int sum3 = rm.sumArraySplitInTwo(array, 0, 0);
        System.out.println("sum method3 = " + sum3);
        int sum4 = rm.sumArraySplitInTwoAddMid(array, 0, 0);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 4 && sum2 == 4 && sum3 == 4 && sum4 == 4)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 4");

        int array2s[] = {2, 2};
        System.out.println("\nTrying an array of size 2, the same elements: " + Arrays.toString(array2s));
        smallest = rm.minimum(array2s, 0, 1);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 2)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 2");
        sum1 = rm.sumArrayStartWithFirst(array2s, 0, 1);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array2s, 0, 1);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array2s, 0, 1);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array2s, 0, 1);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 4 && sum2 == 4 && sum3 == 4 && sum4 == 4)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 4");

        int array2[] = {3, 2};
        System.out.println("\nTrying an array of size 2, second is smallest: " + Arrays.toString(array2));
        smallest = rm.minimum(array2, 0, 1);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 2)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 2");
        sum1 = rm.sumArrayStartWithFirst(array2, 0, 1);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array2, 0, 1);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array2, 0, 1);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array2, 0, 1);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 5 && sum2 == 5 && sum3 == 5 && sum4 == 5)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 5");

        int array3[] = {3, 4};
        System.out.println("\nTrying an array of size 2, first is smallest: " + Arrays.toString(array3));
        smallest = rm.minimum(array3, 0, 1);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 3)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 3");
        sum1 = rm.sumArrayStartWithFirst(array3, 0, 1);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array3, 0, 1);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array3, 0, 1);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array3, 0, 1);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 7 && sum2 == 7 && sum3 == 7 && sum4 == 7)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 7");


        int array4[] = {3, 10, 4};
        System.out.println("\nTrying an array of size 3, first is smallest: " + Arrays.toString(array4));
        smallest = rm.minimum(array4, 0, 2);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 3)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 3");
        sum1 = rm.sumArrayStartWithFirst(array4, 0, 2);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array4, 0, 2);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array4, 0, 2);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array4, 0, 2);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 17 && sum2 == 17 && sum3 == 17 && sum4 == 17)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 17");


        int array5[] = {10, 4, 13};
        System.out.println("\nTrying an array of size 3, second is smallest: " + Arrays.toString(array5));
        smallest = rm.minimum(array5, 0, 2);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 4)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 4");
        sum1 = rm.sumArrayStartWithFirst(array5, 0, 2);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array5, 0, 2);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array5, 0, 2);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array5, 0, 2);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 27 && sum2 == 27 && sum3 == 27 && sum4 == 27)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 27");

        int array6[] = {10, 13, 4};
        System.out.println("\nTrying an array of size 3, third is smallest: " + Arrays.toString(array6));
        smallest = rm.minimum(array6, 0, 2);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 4)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 4");
        sum1 = rm.sumArrayStartWithFirst(array6, 0, 2);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array6, 0, 2);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array6, 0, 2);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array6, 0, 2);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 27 && sum2 == 27 && sum3 == 27 && sum4 == 27)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 27");


        int array7[] = {10, 113, 25, 10, 35, 14, 29, 13, 14, 110, 13, 17, 34, 83, 9, 32, 44, 12, 90, 2};
        System.out.println("\nTrying an array of size 20: " + Arrays.toString(array7));
        System.out.println("--> Looking at all values - at indexes 0 ... 19");
        smallest = rm.minimum(array7, 0, 19);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 2)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 2");
        sum1 = rm.sumArrayStartWithFirst(array7, 0, 19);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array7, 0, 19);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array7, 0, 19);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array7, 0, 19);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 709 && sum2 == 709 && sum3 == 709 && sum4 == 709)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 709");

        System.out.println("--> Looking at first third - at indexes 0 ... 6");
        smallest = rm.minimum(array7, 0, 6);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 10)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 10");
        sum1 = rm.sumArrayStartWithFirst(array7, 0, 6);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array7, 0, 6);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array7, 0, 6);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array7, 0, 6);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 236 && sum2 == 236 && sum3 == 236 && sum4 == 236)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 236");

        System.out.println("--> Looking at second third - at indexes 7 ... 13");
        smallest = rm.minimum(array7, 7, 13);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 13)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 13");
        sum1 = rm.sumArrayStartWithFirst(array7, 7, 13);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array7, 7, 13);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array7, 7, 13);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array7, 7, 13);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 284 && sum2 == 284 && sum3 == 284 && sum4 == 284)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 284");

        System.out.println("--> Looking at last third excluding the last value - at indexes 13 ... 18");
        smallest = rm.minimum(array7, 13, 18);
        System.out.println("the smallest value = " + smallest);
        if (smallest == 9)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: returned smallest value of " + smallest + " is not expected value of 9");
        sum1 = rm.sumArrayStartWithFirst(array7, 13, 18);
        System.out.println("sum method1 = " + sum1);
        sum2 = rm.sumArrayStartWithLast(array7, 13, 18);
        System.out.println("sum method2 = " + sum2);
        sum3 = rm.sumArraySplitInTwo(array7, 13, 18);
        System.out.println("sum method3 = " + sum3);
        sum4 = rm.sumArraySplitInTwoAddMid(array7, 13, 18);
        System.out.println("sum method4 = " + sum4);
        if (sum1 == 270 && sum2 == 270 && sum3 == 270 && sum4 == 270)
            System.out.println("    Passed test");
        else
            System.out.println("*** Failed test: computed sum is not expected value of 270");

        System.out.println();
        System.out.println();
    }
}
