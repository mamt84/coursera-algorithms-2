package assignment7;

import assignment2.Edge;
import assignment2.Vertice;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by mmorcate on 4/23/2015.
 */
public class BellmanFordVariant implements SingleSourceShortestPaths
{
   public Map<Integer, Collection<Edge>> createForwardEdges(DirectedGraph graph, List<Vertice> vertices){

      Map<Integer, Collection<Edge>> result = Maps.newHashMap();
      for ( int i = 0; i < vertices.size(); i++ )
      {
         for ( int j = 0; j < vertices.size(); j++ )
         {
            if ( vertices.get( i ).key < vertices.get( j ).key && graph.existsEdge( vertices.get( i ), vertices.get( j ) )){
               if (!result.containsKey( vertices.get( i ) ))
                  result.put( vertices.get( i ).id, new ArrayList<Edge>(  ) );
               result.get( vertices.get( i ) ).add( graph.getEdge( vertices.get( i ), vertices.get( j ) ) );
            }
         }
      }

      return result;
   }

   public Map<Integer, Collection<Edge>> createBackwardEdges(DirectedGraph graph, List<Vertice> vertices){

      Map<Integer, Collection<Edge>> result = Maps.newHashMap();
      for ( int i = 0; i < vertices.size(); i++ )
      {
         for ( int j = 0; j < vertices.size(); j++ )
         {
            if (vertices.get( i ).key > vertices.get( j ).key && graph.existsEdge( vertices.get( i ), vertices.get( j ) )){
               if (!result.containsKey( vertices.get( i ) ))
                  result.put( vertices.get( i ).id, new ArrayList<Edge>(  ) );
               result.get( vertices.get( i ) ).add( graph.getEdge( vertices.get( i ), vertices.get( j ) ) );
            }
         }
      }

      return result;
   }

   @Override
   public Map<Integer, Long> computeShortestPaths( DirectedGraph graph,
         Vertice startingVertex )
   {
      List<Vertice> vertices = Lists.newArrayList(graph.vertices.values());

      Vertice temp;
      for ( int i = 0; i < vertices.size(); i++ )
      {
         if (vertices.get( i ).id == startingVertex.id){
            vertices.get( i ).key = 1;
            vertices.get( 0 ).key = i+1;
         }
         else{
            vertices.get( i ).key = i+1;
         }
      }

      Collections.sort( vertices );

      Map<Integer, Long> solution = Maps.newHashMap();
      for ( Vertice vertex : graph.vertices.values() )
      {
         solution.put( vertex.id, (long)Integer.MAX_VALUE );
      }

      solution.put( startingVertex.id, 0L );
      int n = graph.vertices.size();
      long min;
      boolean oddIteration = true;
      for ( int i = 1; i < n-1; i++ )
      {
         if(oddIteration){
            oddIteration = !oddIteration;

            for ( int j = 0; j < vertices.size(); j++ )
            {
               for ( int k = j+1; k < vertices.size(); k++ )
               {

               }
            }
            for ( Vertice vertex : vertices )
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


      }

      return null;
   }
}
