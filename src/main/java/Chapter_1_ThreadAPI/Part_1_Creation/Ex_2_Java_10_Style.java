package Chapter_1_ThreadAPI.Part_1_Creation;

public class Ex_2_Java_10_Style {
    public static void main(String[] args) {
        // First way
        var worker = new Worker();
        worker.start();

        // Second way
        var t = new Thread(() -> System.out.println("Anonymous thread is running"));
        t.start();

        // Third way
        var runnableThread = new Thread(new MyRunnable());
        runnableThread.start();


        // Fourth way
        Runnable myRunnable = () -> System.out.println("MyRunnable is running"); // Try to add var and remove type!

        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}
