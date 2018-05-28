package Chapter_3_Lock_mechanism.Part_1_Reentrant_Lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Guard access to shared variable with ReentrantLock
 *
 * Q: Why have this program not finish yet?
 * A: But it doesn't work due to forgotten unlock
 * Thread t1 is waiting on #1
 *
 * If you make ThreadDump you can get something like below
 *
 * "Thread-0" #13 prio=5 os_prio=0 tid=0x000000002b928000 nid=0xe68 waiting on condition  [0x000000002c6df000]
 *    java.lang.Thread.State: WAITING (parking)
 * 	at jdk.internal.misc.Unsafe.park(java.base@10/Native Method)
 */
public class Ex_2_Problem_Reentrant_Lock {

    private static int counter;

    public static void main(String[] args) throws InterruptedException {

        var lock = new ReentrantLock();

        var t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++){
                lock.lock();  //#1
                counter++;
                lock.unlock();
            }
        });

        var t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++){
                lock.lock();
                counter--;
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter = " + counter);
    }

}
