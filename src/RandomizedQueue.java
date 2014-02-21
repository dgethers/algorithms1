import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * User: outzider
 * Date: 2/16/14
 * Time: 5:06 PM
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] array;
    private int size;

    // construct an empty randomized queue
    @SuppressWarnings("unchecked")
    public RandomizedQueue() {
        array = (Item[]) new Object[2];
    }

    // is the queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        if (size == array.length) {
            resize(2 * array.length);
        }

        array[size++] = item;
    }

    // delete and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        //remove random position
        int removePosition = StdRandom.uniform(size);
        Item item = array[removePosition];

        //swap empty position with last known filled position
        array[removePosition] = array[size - 1];
        array[size - 1] = null;
        size--;

        // shrink size of array if necessary
        if (size > 0 && size == array.length / 4) {
            resize(array.length / 2);
        }

        return item;
    }

    // return (but do not delete) a random item
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        return array[StdRandom.uniform(size)];
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        assert capacity >= size;
        Item[] dest = (Item[]) new Object[capacity];
        System.arraycopy(array, 0, dest, 0, size);
        array = dest;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {

        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        Item[] items;
        int index;

        @SuppressWarnings("unchecked")
        public RandomizedQueueIterator() {
            items = (Item[]) new Object[size];
            System.arraycopy(array, 0, items, 0, size);
            StdRandom.shuffle(items);
        }

        @Override
        public boolean hasNext() {
            return index < items.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }

            return items[index++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
    }
}