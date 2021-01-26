package per.gh.study.datastructure.map;

public class MyHashTable<K, V> implements MyMap<K, V> {
    private class Entry<K, V> {
        int hash;
        K key;
        V value;
        Entry<K, V> next;

        public Entry(int hash, K key, V value) {
            this.hash = hash;
            this.key = key;
            this.value = value;
        }
    }

    private Entry[] table;

    public MyHashTable() {
        table = new Entry[16];
    }

    @Override
    public V put(K k, V v) {//不扩展了
        if (k == null)
            throw new IllegalArgumentException();
        int hash = hash(k);
        Entry<K, V> e = new Entry<>(hash, k, v);
        int i = (table.length - 1) & hash;
        Entry<K, V> next = table[i];
        table[i] = e;
        e.next = next;
        return v;
    }

    @Override
    public V get(K k) {
        Entry<K, V> e;
        if ((e = getEntryByKey(k)) != null) {
            return e.value;
        }
        throw new RuntimeException("key not exist " + k);
    }

    @Override
    public boolean contains(K k) {
        return getEntryByKey(k) != null;
    }

    private Entry<K, V> getEntryByKey(K k) {
        int hash = hash(k);
        for (Entry<K, V> e = table[(table.length - 1) & hash]; e != null; e = e.next) {
            if (e.hash == hash && e.key.equals(k)) {
                return e;
            }
        }
        return null;
    }

    private int hash(K k) {
        int hash;
        return (hash = k.hashCode()) ^ (hash >>> 16);
    }
}
