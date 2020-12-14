

import java.util.*;

/**
 * *****************************************************************
 * Class for sorting an array of Comparable objects from smallest to
 * largest.
 * <p/>
 * This code is from Chapter 8 of
 * Data Structures and Abstractions with Java 4/e by Carrano
 * <p/>
 * Modified by atb
 * <p/>
 *
 * @author Sean Blanchard
 * @version 2/26/2019
 * ******************************************************************
 */
public class SortArray
{
    public static int moves = 0;
    public static int comparisons = 0;
    public static final int SELECTION_SORT = 0;
    public static final int INSERTION_SORT = 1;
    public static final int SHELL_SORT = 2;
    public static final int IMPROVED_SHELL_SORT = 3;
    public static final int BUBBLE_SORT = 4;
    public static final int IMPROVED_BUBBLE_SORT = 5;
    public static final int SHAKER_IMPROVED_BUBBLE_SORT = 6;
    public static final String SORT_NAMES[] = {"SELECTION", "INSERTION", "SHELL", "IMPROVED SHELL",
            "BUBBLE", "IMPROVED BUBBLE", "SHAKER IMPROVED BUBBLE"};
    public static final int NUMBER_OF_SORTS = 7;


    /**************************************************************
     * ITERATIVE SELECTION SORT
     **************************************************************/


    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void selectionSort(T[] a)
    {
        for (int index = 0; index < a.length - 1; index++)
        {
            int indexOfNextSmallest = getIndexOfSmallest(a, index, a.length - 1);
            swap(a, index, indexOfNextSmallest);
        } // end for
    } // end selectionSort


    /**
     * Task: Finds the index of the smallest value in an array.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= 0 that is the index of the last
     *              array element to consider
     * @return the index of the smallest value among
     * a[first], a[first+1], . . . , a[last]
     */
    public static <T extends Comparable<? super T>>
    int getIndexOfSmallest(T[] a, int first, int last)
    {
        T min = a[first];
        int indexOfMin = first;
        for (int index = first + 1; index <= last; index++)
        {
            comparisons++;
            if (a[index].compareTo(min) < 0)
            {
                min = a[index];
                indexOfMin = index;
            } // end if
        } // end for
        return indexOfMin;
    } // end getIndexOfSmallest


    /**
     * Task: Swaps the array elements a[i] and a[j].
     *
     * @param a an array of  objects
     * @param i an integer >= 0 and < a.length
     * @param j an integer >= 0 and < a.length
     */
    private static <T>
    void swap(T[] a, int i, int j)
    {
        if (i != j)
        {
            T temp = a[i];
            a[i] = a[j];
            a[j] = temp;

            moves += 2;
        }
    } // end swap


    /**************************************************************
     * ITERATIVE INSERTION SORT (WE ARE NOT USING THE RECURSIVE VERSION
     * TO AVOID STACK OVERFLOWS)
     **************************************************************/


    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void insertionSort(T[] a
    )
    {
        insertionSort(a, 0, a.length - 1);
    } // end insertionSort


    /**
     * Task: Iterative insertion sort of the  objects in a range of locations in an array into ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0
     * @param last  an integer > first and < a.length
     */

    public static <T extends Comparable<? super T>>
    void insertionSort(T[] a, int first, int last)
    {
        T temp;
        boolean foundLocation;
        int loc;

        for (int i = first + 1; i <= last; i++)
        {
            temp = a[i];

            // move values over to make room
            loc = i - 1;  // start with value to the left
            foundLocation = false;
            while (loc >= first && !foundLocation)
            {
                comparisons++;
                if (a[loc].compareTo(temp) > 0)
                {
                    moves++;
                    a[loc + 1] = a[loc];
                    loc--;
                }
                else
                    foundLocation = true;  // found the spot
            }

            // put the value in the right place
            moves++;
            a[loc + 1] = temp;
        } // end for
    } // end insertionSort


    /**************************************************************
     * ITERATIVE SHELL SORT
     **************************************************************/

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void shellSort(T[] a)
    {
        shellSort(a, 0, a.length - 1);
    } // end shellSort


    /**
     * Task: Use incremental insertion sort with different increments to
     * sort a range of values in the array
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0
     * @param last  an integer > first and < a.length
     */
    public static <T extends Comparable<? super T>>
    void shellSort(T[] a, int first, int last)
    {
        int n = last - first + 1; // number of array elements
        for (int space = n / 2; space > 0; space = space / 2)
        {
            for (int begin = first; begin < first + space; begin++)
                incrementalInsertionSort(a, begin, last, space);
        } // end for
    } // end shellSort


