package per.gh.study.datastructure.list;

public class DoubleLinkedList<T> implements MyList<T> {
    private class Node<T> {
        T data;
        Node<T> prev, next;

        public Node(T data, Node<T> prev, Node<T> next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<T> head, tail;
    private int size = 0;

    public DoubleLinkedList() {
        head = new Node<>(null, null, null);
        tail = head;
    }

    @Override
    public void add(T t) {
        Node<T> node = new Node<>(t, tail, null);
        tail.next = node;
        tail = node;
        size++;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void set(int index, T t) {
        getNodeByIndex(index).data = t;
    }

    @Override
    public boolean remove(int index) {
        if (index < size) {
            Node<T> node = getNodeByIndex(index);
            if (index == size - 1) {
                tail = node.prev;
                tail.next = null;
            } else if (index == 0) {
                head = node;
                head.prev = null;
                head.data = null;
            } else {
                Node<T> prev = node.prev;
                prev.next = node.next;
                node.next.prev = prev;
            }
            size--;
            return true;
        }
        return false;
    }

    @Override
    public boolean isEmpty() {
        return head == tail;
    }

    private Node<T> getNodeByIndex(int index) {
        if (index < size) {
            Node<T> cNode;
            int count = 0;
            if (index < size / 2) {
                for (cNode = head.next; ; count++, cNode = cNode.next) {
                    if (count == index) {
                        return cNode;
                    }
                }
            } else {
                for (cNode = tail, count = size - 1; ; count--, cNode = cNode.prev) {
                    if (count == index) {
                        return cNode;
                    }
                }
            }
        }
        throw new IndexOutOfBoundsException();
    }
}
