package per.gh.study.container.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class S03_ArrayBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(5);
        new Thread(()->{
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        System.out.println("sleeping...");
        TimeUnit.SECONDS.sleep(1);
        queue.offer("kami");
        System.out.println(queue.poll());

    }
}
