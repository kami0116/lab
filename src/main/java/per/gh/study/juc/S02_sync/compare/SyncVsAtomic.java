package per.gh.study.juc.S02_sync.compare;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class SyncVsAtomic {
    private static int count1 = 0;
    private static final AtomicInteger count2 = new AtomicInteger(0);
    private static LongAdder count3=new LongAdder();

    public static void main(String[] args) throws Exception {
        int size = 1000;
        sync(size);
        atomic(size);
        longAdder(size);
        System.out.println(count1);
        System.out.println(count2.get());
        System.out.println(count3.longValue());
        /**
         * 输出结果：
         * sync: 2850
         * atomic: 1888
         * longAdder: 207
         * 100000000
         * 100000000
         * 100000000
         */
    }

    public static void sync(int size) throws InterruptedException {
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    synchronized (SyncVsAtomic.class) {
                        count1++;
                    }
                }
            });
        }
        Instant start = Instant.now();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        Instant end = Instant.now();
        System.out.println("sync: " + Duration.between(start, end).toMillis());
    }

    public static void atomic(int size) throws InterruptedException {
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count2.incrementAndGet();
                }

            });
        }
        Instant start = Instant.now();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        Instant end = Instant.now();
        System.out.println("atomic: " + Duration.between(start, end).toMillis());
    }

    public static void longAdder(int size) throws InterruptedException {
        Thread[] threads = new Thread[size];
        for (int i = 0; i < size; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 100000; j++) {
                    count3.increment();
                }
            });
        }
        Instant start = Instant.now();
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        Instant end = Instant.now();
        System.out.println("longAdder: " + Duration.between(start, end).toMillis());
    }
}
