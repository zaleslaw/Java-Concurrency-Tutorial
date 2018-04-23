package Chapter_3_CriticalSection;

/**
 * Run this example a few times and you will be surprised with different results
 */
public class Ex_1_UnsafeCounter {

    static int counter;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++)
                counter++;
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++)
                counter--;
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter = " + counter);
    }

}
