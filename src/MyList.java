import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Generic version of the LinkedList class.
 *
 * @param <T> the type of the value
 */
public class MyList<T> implements Iterable<T> {
    /**
     * Head node, default is null
     */
    Node<T> head;
    /**
     * Tail node, default is null
     */
    Node<T> tail;
    private int length;

    /**
     * Default constructor
     */
    public MyList() {
    }

    /**
     * Constructor with head
     *
     * @param head Head node of this list
     */
    public MyList(Node<T> head) {
        this.head = this.tail = head;
        length = 1;
    }

    /**
     * Constructor with head and tail
     *
     * @param head Head node of this list
     * @param tail Tail node of this list
     */
    public MyList(Node<T> head, Node<T> tail) {
        this.head = head;
        this.tail = tail;
        length = 2;
    }

    /**
     * Checking if this list is empty
     *
     * @return true if list is empty
     */
    public boolean isEmpty() {
        return length == 0;
    }

    /**
     * Returning the length of this list
     *
     * @return The length of this list
     */
    public int length() {
        return length;
    }

    /**
     * Insert an item to the head of this list
     *
     * @param item The item to be inserted
     */
    public void insertToHead(T item) {
        Node<T> newNode = new Node<>(item, head);

        head = newNode;
        if (isEmpty()) {
            tail = newNode;
        }

        length++;
    }

    /**
     * Insert an item to the tail of this list
     *
     * @param item The item to be inserted
     */
    public void insertToTail(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        length++;
    }

    /**
     * Insert an item at position to this list
     *
     * @param position The position of new item
     * @param item     The item to be inserted
     * @throws IndexOutOfBoundsException If the position is out of bounds
     */
    public void insertAfterPosition(int position, T item) throws IndexOutOfBoundsException {
        if (position < 0 || position > length) {
            throw new IndexOutOfBoundsException("Position: " + position + ", Length: " + length);
        }

        if (position == 0) {
            insertToHead(item);
        } else if (position == length) {
            insertToTail(item);
        } else {
            Node<T> newNode = new Node<>(item);

            int i = 0;
            Node<T> p = head;
            while (i < position - 1) {
                p = p.next;
                i++;
            }

            newNode.next = p.next;
            p.next = newNode;
            length++;
        }
    }

    /**
     * Deleting the tail of this list
     *
     * @throws NoSuchElementException If this list is empty
     */
    public void deleteTail() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        if (length == 1) {
            clear();
        } else {
            int i = 0;
            Node<T> p = head;
            while (i < length - 2) {
                p = p.next;
                i++;
            }

            p.next = null;
            tail = p;
            length--;
        }
    }

    /**
     * Searching and deleting an item from this list
     *
     * @param item The item to be deleted
     */
    public void deleteElement(T item) {
        if (isEmpty()) {
            return;
        }

        Node<T> p = head;
        if (p.info.equals(item)) {
            if (length == 1) {
                clear();
            } else {
                head = head.next;
                length--;
            }
        } else {
            while (p.next != null) {
                if (p.next.info.equals(item)) {
                    p.next = p.next.next;
                    length--;

                    return;
                }

                p = p.next;
            }
        }
    }

    /**
     * Append another list to the list
     *
     * @param list The list to be appended
     */
    public void append(MyList<T> list) {
        if (list.isEmpty()) {
            return;
        }

        if (this.isEmpty()) {
            this.head = list.head;
        } else {
            this.tail.next = list.head;
        }

        this.tail = list.tail;
        this.length += list.length;
    }

    /**
     * Swapping two nodes [firstNode] and [secondNode]
     *
     * @param firstNode  The first node
     * @param secondNode The second node
     */
    public void swap(Node<T> firstNode, Node<T> secondNode) {
        T temp = firstNode.info;

        firstNode.info = secondNode.info;
        secondNode.info = temp;
    }

    /**
     * Deleting all items in the list
     */
    public void clear() {
        head = tail = null;
        length = 0;
    }

    /**
     * Iterator of this list
     *
     * @return Iterator of this list
     */
    @Override
    public Iterator<T> iterator() {
        return new MyIterator<>(this);
    }
}