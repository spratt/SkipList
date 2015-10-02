/******************************************************************************
* Skiplist                                                                    *
*                                                                             *
* View README file for information about this project.                        *
* View LICENSE file for license information.                                  *
******************************************************************************/

import java.util.*;

public class SkipList<E> extends AbstractSortedSet<E> {
    private SkipListNode<E> head;
    private int maxLevel;
    private int size;
    
    private static final double PROBABILITY = 0.5;
    
    public SkipList() {
	size = 0;
	maxLevel = 0;
	// a SkipListNode with value null marks the beginning
	head = new SkipListNode<E>(null);
	// null marks the end
	head.nextNodes.add(null); 
    }

    public SkipListNode getHead() {
	return head;
    }

    // Adds e to the skiplist.
    // Returns false if already in skiplist, true otherwise.
    public boolean add(E e) {
	if(contains(e)) return false;
	size++;
	// random number from 0 to maxLevel+1 (inclusive)
	int level = 0; 
	while (Math.random() < PROBABILITY)
		level++;
	while(level > maxLevel) { // should only happen once
	    head.nextNodes.add(null);
	    maxLevel++;
	}
	SkipListNode newNode = new SkipListNode<E>(e);
	SkipListNode current = head;
      	do {
	    current = findNext(e,current,level);
	    newNode.nextNodes.add(0,current.nextNodes.get(level));
	    current.nextNodes.set(level,newNode);
	} while (level-- > 0);
	return true;
    }

    // Returns the skiplist node with greatest value <= e
    private SkipListNode find(E e) {
	return find(e,head,maxLevel);
    }

    // Returns the skiplist node with greatest value <= e
    // Starts at node start and level
    private SkipListNode find(E e, SkipListNode current, int level) {
	do {
	    current = findNext(e,current,level);
	} while(level-- > 0);
	return current;
    }

    // Returns the node at a given level with highest value less than e
    private SkipListNode findNext(E e, SkipListNode current, int level) {
        SkipListNode next = (SkipListNode)current.nextNodes.get(level);
	while(next != null) {
	    E value = (E)next.getValue();
	    if(lessThan(e,value)) // e < value
		break;
	    current = next;
	    next = (SkipListNode)current.nextNodes.get(level);
	}
	return current;
    }
    
    public int size() {
	return size;
    }

    public boolean contains(Object o) {
	E e = (E)o;
	SkipListNode node = find(e);
	return node != null &&
	    node.getValue() != null &&
	    equalTo((E)node.getValue(),e);
    }

    public Iterator<E> iterator() {
	return new SkipListIterator(this);
    }
    
/******************************************************************************
* Utility Functions                                                           *
******************************************************************************/

    private boolean lessThan(E a, E b) {
	return ((Comparable)a).compareTo(b) < 0;
    }

    private boolean equalTo(E a, E b) {
	return ((Comparable)a).compareTo(b) == 0;
    }

    private boolean greaterThan(E a, E b) {
	return ((Comparable)a).compareTo(b) > 0;
    }

/******************************************************************************
* Testing                                                                     *
******************************************************************************/

    public static void main(String[] args) {
	SkipList testList = new SkipList<Integer>();
        System.out.println(testList);
	testList.add(4);
        System.out.println(testList);
	testList.add(1);
        System.out.println(testList);
        testList.add(2);
        System.out.println(testList);
	testList = new SkipList<String>();
        System.out.println(testList);
	testList.add("hello");
        System.out.println(testList);
	testList.add("beautiful");
        System.out.println(testList);
        testList.add("world");
        System.out.println(testList);
    }

    public String toString() {
	String s = "SkipList: ";
	for(Object o : this)
	    s += o + ", ";
	return s.substring(0,s.length()-2);
    }
}
