package Chapter_2_Basic_Concurrency.Part_4_Deadlock.StringJokes;

/**
 * Ok, if we will control our object creation we will catch up the deadlock again.
 * <p>
 * Please, AVOID SYNCHRONIZATION ON STRINGS
 */
public class Ex_3_Deadlock_withthe_same_Strings {

    public static void main(String[] args) {
        String counter1 = new String("a"); // <-- IDEA tips can change thw concurrent behaviour of our program, wow!
        String counter2 = new String("a");

        var t1 = new Thread(() -> {
            synchronized (counter1) {
                System.out.println("Lock counter1 in thread " + Thread.currentThread().getId());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (counter2) {
                    System.out.println("Lock counter2 in thread " + Thread.currentThread().getId());
                }
            }


        });

        var t2 = new Thread(() -> {
            synchronized (counter2) {
                System.out.println("Lock counter2 in thread " + Thread.currentThread().getId());
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (counter1) {
                    System.out.println("Lock counter1 in thread " + Thread.currentThread().getId());
                }
            }


        });


        t1.start();
        t2.start();
    }
}
