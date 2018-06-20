package Chapter_7_ExecutorFramework.Part_1_Executor_Intro;

import java.util.concurrent.Executors;

/**
 * Submit two tasks to execute them.
 * <p>
 * Details of execution depends on ThreadPoolExecutor implementation.
 * <p>
 * NOTE: In this example all tasks are added to the LinkedBlockingQueue<Runnable>() in the internals of ThreadPoolExecutor.
 * Tasks are guaranteed to execute sequentially, and no more than one task will be active at any
 * given time.
 * <p>
 * A: Why does this example not stop?
 * Q: Due to alive threads in executor, you should stop the executor manually.
 */
public class Ex_2_Submit_two_tasks {
    public static void main(String[] args) {
        var executor = Executors.newSingleThreadExecutor();

        executor.submit(() -> {
            System.out.println("I'm doing my job, sir!");
        });
        executor.submit(() -> {
            System.out.println("Du bist Rabovladelec");
        });
    }
}