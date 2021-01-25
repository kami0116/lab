package per.gh.study.datastructure.tree;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class RedBlackTreeTest {
    RedBlackTree<Integer> tree;
    Field rootField;
    List<Integer> list;
    int size = 10000;
    Random rand;

    @Before
    public void init() throws NoSuchFieldException {
        tree = new RedBlackTree<>();
        rootField = RedBlackTree.class.getDeclaredField("root");
        rootField.setAccessible(true);

        list = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        long seed = new Random().nextLong();
        System.out.println("seed: " + seed);
        rand = new Random(seed);
        Collections.shuffle(list, rand);
    }

    @Test
    public void test() throws IllegalAccessException, NoSuchFieldException {
        size=10;
        init();

        for (Integer i : list) {
            tree.add(i);
            System.out.println("=============add " + i);
            RedBlackTree.Node root = (RedBlackTree.Node) rootField.get(tree);
            tree.printTree();
            rule1(root);
            rule2(root, 0);
        }
    }

    @Test
    public void addTest() throws IllegalAccessException {
        for (Integer i : list) {
            tree.add(i);
            RedBlackTree.Node root = (RedBlackTree.Node) rootField.get(tree);
            rule1(root);
            rule2(root, 0);
        }
    }

    @Test
    public void removeTest() throws IllegalAccessException {
        for (Integer i : list) {
            tree.add(i);
        }
        Collections.shuffle(list, rand);
        for (Integer i : list) {
            tree.remove(i);
            RedBlackTree.Node root = (RedBlackTree.Node) rootField.get(tree);
            rule1(root);
            rule2(root, 0);
        }

    }

    //红色结点的子节点都是黑色的
    void rule1(RedBlackTree.Node p) {
        if (p.red) {
            if (p.left != null && p.right != null) {
                assert !p.left.red;
                assert !p.right.red;
                rule1(p.left);
                rule1(p.right);
            } else {
                assert p.left == null && p.right == null;
            }
        } else {
            if (p.left != null) rule1(p.left);
            if (p.right != null) rule1(p.right);
        }

    }

    //结点到它左右子树叶子的路径上，经过相同数量的黑色结点
    int rule2(RedBlackTree.Node p, int count) {
        if (p == null) {
            return count + 1;
        }
        int lc = rule2(p.left, count);
        int rc = rule2(p.right, count);
        assert lc == rc;

        if (!p.red) {
            return lc + 1;
        } else {
            return lc;
        }
    }
}
