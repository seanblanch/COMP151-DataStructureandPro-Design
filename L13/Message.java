package Lab13;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A class that will be used to display the message that will be encoded.
 *
 * @author Charles Hoot
 * @version 4/23/2019
 * @modifiedBy atb
 */

public class Message implements Iterator<Character>
{

    private ArrayList<String> lines;
    private int currentLine;
    private int lastCharacterProduced;

    /**
     * Constructor for objects of class Message
     */
    public Message()
    {
        this.lines = new ArrayList<>();
        reset();
    }

    /**
     * Add a new string to the Lines.  (don't add empty strings).
     *
     * @param s a string
     */
    public void addLine(String s)
    {
        if (s.length() > 0)
            this.lines.add(s);

    }

    private boolean hasAnotherLine()
    {
        return this.currentLine < this.lines.size() - 1;
    }


    private boolean hasAnotherCharacterInLine()
    {
        return (this.lastCharacterProduced < this.lines.get(this.currentLine).length() - 1);
    }

    /**
     * Is there another character that can be retrieved.  Satisfies the iterator interface.
     *
     * @return true if there is another character
     */
    public boolean hasNext()
    {
        return hasAnotherLine() || hasAnotherCharacterInLine();
    }

    /**
     * this method is needed for the iterator interface, but it is not implemented by this class
     */
    public void remove()
    {
        throw new RuntimeException("Remove not supported");
    }

    /**
     * Reset the message buffer iteration back to the beginning.
     */
    public void reset()
    {
        this.currentLine = 0;
        this.lastCharacterProduced = -1;
    }

    /**
     * get the next character
     * if the end of a line has been reached, increment the current line and set the current
     * character to the first character in the next line.Satisfies the iterator interface.
     *
     * @return the next character
     */
    public Character next()
    {
        Character result = null;

        if (hasAnotherCharacterInLine())
        {
            this.lastCharacterProduced++;
            result = this.lines.get(this.currentLine).charAt(this.lastCharacterProduced);
        }
        else
        {
            // Check to see if we can go to the next line
            this.currentLine++;
            this.lastCharacterProduced = 0;
            result = this.lines.get(this.currentLine).charAt(this.lastCharacterProduced);
        }
        return result;
    }

    /**
     * Creates String representation of the message to be encoded
     *
     * @return
     */
    public String toString()
    {
        String result = "The message lines are:\n";
        Iterator iter = this.lines.iterator();
        while (iter.hasNext())
        {
            result = result + iter.next() + "\n";
        }
        return result;
    }
}