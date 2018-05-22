package Chapter_1_ThreadAPI.Part_7_Join;


/**
 * Q: How to order the manipulation on shared resource?
 * A: With power of .join operator
 */
public class Ex_1_Disordered {
    private static int counter;

    public static void main(String[] args) {
        var t1 = new Thread(() -> {
            System.out.println("Run by: " + Thread.currentThread().getName());
            var t2 = new Thread(() -> {
                System.out.println("Run by: " + Thread.currentThread().getName());
                var t3 = new Thread(() -> {
                    System.out.println("Run by: " + Thread.currentThread().getName());
                    counter++;
                });
                t3.start();
                counter++;
            });
            t2.start();
            counter++;
        });

        t1.start();

        counter++;
        System.out.println("Result is " + counter);
    }
}
