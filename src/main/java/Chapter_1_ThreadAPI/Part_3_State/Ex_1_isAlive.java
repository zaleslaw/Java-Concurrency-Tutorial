package Chapter_1_ThreadAPI.Part_3_State;


/**
 * Q: How to get status quickly?
 * <p>
 * A: via .isAlive()
 * </p>
 * Q: Could you predict true or false in each case?
 * Hint: from .start() call before end of run method (lambda body) it's true. Outside is false.
 */
public class Ex_1_isAlive {
    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            final Thread thisThread = Thread.currentThread();

            System.out.println("Run by: " + thisThread.getName());
            System.out.println("#1 Is alive " + thisThread.isAlive());
        });

        System.out.println("#2 Is alive " + t.isAlive());
        t.start();
        System.out.println("#3 Is alive " + t.isAlive());

        Thread.sleep(1000);

        System.out.println("#4 Is alive " + t.isAlive());
    }
}