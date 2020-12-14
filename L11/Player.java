package Lab11;
/**
 * @author Sean Blanchard
 * @version 4/9/2019
 */
import java.util.TreeSet;

public class Player
{
    private TreeSet<Character> bingoChars;
    private BingoCard bingoCard;

    public Player()
    {
        // TODO 2.3
        //this.bingoChars = new TreeSet<>();
        //Creates bingoCard
        this.bingoCard = new BingoCard();
        //prints created card
        printCard();
        //Creates bingoChars
        this.bingoChars = new TreeSet<>();
    }

    public boolean isWinner()
    {
    	// TODO 2.3
        //When the size of bingoChar is 5 (BINGO_KEYS) we have a winner
        //Checks if the player had at least one number for each letter
        return this.bingoChars.size() == BingoCard.BINGO_KEYS.length();
    }

    public void checkCard(BingoChip chip)
    {
        // TODO 2.3
        //Checks if the called number is on the card
        if(this.bingoCard.hasNumber(chip))
        {
            //adds the letter to bingoChars
            this.bingoChars.add(chip.getLetter());
        }
    }

    public void printCard()
    {
        // TODO 2.3
        //Print card by utilizing bingoCard toString()
        System.out.printf(this.bingoCard.toString());
    }

}
