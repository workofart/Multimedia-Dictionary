/**
 * The Node class that will used to support the implementation of the Binary
 * Search Tree
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 */
public class Node {
	private Node left, right; // left and right subtree
	private Node parent; // the parent node of the current node
	private DictEntry data; // the DictEntry that is contained in a node
	
	/**
	 * Node constructor that initializes the left, right, parent and data
	 * values
	 */
	public Node(DictEntry data){
		this.left = null;
		this.right = null;
		this.parent = null;
		this.data = data;
	}
	
	/**
	 * getData method retrieves type DictEntry data.
	 * 
	 * @return - DictEntry type data that is stored in a specified node
	 */
	public DictEntry getData(){
		return data;
	}
	
	/**
	 * getLeft method retrieves the left child of the specified node
	 * 
	 * @return - the left Node of the given node
	 */
	public Node getLeft(){
		return left;
	}

	/**
	 * getRight method retrieves the right child of the specified node
	 * 
	 * @return - the right Node of the given node
	 */
	public Node getRight(){
		return right;
	}
	
	/**
	 * getParent method retrieves the parent node of the specified node
	 * 
	 * @return - the parent node of the given node
	 */
	public Node getParent(){
		return parent;
	}
	
	/**
	 * setData method sets the DictEntry of a specified node
	 * 
	 * @param data - the DictEntry data that will be stored in the given node
	 */
	public void setData(DictEntry data){
		this.data = data;
	}
	
	/**
	 * setLeft method sets the left child of a specified node
	 * 
	 * @param n - the node that will be set as the left child of the given node
	 */
	public void setLeft(Node n){
		this.left = n;
	}

	/**
	 * setRight method sets the right child of a specified node
	 * 
	 * @param n - the node that will be set as the right child of the given node
	 */
	public void setRight(Node n){
		this.right = n;
	}

	/**
	 * setParent method sets the parent of a specified node
	 * 
	 * @param n - the node that will be set as the parent of the given node
	 */
	public void setParent(Node n){
		this.parent = n;
	}

	/**
	 * getSmallest method retrieves the node containing the smallest element in
	 * the BST of the given node
	 * 
	 * @return - the node containing the smallest (lexicographically) key (word)
	 */
	public Node getSmallest(){
		if (this.left == null)
			return this;
		else {
			return left.getSmallest();
		}
	}
	
	/**
	 * getLargest method retrieves the node containing the largest element in
	 * the BST of the given node
	 * 
	 * @return - the node containing the largest (lexicographically) key (word)
	 */
	public Node getLargest(){
		if (this.right == null)
			return this;
		else
			return right.getLargest();
	}
	
}
