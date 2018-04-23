package Chapter_9_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Add custom executor
 * But it doesn't work correctly
 * As we see only commonPool is used for async
 */
public class Ex_8_Problem_with_Custom_Executor {
    public static void main(String[] args) throws ExecutionException, InterruptedException {


        final ExecutorService executorService = Executors.newFixedThreadPool(1);
        CompletableFuture.supplyAsync(() -> "Sputnik with dogs", executorService)
                .thenApplyAsync(s -> {
                    CompletableFuture.runAsync(()-> {
                        System.out.println("Step 1.1: " + Thread.currentThread().getName());
                        System.out.println("gaff");
                    });
                    System.out.println("Step 1.2: " + Thread.currentThread().getName());
                    s+=": Belka";
                    return s;
                })
                .thenApplyAsync(s -> {
                    CompletableFuture.runAsync(()-> {
                        System.out.println("Step 2.1: " + Thread.currentThread().getName());
                        System.out.println("gaff");
                    });
                    System.out.println("Step 2.2: " + Thread.currentThread().getName());
                    s += " & Strelka ";
                    return s;
                })
                .thenAcceptAsync(s -> {
                    CompletableFuture.runAsync(()-> {
                        System.out.println("Step 3.1: " + Thread.currentThread().getName());
                        System.out.println("gaff");
                    });
                    System.out.println("Step 3.2: " + Thread.currentThread().getName());
                    System.out.println(s);
                });


        Thread.sleep(5000);


    }
}
