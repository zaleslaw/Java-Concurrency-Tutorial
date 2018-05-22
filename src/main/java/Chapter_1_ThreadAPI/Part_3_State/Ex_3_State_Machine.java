package Chapter_1_ThreadAPI.Part_3_State;


/**
 * Q: Could we call .start twice?
 * <p>
 * A: No compilation error, but java.lang.IllegalThreadStateException
 * Exception in thread "main" Thread-0 :: in State :: RUNNABLE
 * </p>
 * It's impossible due to documentation and source code.
 */
public class Ex_3_State_Machine {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " :: in State :: " + Thread.currentThread().getState());

        var t = new Thread(() -> {
            final Thread thisThread = Thread.currentThread();
            System.out.println(thisThread.getName() + " :: in State :: " + thisThread.getState());
        });
        t.start();
        t.start();
    }
}