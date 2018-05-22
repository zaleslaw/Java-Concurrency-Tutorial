package Chapter_2_Basic_Concurrency.Part_2_Power_of_Synchronize;

public class Ex_5_Synchronize_your_object {
    public static void main(String[] args) {
        SynchronizedObject mutableObject = new SynchronizedObject();
        mutableObject.setField1(100);
        mutableObject.setField2(10);
        System.out.println(mutableObject.getField1() + " " + mutableObject.getField2());
    }
}

class SynchronizedObject {
    private int field1;
    private static int field2;

    public static synchronized int getField2() {
        System.out.println(Thread.currentThread().getName() + " holds .class monitor " + Thread.holdsLock(SynchronizedObject.class));
        return field2;
    }

    public static void setField2(int field2) {
        System.out.println("Enters setField2");
        System.out.println(Thread.currentThread().getName() + " holds .class monitor " + Thread.holdsLock(SynchronizedObject.class));
        synchronized (SynchronizedObject.class){
            System.out.println(Thread.currentThread().getName() + " holds .class monitor " + Thread.holdsLock(SynchronizedObject.class));
            SynchronizedObject.field2 = field2;
        }
        System.out.println("Leaves setField2");
    }


    public synchronized int getField1() {
        System.out.println(Thread.currentThread().getName() + " holds .class monitor " + Thread.holdsLock(SynchronizedObject.class));
        System.out.println(Thread.currentThread().getName() + " holds THIS monitor " + Thread.holdsLock(this));
        return field1;
    }

    public void setField1(int field1) {
        System.out.println("Enters setField1");
        System.out.println(Thread.currentThread().getName() + " holds .class monitor " + Thread.holdsLock(SynchronizedObject.class));
        System.out.println(Thread.currentThread().getName() + " holds THIS monitor " + Thread.holdsLock(this));
        synchronized (this) {
            System.out.println(Thread.currentThread().getName() + " holds .class monitor " + Thread.holdsLock(SynchronizedObject.class));
            System.out.println(Thread.currentThread().getName() + " holds THIS monitor " + Thread.holdsLock(this));
            this.field1 = field1;
        }
        System.out.println("Leaves setField1");
    }

}
