package per.gh.study.datastructure.tree;

public class AVLTree<T extends Comparable> {
    class Node<T> {
        T v;
        Node<T> parent, left, right;

        public Node(T v, Node<T> parent) {
            this.v = v;
            this.parent = parent;
        }
    }

    private Node<T> root;

    public void add(T v) {
        assert v != null;
        Node<T> x = new Node<>(v, null);
        if (root == null) {
            root = x;
        } else {
            for (Node<T> p = root; ; ) {
                if (p.v.compareTo(v) > 0) {
                    if (p.left == null) {
                        x.parent = p;
                        p.left = x;
                        balance(x);
                        return;
                    } else {
                        p = p.left;
                    }
                } else {
                    if (p.right == null) {
                        x.parent = p;
                        p.right = x;
                        balance(x);
                        return;
                    } else {
                        p = p.right;
                    }
                }
            }
        }
    }

    private void balance(Node<T> x) {
        doBalance(x, true);
    }

    private void doBalance(Node<T> x, boolean once) {
        int h = height(x);
        for (Node<T> xp = x.parent; xp != null; x = xp, xp = xp.parent, h++) {
            int h2 = x == xp.left ? height(xp.right) : height(xp.left);
            if (h - h2 > 1) {
                Node<T> pp = xp.parent;
                if (x == xp.left) {
                    xp.left = x.right;
                    if (x.right != null) x.right.parent = xp;

                    x.right = xp;
                } else {
                    xp.right = x.left;
                    if (x.left != null) x.left.parent = xp;

                    x.left = xp;
                }
                xp.parent = x;
                x.parent = pp;
                if (pp == null) {
                    root = x;
                } else if (xp == pp.left) {
                    pp.left = x;
                } else {
                    pp.right = x;
                }
                if (once)
                    return;
            }
        }
    }

    public int height(Node<T> n) {
        return n == null ? 0 : (Math.max(n.left == null ? 0 : height(n.left), n.right == null ? 0 : height(n.right)) + 1);
    }

    public void remove(T v) {
        Node<T> x = getNode(v);
        if (x == null) return;
        if (x.left != null && x.right != null) {
            Node<T> p = x.right;
            for (; p.left != null; p = p.left) ;
            x.v = p.v;
            x = p;
        }
        doRemoveLeafOrSingleBranch(x);
        if (x.parent != null) {
            doBalance(x.parent, false);
        }
    }

    private Node<T> getNode(T v) {
        if (root == null) return null;
        for (Node<T> p = root; p != null; ) {
            int dir;
            if ((dir = p.v.compareTo(v)) < 0) {
                p = p.left;
            } else if (dir > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private void doRemoveLeafOrSingleBranch(Node<T> x) {
        Node<T> xp = x.parent;
        if (x.left == null && x.right == null) {
            if (xp == null) {
                root = null;
            } else if (xp.left == x) {
                xp.left = null;
            } else {
                xp.right = null;
            }

        } else if (x.left == null) {
            if (xp == null) {
                root = x.right;
                root.parent = null;
            } else if (xp.left == x) {
                xp.left = x.right;
                x.right.parent = xp;
            } else {
                xp.right = x.right;
                x.right.parent = xp;
            }
        } else if (x.right == null) {
            if (xp == null) {
                root = x.left;
                root.parent = null;
            } else if (xp.left == x) {
                xp.left = x.left;
                x.left.parent = xp;
            } else {
                xp.right = x.left;
                x.left.parent = xp;
            }
        } else {
            throw new IllegalArgumentException("该结点下左右子树都不为空");
        }

    }
}
