import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * User: outzider
 * Date: 2/16/14
 * Time: 6:36 PM
 */
public class DequeTest {

    @Test(expected = NullPointerException.class)
    public void addFirstNullItemToDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(null);
    }

    @Test(expected = NullPointerException.class)
    public void addLastNullItemToDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeFirstItemFromEmptyDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.removeFirst();
    }

    @Test(expected = NoSuchElementException.class)
    public void removeLastItemFromEmptyDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.removeLast();
    }

    @Test(expected = UnsupportedOperationException.class)
    public void callRemoveOnIterator() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.iterator().remove();
    }

    @Test(expected = NoSuchElementException.class)
    public void nextOnEmptyDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.iterator().next();
    }

    @Test
    public void returnCorrectSize() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(2, deque.size());
    }

    @Test
    public void isEmptyOnFilledDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.addLast(2);
        assertFalse(deque.isEmpty());
    }

    @Test
    public void addItemsFirstInOrder() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(3);
        deque.addFirst(2);
        deque.addFirst(1);
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(new Integer(2), deque.removeFirst());
        assertEquals(new Integer(3), deque.removeFirst());
    }

    @Test
    public void addItemsLastInOrder() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        assertEquals(new Integer(3), deque.removeLast());
        assertEquals(new Integer(2), deque.removeLast());
        assertEquals(new Integer(1), deque.removeLast());
    }

    @Test
    public void iteratorHasNextFilledDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        Iterator<Integer> iterator = deque.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void iteratorHasNextEmptyDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        Iterator<Integer> iterator = deque.iterator();
        assertFalse(iterator.hasNext());
    }

    @Test
    public void iterator() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        Iterator<Integer> iterator = deque.iterator();
        assertEquals(new Integer(1), iterator.next());
    }

    @Test
    public void addRemoveAddItemFirst() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.removeFirst();
        deque.addFirst(1);
        deque.addFirst(2);
        assertEquals(2, deque.size());
        assertEquals(new Integer(2), deque.removeFirst());
        assertEquals(new Integer(1), deque.removeFirst());
    }

    @Test
    public void addRemoveAddItemLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.removeLast();
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(2, deque.size());
        assertEquals(new Integer(2), deque.removeLast());
        assertEquals(new Integer(1), deque.removeLast());
    }

    @Test
    public void randomlyAddAndRemoveFirstAndLast() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addFirst(1);
        deque.removeLast();
        deque.addFirst(1);
        deque.addLast(2);
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(new Integer(2), deque.removeLast());
        deque.addFirst(3);
        assertEquals(new Integer(3), deque.removeLast());
        deque.addLast(1);
        deque.addLast(2);
        assertEquals(new Integer(1), deque.removeFirst());
        assertEquals(new Integer(2), deque.removeFirst());

    }

    @Test
    public void iteratorForEach() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        int currentNumber = 1;
        for (Integer integer : deque) {
            assertEquals(new Integer(currentNumber++), integer);
        }
    }

    @Test
    public void iteratorForEachEmptyDeque() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        deque.addLast(3);
        deque.addLast(4);
        int currentNumber = 1;
        for (Integer integer : deque) {
            assertEquals(new Integer(currentNumber++), integer);
        }
    }

    @Test
    public void multipleUniqueIterators() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        deque.addLast(2);
        Iterator<Integer> iterator1 = deque.iterator();
        Iterator<Integer> iterator2 = deque.iterator();
        assertEquals(new Integer(1), iterator1.next());
        assertEquals(new Integer(1), iterator2.next());
    }

    @Test
    public void addNItemsAndRemoveNItemsWithRandomOperations() {
        Deque<Integer> deque = new Deque<Integer>();
        for (int i = 0; i < 3000; i++) {
            randomInsertOperation(deque, i);
        }

        for (int i = 0; i < 3000; i++) {
            randomRemoveOperation(deque);
        }
    }

    private void randomInsertOperation(Deque<Integer> deque, Integer integer) {
        int type = StdRandom.uniform(1);
        switch (type) {
            case 0:
                deque.addFirst(integer);
                break;
            case 1:
                deque.addLast(integer);
                break;
        }
    }

    private void randomRemoveOperation(Deque<Integer> deque) {
        int type = StdRandom.uniform(1);
        switch (type) {
            case 0:
                deque.removeFirst();
                break;
            case 1:
                deque.removeLast();
                break;
        }
    }
}