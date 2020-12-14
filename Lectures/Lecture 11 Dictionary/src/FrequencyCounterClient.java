import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
public class FrequencyCounterClient
{
    public static void main (String [] args)
    {
        FrequencyCounter wordCounter = new FrequencyCounter ();
        String fileName = "Data.txt"; // or file name could be read
        try
        {
            Scanner data = new Scanner (new File (fileName));
            wordCounter.readFile (data);
        }
        catch (FileNotFoundException e)
        {
            System.out.println ("File not found: " + e.getMessage ());
        }
        catch (IOException e)
        {
            System.out.println ("I/O error " + e.getMessage ());
        }
        wordCounter.display ();
        System.out.println ("Bye!");
    } // end main
}