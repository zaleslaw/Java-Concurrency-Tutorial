package Chapter_9_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Help Sputnik to reach upon 8 km/s
 * Map operations with CF
 * Don't forget print our Thread names
 */
public class Ex_3_Map_Operations {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // Step 1: Return String
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "Sputnik");
        System.out.println(cf.get());

        cf = CompletableFuture.supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return "Sputnik";
                }
        );
        System.out.println(cf.get());

        // Step 2; Add more calculations
        CompletableFuture<Integer> speed = CompletableFuture.supplyAsync(() -> 1)
                .thenApplyAsync(x -> x * 7)
                .thenApply(x -> x + 1);

        System.out.println(speed.get());


        speed = CompletableFuture
                .supplyAsync(() -> {
                    System.out.println(Thread.currentThread().getName());
                    return 1;
                })
                .thenApplyAsync(x -> {
                    System.out.println(Thread.currentThread().getName());
                    return x * 7;
                })
                .thenApply(x -> {
                    System.out.println(Thread.currentThread().getName());
                    return x + 1;
                });

        System.out.println(speed.get());
    }
}
