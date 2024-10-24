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
        double pi = 0.0;

        for (int k = 0; k <= n; k++) {
            pi += (1.0 / Math.pow(16, k)) *
                    ((4.0 / (8 * k + 1)) -
                            (2.0 / (8 * k + 4)) -
                            (1.0 / (8 * k + 5)) -
                            (1.0 / (8 * k + 6)));
        }

        int nthDigit = (int) ((pi * Math.pow(10, n)) % 10);

        return Math.abs(nthDigit);
    }

}

