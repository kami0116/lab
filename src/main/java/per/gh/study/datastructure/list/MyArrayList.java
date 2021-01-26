package per.gh.study.datastructure.list;

import java.util.Arrays;

public class MyArrayList<T> implements MyList<T> {
    private static final int INIT_ARRAY_SIZE = 8;
    private Object[] arr;
    private int size = 0;

    public MyArrayList() {
        arr = new Object[INIT_ARRAY_SIZE];
    }

    private void ensureCapacity(int minCapacity) {
        if (arr.length < minCapacity) {
            int newCapacity = arr.length + (arr.length >>> 1);
            arr = Arrays.copyOf(arr, newCapacity);
        }
    }

    @Override
    public void add(T t) {
        ensureCapacity(size + 1);
        arr[size++] = t;
    }

    @Override
    public T get(int index) {
        if (index<size) {
            return (T) arr[index];
        }
        throw new IndexOutOfBoundsException();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int index, T t) {
        if (size<index) {
            arr[index] = t;
        }
    }

    @Override
    public boolean remove(int index) {
        if (index<size){
            size--;
            for (int i = index; i < size; i++) {
                arr[i]=arr[i+1];
            }
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }
}
