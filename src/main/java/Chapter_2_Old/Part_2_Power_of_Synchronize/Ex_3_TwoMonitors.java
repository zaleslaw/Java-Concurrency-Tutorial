package Chapter_2_Old.Part_2_Power_of_Synchronize;

/**
 * See the power of reentrant locks.
 *
 * Dump of the main thread is
 *
 * "main@411" prio=5 tid=0x1 nid=NA runnable
 *   java.lang.Thread.State: RUNNABLE
 * 	  at Old.monitor.Ex_3_TwoMonitors.main(Ex_3_TwoMonitors.java:14)
 * 	  - locked <0x29e> (a Old.monitor.Ex_3_TwoMonitors$Monitor)
 * 	  - locked <0x29f> (a Old.monitor.Ex_3_TwoMonitors$Monitor)
 */
public class Ex_3_TwoMonitors {
    public static void main(String[] args) throws InterruptedException {
        Monitor m1 = new Monitor();
        Monitor m2 = new Monitor();

        synchronized (m1){
            synchronized (m2){
                synchronized (m1){
                    synchronized (m2){
                        System.out.println("Hello from blocking!"); // #1 See the dump of the main thread
                    }
                }
            }
        }

        System.out.println("Hello from the free world"); // #2 See the dump of the main thread
    }

    private static class Monitor extends Object {};
}
