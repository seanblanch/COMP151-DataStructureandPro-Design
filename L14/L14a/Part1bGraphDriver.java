package Lab14;

import java.util.Queue;
import java.util.Stack;

/**
 * A driver that demonstrates the class Lab14.UndirectedGraph
 * and a weighted graph.
 *
 * @author Frank M. Carrano
 * @version 4/30/2019
 * @modyfiedBy Sean Blanchard
 */
public class Part1bGraphDriver
{
    private UndirectedGraph<String> myGraph;
    private Stack<String> path;
    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static final String D = "D";
    private static final String E = "E";
    private static final String F = "F";
    private static final String G = "G";
    private static final String H = "H";
    private static final String I = "I";

    public Part1bGraphDriver()
    {
        this.myGraph = new UndirectedGraph<>();
        this.path = new Stack<>();
    }

    public static void main(String[] args)
    {
        Part1bGraphDriver tester = new Part1bGraphDriver();
        tester.setAndDisplayGraph();
        tester.testEdges();

        tester.testBFS(A);
        System.out.println("A B D E C G F H I  <-- Expected");

        tester.testDFS(A);
        System.out.println("A B E F C H G D I  <-- Expected");

        tester.testCheapestPath();

        tester.testTopologicalOrder();
        System.out.println("Done");

    }  // end main

    public void testTopologicalOrder()
    {
        try
        {
            myGraph.getTopologicalOrder();
        }
        catch (UnsupportedOperationException uoe)
        {
            System.out.println("CORRECT UnsupportedOperationException thrown: \"" + uoe.getMessage() + "\"");
        }
    }

    public void setAndDisplayGraph()
    {
        setVertices(); // graph cleared before setting vertices
        setEdges();

        System.out.println("Testing the undirected, weighted graph.");
        this.myGraph.display();
    } // end setAndDisplayGraph

    public void testEdges()
    {
        setVertices(); // graph cleared before setting vertices
        setEdges();

        System.out.println("\nNumber of vertices = " + this.myGraph.getNumberOfVertices() +
                " (should be 9)");
        System.out.println("Number of edges = " + this.myGraph.getNumberOfEdges() +
                " (should be 13)");

        // check some existing edges
        boolean ok = true;
        ok = checkEdge(A, B, ok);
        ok = checkEdge(A, D, ok);
        ok = checkEdge(A, E, ok);
        ok = checkEdge(B, E, ok);
        ok = checkEdge(C, B, ok);
        ok = checkEdge(D, G, ok);
        ok = checkEdge(E, F, ok);
        ok = checkEdge(E, H, ok);
        ok = checkEdge(F, C, ok);
        ok = checkEdge(F, H, ok);
        ok = checkEdge(G, H, ok);
        ok = checkEdge(H, I, ok);

        // check some non-existing edges
        ok = checkNoEdge(A, I, ok);
        ok = checkNoEdge(C, E, ok);
        ok = checkNoEdge(D, H, ok);

        if (ok)
            System.out.println("Edges are OK.");
    } // end testEdges

    private boolean checkEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (!this.myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkEdge

    private boolean checkNoEdge(String vertex1, String vertex2, boolean ok)
    {
        boolean check = ok;
        if (this.myGraph.hasEdge(vertex1, vertex2))
        {
            System.out.println("hasEdge error " + vertex1 + " " + vertex2);
            check = false;
        } // end if

        return check;
    } // end checkNoEdge

    public void testBFS(String v)
    {
        setVertices(); // graph cleared before setting vertices
        setEdges();

        System.out.println("\n\nBreadth-First Traversal beginning at vertex " + v + ": ");
        Queue<String> bfs = this.myGraph.getBreadthFirstTraversal(v);

        printQueue(bfs);
    } // end testBFS

    public void testDFS(String v)
    {
        setVertices(); // graph cleared before setting vertices
        setEdges();

        System.out.println("\n\nDepth-First Traversal beginning at vertex " + v + ": ");
        Queue<String> dfs = this.myGraph.getDepthFirstTraversal(v);

        printQueue(dfs);
    } // end testDFS

    public void testCheapestPath()
    {
        // WEIGHTED graph in Figure 28-18a
        setVertices(); // graph cleared before setting vertices
        setEdges();

        System.out.println("\n\nFinding the cheapest path in the graph in Figure 28-18a:\n");

        showPath(A, B);
        showPath(A, C);
        showPath(A, D);
        showPath(A, E);
        showPath(A, F);
        showPath(A, G);
        showPath(A, H);
        showPath(A, I);
    } // end testCheapestPath

    private void showPath(String vertex1, String vertex2)
    {
        System.out.println("The cheapest path from " + vertex1 + " to " + vertex2 + " is ");
        double cost = this.myGraph.getCheapestPath(vertex1, vertex2, this.path);
        printStack(this.path);
        System.out.println("and has a cost of " + cost + ".");
        System.out.println();
    } // end showPath

    public void setVertices()
    {
        this.myGraph.clear();

        this.myGraph.addVertex(A);
        this.myGraph.addVertex(B);
        this.myGraph.addVertex(C);
        this.myGraph.addVertex(D);
        this.myGraph.addVertex(E);
        this.myGraph.addVertex(F);
        this.myGraph.addVertex(G);
        this.myGraph.addVertex(H);
        this.myGraph.addVertex(I);
    } // end setVertices

    public void setEdges()
    {
        this.myGraph.addEdge(A, B, 2);
        this.myGraph.addEdge(A, D, 5);
        this.myGraph.addEdge(A, E, 4);

        this.myGraph.addEdge(B, E, 1);

        this.myGraph.addEdge(C, B, 3);

        this.myGraph.addEdge(D, G, 2);

        this.myGraph.addEdge(E, F, 3);
        this.myGraph.addEdge(E, H, 6);

        this.myGraph.addEdge(F, C, 4);
        this.myGraph.addEdge(F, H, 3);

        this.myGraph.addEdge(G, H, 1);

        this.myGraph.addEdge(H, I, 1);

        this.myGraph.addEdge(I, F, 1);

/* Since additions are made to the end of each edge list,
   these lists appear as follows:
		A: B -> D -> E
		B: E
		C: B
		D: G
		E: F -> H
		F: C -> H
		G: H
		H: I
		I: F
   We can predict the BFS and DFS traversals knowing this. */
    } // end setEdges

    public static void printStack(Stack<String> s)
    {
        while (!s.isEmpty())
            System.out.print(s.pop() + " ");
        System.out.println();
    } // end printStack

    public static void printQueue(Queue<String> q)
    {
        while (!q.isEmpty())
            System.out.print(q.poll() + " ");
        System.out.println(" <-- Calculated");
    } // end printQueue
}  // end DriverDW




