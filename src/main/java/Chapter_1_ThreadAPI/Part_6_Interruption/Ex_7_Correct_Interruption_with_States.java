package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Print out the forked thread' states.
 * Q: What states will be printed in #1 #2, #3?
 */
public class Ex_7_Correct_Interruption_with_States {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counter++;
            }
            System.out.println("It's not immediate interruption!");
            System.out.println("#2 " + Thread.currentThread().getName() + "::" + Thread.currentThread().getState());
        });
        System.out.println("#1 " + t.getName() + "::" + t.getState());
        t.start();

        while (t.isAlive()) {
            System.out.println("It never ends!");
            Thread.sleep(1000);
            t.interrupt();
        }
        System.out.println(counter); // This row will be printed after 2-3 iterations!
        System.out.println("#3 " + t.getName() + "::" + t.getState());
    }
}
