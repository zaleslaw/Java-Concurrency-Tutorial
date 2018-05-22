package Chapter_2_Basic_Concurrency.Part_2_Power_of_Synchronize;

/**
 * It doesn't work without synchronized.
 *
 * Don't call these methods without blocking.
 */
public class Ex_1_IllegalMonitor {
    public static void main(String[] args) throws InterruptedException {
        Object monitor = new Object();
        //monitor.notify();
        monitor.wait();
    }
}
