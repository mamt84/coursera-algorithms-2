package assignment7;

import assignment2.Vertice;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * Created by mmorcate on 4/23/2015.
 */
public class AllPairsShortestPathsNaive implements AllPairsShortestPaths
{
   //SingleSourceShortestPaths singleSourceShortestPaths = new BellmanFord();
    SingleSourceShortestPaths singleSourceShortestPaths = new BellmanFordVariant();

   boolean logging = false;

   @Override
   public Map<Integer, Map<Integer, Long>> computeAllShortestPaths( DirectedGraph graph )
   {
      Map<Integer, Map<Integer, Long>> solution = Maps.newHashMap();

      Map<Integer, Long> partialSolution = null;
      for ( Vertice vertex : graph.vertices.values() )
      {
         partialSolution =  singleSourceShortestPaths.computeShortestPaths( graph, vertex );
         if (partialSolution == null)
            return null;
         solution.put( vertex.id, partialSolution );

         if ( logging )
         {
            System.out.println(vertex);
         }
      }

      return solution;
   }

   @Override
   public void activateLogging()
   {
      logging = true;
   }
}
