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


            Float[][] a = new Float[n][n];
            for (int i = 0; i < n; i++) {
                a[i][i] = weights.get(i);
            }

            float min;
            float sum = 0;
            float temp;
            int j;
           for ( int x = 1; x <= n; x++ )
           {
              for ( int i = 0; i < n-x+1; i++ )
              {
                 j = i+x-1;

                 if (i != j){
                    min = 100000f;
                    for ( int r = i; r <= j; r++ )
                    {
                       sum = 0;
                       for ( int k = i; k <= j; k++ )
                       {
                          sum += weights.get(k);
                       }

                       if (r-1 >= i && r+1 <= j)
                          temp = sum + a[i][r-1] + a[r+1][j];
                       else if (r-1 < i && r+1 <= j)
                          temp = sum + a[r+1][j];
                       else if (r-1 >= i && r+1 > j)
                          temp = sum + a[i][r-1];
                       else
                          temp = sum;

                       if (temp < min)
                          min = temp;
                    }
                    a[i][j] = min;
                 }
                 //printMatrix( a );
              }
           }

           for ( int i = 0; i < n; i++ )
           {
              for ( int k = 0; k < n; k++ )
              {
                 System.out.print(a[i][k]);
                 System.out.print(" ");
              }
              System.out.println();
           }
           System.out.println(a[0][n-1]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

   public static void printMatrix(Float[][] a){
      int n = a.length;
      int n1;
      System.out.println();
      System.out.println();
      for ( int i = 0; i < n; i++ )
      {
         n1 = a[i].length;
         for ( int k = 0; k < n1; k++ )
         {
            System.out.print(a[i][k]);
            System.out.print(" ");
         }
         System.out.println();
      }

      System.out.println();
      System.out.println();
   }
}
