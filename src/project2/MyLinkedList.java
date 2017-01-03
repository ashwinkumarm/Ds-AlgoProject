package project2;

import java.util.Scanner;

public class MyLinkedList<AnyType> implements Iterable<AnyType> {

	public void add(int idx, AnyType x) {
		addBefore(getNode(idx, 0, size()), x);
	}

	/**
	 * Construct an empty LinkedList.
	 */
	public MyLinkedList() {
		doClear();
	}

	private void clear() {
		doClear();
	}

	/**
	 * Change the size of this collection to zero.
	 */
	public void doClear() {
		beginMarker = new Node<>(null, null, null);
		endMarker = new Node<>(null, beginMarker, null);
		beginMarker.next = endMarker;

		theSize = 0;
		modCount++;
	}

	/**
	 * Returns the number of items in this collection.
	 * 
	 * @return the number of items in this collection.
	 */
	public int size() {
		return theSize;
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Adds an item to this collection, at the end.
	 * 
	 * @param x
	 *            any object.
	 * @return true.
	 */
	public boolean add(AnyType x) {
		add(size(), x);
		return true;
	}

	/**
	 *  method to swap the 2 elements for the 
	 *  given position.
	 * */
	public void swap(int i, int j) {
		Node<AnyType> node1 = getNode(i);
		AnyType val = node1.data;
		Node<AnyType> node2 = getNode(j);
		Node<AnyType> tempNode = new Node<>(null, null, null);
		Node<AnyType> tempNode2 = new Node<>(null, null, null);
		if (i < size() && j < size()) {
			tempNode = node1;
			tempNode.data = node2.data;
			tempNode2 = node2;
			tempNode2.data = val;
			node1.prev.next = tempNode;
			node2.prev.next = tempNode2;
		}
	}
	
	public void swapnew(int i, int j) {
		Node<AnyType> node1 = getNode(i);
		AnyType val = node1.data;
		Node<AnyType> node2 = getNode(j);
		AnyType val2 = node2.data;
		if (i < size() && j < size()) {
		if(i+1 != j){
		
		Node<AnyType> tempNode = new Node<>(val2, node1.prev, node1.next);
		Node<AnyType> tempNode2 = new Node<>(val, node2.prev, node2.next);
		node1.prev.next = tempNode;
		node2.prev.next = tempNode2;}
		else{
			
		}
		}
		/*
			tempNode = node1;
			tempNode.data = node2.data;
			tempNode2 = node2;
			tempNode2.data = val;
			node1.prev.next = tempNode;
			node2.prev.next = tempNode2;
		}*/
	}

	/**
	 * shifts the element based on the given value.
	 * */
	public void shift(int shift) {

		if (shift > 0) {
			while (shift > 0) {
				Node<AnyType> node = getNode(0);
				beginMarker.next = node.next;
				node.next.prev = beginMarker.next;
				System.out.println(endMarker.prev.data);
				node.prev = endMarker.prev;
				node.next = endMarker;
				endMarker.prev.next = node;
				endMarker.prev = node;
				shift--;
			}
		} else {
			while (Math.abs(shift) > 0) {
				Node<AnyType> nodeLast = endMarker.prev;
				nodeLast.prev.next = endMarker;
				endMarker.prev = nodeLast.prev;
				nodeLast.next = beginMarker.next;
				beginMarker.next = nodeLast;
				nodeLast.next.prev = nodeLast;
				nodeLast.prev = beginMarker;
				shift++;
			}
		}
	}

	/**
	 * erase the elements from the given index position 
	 * and the number of elements.
	 */
	public void erase(int idx, int size) {
		if ((idx + size) < size()) {
			Node<AnyType> nodePrev = getNode(idx - 1);
			Node<AnyType> nodeNext = getNode(idx + size);
			nodePrev.next = nodeNext;
			nodeNext.prev = nodeNext;

		}
	}

	/**
	 * inserts the given list from the specified 
	 * index position. 
	 * 
	 * */
	public void insertList(MyLinkedList<AnyType> myLinkedList, int idx) {

		if (idx < size()) {
			Node<AnyType> nodeNext = getNode(idx);
			Node<AnyType> nodePrev = getNode(idx - 1);
			nodePrev.next = myLinkedList.beginMarker.next;
			Node<AnyType> nodeLast = myLinkedList.endMarker.prev;
			nodeLast.next = nodeNext;
			nodeNext.prev = nodeLast;
		}

	}

	/**
	 * Adds an item to this collection, at specified position. Items at or after
	 * that position are slid one position higher.
	 * 
	 * @param x
	 *            any object.
	 * @param idx
	 *            position to add at.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive. /** Adds an
	 *             item to this collection, at specified position p. Items at or
	 *             after that position are slid one position higher.
	 * @param p
	 *            Node to add before.
	 * @param x
	 *            any object.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size(), inclusive.
	 */
	private void addBefore(Node<AnyType> p, AnyType x) {
		Node<AnyType> newNode = new Node<>(x, p.prev, p);
		newNode.prev.next = newNode;
		p.prev = newNode;
		theSize++;
		modCount++;
	}

	/**
	 * Returns the item at position idx.
	 * 
	 * @param idx
	 *            the index to search in.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType get(int idx) {
		return getNode(idx).data;
	}

	/**
	 * Changes the item at position idx.
	 * 
	 * @param idx
	 *            the index to change.
	 * @param newVal
	 *            the new value.
	 * @return the old value.
	 * @throws IndexOutOfBoundsException
	 *             if index is out of range.
	 */
	public AnyType set(int idx, AnyType newVal) {
		Node<AnyType> p = getNode(idx);
		AnyType oldVal = p.data;

		p.data = newVal;
		return oldVal;
	}

	/**
	 * Gets the Node at position idx, which must range from 0 to size( ) - 1.
	 * 
	 * @param idx
	 *            index to search at.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between 0 and size( ) - 1, inclusive.
	 */
	private Node<AnyType> getNode(int idx) {
		return getNode(idx, 0, size() - 1);
	}

	/**
	 * Gets the Node at position idx, which must range from lower to upper.
	 * 
	 * @param idx
	 *            index to search at.
	 * @param lower
	 *            lowest valid index.
	 * @param upper
	 *            highest valid index.
	 * @return internal node corresponding to idx.
	 * @throws IndexOutOfBoundsException
	 *             if idx is not between lower and upper, inclusive.
	 */
	private Node<AnyType> getNode(int idx, int lower, int upper) {
		Node<AnyType> p;

		if (idx < lower || idx > upper)
			throw new IndexOutOfBoundsException("getNode index: " + idx + "; size: " + size());

		if (idx < size() / 2) {
			p = beginMarker.next;
			for (int i = 0; i < idx; i++)
				p = p.next;
		} else {
			p = endMarker;
			for (int i = size(); i > idx; i--)
				p = p.prev;
		}

		return p;
	}

	/**
	 * Removes an item from this collection.
	 * 
	 * @param idx
	 *            the index of the object.
	 * @return the item was removed from the collection.
	 */
	public AnyType remove(int idx) {
		return remove(getNode(idx));
	}

	/**
	 * Removes the object contained in Node p.
	 * 
	 * @param p
	 *            the Node containing the object.
	 * @return the item was removed from the collection.
	 */
	private AnyType remove(Node<AnyType> p) {
		p.next.prev = p.prev;
		p.prev.next = p.next;
		theSize--;
		modCount++;

		return p.data;
	}

	/**
	 * Returns a String representation of this collection.
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");

		for (AnyType x : this)
			sb.append(x + " ");
		sb.append("]");

		return new String(sb);
	}

	/**
	 * Obtains an Iterator object used to traverse the collection.
	 * 
	 * @return an iterator positioned prior to the first element.
	 */
	public java.util.Iterator<AnyType> iterator() {
		return new LinkedListIterator();
	}

	/**
	 * This is the implementation of the LinkedListIterator. It maintains a
	 * notion of a current position and of course the implicit reference to the
	 * MyLinkedList.
	 */
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = beginMarker.next;
		private int expectedModCount = modCount;
		private boolean okToRemove = false;

		public boolean hasNext() {
			return current != endMarker;
		}

		public AnyType next() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.next;
			okToRemove = true;
			return nextItem;
		}

