package CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created by Alexey_Zinovyev on 08-May-17.
 */
public class Ex_3_SupplyAsync {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Step 1: Return 42
        CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() -> 42);
        System.out.println(cf.get());

        cf = CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 42;
                }
        );
        System.out.println(cf.get());

        // Step 2; Add more calculations
        cf = CompletableFuture.supplyAsync(() -> 1)
                .thenApply(x -> x + 1)
                .thenApply(x -> x * 10);

        System.out.println(cf.get());


        cf = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 1;
                })
                .thenApplyAsync(x -> {
                    System.out.println(Thread.currentThread().getName());
                    return x + 1;
                })
                .thenApply(x -> {
                    System.out.println(Thread.currentThread().getName());
                    return x * 10;
                });

        System.out.println(cf.get());
    }
}
