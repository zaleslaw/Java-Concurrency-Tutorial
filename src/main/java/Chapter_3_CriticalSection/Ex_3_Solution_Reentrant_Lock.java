package Chapter_3_CriticalSection;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Guard access to shared variable with ReentrantLock
 */
public class Ex_3_Solution_Reentrant_Lock {

    static int counter;

    public static void main(String[] args) throws InterruptedException {

        Lock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++){
                lock.lock();
                counter++;
                lock.unlock();
            }
        });

        Thread t2 = new Thread(() -> {
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
