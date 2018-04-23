package Chapter_1_ThreadAPI.Part_7_Join;


/**
 * Q: How to get the id in lambda if .this on Thread is not available?
 * <p>
 * A: via Thread.currentThread()...
 * </p>
 */
public class Ex_1_Join {
    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println("Run by: " + Thread.currentThread().getId());
        }).start();

        System.out.println(Thread.currentThread().getId());
    }
}
