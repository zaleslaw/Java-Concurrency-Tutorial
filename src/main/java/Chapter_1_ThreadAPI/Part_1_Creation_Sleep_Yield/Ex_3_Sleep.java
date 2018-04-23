package Chapter_1_ThreadAPI.Part_1_Creation_Sleep_Yield;

/**
 * Could predict the output order of operators?
 */
public class Ex_3_Sleep {
    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(() -> {
            System.out.println("Before sleeping in forked thread");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("After sleeping in forked thread");
        });

        t.start();

        System.out.println("Before sleeping");
        Thread.sleep(1000);
        System.out.println("After sleeping");
    }
}
