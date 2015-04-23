package assignment7;

import assignment2.Edge;
import assignment2.Vertice;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by mmorcate on 4/23/2015.
 */
public class BellmanFord implements SingleSourceShortestPaths
{

   @Override
   public Map<Integer, Long> computeShortestPaths( DirectedGraph graph,
         Vertice startingVertex )
   {
      Map<Integer, Long> solution = Maps.newHashMap();
      for ( Vertice vertex : graph.vertices.values() )
      {
         solution.put( vertex.id, (long)Integer.MAX_VALUE );
      }

      solution.put( startingVertex.id, 0L );
      int n = graph.vertices.size();
      long min;
      for ( int i = 1; i < n-1; i++ )
      {
         for ( Vertice vertex : graph.vertices.values() )
         {
            min = Integer.MAX_VALUE;
            for ( Edge edge : graph.getIncommingEdges( vertex ) )
            {
               if (solution.get( edge.source.id ) + edge.weight < min)
                  min = solution.get( edge.source.id ) + edge.weight;
            }
            solution.put( vertex.id, Math.min( solution.get( vertex.id ), min ) );
         }
      }

      for ( Vertice vertex : graph.vertices.values() )
      {
         min = Integer.MAX_VALUE;
         for ( Edge edge : graph.getIncommingEdges( vertex ) )
         {
            if (solution.get( edge.source.id ) + edge.weight < min)
               min = solution.get( edge.source.id ) + edge.weight;
         }

         if (solution.get( vertex.id ) != Math.min( solution.get( vertex.id ), min ))
            return null;
      }

      return solution;
   }
}
