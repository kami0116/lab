package per.gh.study.juc.S02_sync.c02_semaphore;

import java.util.concurrent.Semaphore;

public class Lab {
    public static void main(String[] args) {
        Semaphore s = new Semaphore(5);//可用于限流
        for (int i = 0; i < 20; i++) {
            new Thread(() -> {
                try {
                    s.acquire();
                    System.out.println(Thread.currentThread().getName() + " start to run");
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + " is running...");
                    Thread.sleep(200);
                    System.out.println(Thread.currentThread().getName() + " finished");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    s.release();
                }
            }, "T" + (i + 1)).start();
        }

    }


}
