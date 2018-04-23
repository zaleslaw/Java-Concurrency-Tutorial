package Old.WaitNotify.KafkaEmulator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * In case with notify all a few threads will be notified.
 * <p>
 * <p>
 *
 * Does the notifyAll() method really wake up all the threads?
 * Yes and no. All of the waiting threads wake up, but they still have to reacquire the object lock. So the threads do not run in parallel: they must each wait for the object lock to be freed.
 * Thus, only one thread can run at a time, and only after the thread that called the notifyAll() method releases its lock.
 * // TODO: learn case with  https://howtodoinjava.com/core-java/multi-threading/how-to-work-with-wait-notify-and-notifyall-in-java/
 */
public class KafkaEmulator2 {
    public static void main(String[] args) throws InterruptedException {
        Queue<Message> kafka = new LinkedList<>();
        var pool = new ArrayList<Thread>();

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

                    // kafka.notify(); // to finish processing
                    // System.out.println("NOTIFYYYYYYY");
                }
            });
            pool.add(consumer);
            consumer.start();
        }

        var producer = new Thread(() -> {
            synchronized (kafka) {
                for (int i = 0; i < 100; i++) {
                    kafka.offer(new Message(i, "Log"));
                }
                kafka.notifyAll();
            }

        });

        producer.start();
        producer.join();
        synchronized (kafka) {
            System.out.println("There are " + kafka.size() + " unprocessed messages");
        }

        boolean allTerminated = true;
        while (allTerminated) {
            synchronized (kafka) {
                System.out.println("States");
                var states = new HashMap<String, Integer>();
                for (Thread thread : pool) {
                    var state = thread.getState().toString();
                    if (states.containsKey(state)) {
                        states.put(state, states.get(state) + 1);
                    } else {
                        states.put(state, 1);
                    }
                }
                System.out.println(states.toString());
                if (states.containsKey("TERMINATED") && states.get("TERMINATED") == 100) {
                    allTerminated = false;
                }
                kafka.notify();
            }
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