    /**
     * Task: Sorts equally spaced elements of an array into
     * ascending order.
     *
     * @param a     an array of Comparable objects
     * @param first an integer >= 0 that is the index of the first
     *              array element to consider
     * @param last  an integer >= first and < a.length that is the
     *              index of the last array element to consider
     * @param step  the difference between the indices of the
     *              elements to sort
     */
    public static <T extends Comparable<? super T>>
    void incrementalInsertionSort(T[] a, int first, int last, int step)
    {
        int unsorted, index;
        for (unsorted = first + step; unsorted <= last; unsorted = unsorted + step)
        {
            T firstUnsorted = a[unsorted];

            for (index = unsorted - step; index >= first; index = index - step)
            {
                comparisons++;
                if (firstUnsorted.compareTo(a[index]) >= 0)
                    break;
                else
                {
                    moves++;
                    a[index + step] = a[index];
                }
            } // end for
            moves++;
            a[index + step] = firstUnsorted;
        } // end for
    } // end incrementalInsertionSort


    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void improvedShellSort(T[] a)
    {
        shellSortWithNoEvenStep(a, 0, a.length - 1);
    } // end shellSort

    public static <T extends Comparable<? super T>>
    void shellSortWithNoEvenStep(T[] a, int first, int last)
    {
        int n = last - first + 1; // number of array elements
        for (int step = n / 2; step > 0; step = step / 2)
        {
            //TODO Project1 C
            // YOUR CODE GOES HERE

            if(step % 2 == 0)
            {
            step++;
            }
                for (int begin = first; begin < first + step; begin++)
                    incrementalInsertionSort(a, begin, last, step);
            }
            //TODO done
        } // end for
   // } // end shellSortWithNoEvenStep

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void bubbleSort(T[] a)
    {
        int leftIndex = 0; // first position to check
        int rightIndex = a.length - 1; // last position to check

        startStatistics();
        while (leftIndex < rightIndex)
        {
            for (int index = leftIndex; index < rightIndex; index++)
            {
                comparisons++;
                if (a[index].compareTo(a[index + 1]) > 0)
                {
                    swap(a, index, index + 1);
                } // end if
            } // end for
            rightIndex--;
        } // end while
    }

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void improvedBubbleSort(T[] a)
        {

        //TODO Project1 D


            int leftIndex = 0; // first position to check
            int rightIndex = a.length - 1; // last position to check
           // boolean swapped = true;
            int prevSwap;

            startStatistics();
            while (leftIndex < rightIndex)
            {
                prevSwap = 0;

                for (int index = leftIndex; index < rightIndex; index++)
                {
                    comparisons++;
                    if (a[index].compareTo(a[index + 1]) > 0)
                    {
                        swap(a, index, index + 1);
                        prevSwap = index;
                    } // end if
                } // end for

                rightIndex = prevSwap;

                //TODO Done




            //-----------------------------------------------------------------------
//
//        int leftIndex = 0; // first position to check
//        int rightIndex = a.length - 1; // last position to check
//        boolean swapped = true;
//        final int NOT_FOUND = -1;
//        int lastSwap, firstSwap;
//
//        startStatistics();
//        while (swapped && leftIndex < rightIndex)
//        {
//            swapped = false;
//            lastSwap = NOT_FOUND;
//            firstSwap = NOT_FOUND;
//
//            for (int index = leftIndex; index < rightIndex; index++)
//            {
//                comparisons++;
//                if (a[index].compareTo(a[index + 1]) > 0)
//                {
//                    if(!swapped)
//                    {
//                    swapped = true;
//                }
//                    if(firstSwap < 0)
//                    {
//                        firstSwap = index;
//                    }
//                    if(index > lastSwap)
//                    {
//                        lastSwap = index;
//                    }
//                    swap(a, index, index + 1);
//                } // end if
//            } // end for
//
//            rightIndex = (lastSwap >= 0) ? lastSwap : (rightIndex - 1);
//

            //rightIndex--;
        } // end while



//---------------------------------------------------------------------------------

//
//            int leftIndex = 0; // first position to check
//            int rightIndex = a.length - 1; // last position to check
//            int ind = 0;
//            Boolean bool = false;
//            startStatistics();
//            while (leftIndex < rightIndex)
//            {
//                for (int index = leftIndex; index < rightIndex; index++)
//                {
//                    comparisons++;
//                    bool = true;
//                    if (a[index].compareTo(a[index + 1]) > 0)
//                    {
//                        swap(a, index, index + 1);
//                        bool = false;
//                    } // end if
//                } // end for
//                if (!bool)
//                    rightIndex= leftIndex;
//                else
//                    rightIndex--;
//            } // end while




        //TODO Project1 F
    }

