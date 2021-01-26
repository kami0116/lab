package per.gh.study.datastructure.array;

public interface SparseArray<T> {
    void set(int x, int y, T t);

    T get(int x, int y);

    void remove(int x,int y);

    T[][] decode();
}
