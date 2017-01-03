package project3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;



public class BinarySearchTree<AnyType extends Comparable<? super AnyType>> {

	public void setup() {
		
		BinaryNode leaf1 = new BinaryNode(85, null, null);
		BinaryNode leaf2 = new BinaryNode(65, null, null);
		BinaryNode leaf3 = new BinaryNode(35, null, null);
		BinaryNode leaf4 = new BinaryNode(10, null, null);

		BinaryNode p1 = new BinaryNode(80, leaf1, leaf2);
		BinaryNode p2 = new BinaryNode(20, leaf3, leaf4);

		root = new BinaryNode(50, p1, p2);
	}

	

	/**
	 * Construct the tree.
	 */
	public BinarySearchTree() {
		root = null;
	}

	/**
	 * Insert into the tree; duplicates are ignored.
	 * 
	 * @param x
	 *            the item to insert.
	 */
	public void insert(AnyType x) {
		root = insert(x, root);
	}

	/**
	 * Remove from the tree. Nothing is done if x is not found.
	 * 
	 * @param x
	 *            the item to remove.
	 */
	public void remove(AnyType x) {
		root = remove(x, root);
	}

	/**
	 * Find the smallest item in the tree.
	 * 
	 * @return smallest item or null if empty.
	 * @throws Exception
	 */
	public AnyType findMin() throws Exception {
		if (isEmpty())
			throw new Exception();
		return findMin(root).element;
	}

	/**
	 * Find the largest item in the tree.
	 * 
	 * @return the largest item of null if empty.
	 * @throws Exception
	 */
	public AnyType findMax() throws Exception {
		if (isEmpty())
			throw new Exception();
		return findMax(root).element;
	}

	/**
	 * Find an item in the tree.
	 * 
	 * @param x
	 *            the item to search for.
	 * @return true if not found.
	 */
	public boolean contains(AnyType x) {
		return contains(x, root);
	}

	/**
	 * Make the tree logically empty.
	 */
	public void makeEmpty() {
		root = null;
	}

	/**
	 * Test if the tree is logically empty.
	 * 
	 * @return true if empty, false otherwise.
	 */
	public boolean isEmpty() {
		return root == null;
	}

	/**
	 * Print the tree contents in sorted order.
	 */
	public void printTree() {
		if (isEmpty())
			System.out.println("Empty tree");
		else
			printTree(root);
	}

