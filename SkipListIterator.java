/******************************************************************************
* SkipListIterator                                                            *
*                                                                             *
* View README file for information about this project.                        *
* View LICENSE file for license information.                                  *
******************************************************************************/

import java.util.*;

public class SkipListIterator<E> implements Iterator<E> {
    SkipList<E> list;
    SkipListNode<E> current;
    
    public SkipListIterator(SkipList<E> list) {
	this.list = list;
	this.current = list.getHead();
    }

    public boolean hasNext() {
	return current.nextNodes.get(0) != null;
    }

    public E next() {
	current = (SkipListNode<E>)current.nextNodes.get(0);
	return (E)current.getValue();
    }

    public void remove() throws UnsupportedOperationException {
	throw new UnsupportedOperationException();
    }
}