/**
 * OrderedDictionary class implements OrderedDictionaryADT. The dictionary is
 * implemented using a binary search tree. Comparison between any two nodes is
 * based on lexicographic order
 * 
 * @Name Hanxiang Pan
 * @StudentNumber 250608428
 * 
 * 
 */
public class OrderedDictionary implements OrderedDictionaryADT{
	
	private Node root; // the root of the binary search tree
	
	
	/**
	 * Ordered Dictionary constructor will initialize the root to be null
	 */
	public OrderedDictionary(){
		this.root = null;
	}
	
	
	/**
	 * findWord method will find the definition of the given word
	 * 
	 * @return - definition of the given word, or it returns an empty string if
	 *         the word is not in the dictionary
	 */
	public String findWord (String word) {	
		Node p;
		// if root is null, its an empty tree, return empty string
		if (root == null)
			return "";
		else {
			p = root;
			// while p is not leaf and word is not found
			while (p !=null && p.getData().word() != word){
				// if the word is smaller (lexicographically) than the current node
				// traverse down the BST
				if (word.compareTo(p.getData().word()) < 0) {
					p = p.getLeft();
				}
				
				// else the word is larger (lexicographically) than the current node
				else if (word.compareTo(p.getData().word()) > 0)
						p = p.getRight();
				
				// else the word is found
				else
					return p.getData().definition();
								
			} // end while loop
			
			// p is a leaf, word is not found, return empty string
			if (p == null) {
				return "";
			}
			
			// else, word is found
			else {
				return p.getData().definition(); // return p definition if found
			} // end else - word is found
			
		} // end else - root is not null
		
	} // end findWord method
    
	/**
	 * findType method will find the type associated with a given word.
	 * 
	 *  @return - the type of the given word, or it returns
	 *  -1 if the word is not in the dictionary.
	 */
	public int findType (String word) {
		Node p;
		// if the root is null, the tree is empty, return -1
		if (root == null)
			return -1;
		else {
			p = root;
			
			// while p is not leaf and word is not found
			while (p !=null && p.getData().word() != word){
				
				// if the word is smaller (lexicographically) than the current node
				// traverse down the BST
				if (word.compareTo(p.getData().word()) < 0) {
					p = p.getLeft();
				}
				// else the word is larger (lexicographically) than the current node
				else if (word.compareTo(p.getData().word()) > 0)
						p = p.getRight();
				else
					return p.getData().type();				
			} // end while loop
			
			// p is a leaf, word is not found, return -1
			if (p == null) {
				return -1;
			}
			// else, word is found
			else {
				return p.getData().type(); // return p definition if found
			}
		} // end else
	} // end findType method
    
	/**
	 * insert method adds the given word, its definition and type into the 
	 * dictionary. It throws an OrderedDictionaryException if the 
	 * word is already in the dictionary. 
	 */
	public void insert (String word, String definition, int type) throws DictionaryException {
		Node p = root;
		if (p == null){
			root = new Node (new DictEntry(word,definition,type));
		}
		else {
			// find the word first
			if (findWord(word) != "") // if the word is found, then throw exception, exit
				throw new DictionaryException("The word is already in the dictionary");
			
			// create a new node containing the values
			Node newNode = new Node (new DictEntry(word,definition,type));
			Node parent = p;
			
			// while p doesn't have a right child or a left child
			// traverse down the BST
			while (p.getLeft() != null || p.getRight() != null) {

				// if the word is smaller (lexicographically), 
				if (word.compareTo(p.getData().word()) < 0) {
					parent = p; // always point to the parent of p
					p = p.getLeft();
				}
				// else the word is larger (lexicographically)
				else if (word.compareTo(p.getData().word()) > 0) {
					parent = p;
					p = p.getRight();
				}
				
				// p is now a leaf, store the new node in this leaf
				if (p == null) {
					p = newNode;
					
					// if the word is the left child of its parent
					if (word.compareTo(parent.getData().word()) < 0){
						parent.setLeft(p); // set the parent to point to p
						p.setParent(parent);
					}
					// else the word is the right child of its parent
					else { 
						parent.setRight(p);
						p.setParent(parent);
					} // end else - word is the right child of its parent 
					
				} // end if - p is a leaf
				
			} // end while loop
			
			// p doesn't have any children
			if (p.getLeft() == null && p.getRight() == null) {
				if (word.compareTo(p.getData().word()) < 0) {
					p.setLeft(newNode);
					newNode.setParent(p);
				}
			else if (word.compareTo(p.getData().word()) > 0) {
					p.setRight(newNode);
					newNode.setParent(p);
				}
			}
			
		} // end else 
	} // end insert method
	
