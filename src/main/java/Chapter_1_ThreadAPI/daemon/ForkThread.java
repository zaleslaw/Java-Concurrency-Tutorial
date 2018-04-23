package Chapter_1_ThreadAPI.daemon;

/** It never stops */
public class ForkThread {
    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                try {
                    System.out.println("I'm forked");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
