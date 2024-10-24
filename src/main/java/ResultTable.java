package src.main.java;

import java.util.HashMap;

public class ResultTable {
    private HashMap<Integer, Integer> results = new HashMap<>();

    public synchronized void storeResult(int position, int digit) {
        results.put(position, digit);
    }

    public synchronized void printResults() {
        results.keySet().stream().sorted().forEach(key -> {
            System.out.print(results.get(key));
        });
    }
}
