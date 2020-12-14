package Lab13;

import java.io.File;
import java.util.*;


/**
 * A class that implements text encoding using Huffman compression algorithm
 *
 * @author Sean Blanchard
 * @version 4/23/2019
 */
public class EncodeApplication
{
    //  contains all the lines included in the given text file
    private Message myMessage;
    //  contains the myMessage encoded with Huffman code
    private Code myCode;
    //  contains Huffman trees for each unique character in myMessage
    private HuffmanTree[] myTrees;
    //  initialized to the number of unique characters in the message
    private int numberOfTrees;
    //   allowing ASCII character set
    private final int MAX_NUMBER_OF_CHARS = 128;

    /**
     * Constructor for objects of class EncodeApplication
     */
    public EncodeApplication()
    {
        this.myMessage = new Message();
        this.myCode = new Code();
        this.myTrees = null;
        this.numberOfTrees = 0;
    }


    /**
     * count the number of times each character appears in the message.
     *
     * @return an array with the count for the number of times each character occurs.
     */
    public int[] getCounts()
    {
        // TODO Project 4
        int[] result = new int[MAX_NUMBER_OF_CHARS + 1];
       // System.out.println("getCounts - IMPLEMENT ME");
        Character c;

        while(this.myMessage.hasNext())
        {
            c = this.myMessage.next();
            result[c]++;
            //if there is 1 there is a tree there
            if(result[c] == 1)
            {
                this.numberOfTrees++;
            }
        }

        for(int i = 0; i < result.length; i++)
        {
            c = (char) i;
            //if there is something there
            if(result [c] != 0)
            {
                System.out.println("Count of character " + c + " is " + result[c]);
            }
        }
        return result;
    }

    /**
     * create the initial forrest of trees.
     *
     * @param count the frequency of each character
     *              to be encoded - essentially the array returned by getCounts method.
     * @return the forest of trees for each single letter
     */
    public HuffmanTree[] createInitialTrees(int[] count)
    {
        // TODO Project 4
        System.out.println("Creating " + this.numberOfTrees + " initial trees");
        HuffmanTree[] result = new HuffmanTree[this.numberOfTrees];

        //System.out.println("createInitialTrees - IMPLEMENT ME");

        ArrayList<Character> tempCount = new ArrayList<>(1);
        Character c;

        int resultCounter = 0;
        for(int i = 0; i < count.length; i++)
        {
            tempCount = new ArrayList<>(1);
            c = (char) i;
            if(count[c] != 0)
            {
                tempCount.add(c);
                result[resultCounter] = new HuffmanTree(new HuffmanCode(tempCount, count[c]));
                resultCounter++;
            }

        }


        return result;
    }


    /**
     * Creates final Huffman Tree by combing two "smallest trees" at a time.
     * The smallest tree is the tree with the smallest frequency
     * Utilizes findSmallest and swap methods
     * Operates on the forest of trees contained in the variable myTrees.
     */
    public void createHuffmanTree()
    {
        // TODO Project 4
        //System.out.println("createHuffmanTree - IMPLEMENT ME");
        int smallest;
        int secondSmallest;
        while (this.numberOfTrees > 1)
        {
            // swap smallest to the end

            //ArrayList<Character> temp = new ArrayList<>();
            //ArrayList<Character> temp2 = new ArrayList<>();

            smallest = findSmallest(numberOfTrees);
            swap(smallest, numberOfTrees - 1);

            System.out.println("--->Smallest tree moved to the position " + (this.numberOfTrees - 1));


            // swap second smallest to the end

            secondSmallest = findSmallest(numberOfTrees-1);
            swap(secondSmallest, numberOfTrees-2);

            System.out.println("--->Second smallest tree moved to the position " + (this.numberOfTrees - 2));

            // Construct a new combined tree and put in place of the second last tree

            ArrayList<Character> temp = new ArrayList<>(this.numberOfTrees);
            ArrayList<Character> temp2 =(ArrayList) this.myTrees[this.numberOfTrees-1].getCurrentData().getSymbols();
            ArrayList<Character> temp3 = (ArrayList) this.myTrees[this.numberOfTrees-2].getCurrentData().getSymbols();

            for(int i = 0; i < temp2.size(); i++)
            {
                temp.add(temp2.get(i));
            }
            for(int g = 0; g < temp3.size(); g++)
            {
                temp.add(temp3.get(g));
            }

            int total = this.myTrees[this.numberOfTrees-1].getCurrentData().getFrequency() + this.myTrees[this.numberOfTrees-2].getCurrentData().getFrequency();
            this.myTrees[this.numberOfTrees-2] = new HuffmanTree(new HuffmanCode(temp, total), this.myTrees[numberOfTrees-1], this.myTrees[numberOfTrees-2]);
            System.out.println("Combined create Tree " + this.myTrees[numberOfTrees-2].getCurrentData().toString());
            System.out.println("Position of combined tree " + (this.numberOfTrees-2));



                 //System.out.println("--->Combined tree created: " + symbols + " -> " + combinedCount);

            System.out.println("--->Combined tree added at position " + (this.numberOfTrees - 2));
            //Decrement number of trees by 1
            this.numberOfTrees--;
        }
    }

