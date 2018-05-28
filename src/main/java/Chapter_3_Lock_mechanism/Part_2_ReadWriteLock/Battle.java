package Chapter_3_Lock_mechanism.Part_2_ReadWriteLock;

/**
 * Created by Alexey_Zinovyev on 07-Jun-17.
 */
public class Battle {
    public static void main(String[] args) throws InterruptedException {
        LightCruiser varyag = new LightCruiser();

        Runnable japanFleet = () -> {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    System.out.println("ERROR in japanFleet " + e.getMessage());
                    //Thread.currentThread().interrupt(); // Uncomment this to handle interruption correctly
                }
                varyag.shoot();
                System.out.println("Varyag was damaged " + varyag.getHealth());
            }
        };
        Runnable vladivostok =  () -> {
            while(!Thread.currentThread().isInterrupted()){
                if(varyag.getHealth() < 50){
                    for (int i = 0; i < 9; i++) {
                        System.out.println("Repairing in Vladik " + varyag.getHealth());
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            System.out.println("ERROR in vladivostok " + e.getMessage());
                            //Thread.currentThread().interrupt(); // Uncomment this to handle interruption correctly
                        }
                        varyag.repair();
                    }
                }
            }
        };
        Thread t1 = new Thread(japanFleet);
        Thread t2 = new Thread(vladivostok);
        t1.start();
        t2.start();

        Thread.sleep(1000);
        t1.interrupt();
        t2.interrupt();
        t1.join();
        t2.join();
    }
}
