package ForkJoin;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.RecursiveTask;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * In this class removed stupid fork, added cached map with initial values, moved computation of task 2 before task1.join
 */
public class ImprovedFibonacciTask extends RecursiveTask {

    private Integer fibIndex;
    private static AtomicInteger taskCounter = new AtomicInteger(0);
    Map<Integer, Integer> fibMap = new HashMap<>();

    
    {
        fibMap.put(0,0);
        fibMap.put(1,1);
        fibMap.put(2,1);
        fibMap.put(3,2);
        fibMap.put(4,3);
    }


    public ImprovedFibonacciTask(Integer fibIndex) {
        this.fibIndex = fibIndex;
    }

    public Integer getTaskCounter() {
        return taskCounter.get();
    }

    @Override
    protected Integer compute() {
        if (fibMap.containsKey(fibIndex)){
            return fibMap.get(fibIndex);
        }
        final ImprovedFibonacciTask task1 = new ImprovedFibonacciTask(fibIndex-1);
        task1.fork();
        taskCounter.getAndIncrement();

        final ImprovedFibonacciTask task2 = new ImprovedFibonacciTask(fibIndex-2);
        Integer result2 = task2.compute();// to calculate in current thread
        taskCounter.getAndIncrement();

        Integer result1 = (Integer) task1.join();

        // Move before .join()
/*        final ImprovedFibonacciTask task2 = new ImprovedFibonacciTask(fibIndex-2);
        Integer result2 = task2.compute();
        taskCounter.getAndIncrement();*/

        int result = result1 + result2;

        return result;
    }
}
