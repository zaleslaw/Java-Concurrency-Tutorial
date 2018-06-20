package Chapter_7_ExecutorFramework.Part_2_Stop_Executor_Service;

import java.util.concurrent.Executors;

/**
 * A: Why were my tasks not executed?
 * Q: Due to halting born by shutdownNow command.
 * <p>
 * NOTE: Also shutdownNow returns the list of non-executed tasks.
 */
public class Ex_1_Stop_the_execution {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println("I'm doing my job, sir!");
        });
        executor.submit(() -> {
            System.out.println("Du bist Rabovladelec");
        });

        var tasks = executor.shutdownNow();

        tasks.forEach(System.out::println);
    }
}