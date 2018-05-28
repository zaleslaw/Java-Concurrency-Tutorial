package Chapter_1_ThreadAPI.Part_5_Thread_Group;

/**
 * A: Thread Group handling mechanism could help.
 * No need use it explicitly, but you can declare your separate Thread Group and call Exception Hadler directly (but this is special method for JVM needs)
 */
public class Ex_2_Solution_Exception_handling {
    public static void main(String[] args) throws InterruptedException {

        ThreadGroup group = new ThreadGroup("Group");

        var thread = new Thread(group, () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Please, handle ME");
        });

        thread.setUncaughtExceptionHandler(
                (t, e) -> System.out.println("Caught throwable " + e + " for thread " + t));


        Thread.setDefaultUncaughtExceptionHandler((t, e) -> {
            System.out.println("Default uncaught exception handler");
            System.out.println("Caught throwable " + e + " for thread " + t);
        });

        thread.start();

        group.uncaughtException(thread, new RuntimeException("Hello from thread group"));
    }
}