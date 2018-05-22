package Chapter_1_ThreadAPI.Part_2_Thread_properties;


/**
 * Q: How to get the name of thread if link is not accessible (like in main thread)?
 * <p>
 * A: via Thread.currentThread()...
 * </p>
 */
public class Ex_2_Print_out_name {
    public static void main(String[] args) {
        new Thread("V.I.P. Thread") {
            public void run() {
                System.out.println("Run by: " + getName());
            }
        }.start();

        System.out.println(Thread.currentThread().getName());
    }
}
