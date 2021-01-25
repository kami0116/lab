package per.gh.study.datastructure.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MyQueueTest {
    private MyQueue<Integer> queue;

    @Before
    public void prepare() {
        queue = new MyQueueImpl<>(10);
    }

    @Test
    public void test() {
        for (int i = 1; i <= 10; i++) {
            assert queue.add(i);
        }

        assert !queue.add(11);
        assert queue.poll()==1;
        assert queue.poll()==2;
        assert queue.add(11);
        assert queue.add(12);

        for (int i = 3; i <= 12; i++) {
            assert queue.poll() == i;
        }
        assert queue.poll() == null;

    }
}
