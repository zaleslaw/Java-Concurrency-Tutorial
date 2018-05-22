package Chapter_1_ThreadAPI.Part_8_Priority_Starvation;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.TimeUnit;

public class StarvationSwingDemo {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Starvation Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(new Dimension(1000, 1000));
        frame.add(createAnimationContainer());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static Component createAnimationContainer() {
        JPanel panel = new JPanel(new BorderLayout());
        AnimatedPanel animatedPanel = new AnimatedPanel();
        panel.add(animatedPanel);
        JButton button = new JButton("Start");
        panel.add(button, BorderLayout.SOUTH);
        button.addActionListener(e -> {
            animatedPanel.thread1.start();
            animatedPanel.thread2.start();

        });
        return panel;
    }

    private static class AnimatedPanel extends JComponent {
        private int x1, x2;
        Thread thread1;
        Thread thread2;

        AnimatedPanel() {
            startAnimation();
        }

        private void startAnimation() {
            thread1 = new Thread(() -> {
                while (true) {
                    x1++;
                    //no need to create EDT
                    repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException ignored) {
                    }
                }
            });

            thread1.setPriority(10);
            thread1.setName("Fat");

            thread2 = new Thread(() -> {
                while (true) {
                    x2++;
                    //no need to create EDT
                    repaint();
                    try {
                        TimeUnit.MILLISECONDS.sleep(10);
                    } catch (InterruptedException ignored) {
                    }
                }
            });

            thread2.setPriority(1);
            thread2.setName("Angry");
        }

        @Override
        public void paint(Graphics g) {
            g.setColor(Color.RED);
            g.fillOval(x1, 200, x1, x1);
            g.fillOval(1000 - x2, 200, x2, x2);
        }
    }
}