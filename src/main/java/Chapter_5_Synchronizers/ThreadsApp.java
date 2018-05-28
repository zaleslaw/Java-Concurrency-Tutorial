package Chapter_5_Synchronizers;

import java.util.concurrent.Semaphore;

public class ThreadsApp {
 
    public static void main(String[] args) {
         
        Semaphore sem = new Semaphore(2);
        for(int i=1;i<6;i++)
            new Philosopher(sem,i).start();
    }
}

class Philosopher extends Thread 
{
    Semaphore sem;
    // how any times to eat
    int num = 0;
    int id;
    Philosopher(Semaphore sem, int id)
    {
        this.sem=sem;
        this.id=id;
    }
     
    public void run()
    {
        try
        {
            while (num < 3)
            {

                sem.acquire();
                System.out.println("Philosopher  " + id + " is starting to eat");

                sleep(500); // philosopher is eating
                num++;

                System.out.println("Philosopher " + id + " is finishing to eat");
                sem.release();

                // Philosopher is walking around
                sleep(500);
            }
        }
        catch(InterruptedException e)
        {
            System.out.println("Philosopher " + id + " is sick");
        }
    }
}