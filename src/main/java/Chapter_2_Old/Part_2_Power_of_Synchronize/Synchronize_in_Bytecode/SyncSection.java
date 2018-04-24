package Chapter_2_Old.Part_2_Power_of_Synchronize.Synchronize_in_Bytecode;


/**
 * With javap -c SyncSection you can find next rows
 *
 *  11: monitorenter
 *       12: getstatic     #3                  // Field java/lang/System.out:Ljava/io/PrintStream;
 *       15: ldc           #4                  // String Hello from section!
 *       17: invokevirtual #5                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
 *       20: aload_2
 *       21: monitorexit
 */
public class SyncSection {
    public static void main(String[] args) {
        Object monitor = new Object();
        synchronized (monitor){
            System.out.println("Hello from section!");
        }
    }
}
