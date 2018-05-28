package Chapter_3_Lock_mechanism.Part_4_Atomic;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.SafeAccess;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Safety with AtomicInteger counter without additional synchronization.
 */
public class Ex_1_Atomic_Integer {

    @SafeAccess
    private static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        var t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++){
                counter.getAndIncrement();
            }
        });

        var t2 = new Thread(() -> {
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
