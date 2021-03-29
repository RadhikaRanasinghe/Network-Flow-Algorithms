import java.util.Iterator;
import java.util.NoSuchElementException;

public class AdjacencyList<Item> implements Iterable<Item> {
    private Node<Item> firstItem;    // beginning of Adjacency List
    private int num;               // number of elements in Adjacency List

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;

        @Override
        public String toString() {
            return "Node{" +
                    "item=" + item +
                    ", next=" + next +
                    '}';
        }
    }

    public AdjacencyList() {
        firstItem = new Node<>();
        num = 0;
    }


    public boolean isEmpty() {
        return firstItem == null;
    }

    public int size() {
        return num;
    }

    public void add(Item item) {
        if (num == 0) {
            firstItem = new Node<Item>();
            firstItem.item = item;
        } else {
            Node<Item> oldfirst = firstItem;
            firstItem = new Node<Item>();
            firstItem.item = item;
            firstItem.next = oldfirst;
        }
        num++; //Item in the list gets increased by one
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator(firstItem);
    }

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

    @Override
    public String toString() {
        return "AdjacencyList{" +
                "firstItem=" + firstItem +
                ", num=" + num +
                '}';
    }

}


