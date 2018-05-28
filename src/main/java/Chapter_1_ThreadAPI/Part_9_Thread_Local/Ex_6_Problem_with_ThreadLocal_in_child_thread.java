package Chapter_1_ThreadAPI.Part_9_Thread_Local;

/**
 * A: Use InheritableThreadLocal and when childThread is starting it will copy ThreadLocal value from parentThread.
 * <p>
 * NOTE: Child thread couldn't change parent ThreadLocal value. But it can change its own ThreadLocal value.
 */
public class Ex_6_Problem_with_ThreadLocal_in_child_thread {
    private static InheritableThreadLocal<Integer> counter = new InheritableThreadLocal<>() {
        @Override
        public Integer initialValue() {
            return 0;
        }
    };

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
