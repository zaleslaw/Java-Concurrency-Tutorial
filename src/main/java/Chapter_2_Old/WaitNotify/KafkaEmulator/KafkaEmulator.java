package Chapter_2_Old.WaitNotify.KafkaEmulator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Add breakpoint at #1 and see the result like pasted below
 * <p>
 * "Consumer 1@742" prio=5 tid=0x10 nid=NA runnable
 * java.lang.Thread.State: RUNNABLE
 * blocks Thread-0@743
 * blocks Thread-2@762
 * blocks Thread-3@864
 * blocks Thread-4@747
 * blocks Thread-5@756
 * blocks Thread-6@764
 * blocks Thread-7@753
 * blocks Thread-8@748
 * blocks Thread-9@758
 * blocks Thread-10@765
 * blocks Thread-11@754
 * blocks Thread-12@749
 * blocks Thread-13@760
 * blocks Thread-14@858
 * blocks Thread-15@757
 * blocks Thread-16@750
 * blocks Thread-17@778
 * blocks Thread-18@856
 * blocks Thread-19@755
 * blocks Thread-20@751
 * blocks Thread-21@784
 * blocks Thread-22@851
 * blocks Thread-23@761
 * blocks Thread-24@752
 * blocks Thread-25@873
 * blocks Thread-26@836
 * blocks Thread-27@763
 * blocks Thread-28@777
 * blocks Thread-29@779
 * blocks Thread-30@845
 * blocks Thread-31@863
 * blocks Thread-32@776
 * blocks Thread-33@783
 * blocks Thread-34@848
 * blocks Thread-35@766
 * blocks Thread-36@775
 * blocks Thread-37@813
 * blocks Thread-38@844
 * blocks Thread-39@862
 * blocks Thread-40@774
 * blocks Thread-41@782
 * blocks Thread-42@843
 * blocks Thread-43@759
 * blocks Thread-44@773
 * blocks Thread-45@816
 * blocks Thread-46@838
 * blocks Thread-47@767
 * blocks Thread-48@772
 * blocks Thread-49@872
 * blocks Thread-50@842
 * blocks Thread-51@860
 * blocks Thread-52@771
 * blocks Thread-53@814
 * blocks Thread-54@841
 * blocks Thread-55@861
 * blocks Thread-56@770
 * blocks Thread-57@871
 * blocks Thread-58@840
 * blocks Thread-59@867
 * blocks Thread-60@769
 * blocks Thread-61@780
 * blocks Thread-62@839
 * blocks Thread-63@768
 * blocks Thread-64@789
 * blocks Thread-65@800
 * blocks Thread-66@849
 * blocks Thread-67@791
 * blocks Thread-68@804
 * blocks Thread-69@798
 * blocks Thread-70@854
 * blocks Thread-71@796
 * blocks Thread-72@807
 * blocks Thread-73@803
 * blocks Thread-74@853
 * blocks Thread-75@793
 * blocks Thread-76@790
 * blocks Thread-77@808
 * blocks Thread-78@852
 * blocks Thread-79@794
 * blocks Thread-80@806
 * blocks Thread-81@802
 * blocks Thread-82@850
 * blocks Thread-83@799
 * blocks Thread-84@788
 * blocks Thread-85@801
 * blocks Thread-86@847
 * blocks Thread-87@792
 * blocks Thread-88@787
 * blocks Thread-89@809
 * blocks Thread-90@846
 * blocks Thread-91@785
 * blocks Thread-92@786
 * blocks Thread-93@810
 * blocks Thread-94@857
 * blocks Thread-95@797
 * blocks Thread-96@795
 * blocks Thread-97@815
 * blocks Thread-98@855
 * blocks Thread-99@859
 * blocks Thread-100@920
 * at Old.WaitNotify.KafkaEmulator.KafkaEmulator.lambda$main$0(KafkaEmulator.java:20)
 * - locked <0x397> (a java.util.LinkedList)
 * at Old.WaitNotify.KafkaEmulator.KafkaEmulator$$Lambda$1.157456214.run(Unknown Source:-1)
 * at java.lang.Thread.run(Thread.java:844)
 * <p>
 * In case with notify all a few threads will be notified.
 * <p>
 * <p>
 * // TODO: learn case with  https://howtodoinjava.com/core-java/multi-threading/how-to-work-with-wait-notify-and-notifyall-in-java/
 */
public class KafkaEmulator {
    public static void main(String[] args) throws InterruptedException {
        Queue<Message> kafka = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            int finalI = i;
            var consumer = new Thread(() -> {
                synchronized (kafka) {
                    final Thread thisThread = Thread.currentThread();
                    thisThread.setName("Consumer " + finalI);
                    try {

                        System.out.println(thisThread.getName() + " is waiting at time:" + System.currentTimeMillis());
                        kafka.wait(); // #1 wait() : It tells the calling thread to give up the lock and go to sleep until some other thread enters the same monitor and calls notify().
                        // The wait() method releases the lock prior to waiting and reacquires the lock prior to returning from the wait() method.
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(thisThread.getName() + " thread got notified at time:" + System.currentTimeMillis());
                    System.out.println(thisThread.getName() + " processed the Kafka message with key : " + kafka.poll().key);
                }
            });
            consumer.start();
        }

        var producer = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                synchronized (kafka) {
                    kafka.offer(new Message(i, "Log"));
                    kafka.notify();
                }
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });


        producer.start();
        producer.join();
        synchronized (kafka) {
            System.out.println("There are " + kafka.size() + " unprocessed messages");
        }
    }

    private static class Message {
        int key;
        String value;

        public Message(int key, String value) {
            this.key = key;
            this.value = value;
        }
    }
}


