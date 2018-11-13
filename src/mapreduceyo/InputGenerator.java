/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapreduceyo;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.io.*;

/**
 *
 * @author cuppycakes
 */
public class InputGenerator {
    
    private static final String FILENAME = "./input.txt";
    
    public static void main(String[] args) {
        
        // Delete file if it already exists for a fresh input file
        File file = new File("./input.txt"); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        }
        
        // For generating a list of names and numbers for MapReduce multithreading example
        
        String[] names = { "Gabriel", "Mokkie", "Ouh Eng Lieh", "Aldric", "Potato", "Dion", "Moses", "Randall", "Jordy", "David" };
        Random rand = new Random();
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        for(String name : names) {
            System.out.println(name + "\n===============");
            
            int count = 0;
            int target = 100;
            do {
                int randomNumber = rand.nextInt(10) + 1;
                if ((target - (count + randomNumber)) < 0) {
                    randomNumber = target - count;
                }
                try {
                    fw = new FileWriter(FILENAME, true);
                    bw = new BufferedWriter(fw);
                    bw.write(name + "," + randomNumber + "\n");
                } catch(IOException ioe) {
                    ioe.printStackTrace();
                } finally {
                    try {
                        if (bw != null)
                            bw.close();

                        if (fw != null)
                            fw.close();

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                
                count += randomNumber;
            } while(count != target);
            
            // Scramble the lines now
            
            
            
        }
        
    }
}
