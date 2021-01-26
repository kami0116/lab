package per.gh.study.container.queue;

import java.util.concurrent.LinkedBlockingDeque;

public class S04_LinkedBlockingQueue {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingDeque<String> queue = new LinkedBlockingDeque<>();
        queue.offer("a");
        queue.poll();
        queue.add("b");
        queue.take();
    }
}
