package Chapter_7_ExecutorFramework.Part_2_Stop_Executor_Service;

import java.util.concurrent.Executors;

/**
 * Sometimes shutdownNow doesn't work like a killer.
 * It doesn't break our calculations immediately.
 * Q: What's wrong with it?
 * A: From the javadoc of shutdownNow():
 * There are no guarantees beyond best-effort attempts to stop processing actively executing tasks.
 * For example, typical implementations will cancel via Thread.interrupt, so any task that fails to respond to interrupts may never terminate.
 */
public class Ex_4_How_to_finish_long_term_tasks {
    public static void main(String[] args) throws InterruptedException {
        var executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {

            System.out.println("I'm doing my job, sir!");

            double res = 0;

            for (long i = 0; i < 10_000_000_000L; i++) {
                res = res + 0.000000001;
            }
            System.out.println("I'm doing my job, sir! " + res);
        });

        System.out.println("Trying to shutdown");
        executor.shutdownNow();
    }
}