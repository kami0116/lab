package per.gh.study.juc.S02_sync.c03_readwritelock;

import java.util.concurrent.locks.Lock;

public class Writer extends Thread {
    private MyFile file;
    private Lock writeLock;
    private String context;

    public Writer(MyFile file, Lock writeLock, String context, String name) {
        this.file = file;
        this.writeLock = writeLock;
        this.context = context;
        this.setName(name);
    }

    @Override
    public void run() {
        try {
            writeLock.lock();
            file.write(context);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            writeLock.unlock();
        }
    }
}
