package Lab13;

import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;
import java.io.*;

/**
 * A class that implements text encoding/decoding with Morse Code
 *
 * @author Sean Blanchard
 * @version 4/23/2019
 */

public class MorseCode
{
    private BinaryNode<Character> root;

    public MorseCode()
    {
        this.root = new BinaryNode<>(' ');
    }

    private void buildMorseCodeTree()
    {
        int index;
        BinaryNode<Character> current;
        BinaryNode<Character> letterTree;
        String code = null;
        try
        {
            Scanner file = new Scanner(new File("MorseCode.txt"));
            System.out.println("The Morse Code:");
            System.out.println("===============");

            while (file.hasNext()) // test for the end of the file
            {
                code = file.nextLine();  // read a line
                System.out.println(code);  // print the line read
                // building the tree
                letterTree = new BinaryNode<>(code.charAt(0));
                current = this.root;
                index = 2;
                for (; index < code.length() - 1; index++)
                {
                    if (code.charAt(index) == '.')
                    {
                        current = current.getLeftChild();
                    }
                    else // must be '_'
                    {
                        current = current.getRightChild();
                    }
                }
                if (code.charAt(index) == '.')
                {
                    current.setLeftChild(letterTree);
                }
                else // must be '_'
                {
                    current.setRightChild(letterTree);
                }
            }
            file.close();

        } catch (FileNotFoundException fnfe)
        {
            System.out.println("Unable to find MorseCode.txt, exiting");
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }

    private String decode(String encoded)
    {
        // TODO Project 1
        System.out.println("Decoding \"" + encoded + "\"");
        BinaryNode<Character> current = this.root;
        StringBuilder answer = new StringBuilder();;

        try
        {
            Scanner scanner = new Scanner(encoded);
            while(scanner.hasNext())
            {
                String newString = scanner.next();

                for(int i = 0; i < newString.length(); i++)
                {
                    //String chars = scanner.next();
                    //if(encoded.equals("."))
                    if(newString.charAt(i) == '.')
                    {
                            current = current.getLeftChild();
                    }
                    else
                    {
                            current = current.getRightChild();
                    }

                }
                answer = answer.append(current.getData());
                current = this.root;
            }

        } catch(NullPointerException npe)
        {
            System.out.println("Not a morse pattern.");
        }


        // YOUR CODE GOES HERE

        System.out.println("The decoded string is \"" + answer.toString() + "\"");
        return encoded;
    }

    public void displayInPreOrder()
    {
        System.out.println("  " + displayInPreOrder(this.root));
    }

    public String displayInPreOrder(BinaryNode<Character> current)
    {
        return (current.getLeftChild() != null ? current.getLeftChild().getData() + " " + displayInPreOrder(current.getLeftChild()) : "")
                +(current.getRightChild() != null ? current.getRightChild().getData() + " " + displayInPreOrder(current.getRightChild()) : "");
    }

    public LevelOrderIterator getLevelOrderIterator()
    {
        return new LevelOrderIterator();
    } // end getLevelOrderIterator


    private class LevelOrderIterator implements Iterator
    {
        //private QueueInterface<TreeNode> nodeQueue;
        private LinkedBlockingQueue<BinaryNode<Character>> nodeQueue;

        public LevelOrderIterator()
        {
            this.nodeQueue = new LinkedBlockingQueue<>();
            if (root != null)
            {
                this.nodeQueue.offer(root);
            }
        } // end default constructor

        public boolean hasNext()
        {
            return !this.nodeQueue.isEmpty();
        } // end hasNext

        public BinaryNode<Character> next()
        {
            BinaryNode<Character> nextNode;

            if (hasNext())
            {
                nextNode = this.nodeQueue.poll();
                BinaryNode<Character> leftChild = nextNode.getLeftChild();
                BinaryNode<Character> rightChild = nextNode.getRightChild();

                // add to queue in order of recursive calls
                if (leftChild != null)
                    this.nodeQueue.offer(leftChild);

                if (rightChild != null)
                    this.nodeQueue.offer(rightChild);
            }
            else
            {
                throw new NoSuchElementException();
            }

            return nextNode;
        } // end next

        public void remove()
        {
            throw new UnsupportedOperationException();
        } // end remove
    } // end LevelOrderIterator


    public static void main(String[] args)
    {
        MorseCode morseCode = new MorseCode();
        morseCode.buildMorseCodeTree();

        LevelOrderIterator iter = morseCode.getLevelOrderIterator();
        System.out.println("\nThe Morse Code Tree in level order:");
        while (iter.hasNext())
        {
            System.out.print(iter.next().getData() + " ");
        }
        System.out.println("\n\n");

        System.out.println("\nThe Morse Code Tree in pre-order:");
        morseCode.displayInPreOrder();
        System.out.println("\n\n");

        String input = "";
        boolean done = false;
        Scanner keyboard = new Scanner(System.in);
        do
        {
            do
            {
                System.out.println("Please enter a message in Morse Code, use space as a separator. Press enter to stop.");
                input = keyboard.nextLine();
                if (input.equals(""))
                    done = true;
            } while (!input.matches("[._ ]+") && !done);

            if (!done)
                morseCode.decode(input);
        } while (!done);
        System.out.println("Done decoding.");
    }
}