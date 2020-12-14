
import java.util.*;
import java.util.InputMismatchException;

/**
 * A class for implementing and testing kthItem method
 *
 * @author Sean Blanchard
 * @version 3/4/2019
 */


public class KthElement
{
    /**
     * Task: Sorts the first, middle, and last elements of an
     * array into ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first the integer index of the first array element; first >= 0
     * @param mid   the integer index of the middle array element
     * @param last  the integer index of the last array element;
     *              last - first >= 2, last < a.length
     */
    private static <T extends Comparable<? super T>>
    void sortFirstMiddleLast(T[] a, int first, int mid, int last)
    {
        orderTwoItems(a, first, mid); // make a[first] <= a[mid]
        orderTwoItems(a, mid, last); // make a[mid] <= a[last]
        orderTwoItems(a, first, mid); // make a[first] <= a[mid]
    } // end sortFirstMiddleLast

    /**
     * Task: Orders two given array elements into ascending order
     * so that a[i] <= a[j].
     *
     * @param a an array of Comparable objects
     * @param i an integer >= 0 and < array.length
     * @param j an integer >= 0 and < array.length
     */
    private static <T extends Comparable<? super T>>
    void orderTwoItems(T[] a, int i, int j)
    {
        if (a[i].compareTo(a[j]) > 0)
            swap(a, i, j);
    } // end orderToItems

    /**
     * Task: Swaps the array elements a[i] and a[j].
     *
     * @param a an array of  objects
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length
     *          assumes that i != j
     */
    private static <T>
    void swap(T[] a, int i, int j)
    {
        T pivotIndex = a[i];
        a[i] = a[j];
        a[j] = pivotIndex;
    } // end swap


    /**
     * Task: Partitions an array as part of quick sort into two subarrays
     * called Smaller and Larger that are separated by a single
     * element called the pivot.
     * Elements in Smaller are left of the pivot and <= pivot.
     * Elements in Larger are right of the pivot and >= pivot.
     *
     * @param a     an array of Comparable objects
     * @param first the integer index of the first array element;
     *              first >= 0
     * @param last  the integer index of the last array element;
     *              last >= first; last < a.length
     * @return the index of the pivot
     */
    private static <T extends Comparable<? super T>>
    int partition(T[] a, int first, int last)
    {
        int mid = first + (last - first) / 2;
        sortFirstMiddleLast(a, first, mid, last);

        int pivotIndex = mid;

        if (last - first + 1 > 3)
        {

            // Assertion: The pivot is a[mid]; a[first] <= pivot and
            // a[last] >= pivot, so do not compare these two array entries
            // with pivot.

            // Move pivot to next-to-last position in array
            swap(a, mid, last - 1);
            pivotIndex = last - 1;
            T pivotValue = a[pivotIndex];

            // Determine subarrays Smaller = a[first..endSmaller]
            // and                 Larger  = a[endSmaller+1..last-1]
            // such that entries in Smaller are <= pivotValue and
            // entries in Larger are >= pivotValue; initially, these subarrays are empty

            int indexFromLeft = first + 1;
            int indexFromRight = last - 2;

            boolean done = false;
            while (!done)
            {
                // pivotIndexing at beginning of array, leave entries that are < pivotValue;
                // locate first entry that is >= pivotValue; you will find one,
                // since last entry is >= pivot
                while (a[indexFromLeft].compareTo(pivotValue) < 0)
                    indexFromLeft++;

                // pivotIndexing at end of array, leave entries that are > pivot;
                // locate first entry that is <= pivot; you will find one,
                // since first entry is <= pivot

                while (a[indexFromRight].compareTo(pivotValue) > 0)
                    indexFromRight--;

                assert a[indexFromLeft].compareTo(pivotValue) >= 0 &&
                        a[indexFromRight].compareTo(pivotValue) <= 0;

                if (indexFromLeft < indexFromRight)
                {
                    swap(a, indexFromLeft, indexFromRight);
                    indexFromLeft++;
                    indexFromRight--;
                }
                else
                    done = true;
            }

            // Place pivotValue between the subarrays Smaller and Larger
            swap(a, pivotIndex, indexFromLeft);
            pivotIndex = indexFromLeft;

            // Assertion:
            //   Smaller = a[first..pivotIndex-1]
            //   Pivot = a[pivotIndex]
            //   Larger = a[pivotIndex+1..last]
        }
        return pivotIndex;
    } // end partition

    /**************************************************************
     * KTH ORDER STATISTIC - ALGORITHM THAT USES PARTITION
     *        ARRAY MAY BE SORTED OR UNSORTED
     **************************************************************/


