/******************************************************************************
* AbstractSortedSet                                                           *
*                                                                             *
* Extends AbstractSet and implements SortedSet, and contains stub methods     *
*                                                                             *
* View README file for information about this project.                        *
* View LICENSE file for license information.                                  *
******************************************************************************/

import java.util.*;

abstract class AbstractSortedSet<E>
    extends AbstractSet<E>
    implements SortedSet<E> {

    public E first() {
	return null;
    }

    public E last() {
	return null;
    }

    public Iterator<E> iterator() {
	return null;
    }

    public SortedSet<E> headSet(E toElement) {
	return null;
    }

    public SortedSet<E> tailSet(E fromElement) {
	return null;
    }

    public SortedSet<E> subSet(E fromElement, E toElement) {
	return null;
    }

    public Comparator<? super E> comparator() {
	return null;
    }
}