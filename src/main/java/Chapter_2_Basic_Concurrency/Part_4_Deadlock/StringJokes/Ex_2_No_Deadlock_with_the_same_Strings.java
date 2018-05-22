package Chapter_2_Basic_Concurrency.Part_4_Deadlock.StringJokes;

/**
 * Run it and enjoy free deadlock suface.
 * <p>
 * Q: How it can be?
 * A: Synchronization depends on caching of intern value in String type.
 * Both counter1 and counter2 point on the same object which is located in String pool.
 */
public class Ex_2_No_Deadlock_with_the_same_Strings {

    public static void main(String[] args) {
        String counter1 = "a";
        String counter2 = "a";

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
