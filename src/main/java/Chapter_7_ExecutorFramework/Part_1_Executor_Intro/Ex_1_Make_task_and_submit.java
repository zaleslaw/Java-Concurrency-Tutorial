package Chapter_7_ExecutorFramework.Part_1_Executor_Intro;

import java.util.concurrent.Executors;

/**
 * Make task and submit it to run it immediately.
 */
public class Ex_1_Make_task_and_submit {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        Runnable task = () -> {
            System.out.println("I'm doing my job, sir!");
        };

        executor.submit(task);
    }
}