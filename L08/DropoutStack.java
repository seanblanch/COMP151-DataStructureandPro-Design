package Lab08;

import java.util.ArrayDeque;
import java.util.Arrays;

/**
 * A class of stacks whose entries are stored in a deque.
 * @author Sean Blanchard
 * @version 3/12/2019
 */
public class DropoutStack<T>
{
    // TODO Project 1
    // IMPLEMENT ALL THE METHODS DEFINED IN THE UML DIAGRAM
    //       push method should utilize removeLast()
    // UNCOMMENT THE display and main METHODS WHEN READY FOR TESTING


    int DEFAULT_CAPACITY = 20;
    ArrayDeque<T> stack; //= new ArrayDeque<T>();

    DropoutStack()
    {
        stack = new ArrayDeque<T>();
    }

    DropoutStack(int capacity)
    {
        stack = new ArrayDeque<T>(capacity);
    }

    public void push(T val)
    {
        if(stack.size() >= DEFAULT_CAPACITY) {
            stack.removeLast();
        }
        stack.push(val);
    }

    public T peek()
    {
        return stack.peek();
    }

    public T pop()
    {
        if(isEmpty())
        {
            return null;
        }

        return stack.pop();

    }

    public boolean isEmpty()
    {
        return stack.isEmpty();
    }

    public void clear()
    {
        stack.clear();;
    }



    /**
     * METHOD display implemented for debugging purposes
     */
    public void display()
    {
        if (isEmpty())
            System.out.println("The stack is empty");
        else
            System.out.println(Arrays.toString(this.stack.toArray()));
    }

    public static void main(String args[])
    {
        System.out.println("**************  TESTING DROPOUT STACK  **************\n");
        DropoutStack<Integer> dropoutStack = new DropoutStack<Integer>();

        System.out.println("---->  Adding 20 items to empty stack of capacity of 20");
        for (int i = 0; i < 20; i++)
        {
            dropoutStack.push(i);
        }
        System.out.println("---->  The content of the stack is:");
        dropoutStack.display();
        System.out.println("----> The top of the stack is: " + dropoutStack.peek());
        System.out.println("\n----> Adding 5 more items to full stack");
        for (int i = 20; i < 25; i++)
        {
            System.out.println("push " + i);
            dropoutStack.push(i);
        }

        System.out.println("---->  The content of the stack is:");
        dropoutStack.display();
        System.out.println("----> The top of the stack is: " + dropoutStack.peek());

        System.out.println("\n---->  Removing all elements from the stack:");
        while (!dropoutStack.isEmpty())
        {
            System.out.println("----> pop " + dropoutStack.pop());
        }

        dropoutStack.display();
        System.out.println("----> The top of the stack is: " + dropoutStack.peek());

        System.out.println("\n----> Trying to pop from the empty stack");
        System.out.println("----> Got back " + dropoutStack.pop());

        System.out.println("\n----> Trying to peek at the top of the empty stack");
        System.out.println("----> Got back " + dropoutStack.peek());

        System.out.println("\n----> Adding 22 items to empty stack of capacity of 20");
        for (int i = 0; i < 22; i++)
        {
            dropoutStack.push(i);
        }
        System.out.println("---->  The content of the stack is:");
        dropoutStack.display();

        System.out.println("\n---->  Clearing the stack with the clear method");
        dropoutStack.clear();
        System.out.println("---->  The content of the stack is:");
        dropoutStack.display();
    }
}