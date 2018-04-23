package Chapter_9_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Let's combine Soyuz and Apollo together
 */
public class Ex_10_Only_all_together {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1
                = CompletableFuture.supplyAsync(() -> "Soyuz 19");
        CompletableFuture<String> future2
                = CompletableFuture.supplyAsync(() -> "Apollo 18");
        CompletableFuture<String> future3
                = CompletableFuture.supplyAsync(() -> "UFO");


        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(future1, future2, future3);
        System.out.println(combinedFuture.get());
        //strange method, yeah


        // use method join and Stream API to join all results together
        // join() is the same like get() but has other behavior with errors
        String combined = Stream.of(future1, future2, future3)
                .map(CompletableFuture::join)
                .collect(Collectors.joining(" "));
        System.out.println(combined);


    }
}
