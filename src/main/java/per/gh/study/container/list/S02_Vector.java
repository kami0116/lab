package per.gh.study.container.list;

import java.util.Vector;

public class S02_Vector {
    public static void main(String[] args) {
        Vector<Integer> v = new Vector<>();
        for (int i = 0; i < 11; i++) {
            v.add(i);
        }
        System.out.println(v.get(1));
        v.remove(3);
    }
}
