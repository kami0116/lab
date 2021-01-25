package per.gh.study.container.list;

import java.util.LinkedList;

public class S04_LinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(5);
        list.add(3);
        list.remove(1);
        list.set(1,8);
        System.out.println(list.get(0));
    }
}
