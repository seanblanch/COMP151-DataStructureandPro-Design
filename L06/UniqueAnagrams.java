

import java.util.*;
import java.util.Scanner;
import java.util.TreeSet;

/**
 * This class creates all permutations of the given string
 *
 * @author Sean Blanchard
 * @version 2/26/2019
 */
public class UniqueAnagrams
{
    private ArrayList<String> anagrams;

    public UniqueAnagrams()
    {
        this.anagrams = new ArrayList<>();
    }

    public void permutations(String word)
    {
        permutations("", word);
        System.out.println("Possible anagrams = " + this.anagrams);

        TreeSet<String> toTest = new TreeSet(this.anagrams);
        System.out.println("Expected unique and sorted anagrams = " + toTest);
        System.out.println();

        sort(); // duplicates will be removed during the sorting process
    }

    private void permutations(String prefix, String suffix)
    {
        int numOfChars = suffix.length();
        if (numOfChars == 1)
        {
            //System.out.println(prefix + suffix);
            this.anagrams.add(prefix + suffix);
        }
        else
        {

        //TODO Project2

            for(int i=0;i<suffix.length();i++)
            {

                String firstLetter = prefix + suffix.charAt(i); // Find the first index

                String lastLetter = suffix.substring(0, i) + suffix.substring(i+1); // find the last letter indexing

                permutations(firstLetter,lastLetter);

            }

        }
        //TODO end
    }

    private void sort()
    {
        //TODO Project2

        // calls getIndexOfSmallestAndRemoveDuplicates(index, this.anagrams.size());
        // calls swap(index, indexOfNextSmallest);


        //-------------------------------------------------------------------------

        int i = 0;

        while(i < this.anagrams.size()) {

            int indexOfNextSmallest = getIndexOfSmallestAndRemoveDuplicates(i, this.anagrams.size());

            swap(i, indexOfNextSmallest);

            i++;

        }

        //TODO end
    }

    private int getIndexOfSmallestAndRemoveDuplicates(int first, int last)
    {
       //TODO Project2
//
        // as the smallest is being found removes duplicates

        String smallest = this.anagrams.get(first);

        int smallestIndex=first;

        for(int i=first+1;i<last;i++)
        {

            if(this.anagrams.get(i).compareTo(smallest) < 0)
            {

                smallest = this.anagrams.get(i);

                smallestIndex = i;

            }

        }

        boolean duplicate = false;

        for(int i=first;i<this.anagrams.size();i++)
        {

            if(this.anagrams.get(i).equals(smallest))
            {

                if(duplicate)
                {

                    this.anagrams.remove(i);

                    i--;

                }

                else

                    duplicate = true;

            }

        }

        return smallestIndex; // THIS IS A STUB


        //-------------------------------------------------------

//        //boolean duplicate = false;
//
//        for(int i=smallestIndex+1; i<this.anagrams.size(); i++)
//        {
//
//            if(this.anagrams.get(i).equals(smallest))
//            {
//
//                this.anagrams.remove(i);
//
//                i++;
//
//            }
//
//        }
//
//        return smallestIndex; // THIS IS A STUB



//        //TODO done
    }

    private void swap(int i, int j)
    {
        //TODO Project2


        String temp1 = this.anagrams.get(i);

        String temp2 = this.anagrams.get(j);

        this.anagrams.set(i, temp2);

        this.anagrams.set(j, temp1);

        //TODO Done
    }

    private void display()
    {
        System.out.println("Computed unique and sorted anagrams = " + this.anagrams);
    }

    public static void main(String[] args)
    {
        UniqueAnagrams uniqueAnagrams = new UniqueAnagrams();
        Scanner keyboard = new Scanner(System.in);

        System.out.println("Please enter a word");
        String word = keyboard.next();

        uniqueAnagrams.permutations(word);
        uniqueAnagrams.display();
    }
}
