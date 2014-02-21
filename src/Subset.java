/**
 * User: outzider
 * Date: 2/21/14
 * Time: 6:09 PM
 */
public class Subset {

    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        RandomizedQueue<String> deque = new RandomizedQueue<String>();

        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            deque.enqueue(item);
        }

        for (int i = 0; i < count; i++) {
            StdOut.print(deque.dequeue());
        }
    }
}
