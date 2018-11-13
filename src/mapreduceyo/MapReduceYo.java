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
import java.util.HashMap;
import java.util.Scanner;

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
        HashMap<String, Integer> nameTotals = new HashMap<>();
        
        // Loop through each file in /inputs directory (warning: the files are LARGE at ~190mb each)
        File inputsFolder = new File(folderLocation);
        for(File file : inputsFolder.listFiles()) {
            if(file.isFile()) {
                // Try to take a file location, read the contents and aggregate the name value pairs
                try {

                    // File file = new File(fileLocation); 

                    BufferedReader br = new BufferedReader(new FileReader(file)); 

                    String line; 
                    while ((line = br.readLine()) != null) {
                      String[] tokens = line.split(",");
                      String name = tokens[0];
                      int value = Integer.parseInt(tokens[1]);

                      if(!nameTotals.containsKey(name)) {
                          nameTotals.put(name, 0);
                      }

                      int currValue = nameTotals.get(name);
                      nameTotals.put(name, currValue + value);
                    }

                } catch(FileNotFoundException fnfe) {
                    System.out.println("Can't find the file");
                } catch(IOException ioe) {
                    System.out.println("Issues with reading the file");
                }
            }
        }
        
        for(String name : nameTotals.keySet()) {
            System.out.println(name + ": " + nameTotals.get(name));
        }
        
    }
    
}
