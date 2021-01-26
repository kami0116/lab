package per.gh.study.container.queue;

import java.util.PriorityQueue;

public class S02_PriorityQueue {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 20; i > 0; i--) {
            queue.offer(i);
        }
        for(;!queue.isEmpty();){
            System.out.println(queue.poll());
        }

    }
}