    /**
     * Task: Sorts the first n objects in an array into ascending order.
     *
     * @param a an array of Comparable objects
     */
    public static <T extends Comparable<? super T>>
    void shakerImprovedBubbleSort(T[] a)
    {

        //TODO Project1 E

//        int leftIndex = 0; // first position to check
//        int rightIndex = a.length - 1; // last position to check
//        boolean moved = false;
//
//        startStatistics();
//        while (leftIndex < rightIndex)
//        {
//            moved = false;
//            for (int index = leftIndex; index < rightIndex; index++)
//            {
//                comparisons++;
//                if (a[index].compareTo(a[index + 1]) > 0)
//                {
//                    swap(a, index, index + 1);
//                    moved = true;
//                } // end if
//                rightIndex--;
//            } // end for
//            //rightIndex--;
//
//            if(!moved) break;
//            for(int index = rightIndex - 1; index >= leftIndex; index--)
//            {
//                comparisons++;
//                if (a[index].compareTo(a[index + 1]) > 0)
//                {
//                    swap(a, index, index + 1);
//                    moved = true;
//                } // end if
//                leftIndex++;
//            }
//            //leftIndex++;
//        } // end while
//        while(moved);

        //TODO recheck

        int leftIndex = 0;
        int rightIndex = a.length - 1;

        startStatistics();
        int pass = 0;

        boolean swapped = true;
        while (swapped && leftIndex < rightIndex)
        {
            swapped = false;
            int lastSwap = -1;
            int firstSwap = -1;
            for (int index = leftIndex; index < rightIndex; index++)
            {
                comparisons++;
                if (a[index].compareTo(a[index + 1]) > 0)
                {
                    if (!swapped) {
                        swapped = true;
                    }
                    if (firstSwap < 0) {
                        firstSwap = index;
                    }
                    if (index > lastSwap) {
                        lastSwap = index;
                    }
                    swap(a, index, index + 1);
                }
            }
            rightIndex = (lastSwap >= 0) ? lastSwap : (rightIndex - 1);
            //System.out.println("PASS: " + ++pass + " leftIndex: " + leftIndex + " rightIndex: " + rightIndex);

            if (swapped) {
                swapped = false;
                lastSwap = -1;
                firstSwap = -1;
                for (int index = rightIndex; index > leftIndex; index--) {
                    comparisons++;
                    if (a[index].compareTo(a[index - 1]) < 0) {
                        if (!swapped) {
                            swapped = true;
                        }
                        if (firstSwap < 0) {
                            firstSwap = index;
                        }
                        if (index < lastSwap) {
                            lastSwap = index;
                        }
                        swap(a, index, index - 1);
                    }
                }
                leftIndex = (lastSwap >= 0) ? lastSwap : (leftIndex + 1);
                //System.out.println("PASS: " + ++pass + " leftIndex: " + leftIndex + " rightIndex: " + rightIndex);
            }
        }



        //TODO done
        //TODO Project1 F

    }


    // ************ TEST DRIVER *****************

    public static void main(String args[])
    {
        Integer dataRandom[];

        System.out.println("What size arrays should be used?");
        int arraySize = getInt("   It should be an integer value greater than or equal to 1.");

        dataRandom = generateRandomArray(arraySize);
        runStatistics(dataRandom);
        System.out.println("****************DONE********************\n");
    }

    /**
     * Runs statistics using each of the supported sorts sorting the same array
     * first in the original random order,
     * next in ascending order, and finally in descending order.
     * Calculates total only for the sorts of arrays in random order
     */

