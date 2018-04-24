package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Stop method works. Don't use 'em.
 */
public class Ex_2_Stop_Thread {
    private static int counter = 0;

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
            t.stop();
        }
        System.out.println(counter); // This row will be printed after 2-3 iterations
    }
}
