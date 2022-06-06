public class LinkedListDeque<T> implements Deque<T>{
    private Node firstSentinel;
    private Node lastSentinel;
    private int size;

    // Nested class
    private class Node {
        private T item;
        private Node prev;
        private Node next;

        private Node() {
        }

        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    public LinkedListDeque() {
        firstSentinel = new Node();
        lastSentinel = new Node();
        firstSentinel.next = lastSentinel;
        lastSentinel.prev = firstSentinel;
    }

    @Override
    public void addFirst(T item) {
        size++;
        Node node = new Node(item, firstSentinel, firstSentinel.next);
        firstSentinel.next.prev = node;
        firstSentinel.next = node;
    }

    @Override
    public void addLast(T item) {
        size++;
        Node node = new Node(item, lastSentinel.prev, lastSentinel);
        lastSentinel.prev.next = node;
        lastSentinel.prev = node;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        StringBuilder string = new StringBuilder();
        Node p = firstSentinel.next;
        while (p.next != null) {
            string.append(p.item);
            string.append(" ");
            p = p.next;
        }
        System.out.print(string);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        size--;
        Node removeNode = firstSentinel.next;
        firstSentinel.next = removeNode.next;
        removeNode.next.prev = firstSentinel;
        return removeNode.item;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        size--;
        Node removeNode = lastSentinel.prev;
        lastSentinel.prev = removeNode.prev;
        removeNode.prev.next = lastSentinel;
        return removeNode.item;
    }

    /**
     * @return Get the ith item using iteration
     */
    @Override
    public T get(int index) {
        Node p = firstSentinel.next;
        for (int i = 0; i < index; i++) {
            p = p.next;
        }

        T target = p.item;
        return target;
    }

    /**
     * @return the ith item using recursion
     */
    public T getRecursive(int index) {
        return getRecursive(firstSentinel.next, index);
    }

    private T getRecursive(Node node, int index) {
        if (index == 0) {
            return node.item;
        }
        return getRecursive(node.next, index - 1);
    }
}
