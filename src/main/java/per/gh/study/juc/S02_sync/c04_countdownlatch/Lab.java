package per.gh.study.juc.S02_sync.c04_countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

public class Lab {
    public static void main(String[] args) throws InterruptedException {
        int size = 100;
        AtomicInteger count = new AtomicInteger(0);
        CountDownLatch countDownLatch = new CountDownLatch(size);
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000000; j++) {
                    count.incrementAndGet();
                    if (j == 5000) countDownLatch.countDown();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        countDownLatch.await();
        System.out.println(count.get());
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println(count.get());
    }
}
