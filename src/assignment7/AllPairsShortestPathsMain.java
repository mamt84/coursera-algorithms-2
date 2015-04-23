package assignment7;

import assignment2.Edge;
import assignment2.Vertice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;

/**
 * Created by mmorcate on 4/23/2015.
 */
public class AllPairsShortestPathsMain
{
   public static void main(String[] args){
      try
      {
         BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
         String line = bufferRead.readLine();

         String[] splittedLine = line.split(" ");
         int nodesCount = Integer.parseInt(splittedLine[0]);
         int edgesCount = Integer.parseInt(splittedLine[1]);

         Edge edge;
         DirectedGraph graph = new DirectedGraph();
         Vertice verticeLeft;
         Vertice verticeRight;
         Integer verticeLeftId;
         Integer verticeRightId;
         //Filling graph
         for (int i = 0; i < edgesCount; i++)
         {
            line = bufferRead.readLine();
            splittedLine = line.split( " " );

            verticeLeftId = Integer.parseInt( splittedLine[0] );
            verticeRightId = Integer.parseInt( splittedLine[1] );
            verticeLeft = graph.existsVertice( verticeLeftId ) ?
                  graph.getVertice( verticeLeftId ) :
                  new Vertice( verticeLeftId );
            verticeRight = graph.existsVertice( verticeRightId ) ?
                  graph.getVertice( verticeRightId ) :
                  new Vertice( verticeRightId );

            if ( !graph.existsEdge( verticeLeftId, verticeRightId ) )
            {
               edge = new Edge( verticeLeft, verticeRight, Long.parseLong( splittedLine[2] ) );
               graph.addEdge( edge );
            }
         }

         long min = Long.MAX_VALUE;
         AllPairsShortestPaths allPairsShortestPaths = new AllPairsShortestPathsNaive();
         allPairsShortestPaths.activateLogging();
         Map<Integer, Map<Integer, Long>> solution = allPairsShortestPaths.computeAllShortestPaths( graph );
         if (solution != null){
            for ( Integer vertexId : solution.keySet() )
            {
               for ( Long pathCost : solution.get( vertexId ).values() )
               {
                  if (pathCost < min)
                     min = pathCost;
               }
            }
            System.out.println(min);
         }
         else{
            System.out.println("NULL");
         }



      }
      catch ( IOException e )
      {
         e.printStackTrace();
      }
   }
}
