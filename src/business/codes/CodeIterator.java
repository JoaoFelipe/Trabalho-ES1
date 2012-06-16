package business.codes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CodeIterator implements Iterator<Object[]>, Iterable<Object[]> {

    private List<Object[]> currentList;
    private int currentIndex;

    public CodeIterator(Collection<Code> codes) {
        currentList = new ArrayList<Object[]>();
        for (Code code : codes) {
            if (!code.isActivated()) {
                currentList.add(Arrays.asList(code.getKey(), code.getValue()).toArray());
            }
        }

        Collections.sort(currentList, new Comparator<Object[]>() {

            public int compare(Object[] o1, Object[] o2) {
                return Integer.valueOf(o1[1].toString()).compareTo(Integer.parseInt(o2[1].toString()));
            }
        });
        currentIndex = -1;

    }

    public Object[] next() {
        if (currentIndex == currentList.size() - 1) {
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