    private static void runStatistics(Integer dataOriginal[])
    {
        Integer data[];

        for (int sort = 0; sort < NUMBER_OF_SORTS; sort++)
        {
            System.out.println("\n*** ANALYZING " + SORT_NAMES[sort] + " SORT ***");
            System.out.println("---> ARRAY IN RANDOM ORDER");
            data = Arrays.copyOf(dataOriginal, dataOriginal.length);
            display(data);
            startStatistics();
            switch (sort)
            {
                case SELECTION_SORT:
                    selectionSort(data);
                    break;
                case INSERTION_SORT:
                    insertionSort(data);
                    break;
                case SHELL_SORT:
                    shellSort(data);
                    break;
                case IMPROVED_SHELL_SORT:
                    improvedShellSort(data);
                    break;
                case BUBBLE_SORT:
                    bubbleSort(data);
                    break;
                case IMPROVED_BUBBLE_SORT:
                    improvedBubbleSort(data);
                    break;
                case SHAKER_IMPROVED_BUBBLE_SORT:
                    shakerImprovedBubbleSort(data);
                    break;
                default:
                    System.out.println("NOT SUPPORTED SORT");
            }
            displayStatistics();
            System.out.println("The sorted array is: ");
            display(data);

            System.out.println("---> ARRAY SORTED IN ASCENDING ORDER");
            System.out.println("The array is: ");
            display(data);
            startStatistics();
            switch (sort)
            {
                case SELECTION_SORT:
                    selectionSort(data);
                    break;
                case INSERTION_SORT:
                    insertionSort(data);
                    break;
                case SHELL_SORT:
                    shellSort(data);
                    break;
                case IMPROVED_SHELL_SORT:
                    improvedShellSort(data);
                    break;
                case BUBBLE_SORT:
                    bubbleSort(data);
                    break;
                case IMPROVED_BUBBLE_SORT:
                    improvedBubbleSort(data);
                    break;
                case SHAKER_IMPROVED_BUBBLE_SORT:
                    shakerImprovedBubbleSort(data);
                    break;
                default:
                    System.out.println("NOT SUPPORTED SORT");
            }
            displayStatistics();
            System.out.println("The sorted array is: ");
            display(data);

            System.out.println("---> ARRAY SORTED IN DESCENDING ORDER");
            reverseOrder(data);
            System.out.println("The array is: ");
            display(data);
            startStatistics();
            switch (sort)
            {
                case SELECTION_SORT:
                    selectionSort(data);
                    break;
                case INSERTION_SORT:
                    insertionSort(data);
                    break;
                case SHELL_SORT:
                    shellSort(data);
                    break;
                case IMPROVED_SHELL_SORT:
                    improvedShellSort(data);
                    break;
                case BUBBLE_SORT:
                    bubbleSort(data);
                    break;
                case IMPROVED_BUBBLE_SORT:
                    improvedBubbleSort(data);
                    break;
                case SHAKER_IMPROVED_BUBBLE_SORT:
                    shakerImprovedBubbleSort(data);
                    break;
                default:
                    System.out.println("NOT SUPPORTED SORT");
            }
            displayStatistics();
            System.out.println("The sorted array is: ");
            display(data);
        }
    }

    /**
     * Initializes counters for the statistics of the single run of the analyzed sort
     */
    private static void startStatistics()
    {
        moves = 0;
        comparisons = 0;
    }

    /**
     * Displays statistics for the current run of the analyzed sort
     */
    private static void displayStatistics()
    {
        System.out.println("Number of comparisons --> " + comparisons);
        System.out.println("Number of moves --> " + moves);
    }

    /**
     * Reverses the order of elements in the given array
     *
     * @param data array that in this application is sorted in ascending order
     */

    private static void reverseOrder(Integer[] data)
    {
        //TODO Project1 A

        int setData = data.length;
        int halfData = data.length/2;

        for ( int i = 0; i < halfData; i++)
        {
            //data[i] = data[setData - i - 1];
            swap(data, i, setData - i - 1);
            //System.out.println("HERE" + Arrays.toString(data));
        }



        //TODO not done - Think it is done

    }

    /**
     * Generate an array of random integer values.  The values will be between 0 and 99.
     *
     * @param size the size of the array to generate
     * @return the array of integers
     */
    private static Integer[] generateRandomArray(int size)
    {
        Integer result[] = new Integer[size];

        //TODO Project1 A

        final int MAX_VALUE = 99;

        Random rand = new Random(101);
        //rand = generateRandomArray();

        for(int i = 0; i < size; i++)
        {
            result[i] = rand.nextInt(MAX_VALUE + 1);
        }


        return result;
    }

    /**
     * Displays all elements of an array of Objects
     *
     * @param data the array to display
     */
    private static void display(Object[] data)
    {
        System.out.print("[ ");
        for (int i = 0; i < data.length; i++)
        {
            System.out.print(data[i] + " ");
        }
        System.out.println("]");
    }


    /**
     * Get an integer value
     *
     * @param rangePrompt String representing a message used to ask the user for input
     * @return an integer
     */
    private static int getInt(String rangePrompt)
    {
        Scanner input;
        int result = 10;        //default value is 10
        try
        {
            input = new Scanner(System.in);
            System.out.println(rangePrompt);
            result = input.nextInt();

        } catch (NumberFormatException e)
        {
            System.out.println("Could not convert input to an integer");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        } catch (Exception e)
        {
            System.out.println("There was an error with System.in");
            System.out.println(e.getMessage());
            System.out.println("Will use 10 as the default value");
        }
        return result;
    }
}// end SortArray
