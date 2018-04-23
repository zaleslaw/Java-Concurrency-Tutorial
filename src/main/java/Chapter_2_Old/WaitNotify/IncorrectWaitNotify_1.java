package Chapter_2_Old.WaitNotify;


/**
 * Add synchronization on counter (to make it safe)
 */
public class IncorrectWaitNotify_1 {
    static String monitor = "Monitor";
    static int counter = 0;

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
