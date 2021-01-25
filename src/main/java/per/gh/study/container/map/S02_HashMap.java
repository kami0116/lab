package per.gh.study.container.map;

import java.util.HashMap;

public class S02_HashMap {
    public static void main(String[] args) {
        HashMap<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 13; i++) {
            map.put(i, "s" + i);
        }
    }
}
