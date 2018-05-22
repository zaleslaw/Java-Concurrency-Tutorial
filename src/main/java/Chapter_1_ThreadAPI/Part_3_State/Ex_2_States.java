package Chapter_1_ThreadAPI.Part_3_State;


/**
 * Q: How to get state?
 * <p>
 * A: via .getState()
 * </p>
 * Q: How to catch up other states?
 * Hint: We need to block our custom thread with wat/notify communication model for example
 */
public class Ex_2_States {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " :: in State :: " + Thread.currentThread().getState());

        var t = new Thread(() -> {
            final Thread thisThread = Thread.currentThread();
            System.out.println(thisThread.getName() + " :: in State :: " + thisThread.getState());
        });

        System.out.println(t.getName() + " :: in State :: " + t.getState());
        t.start();
        System.out.println(t.getName() + " :: in State :: " + t.getState());

        Thread.sleep(1000);

        System.out.println(t.getName() + " :: in State :: " + t.getState());
    }
}