package per.gh.study.datastructure.list;

public interface MyList<T> {
    void add(T t);

    T get(int index);

    int size();

    void set(int index, T t);

    boolean remove(int index);

    boolean isEmpty();

}
