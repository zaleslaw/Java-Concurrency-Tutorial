package Old.monitor;

public class Ex_6_Run_threads {
    public static void main(String[] args) throws InterruptedException {
        SynchronizedObject2 mutableObject = new SynchronizedObject2();
        var t = new Thread(()->{
                for (int i = 0; i < 100; i++) {
                    mutableObject.add(1);
                    System.out.println("Iteration " + i + " in " + Thread.currentThread().getName());
                }
        });
        t.start();


        for (int i = 0; i < 100; i++) {
            mutableObject.add(-1);
            System.out.println("Iteration " + i + " in " + Thread.currentThread().getName());
        }

        t.join();

        System.out.println("The result is " + mutableObject.getField1());
    }
}

class SynchronizedObject2 {
    private int field1;

    public synchronized int getField1() {
        return field1;
    }

    public synchronized void add(int delta) {
        System.out.println(Thread.currentThread().getName() + " enters setField1");
        this.field1 = this.field1 + delta;
        System.out.println(Thread.currentThread().getName() + " leaves setField1");
    }

}