	/**
	 * Internal method to insert into a subtree.
	 * 
	 * @param x
	 *            the item to insert.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return new BinaryNode<>(x, null, null);

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = insert(x, t.left);
		else if (compareResult > 0)
			t.right = insert(x, t.right);
		else
			; // Duplicate; do nothing
		return t;
	}

	/**
	 * Internal method to remove from a subtree.
	 * 
	 * @param x
	 *            the item to remove.
	 * @param t
	 *            the node that roots the subtree.
	 * @return the new root of the subtree.
	 */
	private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return t; // Item not found; do nothing

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			t.left = remove(x, t.left);
		else if (compareResult > 0)
			t.right = remove(x, t.right);
		else if (t.left != null && t.right != null) // Two children
		{
			t.element = findMin(t.right).element;
			t.right = remove(t.element, t.right);
		} else
			t = (t.left != null) ? t.left : t.right;
		return t;
	}

	/**
	 * Internal method to find the smallest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the smallest item.
	 */
	private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {
		if (t == null)
			return null;
		else if (t.left == null)
			return t;
		return findMin(t.left);
	}

	/**
	 * Internal method to find the largest item in a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the largest item.
	 */
	private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {
		if (t != null)
			while (t.right != null)
				t = t.right;

		return t;
	}

	/**
	 * Internal method to find an item in a subtree.
	 * 
	 * @param x
	 *            is item to search for.
	 * @param t
	 *            the node that roots the subtree.
	 * @return node containing the matched item.
	 */
	private boolean contains(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return false;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return contains(x, t.left);
		else if (compareResult > 0)
			return contains(x, t.right);
		else
			return true; // Match
	}

	private BinaryNode<AnyType> getNode(AnyType x, BinaryNode<AnyType> t) {
		if (t == null)
			return null;

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0)
			return getNode(x, t.left);
		else if (compareResult > 0)
			return getNode(x, t.right);
		else
			return t; // Match
	}

	/**
	 * Internal method to print a subtree in sorted order.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private void printTree(BinaryNode<AnyType> t) {
		if (t != null) {
			printTree(t.left);
			System.out.println(t.element);
			printTree(t.right);
		}
	}

	/**
	 * Internal method to compute height of a subtree.
	 * 
	 * @param t
	 *            the node that roots the subtree.
	 */
	private int height(BinaryNode<AnyType> t) {
		if (t == null)
			return -1;
		else
			return 1 + Math.max(height(t.left), height(t.right));
	}

	public int nodeCount() {
		nodeCount = 0;
		return nodeCount(root);
	}

	private int nodeCount(BinaryNode<AnyType> t) {

		if (t != null) {
			nodeCount(t.left);
			nodeCount++;
			nodeCount(t.right);
		}
		return nodeCount;
	}

	public Boolean isFull() {
		isFull(root);
		return isFull;
	}

	private void isFull(BinaryNode<AnyType> t) {
		if (t.left != null && t.right != null) {
			if (t.left.left != null || t.right.right != null) {
				isFull(t.left);
				isFull(t.right);
			}
		} else {
			isFull = false;
		}
	}

	public Boolean equal(BinarySearchTree<AnyType> t1) {
		ArrayList<AnyType> inOrderOriginal = new ArrayList<AnyType>();
		ArrayList<AnyType> inOrderNew = new ArrayList<AnyType>();
		inOrderOriginal = equal(inOrderOriginal, root);
		inOrderNew = equal(inOrderNew, t1.root);
		if (inOrderNew.equals(inOrderOriginal))
			return true;
		else
			return false;
	}

	private ArrayList<AnyType> equal(ArrayList<AnyType> inOrder, BinaryNode<AnyType> t) {
		if (t != null) {
			equal(inOrder, t.left);
			inOrder.add(t.element);
			equal(inOrder, t.right);
		}
		return inOrder;
	}

	public Boolean compareStructure(BinarySearchTree<AnyType> t1) {
	    isSimilar = true;
		compareStructure(root, t1.root);
		return isSimilar;
	}

	private void compareStructure(BinaryNode<AnyType> tOriginal, BinaryNode<AnyType> tNew) {
		if (tNew != null || tOriginal != null) {
			if (tOriginal == null && tNew != null) {
				isSimilar = false;
			} else if (tOriginal != null && tNew == null) {
				isSimilar = false;
			}
		}
		if (tNew != null && tOriginal != null) {
			compareStructure(tOriginal.left, tNew.left);
			compareStructure(tOriginal.right, tNew.right);
		}
	}
	
	public BinarySearchTree<AnyType> copy() {
		BinarySearchTree<AnyType> newTree = new BinarySearchTree<>();
		newTree.root = copy(root);
		return newTree;
	}

	
	private BinaryNode<AnyType> copy(BinaryNode<AnyType> t) {
		if (t != null) {
			BinaryNode<AnyType> newNode = new BinaryNode<AnyType>(t.element, null, null);
			newNode.left = copy(t.left);
			newNode.right = copy(t.right);
			return new BinaryNode<AnyType>(t.element, t.left, t.right);
		}
		return null;
	}


	public BinarySearchTree<AnyType> mirror(BinarySearchTree<AnyType> t) {
		mirror(root);
		return t;

	}

	private void mirror(BinaryNode<AnyType> t) {
		if (t != null) {
			BinaryNode<AnyType> temp;
			temp = t.left;
			t.left = t.right;
			t.right = temp;
			mirror(t.right);
			mirror(t.left);
		}

	}

	public Boolean isMirror(BinarySearchTree<AnyType> t) {
		ArrayList<AnyType> inOrdertOriginal = new ArrayList<AnyType>();
		inOrdertOriginal = equal(inOrdertOriginal, root);
		BinarySearchTree<AnyType> tNew = mirror(t);
		ArrayList<AnyType> inOrdertNew = new ArrayList<AnyType>();
		inOrdertNew = equal(inOrdertNew, tNew.root);
		if (inOrdertNew.equals(inOrdertOriginal)) {
			return true;
		} else
			return false;

	}

	public void rightRotation(AnyType x) {
		BinaryNode<AnyType> node = getNode(x, root);
		if (node != null)
		 rightRotation(node);

	}

	private BinaryNode<AnyType> rightRotation(BinaryNode<AnyType> t) {
		if (t.left != null) {
			BinaryNode<AnyType> temp = t.left.right;
			BinaryNode<AnyType> tCopy = t;
			t = t.left;
			t.right = tCopy;
			t.right.left = temp;
			root = t;
		}
		return t;
	}

	public void rotateLeft(AnyType x) {
	   BinaryNode<AnyType> node =  getNode(x, root);
		leftRotation(node);
	}

	/**
	 * Routine to rotate the given node to its left.
	 */
	private BinaryNode<AnyType> leftRotation(BinaryNode<AnyType> k2) {
		BinaryNode<AnyType> k1 = k2.right;
		if (k1 != null) {
			k2.right = k1.left;
			k1.left = k2;
			return k1;
		}
		return k1;
	}	
	
	public void printLevelByLevel(AnyType x){
		printLevelByLevel(root,x);
	}

	private void printLevelByLevel(BinaryNode<AnyType> root, AnyType x) {
		int h = height(root);
		HashMap<Integer, ArrayList<AnyType>> hash = new HashMap<>();
		for (int i = 1; i <= h + 1; i++) {
			ArrayList<AnyType> arr = new ArrayList<AnyType>();
			ArrayList<AnyType> arr1 = printLevelbyLevel(root, i, arr,x);
			hash.put(i, arr1);
		}
		printLevelbyLevel(h + 1, hash, 1);
	}

	private void printLevelbyLevel(int h, HashMap<Integer, ArrayList<AnyType>> hash, int level) {
		if (h == 0) {
			return;
		}

		ArrayList<AnyType> noOfElements = hash.get(level);
		if (level == 1) {
			int n = calculateSpace(h - 1);
			for (int i = 0; i < n; i++) {
				System.out.print(" ");
			}
			System.out.println(noOfElements.get(0));
			printLevelbyLevel(h - 1, hash, level + 1);
		} else {
			int n = calculateSpace(h - 1);
			for (int k = 0; k < n; k++) {
				System.out.print(" ");
			}
			int i = 0;
			while (noOfElements.size() > i) {
				System.out.print(noOfElements.get(i));
				i++;
				for (int j = 0; j < calculateSpace(h); j++) {
					System.out.print(" ");
				}
			}
			System.out.println("");
			printLevelbyLevel(h - 1, hash, level + 1);
		}

	}

	private int calculateSpace(int h) {
		int count = 0;
		while (h > 0) {
			count += Math.pow(2, h);
			h--;
		}
		return count;

	}

	private ArrayList<AnyType> printLevelbyLevel(BinaryNode<AnyType> root, int h, ArrayList<AnyType> arrh, AnyType x) {
		if (root == null)
			{arrh.add(x);return arrh;}
		if (h == 1) {
			arrh.add(root.element);
		} else {
			printLevelbyLevel(root.left, h - 1, arrh,x);
			printLevelbyLevel(root.right, h - 1, arrh,x);
		}
		return arrh;
	}
	 
	public void printTreeLevelOrder() {
		printTreeLevelOrder(root);
	}

	/**
	 * This routine prints the items of the tree level by level using breadth
	 * first search.
	 * 
	 * @param root
	 */
	private void printTreeLevelOrder(BinaryNode<AnyType> root) {
		if (root == null)
			return;
		Queue<BinaryNode<AnyType>> q = new LinkedList<>();
		q.add(root);
		BinaryNode<AnyType> b;
		while (!q.isEmpty()) {
			b = q.poll();
			if (b.left != null)
				q.add(b.left);
			if (b.right != null)
				q.add(b.right);
			System.out.print(b.element + " ");
		}
	}

	// Basic node stored in unbalanced binary search trees
	private static class BinaryNode<AnyType> {
		// Constructors
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}

		AnyType element; // The data in the node
		BinaryNode<AnyType> left; // Left child
		BinaryNode<AnyType> right; // Right child
	}

	/** The tree root. */
	private BinaryNode<AnyType> root;
	private int nodeCount = 0;
	private boolean isFull = true;
	private boolean isSimilar = true;
	ArrayList<AnyType> inOrder = new ArrayList<AnyType>();
	ArrayList<AnyType> allElements = new ArrayList<AnyType>();

	// Test program
	public static void main(String[] args) throws Exception {
		BinarySearchTree<Integer> treeA = new BinarySearchTree<>();
		BinarySearchTree<Integer> treeB = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> treeC = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> treeCopy = new BinarySearchTree<Integer>();
		BinarySearchTree<Integer> TreeMirror = new BinarySearchTree<Integer>();
		
		int elementsA[] = { 50, 80, 20, 35, 65, 10, 85 };
		int elementsB[] = { 100, 150, 50, 40, 45 };
		
		
		for (int i = 0; i < elementsA.length; i++) {
			treeA.insert(elementsA[i]);
		}
		
		for (int i = 0; i < elementsB.length; i++) {
			treeB.insert(elementsB[i]);
		}
		
		treeC.setup();
		
		System.out.println("Default Tree A is Shown Below");
		treeA.printLevelByLevel(0);
		System.out.println("");
		System.out.println("Default Tree B is Shown Below");
		treeB.printLevelByLevel(0);
		System.out.println("");
		System.out.println("Default Tree c is Shown Below");
		treeC.printLevelByLevel(0);
		System.out.println("");
		System.out.println("Node count for the Default Tree A is :" + treeA.nodeCount());
		System.out.println("Node count for the Default Tree B is :" + treeB.nodeCount());
		System.out.println("");
		System.out.println("Is the Tree A full? "+treeA.isFull());
		System.out.println("Is the Tree B full? "+treeB.isFull());
		System.out.println("");
		System.out.println("Do the Structure of Tree A and Tree B match? "+treeA.compareStructure(treeB));
		System.out.println("Do the Structure of Tree A and Tree C match? "+treeA.compareStructure(treeC));
		System.out.println("");
		System.out.println("Are the Tree A and Tree B identical? "+treeA.equal(treeB));
		System.out.println("Are the Tree A and Tree C identical? "+treeA.equal(treeC));
		System.out.println("");
		System.out.println("Copy of the tree A is ");
		treeCopy = treeA.copy();
		treeCopy.printLevelByLevel(0);
		System.out.println("");
		System.out.println("Mirror image for Tree B is ");
		TreeMirror = treeA.mirror(treeA);
		TreeMirror.printLevelByLevel(0);
		System.out.println("");
		System.out.println("Is Tree B mirror image of Tree A "+ treeA.isMirror(treeB));
		System.out.println("Is Tree C mirror image of Tree A "+ treeA.isMirror(treeC));
		System.out.println("");
		System.out.println("Right rotation for the node 100");
		treeB.rightRotation(100);
		treeB.printTreeLevelOrder();
		//treeB.printLevelByLevel(0);
		System.out.println("");
		System.out.println("Left rotation for the node 100");
		treeB.rotateLeft(100);
		//treeB.printLevelByLevel(0);
		treeB.printTreeLevelOrder();
	}

}
