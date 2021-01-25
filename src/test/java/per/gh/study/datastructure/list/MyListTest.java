package per.gh.study.datastructure.list;

import org.junit.After;
import org.junit.Test;

public class MyListTest {
    MyList<Integer> list;

    @Test
    public void singlyLinkedListTest() {
        list = new SinglyLinkedList<>();
    }

    @Test
    public void doubleLinkedListTest(){
        list=new DoubleLinkedList<>();
    }

    @Test
    public void arrayListTest(){
        list=new MyArrayList<>();
    }

    void init() {
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
    }

    @After
    public void test() {
        init();
        for (int i = 0; i < 10; i++) {
            assert list.get(i) == i;
        }
        list.remove(9);
        list.remove(0);
        list.remove(3);
        list.remove(2);
        list.remove(3);
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            s.append(list.get(i));
        }
        assert s.toString().equals("12578");
    }
}
