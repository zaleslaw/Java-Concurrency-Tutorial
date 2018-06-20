package Chapter_7_ExecutorFramework;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * This example lights role of schedule*** method as a submit method
 */
public class PingSample {
    public static void main(String[] args) {
        ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);

        //Step 1: schedule 1 event
        pool.schedule(() -> System.out.println("Ping google.com"), 2, TimeUnit.SECONDS);
        //Step 2: schedule repeatable event each second
        pool.scheduleAtFixedRate(() -> System.out.println("Ping yandex.ru"), 0, 2, TimeUnit.SECONDS);
        //Step 3: schedule repeatable event with delay 1 second between events
        pool.scheduleWithFixedDelay(() -> System.out.println("Ping amazon.com"), 3, 2, TimeUnit.SECONDS);

        try {
            pool.awaitTermination(15, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdownNow();
        }
    }
}
