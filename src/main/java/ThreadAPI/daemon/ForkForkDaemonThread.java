package ThreadAPI.daemon;

/** It will stops when the main thread die */
public class ForkForkDaemonThread {
    public static void main(String[] args) throws InterruptedException {
        var t = new Thread(()->{

            var t2 = new Thread(()->{
                while(true){
                    try {
                        System.out.println("I'm forked daemon from forked daemon");
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            // t2.setDaemon(false); // the parent forked daemon couldn't die before this thread will not die
            t2.start();


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
