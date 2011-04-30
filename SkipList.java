/******************************************************************************
* Skiplist                                                                    *
*                                                                             *
* View README file for information about this project.                        *
* View LICENSE file for license information.                                  *
******************************************************************************/

import java.util.*;

public class SkipList<E> extends AbstractSortedSet<E> {
    private SkipListNode head;
    private int maxLevel;
    private int size;
    
    public SkipList() {
	size = 0;
	maxLevel = 0;
	// a skiplistnode with value null marks the beginning
	head = new SkipListNode(null);
	// a null skiplistnode marks the end
	head.nextNodes.add(null); 
    }

    // Adds e to the skiplist.
    // Returns false if already in skiplist, true otherwise.
    public boolean add(E e) {
	if(contains(e)) return false;
	size++;
	// random number from 0 to maxLevel+1 (inclusive)
	int level = (new Random()).nextInt(maxLevel+2); 
	while(level > maxLevel) { // should only happen once
	    head.nextNodes.add(null);
	    maxLevel++;
	}
	SkipListNode newNode = new SkipListNode(e);
	SkipListNode current = head;
      	do {
	    current = findNext(e,current,level);
	    newNode.nextNodes.add(0,current.nextNodes.get(level));
	    current.nextNodes.set(level,newNode);
	} while (level-- > 0);
	return true;
    }

    // Returns either
    //  - the skiplist node with value e
    //  - the skiplist node with greatest value less than e
    private SkipListNode find(E e) {
	return find(e,head,maxLevel);
    }

    private SkipListNode find(E e, SkipListNode start, int beginLevel) {
	SkipListNode current = start;
	SkipListNode next = null;
	int level = beginLevel;
	do {
	    current = findNext(e,current,level);
	} while(level-- > 0);
	return current;
    }

    // Returns the node at a given level with highest value less than e
    private SkipListNode findNext(E e, SkipListNode current, int level) {
        SkipListNode next = current.nextNodes.get(level);
	while(next != null) {
	    E value = next.getValue();
	    if(((Comparable)e).compareTo(value) > 0)
		break;
	    current = next;
	    next = current.nextNodes.get(level);
	}
	return current;
    }
    
    public int size() {
	return size;
    }

    public boolean contains(Object o) {
	SkipListNode node = find((E)o);
	return node != null &&
	    node.getValue() != null &&
	    ((Comparable)node.getValue()).compareTo(o) == 0;
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

	public int level() {
	    return nextNodes.size()-1;
	}

	public String toString() {
	    return "SLN: " + value;
	}
    }

    // Testing
    public static void main(String[] args) {
	SkipList testList = new SkipList<Integer>();
	System.out.println(testList.find(2));
	System.out.println(testList.contains(2));
	System.out.println(testList.add(2));
	//SkipList.SkipListNode sln = testList.find(2);
	//System.out.println(sln.nextNodes.get(0));
	System.out.println(testList.find(2));
	System.out.println(testList.contains(2));
    }
}