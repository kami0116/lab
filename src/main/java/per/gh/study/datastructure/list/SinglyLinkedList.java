package per.gh.study.datastructure.list;

public class SinglyLinkedList<T> implements MyList<T> {
    private Node<T> head, tail;
    private int size = 0;

    private class Node<T> {
        Node<T> next;
        T data;

        Node(T data, Node<T> next) {
            this.next = next;
            this.data = data;
        }
    }

    public SinglyLinkedList() {
        head = new Node<>(null, null);
        tail = head;
    }

    @Override
    public void add(T t) {
        Node<T> node = new Node<>(t, null);
        tail.next = node;
        tail = node;
        size++;
    }

    @Override
    public T get(int index) {
        return getNodeByIndex(index).data;
    }

    private Node<T> getNodeByIndex(int index) {
        if (index < size) {
            int count = 0;
            for (Node<T> c = head.next; c != null; c = c.next, count++) {
                if (count == index)
                    return c;
            }
        }
        throw new IndexOutOfBoundsException();
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
            if (index == 0) {
                head = head.next;
                head.data = null;
            } else {
                Node<T> preNode = getNodeByIndex(index - 1);
                preNode.next = preNode.next.next;
                if (size == index + 1) {
                    tail = preNode;
                }
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
}
