package Chapter_2_Old.WaitNotify.syncBytecode;


/**
 * But here you can not find monitorenter/exit bytecode instruction
 *
 * public class Old.WaitNotify.Synchronized.SyncM {
 *   public Old.WaitNotify.Synchronized.SyncM();
 *     Code:
 *        0: aload_0
 *        1: invokespecial #1                  // Method java/lang/Object."<init>":()V
 *        4: return
 *
 *   public static void main(java.lang.String[]);
 *     Code:
 *        0: invokestatic  #2                  // Method f:()V
 *        3: return
 * }
 */
public class SyncM {
    public static void main(String[] args) {
        f();
    }

    private synchronized static void f() {
            System.out.println("Hello from section!");
    }
}
