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
        private Node next;
        private Node prev;

        private Node(Item data, Node next) {
            this.data = data;
            this.next = next;
        }

        private Node(Item data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        @Override
        public String toString() {
            return String.format("[%s]", data);
        }
    }

    private class SinglyLinkedList {
        private Node head;
        private Node tail;

        public void addToFront(Item data) {
            Node tmp = new Node(data, head);
            tmp.next = head;

            if (tail == null) {
                tail = tmp;
            }

            if (head != null) {
                head.prev = tmp;
            }

            head = tmp;
        }

        public void addToRear(Item data) {
            Node tmp = new Node(data, null);

            if (head == null) {
                head = tmp;
                tail = tmp;
            } else {
                tail.next = tmp;
                tmp.prev = tail;
                tail = tmp;
            }
        }

        /*public void printNodes() {
            Node next = head;

            while (next != null) {
                System.out.print(next.data);
                next = next.next;
            }
            System.out.println();
        }*/

        public Item popFromFront() {
            Node tmp = head;

            if (tmp != null) {
                head = head.next;

                if (head != null) {
                    head.prev = null;
                } else {
                    tail = null;
                }

                return tmp.data;
            }

            return null;
        }

        public Item popFromRear() {
            Node tmp = tail;

            if (tmp != null) {
                Node prev = tmp.prev;

                if (prev != null) {
                    prev.next = null;
                } else {
                    tail = null;
                    head = null;
                }
                tail = prev;

                return tmp.data;
            }

            return null;
        }

    }

    private SinglyLinkedList linkedList;
    private int size;

    // construct an empty deque
    public Deque() {
        linkedList = new SinglyLinkedList();
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
//        linkedList.printNodes();
    }

    // insert the item at the end
    public void addLast(Item item) {
        if (item == null) {
            throw new NullPointerException();
        }

        linkedList.addToRear(item);
        size++;
//        linkedList.printNodes();
    }

    // delete and return the item at the front
    public Item removeFirst() {

        Item item = linkedList.popFromFront();
        if (item == null) {
            throw new NoSuchElementException();
        }

        size--;
//        linkedList.printNodes();
        return item;
    }

    // delete and return the item at the end
    public Item removeLast() {

        Item item = linkedList.popFromRear();
        if (item == null) {
            throw new NoSuchElementException();
        }

        size--;
//        linkedList.printNodes();
        return item;

    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {

        return new DequeIterator(linkedList.head);
    }

    private class DequeIterator implements Iterator<Item> {
        Node current;

        private DequeIterator(Node current) {
            this.current = current;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item;

            if (hasNext()) {
                item = current.data;
                current = current.next;
            } else {
                throw new NoSuchElementException();
            }

            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing
    public static void main(String[] args) {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(4);
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        deque.removeFirst();
        deque.addFirst(1);
        deque.removeLast();
        deque.addLast(4);
        deque.removeLast();
        deque.removeLast();
    }

}