    /**
     * swaps two elements in myTrees
     *
     * @param index1
     * @param index2
     */
    private void swap(int index1, int index2)
    {
        HuffmanTree temp = this.myTrees[index1];
        this.myTrees[index1] = this.myTrees[index2];
        this.myTrees[index2] = temp;
    }

    /**
     * finds the Huffman tree with the smallest frequency
     *
     * @param last - the index of the last tree to check
     * @return - the index of the "smallest" tree in the given range
     */
    private int findSmallest(int last)
    {
        int smallestAt = 0;
        for (int i = 1; i < last; i++)
        {
            if ((this.myTrees[smallestAt].getRootData()).compareTo(this.myTrees[i].getRootData()) > 0)
            {
                smallestAt = i;
            }
        }
        return smallestAt;
    }

    /**
     * encode a single symbol using a Huffman tree and append
     * the Huffman code to myCode  object
     *
     * @param c the symbol to be encoded.
     */
    public void encodeCharacter(Character c)
    {
        // TODO Project 4
        //System.out.println("encodeCharacter - IMPLEMENT ME");
        // make sure that we point to the root
        // at this point we only have one tree
        this.myTrees[0].reset();

        while(!this.myTrees[0].isSingleSymbol())
        {
            //<-- only look at the trees where the symbol list contains one element
            if(this.myTrees[0].checkLeft(c))
            {
                this.myTrees[0].advanceLeft();
                this.myCode.addCharacter('0');
            }
            else
            {
                this.myTrees[0].advanceRight();
                this.myCode.addCharacter('1');
            }
        }

        this.myCode.addCharacter(' ');




    }

    /**
     * encodes myMessage using the generated Huffman Code by calling
     * the encodeCharacter method for each character in myMessage.
     */
    private void encodeMessage()
    {
        this.myMessage.reset();
        while (this.myMessage.hasNext())
        {
            //fill myCode with the code message
            encodeCharacter(this.myMessage.next());
        }
    }

    /**
     * load the words into the Message
     *
     * @param theFileName the name of the file holding the message
     */
    public boolean loadMessage(String theFileName)
    {
        Scanner input;
        boolean loaded = false;
        try
        {
            input = new Scanner(new File(theFileName));

            while (input.hasNextLine())  // read until  end of file
            {
                this.myMessage.addLine(input.nextLine());
            }
            System.out.println(this.myMessage);
            loaded = true;
        } catch (Exception e)
        {
            System.out.println("There was an error in reading or opening the file: " + theFileName);
            System.out.println(e.getMessage());
        }
        return loaded;
    }

    public void run()
    {
        if (loadMessage("myName.txt"))
        {
            this.myTrees = createInitialTrees(getCounts());
            this.numberOfTrees = this.myTrees.length;

            System.out.println("Building Huffman Tree");
            createHuffmanTree();

            //the tree is created so let's display it using preOrder
            System.out.println("\nHuffman Tree:");
            this.myTrees[0].preOrderTraverse();
            System.out.println();
            //we can encode now
            encodeMessage();
            System.out.println(this.myCode);
        }
    }


    public static void main(String[] args)
    {
        EncodeApplication encodeApp = new EncodeApplication();
        encodeApp.run();
    }
}