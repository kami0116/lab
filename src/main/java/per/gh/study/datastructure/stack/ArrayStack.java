package per.gh.study.datastructure.stack;

public class ArrayStack<T> implements Stack<T> {
    private Object[] arr;
    private int offset=-1;

    public ArrayStack() {
        arr=new Object[10];
    }

    @Override
    public void push(T t) {
        arr[++offset]=t;
    }

    @Override
    public T pop() {
        return (T) arr[offset--];
    }

    @Override
    public T peek() {
        return (T) arr[offset];
    }

    @Override
    public boolean isEmpty() {
        return offset==-1;
    }
}
