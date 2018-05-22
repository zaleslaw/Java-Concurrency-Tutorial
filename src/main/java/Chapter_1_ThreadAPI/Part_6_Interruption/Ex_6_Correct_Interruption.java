package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Check interruption status as a flag from previous examples.
 * <p>
 * It's only flag without internal .stop() call. You can finish your actions and release resources if required after "interruption".
 */
public class Ex_6_Correct_Interruption {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counter++;
            }
            System.out.println("It's not immediate interruption!");
        });
        t.start();

        while (t.isAlive()) {
            System.out.println("It never ends!");
            Thread.sleep(1000);
            t.interrupt();
        }
        System.out.println(counter); // This row will be printed after 2-3 iterations!
    }
}
