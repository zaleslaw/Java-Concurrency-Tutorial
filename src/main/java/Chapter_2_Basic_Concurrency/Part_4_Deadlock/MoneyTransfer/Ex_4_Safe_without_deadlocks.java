package Chapter_2_Basic_Concurrency.Part_4_Deadlock.MoneyTransfer;

import Chapter_2_Basic_Concurrency.Part_1_Race_condition.UnsafeAccess;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/**
 * No deadlocks here due to special approach Ordered Locks. This approach ensures that multiple locks are acquired and released in the same order.
 * It requires a consistent ordering over ReallySafeAccount objects.
 * <p>
 * We can order accounts by id
 */
public class Ex_4_Safe_without_deadlocks {
    public static void main(String[] args) throws InterruptedException {
        var accounts = new HashMap<Integer, ReallySafeAccount>();
        var threadPool = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            accounts.put(i, new ReallySafeAccount(i, 100));
        }
        printTotalCash(accounts);

        Runnable r = () -> {
            while (!Thread.currentThread().isInterrupted()) {
                var firstAccId = ThreadLocalRandom.current().nextInt(10);
                var secondAccId = ThreadLocalRandom.current().nextInt(10);
                var firstAcc = accounts.get(firstAccId);
                var secondAcc = accounts.get(secondAccId);

                firstAcc.transfer(secondAcc, 10);
            }
        };

        for (int i = 0; i < 100; i++) {
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
    private static void printTotalCash(HashMap<Integer, ReallySafeAccount> accounts) {
        accounts.entrySet().stream().map(e -> e.getValue().getMoney()).reduce((a, b) -> a + b).ifPresent(System.out::println);
    }
}

class ReallySafeAccount {
    private double money;
    private final int id;

    public ReallySafeAccount(int id, int money) {
        this.id = id;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public void transfer(ReallySafeAccount anotherAccount, double sum) {
        if (anotherAccount.id == this.id) {
            // System.out.println("We can not do this transfer");
        } else {
            if (anotherAccount.id < this.id) {
                synchronized (anotherAccount) {
                    synchronized (this) {
                        this.money -= sum;
                        anotherAccount.money += sum;
                    }
                }
            } else {
                synchronized (this) {
                    synchronized (anotherAccount) {
                        this.money -= sum;
                        anotherAccount.money += sum;
                    }
                }
            }
        }
    }
}
