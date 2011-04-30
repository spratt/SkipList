/******************************************************************************
* Skiplist                                                                    *
*                                                                             *
* View README file for information about this project.                        *
* View LICENSE file for license information.                                  *
******************************************************************************/

import java.util.*;

public class SkipList<E>
    extends AbstractSortedSet<E> {
    private SkipListNode head;
    private int size;
    
    public SkipList() {
	size = 0;
	// a skiplistnode with value null marks the beginning
	head = new SkipListNode(null);
	// a null skiplistnode marks the end
	head.nextNodes.add(null); 
    }
    
    public boolean add(E e) {
	SkipListNode prevNode = find(e);
	if(((Comparable)e).compareTo(prevNode.getValue()) == 0) return false;
	size++;
	SkipListNode newNode = new SkipListNode(e);
	return true;
    }

    // Returns either
    //  - the skiplist node with value e
    //  - the skiplist node with greatest value less than e
    private SkipListNode find(E e) {
	SkipListNode current = null;
	SkipListNode next = head;
	while(next != null) {
	    current = next;
	    next = findNext(e,current);
	}
	return current;
    }

    private SkipListNode findNext(E e, SkipListNode current) {
	for(int i = current.nextNodes.size()-1; i >= 0; i--) {
	    SkipListNode next = current.nextNodes.get(i);
	    if(next != null) {
		E value = next.getValue();
		if(((Comparable)e).compareTo(value) >= 0)
		    return next;
	    }
	}
	return null;
    }
    
    public int size() {
	return size;
    }
    
    class SkipListNode {
	private E value;
	public List<SkipListNode> nextNodes;
	
	public E getValue() {
	    return value;
	}
	
	public SkipListNode(E value) {
	    this.value = value;
	    nextNodes = new ArrayList<SkipListNode>();
	}
    }

    // Testing
    public static void main(String[] args) {
	SkipList testList = new SkipList<Integer>();
	System.out.println(testList.find(2).getValue() == null);
    }
}