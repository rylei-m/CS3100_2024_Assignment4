package src.main.java;

import java.util.concurrent.TimeUnit;

public class ParallelPi {
    public static void main(String[] args) throws InterruptedException {
        int numCores = Runtime.getRuntime().availableProcessors();
        int numDigits = 1000;

        TaskQueue taskQueue = new TaskQueue(numDigits);
        ResultTable resultTable = new ResultTable();

        long startTime = System.nanoTime();

        WorkerThread[] workers = new WorkerThread[numCores];
        for (int i = 0; i < numCores; i++) {
            workers[i] = new WorkerThread(taskQueue, resultTable);
            workers[i].start();
        }

        for (WorkerThread worker : workers) {
            worker.join();
        }

        long endTime = System.nanoTime();
        long totalTimeMillis = TimeUnit.NANOSECONDS.toMillis(endTime - startTime);
        System.out.println("Time taken: " + totalTimeMillis + " ms");

        System.out.println("\nComputed Pi: ");
        resultTable.printResults();

    }
}
