package per.gh.study.datastructure.stack;

public interface Stack<T> {
    void push(T t);
    T pop();
    T peek();
    boolean isEmpty();

}
