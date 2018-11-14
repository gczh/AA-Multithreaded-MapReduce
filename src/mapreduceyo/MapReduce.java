/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapreduceyo;

import java.util.*;

/**
 *
 * @author cuppycakes
 * 
 * This class should take in a file full of Name Value pairs
 * It will map to a HashMap of Name and Totals for that ONE file
 * Then it reduces it further to Name and Totals for ALL files
 * 
 */
public class MapReduce {
    
    private HashMap<String, Long> valueTotals = new HashMap<>();
    
    public Map<String, Long> map(String key, long value) {
        
    }
    
    public Map<String, Long> reduce(String key, long value) {
        
    }
}
