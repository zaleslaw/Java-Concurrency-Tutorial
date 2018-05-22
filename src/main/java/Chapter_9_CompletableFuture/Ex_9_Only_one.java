package Chapter_9_CompletableFuture;

import java.util.concurrent.CompletableFuture;

/**
 * Who will be the first on the Moon
 */
public class Ex_9_Only_one {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<String> ussrMoon = CompletableFuture.supplyAsync(()->"USSR")
                .thenApplyAsync(s -> {
                    s += " are Moon communists";
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return s;
                });
        CompletableFuture<String> usaMoon = CompletableFuture.supplyAsync(()-> "USA")
                .thenApply(s -> s+= " will build first Moon base");

        ussrMoon.acceptEitherAsync(usaMoon, System.out::println);

        Thread.sleep(1000);
    }
}
