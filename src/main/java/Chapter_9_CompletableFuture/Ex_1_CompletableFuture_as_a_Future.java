package Chapter_9_CompletableFuture;

import java.util.concurrent.*;


public class Ex_1_CompletableFuture_as_a_Future {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = new CompletableFuture<>();

        System.out.println(cf.isDone());

       /* cf.complete("Good evening, my Lord!");
        System.out.println(cf.get()); // waiting forever without complete*/


        // or run in custom infrastructure

        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(()->{cf.complete("Go to the stars, boy!");});

        System.out.println(cf.getNow("Galileo"));

        // we couldn't get "Go to the stars" or default value due to lifecycle of CF
        // The CF consists of stages

        service.awaitTermination(3, TimeUnit.SECONDS);

        System.out.println(cf.getNow("Galileo"));
        service.shutdown();

    }
}
