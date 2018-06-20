package Chapter_7_ExecutorFramework.Part_2_Stop_Executor_Service;

import java.util.concurrent.Executors;

/**
 * After addition of interruption handling logic the thread could be interrupted via shutdownNow() call.
 * <p>
 * The reason is that shutdownNow doesn't terminate the thread. it just interrupts all running threads.
 * <p>
 * NOTE: the common pattern here is wrapping to the while(!isInterrupted) cycle.
 */
public class Ex_5_How_to_finish_long_term_tasks_via_interruption {
    public static void main(String[] args) throws InterruptedException {
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

        System.out.println("Trying to shutdown");
        executor.shutdownNow();
    }
}