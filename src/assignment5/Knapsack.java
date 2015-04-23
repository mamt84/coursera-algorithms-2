package assignment5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Martin on 4/16/2015.
 */
public class Knapsack {

    public static void main (String[] args){

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            String[] splittedLine = line.split(" ");

            int W = Integer.parseInt(splittedLine[0]);
            int n = Integer.parseInt(splittedLine[1]);

            long A[][] = new long[n+1][W];

            int[] weight = new int[n+1];
            int[] value = new int[n+1];

            for (int i = 1; i <= n; i++) {
                line = bufferRead.readLine();
                splittedLine = line.split(" ");

                value[i] = Integer.parseInt(splittedLine[0]);
                weight[i] = Integer.parseInt(splittedLine[1]);
            }

            for (int i = 0; i < W; i++) {
                A[0][i] = 0;
            }

            for (int i = 1; i <= n; i++) {
                for (int j = 0; j < W; j++) {
                    if (weight[i] > j)
                        A[i][j] = A[i-1][j];
                    else
                        A[i][j] = Math.max(A[i-1][j], A[i-1][j-weight[i]] + (long)value[i]);
                }
            }

            System.out.println(A[n][W-1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
