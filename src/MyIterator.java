import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator for MyList
 *
 * @param <T> the type of the value
 */
class MyIterator<T> implements Iterator<T> {
    Node<T> current;

    /**
     * Constructor with MyList
     *
     * @param list the MyList to iterate over
     */
    public MyIterator(MyList<T> list) {
        current = list.head;
    }

    /**
     * Checking if the iteration has more elements
     *
     * @return True if the iteration has more elements
     */
    public boolean hasNext() {
        return current != null;
    }

    /**
     * Returns the next element in the iteration
     *
     * @return The next element in the iteration
     * @throws NoSuchElementException if the iteration has no more elements
     */
    public T next() throws NoSuchElementException {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        T data = current.info;
        current = current.next;
        return data;
    }
}