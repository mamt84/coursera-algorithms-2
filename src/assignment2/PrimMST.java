package assignment2;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.MinMaxPriorityQueue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * Created by Martin on 4/5/2015.
 */
public class PrimMST {
    public static void main (String[] args){

        Graph graph = new Graph();
        Vertice verticeLeft;
        Vertice verticeRight;
        Integer verticeLeftId;
        Integer verticeRightId;
        Map<Integer, Vertice> nonSpanned = Maps.newHashMap();

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            String[] splittedLine = line.split(" ");
            Integer edgesCount = Integer.parseInt(splittedLine[1]);

            Edge edge;
            for (int i = 0; i < edgesCount; i++) {
                line = bufferRead.readLine();
                splittedLine = line.split(" ");

                verticeLeftId = Integer.parseInt(splittedLine[0]);
                verticeRightId = Integer.parseInt(splittedLine[1]);
                verticeLeft = graph.existsVertice(verticeLeftId) ? graph.getVertice(verticeLeftId) : new Vertice(verticeLeftId);
                verticeRight = graph.existsVertice(verticeRightId) ? graph.getVertice(verticeRightId) : new Vertice(verticeRightId);

                if(!graph.existsEdge(verticeLeftId, verticeRightId)){
                    edge = new Edge(verticeLeft, verticeRight, Long.parseLong(splittedLine[2]));
                    graph.addEdge(edge);
                }

                if(!nonSpanned.containsKey(verticeLeft.id))
                    nonSpanned.put(verticeLeft.id, verticeLeft);
                if(!nonSpanned.containsKey(verticeRight.id)) {
                    nonSpanned.put(verticeRight.id, verticeRight);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<Integer, Vertice> spanned = Maps.newHashMap();

        //List<Integer> test = Lists.newArrayList(nonSpanned.keySet());

        //Vertice vertice = nonSpanned.remove(test.get(5));
        //Vertice vertice = nonSpanned.remove(test.get(test.size()-1));
        Vertice vertice = nonSpanned.remove(nonSpanned.keySet().iterator().next());
        //vertice.key = Integer.MIN_VALUE;
        spanned.put(vertice.id, vertice);

        MinMaxPriorityQueue<Vertice> heap = MinMaxPriorityQueue.create();
        long min;
        for (Integer nonSpannedId : nonSpanned.keySet()) {
            min = Integer.MAX_VALUE;
            for (Integer spannedId : spanned.keySet()) {
                if(graph.existsEdge(nonSpannedId, spannedId)){
                    if(graph.getEdge(nonSpannedId, spannedId).weight < min){
                        min = graph.getEdge(nonSpannedId, spannedId).weight;
                    }
                }
            }
            vertice = graph.getVertice(nonSpannedId);
            vertice.key = min;
            heap.add(vertice);
        }

        //BigInteger overallCost = BigInteger.ZERO;
        long overallCost = 0;
        Vertice nextVertice;
        while (!heap.isEmpty()){
            vertice = heap.removeFirst();
            nonSpanned.remove(vertice.id);
            spanned.put(vertice.id, vertice);

            overallCost += vertice.key;
            //overallCost = overallCost.add(BigInteger.valueOf(vertice.key));

            for (Edge edge : graph.getIncidentEdges(vertice)) {
                nextVertice = edge.getNextVertice(vertice);
                if (nonSpanned.containsKey(nextVertice.id)){
                    heap.remove(nextVertice);
                    nextVertice.key = Math.min(nextVertice.key, edge.weight);
                    heap.add(nextVertice);
                }
            }
        }

        System.out.println(overallCost);
    }
}
