package Chapter_7_ExecutorFramework;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


/**
 * Sleep is interrupted!
 */
public class ExecutorSample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Runnable task1 = () -> {
            System.out.println("Hello from task 1 " + Thread.currentThread().getName());
        };
        Runnable task2 = () -> {
            try {
                Thread.sleep(4000);
                System.out.println("Hello from task 2 " + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };


        executor.submit(task1);
        executor.submit(task2);
        try {
            System.out.println("Trying to shutdown");
            executor.shutdown();
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.err.println("Task was interrupted");
        } finally {
            if(!executor.isTerminated()){
                System.out.println("Start tasks cancellation");
            }
            executor.shutdownNow();
            System.out.println("Pool is dead!");
        }




    }
}