/*
 * Name: Radhika Ranasinghe
 * IIT ID: 2018199
 * UoW ID: W1761764
 */

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * This class mimics the role of a row of adjacency list
 */
public class EdgeList<Item> implements Iterable<Item> {
    private Node<Item> firstItem;    // beginning of Adjacency List
    private int num;               // number of elements in Adjacency List

    /**
     * This class is helper linked list class
     *
     * @param <Item>
     */
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        /**
         * toString method of the Node class
         *
         * @return a string containing a Node
         */
        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }

    }

    /**
     * Constructor of the EdgeList
     */
    public EdgeList() {
        firstItem = new Node<>();
        num = 0;
    }

    /**
     * checks if the firstItem is a null value
     *
     * @return returns a boolean containing the firstItem null or not
     */
    public boolean isEmpty() {
        return firstItem == null;
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
     * Method to add an item to the EdgeList
     *
     * @param item The item to be added to the EdgeList
     */
    public void add(Item item) {
        if (num == 0) {
            firstItem = new Node<Item>();
            firstItem.item = item;
        } else {
            Node<Item> oldFirst = firstItem;
            firstItem = new Node<Item>();
            firstItem.item = item;
            firstItem.next = oldFirst;
        }
        num++; //Item in the list gets increased by one
    }


    /**
     * iterator method
     * @return a LinkedIterator containing the firstItem
     */
    public Iterator<Item> iterator() {
        return new LinkedIterator(firstItem);
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
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    /**
     * toString method of the class EdgeList
     *
     * @return a string containing an EdgeList
     */
    @Override
    public String toString() {
        return "AdjacencyList{" +
                "firstItem=" + firstItem +
                ", num=" + num +
                '}';
    }

    /**
     * equals method of the class EdgeList
     *
     * @param o object to be checked
     * @return boolean containing if its equal or not
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EdgeList)) return false;
        EdgeList<?> that = (EdgeList<?>) o;
        return num == that.num && Objects.equals(firstItem, that.firstItem);
    }

}


