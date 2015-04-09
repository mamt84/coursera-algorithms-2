package assignment2;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by Martin on 4/4/2015.
 */
public class Graph {
    public Map<Integer, Vertice> vertices = new HashMap<Integer, Vertice>();
    public Map<Integer, Map<Integer,Edge>> incidentEdges = Maps.newHashMap();
    public List<Edge> edges = new ArrayList<Edge>();

    public Vertice getVertice(Integer id){
        return vertices.get(id);
    }

    public Collection<Edge> getIncidentEdges(Vertice vertice){
        return incidentEdges.get(vertice.id).values();
    }

    public void addVertice(Vertice vertice){
        vertices.put(vertice.id, vertice);
    }

    public void addEdge(Edge edge){
        if (!vertices.containsKey(edge.source.id))
            vertices.put(edge.source.id, edge.source);
        if(!vertices.containsKey(edge.target.id))
            vertices.put(edge.target.id, edge.target);

        edges.add(edge);

        if (!incidentEdges.containsKey(edge.source.id)) {
            incidentEdges.put(edge.source.id, new HashMap<Integer, Edge>());
        }
        incidentEdges.get(edge.source.id).put(edge.target.id, edge);

        if (!incidentEdges.containsKey(edge.target.id)) {
            incidentEdges.put(edge.target.id, new HashMap<Integer, Edge>());
        }
        incidentEdges.get(edge.target.id).put(edge.source.id, edge);
    }

    public Edge getEdge(Integer verticeLeftId, Integer verticeRightId){
        return incidentEdges.get(verticeLeftId).get(verticeRightId);
    }

    public Edge getEdge(Vertice verticeLeft, Vertice verticeRight){
        return incidentEdges.get(verticeLeft.id).get(verticeRight.id);
    }

    public boolean existsEdge(Integer verticeLeftId, Integer verticeRightId){
        return incidentEdges.get(verticeLeftId) != null && incidentEdges.get(verticeLeftId).get(verticeRightId) != null;
    }

    public boolean existsEdge(Vertice verticeLeft, Vertice verticeRight){
        return incidentEdges.get(verticeLeft.id) != null && incidentEdges.get(verticeLeft.id).get(verticeRight.id) != null;
    }

    public boolean existsVertice(Integer verticeId){
        return vertices.containsKey(verticeId);
    }

    public boolean existsVertice(Vertice vertice){
        return vertices.containsKey(vertice.id);
    }

    /*
    Basically a BFS
     */
    public void updateLeader(Vertice verticeToUpdate, Vertice newLeader){

        Queue<Integer> verticeQueue = new LinkedList<Integer>();

        newLeader.componentSize += verticeToUpdate.leader.componentSize;
        verticeQueue.add(verticeToUpdate.id);
        Vertice currentVertice;
        while (!verticeQueue.isEmpty()){
            currentVertice = vertices.get(verticeQueue.poll());
            //Updating leader
            currentVertice.leader = newLeader;
            if(incidentEdges.containsKey(currentVertice.id)){
                for (Integer vertice : incidentEdges.get(currentVertice.id).keySet()) {
                    if (vertices.get(vertice).leader.id != newLeader.id)
                        verticeQueue.add(vertice);
                }
            }
        }
    }
}
