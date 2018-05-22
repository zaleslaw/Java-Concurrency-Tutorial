package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Q: I hear that .interrupt) can stop the thread. Why does it not work?
 * <p>
 * A: You should handle the interruption signal inside the interrupted thread. It's like the volatile flag with special API.
 * </p>
 */
public class Ex_5_Incorrect_Interruption {
    private static int counter = 0;
    private static volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (true) {
                counter++;
            }
        });
        t.start();

        while (t.isAlive()) {
            System.out.println("It never ends!");
            Thread.sleep(1000);
            t.interrupt();
        }
        System.out.println(counter); // This row will never be printed!
    }
}
