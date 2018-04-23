package Chapter_4_JMM;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Before-happens exists between #1, #2, #3, #4, #5, #6 due to Program order
 * also between lambda#1, lambda#2 due to Program Order
 *
 * #2 < lambda#1  due to Start Rule
 * lambda #2 < #5 due to Termination rule
 *
 * #3,#4,lambda#1, lambda#2 could be printed in a few different ways
 *
 * Change sleep time to reach different print paths
 *
 */
public class Ex_1_Thread_start_rule {
    public static void main(String[] args) throws InterruptedException {


        final Runnable lambda = () -> {
            System.out.println("Hi from lambda"); // lambda #1
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(500));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("How are you?");   // lambda #2
        };
        Thread t = new Thread(lambda);


        System.out.println("Step 1");  //#1

        t.start();//#2

        System.out.println("Step 2");  //#3

        Thread.sleep(ThreadLocalRandom.current().nextInt(100));

        System.out.println("Step 3");  //#4

        t.join();                      //#5

        System.out.println("Step 4");  //#6

    }
}
