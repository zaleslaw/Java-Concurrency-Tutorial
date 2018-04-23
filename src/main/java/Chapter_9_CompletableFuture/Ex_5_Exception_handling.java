package Chapter_9_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Subscribe on Belka & Strelka events
 * Don't forget print our Thread names
 *
 * Sometimes nothing printed or second part are not printed due to damon nature of run threads in CF
 * Yes, we haven't .get() calls like in previous examples
 */
public class Ex_5_Exception_handling {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(() -> "Wernher von Braun")
                .thenApplyAsync(s -> "Built V-2")
                .thenApplyAsync(s -> "Launched V-2 in London")
                .thenApplyAsync(s -> {
                    throw new RuntimeException("Nazi base is broken be Soviet partizanen");
                })
                // starts new CF to handle exception
                .exceptionally(throwable -> {
                    System.out.println("This is new chance");
                    throwable.printStackTrace();
                    return "Wernher von Braun";
                }).thenAccept(s -> System.out.println(s + " had continued his work on space program"));

                Thread.sleep(1000);
    }
}
