/*
 * Name: Radhika Ranasinghe
 * IIT ID: 2018199
 * UoW ID: W1761764
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * This class mimics the first-in-first-out queue
 *
 * @author Radhika Ranasinghe
 */
public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int num;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    /**
     * Constructor of the Queue class
     */
    public Queue() {
        first = null;
        last = null;
        num = 0;
    }


    /**
     * Checks if the first element is a null value
     *
     * @return returns a boolean containing the firstItem null or not
     */
    public boolean isEmpty() {
        return first == null;
    }

    /**
     * Returns the size of the EdgeList
     *
     * @return int containing the size of the EdgeList
     */
    public int size() {
        return num;
    }

    /**
     * Returns the first item
     *
     * @return Item containing the first item
     */
    public Item peek() {
        return first.item;
    }

    /**
     * Adds an item to the queue
     *
     * @param item item that is to be added to the queue
     */
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) {
            first = last;
        } else {
            oldlast.next = last;
        }
        num++;
    }

    /**
     * Removes an item from the queue
     *
     * @return the item that is removed from the queue
     */
    public Item dequeue() {
        Item item = first.item;
        first = first.next;
        num--;
        if (isEmpty()) {
            last = null;
        }
        return item;
    }

    /**
     * toString method of the Queue class
     *
     * @return string containing the queue object
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    /**
     * iterator method
     *
     * @return a LinkedIterator containing the first item
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(first);
    }

    /**
     * LinkedIterator implementation
     */
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}