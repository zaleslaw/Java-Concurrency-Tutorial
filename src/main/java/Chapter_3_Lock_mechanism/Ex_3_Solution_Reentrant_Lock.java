package Chapter_3_Lock_mechanism;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Guard access to shared variable with ReentrantLock
 */
public class Ex_3_Solution_Reentrant_Lock {

    static int counter;

    public static void main(String[] args) throws InterruptedException {

        var lock = new ReentrantLock();

        var t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++){
                lock.lock();
                counter++;
                lock.unlock();
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++){
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

}
