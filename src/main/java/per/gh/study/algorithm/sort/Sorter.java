package per.gh.study.algorithm.sort;

public interface Sorter<T extends Comparable> {
    T[] sort(T[] arr);
}
