package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Infinite thread. Let's try to stop it.
 */
public class Ex_1_InfiniteThread {
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
        }
        System.out.println(counter); // This row will never be printed!
    }
}
