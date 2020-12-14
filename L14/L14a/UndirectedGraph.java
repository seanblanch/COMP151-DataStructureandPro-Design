package Lab14;

import java.util.*;

/**
 * @author Sean Blanchard
 * @version 5/2/2019
 */


public class UndirectedGraph<T> extends DirectedGraph<T> implements GraphInterface<T>, ConnectedGraphInterface<T>, java.io.Serializable {

    private Map<T, VertexInterface<T>> vertices;
    private int edgeCount;

    public UndirectedGraph()
    {
        this.vertices = new TreeMap<>();
        this.edgeCount = 0;
    }

    public boolean addEdge(T begin, T end, double edgeWeight)
    {
        boolean result = false;
        //boolean result2 = false;

      result = super.addEdge(begin, end, edgeWeight);

      //result2 = super.addEdge(end, begin, edgeWeight);

      if (result)
      {
          result = super.addEdge(end, begin, edgeWeight);
      }

      return result;
    }

    public boolean addEdge(T begin, T end)
    {
        return addEdge(begin, end, 0);
    } // end addEdge

    public int getNumberOfEdges()
    {
        return super.getNumberOfEdges()/2;
    } // end getNumberOfEdges

    public Stack<T> getTopologicalOrder() throws UnsupportedOperationException
    {
        throw new UnsupportedOperationException("Topological sort illegal in an undirected graph.");
    } // end getTopologicalOrder

    public boolean isConnected(T origin)
    {
        Queue<T> connection = super.getBreadthFirstTraversal(origin);

        return connection.size() == super.getNumberOfEdges()/2;
    }

}
