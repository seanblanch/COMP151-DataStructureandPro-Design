package Lab14b;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * A class that checks connectivity in a directed unweighted graph
 * The graph is represented by adjacency matrix
 *
 * @author Sean Blanchard
 * @version 4/30/2019
 */
public class StrongWeakDisjoint
{
    private int[][] adjMatrixDirected;
    private int[][] adjMatrixUndirected;
    private int[][] reachabilityMatrix;
    private int numberOfNodes;

    public StrongWeakDisjoint(int numberOfNodes, int[][] graph)
    {
        this.numberOfNodes = numberOfNodes;
        this.adjMatrixDirected = new int[numberOfNodes][numberOfNodes];
        this.reachabilityMatrix = new int[this.numberOfNodes][this.numberOfNodes];

        for (int row = 0; row < this.numberOfNodes; row++)
            for (int col = 0; col < this.numberOfNodes; col++)
            {
                this.adjMatrixDirected[row][col] = graph[row][col];
                this.reachabilityMatrix[row][col] = this.adjMatrixDirected[row][col];
            }
        buildReachabilityMatrix();
    }

    private void buildReachabilityMatrix() {
        //TODO Lab14b #2.1


        for (int row = 0; row < numberOfNodes; row++)
        {
            for(int col = 0; col < numberOfNodes; col++)
            {
                reachabilityMatrix[row][col] = adjMatrixDirected[row][col];
            }
        }

        for(int b = 0; b < numberOfNodes; b++)
        {
            for(int c = 0; c < numberOfNodes; c++)
            {
                if(reachabilityMatrix[b][c] == 1)
                {
                    for(int a = 0; a < numberOfNodes; a++)
                    {
                        if(reachabilityMatrix[a][b] == 1 && a != c)
                        {
                           reachabilityMatrix[a][c] =1;
                        }
                    }
                }
            }
        }


    }
    public boolean isGraphStronglyConnected()
    {
        //TODO Lab14b #2.2

        //iterate through the reachability matrix
        //if row and columns != 1 then they are not connected

//        Integer counter = 0;
//        Queue<Integer> vertexQueue = new LinkedBlockingQueue<Integer>();
//        int originVertex = 1;
//        this.adjMatrixDirected[0][1] = -1;
//        counter++;    // enqueue vertex label
//        vertexQueue.offer(originVertex);

        for (int row = 0; row < numberOfNodes; row++) {
            for (int col = 0; col < numberOfNodes; col++) {
                reachabilityMatrix[row][col] = adjMatrixDirected[row][col];
            }
            if (reachabilityMatrix[0][0] != 1) {
                return isGraphWeaklyConnected();
            } else {
                return isGraphStronglyConnected();
            }
        }

        return true; // THIS IS A STUB
    }

    private boolean isUndirectedGraphConnected()
    {
        //TODO Lab14b #2.4
        // utilizes breath-first-traversal algorithm - the same implementation as in Lab14b #1

//        Integer counter = 0;
//        Queue<Integer> vertexQueue =
//                new LinkedBlockingQueue<Integer>();
//        int originVertex = 1;
//        this.adjMatrixUndirected[0][1] = -1;
//        counter++;    // enqueue vertex label
//        vertexQueue.offer(originVertex); // enqueue vertex
//
//        while (!vertexQueue.isEmpty())
//        {
//            Integer frontVertex = vertexQueue.poll();
//            for (int i = 1; i < this.numberOfNodes +1 ; i++) {
//                if(this.adjMatrixUndirected[frontVertex][i] !=0 && this.adjMatrixUndirected[0][i] != -1){
//                    this.adjMatrixUndirected[0][i] = -1;
//                    counter++;
//                    vertexQueue.offer(i);
//                }
//            }
//        }
        // utilize BFS algorithm
       return false;

    }

    public boolean isGraphWeaklyConnected()
    {
        this.adjMatrixUndirected = new int[this.numberOfNodes][this.numberOfNodes];
        // TODO Lab14b #2.3
        //  builds adjMatrixUndirected

        return isUndirectedGraphConnected();
    }

    public void displayGraph()
    {
        System.out.println("\n***** GRAPH TO CHECK *****");
        displayMatrix(this.adjMatrixDirected);
    }

    public void displayReachabilityMatrix()
    {
        System.out.println("\n***** REACHABILITY MATRIX *****");
        displayMatrix(this.reachabilityMatrix);
    }


    private void displayMatrix(int[][] matrix)
    {
        System.out.print("     ");
        for (int c = 0; c < this.numberOfNodes; c++)
        {
            System.out.printf("[%1$2d]", c);
        }
        System.out.println();
        for (int r = 0; r < this.numberOfNodes; r++)
        {
            System.out.printf("[%1$2d]", r);
            for (int c = 0; c < this.numberOfNodes; c++)
            {
                System.out.printf("%1$4d", matrix[r][c]);
            }
            System.out.println();
        }
    }

    public static void main(String[] args)
    {
        System.out.println("\n*** Checking graphs' connectivity ***");
        int[][] matrix = new int[5][5]; // setting the matrix to the matrix shown in the project description
        matrix[0][1] = 1;
        matrix[0][3] = 1;
        matrix[1][2] = 1;
        matrix[1][3] = 1;
        matrix[3][4] = 1;
        matrix[4][0] = 1;
        matrix[4][3] = 1;
        StrongWeakDisjoint graph = new StrongWeakDisjoint(5, matrix);
        graph.displayGraph();
        graph.displayReachabilityMatrix();

        if (graph.isGraphStronglyConnected())
            System.out.println("-->The graph is strongly connected.\n\n");
        else if (graph.isGraphWeaklyConnected())
            System.out.println("-->The graph is weakly connected.\n\n");
        else
            System.out.println("-->The graph is disjoint.\n\n");

        matrix = new int[5][5];
        matrix[0][1] = 1;
        matrix[0][3] = 1;
        matrix[1][2] = 1;
        matrix[1][3] = 1;
        matrix[2][1] = 1;
        matrix[3][4] = 1;
        matrix[4][0] = 1;
        matrix[4][3] = 1;

        graph = new StrongWeakDisjoint(5, matrix);
        graph.displayGraph();
        graph.displayReachabilityMatrix();
        if (graph.isGraphStronglyConnected())
            System.out.println("-->The graph is strongly connected.\n\n");
        else if (graph.isGraphWeaklyConnected())
            System.out.println("-->The graph is weakly connected.\n\n");
        else
            System.out.println("-->The graph is disjoint.\n\n");


        matrix = new int[5][5];
        matrix[0][1] = 1;
        matrix[0][3] = 1;
        matrix[1][3] = 1;
        matrix[3][4] = 1;
        matrix[4][0] = 1;
        matrix[4][3] = 1;

        graph = new StrongWeakDisjoint(5, matrix);
        graph.displayGraph();
        graph.displayReachabilityMatrix();
        if (graph.isGraphStronglyConnected())
            System.out.println("-->The graph is strongly connected.\n\n");
        else if (graph.isGraphWeaklyConnected())
            System.out.println("-->The graph is weakly connected.\n\n");
        else
            System.out.println("-->The graph is disjoint.\n\n");
    } // end main
}
