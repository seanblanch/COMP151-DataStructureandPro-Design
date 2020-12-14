package Lab09;

/**
 * A class that implements the ADT list by using a chain of nodes.
 * The list has an iterator. The class is similar to LList.
 *
 * @author Frank M. Carrano
 * @author Timothy M. Henry
 * @modifiedBy Sean Blanchard
 * @version 3/26/2019
 */
public class ReverseLinkedList<T> implements ListInterface<T>
{
    private Node<T> firstNode;
    private int numberOfEntries;

    public ReverseLinkedList()
    {
        clear();
    } // end default constructor

    public void clear()
    {
        this.firstNode = null;
        this.numberOfEntries = 0;
    } // end clear

    public void add(T newEntry)                   // OutOfMemoryError possible
    {
        Node<T> newNode = new Node<>(newEntry);

        if (isEmpty())
            this.firstNode = newNode;
        else                                      // Add to end of non-empty list
        {
            Node<T> lastNode = getNodeAt(this.numberOfEntries);
            lastNode.next = newNode;         // Make last node reference new node
        }

        this.numberOfEntries++;
    }  // end add

    public void add(int newPosition, T newEntry) // OutOfMemoryError possible
    {
        if ((newPosition >= 1) && (newPosition <= this.numberOfEntries + 1))
        {
            Node<T> newNode = new Node<>(newEntry);

            if (newPosition == 1)                  // Case 1
            {
                newNode.next = this.firstNode;
                this.firstNode = newNode;
            }
            else                                 // Case 2: list is not empty
            {                                      // and newPosition > 1
                Node<T> nodeBefore = getNodeAt(newPosition - 1);
                Node<T> nodeAfter = nodeBefore.next;
                newNode.next = nodeAfter;
                nodeBefore.next = newNode;
            }

            this.numberOfEntries++;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to add operation.");
    } // end add

    public T remove(int givenPosition)
    {
        T result = null;                          // Return value

        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();

            if (givenPosition == 1)                // Case 1: remove first entry
            {
                result = this.firstNode.data;       // Save entry to be removed
                this.firstNode = this.firstNode.next;
            }
            else                                   // Case 2: not first entry
            {
                Node<T> nodeBefore = getNodeAt(givenPosition - 1);
                Node<T> nodeToRemove = nodeBefore.next;
                Node nodeAfter = nodeToRemove.next;
                nodeBefore.next = nodeAfter;
                result = nodeToRemove.data;    // Save entry to be removed
            }

            this.numberOfEntries--;
            return result;                         // Return removed entry
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to remove operation.");
    } // end remove

    public T replace(int givenPosition, T newEntry)
    {
        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();

            Node<T> desiredNode = getNodeAt(givenPosition);
            T originalEntry = desiredNode.data;
            desiredNode.data = newEntry;
            return originalEntry;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to replace operation.");
    } // end replace

    public T getEntry(int givenPosition)
    {
        if ((givenPosition >= 1) && (givenPosition <= this.numberOfEntries))
        {
            assert !isEmpty();
            return (getNodeAt(givenPosition)).data;
        }
        else
            throw new IndexOutOfBoundsException("Illegal position given to getEntry operation.");
    } // end getEntry

    public T[] toArray()
    {
        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[this.numberOfEntries];

        int index = 0;
        Node<T> currentNode = this.firstNode;
        while ((index < this.numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.data;
            currentNode = currentNode.next;
            index++;
        }

        return result;
    } // end toArray

    public boolean contains(T anEntry)
    {
        boolean found = false;
        Node<T> currentNode = this.firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.data))
                found = true;
            else
                currentNode = currentNode.next;
        }

        return found;
    } // end contains

    public int getLength()
    {
        return this.numberOfEntries;
    } // end getLength

    public boolean isEmpty()
    {
        boolean result;

        if (this.numberOfEntries == 0)
        {
            assert this.firstNode == null;
            result = true;
        }
        else
        {
            assert this.firstNode != null;
            result = false;
        }

        return result;
    } // end isEmpty

    // Returns a reference to the node at a given position.
    // Precondition: List is not empty;
    //               1 <= givenPosition <= numberOfEntries.
    private Node<T> getNodeAt(int givenPosition)
    {
        assert !isEmpty() && (1 <= givenPosition) && (givenPosition <= this.numberOfEntries);
        Node<T> currentNode = this.firstNode;

        // Traverse the chain to locate the desired node (skipped
        // if givenPosition is 1)
        for (int counter = 1; counter < givenPosition; counter++)
            currentNode = currentNode.next;

        assert currentNode != null;

        return currentNode;
    } // end getNodeAt

//==================================

    public void displayList()
    {
        System.out.print("The list contains: " );
        Node<T> currentNode = this.firstNode;
        while (currentNode != null)
        {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    } // end displayList

    public void reverseLinkedListIteratively()
    {
        // TODO Project 7
        Node current = firstNode;
        Node previous = null;
        Node forward = null;

        while(current.next != null)
        {
            forward = current.next;
            current.next = previous;
            previous = current;
            current = forward;
        }
        firstNode = current;
        firstNode.next = previous;

        // please put here the link to the video you followed in your solution
        // https://www.youtube.com/watch?v=sYcOK51hl-A
    }

    public void reverseLinkedListRecursively()
    {
        recursiveReverse(this.firstNode);
    }

    private void recursiveReverse(Node<T> currentNode)
    {
        // TODO Project 7


        if(currentNode.next == null)
        {
            firstNode = currentNode;
            return;
        }
        recursiveReverse(currentNode.next);
        //Node<T> temp = currentNode;
        currentNode.next.next = currentNode;
        currentNode.next = null;
//        // please put here the link to the video you followed in your solution
        // https://www.youtube.com/watch?v=Ip4y7Inx7QY
    }

    private class Node<S>
    {
        private S data; // Entry in list
        private Node<S> next; // Link to next node

        private Node(S dataPortion)
        {
            this(dataPortion, null);
        } // end constructor

        private Node(S dataPortion, Node nextNode)
        {
            this.data = dataPortion;
            this.next = nextNode;
        } // end constructor
    }

    public static void main(String[] args)
    {
        System.out.println("*** Create a list ***");
        ReverseLinkedList<String> myList = new ReverseLinkedList<>();
        myList.add("15");
        myList.add("25");
        myList.add("35");
        myList.add("45");
        myList.add("55");
        myList.add("65");
        myList.add("75");
        myList.add("85");
        myList.add("95");

        myList.displayList();

        System.out.println("\n*** Calling reverseLinkedListIteratively ***");
        myList.reverseLinkedListIteratively();

        System.out.println("\nExpected result: 95 85 75 65 55 45 35 25 15");
        myList.displayList();


        System.out.println("===========================================================");

        System.out.println("\n\n*** Calling reverseLinkedListRecursively ***");
        myList.reverseLinkedListRecursively();
        System.out.println("\nExpected result: 15 25 35 45 55 65 75 85 95");
        myList.displayList();
        System.out.println("*** Done ***");
    }  // end main
} // end LinkedListWithIterator