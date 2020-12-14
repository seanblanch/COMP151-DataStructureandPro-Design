package Lab12;

import java.lang.reflect.Array;
import java.util.*;

/**
 * A class that implements solutions to several problems using hashing
 *
 *
 * @author Sean Blanchard
 * @version 4/16/2019
 */
public class SolveWithHashing
{
    private DictionaryInterface<Integer, Integer> hashedDictionary;
    private final int DEFAULT_CAPACITY = 5;

    public SolveWithHashing()
    {
        createHashedDictionary();
    }

    public void createHashedDictionary()
    {
        this.hashedDictionary = new HashedDictionary<>(DEFAULT_CAPACITY);
    }

    private void testDisplayHashTable()
    {
        System.out.println("displaying empty dictionary");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.add(1, 1);
        this.hashedDictionary.add(7, 7);
        System.out.println("displaying dictionary after 2 entries have been added");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.add(13, 13);
        this.hashedDictionary.add(17, 17);
        this.hashedDictionary.add(8, 8);
        System.out.println("displaying dictionary after 3 additional entries have been added");
        this.hashedDictionary.displayHashTable();

        this.hashedDictionary.remove(1);
        this.hashedDictionary.remove(17);
        System.out.println("displaying dictionary after 2 entries have been removed");
        this.hashedDictionary.displayHashTable();
    }

    public Integer getFirstRepeatedElement(int[] a)
    {
        // TODO Project1 #1
        Iterator<Integer> iterator;



        System.out.println("The content of the hash table for array: " + Arrays.toString(a));




            for(int i = 0; i < a.length; i++)
            {
                Integer value = this.hashedDictionary.getValue(a[i]);
               //if it exist - negate the value
                if(value != null)
                {
                    if(value > 0)
                    {
                        this.hashedDictionary.add(a[i], -value);
                    }
                }
                else
                {
                    this.hashedDictionary.add(a[i], i+1);
                }




                 iterator = hashedDictionary.getValueIterator();
                int max = Integer.MIN_VALUE;

                while(iterator.hasNext())
                {
                    int storage = iterator.next();
                    if(storage < 0 && storage > max)
                    {
                            max = storage;
                            //return a[(min*-1)-1];
                    }
                }

                if(max > Integer.MIN_VALUE)
                {
                    return a[(max*-1)-1];
                }
            }

        return null;
    }

    public boolean lookForPair(int[] a, int[] b, int k)
    {
        // TODO Project1 #2
        //1) Find smallest array a or b


            int position = 0;
            int[] small; //small array
            int[] large; //large array
            boolean pair = true;
            if (a.length > b.length) {
                small = b;
                large = a;
            }
            else
            {
                small = a;
                large = b;
            }

            for(int i = 0; i < small.length; i++)
            {
                if(this.hashedDictionary.getValue(small[i]) == null)
                {
                    this.hashedDictionary.add(small[i], small[i]);
                }

            }
        System.out.println("toPutInHashTable = " + Arrays.toString(small));
        System.out.println("toCheck = " + Arrays.toString(large));

            Iterator iterator = this.hashedDictionary.getValueIterator();

            while(iterator.hasNext())
            {
                int value = (int) iterator.next();
                for(int j = 0; j < large.length; j++)
                {
                    int arrayValue = large[j];
                    if(value + arrayValue == k)
                    {
                        System.out.println("The pair {" + value + "," + arrayValue + "} adds up to " + k);
                        return pair;
                    }
                }
            }


        //2) Store smallest array into a hashDictionary
            //-Element is key, Position is value
        //3) Iterate through other array(for loop)
            //-check hashDictionary for key
            //-if exist, negate position
        //4) use value iterator and find highest neg value
        return false;
    }

    public static void main(String[] args)
    {
        ArrayList<int[]> toCheck = new ArrayList<>();
        toCheck.add(new int[]{9, 3, 5, 1, 2, 2, 5, 3});
        toCheck.add(new int[]{6, 6, 3, 2, 1, 2, 2, 3});
        toCheck.add(new int[]{2, 1, 6, 2, 3, 2, 3, 6});
        toCheck.add(new int[]{3, 2, 1, 2, 2, 3, 6, 6});
        toCheck.add(new int[]{9, 3, 4, 4, 4, 1, 2, 2, 5, 3});
        toCheck.add(new int[]{3, 3, 3, 3, 3, 3, 3});
        toCheck.add(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        toCheck.add(new int[]{8, 1, 2, 3, 4, 5, 6, 7});

        SolveWithHashing solver = new SolveWithHashing();

        System.out.println("\n\t*** Testing displayHashTable ***");
        solver.testDisplayHashTable();

        System.out.println("\n\t*** Find The First Element With Duplicate ***");
        Integer firstDuplicate;
        for (int[] array : toCheck)
        {
            solver.createHashedDictionary();
            firstDuplicate = solver.getFirstRepeatedElement(array);
            if (firstDuplicate != null)
                System.out.println("--> the first element that is repeated is: " + firstDuplicate);
            else
                System.out.println("--> duplicates not found");
            System.out.println();
        }

        System.out.println("\n\t*** Check If There Exists A Pair Of Elements That Add Up To k ***");
        boolean found;
        for (int k = 2; k < 10; k++)
        {
            System.out.println("k = " + k);
            for (int i = 1; i < toCheck.size(); i++)
            {
                solver.createHashedDictionary();
                found = solver.lookForPair(toCheck.get(i - 1), toCheck.get(i), k);
                System.out.println("--> pair that add up to " + k + (found ? "" : " NOT") + " found.");
            }
            System.out.println();
        }
        System.out.println("\nBye!");
    }  // end main
}
