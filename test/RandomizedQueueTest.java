import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

/**
 * User: outzider
 * Date: 2/20/14
 * Time: 9:42 AM
 */
public class RandomizedQueueTest {


    @Test
    public void enqueueAndDequeue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        assertEquals(new Integer(1), randomizedQueue.dequeue());
    }

    @Test
    public void returnCorrectSizeEmptyQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        assertEquals(0, randomizedQueue.size());
    }

    @Test
    public void returnCorrectSizeFilledQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        assertEquals(2, randomizedQueue.size());
    }

    @Test
    public void isEmptyOnEmptyQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        assertTrue(randomizedQueue.isEmpty());
    }

    @Test
    public void isEmptyOnFilledQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        assertFalse(randomizedQueue.isEmpty());
    }

    @Test(expected = NullPointerException.class)
    public void addLastNullItemToDeque() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeItemFromEmptyQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.dequeue();
    }

    @Test(expected = NoSuchElementException.class)
    public void sampleItemFromEmptyQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.sample();
    }

    @Test(expected = NoSuchElementException.class)
    public void callNextOnIteratorOfEmptyQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        Iterator<Integer> iterator = randomizedQueue.iterator();
        iterator.next();
    }

    @Test
    public void callHasNextOnIteratorOfFilledQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        Iterator<Integer> iterator = randomizedQueue.iterator();
        assertTrue(iterator.hasNext());
    }

    @Test
    public void callNextOnIteratorOfFilledQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        Iterator<Integer> iterator = randomizedQueue.iterator();
        assertEquals(new Integer(1), iterator.next());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void callRemoveOnIterator() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        Iterator<Integer> iterator = randomizedQueue.iterator();
        iterator.remove();
    }

    @Test
    public void callNextOnIteratorWithUniqueResults() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10000; i++) {
            randomizedQueue.enqueue(i);
        }
        Iterator<Integer> iterator1 = randomizedQueue.iterator();
        Iterator<Integer> iterator2 = randomizedQueue.iterator();

        int random1 = iterator1.next();
        int random2 = iterator2.next();

        assertNotSame(random1, random2);
    }

    @Test
    public void callNextOnQueueForUniqueItems() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10000; i++) {
            randomizedQueue.enqueue(i);
        }

        int random1 = randomizedQueue.dequeue();
        int random2 = randomizedQueue.dequeue();
        assertNotSame(random1, random2);
    }

    @Test
    public void callEnqueueDequeueEnsuringCorrectSize() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 10; i++) {
            randomizedQueue.enqueue(i);
        }

        assertEquals(10, randomizedQueue.size());
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        assertEquals(7, randomizedQueue.size());
    }

    @Test
    public void emptyCollectionAndRefill() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.dequeue();
        randomizedQueue.enqueue(1);
        assertEquals(1, randomizedQueue.size());
    }

    @Test
    public void forEachFilledQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        int count = 0;
        for (Integer integer : randomizedQueue) {
            assertNotNull(integer);
            count++;
        }
        assertEquals(randomizedQueue.size(), count);
    }

    @Test
    public void forEachEmptyQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        for (Integer integer : randomizedQueue) {
            assertNull(integer);
        }
    }

    @Test
    public void sampleUniqueItemsInFilledQueue() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        for (int i = 1; i <= 1000; i++) {
            randomizedQueue.enqueue(i);
        }
        assertFalse(randomizedQueue.sample().equals(randomizedQueue.sample()));
        assertEquals(1000, randomizedQueue.size());
    }

    @Test
    public void confirmUniqueConstructorOfIterator() {
        RandomizedQueue<Integer> randomizedQueue = new RandomizedQueue<Integer>();
        randomizedQueue.enqueue(1);
        randomizedQueue.enqueue(2);
        randomizedQueue.enqueue(3);
        Iterator<Integer> iterator1 = randomizedQueue.iterator();
        int oldCount = 0;
        while (iterator1.hasNext()) {
            iterator1.next();
            oldCount++;
        }
        assertEquals(3, oldCount);
        randomizedQueue.dequeue();
        Iterator<Integer> iterator2 = randomizedQueue.iterator();
        int newCount = 0;
        while (iterator2.hasNext()) {
            iterator2.next();
            newCount++;
        }
        assertEquals(2, newCount);
    }
}
