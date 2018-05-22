package Chapter_2_Basic_Concurrency.Part_4_Deadlock.StringJokes;

/**
 * Ok, typical deadlock, right?
 */
public class Ex_1_Deadlock_with_different_Strings {

    public static void main(String[] args) {
        String counter1 = "a";
        String counter2 = "b";

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
