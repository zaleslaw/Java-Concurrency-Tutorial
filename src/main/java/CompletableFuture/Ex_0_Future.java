package CompletableFuture;

import java.util.concurrent.*;

/**
 * Push a book asynchronously
 * Depends on timing we are waiting on .get() or get prepared result
 */
public class Ex_0_Future {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Callable<String> Jules_Verne = () -> {
            //Thread.sleep(2000); uncomment this to be blocked on get
            System.out.println("The romance was written");
            return  "De la terre ? la lune";
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<String> firstTripOutOfTheEarth = executorService.submit(Jules_Verne);

        System.out.println(firstTripOutOfTheEarth.isDone());

        Thread.sleep(1000);
        System.out.println(firstTripOutOfTheEarth.isDone());
        System.out.println(firstTripOutOfTheEarth.get());

        executorService.shutdown();
    }
}
