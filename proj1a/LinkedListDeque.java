public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T data;
        private Node next;

        public Node(Node prev, T data, Node next) {
            this.prev = prev;
            this.data = data;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(T item) {
        // create sentinel node
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

        addLast(item);
    }

    public void addFirst(T item) {
        Node node = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    public void addLast(T item) {
        Node node = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        Node p = sentinel.next;
        for (int i = 0; i < size - 1; i+=1) {
            System.out.print(p.data.toString() + ' ');
            p = p.next;
        }
        System.out.println(p.data.toString());
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T data = sentinel.next.data;
        sentinel.next.next.prev = sentinel.next.prev;
        sentinel.next = sentinel.next.next;
        return data;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T data = sentinel.prev.data;
        sentinel.prev.prev.next = sentinel.prev.next;
        sentinel.prev = sentinel.prev.prev;
        return data;
    }

    public T get(int index) {
        if (isEmpty() || index < 0 || index > size) {
            return null;
        }
        Node p = sentinel.next;
        for (int i = 0; i < index; i += 1) {
            p = p.next;
        }

        return p.data;
    }

    private T getRecursive(Node p, int index) {
        if (index == 0) {
            return p.data;
        }
        index -= 1;
        p = p.next;
        return getRecursive(p, index);
    }
    public T getRecursive(int index) {
        if (isEmpty() || index < 0 || index > size) {
            return null;
        }
        Node p = sentinel.next;
        return getRecursive(p, index);
    }
 }
