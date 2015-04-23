package assignment7;

import java.util.Map;

/**
 * Created by mmorcate on 4/23/2015.
 */
public interface AllPairsShortestPaths
{
   public Map<Integer, Map<Integer, Long>> computeAllShortestPaths(DirectedGraph graph);

   public void activateLogging();
}
