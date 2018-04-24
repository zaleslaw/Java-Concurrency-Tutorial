package Chapter_2_Old.Part_3_Wait_Notify.Advanced;


import java.util.ArrayList;

/**
 * A lot of threads are waiting and a lot of threads are blocked if change monitor.notify tp monitor.notifyAll in row::35
 */
public class Ex_3_Lock_contention {
    public static void main(String[] args) throws InterruptedException {
        var monitor = new Object();
        var pool = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            var t = new Thread(() -> {
                synchronized (monitor) {
                    try {
                        monitor.wait();
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            pool.add(t);
        }

        pool.forEach(Thread::start);

        while (true) {
            System.out.println("------- REQUEST STATUSES ------");
            pool.forEach(t -> System.out.println(t.getName() + "::" + t.getState()));
            Thread.sleep(100);
            synchronized (monitor) {
                monitor.notify(); // monitor.notifyAll(); to reach ore blocked threads
            }
        }
    }
}