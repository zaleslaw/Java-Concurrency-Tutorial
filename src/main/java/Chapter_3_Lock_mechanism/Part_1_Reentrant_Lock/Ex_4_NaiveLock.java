package Chapter_3_Lock_mechanism.Part_1_Reentrant_Lock;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.SafeAccess;

/**
 * Adds a naive Lock implementation.
 */
public class Ex_4_NaiveLock {
    @SafeAccess
    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        var lock = new NaiveLock();

        var t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++) {
                lock.lock();
                counter++;
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

    private static class NaiveLock {
        private boolean isLocked = false;

        public synchronized void lock() { // enters in monitor on this
            while (isLocked) {
                try {
                    this.wait();
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
