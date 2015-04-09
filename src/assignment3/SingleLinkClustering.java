package assignment3;

import assignment2.Edge;
import assignment2.Graph;
import assignment2.Vertice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Martin on 4/8/2015.
 */
public class SingleLinkClustering {

    public static void main(String[] args){

        Graph graph = new Graph();
        Vertice verticeLeft;
        Vertice verticeRight;
        Integer verticeLeftId;
        Integer verticeRightId;
        Edge edge;
        Integer k = 0;
        List<Edge> edges = null;
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            k = Integer.parseInt(line);
            edges = new ArrayList<Edge>(k*(k-1)/2);

            String[] splittedLine;
            for (int i = 0; i < k*(k-1)/2; i++) {
                line = bufferRead.readLine();
                splittedLine = line.split(" ");

                verticeLeftId = Integer.parseInt(splittedLine[0]);
                verticeRightId = Integer.parseInt(splittedLine[1]);
                if (!graph.existsVertice(verticeLeftId)){
                    verticeLeft = new Vertice(verticeLeftId);
                    graph.addVertice(verticeLeft);
                }
                else
                    verticeLeft = graph.getVertice(verticeLeftId);

                if(!graph.existsVertice(verticeRightId)){
                    verticeRight = new Vertice(verticeRightId);
                    graph.addVertice(verticeRight);
                }
                else
                    verticeRight = graph.getVertice(verticeRightId);


                edges.add(new Edge(verticeLeft, verticeRight, Long.parseLong(splittedLine[2])));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(edges);
        Iterator<Edge> iterator = edges.iterator();
        while (k > 4){
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

        //Maximum spacing is the length of next "crossing" edge
        do {
            edge = iterator.next();
        } while (edge.source.leader.id == edge.target.leader.id);

        System.out.println(edge.weight);
    }
}
