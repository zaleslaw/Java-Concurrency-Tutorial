package Chapter_1_ThreadAPI.Part_2_Thread_properties;


/**
 * Q: How to set the name in lambda if .this on Thread is not available?
 * <p>
 * A: via Thread.currentThread()...
 * </p>
 */
public class Ex_3_Set_name_in_lambda {
    public static void main(String[] args) {
        new Thread(() -> {
            Thread.currentThread().setName("V.I.P. Lambda in Thread");
            System.out.println("Run by: " + Thread.currentThread().getName());
        }).start();

        System.out.println(Thread.currentThread().getName());
    }
}
