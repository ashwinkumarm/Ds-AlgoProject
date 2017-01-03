package project4;

public class AVLTreeMine<AnyType>  {

	private Node<AnyType> root;
	boolean isPresent = false;
	
	
	AVLTreeMine(){
		root = null;
	}

	public void insert(String data) {
		if (root == null) {
			root = new Node(data, null, null);
		}
		insert(data, root);
	}

	public Node insert(String data, Node t) {
		if (t == null) {
			return new Node(data, null, null );
		} else {
			int x = data.compareTo(t.data);
			if (x > 0) {
				t.right = insert(data, t.right);
			} else if (x < 0) {
				t.left = insert(data, t.left);
			}
		}
		return (t);
	}

	public int height() {
		return height(root);
	}

	private int height(Node t) {
		if (t == null) {
			return -1;
		} else {
			return 1 + Math.max(height(t.left), height(t.right));
		}
	}
	
	private void singleRotateLeftTree(Node t){
		System.out.println("Single rotation left tree");
	}
	
	private Node singleRotateRightTree(Node k1){
		
	     	Node k2 = k1.right;
	        k1.right = k2.left;
	        k2.left = k1;
	        k1.height = Math.max( height( k1.left ), height( k1.right ) ) + 1;
	        k2.height = Math.max( height( k2.right ), k1.height ) + 1;
	        return k2;
	}
	
	private void doubleRotateLeftTree(Node t){
		System.out.println("Double rotation left tree");
	}
	
    private void doubleRotateRightTree(Node t){
	 System.out.println("Double rotation right tree");	
	}

	public Node balance(Node t) {
		
		if(t == null){
			return t;
		}
		if ((height(t.left) - height(t.right)) > 1) {
			if(height(t.left.left) >= height(t.left.right)){
				singleRotateLeftTree(t);
			}
			else{
				doubleRotateLeftTree(t);
			}
		}
		else if(height(t.right) - height(t.left) > 1){
			if(height(t.right.right) >= height(t.right.left)){
				singleRotateRightTree(t);
			}else{
				doubleRotateRightTree(t);
			}
		}
		t.height = Math.max( height( t.left ), height( t.right ) );
		return t;
	}

	public void displayTree() {
		displayTree(root);
	}
	
	public Node getRoot(){
		return root;
	}

	private void displayTree(Node t) {
		if (t != null) {
			System.out.println(t.data);
			displayTree(t.left);
			displayTree(t.right);
		}
	}
	
	public boolean isPresent(AnyType x){
	    isPresent = false;
		isPresent(x, root);
		return isPresent;
	}
	
	public boolean isPresent(AnyType x, Node t){
		if (t != null) {
			if(t.data.equals(x)){
				isPresent= true;
				return true;
			}
			isPresent(x, t.left);
			isPresent(x, t.right);
		}
		return false;
	}

	public class Node<AnyType> {
		String data;
		Node left;
		Node right;
		int height;

		Node(String data, Node left, Node right) {
			this.data = data;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
	}

	public static void main(String[] args) {
		AVLTreeMine<String> avlTree = new AVLTreeMine<String>();
		avlTree.insert("ash");
		avlTree.insert("win");
		avlTree.insert("aaa");
		avlTree.insert("a");
		avlTree.insert("b");
		avlTree.insert("z");
		avlTree.insert("zzz");
		avlTree.displayTree();
		System.out.println(avlTree.isPresent("asha"));
		System.out.println(avlTree.isPresent("ash"));
		System.out.println(avlTree.isPresent("ash"));
		System.out.println(avlTree.isPresent("asha"));
		System.out.println(avlTree.isPresent("aqqa"));
		System.out.println(avlTree.isPresent("ash"));
		System.out.println(avlTree.height());
	}


}
