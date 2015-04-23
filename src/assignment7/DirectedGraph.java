package assignment7;

import assignment2.Edge;
import assignment2.Vertice;
import com.google.common.collect.Maps;

import java.util.*;

/**
 * Created by mmorcate on 4/23/2015.
 */
public class DirectedGraph
{

   public Map<Integer, Vertice> vertices = new HashMap<Integer, Vertice>();
   public Map<Integer, Map<Integer, Edge>> incommingEdges = Maps.newHashMap();
   public Map<Integer, Map<Integer, Edge>> outgoingEdges = Maps.newHashMap();
   public List<Edge> edges = new ArrayList<Edge>();

   public Vertice getVertice(Integer id){
      return vertices.get(id);
   }

   public Collection<Edge> getOutgoingEdges(Vertice vertice){
      return outgoingEdges.get(vertice.id) != null ? outgoingEdges.get(vertice.id).values() :
            new ArrayList<Edge>(0);
   }

   public Collection<Edge> getIncommingEdges(Vertice vertice){
      return incommingEdges.get(vertice.id) != null ? incommingEdges.get(vertice.id).values() : new ArrayList<Edge>(0);
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

      if (!outgoingEdges.containsKey(edge.source.id)) {
         outgoingEdges.put(edge.source.id, new HashMap<Integer, Edge>());
      }
      outgoingEdges.get(edge.source.id).put( edge.target.id, edge );

      if (!incommingEdges.containsKey(edge.target.id)) {
         incommingEdges.put(edge.target.id, new HashMap<Integer, Edge>());
      }
      incommingEdges.get( edge.target.id ).put(edge.source.id, edge);
   }

   public Edge getEdge(Integer verticeLeftId, Integer verticeRightId){
      return outgoingEdges.get(verticeLeftId).get(verticeRightId);
   }

   public Edge getEdge(Vertice verticeLeft, Vertice verticeRight){
      return outgoingEdges.get(verticeLeft.id).get(verticeRight.id);
   }

   public boolean existsEdge(Integer verticeLeftId, Integer verticeRightId){
      return outgoingEdges.get(verticeLeftId) != null && outgoingEdges.get(verticeLeftId).get(verticeRightId) != null;
   }

   public boolean existsEdge(Vertice verticeLeft, Vertice verticeRight){
      return outgoingEdges.get(verticeLeft.id) != null && outgoingEdges.get(verticeLeft.id).get(verticeRight.id) != null;
   }

   public boolean existsVertice(Integer verticeId){
      return vertices.containsKey(verticeId);
   }

   public boolean existsVertice(Vertice vertice){
      return vertices.containsKey(vertice.id);
   }

   public int inDegree(Integer verticeId){
      return incommingEdges.get( verticeId ).size();
   }

   public int outDegree(Integer verticeId){
      return outgoingEdges.get( verticeId ).size();
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
         if( outgoingEdges.containsKey(currentVertice.id)){
            for (Integer vertice : outgoingEdges.get(currentVertice.id).keySet()) {
               if (vertices.get(vertice).leader.id != newLeader.id)
                  verticeQueue.add(vertice);
            }
         }
      }
   }
}
