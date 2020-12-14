package Lab12;

import java.util.*;
import java.io.*;

/**
 * @author atb
 * @version 4/16/2019
 * @modifiedBy Sean Blanchard
 */
public class CheckInventory
{
    // TODO Project 2 Part1 - implement CardID class
    // TODO Project 2 Part1 - implement CheckInventory class

    // uncomment main when the class CardID and
    // the skeleton for this class are in place

    private DictionaryInterface<CarID, Integer> hashDictionary;
    //set DEFAULT_CAPACITY to 5
    private final int DEFAULT_CAPACITY = 5;
    private Random random;

    public CheckInventory()
    {
        //random with seed set to 101
        this.random = new Random(101);
        createHashedDictionary();
    }

    public void createHashedDictionary()
    {
        this.hashDictionary = new HashedDictionary<>();
    }

    public boolean compareInventory(String newFile1, String newFile2) throws InputMismatchException, FileNotFoundException
    {
        File File1 = new File(newFile1);
        File File2 = new File(newFile2);
        Scanner f1Scanner = new Scanner(File1);
        Scanner f2Scanner = new Scanner(File2);

        while (f1Scanner.hasNextLine())
            {
            Scanner carIDScan = new Scanner(f1Scanner.nextLine());
            String carSequence = carIDScan.next();
            long carNumberSequence = carIDScan.nextLong();
            CarID newID = new CarID(carSequence, carNumberSequence);
            hashDictionary.add(newID, 1);
            } //while (f1Scanner.hasNextLine());

        while (f1Scanner.hasNextLine())
        {
            Scanner carIDScan = new Scanner(f2Scanner.nextLine());
            String carSequence = carIDScan.next();
            long carNumberSequence = carIDScan.nextLong();
            CarID carKey = new CarID(carSequence, carNumberSequence);
            Integer value = this.hashDictionary.getValue(carKey);
            if(value == null)
            {
                this.hashDictionary.add(carKey, -1);
            }
            else
            {
                this.hashDictionary.add(carKey, value - 1);
            }
        } //while (f2Scanner.hasNextLine());

        boolean bool = true;
        Iterator <Integer> iterator = this.hashDictionary.getValueIterator();
        while(iterator.hasNext() && bool)
        {
            Integer current = iterator.next();
            if(current != 0)
            {
                bool = false;
            }
        }
        return !bool;

    }

    public TreeSet<CarID> generateContentAndSaveToRandomFile(int size, String newfile) throws IOException
    {
        //generateContentAndSaveToRandomFile method: generates the car IDs
        //randomly and save them to a Treeset<CarID> to ensure that they
        //are distinct. At the same time when it is know they are distinct
        //writes them into randomFile.txt

        PrintWriter print = new PrintWriter(newfile);
        TreeSet<CarID> treeSet = new TreeSet<>();

        for(int i = 0; i < size; i++)
        {
            final int ASCII_Z = 90;
            final int ASCII_A = 65;
            StringBuilder characterSequence = new StringBuilder();
            long numberSequence;

            for(int j = 0; j < CarID.NUMERIC_SEQUENCE_LENGTH; j++)
            {
                int upperCase = this.random.nextInt((ASCII_Z-ASCII_A+1) + ASCII_A);
                characterSequence.append((char)upperCase);
            }

            do {
                numberSequence = Math.abs(random.nextLong());
            } while((int)Math.log10(numberSequence) + 1 != CarID.NUMERIC_SEQUENCE_LENGTH);

            print.println(characterSequence + " " + numberSequence);


            String characterSequenceString = characterSequence.toString();

            CarID currentID = new CarID(characterSequenceString, numberSequence);

            treeSet.add(currentID);

          print.println(currentID);
        }

        hashDictionary.displayHashTable();
        //print.close();
        return treeSet;


    }

    public void saveSortedContentToSortedFile(TreeSet<CarID> tree, String file) throws IOException
    {
        PrintWriter print = new PrintWriter(file);
        tree.forEach(CarID ->
        {
            print.println(CarID);
        });
        //print.close();
    }

    public void createCorruptedFile(TreeSet<CarID>tree, String file) throws IOException
    {
        PrintWriter print = new PrintWriter(file);
        tree.forEach(CarID ->
        {
           boolean rand = this.random.nextBoolean();
           if(rand = true)
           {
               print.println(CarID);
           }
        });
        print.close();

    }


    public static void main(String[] args)
    {
        String receivedFile = "randomFile.txt";
        String sentFile = "sortedFile.txt";
        String corruptedFile = "corruptedFile.txt";
        CheckInventory checker = new CheckInventory();

        try
        {
            System.out.println("How many CarIDs to generate?");
            Scanner keyboard = new Scanner(System.in);
            int amount = keyboard.nextInt();
            TreeSet<CarID> sortedSet = checker.generateContentAndSaveToRandomFile(amount, receivedFile);
            checker.saveSortedContentToSortedFile(sortedSet, sentFile);
            checker.createCorruptedFile(sortedSet, corruptedFile);
            System.out.println("\n*** Checking if \"" + sentFile + "\" and \"" + receivedFile + "\" have the same elements ***");
            boolean same = checker.compareInventory(receivedFile, sentFile);
            System.out.println("--> the elements in files \"" + receivedFile
                    + "\" and \"" + sentFile
                    + " are " + (same ? "" : "NOT ") + "the same");


            System.out.println("\n*** Checking if \"" + sentFile + "\" and \"" + corruptedFile + "\" have the same elements ***");
            checker.createHashedDictionary();
            same = checker.compareInventory(sentFile, corruptedFile);
            System.out.println("--> the elements in files \"" + corruptedFile
                    + "\" and \"" + sentFile
                    + " are " + (same ? "" : "NOT ") + "the same");

        } catch (IOException ioe)
        {
            System.out.println("There was an error in reading or opening the file: ");
            System.out.println(ioe.getMessage());
        } catch (InputMismatchException ime)
        {
            System.out.println(ime.getMessage());
        }
        System.out.println("\nBye!");
    }  // end main
}
