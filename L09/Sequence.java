package Lab09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Determine if one sequence of words is a sub-sequence of a second.
 *
 * @author Sean Blanchard
 * @version 3/26/2019
 */


public class Sequence
{
    private ArrayList<String>  sequence;

    public Sequence(String... input)
    {
        this.sequence = new ArrayList<>();
        Collections.addAll(this.sequence,input);
    }

    public Sequence (ArrayList<String> input)
    {
        this.sequence = new ArrayList<>(input);
    }

    public void add (String element)
    {
        this.sequence.add(element);
    }

    public void clear()
    {
        this.sequence.clear();
    }

    public String toString()
    {
        return this.sequence.toString();
    }


    /**
     * isSubSequenceOf - determine if one sequence is a subsequence of the other
     *
     * @param other the second sequence to test
     * @return    a true if the first list is a subsequence of the other
     */
    private boolean isSubSequenceOf(Sequence other)
    {
        // TODO Project 4
        boolean result = false;
        boolean foundMatch = false;
        Iterator<String> iterThis  = this.sequence.iterator();
        Iterator<String> iterOther  = other.sequence.iterator();
        String itemOfThisList = null;
        String itemOfOtherList = null;

        if(other.sequence.size() < this.sequence.size())
        {
            result = false;
        }
        while(iterThis.hasNext())
        {
            itemOfThisList = iterThis.next();

            foundMatch = false;
            while(iterOther.hasNext() && !foundMatch)
            {
                itemOfOtherList = iterOther.next();

                if(itemOfThisList.equals(itemOfOtherList))
                {
                    foundMatch = true;
                }
            }
            result = foundMatch;

        }


        return result;
    }

    public static void testSubSequence()
    {
        ArrayList<String> a1, a2;
        System.out.println("\n*** RUNNING AUTOMATED TESTCASES ***");

        a1 = new ArrayList<>();
        a2 = new ArrayList<>();

        a1.add("a");
        a1.add("b");
        a1.add("c");

        Sequence s1 = new Sequence(a1);
        Sequence s2 = new Sequence(a2);

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(!s1.isSubSequenceOf(s2))
        {
            System.out.println("    Not a subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should not be a subsequence of s2");
        }

        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(s2.isSubSequenceOf(s1))
        {
            System.out.println("    A subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s2 should be a subsequence of s1");
        }

        s2.add("a");
        s2.add("c");
        s2.add("b");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(!s1.isSubSequenceOf(s2))
        {
            System.out.println("    Not a subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should not be a subsequence of s2");
        }

        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(!s2.isSubSequenceOf(s1))
        {
            System.out.println("    Not a subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s2 should not be a subsequence of s1");
        }

        s2.add("c");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(s1.isSubSequenceOf(s2))
        {
            System.out.println("    A subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should be a subsequence of s2");
        }
        ;
        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(!s2.isSubSequenceOf(s1))
        {
            System.out.println("    Not a subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s2 should not be a subsequence of s1");
        }


        s1.add("a");
        s1.add("b");
        s1.add("c");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(!s1.isSubSequenceOf(s2))
        {
            System.out.println("    Not a subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should not be a subsequence of s2");
        }

        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(s2.isSubSequenceOf(s1))
        {
            System.out.println("    A subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s2 should be a subsequence of s1");
        }

        s1.clear();
        s1.add("a");
        s1.add("b");
        s1.add("a");
        s1.add("c");

        s2.clear();
        s2.add("a");
        s2.add("b");
        s2.add("a");
        s2.add("c");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(s1.isSubSequenceOf(s2))
        {
            System.out.println("    A subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should be a subsequence of s2");
        }

        s2.add("x");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(s1.isSubSequenceOf(s2))
        {
            System.out.println("    A subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should be a subsequence of s2");
        }

        s1.clear();
        s1.add("a");
        s1.add("b");
        s1.add("c");

        s2.clear();
        s2.add("a");
        s2.add("b");
        s2.add("d");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(s1.isSubSequenceOf(s2))
        {
            System.out.println("**** Fails - s1 should not be a subsequence of s2");
        }
        else
        {
            System.out.println("    Not a subsequence - Passes");
        }

        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(s2.isSubSequenceOf(s1))
        {
            System.out.println("**** Fails - s2 should not be a subsequence of s1");
        }
        else
        {
            System.out.println("    Not a subsequence - Passes");
        }

        s2.clear();
        s2.add("x");
        s2.add("y");
        s2.add("a");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(s1.isSubSequenceOf(s2))
        {
            System.out.println("**** Fails - s1 should not be a subsequence of s2");
        }
        else
        {
            System.out.println("    Not a subsequence - Passes");
        }

        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(s2.isSubSequenceOf(s1))
        {
            System.out.println("**** Fails - s2 should not be a subsequence of s1");
        }
        else
        {
            System.out.println("    Not a subsequence - Passes");
        }

        s2.clear();
        s2.add("a");
        s2.add("b");
        s2.add("a");
        s2.add("a");
        s2.add("b");
        s2.add("c");

        System.out.println("Testing if " + s1 + " is a subsequence of " + s2 + ": ");
        if(s1.isSubSequenceOf(s2))
        {
            System.out.println("    A subsequence - Passes");
        }
        else
        {
            System.out.println("**** Fails - s1 should be a subsequence of s2");
        }

        System.out.println("Testing if " + s2 + " is a subsequence of " + s1 + ": ");
        if(s2.isSubSequenceOf(s1))
        {
            System.out.println("**** Fails - s2 should not be a subsequence of s1");
        }
        else
        {
            System.out.println("    Not a subsequence - Passes");
        }
    }

    public static void main(String args[])
    {
        Scanner keyboard = new Scanner(System.in);
        Sequence seq1 = new Sequence("a","b","c");
        System.out.println("The seq1 is " + seq1);

        System.out.println("==> Please enter a sequence of words on a single line,"
                + " (words should be separated by spaces), or stop.");
        String line = keyboard.nextLine();
        while (!line.equalsIgnoreCase("stop"))
        {
            Sequence seq2 = new Sequence(line.split("\\s"));
            System.out.println("Is " + seq1 + " a subsequence of " + seq2 + ": " + seq1.isSubSequenceOf(seq2));
            System.out.println("==> Please enter a sequence of words on a single line,"
                    + " (words should be separated by spaces), or stop.");
            line = keyboard.nextLine();
        }

        testSubSequence();
    }
}