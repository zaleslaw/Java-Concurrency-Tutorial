package Chapter_3_CriticalSection;

/**
 * Guard access to shared variable with synchronized section
 * Counter is 0
 */
public class Ex_2_Synchronized {

    static int counter;

    public static void main(String[] args) throws InterruptedException {

        Object lock = new Object(); // We are using trivial object to enjoy its monitor's power as a lock

        Thread t1 = new Thread(() -> {

            for (int i = 0; i < 1_000_000; i++)
                synchronized (lock){
                    counter++;
                }

        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1_000_000; i++)
                synchronized (lock){
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
