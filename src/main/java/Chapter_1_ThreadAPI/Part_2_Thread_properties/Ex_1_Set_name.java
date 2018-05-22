package Chapter_1_ThreadAPI.Part_2_Thread_properties;

public class Ex_1_Set_name {
    public static void main(String[] args) {
        var thread = new Thread("V.I.P. Thread") {
            public void run() {
                System.out.println("Run by: " + getName());
            }
        };

        thread.start();
        System.out.println(thread.getName());
    }
}
