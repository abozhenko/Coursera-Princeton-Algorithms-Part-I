/**
 * Created by Andrew on 1/9/2017.
 */

// import edu.princeton.cs.algs4.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private class Node
    {
        private Node previous;
        private Item item;
        private Node next;
    }

    private Node head, tail;
    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return head == null;
    }

    // return the number of items on the deque
    public int size() {
        return this.size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        validateInputItem(item);

        Node node = new Node();
        node.item = item;
        node.next = head;
        if (head != null) {
            head.previous = node;
        }
        head = node;
        this.size++;

        if (tail == null) {
            tail = head;
        }
    }

    // add the item to the end
    public void addLast(Item item) {
        validateInputItem(item);

        Node node = new Node();
        node.item = item;
        node.previous = tail;
        if (tail != null) {
            tail.next = node;
        }
        tail = node;
        this.size++;

        if (head == null) {
            head = tail;
        }
    }

    // remove and return the item from the front
    public Item removeFirst() {
        validateForEmptiness();

        Item value = head.item;
        head = head.next;
        this.size--;

        if (head != null) {
            head.previous = null;
        } else {
            tail = null;
        }

        return value;
    }

    // remove and return the item from the end
    public Item removeLast() {
        validateForEmptiness();

        Item value = tail.item;
        tail = tail.previous;
        this.size--;

        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        return value;
    }

    // return an iterator over items in order from front to end
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item>
    {
        private Node current = head;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException(
                    "remove operation is not supported for deque iterator");
        }

        public Item next()
        {
            if (!hasNext()) {
                throw new NoSuchElementException(
                        "Iteration is over. No more elements are available");
            }

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    private void validateInputItem(Item item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        }
    }

    private void validateForEmptiness() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                    "item cannot be removed from empty deque");
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
//        Deque<Integer> deque = new Deque<Integer>();
    }
}
