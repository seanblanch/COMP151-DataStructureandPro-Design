

import java.util.Arrays;

/**
 * A class that implements the ADT set by using a linked bag.
 * The set is never full.
 *
 * @author Sean Blanchard
 * @version 2/5/2019
 */
public class LinkedSetWithLinkedBag<T extends Comparable<? super T>> implements SetInterface<T>
{
    private LinkedBag<T> setOfEntries;

    /**
     * Creates a set from a new, empty linked bag.
     */
    public LinkedSetWithLinkedBag() {
        //TODO Project1
        this.setOfEntries = new LinkedBag<>();
    } // end default constructor

    public boolean add(T newEntry) {
        //TODO Project1
        boolean success = true;

        if (newEntry != null && !setOfEntries.contains(newEntry))
        {
            this.setOfEntries.add(newEntry);
        }
        else
            {
            success = false;
        }
        return success;
        //TODO done

        //return false; //THIS IS A STUB
    } // end add

    public T[] toArray() {
        //TODO Project1

        return this.setOfEntries.toArray();
        //TODO done
        //return null; //THIS IS A STUB
    } // end toArray

    public boolean isEmpty() {
        //TODO Project1
        return this.setOfEntries.isEmpty();
        //TODO done
        //return false; //THIS IS A STUB
    } // end isEmpty

    public boolean contains(T anEntry) {
        //TODO Project1
        return this.setOfEntries.contains(anEntry);
        //TODO done

        //return false; //THIS IS A STUB
    } // end contains

    public void clear() {
        //TODO Project1
        this.setOfEntries.clear();
        //TODO done
    } // end clear

    public T remove() {
        //TODO Project1
        return this.setOfEntries.remove();
        //TODO done

        //return null; //THIS IS A STUB
    } // end remove

    public boolean removeElement(T anEntry) {
        //TODO Project1
        return this.setOfEntries.removeElement(anEntry);
        //TODO done

        //return false; //THIS IS A STUB
    } // end remove

    // Displays a set.
    public void displaySet() {
        //TODO Project1

        if (this.setOfEntries.isEmpty())
        {
            System.out.println("The bag is empty");
        }
        else
        {
            T[] excessBag = this.toArray();
            System.out.println("The set contains " + excessBag.length +  " String(s), as follows: ");
            for (T entry : excessBag)

            {
                System.out.print(entry + " ");
                this.setOfEntries.toArray();

            }




        }

        System.out.println();

        //TODO done

//        if (!this.setOfEntries.isEmpty()) {
//            System.out.println("The set contains " + this.setOfEntries.getCurrentSize() + " String(s): ");
//            this.setOfEntries.toArray();
//
//        T[] excessBag = this.toArray();
//        for (T entry : excessBag)
//            System.out.print(entry + " ");
//        System.out.println();
//    }
//         else
//    {
//        System.out.println("The bag is empty");
//    }

}




        // end displaySet

    public static void main(String[] args)
    {
        String[] inputData = {"A", "B", "C", "D", "A", "C", "B", "B"};
        System.out.println("--> Creating aSet and adding to it elements from inputData: " + Arrays.toString(inputData));
        SetInterface<String> aSet = new LinkedSetWithLinkedBag<>();
        for (int i=0; i < inputData.length; i++)
        {
            aSet.add(inputData[i]);
        }
        aSet.displaySet();

        System.out.println("\n--> Clearing aSet");
        aSet.clear();
        aSet.displaySet();
        System.out.println("--> aSet isEmpty returns: " + aSet.isEmpty());

        System.out.println("\n--> Creating set1 and set2");
        SetInterface<String> set1 = new LinkedSetWithLinkedBag<>();
        SetInterface<String> set2 = new LinkedSetWithLinkedBag<>();

        System.out.println("\n--> Adding elements to set1");
        set1.add("A");
        set1.add("A");
        set1.add("B");
        set1.add("A");
        set1.add("C");
        set1.add("A");
        System.out.println("--> set1 after adding elements");
        set1.displaySet();

        System.out.println("\n--> Adding elements to set2");
        set2.add("A");
        set2.add("B");
        set2.add("B");
        set2.add("A");
        set2.add("C");
        set2.add("C");
        set2.add("D");
        System.out.println("--> set2 after adding elements");
        set2.displaySet();

        System.out.println("\n--> set1 contains \"A\": " + set1.contains("A"));
        System.out.println("--> set1 contains \"E\": " + set1.contains("E"));

        System.out.println("\n--> Removing \"B\" from set1");
        set1.removeElement("B");
        System.out.println("--> After removing \"B\" from set1, ");
        set1.displaySet();

        System.out.println("\n--> Removing random element from set1");
        String removed = set1.remove();
        System.out.println("--> set1.remove() returned: \"" + removed + "\"");
        set1.displaySet();

        System.out.println("\n--> Removing \"A\" from set1");
        set1.removeElement("A");
        System.out.println("--> After removing \"A\" from set1, ");
        set1.displaySet();

        System.out.println("\n--> Removing random element from set1");
        removed = set1.remove();
        System.out.println("--> set1.remove() returned: \"" + removed + "\"");
        set1.displaySet();

        System.out.println("\n--> Adding 4 elements to set1");
        set1.add("K");
        set1.add("L");
        set1.add("M");
        set1.add("N");
        System.out.println("--> After adding 4 elements to set1:");
        set1.displaySet();

        System.out.println("\n--> Trying to add duplicate element \"N\" to set1");
        set1.add("N");
        System.out.println("--> After adding a duplicate element \"N\" to set1");
        set1.displaySet();

        System.out.println("\nTrying to add null entry");
        String nullEntry = null;
        set1.add(nullEntry);
        System.out.println("--> set1 after adding:");
        set1.displaySet();
    } // end main

} // end LinkedSetWithLinkedBag
