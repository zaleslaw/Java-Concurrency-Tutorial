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
public class Ex_4_Subscribe_Operations {
    public static void main(String[] args) throws ExecutionException, InterruptedException {

        CompletableFuture.supplyAsync(() -> "Sputnik with dogs")
                .thenApplyAsync(s -> s+=": Belka")
                .thenApply(s-> s+=" & Strelka ")
                .thenAcceptAsync(System.out::println);

        // With Thread names
/*        CompletableFuture.supplyAsync(() -> "Sputnik with dogs")
                .thenApplyAsync(s -> {
                    System.out.println("Step 1: " + Thread.currentThread().getName());
                    s+=": Belka";
                    return s;
                })
                .thenApply(s-> {
                    System.out.println("Step 2: " + Thread.currentThread().getName());
                    s+=" & Strelka ";
                    return s;
                })
                .thenAcceptAsync(s->{
                    System.out.println("Step 3: " + Thread.currentThread().getName());
                    System.out.println(s);
                });*/

        CompletableFuture.supplyAsync(() -> "Sputnik with cats")
                .thenApplyAsync(s -> s+=":Meow ")
                .thenApply(s-> s+=" Meoooooww ")
                .thenRunAsync(() -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Nobody knows the name of heroic cat");
                });

        Thread.sleep(5000);


    }
}
