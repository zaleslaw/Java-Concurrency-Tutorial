package Chapter_9_CompletableFuture;


import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


/**
 * It's very easy to make error in jungles of CF's hierarchy
 */
public class Ex_7_Hierarchy_of_CF {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<CompletableFuture<Integer>> cf = CompletableFuture
                .supplyAsync(() -> 1)
                .thenApply(x -> CompletableFuture.supplyAsync(() -> x + 2))
                .thenApply (x -> CompletableFuture.supplyAsync (() -> {
                    try {
                        System.out.println("Wow, I'm here");
                        return x.get() + 3;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        return 1001;
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                        return 1000;
                    }
                }));

        System.out.println(cf.get().get());


        // without error
        CompletableFuture<Integer> combinedCf = CompletableFuture
                .supplyAsync(() -> 1)
                .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 2))
                .thenCompose(x -> CompletableFuture.supplyAsync(() -> x + 3));

        System.out.println(combinedCf.get());

    }
}
