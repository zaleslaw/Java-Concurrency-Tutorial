package Chapter_2_Old.Part_2_Power_of_Synchronize.MoneyTransfer;

import Chapter_2_Old.Part_1_Race_condition.UnsafeAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * We need two shared resources: two accounts which are participated in money transfer.
 * But if you increase amount of threads you will catch up a deadlock like this
 * <p>
 * Found one Java-level deadlock:
 * =============================
 * "Thread-0":
 * waiting to lock monitor 0x000000002ba2e000 (object 0x00000006d17d4fc8, a Chapter_2_Old.Part_2_Power_of_Synchronize.MoneyTransfer.SafeAccount),
 * which is held by "Thread-3"
 * "Thread-3":
 * waiting to lock monitor 0x000000002ba11e80 (object 0x00000006d17d5000, a Chapter_2_Old.Part_2_Power_of_Synchronize.MoneyTransfer.SafeAccount),
 * which is held by "Thread-1"
 * "Thread-1":
 * waiting to lock monitor 0x000000002ba0ff80 (object 0x00000006d17d5070, a Chapter_2_Old.Part_2_Power_of_Synchronize.MoneyTransfer.SafeAccount),
 * which is held by "Thread-11"
 * "Thread-11":
 * waiting to lock monitor 0x000000002ba11e80 (object 0x00000006d17d5000, a Chapter_2_Old.Part_2_Power_of_Synchronize.MoneyTransfer.SafeAccount),
 * which is held by "Thread-1"
 */

public class Ex_3_Safe_but_has_deadlock {

    public static final int AMOUNT_OF_THREADS = 10; // change to 100 or 1000 to catch up the deadlock on disordered threads

    public static void main(String[] args) throws InterruptedException {
        var accounts = new HashMap<Integer, SafeAccount>();
        var threadPool = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            accounts.put(i, new SafeAccount(i, 100));
        }
        printTotalCash(accounts);

        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                var firstAccId = ThreadLocalRandom.current().nextInt(10);
                var secondAccId = ThreadLocalRandom.current().nextInt(10);
                var firstAcc = accounts.get(firstAccId);
                var secondAcc = accounts.get(secondAccId);
                synchronized (firstAcc) {
                    synchronized (secondAcc) {
                        firstAcc.transfer(secondAcc, 10);
                    }
                }

            }
        };

        for (int i = 0; i < AMOUNT_OF_THREADS; i++) {
            var t = new Thread(r);
            threadPool.add(t);
            t.start();
        }
        Thread.sleep(2000);
        Thread.sleep(2000);
        threadPool.forEach(Thread::interrupt);
        threadPool.forEach(e -> {
            try {
                e.join();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        });
        printTotalCash(accounts);
    }

    @UnsafeAccess
    private static void printTotalCash(HashMap<Integer, SafeAccount> accounts) {
        accounts.entrySet().stream().map(e -> e.getValue().getMoney()).reduce((a, b) -> a + b).ifPresent(System.out::println);
    }
}

class SafeAccount {
    private double money;
    private final int id;

    public SafeAccount(int id, int money) {
        this.id = id;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public void transfer(SafeAccount anotherAccount, double sum) {
        if (anotherAccount.id == this.id) {
            System.out.println("We can not do this transfer");
        } else {
            this.money -= sum;
            anotherAccount.money += sum;
        }
    }
}
