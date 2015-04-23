package assignment7;

import assignment2.Edge;
import assignment2.Vertice;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by mmorcate on 4/23/2015.
 */
public class BellmanFordVariant implements SingleSourceShortestPaths {
    private Map<Integer, Collection<Edge>> forwardEdges;

    private Map<Integer, Collection<Edge>> backwardEdges;

    public Map<Integer, Collection<Edge>> createForwardEdges(DirectedGraph graph, List<Vertice> vertices) {

        if (forwardEdges != null) {
            return forwardEdges;
        }

        Map<Integer, Collection<Edge>> result = Maps.newHashMap();
        for (int i = 0; i < vertices.size(); i++) {
            if (!result.containsKey(vertices.get(i).id))
                result.put(vertices.get(i).id, new ArrayList<Edge>());
            for (int j = 0; j < vertices.size(); j++) {
                if (vertices.get(i).key < vertices.get(j).key && graph.existsEdge(vertices.get(i), vertices.get(j))) {
                    result.get(vertices.get(i).id).add(graph.getEdge(vertices.get(i), vertices.get(j)));
                }
            }
        }

        return forwardEdges = result;
    }

    public Map<Integer, Collection<Edge>> createBackwardEdges(DirectedGraph graph, List<Vertice> vertices) {

        if (backwardEdges != null) {
            return backwardEdges;
        }
        Map<Integer, Collection<Edge>> result = Maps.newHashMap();
        for (int i = 0; i < vertices.size(); i++) {
            if (!result.containsKey(vertices.get(i).id))
                result.put(vertices.get(i).id, new ArrayList<Edge>());
            for (int j = 0; j < vertices.size(); j++) {
                if (vertices.get(i).key > vertices.get(j).key && graph.existsEdge(vertices.get(i), vertices.get(j))) {
                    result.get(vertices.get(i).id).add(graph.getEdge(vertices.get(i), vertices.get(j)));
                }
            }
        }

        return backwardEdges = result;
    }

    @Override
    public Map<Integer, Long> computeShortestPaths(DirectedGraph graph,
                                                   Vertice startingVertex) {
        List<Vertice> vertices = Lists.newArrayList(graph.vertices.values());

        Vertice temp;
        for (int i = 0; i < vertices.size(); i++) {
            if (vertices.get(i).id == startingVertex.id) {
                vertices.get(i).key = 1;
                vertices.get(0).key = i + 1;
            } else {
                vertices.get(i).key = i + 1;
            }
        }

        Collections.sort(vertices);

        Map<Integer, Long> solution = Maps.newHashMap();
        for (Vertice vertex : vertices) {
            solution.put(vertex.id, (long) Integer.MAX_VALUE);
        }

        createForwardEdges(graph, vertices);
        createBackwardEdges(graph, vertices);

        /*for (int i = 0; i < vertices.size(); i++) {
            System.out.println(vertices.get(i));
            System.out.println(forwardEdges.get(vertices.get(i)));
            for (Edge data : forwardEdges.get(vertices.get(i))) {
                System.out.println(data);
            }
        }*/


        solution.put(startingVertex.id, 0L);
        int n = graph.vertices.size();
        long min;
        boolean oddIteration = true;
        for (int i = 1; i < n - 1; i++) {
            if (oddIteration) {

                for (int j = 0; j < vertices.size(); j++) {
                    min = Integer.MAX_VALUE;
                    for (Edge edge : forwardEdges.get(vertices.get(j).id)) {
                        if (solution.get(edge.source.id) + edge.weight < min)
                            min = solution.get(edge.source.id) + edge.weight;
                    }
                    solution.put(vertices.get(j).id, Math.min(solution.get(vertices.get(j).id), min));
                }
            }
            else{
                for (int j = vertices.size()-1; j >= 0 ; j--) {
                    min = Integer.MAX_VALUE;
                    for (Edge edge : backwardEdges.get(vertices.get(j).id)) {
                        if (solution.get(edge.source.id) + edge.weight < min)
                            min = solution.get(edge.source.id) + edge.weight;
                    }
                    solution.put(vertices.get(j).id, Math.min(solution.get(vertices.get(j).id), min));
                }
            }

            oddIteration = !oddIteration;
        }

        for ( Vertice vertex : vertices )
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
