import java.util.*;

public class Concordance
{
    private TreeMap<String, LinkedList<Integer>> wordTable;

    public Concordance()
    {
        this.wordTable = new TreeMap<>();
    } // end default constructor


    /**
     * Task: Reads a text file of words and creates a concordance.
     *
     * @param data a text scanner for the text file of data
     */
    public void readFile(Scanner data)
    {
        int lineNumber = 1;
        while (data.hasNext())
        {
            String line = data.nextLine();
            line = line.toLowerCase();
            Scanner lineProcessor = new Scanner(line);
            lineProcessor.useDelimiter("\\W+");
            while (lineProcessor.hasNext())
            {
                String nextWord = lineProcessor.next();
                LinkedList<Integer> lineList = this.wordTable.get(nextWord);
                if (lineList == null)
                { // create new list for new word; add list and word to index
                    lineList = new LinkedList<>();
                    this.wordTable.put(nextWord, lineList);
                }
                // add line number to end of list so list is sorted
                lineList.add(lineNumber);
            }
            lineNumber++;
        }
        data.close();
    } // end readFile


    /**
     * Task: Displays words and the lines in which they occur.
     */
    public void display()
    {
//        VERSION #1:
//        Set<String> keys = this.wordTable.keySet();
//        Iterator<String> keyIterator = keys.iterator();
//        while (keyIterator.hasNext ())
//        {
//            // display the word
//            String currentKey = keyIterator.next();
//        	  System.out.print (currentKey + " " );
//            // get line numbers and iterator
//            LinkedList<Integer> lineList = this.wordTable.get(currentKey);
//            Iterator < Integer > listIterator = lineList.iterator ();
//            // display line numbers
//            while (listIterator.hasNext ())
//            {
//                System.out.print (listIterator.next () + " ");
//            } // end while
//            System.out.println ();
//        } // end while


//      VERSION #2:
//        Set<Map.Entry<String, LinkedList<Integer>>> set = this.wordTable.entrySet();
//        Iterator<Map.Entry<String, LinkedList<Integer>>> entryIterator = set.iterator();
//        while (entryIterator.hasNext())
//        {
//            Map.Entry<String, LinkedList<Integer>> entry = entryIterator.next();
//            System.out.print(entry.getKey() + " ");
//            LinkedList<Integer> lineList = entry.getValue();
//            Iterator<Integer> listIterator = lineList.iterator();
//            // display line numbers
//            while (listIterator.hasNext())
//            {
//                System.out.print(listIterator.next() + " ");
//            }
//            System.out.println();
//        }


//      VERSION #3:
//        for (Map.Entry<String, LinkedList<Integer>> entry : this.wordTable.entrySet())
//        {
//            System.out.print(entry.getKey() + " ");
//            for (Integer lineItem : entry.getValue())
//            {
//                System.out.print(lineItem + " ");
//            }
//            System.out.println();
//        }


//      VERSION #4 (shorthand of version #3):
//        for (Map.Entry<String, LinkedList<Integer>> entry : this.wordTable.entrySet())
//        {
//            System.out.print(entry.getKey() + " ");
//            entry.getValue().forEach(lineItem -> System.out.print(lineItem + " "));
//            System.out.println();
//        }


//      VERSION #5 (as of java 1.8):
        this.wordTable.forEach((k,v) ->
        {
            System.out.print(k + " ");
            v.forEach(lineItem -> System.out.print(lineItem + " "));
            System.out.println();
        });
    } // end display   
}