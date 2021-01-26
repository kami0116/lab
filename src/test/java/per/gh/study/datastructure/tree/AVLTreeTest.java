package per.gh.study.datastructure.tree;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class AVLTreeTest {
    AVLTree<Integer> tree;
    Field rootField;

    @Before
    public void prepare() throws NoSuchFieldException {
        tree = new AVLTree<>();
        rootField = AVLTree.class.getDeclaredField("root");
        rootField.setAccessible(true);
    }

    @Test
    public void addTest() throws IllegalAccessException {
        for (int i = 0; i < 100; i++) {
            tree.add(i);
            AVLTree.Node root = (AVLTree.Node) rootField.get(tree);
            assert Math.abs(tree.height(root.right) - tree.height(root.left)) < 2;
        }
    }

    @Test
    public void removeTest() throws IllegalAccessException {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(i);
        }
        list.forEach(i -> tree.add(i));
        Collections.shuffle(list);
        for (Integer i : list) {
            tree.remove(i);
            AVLTree.Node root = (AVLTree.Node) rootField.get(tree);
            assert Math.abs(tree.height(root.right) - tree.height(root.left)) < 2;
        }
    }


}
