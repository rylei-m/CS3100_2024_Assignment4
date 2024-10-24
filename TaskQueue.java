import java.util.LinkedList;
import java.util.Collections;

public class TaskQueue {
    private LinkedList<Integer> tasks = new LinkedList<>();

    public TaskQueue(int numTasks) {
        // Populate tasks in random order
        for (int i = 0; i < numTasks; i++) {
            tasks.add(i);
        }
        Collections.shuffle(tasks); // Shuffle to randomize task order
    }

    public synchronized Integer getNextTask() {
        return tasks.poll();
    }

    public synchronized boolean isEmpty() {
        return tasks.isEmpty();
    }
}
