package per.gh.study.juc.S02_sync.c03_readwritelock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Lab {
    public static void main(String[] args) throws Exception {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock rl = lock.readLock();
        Lock wl = lock.writeLock();
        MyFile file = new MyFile();
        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 18; i++) {
            threads.add(new Reader(file, rl, "reader" + i));
        }
        threads.add(new Writer(file, wl, "hello!", "writer01"));

        threads.add(new Writer(file, wl, "world!", "writer02"));
        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }
}
