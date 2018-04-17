package Old;


/**
 * Synchronize on correct monitor: use variable Monitor
 */
public class IncorrectWaitNotify_2 {
    static String monitor = "Monitor";
    static Integer counter = 0;

    public static void main(String[] args) throws InterruptedException {

        synchronized (counter) {
            System.out.println("Waiting for the Child Thread");
            monitor.wait();
            System.out.println(counter);
        }

        var t = new Thread(() -> {
            synchronized (counter) {
                for (int i = 0; i < 1_000_000; i++) {
                    counter++;
                }
                monitor.notify();
            }
        });

        t.start();
    }
}
