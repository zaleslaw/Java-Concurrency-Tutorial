package Chapter_1_ThreadAPI.Part_6_Interruption;


/**
 * The t thread should be interrupted again in catch section.
 * <p>
 * The same handling should be added for the wait operation and so on.
 */
public class Ex_10_Correct_Handling_Interruption_Exceptions {
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
                    System.out.println(e);
                    Thread.currentThread().interrupt();
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
