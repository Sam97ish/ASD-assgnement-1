package main.assignment1.impl;

import main.assignment1.Couple;
import main.assignment1.MyAVL4Strings;
import main.assignment1.MyList;
import main.assignment1.myqueue;

public class MyAVL4StringsImpl implements MyAVL4Strings {
	
	private static final int ALLOWED_IMBALANCE = 1; //for insertion
	private AVLNode root;


	//Constructor
	public MyAVL4StringsImpl(){
		root = null;
	}
	
	//nested Node Class.
	private static class AVLNode{
		
		String element; //the data.
		AVLNode left; //left subtree.
		AVLNode right; //right subtree.
		int height; //height of a node.
		
		//Constructor
		AVLNode(String elm){
			this(elm, null, null);
		}
		
		//Constructor
		AVLNode(String elm, AVLNode lt, AVLNode rt){
			element = elm; left = lt; right = rt; height = 0;
		}
		

	}
	
	/**
	 * @role: to handle when the node is null.
	 * @complexity: O(1).
	 */
	private int height(AVLNode t) {
		return t==null ? -1 : t.height;
	}
	

	/**
	 * @role: left-left rotation.
	 * @complexity: O(1).
	 */
	private AVLNode rotateWithLeftChild(AVLNode node) {
		AVLNode leftChild = node.left;
		node.left = leftChild.right;
		leftChild.right = node;
		
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		leftChild.height = Math.max(height(leftChild.left), node.height) +1;
		
		
		return leftChild;
	}
	
	/**
	 * @role: right-right rotation.
	 * @complexity: O(1).
	 */
	private AVLNode rotateWithRightChild(AVLNode node) {
		AVLNode RightChild = node.right;
		node.right = RightChild.left;
		RightChild.left = node;
		
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		RightChild.height = Math.max(node.height, height(RightChild.right)) +1;

		
		return RightChild;
	}
	
	/**
	 * @role: double rotation with the left child.
	 * @complexity: O(1).
	 */
	private AVLNode doubleWithLeftChild(AVLNode node) {
		
		node.left = rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}
	
	/**
	 * @role: double rotation with the right child.
	 * @complexity: O(1).
	 */
	private AVLNode doubleWithRightChild(AVLNode node) {
		
		node.right = rotateWithLeftChild(node.right);
		return rotateWithRightChild(node);
	}
	
	/**
	 * @role: balances the tree when the imbalance is greater than 1.
	 * @complexity: O(1).
	 */
	private AVLNode balance(AVLNode node) {
		
		if(node == null) {
			return node;
		}
		//handles the imbalance when it's in the left subtree.
		if(height(node.left) - height(node.right) > ALLOWED_IMBALANCE) {
			
			//checks if we need an inside rotation or an outside rotation.
			if(height(node.left.left) >= height(node.left.right)) {
				node = rotateWithLeftChild(node);
			}else {
				node = doubleWithLeftChild(node);
			}
			
		}else {//handles the imbalance when it's in the right subtree.
		if(height(node.right) - height(node.left) > ALLOWED_IMBALANCE) {
			
			//checks which rotation is needed as before.
			if(height(node.right.right) >= height(node.right.left)) {
				node = rotateWithRightChild(node);
			}else {
				node = doubleWithRightChild(node);
			}
		}
		
		}
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		return node;
		
		
	}
	
	/**
	 * @role: helper to the public insert, inserts an element in the tree after finding a proper place.
	 * @complexity: O(LogN).
	 */
	private AVLNode insert(String elm, AVLNode node) {
		
		if(node == null) {
			return new AVLNode(elm,null,null);
		}
		
		int compareResult = elm.compareTo(node.element);
		
		if(compareResult < 0) {
			node.left = insert(elm,node.left);
			
			
		}else if(compareResult > 0) {
			node.right = insert(elm,node.right);
			

		}else {
			
			; //duplicate do nothing.
			
		}

		return balance(node);
	}

	
    /*
     * @role: inserts element into the AVL tree and rebalances if necessary.
     * @complexity : O(logN). because the height of the Avl tree is LogN.
     */
    @Override
    public void insert(String element) {
	// TODO Auto-generated method stub
    	this.root = insert(element,root);
    }
    
