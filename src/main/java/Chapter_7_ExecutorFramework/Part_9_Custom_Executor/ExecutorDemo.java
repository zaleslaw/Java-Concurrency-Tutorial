package Chapter_7_ExecutorFramework.Part_9_Custom_Executor;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;

public class ExecutorDemo {
    public static void main(String[] args) {
        RunnableThread t1 = new RunnableThread("One");
        RunnableThread t2 = new RunnableThread("Two");
        Executor executor = new SequentialExecutor();
        executor.execute(t1);
        executor.execute(t2);
    }
}

class SequentialExecutor implements Executor {
    private final Queue<Runnable> queue = new ArrayDeque<Runnable>();
    private Runnable task;

    public synchronized void execute(final Runnable r) {
        queue.offer(() -> {
            try {
                r.run();
            } finally {
                next();
            }
        });
        if (task == null) {
            next();
        }
    }

    private synchronized void next() {
        if ((task = queue.poll()) != null) {
            new Thread(task).start();

        }
    }
}