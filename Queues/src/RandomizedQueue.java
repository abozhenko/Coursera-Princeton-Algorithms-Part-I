
import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * Created by Andrew on 1/9/2017.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] data;
    private int len;

    // construct an empty randomized queue
    public RandomizedQueue() {
        this.data = (Item[]) new Object[1];
        this.len = 0;
    }

    // is the queue empty?
    public boolean isEmpty() {
        return len == 0;
    }

    // return the number of items on the queue
    public int size() {
        return len;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new NullPointerException("item cannot be null");
        }

        if (len == data.length) resize(2 * data.length);
        data[len++] = item;
    }

    // remove and return a random item
    public Item dequeue() {
        validateForEmptiness();

        int randomIndex = StdRandom.uniform(len);
        Item value = data[randomIndex];
        int lastIndex = len-1;
        data[randomIndex] = data[lastIndex];
        data[lastIndex] = null;
        len--;
        if (len > 0 && len == data.length/4) resize(data.length/2);
        return value;
    }

    // return (but do not remove) a random item
    public Item sample() {
        validateForEmptiness();

        int randomIndex = StdRandom.uniform(len);
        Item value = data[randomIndex];
        return value;
    }

    private void resize(int capacity)
    {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < len; i++)
            copy[i] = data[i];
        data = copy;
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class RandomizedQueueIterator implements Iterator<Item> {
        private int index = 0;
        private Item[] shuffledData;

        public RandomizedQueueIterator() {
            Object[] copy = new Object[len];
            for (int i = 0; i < len; i++)
                copy[i] = data[i];

            StdRandom.shuffle(copy);
            shuffledData = (Item[]) copy;
        }

        public boolean hasNext() {
            return index < shuffledData.length;
        }
        public void remove() {
            throw new UnsupportedOperationException(
                    "Remove is forbidden for RandomizedQueue's iterator");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException(
                    "Iteration is over. No more elements are available");
            return shuffledData[index++];
        }
    }

    private void validateForEmptiness() {
        if (isEmpty()) {
            throw new NoSuchElementException(
                    "Cannot dequeue from empty queue");
        }
    }

    // unit testing (optional)
    public static void main(String[] args) {
    }
}
