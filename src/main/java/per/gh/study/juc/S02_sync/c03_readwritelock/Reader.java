package per.gh.study.juc.S02_sync.c03_readwritelock;

import java.util.concurrent.locks.Lock;

public class Reader extends Thread {
    private MyFile file;

    private Lock readLock;

    public Reader(MyFile file, Lock readLock, String name) {
        this.file = file;
        this.readLock = readLock;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName()+" 读取到：" + file.read());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}
