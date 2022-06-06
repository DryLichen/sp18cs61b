public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int nextFirst = 4;
    private int nextLast = 5;
    private static final int FACTOR = 2;

    public ArrayDeque() {
        items = (T[]) new Object[8];
    }

    /** add and remove must take constant time */
    @Override
    public void addFirst(T item) {
        if (size + 1 > items.length) {
            resize(items.length * FACTOR);
        }
        items[nextFirst--] = item;
        nextFirst = stdIndex(nextFirst);
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size + 1 > items.length) {
            resize(items.length * FACTOR);
        }
        items[nextLast++] = item;
        nextLast = stdIndex(nextLast);
        size++;
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
        for (int i = 0; i < size; i++) {
            string.append(get(i));
            string.append(" ");
        }
        System.out.println(string);
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (float) (size - 1) / items.length < 0.25) {
            resize(items.length / FACTOR);
        }

        nextFirst = stdIndex(++nextFirst);
        T target = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return target;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && (float) (size - 1) / items.length < 0.25) {
            resize(items.length / FACTOR);
        }

        nextLast = stdIndex(--nextLast);
        T target = items[nextLast];
        items[nextLast] = null;
        size--;
        return target;
    }

    /** get must take constant time */
    @Override
    public T get(int index) {
        int realIndex = nextFirst + 1 + index;
        realIndex = stdIndex(realIndex);
        return items[realIndex];
    }

    /** Resize the array to target capacity */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = get(i);
        }
        items = newItems;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    /** make the index circular */
    private int stdIndex(int index) {
        if (index < 0) {
            index += items.length;
        }
        if (index > items.length - 1) {
            index -= items.length;
        }

        return index;
    }
}
