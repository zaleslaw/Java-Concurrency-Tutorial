package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Stop flag doesn't grow to the forked thread due to busy work on main thread. It happens sometimes due to JIT optimizations and so on.
 */
public class Ex_3_Flag {
    private static int counter = 0;
    private static boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (isRunning) { // No guarantee to read isRunning = false here
                counter++;
            }
        });
        t.start();

        while (t.isAlive()) {
            System.out.println("It never ends!");
            Thread.sleep(1000);
            isRunning = false;
        }
        System.out.println(counter); // This row will be not printed SOMETIMES!
    }
}
