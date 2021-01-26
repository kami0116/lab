package per.gh.study.datastructure.map;

public interface MyMap<K,V> {
    V put(K k,V v);
    V get(K k);
    boolean contains(K k);
}
