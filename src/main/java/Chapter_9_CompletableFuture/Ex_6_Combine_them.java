package Chapter_9_CompletableFuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Help to Korolev pick up Yuri from backups for Vostok-1
 */
public class Ex_6_Combine_them {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture<Void> result = CompletableFuture.supplyAsync(() -> "Yuri Gagarin")
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> "Gherman Titov"), Ex_6_Combine_them::pickYura)
                .thenCombineAsync(CompletableFuture.supplyAsync(() -> "Grigori Nelyubov"), Ex_6_Combine_them::pickYura)
                .thenApplyAsync(String::toUpperCase)
                .thenAcceptAsync(s -> System.out.println("First man in Cosmos is " + s));

        System.out.println(result.isDone());

    }

    private static String pickYura(String s1, String s2) {
        return "Yuri Gagarin";
    }


}
