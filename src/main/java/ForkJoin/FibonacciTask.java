package ForkJoin;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

public class FibonacciTask extends RecursiveTask {

    private Integer fibIndex;
    private static AtomicInteger taskCounter = new AtomicInteger(0);



    public FibonacciTask(Integer fibIndex) {
        this.fibIndex = fibIndex;
    }

    public static Integer getTaskCounter() {
        return taskCounter.get();
    }

    @Override
    protected Integer compute() {
        if(fibIndex == 0) {
            return 0;
        }
        if (fibIndex == 1){
            return 1;
        }
        final FibonacciTask task1 = new FibonacciTask(fibIndex-1);
        task1.fork();
        taskCounter.getAndIncrement();
        Integer result1 = (Integer) task1.join();

        final FibonacciTask task2 = new FibonacciTask(fibIndex-2);
        Integer result2 = task2.compute();
        taskCounter.getAndIncrement();

        int result = result1 + result2;
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Intermediate result is " + result + " from thread " + Thread.currentThread().getName());
        return result;
    }
}
