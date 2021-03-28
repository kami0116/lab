package per.gh.study.juc;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockTest {
    @Test
    public void illegalMonitorStateException() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        lock.lock();
        lock.unlock();
        lock.unlock();
        Exception ex = null;
        try {
            lock.unlock();
        } catch (Exception e) {
            ex = e;
        }
        assert ex instanceof IllegalMonitorStateException;
    }
}
