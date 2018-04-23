package Chapter_1_ThreadAPI.daemon;

/** It never stops */
public class ForkForkThread {
    public static void main(String[] args) {
        new Thread(()->{
            new Thread(()->{
                while(true){
                    try {
                        System.out.println("I'm forked from forked thread");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();

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
