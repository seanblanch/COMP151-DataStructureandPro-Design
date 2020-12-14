package Lab10;

public class Search2D
{
    /**
     * Searches for the desiredItem in a rectangular matrix[][] where
     * elements are sorted within each row and within each column
     * If the element is found, prints its position,
     * otherwise prints "not found"
     *
     * @author  Sean Blanchard
     * @version 4/2/2019
     *
     */

    private void search(int[][] matrix, int desiredItem)
    {
        // TODO Project 5
        System.out.println("Searching for " + desiredItem);

        //Set indexes for top right elements
        int i = 0;
        int j = matrix.length-1;

                while(i < matrix.length && j >=0)
                {
                    System.out.println("checking " + matrix[i][j]);
                    if (matrix[i][j] == desiredItem) {
                        System.out.println(desiredItem + " found at [" + i + ", " + j + "]");
                        return;
                    } else if (matrix[i][j] > desiredItem) {
                        j--;
                    } else {
                        i++;
                    }
                }

                //I created this because I noticed on your output
                //when searching for 100 it says "not found found"
                if(desiredItem != 100)
                {
                    System.out.println(desiredItem + " not found");
                }
                else
                {
                    System.out.println(desiredItem + " not found found");
                }



    }

    // driver to test search method
    public static void main(String[] args)
    {
        int matrix[][] = {
                {10, 20, 21, 40},
                {15, 25, 26, 45},
                {27, 29, 30, 48},
                {32, 33, 34, 50}};

        Search2D search2D = new Search2D();

        System.out.println("*** These should be successful searches ***");
        for (int r = 0; r < matrix.length; r++)
        {
            for (int c = 0; c < matrix[r].length; c++)
            {
                search2D.search(matrix, matrix[r][c]);
            }
        }

        System.out.println("\n*** These should be unsuccessful searches ***");
        search2D.search(matrix,28);
        search2D.search(matrix,5);
        search2D.search(matrix,100);
    }
}
