package assignment5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Martin on 4/15/2015.
 */
public class OptimalBinarySearchTree {
    public static void main(String[] args){
        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            int n = Integer.parseInt(line);
            List<Integer> elements = new ArrayList<Integer>();
            List<Float> weights = new ArrayList<Float>();
            for (int i = 0; i < n; i++) {
                line = bufferRead.readLine();
                String[] splittedLine = line.split(" ");
                elements.add(Integer.parseInt(splittedLine[0]));
                weights.add(Float.parseFloat(splittedLine[1]));
            }


            Float[][] optimalCost = new Float[n][n];
            for (int i = 0; i < n; i++) {
                optimalCost[i][i] = weights.get(i);
            }

            float min;
            float sum = 0;
            for (int s = 0; s < n-1; s++) {
                for (int i = 0; i < n; i++) {
                    min = 100000f;
                    sum = 0;
                    for (int r = i; r <= i+s; r++) {
                        if (r-1 >= i && r+1 < i+s)
                            sum += weights.get(r) + optimalCost[i][r-1] + optimalCost[r+1][i+s];
                        else if (r-1 < i && r+1 < i+s)
                            sum += weights.get(r) + optimalCost[r+1][i+s];
                        else if (r+1 >= i+s && r-1 >= i)
                            sum += weights.get(r) + optimalCost[i][r-1];
                        else
                            sum += weights.get(r);
                        if (sum < min)
                            min = sum;
                    }
                    optimalCost[i][i+s] = min;
                }
            }

            System.out.println(optimalCost[1][n-1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
