package Chapter_1_ThreadAPI.Part_9_Thread_Local;

/**
 * Q: How to join counter result from childThread and parentThread?
 * How to share the same local variable between parent and all child threads?
 */
public class Ex_5_Problem_with_ThreadLocal_in_child_thread {
    private static ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);

    public static void main(String[] args) throws InterruptedException {
        var parentThread = new Thread(() -> {
            try {


                for (int i = 0; i < 1_000; i++) {
                    counter.set(counter.get() + 1);
                }
                System.out.println("Thread " + Thread.currentThread().getId() + " counted before " + counter.get());

                var childThread = new Thread(() -> {
                    for (int i = 0; i < 2_000; i++) {
                        counter.set(counter.get() + 1);
                    }
                    System.out.println("Thread " + Thread.currentThread().getId() + " counted before " + counter.get());
                });

                childThread.start();
                childThread.join();

                System.out.println("Thread " + Thread.currentThread().getId() + " counted before " + counter.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        parentThread.start();
        parentThread.join();


        for (int i = 0; i < 1_000; i++) {
            counter.set(counter.get() - 1);
        }

        System.out.println("Thread " + Thread.currentThread().getId() + " counted before " + counter.get());
    }
}
