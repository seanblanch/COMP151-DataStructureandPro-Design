import java.util.*;

public class FrequencyCounter
{
    private TreeMap<String, Integer> wordTable;

    public FrequencyCounter()
    {
        this.wordTable = new TreeMap<>();
    } // end default constructor


    /**
     * Task: Reads a text file of words and counts their frequencies
     * of occurrence.
     *
     * @param data a text scanner for the text file of data
     */
    public void readFile(Scanner data)
    {
        data.useDelimiter("\\W+");
        while (data.hasNext())
        {
            String nextWord = data.next();
            nextWord = nextWord.toLowerCase();
            Integer frequency = this.wordTable.get(nextWord);
            if (frequency == null)
            { // add new word to table
                this.wordTable.put(nextWord, new Integer(1));
            } else
            { // increment count of existing word; replace wordTable entry
                frequency++;
                this.wordTable.put(nextWord, frequency);
            }
        }
        data.close();
    } // end readFile


    /**
     * Task: Displays words and their frequencies of occurrence.
     */
    public void display()
    {
//      VERSION #1:
//        Set<String> keys = wordTable.keySet();
//        Iterator<String> keyIterator = keys.iterator();
//        Collection<Integer> values = wordTable.values();
//        Iterator<Integer> valueIterator = values.iterator();
//        while (keyIterator.hasNext ())
//        {
//            System.out.println (keyIterator.next() + " " + valueIterator.next());
//        } // end while


//      VERSION #2:
//        Set<Map.Entry<String, Integer>> set = this.wordTable.entrySet();
//        Iterator<Map.Entry<String, Integer>> entryIterator = set.iterator();
//        while (entryIterator.hasNext())
//        {
//            Map.Entry<String, Integer> entry = entryIterator.next();
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }


//      VERSION #3 (shorthand of version 2):
//        for (Map.Entry<String, Integer> entry : this.wordTable.entrySet())
//        {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }


//      VERSION #4 (as of java 1.8):
        this.wordTable.forEach((k, v) -> System.out.println(k + " " + v));

    } // end display
} // end FrequencyCounter
