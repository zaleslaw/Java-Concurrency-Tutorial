package Chapter_2_Basic_Concurrency.Part_2_Power_of_Synchronize;
/**
 * Sets of 10 increment or decrement operations will be performed like one indivisible operation (transaction)
 *
 * We add atomicity with locks via synchronized operator.
 *
 * The critical section was extended from 1 update to 10 updates.
 */
public class Ex_7_Run_updates_by_batches {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedObject2 mutableObject = new SynchronizedObject2();
        var t = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                synchronized (mutableObject) {
                    for (int j = 0; j < 10; j++) {
                        mutableObject.add(1);
                        System.out.println("The result is " + mutableObject.getField1());
                    }
                }
                Thread.yield();
            }
        });
        t.start();

        for (int i = 0; i < 10; i++) {
            synchronized (mutableObject) {
                for (int j = 0; j < 10; j++) {
                    mutableObject.add(-1);
                    System.out.println("The result is " + mutableObject.getField1());
                }
            }
            Thread.yield();
        }

        t.join();
        System.out.println("The result is " + mutableObject.getField1());
    }
}





