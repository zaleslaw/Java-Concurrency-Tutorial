package Chapter_2_Basic_Concurrency.Part_4_Deadlock.IntegerJokes;

/**
 * Deadlock due to limitation of Integer pool.
 */
public class Ex_2_Deadlock_with_same_integers {

    public static void main(String[] args) {
        Integer counter1 = 1000;
        Integer counter2 = 1000;

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
