package per.gh.study.container.map;

import java.util.Hashtable;

public class S01_HashTable {
    public static void main(String[] args) {
        Hashtable<Integer, String> map = new Hashtable<>();
        map.put(2,"kami");
        System.out.println(map.get(2));
        map.remove(2);
        System.out.println(map.isEmpty());
    }
}
