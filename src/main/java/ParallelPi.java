package src.main.java;

import java.util.concurrent.TimeUnit;

public class ParallelPi {
    public static void main(String[] args) throws InterruptedException {
        int numCores = Runtime.getRuntime().availableProcessors();
        int numDigits = 1000;

        TaskQueue taskQueue = new TaskQueue(numDigits);
        ResultTable resultTable = new ResultTable();

        // Start timing
        long startTime = System.nanoTime();

        // Create worker threads
        WorkerThread[] workers = new WorkerThread[numCores];
        for (int i = 0; i < numCores; i++) {
            workers[i] = new WorkerThread(taskQueue, resultTable);
            workers[i].start();
        }

        // Wait for all threads to finish
        for (WorkerThread worker : workers) {
            worker.join();
        }

        // Stop timing
        long endTime = System.nanoTime();
        long totalTime = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);

        // Print results
        System.out.println("\nComputed Pi: ");
        resultTable.printResults();

        // Print the time taken
        System.out.println("\nTime taken: " + totalTime + " seconds");
    }
}
