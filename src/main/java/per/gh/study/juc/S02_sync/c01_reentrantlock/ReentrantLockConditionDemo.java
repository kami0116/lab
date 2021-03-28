package per.gh.study.juc.S02_sync.c01_reentrantlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockConditionDemo {
    /**
     * note:
     * 1. shouldParkAfterAcquired把ws改成-1之后，为什么不直接返回true？ （彻底盘清楚这套逻辑）
     *
     */
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();
        Condition cond = lock.newCondition();
        new Thread(() -> {
            lock.lock();
            System.out.println("资源消耗完了");
            try {
                System.out.println("await等待资源");
                cond.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("有了新资源，继续执行");
            lock.unlock();
        },"子线程").start();

        Thread.sleep(100);

        lock.lock();
        Thread.sleep(Integer.MAX_VALUE);
        System.out.println("产生新的资源");
        cond.signal();
        lock.unlock();

    }
}
