package Lab08;

import javafx.scene.Node;

/**
 * @version 3/12/2019
 * @name Sean Blanchard
 * Lab 08
 */
public class CircularArrayQueue<T> implements QueueInterface<T>
{
    private T[] queue; // Circular array of queue entries and one unused location
    private int frontIndex; // Index of front entry
    private int backIndex;  // Index of back entry
    private boolean initialized = false;
    private static final int DEFAULT_CAPACITY = 3;
    private static final int MAX_CAPACITY = 10000;

    public CircularArrayQueue(int length)
    {

        // The cast is safe because the new array contains null entries
        @SuppressWarnings("unchecked")
        T[] tempQueue = (T[]) new Object[DEFAULT_CAPACITY + 1];
        this.queue = tempQueue;
        this.frontIndex = 0;
        this.backIndex = DEFAULT_CAPACITY;
        this.initialized = true;
    } // end constructor

    public CircularArrayQueue(T[] initialContent)
    {
        // TODO Project 2A
        this(initialContent.length);
        for (int i = 0; i < initialContent.length; i++)
        {
            this.enqueue(initialContent[i]);// filling this.queue with entries
        }

    } // end constructor


    public void enqueue(T newEntry)
    {
        //System.out.println("enqueue(" + newEntry + ")");               // ***TESTING
        // TODO Project 2A
        checkInitialization();
        ensureCapacity();
//        if(isEmpty())
//        {
//            this.frontIndex = this.backIndex;
//        }


        this.backIndex = (backIndex + 1) % queue.length;


        this.queue[backIndex] = newEntry;

//        freeNode.setData(newEntry);
//
//        if(isChain)

        //-------------------------------------------------
        //FROM THE BOOK

//        Node newNode = new Node(newEntry, null);
//
//        if(isEmpty())
//        {
//            this.frontIndex = newNode;
//        }
//        else
//        {
//            this.backIndex.setNextNode(newNode);
//        }
//        this.backIndex = newNode;
        //System.out.println("queue[" + backIndex + "] = " + newEntry);  // ***TESTING
    } // end enqueue

    public T getFront() throws EmptyQueueException
    {
        // TODO Project 2A
        checkInitialization();
        if(isEmpty()) {
            throw new Lab08.EmptyQueueException();
        }
            return this.queue[frontIndex];
    } // end getFront

    public T dequeue() throws EmptyQueueException
    {
        // TODO Project 2A
        checkInitialization();
        if(isEmpty())
        {
            throw new Lab08.EmptyQueueException();
        }
        else
        {
            T front = queue[frontIndex];
            queue[frontIndex] = null;
            frontIndex = (frontIndex + 1) % queue.length;
            return front;
        }
       // return null;
    } // end dequeue

    public boolean isEmpty()
    {
        // TODO Project 2A
        return frontIndex == ((backIndex + 1 ) % this.queue.length);
        //return true;  // THIS IS A STUB
    } // end isEmpty

    public void clear()
    {
        // null out only the used slots
        // TODO Project 2A
//        if(!isEmpty()) {
//            for (int i = 0; i < queue.length; i++) {
//                if (this.queue[i] != null)
//                {
//                    this.queue[i] = null;
//                }
//            }
        checkInitialization();
        if (!isEmpty())
        {
            for (int index = frontIndex; index != backIndex; index = (index + 1) % queue.length)
            {
                queue[index] = null;
            } // end for

        queue[backIndex] = null;

        } // end if
        frontIndex = 0;
        backIndex = queue.length - 1;
    }
    //} // end clear


    // Throws an exception if this object is not initialized.
    private void checkInitialization()
    {
        if (!this.initialized)
            throw new SecurityException("CircularArrayQueue object is not initialized properly.");
    } // end checkInitialization

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity)
    {
        if (capacity > MAX_CAPACITY)
            throw new IllegalStateException("Attempt to create a queue " +
                    "whose capacity exceeds " +
                    "allowed maximum.");
    } // end checkCapacity

    // Doubles the size of the array queue if it is full.
    // Precondition: checkInitialization has been called.
    private void ensureCapacity()
    {
        // TODO Project 2A
        if (frontIndex == ((backIndex + 2) % queue.length))
        {
            T[] oldQueue = this.queue;
            int oldSize = oldQueue.length;
            int newSize = 2 * oldSize;
            checkCapacity(newSize - 1);
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[]) new Object[newSize];
            this.queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++)
            {
                this.queue[index] = oldQueue[frontIndex];
                frontIndex = (frontIndex + 1) % oldSize;
            }
            frontIndex = 0;
            backIndex = oldSize - 2;
        }
    } // end ensureCapacity
}
