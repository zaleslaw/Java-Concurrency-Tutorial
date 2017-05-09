package ForkJoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinSample {
    public static final Integer FIB_INDEX = 20;

    public static void main(String[] args) {
        final ForkJoinPool pool = new ForkJoinPool();

        //final ForkJoinPool pool = new ForkJoinPool(4);
        //final ForkJoinPool pool = new ForkJoinPool(4, ForkJoinPool.defaultForkJoinWorkerThreadFactory, null,false);
        FibonacciTask task = new FibonacciTask(FIB_INDEX);

        // Step 2: Change task type
        //ImprovedFibonacciTask task = new ImprovedFibonacciTask(FIB_INDEX);

        long startTime = System.currentTimeMillis();
        Integer fibNumber = (Integer) pool.invoke(task);


        System.out.println("Fib number " + fibNumber + " with index " + FIB_INDEX);
        System.out.println("Spent time: " + (System.currentTimeMillis() - startTime));
        System.out.println("# of tasks " + task.getTaskCounter());

    }
}
