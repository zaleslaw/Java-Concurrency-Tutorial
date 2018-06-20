package Chapter_7_ExecutorFramework.Part_0_Timer_Framework;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Run three different tasks using different schedule methods.
 * A: Why does this example not stop?
 * Q: Due to alive threads in timer, you should stop the Timer manually.
 */
public class Ex_3_Fixed_Or_Not_Fixed {
    private static final Timer timer = new Timer();

    public static void main(String[] args) throws InterruptedException {
        TimerTask task1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Lonely Task");
            }
        };

        TimerTask task2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Repeated Task");
            }
        };

        TimerTask task3 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task with fixed rate");
            }
        };


        timer.schedule(task1, 100);
        timer.schedule(task2, 1000, 1000);
        timer.scheduleAtFixedRate(task3, 5000, 2000);

        Thread.sleep(20_000);
        task1.cancel();
        task2.cancel();
        Thread.sleep(10_000);
        task3.cancel();

        /*System.out.println("All tasks are finished, let's kill the Timer");
        timer.cancel(); */
    }
}

