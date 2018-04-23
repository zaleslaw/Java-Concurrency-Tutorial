package Chapter_1_ThreadAPI.Part_1_Creation_Sleep_Yield;

/**
 * Could we help t2 to run more concurrently?
 * <p>
 * Without yield Thread-0 is executed earlier than Thread-1
 * With yield both threads will be mixed
 * </p>
 */
public class Ex_4_Yield {
    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
                // Thread.yield(); // <===================== Uncomment this
            }
        });
        var t2 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "::" + i);
                // Thread.yield(); // <===================== Uncomment this
            }
        });

        t1.start();
        t2.start();
    }
}
