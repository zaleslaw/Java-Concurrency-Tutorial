package JMM;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Before-happens exists between #1, #2, #3, #4, #5, #6 due to Program order
 * also between lambda#1, lambda#2 due to Program Order
 *
 * #2 < lambda#1  due to Start Rule
 * lambda #2 < #5 due to Termination rule
 *
 * Let's add lock and take it in lambda and in main thread
 *
 * #3 < lambda #1 < lambda#2 < #4
 *
 */
public class Ex_2_Lock_Rule {
    public static void main(String[] args) throws InterruptedException {



        Lock lock = new ReentrantLock();

        final Runnable lambda = () -> {
            lock.lock();
            System.out.println("Hi from lambda"); // lambda #1
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("How are you?");   // lambda #2
            lock.unlock();
        };
        Thread t = new Thread(lambda);



        System.out.println("Step 1");  //#1
        lock.lock();
        t.start();//#2

        System.out.println("Step 2");  //#3

        Thread.sleep(ThreadLocalRandom.current().nextInt(100));

        System.out.println("Step 3");  //#4

        lock.unlock();

        t.join();                      //#5
        // lock.unlock(); // wrong place for lock
        System.out.println("Step 4");  //#6

    }
}