		public void remove() {
			if (modCount != expectedModCount)
				throw new java.util.ConcurrentModificationException();
			if (!okToRemove)
				throw new IllegalStateException();

			MyLinkedList.this.remove(current.prev);
			expectedModCount++;
			okToRemove = false;
		}
	}

	/**
	 * This is the doubly-linked list node.
	 */
	private static class Node<AnyType> {
		public Node(AnyType d, Node<AnyType> p, Node<AnyType> n) {
			data = d;
			prev = p;
			next = n;
		}

		public AnyType data;
		public Node<AnyType> prev;
		public Node<AnyType> next;
	}

	private int theSize;
	private int modCount = 0;
	private Node<AnyType> beginMarker;
	private Node<AnyType> endMarker;

	public static void main(String args[]) {
		MyLinkedList<Integer> lst = new MyLinkedList<>();
		MyLinkedList<Integer> lst2 = new MyLinkedList<>();
		for (int i = 1; i <= 15; i++)
			lst.add(i);

		for (int i = 16; i < 20; i++)
			lst2.add(i);
		//lst.swapnew(2, 3);
		lst.erase(2, 3);
		System.out.println(lst);
		/*System.out.println("The Original array");
		System.out.println(lst);
		System.out.println("Enter the positions you wanted to swap");
		Scanner inp = new Scanner(System.in);
		int a = inp.nextInt();
		int b = inp.nextInt();
		lst.swap(a, b);
		System.out.println("After swapping");
		System.out.println(lst);
		System.out.println("Enter no of times you want to shift");
		int c = inp.nextInt();
		lst.shift(c);
		System.out.println(lst);
		System.out.println("Enter the starting position and no of elements you want to erase");
		int d = inp.nextInt();
		int e = inp.nextInt();
		lst.erase(d, e);
		System.out.println(lst);
		System.out.println("New linked list which is going to be inserted");
		System.out.println(lst2);
		System.out.println("Enter the position from where you want to insert");
		int f = inp.nextInt();
		lst.insertList(lst2, f);
		System.out.println(lst);*/
	}
}
	    
	

	class TestLinkedList
	{
	    public static void main( String [ ] args )
	    {
	        MyLinkedList<Integer> lst = new MyLinkedList<>( );

	        for( int i = 0; i < 10; i++ )
	                lst.add( i );
	        for( int i = 20; i < 30; i++ )
	                lst.add( 0, i );

	        lst.remove( 0 );
	        lst.remove( lst.size( ) - 1 );

	        System.out.println( lst );

	        java.util.Iterator<Integer> itr = lst.iterator( );
	        while( itr.hasNext( ) )
	        {
	                itr.next( );
	                itr.remove( );
	                System.out.println( lst );
	        }
	    }

}
