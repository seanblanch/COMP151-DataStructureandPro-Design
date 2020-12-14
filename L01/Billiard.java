import java.util.*;

/**
 * This class determines how long it would take to remove billiard balls from a table,
 * where ball n is replaced by n balls with randomly generated numbers between 1 and n-1.
 *
 * @version 1/22/2019
 * @updatedBy Sean Blanchard
 */
public class Billiard {
    private BagInterface<Integer> table;

    /**
     * constructor creates this.table object as ResizableArrayBag
     */
    public Billiard() {
        this.table = new ResizableArrayBag<>();
    }

    /**
     * prompts the user for the first numbered ball and adds it to this.table
     */
    public void addFirstElement() {
        final int SMALLEST_BALL = 1;
        final int LARGEST_BALL = 6;
        Scanner keyboard = new Scanner(System.in);
        int start;
        do {
            System.out.println("What is the first numbered ball to start with? (must be between " + SMALLEST_BALL
                    + " and " + LARGEST_BALL + " inclusive)");
            start = keyboard.nextInt();
        } while (!(start >= SMALLEST_BALL && start <= LARGEST_BALL));

        System.out.println("The first ball is: \"" + start + "\"");
        this.table.add(start);
    }

    /**
     * Removes balls from this.table until all are gone.
     */
    public void removeBallsFromTable() {
        System.out.println("\n*** Removing balls from the table ***\n");
        //TODO Project1
        /*Random random = new Random();
        int ball = random.nextInt(6-1)+1;
        for(int i=1; table.isEmpty(); i++){
            table.remove(ball);
            System.out.println("--> Removed: " + ball);*/

        Random random = new Random(); //Generate Random Object

          while(!this.table.isEmpty()) { //Repeat for as long as there is balls on the table
              int removedBall = this.table.remove(); //Remove random ball
              System.out.println("--> Removed \"" + removedBall + "\""); //Display its value

              if(removedBall != 1) { //If ball number is not 1
                  for (int i = 0; i < removedBall; i++) { //Create a loop
                      this.table.add(random.nextInt(removedBall - 1) + 1); //put the ball number of randomly generated balls with in range on the table
                  }

                  System.out.println("After adding " + removedBall + " balls, we have " + this.table.getCurrentSize() + " balls on the table:");
                  displayBag(); // Print what is in your bag
              } else { //If ball number is equal to 1
                  System.out.println("Removed ball has number \"1\", no new balls will be added - " + this.table.getCurrentSize() + " balls remaining."); //
                  displayBag(); // Print what is in your bag
              }





        }




        System.out.println("\nThe table is empty!!!");
    } // end removeBallsFromTable

    /**
     * Displays the content of this.table
     */
    private void displayBag() {
        Object[] bagArray = this.table.toArray();
        System.out.println(Arrays.toString(bagArray));
        System.out.println();
    } // end displayBag

    public static void main(String args[]) {
        Billiard billiard = new Billiard();
        billiard.addFirstElement();

        long startTime = Calendar.getInstance().getTime().getTime(); // get current time in miliseconds

        billiard.removeBallsFromTable();

        long stopTime = Calendar.getInstance().getTime().getTime();

        System.out.println("\nThe time required was " + (stopTime - startTime) + " milliseconds");
    } // end main
} // end Billiard