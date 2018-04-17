package Old;

public class WaitNotify {
    static String monitor = "Monitor";
    static Integer counter = 0;

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

        t.join();
    }
}
