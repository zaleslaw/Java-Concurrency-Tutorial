package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Make new calculations after interruption.
 * <p>
 * Here the interruption mechanism is the simplest signal between two threads.
 */
public class Ex_8_Long_Live_Interrupted_Thread {
    private static int counter1 = 0;
    private static int counter2 = 0;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (!Thread.interrupted()) {  // special operator to refresh interruption status.
                counter1++;
            }
            System.out.println("I was interrupted, but my current interruption status is " + Thread.currentThread().isInterrupted());
            while (!Thread.currentThread().isInterrupted()) {
                counter2++;
            }
        });
        t.start();


        Thread.sleep(1000);
        t.interrupt();
        System.out.println("t is interrupted " + t.isInterrupted());
        System.out.println(counter1 + " " + counter2);

        Thread.sleep(1000);
        t.interrupt();
        System.out.println("t is interrupted " + t.isInterrupted());
        System.out.println(counter1 + " " + counter2);

    }
}
