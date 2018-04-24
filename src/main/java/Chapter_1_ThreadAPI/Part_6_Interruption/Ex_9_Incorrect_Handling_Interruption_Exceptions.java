package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * Print out the forked thread' states.
 * Q: I'm trying to add Thread.sleep() call. It requires handle checked exceptions. How to handle correctly?
 * A: In example below the t thread is interrupted during the sleep (2 seconds per iteration in t thread and 1 second in main thread).
 *
 * NOTE: in #3 we see RUNNABLE and isInterrupted = false because the in the handling section the interruption status was erased.
 */
public class Ex_9_Incorrect_Handling_Interruption_Exceptions {
    private static int counter = 0;

    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                counter++;
                try {
                    Thread.sleep(2000); // <----- After 1 second this thread will wake up from the sleep
                } catch (InterruptedException e) {
                    System.out.println("#3 " + Thread.currentThread().getName() + ":: interruption status is " + Thread.currentThread().isInterrupted());
                    System.out.println("#4 " + Thread.currentThread().getName() + "::" + Thread.currentThread().getState());
                    e.printStackTrace();
                }
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