    /**
     * @role: helper for partialSearch, searches for the min and the max of a given beginning by comparing and excluding at most half of the tree with each call.
     * @param minMax : the couple that has min in first and max in last.
     * @param left : pointer to the left of the previous node.
     * @param right : pointer to the right of the previous node.
     * @param beginning : the string given.
     * @complexity: O(LogN), as we exclude one side of the tree each time, and go as far as the height of the AVL tree which is LogN.
     */
    private void searchMinMax(Couple<String> minMax, AVLNode left, AVLNode right, String beginning) {
    	
    	if(left != null){
    		
    		int diff = left.element.compareTo(beginning);
    		
    		if(left.element.startsWith(beginning)) {
    			
    			if(diff == 0) {//this is the min.
    				
    				minMax.setFirst(left.element);
    				
    			}else if(diff > 0) {
    				
    				int diff2 = left.element.compareTo(minMax.getFirst());
    				int diff3 = left.element.compareTo(minMax.getLast());
    				
    				if(diff2 < 0 || minMax.getFirst().equals("")) {//must switch the current min with the new min.
    					minMax.setFirst(left.element);
    				}
    				
    				if(diff3 > 0 || minMax.getLast().equals("")) {//must switch the current max with the new max.
    					minMax.setLast(left.element);
    				}
    				
    				//continue to the left.
    				searchMinMax(minMax, left.left, null, beginning);
    				
    			}
    			
    		}else {
    			
        		if(diff > 0) {// go to the left subtree because node is bigger than beginning.
        			
        			searchMinMax(minMax, left.left, null, beginning);
        			
        		}else if(diff < 0) {//go to right subtree because node is smaller than beginning.
        			
        			searchMinMax(minMax, null, left.right, beginning);
        			
        		}
    			
    		}
    		
    	}
    	
    	if(right != null) {
    		
    		int diff = right.element.compareTo(beginning);
    		
    		if(right.element.startsWith(beginning)) {
    			
    			if(diff == 0) {//this is the min.
    				
    				minMax.setFirst(right.element);
    				
    			}else if(diff > 0) {
    				
    				int diff2 = right.element.compareTo(minMax.getLast());
    				int diff3 = right.element.compareTo(minMax.getFirst());
    				
    				if(diff2 > 0 || minMax.getLast().equals("")) {//must switch the current max with the new max.
    					minMax.setLast(right.element);
    				}
    				
    				if(diff3 < 0 || minMax.getFirst().equals("")) {//must switch the current min with the new min.
    					minMax.setFirst(right.element);
    				}
    				
    				//continue to the right.
    				searchMinMax(minMax, null, right.right, beginning);
    				
    			}
    			
    		}else {
    			
        		if(diff > 0) {// go to the left subtree because node is bigger than beginning.
        			
        			searchMinMax(minMax, right.left, null, beginning);
        			
        		}else if(diff < 0) {//go to right subtree because node is smaller than beginning.
        			
        			searchMinMax(minMax, null, right.right, beginning);
        			
        		}
    			
    		}
    		
    	}
    	
    }
    
    /**
     * @role: searches for the min and the max that start with the given string inside the AVL tree.
     * @param beginning: the string given.
     * @complexity: O(LogN), all the work is constant, ignoring the complexities of the methods of the String class.
     * the complexity is O(logN) since it calls the searchMinMax method.
     */
    @Override
    public Couple<String> partialSearch(String beginning) {
	// TODO Auto-generated method stub
    	
    	Couple<String> minMax = new CoupleImpl<String>();
    	minMax.setFirst(""); minMax.setLast("");
    	
    	//start by comparing input to root.
    	int diff = this.root.element.compareTo(beginning);
    	if(this.root.element.startsWith(beginning)) { //we can use the diff instead, but we have to make sure that it starts with beginning.
    		
    		
    		if(diff == 0) {//this is the min.
    			
    			minMax.setFirst(this.root.element);
    			searchMinMax(minMax, null, this.root.right, beginning); //found the min, looking for the max.
    		
    		}
    		
    		if(diff > 0) { //root element is a possible min and max.
    			
    			minMax.setLast(this.root.element);
    			minMax.setFirst(this.root.element);
    			
    			searchMinMax(minMax, this.root.left, this.root.right, beginning);
    		}
    		
    	}else { //doesn't begin with beginning.
    		
    		
    		if(diff > 0) {// go to the left subtree because root is bigger than beginning.
    			
    			searchMinMax(minMax, this.root.left, null, beginning);
    			
    		}else if(diff < 0) {//go to right subtree because root is smaller than beginning.
    			
    			searchMinMax(minMax, null, this.root.right, beginning);
    			
    		}
    		
    	}
    	
    	if(minMax.getFirst().equals("") && minMax.getLast().equals("")) {
    		minMax.setFirst(null);
    		minMax.setLast(null);
    		return minMax;
    	}
    	
    	return minMax;
    }
    
