package Chapter_7_ExecutorFramework.Part_2_Stop_Executor_Service;

import java.util.concurrent.Executors;

/**
 * A new task will be aborted after shutdown() command call.
 */
public class Ex_3_Reject_the_novichok {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println("I'm doing my job, sir!");
        });
        executor.submit(() -> {
            System.out.println("Du bist Rabovladelec");
        });

        executor.shutdown();

        executor.submit(() -> {
            System.out.println("Ya Novichok tut");
        });
    }
}