package per.gh.study.container.list;

import java.util.concurrent.CopyOnWriteArrayList;

public class S03_CopyOnWriteList {
    public static void main(String[] args) {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(10);
        list.set(0,5);
        list.get(0);
        list.remove(0);
    }
}
