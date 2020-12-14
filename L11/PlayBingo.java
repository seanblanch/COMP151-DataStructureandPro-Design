package Lab11;

/**
 * @author Sean Blanchard
 * @version 4/9/2019
 */

import java.util.*;

public class PlayBingo
{
    public static void main(String[] args)
    {
        System.out.println("---> Setting up bingo game.");

        Scanner scan = new Scanner(System.in);
        int numOfPlayers;
        do
        {
            System.out.println("Enter number of players.");
            //gets user input with the number of players
            numOfPlayers = scan.nextInt();
            //if negative number is entered 1 is assumed
            if(numOfPlayers < 0)
            {
                numOfPlayers = 1;
            }
        } while (numOfPlayers < 1);
        //Create the game object
        BingoGame game = new BingoGame(numOfPlayers);

        System.out.println("---> Starting the game with " + numOfPlayers + " players:");
        System.out.println("     *********************************\n");
        //starts the game
        game.play();
    }
}