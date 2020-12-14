

import java.text.DecimalFormat;
import java.util.*;

/**
 * A class that implements math operations utilizing a stack.
 *
 * @author Sean Blanchard
 * @version 02/12/2019
 */
public class FunWithStack
{
    public void decimalToBinary()
    {
        System.out.println("DECIMAL TO BINARY CONVERTER");
        // TODO PROJECT #1
        Scanner keyboard = new Scanner(System.in);
        Stack<Integer> stack = new Stack<>();
        try
        {
            do
            {
                System.out.println("\nPlease enter a positive integer, or type \"stop\"");
                int decimalNumber = keyboard.nextInt();

                System.out.print(decimalNumber + " in binary is --> ");

                // YOUR CODE GOES HERE


//                if (decimalNumber <= 0 )
//                {
//                    System.out.println("Please enter a positive number.");
//                } else if (decimalNumber == 1)
//                {
//                    System.out.println(decimalNumber);
//                } else {
//                    int d = decimalNumber % 2;
//                    stack.push(d);
//                    decimalNumber /= 2;
//                }
//
//                System.out.println(stack.pop());

                if(decimalNumber <= 0) {
                    System.out.println("Please enter a positive number");
                }else
                    {


                    while (decimalNumber != 0) {
                        int d = decimalNumber % 2;
                        stack.push(d);
                        decimalNumber /= 2;
                    }
                    while (!(stack.isEmpty())) {
                        System.out.print(stack.pop());
                    }
                }



                System.out.println();

                //TODO end
            } while (true);
        }
        catch (InputMismatchException ime)
        {
            System.out.println("Done with conversion.\n");
        }
    }

    public void ancientMultiplier()
    {
        // TODO PROJECT #1
        // http://en.wikipedia.org/wiki/Ancient_Egyptian_multiplication
        Stack<Integer> op1 = new Stack<>();
        Stack<Integer> op2 = new Stack<>();
        Scanner keyboard = new Scanner(System.in);

        int largestNumber = 0;
        int smallestNumber = 0;

        System.out.println("Please enter operand1, or type stop ");
        int firstNumber = keyboard.nextInt();

        System.out.println(firstNumber + " Is the first number selected.");

        System.out.println("Please enter operand2 ");
        int secondNumber = keyboard.nextInt();
        System.out.println(secondNumber + " Is the second number selected.");

        if (firstNumber > secondNumber)
        {
            int temp = secondNumber;
            secondNumber = firstNumber;
            firstNumber = temp;
        }

        System.out.println("The smaller operand is: " + firstNumber + "; and the larger operand is: " + secondNumber);

//        int answer = 0;
//        int s1 = 1;
//
//        while (secondNumber != 0)
//        {
//            if (secondNumber%2 != 0)
//            {
//                answer = answer + firstNumber;
//                firstNumber = firstNumber * 2;
//                secondNumber = secondNumber / 2;
//                System.out.println(s1 + "--->" + firstNumber);
//                s1 = s1 + 2;
//            }else
//            {
//                firstNumber = firstNumber * 2;
//                secondNumber = secondNumber / 2;
//                System.out.println(s1 + "--->" + secondNumber);
//            }
//        }

        int loop = 1;
        int copyOfSecondNumber;
        copyOfSecondNumber = secondNumber;

        System.out.println("--> Creating the mapping table: ");

        while(loop <= firstNumber)
        {
           op1.push(loop);
           op2.push(secondNumber);
            System.out.printf( "%,d --> %,d%n", loop, secondNumber);
           loop += loop;
           secondNumber += secondNumber;

//            System.out.println(loop + " --> " + secondNumber);
        }


        loop = 0;

        System.out.println("--> Calculating the result ");
        System.out.print(firstNumber + " * " + copyOfSecondNumber + " is: ");


        int result = 0;

        while (!op1.isEmpty() && loop <= firstNumber)

        {
            int temp = op1.pop();
            if(loop + temp <= firstNumber)
            {
                loop = loop + temp;
                int temp2 = op2.pop();
                result += temp2;

                System.out.printf("%,d", temp2);
            } else
            {
                op1.pop();
                op2.pop();
            }



        }
//









    }

    public ArrayList<Integer> noAdjacentDuplicates(int... input)
    {
        // TODO PROJECT #1
        ArrayList<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        System.out.println("input = " + Arrays.toString(input));

       int i = 0;
       int j = 0;

       while(i<result.size())
       {
           if(i<result.size())
           {
               stack.push(result.get(i));
               i++;
           }else
           {
               if(!stack.contains(result.get(j)))
               {
                   stack.push(result.get(i));
                   j++;
               }
           }
       }


        return result;
    }


    public static void main(String[] args)
    {
        FunWithStack funWithStack = new FunWithStack();
        System.out.println("*** DECIMAL TO BINARY CONVERTER ***");
        funWithStack.decimalToBinary();
        System.out.println("*** ANCIENT MULTIPLIER ***");
        funWithStack.ancientMultiplier();

        System.out.println("*** ELIMINATING ADJACENT DUPLICATES ***");

        System.out.println("--> testcase #1");
        ArrayList<Integer> result = funWithStack.noAdjacentDuplicates(1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        ArrayList<Integer> expected = new ArrayList<>();
        expected.add(1);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #2");
        result = funWithStack.noAdjacentDuplicates(1, 9, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        expected.add(1);
        expected.add(9);
        expected.add(5);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #3");
        result = funWithStack.noAdjacentDuplicates(1, 1, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        expected.add(5);
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("\n---> testcase #4");
        result = funWithStack.noAdjacentDuplicates(1, 1, 1, 5, 6, 8, 8, 8, 0, 1, 1, 0, 6, 5);
        expected.clear();
        if (result.equals(expected))
            System.out.println("result = " + result + " CORRECT");
        else
        {
            System.out.println("INCORRECT, expected: " + expected);
            System.out.println("got: " + result);
        }

        System.out.println("Done!");
    }
}