    /**
     * Task: Find the kth item of the first n items in sorted order
     *
     * @param a an array of Comparable objects
     * @param n an integer >= 0 and less than or equal to a.length
     * @param k an integer >= 0 and less than or equal to n
     */
    public static <T extends Comparable<? super T>>
    T kthItem(T[] a, int k, int n)
    {
        return kthItem(a, k, 0, n - 1);
    } // end kthItem


    /**
     * Recursively finds the kth item in a array of items
     * utilizing partitioning strategy of quick sort.
     *
     * @param a     an array of Comparable objects
     * @param k     an integer >= 0 that is the position of the item to return.
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= 0 that is the index of the last
     *              array element to consider
     */
    private static <T extends Comparable<? super T>>
    T kthItem(T[] a, int k, int first, int last)
    {
        //TODO Project1

        //System.out.println("call: " + k + " " + first + " " + last + " ");

        // IMPLEMENT kthItem METHOD RECURSIVELY
        // HINT: call partition(a, first, last) method to get the pivotIndex

        int pivotIndex = partition(a, first, last);
        if(pivotIndex == k)
        {
            //if pivot index i the same as k, the kth smallest element is the entry at the pivot index
            return a[pivotIndex];
        }
        else if (pivotIndex > k)
        {
            //if Smaller sub-array contains k or more entries, it must contain the kth smallest entry
            return kthItem(a, k, first, pivotIndex-1);
        }
        else
        {
            //otherwise the kth element is in Larger sub-array
            return kthItem(a, k, pivotIndex+1, last);
        }


        //return null; //THIS IS A STUB
    } // end kthItem

    public static int getNextPrime(int integer)
    {
        // if even, add 1 to make odd
        if (integer % 2 == 0)
        {
            integer++;
        }

        // test odd integers
        while (!isPrime(integer))
        {
            integer = integer + 2;
        }

        return integer;
    } // end getNextPrime

    private static boolean isPrime(int integer)
    {
        boolean result;
        boolean done = false;

        // 1 and even numbers are not prime
        if ((integer == 1) || (integer % 2 == 0))
        {
            result = false;
        }

        // 2 and 3 are prime
        else if ((integer == 2) || (integer == 3))
        {
            result = true;
        }

        else // integer is odd and >= 5
        {
            assert (integer % 2 != 0) && (integer >= 5);

            // a prime is odd and not divisible by every odd integer up to its square root
            result = true; // assume prime
            for (int divisor = 3; !done && (divisor * divisor <= integer); divisor = divisor + 2)
            {
                if (integer % divisor == 0)
                {
                    result = false; // divisible; not prime
                    done = true;
                }
            }
        }

        return result;
    } // end isPrime

    public static void main(String args[])
    {
        int arraySize = 0;
        int trials = 0;
        int seed = 0;
        final int MAX_RANDOM_NUMBER = 99;
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

                System.out.println("What seed should be used?" +
                        "\n It should be an integer value greater than or equal to 1.");
                seed = keyboard.nextInt();
            } catch (InputMismatchException ime)
            {
                System.out.println("Could not convert input to an integer");
                invalidInput = true;
            } catch (Exception e)
            {
                System.out.println("There was an error with System.in");
                System.out.println(e.getMessage());
                invalidInput = true;
            }
        } while (invalidInput);

        // pivotIndex testing
        Integer data[];
        Integer forTesting[];
        for (int i = 1; i <= trials; i++)
        {
            System.out.println("\nTRIAL #" + i);
            Random generator = new Random(seed);
            // create an array and fill it with random values between 0 and MAX_RANDOM exclusive
            data = new Integer[arraySize];
            for (int j = 0; j < arraySize; j++)
            {
                data[j] = generator.nextInt(MAX_RANDOM_NUMBER + 1);
            }
            System.out.println("The original array is: ");
            System.out.println(Arrays.toString(data));
            // make a copy of the original array we will sort it and use it for comparing result
            forTesting = Arrays.copyOf(data, arraySize);
            Arrays.sort(forTesting);

            System.out.println("The original array sorted would be: ");
            System.out.println(Arrays.toString(forTesting));

            boolean failed = false;
            for (int k = 0; k < arraySize; k++)
            {
                Object result = kthItem(data, k, arraySize);
                // we want to test the code as much as possible but the following case is what we want to print
                if (k == (arraySize / 2))
                {
                    System.out.println(">>> kthItem found median at index " + k + " with value of " + result + " <<<");
                }

                if (!result.equals(forTesting[k]))
                {
                    failed = true;
                    System.out.println("    fails - item at k equal to " + k + " is : " + forTesting[k] +
                            " got " + result + " instead");
                }
            }
            if (!failed)
                System.out.println("    passes");

            seed = getNextPrime(++seed);
        }
    }
}