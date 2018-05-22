package Chapter_2_Basic_Concurrency.Part_1_Race_condition;

/**
 * Run this example a few times and you will be surprised with different results
 * <p>
 * In ouput easy to find something like that
 * <p>
 * Thread::Thread-1 enters critical section
 * Thread::Thread-0 enters critical section
 * Thread::Thread-1 leaves critical section
 * Thread::Thread-0 leaves critical section
 * <p>
 * 2 threads in critical section together. it's unappropriated behaviour
 */
public class Ex_4_UnsafeCounter_with_Thread_names {
    @UnsafeAccess
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                System.out.println("Thread::" + Thread.currentThread().getName() + " enters critical section");
                counter++;
                System.out.println("Thread::" + Thread.currentThread().getName() + " leaves critical section");
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                System.out.println("Thread::" + Thread.currentThread().getName() + " enters critical section");
                counter--;
                System.out.println("Thread::" + Thread.currentThread().getName() + " leaves critical section");
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("Counter = " + counter);
    }

}
