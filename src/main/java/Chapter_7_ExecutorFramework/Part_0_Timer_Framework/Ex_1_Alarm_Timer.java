package Chapter_7_ExecutorFramework.Part_0_Timer_Framework;

import java.util.Timer;
import java.util.TimerTask;

public class Ex_1_Alarm_Timer {
    public static void main(String[] args) {
        Alarm alarm = new Alarm(2);
        alarm.start();
    }
}

class Alarm {
    private final Timer timer = new Timer();
    private final int seconds;

    Alarm(int seconds) {
        this.seconds = seconds;
    }

    public void start() {
        timer.schedule(new TimerTask() {
            public void run() {
                playSound();
                timer.cancel();
            }

            private void playSound() {
                System.out.println("ALAAAARM!");
            }
        }, seconds * 1000);
    }
}
