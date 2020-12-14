package Lab08;

/**
 * A class that implements a cipher with repeating key algorithm.
 *
 * @author Sean Blanchard
 * @version 3/12/2019
 */


public class Cipher
{
    private Integer[] key;
    private final int ADD_FACTOR = 1;
    private final int SUBTRACT_FACTOR = -1;

    public Cipher(Integer... key)
    {

        // TODO Project 2B
        this.key = key;

    }


    public String encode(String message)
    {
        // TODO Project 2B
        //calls code method with ADD_FACTOR
        return code(message, ADD_FACTOR);

        //return "???";
    }

    public String decode(String encoded)
    {
        // TODO Project 2B
        return code(encoded, SUBTRACT_FACTOR);
        //calls code method with SUBTRACT_FACTOR

        //return "???";
    }

    private String code(String message, int addOrSubtractFactor)
    {
        StringBuilder encoded = new StringBuilder();

        // TODO Project 2B
        QueueInterface keyValues = this.getKeyQueue();
        Integer a;
        char b = 'i';
        for (int i = 0; i < message.length(); i++) // for encoding
        {
            a = (Integer) keyValues.dequeue();
            if (addOrSubtractFactor < 0) {
                b = (char) (message.charAt(i) - a);
            } else {
                b = (char) (message.charAt(i) + a);
            }


            // calls getKeyQueue to get the queue for encoding and decoding
            encoded.append(b);
            keyValues.enqueue(a);
        }
        return encoded.toString();
    }

    private QueueInterface<Integer> getKeyQueue()
    {

        // TODO Project 2B
        return new CircularArrayQueue<Integer>(this.key);
       // return null; // THIS IS A STUB
    }


    public static void main(String args[])
    {
        System.out.println("**************  TESTING THE CIPHER  **************\n");
        Cipher cipher = new Cipher(5, 12, -3, 8, -9, 4, 10, 2, 3, 5, 1);
        String encoded = cipher.encode("All programmers are playwrights and all computers are lousy actors.");
        System.out.println("--->The original message encoded is:");
        System.out.println(encoded);
        String decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        encoded = cipher.encode("There is no elevator to success, You have to take the stairs...");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        cipher = new Cipher(3, 1, 7, 4, 2, 5);
        encoded = cipher.encode("Trust but Verify");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        encoded = cipher.encode("race car");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);
    }
}


