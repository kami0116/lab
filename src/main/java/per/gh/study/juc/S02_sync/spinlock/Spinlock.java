package per.gh.study.juc.S02_sync.spinlock;

import java.util.concurrent.atomic.AtomicInteger;

public class Spinlock {
    private AtomicInteger lock = new AtomicInteger(0);
    public void lock() {
        while (lock.getAndIncrement() != 0) ;
    }
    public void unlock() {
        lock.set(0);
    }
}
