package Chapter_2_Old.WaitNotify;


/**
 * This example demostrates the behaviour on monitor.wait() after monitor.notify in the other thread.
 *
 * First of all after monitor.notify will be finished critical section guarded by synchronized where .notify() method was called.
 *
 * Only after that the lock will be released in main thread and will be acquired in the Thread-0.
 *
 * NOTE: Thread.sleep doesn't release the lock!
 */
public class WaitNotifyLockReleasing {
    public static void main(String[] args) throws InterruptedException {
        var monitor = new Object();

        new Thread(()->{
            synchronized (monitor) {
                System.out.println(Thread.currentThread().getName() + ":: is waiting"); // #1
                try {
                    monitor.wait();
                } catch (InterruptedException e) {

                }
                System.out.println(Thread.currentThread().getName() + ":: is ready for battle!"); // #2
            }
        }).start();

        Thread.sleep(1000);

        synchronized (monitor) {
            System.out.println(Thread.currentThread().getName() + ":: is notifying"); // #3
            monitor.notify();
            System.out.println(Thread.currentThread().getName() + ":: is going to sleep again"); // #4
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + ":: is going to leave synchronized block"); // #5
        }



    }
}
