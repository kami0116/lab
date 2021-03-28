package per.gh.study.juc.S02_sync.c01_reentrantlock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockSimpleDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        int size=100;
        Thread[] threads=new Thread[size];
        final int[] count = {0};
        for (int i = 0; i < size; i++) {
            threads[i]=new Thread(()->{
                for (int j = 0; j < 100000; j++) {
                    try {
                        lock.lock();
                        count[0]++;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }finally {
                        lock.unlock();
                    }
                }
            });
        }
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            t.join();
        }

        System.out.println(count[0]);
    }
    
}
