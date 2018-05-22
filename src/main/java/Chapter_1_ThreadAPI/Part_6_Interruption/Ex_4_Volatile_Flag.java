package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Stop flag value will be read in forked thread due to volatile semantics and JMM.
 * Compiler have no power to erase the value or something else.
 */
public class Ex_4_Volatile_Flag {
    private static int counter = 0;
    private static volatile boolean isRunning = true;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (isRunning) { // Strict guarantee to read isRunning = false here
                counter++;
            }
        });
        t.start();

        while (t.isAlive()) {
            System.out.println("It never ends!");
            Thread.sleep(1000);
            isRunning = false;
        }
        System.out.println(counter); // This row will be printed after 2-3 iterations!
    }
}
