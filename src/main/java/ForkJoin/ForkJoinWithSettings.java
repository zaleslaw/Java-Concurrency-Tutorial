package ForkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class ForkJoinWithSettings {
    public static final Integer FIB_INDEX = 15;

    public static void main(String[] args) throws InterruptedException {
        final ForkJoinPool pool = new ForkJoinPool(20);

        FibonacciTask task = new FibonacciTask(FIB_INDEX);

        pool.execute(task);

        do {
            System.out.println("Parallelism is " + pool.getParallelism());
            System.out.println("Pool size is " + pool.getPoolSize());
            System.out.println("Active thread count is " + pool.getActiveThreadCount());
            System.out.println("# of task in queue  is " + pool.getQueuedTaskCount());
            Thread.sleep(1000);
        } while (!task.isDone());


        System.out.println("# of tasks " + task.getTaskCounter());


        pool.shutdown();

    }
}
