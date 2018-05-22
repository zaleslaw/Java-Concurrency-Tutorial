package Chapter_2_Old.Part_2_Power_of_Synchronize.MoneyTransfer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

public class Ex_2_Synchronized_Trasfer_Method {
    public static void main(String[] args) throws InterruptedException {
        var accounts = new HashMap<Integer, Account>();
        var threadPool = new ArrayList<Thread>();

        for (int i = 0; i < 10; i++) {
            accounts.put(i, new Account(i, 100));
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

        for (int i = 0; i < 10; i++) {
            var t = new Thread(r);
            threadPool.add(t);
            t.start();
        }
        printTotalCash(accounts);
        Thread.sleep(2000);
        printTotalCash(accounts);
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

    private static void printTotalCash(HashMap<Integer, Account> accounts) {
        accounts.entrySet().stream().map(e -> e.getValue().getMoney()).reduce((a, b) -> a + b).ifPresent(System.out::println);

    }
}

class Account {
    private double money;
    private final int id;

    public Account(int id, int money) {
        this.id = id;
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public double getMoney() {
        return money;
    }

    public synchronized void transfer(Account anotherAccount, double sum) {
        if (anotherAccount.id == this.id) {
            // System.out.println("We can not do this transfer");
        } else {
            this.money -= sum;
            anotherAccount.money += sum;
        }
    }
}
