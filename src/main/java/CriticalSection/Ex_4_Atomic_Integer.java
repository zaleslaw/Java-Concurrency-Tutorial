package CriticalSection;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Guard access to shared variable with ReentrantLock
 */
public class Ex_4_Atomic_Integer {

    static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++){
                counter.getAndIncrement();
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++){
                counter.getAndDecrement();
            }

        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter = " + counter);
    }

}
