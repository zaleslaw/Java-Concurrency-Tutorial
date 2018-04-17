package Old;

/**
 * Remember about order: monitor [and MAIN thread] is awaiting before thread is running. This is a kind of deadlock by your hands.
 */
public class IncorrectWaitNotify_3 {
    static String monitor = "Monitor";
    static Integer counter = 0;

    public static void main(String[] args) throws InterruptedException {

        synchronized (monitor) {
            System.out.println("Waiting for the Child Thread");
            monitor.wait(); // add timeout to avoid deadlocks in production code
            System.out.println(counter);
        }

        var t = new Thread(() -> {
            synchronized (monitor) {
                for (int i = 0; i < 1_000_000; i++) {
                    counter++;
                }
                monitor.notify();
            }
        });

        t.start();
    }
}
