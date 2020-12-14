package Lab10;

import java.util.Arrays;

/**
 * A class that implements the ADT sorted list by using a resizable array.
 * Duplicate entries are allowed.
 *
 * @author Sean Blanchard
 * @version 4/2/2019
 */
public class SortedArrayListWithMode<T extends Comparable<? super T>>
        implements SortedListInterface<T>
{
    private T[] list;			 			// Array of list entries; ignore list[0]
    private int numberOfEntries;
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 25;
    private static final int MAX_CAPACITY = 10000;

    public SortedArrayListWithMode()
    {
        this(DEFAULT_CAPACITY);
    } // end default constructor

    public SortedArrayListWithMode(int initialCapacity)
    {
        // Is initialCapacity too small?
        if (initialCapacity < DEFAULT_CAPACITY)
            initialCapacity = DEFAULT_CAPACITY;
        else // Is initialCapacity too big?
            checkCapacity(initialCapacity);

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempList = (T[])new Comparable[initialCapacity + 1];
        this.list = tempList;
        this.numberOfEntries = 0;
        this.initialized = true;
    } // end default constructor

    // Part a: manipulating directly the instance variables of SortedArrayListWithMode,
    //        find the mode. The mode is the most frequent value.
    // vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
    public T getMode()
    {
        // TODO Project 2a
        T mode = null;
        int modeCount = 0;


        //System.out.println("IMPLEMENT Part a: manipulating directly the instance variables of SortedArrayListWithMode,"
        //        + "find the mode. The mode is the most frequent value.");
        //System.out.println("NOTE - the list is 1 based!");

        //int possibleMode = 0;
        //int possibleModeCount = 0;

        int count = 0;

        for(int i = 1; i <= this.numberOfEntries; i++) {

            count = 1;

            for(int j = i+1; j <= this.numberOfEntries; j++) {

                if(this.list[i] ==(this.list[j] ))

                    count++;

            }

            if(count > modeCount) {

                modeCount = count;

                mode = this.list[i] ;

            }

        }

        System.out.println("---> mode is " + mode + "; mode count is " + modeCount );
        return mode;

        //TODO done - come back
    } // end getMode

    // ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^

    public void add(T newEntry)
    {
        checkInitialization();

        // getPosition calls getEntry, which  calls checkInitialization,
        // so we do not have to
        int newEntryPosition = Math.abs(getPosition(newEntry));

        // Beginning at the end of the list, shift entries to next higher
        // position until proper sorted position for newEntry is reached
        int currentIndex = this.numberOfEntries;

        // Loop is skipped if sorted list is empty or
        // insertion is at end of sorted list
        while (currentIndex >= newEntryPosition)
        {
            this.list[currentIndex + 1] = this.list[currentIndex];
            currentIndex--;
        } // end while

        this.list[currentIndex + 1] = newEntry;
        this.numberOfEntries++;
        ensureCapacity(); // Ensure enough room for next add
    } // end add

    public boolean remove(T anEntry)
    {
        checkInitialization();
        boolean found = false;

        if (!isEmpty())
        {
            int position = getPosition(anEntry);
            assert position != 0;
            if (position > 0)
            {
                removeGap(position);
                this.numberOfEntries--;
                found = true;
            } // end if
        } // end if

        return found;
    } // end remove

    public int getPosition(T anEntry)
    {
        checkInitialization();

        int position = 1;
        boolean found = false;

        while (position <= this.numberOfEntries && !found)
        {
            int result = anEntry.compareTo(this.list[position]);
            if (result > 0)
                position++;
            else if (result == 0)
                found = true;
        } // end while

        if (!found)
        {
            position = -position;
        } // end if

        return position;
    } // end getPosition

    // List operations
    public T getEntry(int givenPosition)
    {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();
            return this.list[givenPosition];
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    } // end getEntry

    public boolean contains(T anEntry)
    {
        return getPosition(anEntry) > 0;
    } // end contains

    public T remove(int givenPosition)
    {
        checkInitialization();
        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();
            T result = this.list[givenPosition]; // Get entry to be removed

            // Move subsequent entries towards entry to be removed,
            // unless it is last in list
            if (givenPosition < this.numberOfEntries)
                removeGap(givenPosition);

            this.numberOfEntries--;
            return result;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    } // end remove

    public void clear()
    {
        checkInitialization();

        // Clear entries but retain array; no need to create a new array
        for (int index = 1; index <= this.numberOfEntries; index++) // Loop is part of Q4
            this.list[index] = null;

        this.numberOfEntries = 0;
    } // end clear

    public int getLength()
    {
        return this.numberOfEntries;
    } // end getLength

    public boolean isEmpty()
    {
        return this.numberOfEntries == 0;
    } // end isEmpty

    public T[] toArray()
    {
        checkInitialization();

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[this.numberOfEntries]; // Unchecked cast
        for (int index = 0; index < this.numberOfEntries; index++)
        {
            result[index] = this.list[index + 1];
        } // end for

        return result;
    } // end toArray

    // Doubles the capacity of the array list if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        int capacity = this.list.length - 1;
        if (this.numberOfEntries >= capacity)
        {
            int newCapacity = 2 * capacity;
            checkCapacity(newCapacity);
            this.list = Arrays.copyOf(this.list, newCapacity + 1);
        } // end if
    } // end ensureCapacity

    // Shifts entries that are beyond the entry to be removed to the
    // next lower position.
    // Precondition: checkInitialization has been called.
    // Precondition: 1 <= givenPosition < numberOfEntries;
    //               numberOfEntries is list's length before removal. */
    private void removeGap(int givenPosition)
    {
        assert (givenPosition >= 1) && (givenPosition < this.numberOfEntries);

        int removedIndex = givenPosition;
        int lastIndex = this.numberOfEntries;

        for (int index = removedIndex; index < lastIndex; index++)
            this.list[index] = this.list[index + 1];
    } // end removeGap

    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException ("ArraySortedList object is not initialized properly.");
    } // end checkInitialization

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a sorted list " +
                    "whose capacity is larger than " +
                    MAX_CAPACITY);
    } // end checkCapacity

    public void display()
    {
        System.out.print("\nThe data has " + this.numberOfEntries + " element(s): ");
        for (int i = 1; i <= this.numberOfEntries; i++)
        {
            System.out.print(this.list[i] + " ");
        }
        System.out.println();
    }

    public static void main(String args[])
    {
        SortedArrayListWithMode<Integer> data = new SortedArrayListWithMode<>();
        System.out.println("The mode of the empty list should be null, got: " + data.getMode());

        // test list of 1 element
        data.add(9);
        data.display();
        System.out.println("The mode should be 9, got: " + data.getMode());

        // test list of 2 elements
        data.add(13);
        data.display();
        System.out.println("The mode should be 9, got: " + data.getMode());

        // test list of 3 elements
        data.add(13);
        data.display();
        System.out.println("The mode should be 13, got: " + data.getMode());

        // test list of 3 elements
        data = new SortedArrayListWithMode<>();
        data.add(9);
        data.add(9);
        data.add(13);
        data.display();
        System.out.println("The mode should be 9, got: " + data.getMode());

        data = new SortedArrayListWithMode<>();
        for (int i = 0; i < 10; i++)
            data.add(i);
        data.display();
        System.out.println("The mode should be 0, got: " + data.getMode());

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < i; j++)
                data.add(i);
        data.display();
        System.out.println("The mode should be 9, got: " + data.getMode());

        for (int i = 0; i < 21; i++)
            for (int j = 8; j < i; j++)
                data.add(i);
        data.display();
        System.out.println("The mode should be 20, got: " + data.getMode());

        for (int i = 0; i < 14; i++)
            data.add(6);
        data.display();
        System.out.println("The mode should be 6, got: " + data.getMode());



        System.out.println("\n*** Done ***");
    } // end main
} // end ArraySortedList
