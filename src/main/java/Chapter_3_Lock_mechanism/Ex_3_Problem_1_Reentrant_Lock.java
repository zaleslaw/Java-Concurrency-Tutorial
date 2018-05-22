package Chapter_3_Lock_mechanism;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Guard access to shared variable with ReentrantLock
 * But it doesn't work due to two different locks
 */
public class Ex_3_Problem_1_Reentrant_Lock {

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
