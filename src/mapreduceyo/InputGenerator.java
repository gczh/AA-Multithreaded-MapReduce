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
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author cuppycakes
 */

class NameValue {
    private String name;
    private int value; 
    
    public NameValue(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public int getValue() {
        return value;
    }
    
    
}

public class InputGenerator {
    
    private static final int TARGET = 10_000_000;
    private static final String FILENAME = "./input_"+TARGET+".txt";
    
    public static void main(String[] args) {
        
        // Delete file if it already exists for a fresh input file
        File file = new File(FILENAME); 
          
        if(file.delete()) 
        { 
            System.out.println("File deleted successfully"); 
        }
        
        // For generating a list of names and numbers for MapReduce multithreading example
        
        String[] names = { "Gabriel", "Sunshine", "Mokkie", "Ouh Eng Lieh", "Aldric", "Potato", "Dion", "Moses", "Randall", "Jordy", "David" };
        ArrayList<NameValue> nameValueList = new ArrayList<>();
        
        Random rand = new Random();
        BufferedWriter bw = null;
        FileWriter fw = null;
        
        for(String name : names) {
            System.out.println(name + "\n===============");
            
            int count = 0;
            do {
                int randomNumber = rand.nextInt(10) + 1;
                if ((TARGET - (count + randomNumber)) < 0) {
                    randomNumber = TARGET - count;
                }
                nameValueList.add(new NameValue(name, randomNumber));
                
                count += randomNumber;
            } while(count != TARGET);
            
        }
        
        // Scramble the lines now
        Collections.shuffle(nameValueList);
        
        try {
            fw = new FileWriter(FILENAME, true);
            bw = new BufferedWriter(fw);

            // Iterate over shuffled arraylist of non-unique name value pairs
            for(NameValue nv : nameValueList) {
                bw.write(nv.getName() + "," + nv.getValue() + "\n");
            }

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
    }
}
