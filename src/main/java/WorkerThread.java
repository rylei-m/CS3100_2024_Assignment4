package src.main.java;
import java.math.BigDecimal;
import java.math.MathContext;

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
        BigDecimal pi = BigDecimal.ZERO;
        MathContext mc = new MathContext(100);  // Higher precision for Pi computation

        for (int k = 0; k <= n; k++) {
            // Use BigDecimal to avoid issues with Math.pow
            BigDecimal sixteenPowK = BigDecimal.valueOf(16).pow(k, mc); // 16^k

            BigDecimal term = BigDecimal.ONE.divide(sixteenPowK, mc)
                    .multiply(
                            BigDecimal.valueOf(4).divide(BigDecimal.valueOf(8 * k + 1), mc)
                                    .subtract(BigDecimal.valueOf(2).divide(BigDecimal.valueOf(8 * k + 4), mc))
                                    .subtract(BigDecimal.ONE.divide(BigDecimal.valueOf(8 * k + 5), mc))
                                    .subtract(BigDecimal.ONE.divide(BigDecimal.valueOf(8 * k + 6), mc))
                    );
            pi = pi.add(term, mc);
        }

        // Multiply pi by 10^n to get the nth digit
        BigDecimal shiftedPi = pi.multiply(BigDecimal.TEN.pow(n), mc);

        return shiftedPi.intValue() % 10;
    }

}

