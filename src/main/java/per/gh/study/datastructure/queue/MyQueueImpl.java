package per.gh.study.datastructure.queue;

public class MyQueueImpl<T> implements MyQueue<T> {
    private Object[] queue;
    private int head;
    private int tail;
    private int maxSize;

    public MyQueueImpl(int maxSize) {
        this.maxSize = maxSize+1;
        queue = new Object[this.maxSize];
        head = 0;
        tail = 0;
    }

    @Override
    public boolean add(T t) {
        if (!isFull()) {
            queue[tail = (tail + 1) % maxSize] = t;
            return true;
        }
        return false;
    }

    @Override
    public T poll() {
        if (!isEmpty()) {
            return (T) queue[head = (head + 1) % maxSize];
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    @Override
    public boolean isFull() {
        return (tail + 1) % maxSize == head;
    }
}
