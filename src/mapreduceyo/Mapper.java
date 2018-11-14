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
import java.util.concurrent.Callable;

/**
 *
 * @author cuppycakes
 */
public class Mapper implements Callable<HashMap<String, Long>>{
    
    private HashMap<String, Long> nameSubTotal;
    private File file;
    
    public Mapper(File file) {
        nameSubTotal = new HashMap<>();
        this.file = file;
    }

    /*
        The map step here is similar to what reduce is doing
        However we are mapping all the name values in a SINGLE file
        and returning an aggregated subtotal of name -> subtotals in each file
    */
    
    public void map(String name, long value) {
        if(!nameSubTotal.containsKey(name)) {
            nameSubTotal.put(name, 0L);
        }
        long currentValue = nameSubTotal.get(name);
        nameSubTotal.put(name, currentValue + value);
    }
    
    @Override
    public HashMap<String, Long> call() {
        System.out.println("Running thread for " + file.getName());
        try {
            // File file = new File(fileLocation); 

            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String line; 
            while ((line = br.readLine()) != null) {
              String[] tokens = line.split(",");
              String name = tokens[0];
              int value = Integer.parseInt(tokens[1]);
              // map the name and value
              map(name, value);
            }

        } catch(FileNotFoundException fnfe) {
            System.out.println("Can't find the file");
        } catch(IOException ioe) {
            System.out.println("Issues with reading the file");
        }
        
        System.out.println("Done with thread for " + file.getName() + " of size " + nameSubTotal.size());
        return nameSubTotal;
    }
    
    
}
