/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapreduceyo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 *
 * @author cuppycakes
 */
public class MapReduceYo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Welcome to MapReduce App");
        
        // Create scanner for CLI app
        Scanner scanner = new Scanner(System.in);
        
        // get file location as String
        System.out.print("Where's the folder you want us to MapReduce? ");
        String folderLocation = scanner.next();
        
        // HashMap to do aggregation
        HashMap<String, Long> nameTotals = new HashMap<>();
        
        // Loop through each file in /inputs directory (warning: the files are LARGE at ~190mb each)
        File inputsFolder = new File(folderLocation);
        
        // start timer
        System.out.println("Starting timer...");
        StopWatch timer = new StopWatch();
        timer.start();
        
        
        // Create Thread Pool to multithread MapReduce tasks
        ExecutorService pool = Executors.newCachedThreadPool();
        /* 
            Each future is mapped to false
            False refers to future not being completed: !isDone()
        */
        HashMap<Future<HashMap<String, Long>>, Boolean> mappers = new HashMap<>();
        
        
        // Loop through directory
        /*
            Assume that we are working with a super large dataset that's being streamed from different
            locations into the server. And our job is to aggregate the name value pairs into 
            name total pairs
        */
        
        for(File file : inputsFolder.listFiles()) {
            if(file.isFile()) {
                System.out.println("Adding " + file.getName());
                // Try to take a file location, read the contents and aggregate the name value pairs
                Mapper mapper = new Mapper(file);
                Future<HashMap<String, Long>> future = pool.submit(mapper);
                mappers.put(future, false);
            }
        }
        
        
        /*
            Instead of perpetually waiting for every Map task to be done
            We want to concurrently work on reducing every finished Map task
        */
        
        
        boolean allDone = false;
        do {
            for(Future<HashMap<String, Long>> future : mappers.keySet()) {
                if(future.isDone()) {
                    System.out.println("Done with a file!");
                    
                    try {
                        HashMap<String, Long> result = future.get();
                        System.out.println("Size of result: " + result.size());
                        // Merge result with nameTotals (replace with reducer later)
                        for(String name : result.keySet()) {
                            Long value = result.get(name);
                            if(!nameTotals.containsKey(name)) {
                                nameTotals.put(name, 0L);
                            }
                            Long currValue = nameTotals.get(name);
                            nameTotals.put(name, currValue + value);
                        }
                    } catch (InterruptedException ie) {
                        System.out.println("Interrupted Execution");
                    } catch (ExecutionException ee) {
                        System.out.println("Execution Exception");
                    }
                }
                // If ALL futures are done, this will be true 
                if(!future.isDone()) 
                    allDone = false;
                else
                    allDone = true;
            }
        } while (!allDone);
       
        
        for(String name : nameTotals.keySet()) {
            System.out.println(name + ": " + nameTotals.get(name));
        }
        
        System.out.println("Time elapsed : " + timer.toString());
        System.out.println("Summary:");
        
    }
    
}
