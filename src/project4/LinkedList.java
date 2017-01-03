package project4;

import java.util.Iterator;


public class LinkedList<AnyType> implements Iterable<AnyType> {

	Node<AnyType> root = null;
	int theSize;

	public void insert(AnyType data) {
		if (root == null) {
			theSize++;
			root = new Node<>(data, null);
		} else {
			Node<AnyType> t = root;
			while (t.right != null) {
				t = t.right;
			}
			Node<AnyType> newNode = new Node<>(data, null);
			t.right = newNode;
		}
	}

	public void display() {
		Node<AnyType> t = root;
		while (t != null) {
			System.out.print(t.data + " ");
			t = t.right;
		}
	}
	
	public boolean isPresent(String data) {
		Node<AnyType> t = root;
		while(t != null){
			if(t.data.equals(data)){
				return true;
			}
			t = t.right;
		}
		return false;
	}
	
	public int size(){
		return theSize;
	}

	@Override
	public Iterator<AnyType> iterator() {
		// TODO Auto-generated method stub
		return new LinkedListIterator();
	}
	
	public AnyType get(int pos){
		Node<AnyType> p = root;
		
		for (int i = 0; i < pos; i++)
			p = p.right;
		
		return p.data;
	}
	public class Node<AnyType> {
		AnyType data;
		Node<AnyType> right;

		Node(AnyType data, Node<AnyType> right) {
			this.data = data;
			this.right = right;
		}
	}
	
	
	private class LinkedListIterator implements java.util.Iterator<AnyType> {
		private Node<AnyType> current = root.right;
		private boolean okToRemove = false;

		public boolean hasNext() {
			if(current.right != null){
				return true;
			}
			return false;
		}

		public AnyType next() {

			if (!hasNext())
				throw new java.util.NoSuchElementException();

			AnyType nextItem = current.data;
			current = current.right;
			okToRemove = true;
			return nextItem;
		}

	}


}
