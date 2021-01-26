package per.gh.study.datastructure.tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RedBlackTree<V extends Comparable> {
    class Node<V> {
        Node<V> left, right, parent;
        boolean red;
        V v;

        public Node(V v) {
            this.v = v;
            this.red = true;
        }
    }

    private Node<V> root;

    public void add(V v) {
        Node<V> x = new Node<>(v);
        if (root == null) {
            root = x;
            x.red = false;
        } else {
            for (Node<V> p = root; ; ) {
                if (v.compareTo(p.v) < 0) {
                    if (p.left == null) {
                        p.left = x;
                        x.parent = p;
                        break;
                    } else {
                        p = p.left;
                    }
                } else {
                    if (p.right == null) {
                        p.right = x;
                        x.parent = p;
                        break;
                    } else {
                        p = p.right;
                    }
                }
            }
            balance(x);
        }
    }

    public boolean remove(V v) {
        Node<V> x = getNode(v);
        if (x == null) return false;
        removeNode(x);
        return true;
    }

    private void removeNode(Node<V> x) {
        Node<V> xp = x.parent, xb = x == xp.left ? xp.right : xp.left;
        if (x.left == null && x.right == null) {
            if (x.red) {// x red, xb red
                if (x == x.parent.left) {
                    xp.left = null;
                } else {
                    xp.right = null;
                }
            } else if (!xb.red && xp.red) {// x black, xb black, xp red
                if (x == x.parent.left) {
                    xp.left = null;
                } else {
                    xp.right = null;
                }
                xb.red = true;
                xp.red = false;
            } else if (xb.red) {// x black, xb red, xp black
                if (x == xp.left) {
                    // left rotate
                    xp.left = null;
                    leftRotate(xp);
                    xp.right.red = true;
                } else {
                    // right rotate
                    xp.right = null;
                    rightRotate(xp);
                    xp.left.red = true;
                }
                xb.red = false;
            } else {// x black, xb black, xp black
                xb.red = true;
                balanceAfterRemovedABlackNode(xp);
            }
        } else if (x.left == null) {
            x.v = x.right.v;
            x.right = null;
        } else if (x.right == null) {
            x.v = x.left.v;
            x.left = null;
        } else {
            Node<V> p = x.right;
            for (; p.left != null; p = p.left) ;
            x.v = p.v;
            removeNode(p);
        }
    }

    private void balanceAfterRemovedABlackNode(Node<V> x) {
        if (x == root) {
            return;
        }
        Node<V> xp = x.parent, xb = x == xp.left ? xp.right : xp.left;
        boolean left = xp.left == x;

        if (xp.red) {
            xp.red = false;
            xb = x;
            left = !left;
        }

        if (xb.red) {//x black, xp black, xb red
            if (left) {
                leftRotate(xp);
                xp.right.red = true;
            } else {
                rightRotate(xp);
                xp.left.red = true;
            }
            xb.red = false;
            return;
        } else if (!xb.left.red && !xb.right.red) {
            xb.red = true;
            balanceAfterRemovedABlackNode(xp);
            return;
        } else if (left && !xb.right.red) {
            rightRotate(xb);
        } else if (!left && !xb.left.red) {
            leftRotate(xb);
        }
        xb.red = true;
        xb.parent.red = false;
        if (left) {
            leftRotate(xp);
            xb.right.red = false;
        } else {
            rightRotate(xp);
            xb.left.red = false;
        }
    }

    public void balance(Node<V> x) {
        Node<V> xp, xpp, xppl, xppr;
        if (x == root) {
            x.red = true;
            return;
        }
        for (; (xp = x.parent) != null && xp.red; ) {
            if ((xpp = xp.parent) == null) {
                xp.red = false;
                return;
            }
            xppl = xpp.left;
            xppr = xpp.right;
            if ((xppl == xp && xppr != null && xppr.red) || (xppr == xp && xppl != null && xppl.red)) {//uncle为红，变色
                xppl.red = false;
                xppr.red = false;
                if (xpp != root) {
                    xpp.red = true;
                    x = xpp;
                    continue;
                } else {
                    break;
                }

            } else {//uncle为黑，旋转
                //非直线，变直线
                boolean flag=false;
                if ((xppl == xp && x == xp.right)) {//非直线，左旋
                    leftRotate(xp);
                    flag=true;
                } else if ((xppr == xp && x == xp.left)) {//非直线，右旋
                    rightRotate(xp);
                    flag=true;
                }
                if (flag){
                    Node<V> t = x;
                    x = xp;
                    xp = t;
                }
                if (xppl == xp && x == xp.left) {//直线,右旋
                    rightRotate(xpp);
                    x.red = false;
                    xpp.red = false;
                    x = xp;
                } else if (xppr == xp && x == xp.right) {//直线，左旋
                    leftRotate(xpp);
                    x.red = false;
                    xpp.red = false;
                    x = xp;
                }
            }
        }
    }

    public void printTree() {
        int maxH = height(root);
        Node[] nodes = new Node[(int) (Math.pow(2, maxH) - 1)];
        set(nodes, root, 0);
        List[] displayNodes = new List[maxH * 2 - 1];

        // nodes
        for (int i = 0; i < nodes.length; i++) {
            Node n = nodes[i];
            if (n == null) {
                continue;
            }
            int h = (int) (Math.log(i + 1) / Math.log(2) + 1);
            int interval = interval(h, maxH);
            List list = displayNodes[(h - 1) * 2];
            if (list == null) {
                list = new LinkedList();
                displayNodes[(h - 1) * 2] = list;
            }
            int index = (int) (i + 1 - Math.pow(2, h - 1));
            list.add(interval / 2 - 2 + (interval + 2) * index);
            list.add(n);
        }
        // ref
        int lastCount = 0;
        for (int i = 1; i < maxH; i++) {
            List list = new ArrayList();
            displayNodes[i * 2 - 1] = list;
            for (int j = 0; j < displayNodes[i * 2].size(); j++) {
                Object o = displayNodes[i * 2].get(j);
                if (o instanceof Integer) {
                    lastCount = (int) o;
                } else {
                    Node n = (Node) o;
                    if (n.parent.left == n) {
                        list.add(lastCount + 2);
                        list.add("/");
                    } else {
                        list.add(lastCount - 1);
                        list.add("\\");
                    }
                }

            }
        }

        // print
        printSpace((Integer) displayNodes[0].get(0));
        printNode((Node) displayNodes[0].get(1));
        System.out.println();
        for (int i = 1; i < maxH; i++) {
            List refRow = displayNodes[i * 2 - 1];
            List nodeRow = displayNodes[i * 2];
            int lastIndex = 0;
            for (Object o : refRow) {
                if (o instanceof Integer) {
                    printSpace((Integer) o - lastIndex);
                    lastIndex = (int) o;
                } else {
                    System.out.print("\033[34;1m" + o + "\033[0m");
                    lastIndex++;
                }
            }
            System.out.println();
            lastIndex = 0;
            for (Object o : nodeRow) {
                if (o instanceof Integer) {
                    printSpace((Integer) o - lastIndex);
                    lastIndex = (int) o;
                } else {
                    printNode((Node) o);
                    lastIndex += 2;
                }
            }
            System.out.println();

        }
    }

    private int interval(int h, int maxH) {
        int i = maxH - h;
        return (int) (Math.pow(2, i + 2) + Math.pow(2, i + 1) - 2);
    }

    private void printSpace(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print(" ");
        }
    }

    private void printNode(Node n) {
        if (n.red) {
            System.out.print("\033[31;1m" + String.format("%02d", n.v) + "\033[0m");
        } else {
            System.out.print("\033[1m" + String.format("%02d", n.v) + "\033[0m");
        }

    }

    private void set(Node[] nodes, Node n, int no) {
        nodes[no] = n;
        if (n.left != null) {
            set(nodes, n.left, no * 2 + 1);
        }
        if (n.right != null) {
            set(nodes, n.right, no * 2 + 2);
        }
    }

    private int height(Node n) {
        return Math.max(n.left == null ? 0 : height(n.left), n.right == null ? 0 : height(n.right)) + 1;
    }

    private Node<V> getNode(V v) {
        for (Node<V> p = root; p != null; ) {
            int dir;
            if ((dir = v.compareTo(p.v)) < 0) {
                p = p.left;
            } else if (dir > 0) {
                p = p.right;
            } else {
                return p;
            }
        }
        return null;
    }

    private void leftRotate(Node<V> n) {
        Node<V> np = n.parent, nr = n.right;
        if (np == null) {
            root = nr;
            nr.parent = null;
        } else if (np.left == n) {
            np.left = nr;
            nr.parent = np;
        } else {
            np.right = nr;
            nr.parent = np;
        }
        n.right = nr.left;
        if (nr.left != null) nr.left.parent = n;

        nr.left = n;
        n.parent = nr;
    }

    private void rightRotate(Node<V> n) {
        Node<V> np = n.parent, nl = n.left;
        if (np == null) {
            root = nl;
            nl.parent = null;
        } else if (np.right == n) {
            np.right = nl;
            nl.parent = np;
        } else {
            np.left = nl;
            nl.parent = np;
        }
        n.left = nl.right;
        if (nl.right != null) nl.right.parent = n;

        nl.right = n;
        n.parent = nl;
    }
}
