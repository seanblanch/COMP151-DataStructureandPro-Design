package Lab11;

/**
 * @author Sean Blanchard
 * @version 4/9/2019
 */

public class BingoChip
{
    private char letter;
    private int number;

    public BingoChip(int number)
    {
        setNumber(number);
        setLetter();
    }

    private void setNumber(int number)
    {
       // TODO Project 2.1
        this.number = number;
    }

    private void setLetter()
    {
        // TODO Project 2.1
        int i = (this.number - 1) / BingoCard.MAX_VALUES_PER_LETTER;
        //sets the letters to appropriate value (BINGO_KEYS)
        this.letter = BingoCard.BINGO_KEYS.charAt(i);
    }

    public int getNumber()
    {
        // TODO Project 2.1
        return this.number;
    }

    public char getLetter()
    {
        // TODO Project 2.1
        return this.letter;
    }

    public String toString()
    {
        // TODO Project 2.1
        return getLetter() + " " + getNumber();
    }
}