package JMM;

public class VolatileTest {
    private static int counter = 0; // add volatile to see the effects

    public static void main(String[] args) {
        var reader = new Thread(()->{
            int copyOfCounter = counter;

            while (counter < 10) { // without volatile it will be cashed in Thread stack
                if (copyOfCounter != counter) { // IDEA thinks that counter is static from iteration to iteration and compiler puts
                    System.out.println("Special Read " + counter + " " + copyOfCounter);
                    copyOfCounter = counter; // without volatile it can reads old values
                }
            }
        });
        reader.start();

        var writer = new Thread(()->{
            while (counter < 10) {
                System.out.println("Will " + (counter + 1));
                counter ++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        writer.start();
    }
}