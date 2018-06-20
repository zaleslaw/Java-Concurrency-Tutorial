package Chapter_7_ExecutorFramework.Part_2_Stop_Executor_Service;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Full example with correct termination handling.
 */
public class Ex_6_Await_termination {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {

            System.out.println("I'm doing my job, sir!");

            double res = 0;

            for (long i = 0; i < 10_000_000_000L; i++) {
                res = res + 0.000000001;
                if (Thread.currentThread().isInterrupted()) break;
            }
            System.out.println("I'm doing my job, sir! " + res);
        });

        try {
            System.out.println("Trying to shutdown");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("Task was interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.out.println("Start tasks cancellation");
            }
            executor.shutdownNow();
            System.out.println("Pool is dead!");
        }
    }
}