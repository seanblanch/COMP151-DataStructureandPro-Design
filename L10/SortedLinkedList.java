package Lab10;

/**
 * A class that implements the ADT sorted list by using a chain of linked nodes.
 * Duplicate entries are allowed.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @version 4.0
 */
public class SortedLinkedList<T extends Comparable<? super T>>
        implements SortedListInterface<T>
{
    private Node<T> firstNode;       // Reference to first node of chain
    private int  numberOfEntries;

    public SortedLinkedList()
    {
        this.firstNode = null;
        this.numberOfEntries = 0;
    } // end default constructor

    public void add(T newEntry)
    {
        Node<T> newNode = new Node(newEntry);
        Node<T> nodeBefore = getNodeBefore(newEntry);

        if (isEmpty() || (nodeBefore == null))
        {  // Add at beginning
            newNode.next = this.firstNode;
            this.firstNode = newNode;
        }
        else
        {  // Add after nodeBefore
            Node<T> nodeAfter = nodeBefore.next;
            newNode.next = nodeAfter;
            nodeBefore.next = newNode;
        } // end if

        this.numberOfEntries++;
    } // end add

    public boolean remove(T anEntry)
    {
        boolean found = false;

        if (this.numberOfEntries > 0)
        {
            Node<T> nodeToRemove;
            Node<T> nodeBefore = getNodeBefore(anEntry);

            if (nodeBefore == null)
                nodeToRemove = this.firstNode;
            else
                nodeToRemove = nodeBefore.next;

            if ((nodeToRemove != null) && anEntry.equals(nodeToRemove.data) )
            {
                found = true;

                if (nodeBefore == null)
                    this.firstNode = nodeToRemove.next;
                else
                {
                    Node<T> nodeAfter = nodeToRemove.next;
                    nodeBefore.next = nodeAfter;
                } // end if

                this.numberOfEntries--;
            } // end if
        } // end if

        return found;
    } // end remove

    public int getPosition(T anEntry)
    {
        int position = 1;
        Node<T> currentNode = this.firstNode;

        while ( (currentNode != null) && ( anEntry.compareTo(currentNode.data) > 0) )
        {
            currentNode = currentNode.next;
            position++;
        } // end while

        if ( (currentNode == null) || anEntry.compareTo(currentNode.data) != 0)
            position = -position;

        return position;
    } // end getPosition

    // List operations
    public T getEntry(int givenPosition)
    {
        T result = null;                          // Result to return

        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();
            result = getNodeAt(givenPosition).data;
        } // end if

        return result;
    } // end getEntry

    public T remove(int givenPosition)
    {
        T result = null;                           // Return value

        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();

            if (givenPosition == 1)                 // Case 1: remove first entry
            {
                result = this.firstNode.data;        // Save entry to be removed
                this.firstNode = this.firstNode.next;
            }
            else                                    // Case 2: givenPosition > 1
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.next;
                Node<T> nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;   // Disconnect the node to be removed
                result = nodeToRemove.data;     // Save entry to be removed
            } // end if

            this.numberOfEntries--;
        } // end if

        return result;                             // Return removed entry, or
        // null if operation fails
    } // end remove

    public final void clear()
    {
        this.firstNode = null;
        this.numberOfEntries = 0;
    } // end clear

    public boolean contains(T anEntry)
    {
        return getPosition(anEntry) > 0;
    } // end contains

    public int getLength()
    {
        return this.numberOfEntries;
    } // end getLength

    public boolean isEmpty()
    {
        boolean result;

        if (this.numberOfEntries == 0) // Or getLength() == 0
        {
            assert this.firstNode == null;
            result = true;
        }
        else
        {
            assert this.firstNode != null;
            result = false;
        } // end if

        return result;
    } // end isEmpty

    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Comparable[this.numberOfEntries]; // Warning: unchecked cast

        int index = 0;
        Node<T> currentNode = this.firstNode;
        while ((index < this.numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.data;
            currentNode = currentNode.next;
            index++;
        } // end while

        return result;
    } // end toArray

    // Finds the node that is before the node that should or does
    // contain a given entry.
    // Returns either a reference to the node that is before the node
    // that does or should contain anEntry, or null if no prior node exists
    // (that is, if anEntry belongs at the beginning of the list).
    private Node<T> getNodeBefore(T anEntry)
    {
        Node<T> currentNode = this.firstNode;
        Node<T> nodeBefore = null;

        while ( (currentNode != null) &&
                (anEntry.compareTo(currentNode.data) > 0) )
        {
            nodeBefore = currentNode;
            currentNode = currentNode.next;
        } // end while

        return nodeBefore;
    } // end getNodeBefore

    private Node<T> getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= this.numberOfEntries);
        Node<T> currentNode = this.firstNode;

        // Traverse the list to locate the desired node
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.next;

        assert currentNode != null;
        return currentNode;
    } // end getNodeAt

    private class Node <S>
    {
        private S     data;  // Entry in list
        private Node<S>	next; // Link to next node

        private Node(S dataPortion)
        {
            this.data = dataPortion;
            this.next = null;
        } // end constructor

        private Node(S dataPortion, Node nextNode)
        {
            this.data = dataPortion;
            this.next = nextNode;
        } // end constructor
    } // end Node
} // end LinkedSortedList

