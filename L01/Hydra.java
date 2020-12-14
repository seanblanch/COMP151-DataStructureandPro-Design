//Sean Blanchard
//1/28/2019
//Hydra Client -


import java.util.*;





public class Hydra {
    private BagInterface<String> hydraHeads;

    /**
     * constructor creates this.hydraHeads object as ResizableArrayBag
     */
    public Hydra() {
        //Initialize
        this.hydraHeads = new ResizableArrayBag<>();
    }


    /**
     * prompts the user for the first word
     */
    public void addFirstElement() {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("What is the initial string?");
        String next = keyboard.nextLine();
        hydraHeads.add(next);
    }

        /**
         * Remove the head, and delete from the word two times
         * till empty
         */
        public void removeHead () {
            System.out.println("\n*** Removing balls from the hydraHeads ***\n");


            Random random = new Random(); //Generate Random Object

            while (!this.hydraHeads.isEmpty()) { //Repeat for as long as there is heads on the hydraHeads
                String remove = hydraHeads.remove(); //Remove random Head
                System.out.println("--> Removed \"" + remove + "\""); //Display its value
                int wordLength = remove.length(); // getting word length

                if (wordLength > 1) { //If word length is greater than 1
                    String tmp = remove.substring(1); // take away first letter
                    hydraHeads.add(tmp);
                    hydraHeads.add(tmp); // Adding new heads
                    System.out.println("After adding two, the Hydra has " + hydraHeads.getCurrentSize() + "heads");

                    displayBag();
                } else {
                    System.out.println("The removed head is of length 1, " + "no new heads will be added - " + hydraHeads.getCurrentSize() + " heads remaining");

                    displayBag();
                } // end else

                System.out.println("The Hydra is no more!!!!"); // hydraHeads is empty


            } // end removeHead


            System.out.println("\nThe hydraHeads is empty!!!");
        } // end removeHead

        /**
         * Displays the content of this.hydraHeads
         */
        private void displayBag () {
            Object[] headArray = this.hydraHeads.toArray();
            System.out.println(Arrays.toString(headArray));
            System.out.println();
        } // end displayBag

        public static void main (String args[]){
            Hydra hydra = new Hydra();

            hydra.addFirstElement();

            long startTime = Calendar.getInstance().getTime().getTime();



            hydra.removeHead();

            long stopTime = Calendar.getInstance().getTime().getTime();



            System.out.println("\nThe time required was " + (stopTime - startTime) + " milliseconds");
        } // end main

    }