package assignment5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Martin on 4/16/2015.
 */
public class KnapsackBig {

    public static void main (String[] args){

        try {
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            String[] splittedLine = line.split(" ");

            int W = Integer.parseInt(splittedLine[0]);
            int n = Integer.parseInt(splittedLine[1]);

            Map<Integer, Map<Integer, Long>> A = new HashMap<Integer, Map<Integer, Long>>();

            int[] weight = new int[n+1];
            int[] value = new int[n+1];

            for (int i = 1; i <= n; i++) {
                line = bufferRead.readLine();
                splittedLine = line.split(" ");

                value[i] = Integer.parseInt(splittedLine[0]);
                weight[i] = Integer.parseInt(splittedLine[1]);
            }

            A.put(0, new HashMap<Integer, Long>());
            for (int i = 0; i < W; i++) {
                A.get(0).put(i, 0L);
            }

            for (int i = 1; i <= n; i++) {
                if (i - 2 >= 1){
                    A.put(i, A.get(i-2));
                }
                else{
                    A.put(i, new HashMap<Integer, Long>());
                }
                for (int j = 0; j < W; j++) {
                    if (weight[i] > j)
                        A.get(i).put(j, A.get(i-1).get(j));
                    else
                        A.get(i).put(j, Math.max(A.get(i-1).get(j), A.get(i-1).get(j-weight[i]) + (long)value[i]));
                }
                System.out.println("Processing i = " + i);
            }

            System.out.println(A.get(n).get(W-1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
