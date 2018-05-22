package Chapter_2_Basic_Concurrency.Part_3_Wait_Notify.Basic;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.SafeAccess;

/**
 * The best idea - move synchronized section on main thread after t.start (t.start hb synchronized)
 */
public class WaitNotify {
    private static String monitor = "Monitor";
    @SafeAccess
    private static Integer counter = 0;

    public static void main(String[] args) throws InterruptedException {

        var t = new Thread(() -> {
            synchronized (monitor){
                for (int i = 0; i < 1_000_000; i++) {
                    counter++;
                }
                monitor.notify();
            }
        });

        t.start();

        synchronized (monitor){
            System.out.println("Waiting for the Child Thread");
            monitor.wait(1000);
            System.out.println(counter);
        }
    }
}