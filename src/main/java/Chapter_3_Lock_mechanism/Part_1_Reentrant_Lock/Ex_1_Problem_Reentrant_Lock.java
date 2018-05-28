package Chapter_3_Lock_mechanism.Part_1_Reentrant_Lock;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.UnsafeAccess;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Guard access to shared variable with ReentrantLock.
 *
 * Q: Why does it not work properly?
 * A: Due to two lock objects.
 */
public class Ex_1_Problem_Reentrant_Lock {
    @UnsafeAccess
    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        var lock1 = new ReentrantLock();
        var lock2 = new ReentrantLock();

        var t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++){
                lock1.lock();
                counter++;
                lock1.unlock();
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++){
                lock2.lock();
                counter--;
                lock2.unlock();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter = " + counter);
    }
}