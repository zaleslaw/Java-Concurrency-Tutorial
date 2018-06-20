package Chapter_7_ExecutorFramework.Part_0_Timer_Framework;

import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Ex_2_Swing {
    private Timer fTimer;
    private JProgressBar prg;

    class ProgressUpdateTask extends TimerTask {
        int progressValue = 0;

        @Override
        public void run() {
            progressValue++;
            prg.setValue(progressValue);
            if (progressValue > 100)
                fTimer.cancel();
        }

    }

    public static void main(String[] args) {
        Ex_2_Swing obj = new Ex_2_Swing();
        obj.createUI();

    }

    private void createUI() {
        JFrame f = new JFrame("Progress through the Timer");
        f.setLayout(null);
        prg = new JProgressBar();
        prg.setSize(500, 20);

        // Display percentage Text
        prg.setStringPainted(true);
        f.add(prg);

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setSize(500, 100);
        f.setVisible(true);

        fTimer = new Timer();
        fTimer.schedule(new ProgressUpdateTask(), 100, 100);
    }
}