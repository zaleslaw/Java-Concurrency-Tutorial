package Chapter_1_ThreadAPI.Part_2_Thread_properties;


/**
 * Q: How to get the id in lambda if .this on Thread is not available?
 * <p>
 * A: via Thread.currentThread()...
 * </p>
 */
public class Ex_4_Read_the_id {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Run by: " + Thread.currentThread().getId());
        }).start();

        System.out.println(Thread.currentThread().getId());
    }
}
