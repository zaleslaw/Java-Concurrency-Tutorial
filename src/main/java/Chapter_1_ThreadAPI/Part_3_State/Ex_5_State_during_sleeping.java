package Chapter_1_ThreadAPI.Part_3_State;


/**
 * Q: Could we catch up the TIMED_WAITED?
 * A: Play with Thread.sleep()
 */
public class Ex_5_State_during_sleeping {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " :: in State :: " + Thread.currentThread().getState());

        var t = new Thread(() -> {
            final Thread thisThread = Thread.currentThread();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        t.start();
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        Thread.sleep(1000);
        System.out.println(t.getName() + " :: in State :: " + t.getState());
        Thread.sleep(2000);
        System.out.println(t.getName() + " :: in State :: " + t.getState());
    }
}