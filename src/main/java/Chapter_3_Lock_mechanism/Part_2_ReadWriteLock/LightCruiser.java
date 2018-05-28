package Chapter_3_Lock_mechanism.Part_2_ReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by Alexey_Zinovyev on 07-Jun-17.
 */
public class LightCruiser {
    private int health = 100;
    private boolean isDamaged;
    final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();


    public void repair(){
        lock.readLock().lock();
        if(isDamaged){
            lock.readLock().unlock(); // #1 Convert read lock to write lock
            lock.writeLock().lock();// #2
            if(isDamaged){ // Repeated check because something can happened between #1 and #2
                health++;
            }
            lock.writeLock().unlock();
        } else {
            System.out.println("You can't repair this new Cruiser " + getHealth());
            lock.readLock().unlock();
        }
    }

    public void shoot(){
        lock.writeLock().lock();
        health-=10;
        isDamaged = true;
        lock.writeLock().unlock();
    }

    public int getHealth(){
        lock.readLock().lock();
        int result = health;
        lock.readLock().unlock();
        return result;
    }
}
