package Chapter_1_ThreadAPI.Part_3_State;


/**
 * Q: Could we call restart the TERMINATED thread?
 * <p>
 * A: No compilation error, but java.lang.IllegalThreadStateException
 * </p>
 * It's impossible due to documentation and source code.
 */
public class Ex_4_Restart {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " :: in State :: " + Thread.currentThread().getState());

        var t = new Thread(() -> {
            final Thread thisThread = Thread.currentThread();
            System.out.println(thisThread.getName() + " :: in State :: " + thisThread.getState());
        });
        t.start();

        Thread.sleep(1000);
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        t.start();
    }
}