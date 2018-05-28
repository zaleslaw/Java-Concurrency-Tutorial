package Chapter_3_Lock_mechanism.Part_1_Reentrant_Lock;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.SafeAccess;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Reentrant Lock has no troubles with taking a lock twice.
 */
public class Ex_5_Solution_with_reentrancy_and_ReentrantLock {
    @SafeAccess
    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        var lock = new ReentrantLock();

        var t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++) {
                lock.lock();
                internalMethod(lock);
                lock.unlock();
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                lock.lock();
                counter--;
                lock.unlock();
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter = " + counter);
    }

    private static void internalMethod(ReentrantLock lock) {
        lock.lock();
        counter++;
        lock.unlock();
    }
}
