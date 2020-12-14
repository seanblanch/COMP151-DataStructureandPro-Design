package Lab11;

/**
 * @author Sean Blanchard
 * @version 4/9/2019
 */

import java.util.*;

public class BingoCard
{
    private HashMap<Character, TreeSet<Integer>> card;
    public final static String BINGO_KEYS = "BINGO";
    public final static int MAX_VALUES_PER_LETTER = 15;
    public final static int NUMBERS_PER_LETTER = 5;

    public BingoCard()
    {
        // TODO Project 2.2
        //Creates a card for a player
        this.card = new HashMap<>();
        int offSet = 0;
        Random random = new Random();
        for(int i = 0; i < BINGO_KEYS.length(); i++)
        {
            TreeSet<Integer> key = new TreeSet<>();
            do
            {
                key.add(random.nextInt(MAX_VALUES_PER_LETTER - 1) + 1 + (offSet * MAX_VALUES_PER_LETTER));
            }while(key.size() < NUMBERS_PER_LETTER);
            this.card.put(BINGO_KEYS.charAt(i), key);
            offSet++;
        }
    }

    public boolean hasNumber(BingoChip chip)
    {
        // TODO Project 2.2
        boolean result = false;
        TreeSet<Integer> number = this.card.get(chip.getLetter());

        //if called number is on card
        if(number.contains(chip.getNumber()))
        {
            result = true;
            //adds a marker
            number.add(0);
            this.card.put(chip.getLetter(), number);
        }
        return result;
    }

    public String toString()
    {
        // TODO Project 2.2

        // utilize StringBuffer and String.format
        StringBuffer stringBuffer = new StringBuffer();
        // utilize forEach lambda construct to process a row

        //Create proper spacing for BINGO and printed "BINGO" values
        for (int i = 0; i < BINGO_KEYS.length(); i++)
        {
            TreeSet<Integer> bingoChar = this.card.get(BINGO_KEYS.charAt(i));
            stringBuffer.append(String.format("%c  ", BINGO_KEYS.charAt(i)));
            bingoChar.forEach(v -> stringBuffer.append(String.format("%2d ", v)));
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public boolean equals(Object o)
    {
        // TODO Project 2.2
        //returns true if this card is the same as the other
        boolean same = false;
        if(this == o)
        {
            same = true;
        }
        if(o instanceof  BingoCard)
        {
            same = this.card.equals(((BingoCard)o).card);
        }
        return same;
    }
}