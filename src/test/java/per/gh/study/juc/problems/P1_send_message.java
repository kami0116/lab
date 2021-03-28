package per.gh.study.juc.problems;

import org.junit.Test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.LockSupport;

/**
 * 实现一个容器，提供两个方法，add，size
 * 写两个线程，线程1添加10个元素到容器中，线程2实现监控元素的个数，当个数到5个时，线程2给出提示并结束
 */
public class P1_send_message {
    @Test
    public  void way1_LockSupport() {
        P1_send_message c = new P1_send_message();
        Thread[] threads = new Thread[2];
        threads[0] = new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                c.add(i);
                LockSupport.unpark(threads[1]);
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        threads[1] = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                LockSupport.park(threads[0]);
            }
            System.out.println("size: " + c.size());
        });
        threads[0].start();
        threads[1].start();
    }
    @Test
    public void way2_wait_notify() {
        P1_send_message c = new P1_send_message();
        Object lock = new Object();
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1启动了");
            for (int i = 1; i <= 10; i++) {
                synchronized (lock) {
                    c.add(i);
                    if (i == 5) {
                        lock.notify();
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1结束了");
        }, "t1").start();
        new Thread(() -> {
            synchronized (lock) {
                System.out.println("t2启动了");
                if (c.size != 5) {
                    try {
                        lock.wait();
                        System.out.println("size:" + c.size());
                        lock.notify();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("t2结束了");
            }
        }, "t2").start();
    }
    @Test
    public void way3_semaphore() {
        P1_send_message c = new P1_send_message();
        Object lock = new Object();
        Semaphore s = new Semaphore(0);
        new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t1启动了");
            for (int i = 1; i <= 10; i++) {
                synchronized (lock) {
                    c.add(i);
                }
                if (c.size() == 5) {
                    try {
                        s.release();
                        s.acquire(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            System.out.println("t1结束了");
        }, "t1").start();
        new Thread(() -> {
            System.out.println("t2启动了");
            try {
                s.acquire();
                System.out.println("size: " + c.size());
                s.release(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t2结束了");
        }, "t2").start();
    }

    //container
    private int size;
    private Object[] objects = new Object[10];

    public void add(Object o) {
        objects[size++] = o;
        System.out.println("add: " + o);
    }

    public int size() {
        return size;
    }
}
