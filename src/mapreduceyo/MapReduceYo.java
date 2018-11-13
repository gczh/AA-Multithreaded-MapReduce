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
        System.out.print("Where's the file you want us to MapReduce? ");
        String fileLocation = scanner.next();
        
        try {
            
            File file = new File(fileLocation); 

            BufferedReader br = new BufferedReader(new FileReader(file)); 

            String st; 
            while ((st = br.readLine()) != null) {
              System.out.println(st); 
            }
        
        } catch(FileNotFoundException fnfe) {
            System.out.println("Can't find the file");
        } catch(IOException ioe) {
            System.out.println("Issues with reading the file");
        }
    }
    
}
