package Chapter_6_Collections.UnsafeMap;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * It represents HashMap object destroyed by writing from many threads
 */
public class ThreadSafeHashMap {

    public static void main(String[] args) throws InterruptedException {


        Map<Integer, Integer> resource = new HashMap<>();
        ExecutorService pool = Executors.newWorkStealingPool();
        Lock lock = new ReentrantLock();



        for (int i = 0; i < 1000; i++) {
            final int number = i;
            pool.submit(() -> {
                try {
                    Thread.sleep(ThreadLocalRandom.current().nextInt(10));
                    lock.lock();
                    resource.put(number, number);
                    lock.unlock();
                    System.out.println("Thread # " + Thread.currentThread().getName() + " did that");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            });

        }

        try {
            pool.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            pool.shutdownNow();
        }

        resource.forEach((k,v)-> System.out.println(k));
        System.out.println("Size is " + resource.size());

    }
}
