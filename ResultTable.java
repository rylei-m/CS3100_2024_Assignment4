import java.util.HashMap;

public class ResultTable {
    private HashMap<Integer, Integer> results = new HashMap<>();

    public synchronized void storeResult(int position, int digit) {
        results.put(position, digit);
    }

    public synchronized void printResults() {
        for (int i = 0; i < results.size(); i++) {
            System.out.print(results.get(i));
        }
    }
}
