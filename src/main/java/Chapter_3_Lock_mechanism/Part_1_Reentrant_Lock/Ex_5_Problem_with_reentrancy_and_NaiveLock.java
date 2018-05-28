package Chapter_3_Lock_mechanism.Part_1_Reentrant_Lock;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.SafeAccess;

/**
 * Let's try to take a lock twice (around method and in method body, for example).
 * <p>
 * Q: Why have the program not finished yet?
 * A: Due to waiting on this.wait in lock method body.
 * <p>
 * If you want t take a lock twice or more it should has to be reentrant (support this ability).
 */
public class Ex_5_Problem_with_reentrancy_and_NaiveLock {
    @SafeAccess
    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        var lock = new NaiveLock();

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

    private static void internalMethod(NaiveLock lock) {
        lock.lock();
        counter++;
        lock.unlock();
    }

    private static class NaiveLock {
        private boolean isLocked = false;

        public synchronized void lock() { // enters in monitor on this
            while (isLocked) {
                try {
                    this.wait(); // Thread-0 is waiting here! But Thread-1 is waiting too! Both are waiting here.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            isLocked = true;
        } // leaves in monitor on this

        public synchronized void unlock() { // enters in monitor on this
            isLocked = false;
            this.notify();
        } // leaves in monitor on this
    }
}
