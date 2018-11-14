package mapreduceyo;

import java.util.HashMap;
import java.util.concurrent.Callable;

/**
 *
 * @author cuppycakes
 */
public class Reducer implements Callable<HashMap<String, Long>> {
    private HashMap<String, Long> nameTotals;
    HashMap<String, Long> masterNameTotals;
    
    /*
        Passes in the main name totals variable from the Main program
        The reducers then calculates the total and mutates that
    */
    public Reducer(HashMap<String, Long> masterNameTotals) {
        this.nameTotals = new HashMap<>();
        this.masterNameTotals = masterNameTotals;
    }
    
    @Override
    public HashMap<String, Long> call() {
        for(String name : nameTotals.keySet()) {
            if(!masterNameTotals.containsKey(name)) {
                masterNameTotals.put(name, 0L);
            }
            Long currentValue = masterNameTotals.get(name);
            masterNameTotals.put(name, currentValue + nameTotals.get(name));
        }
        
        return masterNameTotals;
    }
    
}
