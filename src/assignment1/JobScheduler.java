package assignment1;

import com.google.common.collect.MinMaxPriorityQueue;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Martin on 4/4/2015.
 */
public class JobScheduler {
    public static void main(String []args){

        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            String line = bufferRead.readLine();

            Integer jobsCount = Integer.parseInt(line);

            MinMaxPriorityQueue<Job> jobs = MinMaxPriorityQueue.create();
            for (int i = 0; i < jobsCount; i++) {
                jobs.add(new Job1(i, bufferRead.readLine()));
            }

            long sum = 0;
            long completionTime = 0;

            Job job;
            while (!jobs.isEmpty()){
                job = jobs.removeLast();
                completionTime += job.lenght;
                sum += job.weight*completionTime;
            }
            System.out.println(sum);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
