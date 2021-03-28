package per.gh.study.juc.s03_threadlocal;

import java.util.WeakHashMap;

public class S04_WeakHashMap {
    public static void main(String[] args) throws InterruptedException {
        WeakHashMap<String, String> map = new WeakHashMap<>();
        map.put("name","kami");
        map.put("age","18");
        System.out.println(map.get("name")+"\t"+map.get("age"));

        System.gc();
        Thread.sleep(1000);

        System.out.println(map.get("name")+"\t"+map.get("age"));
    }
}
