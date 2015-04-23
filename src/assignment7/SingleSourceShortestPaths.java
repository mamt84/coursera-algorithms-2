package assignment7;

import assignment2.Vertice;

import java.util.Map;

/**
 * Created by mmorcate on 4/23/2015.
 */
public interface SingleSourceShortestPaths
{
   /**
    *
    * @param graph
    * @param startingVertex
    * @return A map with all the shortest paths from startingVertex
    * , or null if the graph contains any negative cycle.
    */
   public Map<Integer, Long> computeShortestPaths(DirectedGraph graph, Vertice startingVertex);
}