	/**
	 * Removes the entry with the given word from the dictionary.
	 * It throws an OrderedDictionaryException if the word is not
	 * in the dictionary.
	 */
	public void remove (String word) throws DictionaryException {
		Node p = find(word);
		// p is a leaf
		if (p == null)
			throw new DictionaryException("The word is not in the dictionary.");
		
		// else p is an internal node
		else {
			
			// left child of p is a leaf, p has a right subtree
			if (p.getLeft() == null && p.getRight() != null) {
				
				// check if p is the root
				if (p == root) {
					root = p.getRight(); // replace p with the right child of p
				}
				
				// if p is the left child of its parent
				else if (p.getData().word().compareTo(p.getParent().getData().word()) < 0) {
					p.getParent().setLeft(p.getRight());
					p.getRight().setParent(p.getParent());
				}
				
				// else p is the right child of its parent
				else {
					p.getParent().setRight(p.getRight());
					p.getRight().setParent(p.getParent());
				}
			} // end if left child of p is a leaf, p has right subtree
			
			// right child of p is a leaf
			else if (p.getRight() == null && p.getLeft() != null) {
				
				// check if p is the root
				if (p == root) {
					root = p.getLeft(); // replace p with the left child of p
				}
				
				// p is the left child of its parent
				else if (p.getData().word().compareTo(p.getParent().getData().word()) < 0) {
					p.getParent().setLeft(p.getLeft());
					p.getLeft().setParent(p.getParent());
				}
				
				// else p is the right child of its parent
				else {
					p.getParent().setRight(p.getLeft());
					p.getLeft().setParent(p.getParent());
				}
			} // end else right child of p is a leaf
			
			// else, p doesn't have children
			else if (p.getLeft() == null && p.getRight() == null) {
				
				// if p is the root, set root to null
				if (p == root)
					root = null;
				
				// else p is the left child of its parent
				else if (p.getParent().getLeft() == p)
					p.getParent().setLeft(null);
				
				// else p is the right child of its parent
				else
					p.getParent().setRight(null);
			} // end else - p doesn't have children
			
			// else, p has two children
			else if (p.getLeft() != null && p.getRight() != null) {
				Node s = p.getRight().getSmallest();
				p.setData(s.getData()); // copy the predecessor data into p
				s = null;
			} // end else - p has two children
			
		} // end else - p is an internal node
		
	} // end remove method
	
	/**
	 * successor method will find the word following the given word
	 * 
	 * @return the successor of the given word (the word from the
	 * dictionary that lexicographically follows the given one); it
	 * returns an empty string if the given word has no successor.
	 */
	public String successor (String word) {
		Node p, p1;
		if (root == null)
			return "";
		else {
			p = find(word);
			// p is an internal node and the right child of p is internal
			if (p != null && p.getRight() != null){
				return p.getRight().getSmallest().getData().word();
			}
			
			// p is a leaf or the right child of p is a leaf
			else
				// set p1 to point to p's parent
				p1 = p.getParent();
				
				// while p is not a root and p is the right child of the parent
				while (p != root && p == p.getParent().getRight()){
					p = p1;
					p1 = p1.getParent();
				} // end while loop
				
				// if we reached the end, return empty string
				if (p == root)
					return "";
				// else return the word (successor)
				else
					return p1.getData().word();
			
		} // end else
		
	} // end successor method
	
	/**
	 * predecessor method finds the word in the dictionary that precedes the
	 * given word
	 * 
	 * @return the predecessor of the given word (the word from the dictionary
	 *         that lexicographically precedes the given one); it returns an
	 *         empty string if the given word has no predecessor.
	 */
	public String predecessor (String word) {
		Node p, p1;
		
		// if the root is null, return empty string
		if (root == null)
			return "";
		else {
			p = find(word);
			
			// if p is an internal node and the left child of p is internal
			// return the largest child of p's left child
			if (p != null && p.getLeft() != null) {
				return p.getLeft().getLargest().getData().word();
			}
			
			// p is a leaf or the left child of p is a leaf
			else
				p1 = p.getParent();
			
			// while p is not a root and p is the left child of the parent
			while (p != root && p == p.getParent().getLeft()){
				p = p1;
				p1 = p1.getParent();
			} // end while loop
			
			// if we reached the end, return empty string
			if (p == root)
				return "";
			// else return the word (predecessor)
			else
				return p1.getData().word();
		}
	} // end predecessor method
	
	/**
	 * find method will find the key (word) and return the node containing word
	 * if it's found in the dictionary, return null otherwise
	 * 
	 * @param word - the word the user wants to find
	 * @return - the node containing the word if found or null otherwise
	 */
	private Node find (String word) {	
		Node p;
		if (root == null)
			return null;
		else {
			p = root;
			
			// p is not a leaf and the word is not found
			// while p is not leaf and word is not found
			while (p !=null && p.getData().word() != word){
				// traverse down the BST
				if (word.compareTo(p.getData().word()) < 0) {
					p = p.getLeft();
				}
				else {
					if (word.compareTo(p.getData().word()) > 0)
						p = p.getRight();
					else
						return p;
				}				
			} // end while loop
				return p; // return p if found
		} // end else
		
	} // end find method

} // end OrderedDictionary class
