package Chapter_2_Basic_Concurrency.Part_3_Wait_Notify.Advanced;


/**
 * This example shows that after wait() method the data which should be guarded by lock (we mutate it in synchronized section only) was changed!!!!
 */
public class Ex_2_Wait_is_surpised_with_the_changed_data {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var monitor = new Object();

        new Thread(()->{
            synchronized (monitor) {
                System.out.println(Thread.currentThread().getName() + ":: is waiting");
                int localCounter = counter;
                System.out.println("Local counter is " + localCounter);
                try {
                    monitor.wait();
                } catch (InterruptedException ignored) {

                }
                System.out.println(counter == localCounter); // WTF??? Data guarded by lock was changed and we can lose update from the ain thread in row #1
                System.out.println("Counter is " + counter);
                counter = localCounter + 1; //#1
            }
        }).start();

        Thread.sleep(1000);

        synchronized (monitor) {
            System.out.println(Thread.currentThread().getName() + ":: is notifying");
            monitor.notify();
            counter++;
            System.out.println(Thread.currentThread().getName() + ":: is added 1 to counter");
            Thread.sleep(2000);
        }
    }
}