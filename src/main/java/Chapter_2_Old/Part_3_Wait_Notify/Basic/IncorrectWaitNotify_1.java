package Chapter_2_Old.Part_3_Wait_Notify.Basic;


import Chapter_2_Old.Part_1_Race_condition.UnsafeAccess;

/**
 * Add synchronization on counter (to make it safe)
 */
public class IncorrectWaitNotify_1 {
    private static String monitor = "Monitor";
    @UnsafeAccess
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("Waiting for the Child Thread");
        monitor.wait();
        System.out.println(counter);

        var t = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter++;
            }
            monitor.notify();
        });

        t.start();
    }
}
