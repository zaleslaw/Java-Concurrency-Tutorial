package Chapter_9_CompletableFuture;

import java.util.concurrent.*;

/**
 * The Async method without the Executor argument runs a step using the common fork/join pool implementation of Executor
 * that is accessed with the ForkJoinPool.commonPool() method.
 * The Async method with an Executor argument runs a step using the passed Executor.
 */
public class Ex_8_Solution_with_Custom_Executor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        CompletableFuture.supplyAsync(() -> "Sputnik with dogs", executorService)
                .thenApplyAsync(s -> {
                    CompletableFuture.runAsync(()-> {
                        System.out.println("Step 1.1: " + Thread.currentThread().getName());
                        System.out.println("gaff");
                    }, executorService);
                    System.out.println("Step 1.2: " + Thread.currentThread().getName());
                    s+=": Belka";
                    return s;
                }, executorService)
                .thenApplyAsync(s -> {
                    CompletableFuture.runAsync(()-> {
                        System.out.println("Step 2.1: " + Thread.currentThread().getName());
                        System.out.println("gaff");
                    }, executorService);
                    System.out.println("Step 2.2: " + Thread.currentThread().getName());
                    s += " & Strelka ";
                    return s;
                }, executorService)
                .thenAcceptAsync(s -> {
                    CompletableFuture.runAsync(()-> {
                        System.out.println("Step 3.1: " + Thread.currentThread().getName());
                        System.out.println("gaff");
                    }, executorService);
                    System.out.println("Step 3.2: " + Thread.currentThread().getName());
                    System.out.println(s);
                }, executorService);


        executorService.awaitTermination(1, TimeUnit.SECONDS);
        executorService.shutdown();


    }
}
