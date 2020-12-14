package Lab14b;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * A class that implements a solution
 * utilizing greedy algorithm for graph coloring
 * The undirected graph is represented by adjacency list
 *
 * @author Sean Blanchard
 * @version 4/30/2019
 */

class HowManyQuestions
{
    private int numberOfVertices;
    private LinkedList<Integer> adjacencyList[]; //graph represented as Adjacency List

    /**
     * Takes the input matrix and creates the graph represented as adjancency list
     *
     * @param graph two dimensional array of booleans, true indicates
     *              that the corresponding vertexes are neighbors
     */
    public HowManyQuestions(boolean[][] graph)
    {
        //TODO Lab14b #3.1
     this.numberOfVertices = graph.length;
     this.adjacencyList = new LinkedList[this.numberOfVertices];

     for(int i = 0; i < graph.length; i++)
     {
         LinkedList<Integer> linkList = new LinkedList<>();
         for(int j = 0; j < graph.length; j++)
         {
             if(graph[i][j])
                 linkList.add(j);
         }
         this.adjacencyList[i] = linkList;
     }
    }

    /**
     * Traverses the adjacencyList and displays all neighbors of each vertex
     */
    public void displayNeighbors()
    {
        //TODO Lab14b #3.2
        System.out.println("The graph has " + numberOfVertices + " vertexes with the following neighbors: ") ;
        for(int i = 0; i < this.adjacencyList.length; i++)
        {
            //System.out.println("The graph has " + numberOfVertices + " vertexes with the following neighbors: ") ;
            System.out.print("Vertx " + i + " has neighbors: ");
            ListIterator<Integer> listItr = this.adjacencyList[i].listIterator();

            while(listItr.hasNext())
            {
                System.out.print(listItr.next() + " ");
            }
            System.out.println();
        }
        System.out.println("=============");
    }

    /**
     * Assigns questions to all vertices and
     * prints the assignment of questions
     * If the solution is not possible the method throws an Exception
     * with appropriate message. The possible exception is handled by this method as well
     */
    public boolean greedyQuestionChooser(int numberOfQuestions)
    {
        boolean solved = false;

        int assignedQuesions[] = new int[numberOfVertices];
        // Initializes all vertices as unassigned -1
        Arrays.fill(assignedQuesions, -1);

        // A temporary array to store the available questions.
        // Initially, all questions are not taken
        boolean taken[] = new boolean[numberOfQuestions];

        // Assign the first question to first vertex - first question is 0
        assignedQuesions[0] = 0;
        try
        {
            //TODO Lab14b #3.3
            for(int i = 1; i < this.numberOfVertices; i++)
            {
                ListIterator<Integer> listItr = this.adjacencyList[i].listIterator();
                while(listItr.hasNext())
                {
                    int nextItem = listItr.next();

                    if(assignedQuesions[nextItem] != -1)
                    {
                        taken[assignedQuesions[nextItem]] = true;
                    }
                }
                int fill = 0;
                        while(taken[fill] && fill < numberOfQuestions)
                        {
                            fill++;
                        }
                        assignedQuesions[i] = fill;
                        Arrays.fill(taken, false);
            }
            System.out.println("--> The solution exists with " + numberOfQuestions + " questions: ");
            for(int i = 0; i < this.numberOfVertices; i++)
            {
                System.out.println("Student: " + i + " ---> Question " + assignedQuesions[i]);
            }
            solved = true;



        } catch (Exception e)
        {
            //System.out.println(e.getMessage());
            System.out.println("--> The solution does not exist - not enough choices");
        }
        return solved;
    }

    // Driver method
    public static void main(String args[])
    {
        HashMap<String, HowManyQuestions> graphsToCheck = new HashMap<>();

        graphsToCheck.put("g1",
                new HowManyQuestions(new boolean[][]{
                        {false, true, true, false, false},
                        {true, false, true, true, false},
                        {true, true, false, true, false},
                        {false, true, true, false, true},
                        {false, false, false, true, false}}));
        graphsToCheck.put("g2",
                new HowManyQuestions(new boolean[][]{
                        {false, true, true, true, false},
                        {true, false, true, true, true},
                        {true, true, false, false, true},
                        {true, true, false, false, true},
                        {false, true, true, true, false}}));
        graphsToCheck.put("g3",
                new HowManyQuestions(new boolean[][]{
                        {false, true, true, true, false},
                        {true, false, true, true, true},
                        {true, true, false, true, false},
                        {true, true, true, false, true},
                        {false, true, false, true, false}}));

        // circular
        boolean[][] g4 = new boolean[24][24];
        for (int i = 0; i < 24; i++)
        {
            g4[i][(i + 1) % 24] = true;
            g4[(i + 1) % 24][i] = true;
        }
        graphsToCheck.put("g4", new HowManyQuestions(g4));

        // SIE 1232
        graphsToCheck.put("g5",
                new HowManyQuestions(new boolean[][]{
                        {false, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
                        {true, false, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, true, false, true, false, false, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, true, false, true, false, false, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, true, false, true, true, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false},
                        {false, false, false, true, true, true, true, false, true, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
                        {false, false, true, true, true, false, false, true, false, true, false, false, false, false, false, true, true, true, false, false, false, false, false, false},
                        {false, true, true, true, false, false, false, false, true, false, true, false, false, true, true, true, false, false, false, false, false, false, false, false},
                        {true, true, true, false, false, false, false, false, false, true, false, true, true, true, true, false, false, false, false, false, false, false, false, false},
                        {true, true, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, false, false, false, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false, false, false, false, false, true, true},
                        {false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, false, false, false, false, false, true, true, true},
                        {false, false, false, false, false, false, false, false, true, true, true, false, false, true, false, true, false, false, false, false, true, true, true, false},
                        {false, false, false, false, false, false, false, true, true, true, false, false, false, false, true, false, true, false, false, true, true, true, false, false},
                        {false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, true, false, true, true, true, true, false, false, false},
                        {false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false, true, true, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, true, false, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, true, false, true, false, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, true, false, true, false, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, true, false, true, false},
                        {false, false, false, false, false, false, false, false, false, false, false, false, true, true, true, false, false, false, false, false, false, true, false, true},
                        {false, false, false, false, false, false, false, false, false, false, false, false, true, true, false, false, false, false, false, false, false, false, true, false}}));


        final int NUMBER_OF_GRAPHS = 5;
        for (int i = 1; i <= NUMBER_OF_GRAPHS; i++)
        {
            System.out.println("Created graph " + i + ":");
            String key = "g" + i;
            graphsToCheck.get(key).displayNeighbors();
            System.out.println();
        }

        int numberOfQuestions = 1;
        boolean[] solutionFound = new boolean[NUMBER_OF_GRAPHS];
        boolean done = false;
        while (!done)
        {
            numberOfQuestions++;
            System.out.println("****** Checking if " + numberOfQuestions + " questions are enough ******");
            done = true;
            for (int i = 1; i <= NUMBER_OF_GRAPHS; i++)
            {
                if (!solutionFound[i - 1])
                {
                    System.out.println("   *** Checking graph " + i + " ***");
                    String key = "g" + i;
                    if (graphsToCheck.get(key).greedyQuestionChooser(numberOfQuestions))
                        solutionFound[i - 1] = true;
                    else
                        done = false;
                    System.out.println();
                }
            }
        }

        System.out.println("***** DONE - all graphs were assigned solutions *****");
    }
}