package assignment4;

import assignment2.Edge;
import assignment2.Graph;
import assignment2.Vertice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Created by Martin on 4/12/2015.
 */
public class HammingDistKruskal {

    public static void main(String[] args){

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            String[] splittedLine = line.split(" ");
            int nodesCount = Integer.parseInt(splittedLine[0]);
            int nodesSize = Integer.parseInt(splittedLine[1]);

            // Pre processing phase
            //Computing 1-distance, 2-distance bin arrays to XOR with
            int maxVal = (int)Math.pow(2, nodesSize)-1;
            //1-distance
            int initial = 1;
            int[] hammingDist1 = new int[nodesSize];
            int[] hammingDist2 = new int[nodesSize*(nodesSize-1)/2];
            int k = 0;
            for (int i = 0; i < hammingDist1.length; i++) {
                if (i < hammingDist1.length)
                    hammingDist1[i] = (int)Math.pow(2, i);
                for (int j = 0; j < i; j++) {
                    hammingDist2[k++] = (int)(Math.pow(2, i) + Math.pow(2, j));
                }
            }

            //Declaring bucket
            byte[] bucket = new byte[(int)Math.pow(2, nodesSize)];

            Pattern pattern = Pattern.compile("\\s");
            int nodeId;
            Graph graph = new Graph();
            for (int i = 0; i < nodesCount; i++) {
                line = pattern.matcher(bufferRead.readLine()).replaceAll("");
                nodeId = Integer.parseInt(line, 2);
                if (bucket[nodeId] == 0)
                    graph.addVertice(new Vertice(nodeId));
                bucket[nodeId] = 1;
            }

            List<Edge> edges = new LinkedList<Edge>();
            int nextVertice;
            for (Integer vertice : graph.vertices.keySet()) {
                for (int i = 0; i < hammingDist2.length; i++) {
                    if(i < hammingDist1.length){
                        nextVertice = hammingDist1[i] ^ vertice;
                        if (bucket[nextVertice] == 1)
                            edges.add(new Edge(graph.getVertice(vertice), graph.getVertice(nextVertice), 1L));
                    }
                    nextVertice = hammingDist2[i] ^ vertice;
                    if (bucket[nextVertice] == 1)
                        edges.add(new Edge(graph.getVertice(vertice), graph.getVertice(nextVertice), 2L));
                }
            }
            Collections.sort(edges);
            Edge edge;
            Iterator<Edge> iterator = edges.iterator();
            while (iterator.hasNext()){
                edge = iterator.next();
                if (edge.source.leader.id != edge.target.leader.id){
                    graph.addEdge(edge);
                    if(edge.source.leader.componentSize < edge.target.leader.componentSize)
                        graph.updateLeader(edge.source, edge.target.leader);
                    else
                        graph.updateLeader(edge.target, edge.source.leader);
                    k--;
                }
            }
            Set<Integer> leaders = new HashSet<Integer>(graph.vertices.size());
            for (Vertice vertice : graph.vertices.values()) {
                leaders.add(vertice.leader.id);
            }

            System.out.println(leaders.size());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
