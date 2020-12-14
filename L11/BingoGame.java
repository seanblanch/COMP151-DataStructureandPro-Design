package Lab11;

/**
 * @author Sean Blanchard
 * @version 4/9/2019
 */

import java.util.*;

public class BingoGame
{
    public final static int NUMBER_OF_CHIPS = 75;
    private int numberOfPlayers;
    private ArrayList<BingoChip> bingoDrum;
    private Player[] players;

    public BingoGame(int numOfPlayers)
    {
        // TODO Project 2.4
        //Sets the number of players for the game
        setNumOfPlayers(numOfPlayers);
        this.players = new Player[this.numberOfPlayers];
        //Creates bingoDrum
        this.bingoDrum = new ArrayList<>(NUMBER_OF_CHIPS);
        createBingoDrum();
        //Creates players
        createPlayers();
    }

    private void createBingoDrum()
    {
        // TODO Project 2.4
        // ArrayList that contains BingoChip objects
        for(int i = 0; i <= NUMBER_OF_CHIPS; i++)
        {
            this.bingoDrum.add(new BingoChip(i));
        }
    }

    private void createPlayers()
    {
        // TODO Project 2.4
        //array of player objects
        for(int i = 0; i < this.numberOfPlayers; i++)
        {
            System.out.println("--> Creating bingo card for Player " + (i + 1));
            this.players[i] = new Player();
        }
    }

    private void setNumOfPlayers(int numOfPlayers)
    {
        // TODO Project 2.4
        //number of players
        this.numberOfPlayers = numOfPlayers;
    }

    public int getNumberOfPulledChips()
    {
        // TODO Project 2.4
        //returns the number of chips pulled
        return NUMBER_OF_CHIPS - this.bingoDrum.size();
    }

    public BingoChip pullChip()
    {
        // TODO Project 2.4
        //pullChip will randomly be created
        Random random = new Random();
        //pullChip pulls randomly one chip from the bingoDrum
        int randInt = random.nextInt(this.bingoDrum.size());
        //once random chip is pull it is deleted from bingoDrum
        return this.bingoDrum.remove(randInt);
    }

    public void play()
    {
        // TODO Project 2.4
        boolean Winner = false;
        do
        {
            //pulls a chip
            BingoChip bingoChip = pullChip();
            //calls the chip letter and number
            System.out.println(" ---> Calling: " + bingoChip.toString());
            //for each player checks if player card has "called" number
            for (int i = 0; i < this.numberOfPlayers; i++)
            {
                System.out.println("Player " + (i + 1) + "'s card: ");
                this.players[i].checkCard(bingoChip);
                //prints each player's card
                this.players[i].printCard();
            }
            //loop through and check for multiple winners
            for (int j = 0; j < this.numberOfPlayers; j++)
            {
                //check eah players card for winning combination and announces
                //the BINGO if appropriate
                if (this.players[j].isWinner())
                {
                    System.out.println("!!! Player " + (j + 1) + " says BINGO !!!");
                    Winner = true;
                }
            }
        } while (this.bingoDrum.size() > 0 && !Winner);
        //once game is over it prints the number of chips that were called
        System.out.println(getNumberOfPulledChips() + " chips were called.");

    }
}