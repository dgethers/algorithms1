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
}
