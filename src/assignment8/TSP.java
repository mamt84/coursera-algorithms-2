package assignment8;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Martin on 5/3/2015.
 */
public class TSP {
    public static void main (String[] args){
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();
            int N = Integer.parseInt(line);

            String[] splittedLine;
            Set<Point> cities = Sets.newHashSet();
            for (int i = 0; i < N; i++) {
                line = bufferRead.readLine();
                splittedLine = line.split( " " );

                cities.add(new Point(Float.parseFloat(splittedLine[0]), Float.parseFloat(splittedLine[1])));
            }
            Point initial = cities.iterator().next();
            Set<Set<Point>> powerSet = Sets.powerSet(cities);

            Map<Set<Set<Point>>, Point> A = new HashMap<Set<Set<Point>>, Point>();
            float min = Float.MAX_VALUE;
            for (int m = 2; m < N; m++) {
                System.out.println(m);

                for (Set<Point> points : powerSet) {
                    if(points.size() == m && points.contains(initial)){
                        for (Point j : points) {
                            if (j != initial){
                                for (Point k : points) {
                                    if (k != j){
                                        //if(Sets.d)
                                    }
                                }
                            }
                        }


                        //A.add(points);
                    }
                }


            }


            System.out.println(powerSet.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static float distace(Point point1, Point point2){
        return 0;
    }
}
