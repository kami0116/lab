package per.gh.study.datastructure.map;

import org.junit.Before;
import org.junit.Test;

public class MyHashTableTest {
    MyMap<Integer, String> map;
    @Before
    public void prepare(){
        map=new MyHashTable<>();
        for (int i = 0; i < 100; i++) {
            map.put(i,"v"+i);
        }
    }

    @Test
    public void test(){
        for (int i = 0; i < 100; i++) {
            assert map.get(i).equals("v"+i);
        }
    }
}
