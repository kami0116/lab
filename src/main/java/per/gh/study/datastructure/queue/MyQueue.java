package per.gh.study.datastructure.queue;

public interface MyQueue<T> {
    boolean add(T t);
    T poll();
    boolean isEmpty();
    boolean isFull();
}