    /**
     * @role: helper to the getLevelByLevelLists method. visits each node once to add it to it's corresponding list using it's depthOFlevel
     * then call itself to it's left subtree and right subtree if they exist.
     * @param Node : the node visited.
     * @param listOflists : the list of lists of strings.
     * @param depthOflevel : the depth of this node.
     * @complexity: O(N), since each node is visited once without backtracking, AND the list of lists is implemented with
     * an array so the .get() method is O(1) and add() adds to the end of the array so O(1).
     */
    private void iterateSubtree(AVLNode Node, MyListImpl<MyListImpl<String>> listOflists, int depthOflevel) {
    	
    	if(Node != null) {

    		
    		 listOflists.get(depthOflevel).add(Node.element);
    		 
    		 if(Node.left != null) {
    			iterateSubtree(Node.left, listOflists, depthOflevel + 1);
    		 }
    		 
    		if(Node.right != null) {
    			iterateSubtree(Node.right, listOflists, depthOflevel + 1);
    		}
    		
    		
    		
    	}
    	
    }
    
    public boolean isChild(AVLNode node) {
    	return height(node) == 0;
    }
    
    /**
     * @role: visits each node once to add it to its corresponding list.
     * @complexity: O(N), since it calls the iterateTree method.
     */
    @Override
    public MyList<MyList<String>> LevelByLevelLists() {
	// TODO Auto-generated method stub
        int currentLevel = 0;
        int nextLevel = 2;
        
    	myqueue<AVLNode> treeIterator = new myqueueImpl<>();
    	myqueue<AVLNode> nodesOrder = new myqueueImpl<>();
    	MyListImpl<MyList<String>> lsOfls = new MyListImpl<>();
    	MyListImpl<String> ls = new MyListImpl<>();
    	
    	treeIterator.enqueue(root);
    	
    	boolean lastListadded = false;
    	
    	//adding all the nodes in order to the queue nodesOrder.
    	while(!treeIterator.isEmpty()) {
    		lastListadded = false;
    		
    		AVLNode node = treeIterator.dequeue();
    		
    		if(node == null) {//we reached a child.
    			;
    		}else { //add to ls.
    			
    			ls.add(node.element);
    			
    			//check if node has subtrees.
    			
        		if(node.left != null) {
        			treeIterator.enqueue(node.left);
        		}else if(!isChild(node)){ //if the node has right subtree.
        			treeIterator.enqueue(null); //padding to keep the queue consistent.
        		}
        		
        		if(node.right != null) {
        			treeIterator.enqueue(node.right);
        		}else if(!isChild(node)) { //if the node has left subtree.
        			treeIterator.enqueue(null);
        		}
    		}
    		
    		currentLevel++;
    		
            if (currentLevel == nextLevel - 1){
                lsOfls.add((MyList<String>) ls);
                nextLevel *= 2;
                lastListadded = true;
                ls = new MyListImpl<>();
       
            }

    		
    	}
    	
    	if(!lastListadded) {//makes sure that the last level is added in case the tree is heavily one sided.
    	 lsOfls.add((MyList<String>) ls);
    	}
    	return (MyList<MyList<String>>) lsOfls;
    }
    
	
	/**
	 * @role: prints the tree in order.
	 * @param root
	 * @complexity: O(N).
	 */
	private void printInOrder(AVLNode root) {
		if(root != null) {
			printInOrder(root.left);
			System.out.println(root.element);
			printInOrder(root.right);
		}
	}
	
	/**
	 * @role: prints the tree in postorder.
	 * @param root
	 * @complexity: O(N).
	 */
	private void printPostOrder(AVLNode root) {
		if(root != null) {
			printInOrder(root.left);
			printInOrder(root.right);
			System.out.println(root.element);
		}
	}
	
	/**
	 * @role: prints the tree using the specified traversal in the body.
	 * @complexity: O(N).
	 */
	public void printTree() {
		printInOrder(root);
		//printPostOrder(root);
	}
}
