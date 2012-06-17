package business.users;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class ProducerIterator implements Iterator<Object[]>, Iterable<Object[]> {

    private List<Object[]> currentList;
    private int currentIndex;

    public ProducerIterator(Collection<User> users) {
        currentList = new ArrayList<Object[]>();
        for (User user : users) {
            if (user instanceof Producer) {
                Producer producer = (Producer) user;
                currentList.add(Arrays.asList(producer.getName(), producer.getEmail(), producer.getLogin(), producer).toArray());
            }
        }
        currentIndex = -1;
    }

    public Object[] next() {
        if (currentIndex == currentList.size() -1) {
            throw new NoSuchElementException();
        }
        currentIndex += 1;
        return this.getCurrent();
    }

    public boolean hasNext() {
        return currentIndex < currentList.size() - 1;
    }

    public Object[] getCurrent() {
        return currentList.get(currentIndex);
    }

    public Iterator<Object[]> iterator() {
        currentIndex = -1;
        return this;
    }

    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
