package src.main.java;

public class WorkerThread extends Thread {
    private TaskQueue taskQueue;
    private ResultTable resultTable;

    public WorkerThread(TaskQueue taskQueue, ResultTable resultTable) {
        this.taskQueue = taskQueue;
        this.resultTable = resultTable;
    }

    @Override
    public void run() {
        Integer task;
        while ((task = taskQueue.getNextTask()) != null) {
            int digit = computePiDigit(task); // BBP formula computation here
            resultTable.storeResult(task, digit);

            if (task % 10 == 0) {
                System.out.print(".");
                System.out.flush();
            }
        }
    }

    private int computePiDigit(int n) {
        // Implement BBP formula to compute the nth digit of Pi
        return 0; // Placeholder
    }
}
