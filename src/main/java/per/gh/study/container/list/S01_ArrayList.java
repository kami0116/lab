package per.gh.study.container.list;

import java.util.ArrayList;
import java.util.Iterator;

public class S01_ArrayList {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("0_kami");
        list.add("1_sama");
        System.out.println(list.get(1));
        Iterator<String> it = list.iterator();
        System.out.println(it.next());
        it.remove();
        System.out.println(list.get(0));
    }
}
