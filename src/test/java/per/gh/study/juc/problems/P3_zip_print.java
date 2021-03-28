package per.gh.study.juc.problems;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TransferQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程1打印A-Z，线程2打印1-26，轮流打印
 */
public class P3_zip_print {
    Thread t1, t2;
    volatile int flag = 1;

    /**
     *
     */
    @Test
    public void way1_wait_notify() {//需要保证t2先执行到wait，交替才能继续
        Object lock = new Object();
        t1 = new Thread(() -> {
            sleep();
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                synchronized (lock) {
                    System.out.print((char) (a + i));
                    lock.notify();
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "t1");
        t2 = new Thread(() -> {
            int i = 1;
            for (int j = 0; j < 26; j++) {
                synchronized (lock) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    lock.notify();
                    System.out.print(i + j + " ");
                }
            }
        }, "t2");
    }

    @Test
    public void way2_await_signal() {//需要保证t2先执行到await，交替才能继续
        ReentrantLock lock = new ReentrantLock();
        Condition c1 = lock.newCondition();
        Condition c2 = lock.newCondition();
        t1 = new Thread(() -> {
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                lock.lock();
                System.out.print((char) (a + i));
                c2.signal();
                try {
                    c1.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }, "t1");
        t2 = new Thread(() -> {
            int i = 1;
            for (int j = 0; j < 26; j++) {
                lock.lock();
                try {
                    c2.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                c1.signal();
                System.out.print(i + j + " ");
                lock.unlock();
            }
        }, "t2");
    }

    @Test
    public void way3_LockSupport() {
        t1 = new Thread(() -> {
//            sleep();
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                System.out.print((char) (a + i));
                LockSupport.unpark(t2);
                LockSupport.park();
            }
        }, "t1");
        t2 = new Thread(() -> {
//            sleep();
            int i = 1;
            for (int j = 0; j < 26; j++) {
                LockSupport.park();
                System.out.print(i + j + " ");
                LockSupport.unpark(t1);
            }
        }, "t2");
    }

    @Test
    public void way4_semaphore() {
        Semaphore s = new Semaphore(0);

        t1 = new Thread(() -> {
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                System.out.print((char) (a + i));
                s.release(i * 2 + 1);
                try {
                    s.acquire(i * 2 + 2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");
        t2 = new Thread(() -> {
            int i = 1;
            for (int j = 0; j < 26; j++) {
                try {
                    s.acquire(j * 2 + 1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + j + " ");
                s.release(j * 2 + 2);
            }
        }, "t2");
    }

    @Test
    public void way5_volatile_spin_lock() {
        t1 = new Thread(() -> {
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                while (flag != 1) ;
                System.out.print((char) (a + i));
                flag = 2;
            }
        }, "t1");
        t2 = new Thread(() -> {
            int i = 1;
            for (int j = 0; j < 26; j++) {
                while (flag != 2) ;
                System.out.print(i + j + " ");
                flag = 1;
            }
        }, "t2");
    }

    @Test
    public void way6_lock_condition() {
        ReentrantLock lock = new ReentrantLock();
        Condition ch = lock.newCondition();
        Condition nu = lock.newCondition();

        t1 = new Thread(() -> {
            sleep();
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                lock.lock();
                System.out.print((char) (a + i));
                nu.signal();
                try {
                    ch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlock();
            }
        }, "t1");
        t2 = new Thread(() -> {
            int i = 1;
            for (int j = 0; j < 26; j++) {
                lock.lock();
                try {
                    nu.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + j + " ");
                ch.signal();
                lock.unlock();
            }
        }, "t2");
    }

    @Test
    public void way7_exchanger() {
        Exchanger<Object> exchanger = new Exchanger<>();
        t1 = new Thread(() -> {
            int i = 1;
            for (int j = 0; j < 26; j++) {
                try {
                    System.out.print(exchanger.exchange("ack"));
                    exchanger.exchange(i + j + " ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t1");

        t2 = new Thread(() -> {
            char a = 'A';
            for (int i = 0; i < 26; i++) {
                try {
                    exchanger.exchange((char)(a+i));
                    System.out.print(exchanger.exchange("ack"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "t2");
    }

    @After
    public void test() throws InterruptedException {
        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    void sleep() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
