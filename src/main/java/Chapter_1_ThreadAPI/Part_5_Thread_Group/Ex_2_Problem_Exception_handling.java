package Chapter_1_ThreadAPI.Part_5_Thread_Group;

/**
 * Q: How to handle thread abortion after throwing an exception?
 */
public class Ex_2_Problem_Exception_handling {
    public static void main(String[] args) {

        var thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            throw new RuntimeException("Please, handle ME");
        });

        thread.start();
    }
}