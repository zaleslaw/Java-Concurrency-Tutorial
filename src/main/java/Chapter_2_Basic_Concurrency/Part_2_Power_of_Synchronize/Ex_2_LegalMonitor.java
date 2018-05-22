package Chapter_2_Basic_Concurrency.Part_2_Power_of_Synchronize;

/**
 * Make breakpoints at #1
 * "main@1" prio=5 tid=0x1 nid=NA runnable
 *   java.lang.Thread.State: RUNNABLE
 * 	  at Old.monitor.Ex_2_LegalMonitor.main(Ex_2_LegalMonitor.java:16)
 * 	  - locked <0x29e> (a Old.monitor.Ex_2_LegalMonitor$Monitor)
 *
 *
 * Make breakpoint at #2
 *
 * "main@1" prio=5 tid=0x1 nid=NA runnable
 *   java.lang.Thread.State: RUNNABLE
 * 	  at Old.monitor.Ex_2_LegalMonitor.main(Ex_2_LegalMonitor.java:23)
 *
 * Nothing is locked
 */
public class Ex_2_LegalMonitor {
    public static void main(String[] args) throws InterruptedException {
        Monitor monitor = new Monitor();

        synchronized (monitor){
            System.out.println("Hello from blocking!"); // #1 See the dump of the main thread
            monitor.notify();
        }
        System.out.println("Hello from the free world"); // #2 See the dump of the main thread
    }

    private static class Monitor extends Object {};
}
