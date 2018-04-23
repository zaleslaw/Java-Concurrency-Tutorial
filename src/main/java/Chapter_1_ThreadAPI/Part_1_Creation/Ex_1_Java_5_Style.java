package Chapter_1_ThreadAPI.Part_1_Creation;

public class Ex_1_Java_5_Style {
    public static void main(String[] args) {
        // First way
        Worker worker = new Worker();
        worker.start();

        // Second way
        Thread t = new Thread() {
            public void run() {
                System.out.println("Anonymous thread is running");
            }
        };

        t.start();

        // Third way
        Thread runnableThread = new Thread(new MyRunnable());
        runnableThread.start();


        // Fourth way
        Runnable myRunnable = new Runnable() {
            public void run() {
                System.out.println("MyRunnable is running");
            }
        };

        Thread thread = new Thread(myRunnable);
        thread.start();
    }
}

class Worker extends Thread {
    public void run() {
        System.out.println("Worker is running");
    }
}


class MyRunnable implements Runnable {
    public void run() {
        System.out.println("MyRunnable is running");
    }
}
