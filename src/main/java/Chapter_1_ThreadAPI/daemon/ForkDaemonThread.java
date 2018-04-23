package Chapter_1_ThreadAPI.daemon;

/** It will stops when the main thread die */
public class ForkDaemonThread {
    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(()->{
            while(true){
                try {
                    System.out.println("I'm forked daemon and will die in 2 seconds");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t.setDaemon(true);
        t.start();

        Thread.sleep(2000);
    }
}
