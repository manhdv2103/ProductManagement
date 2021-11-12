import java.util.NoSuchElementException;

/**
 * Generic version of the Queue class.
 *
 * @param <T> the type of the value
 */
class MyQueue<T> {

    /**
     * Head node contains front node in the queue
     */
    Node<T> head;

    /**
     * Tail node contains last node in the queue
     */
    Node<T> tail;

    /**
     * Checking if this queue is empty
     *
     * @return true if queue is empty
     */
    boolean isEmpty() {
        return (head == null);
    }

    /**
     * Adding an element to this queue
     *
     * @param item The element to be added
     */
    void enqueue(T item) {
        if (isEmpty()) {
            head = tail = new Node<>(item);
        } else {
            tail.next = new Node<>(item);
            tail = tail.next;
        }
    }

    /**
     * Removing and returning the head of this queue
     *
     * @return The head of this queue
     * @throws NoSuchElementException If this queue is empty
     */
    T dequeue() throws NoSuchElementException {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }

        T item = head.info;
        head = head.next;
        if (head == null) {
            tail = null;
        }

        return item;
    }
}