package Chapter_7_ExecutorFramework.Part_2_Stop_Executor_Service;

import java.util.concurrent.Executors;

/**
 * Shutdown() command is waiting for the end of execution for each task submitted before the call of this command.
 */
public class Ex_2_Stop_the_execution_correctly {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println("I'm doing my job, sir!");
        });
        executor.submit(() -> {
            System.out.println("Du bist Rabovladelec");
        });

        executor.shutdown();
    }
}