/** * A class of bags whose entries are stored in a chain of linked nodes. * The bag is never full. * * @author Sean Blanchard * @version 2/11/2019 */public class LinkedBag<T extends Comparable<? super T>> implements BagInterface<T>{    private Node<T> firstNode;       // reference to first node    public LinkedBag()    {        this.firstNode = null;    } // end default constructor    /**     * Adds a new entry to this bag.     *     * @param newEntry the object to be added as a new entry     * @return true     */    public boolean add(T newEntry) // OutOfMemoryError possible    {        // add to beginning of chain:        Node<T> newNode = new Node<>(newEntry);        newNode.next = this.firstNode;  // make new node reference rest of chain        // (firstNode is null if chain is empty)        this.firstNode = newNode;       // new node is at beginning of chain        return true;    } // end add    /**     * Retrieves all entries that are in this bag.     *     * @return a newly allocated array of all the entries in the bag     */    public T[] toArray()    {        int counter = 0;        Node<T> currentNode = this.firstNode;        while (currentNode != null)        {            counter++;            currentNode = currentNode.next;        } // end while        // the cast is safe because the new array contains null entries        @SuppressWarnings("unchecked")        T[] result = (T[]) new Comparable<?>[counter]; // unchecked cast        int index = 0;        currentNode = this.firstNode;        while (currentNode != null && index < result.length)        {            result[index] = currentNode.data;            index++;            currentNode = currentNode.next;        } // end while        return result;    } // end toArray    /**     * Sees whether this bag is empty.     *     * @return true if the bag is empty, or false if not     */    public boolean isEmpty()    {        return this.firstNode == null;    } // end isEmpty    /**     * Gets the number of entries currently in this bag.     *     * @return the integer number of entries currently in the bag     */    public int getCurrentSize()    {        throw new UnsupportedOperationException();    } // end getCurrentSize    /**     * Counts the number of times a given entry appears in this bag.     *     * @param anEntry the entry to be counted     * @return the number of times anEntry appears in the bag     */    public int getFrequencyOf(T anEntry)    {        int frequency = 0;        Node<T> currentNode = this.firstNode;        while (currentNode != null)        {            if (anEntry.equals(currentNode.data))            {                frequency++;            } // end if            currentNode = currentNode.next;        } // end while        return frequency;    } // end getFrequencyOf    /**     * Tests whether this bag contains a given entry.     *     * @param anEntry the entry to locate     * @return true if the bag contains anEntry, or false otherwise     */    public boolean contains(T anEntry)    {        return getReferenceTo(anEntry) != null;    } // end contains    // Locates a given entry within this bag.    // Returns a reference to the node containing the entry, if located,    // or null otherwise.    private Node<T> getReferenceTo(T anEntry)    {        boolean found = false;        Node<T> currentNode = this.firstNode;        while (!found && (currentNode != null))        {            if (anEntry.equals(currentNode.data))                found = true;            else                currentNode = currentNode.next;        } // end while        return currentNode;    } // end getReferenceTo    /**     * Removes all entries from this bag.     */    public void clear()    {        while (!isEmpty())            remove();    } // end clear    /**     * Removes one unspecified entry from this bag, if possible.     *     * @return either the removed entry, if the removal     * was successful, or null     */    public T remove()    {        T result = null;        if (this.firstNode != null)        {            result = this.firstNode.data;            this.firstNode = this.firstNode.next; // remove first node from chain        } // end if        return result;    } // end remove    /**     * Removes one occurrence of a given entry from this bag, if possible.     *     * @param anElement the entry to be removed     * @return true if the removal was successful, or false otherwise     */    public boolean removeElement(T anElement)    {        boolean result = false;        Node<T> nodeN = getReferenceTo(anElement);        if (nodeN != null)        {            nodeN.data = this.firstNode.data; // replace located entry with entry in first node            this.firstNode = this.firstNode.next; // remove first node from chain            result = true;        } // end if        return result;    } // end remove    /**     * Displays all the elements in the bag     */    public void display()    {        int counter = 0;        if (this.firstNode != null)        {            Node<T> currentNode = this.firstNode;            while (currentNode != null)            {                System.out.print(currentNode.data + " ");                currentNode = currentNode.next;                counter++;            }            System.out.println();            System.out.print("There are " + counter + " element(s) in the bag.");            System.out.println();        }        else            System.out.println("The bag is empty.");    } // end display    // ****** IMPLEMENT THE FOLLOWING METHODS ********    /**     * Checks if the given bag called other is the same as the bag     *     * @param o the other bag to be compared with     * @return true both bags are the same     */    public boolean equals(Object o)    {        //TODO Project3 - implement this method first, show the instructor your finished code        boolean same;        if (this == o)            same = true;        else if (o == null || getClass() != o.getClass())            same = false;        else        {            LinkedBag<T> other = (LinkedBag<T>) o;            same = false;            Node<T> thisCurrentNode = this.firstNode;            Node<T> otherCurrentNode = other.firstNode;            while (thisCurrentNode != null && otherCurrentNode != null)            {                if (!thisCurrentNode.data.equals(otherCurrentNode))                {                    thisCurrentNode = thisCurrentNode.next;                    otherCurrentNode = otherCurrentNode.next;                }                else {                    same = true;                }            }            if (thisCurrentNode == null || otherCurrentNode == null)            {                same = false;            }            if(thisCurrentNode == null && otherCurrentNode == null)            {                same = true;            }        }        //                see lab2 implementation for guidance        return same; // THIS IS A STUB        //TODO end    }    /**     * Gets the largest value in this bag.     *     * @returns a reference to the largest object, or null if the bag is empty     */    public T getMax()    {        //TODO Project3 - implement this method second, show the instructor your finished code       Node<T>currentNode = this.firstNode;       T maximum = (currentNode != null) ? currentNode.data:null; //Create max variable to store the highest variable       while (currentNode != null)       {           if(maximum.compareTo(currentNode.data) < 0)           {               maximum = currentNode.data;           }           currentNode = currentNode.next;       }       return maximum;        //TODO end    } // end getMax    /**     * Removes the smallest element from the bag     *     * @return - null if the element was not found or the element     * <p/>     * NOTE: the method must traverse the data with a while loop; calls remove()     */    public T removeMin()    {        //TODO Project3//        Node<T> currentNode = this.firstNode;//        T minimum = (currentNode != null) ? currentNode.data:null;////        while (currentNode != null)//        {//            if(minimum.compareTo(currentNode.data) > 0)//            {//                minimum = currentNode.data;//            }//            currentNode = currentNode.next;//        }//        if(minimum != null)//        {//            this.removeEvery(minimum);//        }//////        return minimum; //THIS IS A STUB        T min; //Create a min variable        if (firstNode == null)        {            min = null;        } else        {            min = this.firstNode.data;            Node<T> currentNode = this.firstNode;            while (currentNode != null) {                if (min.compareTo(currentNode.data) > 0)                    min = currentNode.data;                currentNode = currentNode.next;            }                removeElement(min);            }        return min;    } // end removeMin    //TODO end    /**     * Removes every occurrence of a given entry from this bag.     * For efficiency it traverses the data and removes entries as it traverses the list     * without calling any other method     *     * @param anEntry the entry to be removed     */    public void removeEvery(T anEntry)    {        //TODO Project3        Node<T> currentNode = this.firstNode;        while (currentNode != null )        {            if (anEntry.compareTo(currentNode.data) == 0)            {                currentNode.data = this.firstNode.data;                this.firstNode = this.firstNode.next;            }            currentNode = currentNode.next;        }        //TODO end        // use only one while loop, change appropriate pointers, no calls to other methods    } // end removeEvery    /**     * Creates a new bag that combines the contents of this bag and a     * second given bag without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the union of the two bags     */    public BagInterface<T> union(BagInterface<T> otherBag)    {        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> unionBag = new LinkedBag<>();        //TODO Project3 - implement this method third, show the instructor your finished code        Node<T>currentNode;        currentNode = this.firstNode;        while(currentNode != null)        {            unionBag.add(currentNode.data);            currentNode = currentNode.next;        }        currentNode = other.firstNode;        while(currentNode != null) {            unionBag.add(currentNode.data);            currentNode = currentNode.next;        }        return unionBag;        //TODO end    } // end union    /**     * Creates a new bag that contains those objects that occur in both this     * bag and a second given bag without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the intersection of the two bags     */    //Close to opposite of difference bag    public BagInterface<T> intersection(BagInterface<T> otherBag)    {        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> intersectionBag = new LinkedBag<>();        LinkedBag<T> copyOfOtherBag = new LinkedBag<>();        //TODO Project3        Node<T>currentNode;        currentNode = other.firstNode;        while(currentNode != null)        {            copyOfOtherBag.add(currentNode.data);            currentNode = currentNode.next;        }        currentNode = this.firstNode;        while (currentNode != null)        {            T intersect = currentNode.data;            if(copyOfOtherBag.removeElement(intersect))            {               intersectionBag.add(intersect);            }            currentNode = currentNode.next;        }        // do not call contains, call removeElement(anElement) instead        return intersectionBag;    } // end intersection    //TODO end    /**     * Creates a new bag of objects that would be left in this bag     * after removing those that also occur in a second given bag     * without affecting the original two bags.     *     * @param otherBag the given bag     * @return a bag that is the difference of the two bags     */    //Close to opposite of intersection    public BagInterface<T> difference(BagInterface<T> otherBag)    {        LinkedBag<T> other = (LinkedBag<T>) otherBag;        LinkedBag<T> differenceBag = new LinkedBag<>();        //TODO Project3        Node<T> currentNode;        currentNode = this.firstNode;        while(currentNode != null)        {            differenceBag.add(currentNode.data);            currentNode = currentNode.next;        }        currentNode = other.firstNode;        while (currentNode != null)        {            differenceBag.removeElement(currentNode.data);            currentNode = currentNode.next;        }        // do not call contains, call removeElement(anElement) instead        return differenceBag;    } // end difference    //TODO end    /**     * Moves the first node in the chain to be the last in the same chain of nodes.     *     */    //loop through the list to get to the end    //link first node to last element    //.next -> make it point to another element using while loop    //Link the new last element to null    //Reset this.firstNode -> keep track of firstNode    public void moveFirstToEnd()    {        //TODO Project3        // this method should run only if the chain has at least two nodes        // do not create a new Node object (i.e. new Node<>()), just utilize        // reference variables (i.e. Node<T> someNode) to change appropriate pointers        // DOES NOT rely on the number of elements        if (this.firstNode != null) {            Node<T> currentNode = this.firstNode;            Node<T> previousNode = null;            while (currentNode != null)            {                previousNode = currentNode;                currentNode = currentNode.next;            }            previousNode.next = firstNode;            firstNode = firstNode.next;            previousNode.next.next = null;        }        //TODO end    } // end moveLastTofront    /**     * Replaces an entry in this bag with a given object.     *     * @param replacement the given object     * @return the original entry in the bag that was replaced, or null if the bag is empty     */    public T replace(T replacement)    {        //TODO Project3        T result = null; //Set result to null        if (this.firstNode != null)        {            result = this.firstNode.data;            this.firstNode.data = replacement; // replacing the first node        }        // do not create a new Node object, do not call any methods - just change the data at the first node        return result; //THIS IS A STUB        //TODO end    } // end replace    /**     * This method finds the data in the middle node in one pass.     * uses two pointers - DOES NOT rely on the number of elements     *     * @return returns tha data in the middle node     */    //Once fastpointer reaches the end, the slowpointer will be on the middle element    //Check if fastpointer is null, or if element next to fastpointer, that is end.    public T findMiddleElementInOnePass()    {        //TODO Project3        Node<T> slowNode = this.firstNode; //use two pointers        Node<T> fastNode = this.firstNode; //One move fast, one move slow        T result = null;        while (fastNode != null)        {            result = slowNode.data;            slowNode = slowNode.next;            fastNode = fastNode.next;            if (fastNode != null)            {                fastNode = fastNode.next;            }        }        return result;        //TODO end//    }//THIS IS A STUB    }    /**     * Check if the linked list has loop in one pass     * uses two pointers - DOES NOT rely on the number of elements     *     * @return returns true as soon as the first loop is found     */    //Check if you have 1 element -> if true loop DNE    //use a while loop to iterate until fast pointer is equal to slow pointer OR fast pointer reaches null    //Traverse the fast pointer by two spaces    //Traverse the slow pointer by one space    //IF fastpointer = slowpointer    //loop exist    //IF fastpointer = null    //loop DNE    public boolean checkIfLoopExists()    {        //TODO Project3        Boolean result = false;        Node<T> nodeA = this.firstNode;        Node<T> nodeB = this.firstNode;        while (!result && nodeA != null && nodeB != null && nodeB.next != null)        {            if(nodeA == nodeB.next || nodeA == nodeB.next.next)            {                result = true;            } else            {                nodeA = nodeA.next;                nodeB = nodeB.next.next;            }        }        return result; //THIS IS A STUB        //TODO end    }    /**     * This method is created to test loop detection     */    private void createALoop()    {        // starting with a chain: A-> B-> C-> D-> E-> F-> G-> H-> I-> null        Node<T> last = this.firstNode;        while (last.next != null)            last = last.next;        last.next = this.firstNode.next.next.next;        // hardcoded a loop in the chain: A-> B-> C-> D-> E-> F-> G-> H-> I-> D    }    private class Node<S>    {        private S data; // entry in bag        private Node<S> next; // link to next node        private Node(S dataPortion)        {            this(dataPortion, null);        } // end constructor        private Node(S dataPortion, Node<S> nextNode)        {            this.data = dataPortion;            this.next = nextNode;        } // end constructor    } // end Node    public static void main(String[] args)    {        System.out.println("RUNNING TEST CASES");        BagInterface<String> bag1 = new LinkedBag<>();        BagInterface<String> bag2 = new LinkedBag<>();        BagInterface<String> testBag = new LinkedBag<>();        BagInterface<String> emptyBag = new LinkedBag<>();        bag1.add("A");        bag1.add("B");        bag1.add("A");        bag1.add("C");        bag1.add("B");        // testing display        System.out.println("\n***Testing display method***");        System.out.println("emptyBag:");        emptyBag.display();        System.out.println("\nbag1:");        bag1.display();        System.out.println("\nbag2:");        bag2.display();        System.out.println("\nAfter removing the first element \"" + bag1.remove() + "\" from bag1, it contains");        bag1.display();        // testing equals        System.out.println("\n***Testing equals method***");        System.out.println("bag1:");        bag1.display();        System.out.println("Are bag1 and emptyBag equal? --> " + (bag1.equals(emptyBag) ? "YES" : "NO"));        System.out.println("Are emptyBag and emptyBag equal? --> " + (emptyBag.equals(emptyBag) ? "YES" : "NO"));        System.out.println("Are emptyBag and bag1 equal? --> " + (emptyBag.equals(bag1) ? "YES" : "NO"));        bag2.add("A");        bag2.add("C");        bag2.add("A");        bag2.add("B");        bag2.add("X");        System.out.println("\nbag2:");        bag2.display();        System.out.println("Are bag1 and bag2 equal? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        System.out.println("\nRemoved \"" + bag2.remove() + "\" from bag2.");        bag2.display();        System.out.println("Are bag1 and bag2 equal now? --> " + (bag1.equals(bag2) ? "YES" : "NO"));        LinkedBag<String> bagCopyOfBag1 = new LinkedBag<String>();        bagCopyOfBag1.add("A");        bagCopyOfBag1.add("B");        bagCopyOfBag1.add("A");        bagCopyOfBag1.add("C");        System.out.println("\nCreated bagCopyOfBag1:");        bagCopyOfBag1.display();        System.out.println("Are bag1 and bagCopyOfBag1 equal? --> " + (bag1.equals(bagCopyOfBag1) ? "YES" : "NO"));        LinkedBag<String> bagCopyOfBag1PlusOne = new LinkedBag<String>();        bagCopyOfBag1PlusOne.add("D");        bagCopyOfBag1PlusOne.add("A");        bagCopyOfBag1PlusOne.add("B");        bagCopyOfBag1PlusOne.add("A");        bagCopyOfBag1PlusOne.add("C");        System.out.println("\nCreated bagCopyOfBag1PlusOne:");        bagCopyOfBag1PlusOne.display();        System.out.println("Are bagCopyOfBag1PlusOne and bagCopyOfBag1 equal? --> " + (bagCopyOfBag1PlusOne.equals(bagCopyOfBag1) ? "YES" : "NO"));        System.out.println("Are bagCopyOfBag1 and bagCopyOfBag1PlusOne equal? --> " + (bagCopyOfBag1.equals(bagCopyOfBag1PlusOne) ? "YES" : "NO"));        // testing getMax        System.out.println("\n***Testing getMax method***");        System.out.println("The largest item in emptyBag is: " + emptyBag.getMax());        bag1.clear();        bag1.add("A");        bag1.add("A");        bag1.add("B");        bag1.add("X");        bag1.add("A");        bag1.add("C");        bag1.add("A");        System.out.println("\nbag1: ");        bag1.display();        System.out.println("The largest item in bag1 is: " + bag1.getMax());        bag2.clear();        bag2.add("A");        bag2.add("B");        bag2.add("B");        bag2.add("A");        bag2.add("C");        bag2.add("C");        bag2.add("D");        System.out.println("\nbag2: ");        bag2.display();        System.out.println("The largest item in bag2 is: " + bag2.getMax());        System.out.println("\n***Testing union, removeMin, intersection, difference and subset methods***");        System.out.println("bag1: ");        bag1.display();        System.out.println("bag2: ");        bag2.display();        // testing union        System.out.println("\n***Testing union method***");        BagInterface<String> everything = bag1.union(bag2);        System.out.println("The union of bag1 and bag2 is ");        everything.display();        everything = bag1.union(emptyBag);        System.out.println("\nThe union of bag1 and emptyBag is ");        everything.display();        everything = emptyBag.union(bag1);        System.out.println("\nThe union of emptyBag and bag1 is ");        everything.display();        // testing removeMin        System.out.println("\n***Testing removeMin method***");        String smallest;        while (!everything.isEmpty())        {            smallest = everything.removeMin();            System.out.println("Removed the smallest element \"" + smallest + "\" from the union bag; the current content is:");            everything.display();        }        smallest = everything.removeMin();        if (smallest == null)            System.out.println("\nThe union bag is empty and removeMin returned null - CORRECT");        else            System.out.println("\nThe union bag is empty but removeMin did not return null - INCORRECT");        // testing intersection        System.out.println("\n***Testing intersection method***");        BagInterface<String> commonItems = bag1.intersection(bag2);        System.out.println("The intersection of bag1 and bag2 is ");        commonItems.display();        commonItems = bag1.intersection(emptyBag);        System.out.println("\nThe intersection of bag1 and emptyBag is ");        commonItems.display();        commonItems = emptyBag.intersection(bag1);        System.out.println("\nThe intersection of emptyBag and bag1 is ");        commonItems.display();        // testing difference        System.out.println("\n***Testing difference method***");        BagInterface<String> leftOver = bag1.difference(bag2);        System.out.println("The difference of bag1 and bag2 is ");        leftOver.display();        leftOver = bag2.difference(bag1);        System.out.println("\nThe difference of bag2 and bag1 is ");        leftOver.display();        leftOver = bag1.difference(emptyBag);        System.out.println("\nThe difference of bag1 and emptyBag is ");        leftOver.display();        leftOver = emptyBag.difference(bag1);        System.out.println("\nThe difference of emptyBag and bag1 is ");        leftOver.display();        // testing replace        System.out.println("\n***Testing replace method***");        bag1.clear();        bag1.add("A");        bag1.add("A");        bag1.add("B");        bag1.add("X");        bag1.add("A");        bag1.add("C");        bag1.add("A");        System.out.println("Bag1 contains:");        bag1.display();        System.out.println("Replaced \"" + bag1.replace("X") + "\" with \"X\"");        System.out.println("Now bag1 contains:");        bag1.display();        System.out.println("\nCalling replace on emptyBag");        String replaced = emptyBag.replace("X");        if (replaced == null)            System.out.println("The bag is empty and replace returned null - CORRECT");        else            System.out.println("The bag is empty but replace did not return null - INCORRECT");        System.out.println("Now emptyBag contains:");        emptyBag.display();        // testing removeEvery        System.out.println("\n***Testing removeEvery method***");        System.out.println("Bag1 contains:");        bag1.display();        System.out.println("Removing all \"Z\"");        bag1.removeEvery("Z");        System.out.println("After removing all \"Z\" bag1 contains:");        bag1.display();        System.out.println("\nRemoving all \"X\"");        bag1.removeEvery("X");        System.out.println("After removing all \"X\" bag1 contains:");        bag1.display();        System.out.println("\nAfter adding two \"A\" bag1 contains:");        bag1.add("A");        bag1.add("A");        bag1.display();        System.out.println("Removing all \"A\"");        bag1.removeEvery("A");        System.out.println("After removing all \"A\" bag1 contains:");        bag1.display();        System.out.println("\nRemoving all \"B\"");        bag1.removeEvery("B");        System.out.println("After removing all \"B\" bag1 contains:");        bag1.display();        System.out.println("\nAfter removing all \"C\" emptyBag contains:");        emptyBag.display();        System.out.println("\n*** TESTING moveFirstToEnd ***");        testBag.clear();        testBag.add("C");        testBag.add("B");        testBag.add("A");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Calling moveFirstToEnd three times");        testBag.clear();        testBag.add("B");        testBag.add("C");        testBag.add("A");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        testBag.moveFirstToEnd();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Calling moveFirstToEnd on a list of length 0");        testBag.clear();        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Calling moveFirstToEnd on a list of length 1");        testBag.clear();        testBag.add("B");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("Calling moveFirstToEnd on a list of length 2");        testBag.clear();        testBag.add("A");        testBag.add("B");        System.out.println("List before:");        testBag.display();        testBag.moveFirstToEnd();        System.out.println("List after:");        testBag.display();        System.out.println();        System.out.println("\n*** TESTING findMiddleElementInOnePass ***");        testBag.clear();        testBag.display();        System.out.println("middle: " + testBag.findMiddleElementInOnePass());        testBag.add("A");        testBag.display();        System.out.println("middle: " + testBag.findMiddleElementInOnePass() + "\n");        testBag.add("B");        testBag.display();        System.out.println("middle: " + testBag.findMiddleElementInOnePass() + "\n");        testBag.add("C");        testBag.add("D");        testBag.add("E");        testBag.add("F");        testBag.display();        System.out.println("middle: " + testBag.findMiddleElementInOnePass() + "\n");        testBag.add("G");        testBag.display();        System.out.println("middle: " + testBag.findMiddleElementInOnePass() + "\n");        System.out.println("\n*** TESTING checkIfLoopExists ***");        boolean result = testBag.checkIfLoopExists();        if (!result)            System.out.println("testBag does not have a loop - CORRECT");        else            System.out.println("testBag does have a loop - INCORRECT");        LinkedBag<String> bagWithLoop = new LinkedBag<>();        bagWithLoop.add("I");        bagWithLoop.add("H");        bagWithLoop.add("G");        bagWithLoop.add("F");        bagWithLoop.add("E");        bagWithLoop.add("D");        bagWithLoop.add("C");        bagWithLoop.add("B");        bagWithLoop.add("A");        bagWithLoop.createALoop();        result = bagWithLoop.checkIfLoopExists();        if (result)            System.out.println("bagWithLoop does have a loop - CORRECT");        else            System.out.println("bagWithLoop does not have a loop - INCORRECT");    }} // end LinkedBag