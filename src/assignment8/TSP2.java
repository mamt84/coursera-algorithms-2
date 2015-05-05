package assignment8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Martin on 5/3/2015.
 */
public class TSP2 {

    static double[][] A;
    static List<Integer> cities;
    static int number_of_cities;
    static List<Set<Integer>> myPowerSetPrevious;
    static List<Set<Integer>> myPowerSetNext;
    static int sizeTogether;

    public static double distance(double x1, double y1, double x2, double y2) {

        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    private static void getSubsets(List<Integer> superSet, int k, int idx, Set<Integer> current, List<Set<Integer>> solution) {
        //successful stop clause
        if (current.size() == k) {
            Set<Integer> a = new HashSet<Integer>();
            a.addAll(current);
            solution.add(a);
            return;
        }
        //unseccessful stop clause
        if (idx == superSet.size()) return;
        Integer x = superSet.get(idx);
        current.add(x);
        //"guess" x is in the subset
        getSubsets(superSet, k, idx + 1, current, solution);
        if (x != 1) {
            current.remove(x);
            //"guess" x is not in the subset
            getSubsets(superSet, k, idx + 1, current, solution);
        }
    }

    public static List<Set<Integer>> getSubsets(List<Integer> superSet, int k) {
        List<Set<Integer>> res = new ArrayList<Set<Integer>>();
        getSubsets(superSet, k, 0, new HashSet<Integer>(), res);
        return res;
    }

    public static void initialize(int k_start) {

        int presize = myPowerSetPrevious.size(); // size of previous (1)
        int oldSize = sizeTogether;
        myPowerSetPrevious = myPowerSetNext;
        myPowerSetNext = getSubsets(cities, k_start);
        sizeTogether = myPowerSetPrevious.size() + myPowerSetNext.size();
        double[][] B = new double[sizeTogether][number_of_cities + 1];

        int count = 0;
        for (int i = presize; i < oldSize; i++) {
            for (int j = 1; j <= number_of_cities; j++) {
                B[count][j] = A[i][j];
            }
            count++;
        }
        presize = myPowerSetPrevious.size();
        for (int i = presize; i < sizeTogether; i++) {
            B[i][1] = Double.POSITIVE_INFINITY;
        }
        A = B;

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();
            number_of_cities = Integer.parseInt(line);

            double[][] xy_points = new double[number_of_cities + 1][2];
            cities = new ArrayList<Integer>();
            String[] splittedLine;
            for (int i = 1; i < number_of_cities + 1; i++) {
                line = bufferRead.readLine();
                splittedLine = line.split( " " );

                xy_points[i][0] = Double.parseDouble(splittedLine[0]);
                xy_points[i][1] = Double.parseDouble(splittedLine[1]);
                cities.add(i);
            }
            myPowerSetPrevious = getSubsets(cities, 1);
            myPowerSetNext = getSubsets(cities, 2);

            sizeTogether = myPowerSetPrevious.size() + myPowerSetNext.size();
            A = new double[sizeTogether][number_of_cities + 1]; // j's for each S
            for (int i = 0; i < myPowerSetPrevious.size(); i++) {
                if (myPowerSetPrevious.get(i).size() == 1 & myPowerSetPrevious.get(i).contains(1)) {
                    A[i][1] = 0;
                } else
                    A[i][1] = Double.POSITIVE_INFINITY;
            }
            for (int i = myPowerSetPrevious.size(); i < sizeTogether; i++) {
                A[i][1] = Double.POSITIVE_INFINITY;
            }

            int k_start = 2;
            //initialize(k_start);


            int count = myPowerSetPrevious.size();
            while (count < sizeTogether) {//Set<Integer> s : myPowerSetNext){ // for each subset S

                Set<Integer> s = myPowerSetNext.get(count - myPowerSetPrevious.size());
                if (s.size() >= 2 & s.contains(1)) {

                    for (Integer j : s) { // for each j vertex
                        if (j != 1) {
                            Set<Integer> newSet = new HashSet<Integer>();
                            newSet.addAll(s);
                            newSet.remove(j);
                            int index = myPowerSetPrevious.indexOf(newSet);
                            double minDist = -1;
                            for (Integer k : s) {
                                if (k != j) {
                                    double dist = A[index][k] + distance(xy_points[k][0], xy_points[k][1], xy_points[j][0], xy_points[j][1]);
                                    if (minDist == -1) {
                                        minDist = dist;
                                    } else if (minDist > dist) {
                                        minDist = dist;
                                    }
                                }
                            }
                            System.out.println("S: " + s + " j: " + j + " " + " = " + minDist);
                            A[count][j] = minDist;
                        }
                    }
                }
                count++;
                if (count >= A.length) {
                    k_start++;
                    //System.out.println("K_start: " + k_start);
                    initialize(k_start);
                    //System.out.println("length: " + myPowerSetNext.size());
                    count = myPowerSetPrevious.size();
                }

            }
            double minDist = -1;
            for (int j = 2; j <= number_of_cities; j++) {
                double dist = A[A.length - 1][j] + distance(xy_points[j][0], xy_points[j][1], xy_points[1][0], xy_points[1][1]);
                if (minDist == -1)
                    minDist = dist;
                else if (minDist > dist) {
                    minDist = dist;
                }
            }
            System.out.println("minDist: " + minDist);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}