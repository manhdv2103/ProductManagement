import java.util.EmptyStackException;

/**
 * Generic version of the Stack class.
 *
 * @param <T> the type of the value
 */
class MyStack<T> {

    /**
     * Head node contains front node in the stack
     */
    Node<T> head;

    /**
     * Checking if this stack is empty
     *
     * @return true if stack is empty
     */
    boolean isEmpty() {
        return (head == null);
    }

    /**
     * Pushing an item onto the head of this stack
     *
     * @param item The item to be pushed onto the stack
     */
    void push(T item) {
        head = new Node<>(item, head);
    }

    /**
     * Removing and returning the head of this stack
     *
     * @return The head of this stack
     * @throws EmptyStackException If this stack is empty
     */
    T pop() throws EmptyStackException {
        if (isEmpty()) {
            throw new EmptyStackException();
        }

        T item = head.info;
        head = head.next;

        return item;
    }
}