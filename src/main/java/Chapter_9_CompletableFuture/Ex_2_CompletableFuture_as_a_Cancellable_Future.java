package Chapter_9_CompletableFuture;

import java.util.concurrent.*;

/**
 * Play with pauses and cancel CF
 */
public class Ex_2_CompletableFuture_as_a_Cancellable_Future {

    public static final String GALILEO = "Galileo";

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<String> cf = new CompletableFuture<>();

        System.out.println(cf.isDone());


        ExecutorService service = Executors.newFixedThreadPool(2);
        service.submit(()->{
            cf.complete("Go to the stars, boy!");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        try {
            Thread.sleep(1000); // comment this and take different results
            if(cf.getNow(GALILEO).equals(GALILEO)){
                if(!cf.isCancelled()){
                    cf.cancel(false);
                }

            } else {
                System.out.println(cf.getNow("Kopernik"));
            }

        } finally {
            service.awaitTermination(3, TimeUnit.SECONDS);
            System.out.println("Task is cancelled: " + cf.isCancelled());
            service.shutdown();
        }




    }
}
