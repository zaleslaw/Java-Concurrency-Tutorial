package Chapter_1_ThreadAPI.Part_7_Join;


/**
 * Q: How to order the manipulation on shared resource?
 * A: With power of .join operator
 */
public class Ex_2_Ordered {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        var t1 = new Thread(() -> {
            System.out.println("Run by: " + Thread.currentThread().getName());
            var t2 = new Thread(() -> {
                System.out.println("Run by: " + Thread.currentThread().getName());
                var t3 = new Thread(() -> {
                    System.out.println("Run by: " + Thread.currentThread().getName());
                    counter++;
                });
                t3.start();
                try {
                    t3.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                counter++;
            });
            t2.start();
            try {
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            counter++;
        });

        t1.start();

        t1.join(); // it's not enough to add only one join here
        counter++;
        System.out.println("Result is " + counter);
    }
}
