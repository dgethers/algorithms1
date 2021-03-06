import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * User: outzider
 * Date: 2/16/14
 * Time: 2:22 PM
 */
public class Deque<Item> implements Iterable<Item> {

    private class Node {
        private Item data;
        private Node prev, next;

        @Override
        public String toString() {
            return String.format("[%s]", data);
        }
    }

    private class LinkedList {
        private Node sentinel;

        private LinkedList() {
            sentinel = new Node();
            sentinel.prev = sentinel;
            sentinel.next = sentinel;
        }

        public void addToFront(Item data) {
            Node tmp = new Node();
            tmp.data = data;

            tmp.next = sentinel.next;
            sentinel.next.prev = tmp;
            sentinel.next = tmp;
            tmp.prev = sentinel;
        }

        public void addToRear(Item data) {
            Node tmp = new Node();
            tmp.data = data;

            tmp.prev = sentinel.prev;
            tmp.next = sentinel;
            sentinel.prev.next = tmp;
            sentinel.prev = tmp;
        }

        public Item popFromFront() {
            Node tmp = sentinel.next;

            if (tmp == sentinel) {
                return null;
            }

            tmp.next.prev = sentinel;
            sentinel.next = tmp.next;

            return tmp.data;
        }

        public Item popFromRear() {
            Node tmp = sentinel.prev;

            if (tmp == sentinel) {
                return null;
            }

            tmp.prev.next = sentinel;
            sentinel.prev = tmp.prev;

            return tmp.data;
        }

    }

    private LinkedList linkedList;
    private int size;

    // construct an empty deque
    public Deque() {
        linkedList = new LinkedList();
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // insert the item at the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        linkedList.addToFront(item);
        size++;
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        linkedList.addToRear(item);
        size++;
    }

    // delete and return the item at the front
    public Item removeFirst() {
        Item item = linkedList.popFromFront();
        if (item == null) {
            throw new NoSuchElementException();
        }

        size--;

        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {
        Item item = linkedList.popFromRear();
        if (item == null) {
            throw new NoSuchElementException();
        }

        size--;

        return item;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator(linkedList.sentinel);
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current;

        public DequeIterator(Node sentinel) {
            current = sentinel;
        }

        @Override
        public boolean hasNext() {
            return current.next != linkedList.sentinel;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            current = current.next;
            return current.data;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}