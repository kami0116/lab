package per.gh.study.container.queue;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * 非阻塞queue， extend AbstractQueue
 */
public class S01_ConcurrentLinkedQueue {
    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        int producerCount = 3;
        int customerCount = 8;
        Thread[] producers = new Thread[producerCount];
        Thread[] customers = new Thread[customerCount];

        for (int i = 0; i < producerCount; i++) {
            producers[i] = new Thread(() -> {
                for (int j = 0; j < 100; j++) {
                    queue.offer(Thread.currentThread().getName() + "-" + j);
                    try {
                        TimeUnit.MILLISECONDS.sleep(80);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "producer" + i);
        }
        for (int i = 0; i < customerCount; i++) {
            customers[i] = new Thread(() -> {
                for(;;) {
                    System.out.println(Thread.currentThread().getName() + ":\t" + queue.poll());
                    try {
                        TimeUnit.MILLISECONDS.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }, "customer" + i);
        }
        for (Thread p : producers) {
            p.start();
        }
        for (Thread c : customers) {
            c.start();
        }
    }
}
