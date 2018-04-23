package Chapter_1_ThreadAPI.Part_5_Thread_Group;

/**
 * ThreadGroup is not the full-time Executor. It's only metainfo storage and a set of old-designed methods to manage active threads.
 */
public class Ex_1_Thread_Group {
    public static void main(String[] args) throws InterruptedException {
        ThreadGroup workers = new ThreadGroup("Workers");

        Thread[] threads = new Thread[100];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(workers, () -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " is part of group" + Thread.currentThread().getThreadGroup().getName());
            });
            threads[i].setName("Worker #" + i);
            threads[i].setDaemon(true);
            threads[i].start();
            Thread.sleep(100);
        }

        while (printOutActiveThreads(workers)) {
        }
    }

    private static boolean printOutActiveThreads(ThreadGroup workers) throws InterruptedException {
        System.out.println("Estimated active count " + workers.activeCount());

        Thread[] tmpSetOfThreads = new Thread[workers.activeCount() * 2]; // maybe amount of workers is increasing

        final int amountOfActiveThreads = workers.enumerate(tmpSetOfThreads);
        System.out.println("Amount of copied active threads " + amountOfActiveThreads);

        for (int i = 0; i < amountOfActiveThreads; i++) {
            if (tmpSetOfThreads[i].getName().equals("Worker #" + 95)) {
                System.out.println("Thread 8 highly likely is active");
            }
        }
        Thread.sleep(20);
        return amountOfActiveThreads > 0;
    }
}
