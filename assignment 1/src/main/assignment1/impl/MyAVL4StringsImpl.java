package main.assignment1.impl;

import main.assignment1.Couple;
import main.assignment1.MyAVL4Strings;
import main.assignment1.MyList;

public class MyAVL4StringsImpl implements MyAVL4Strings {
	
	private static final int ALLOWED_IMBALANCE = 1;
	private AVLNode root;
	
	private static final int capacity = 500; //for the last method only
	
	
	//Constructor
	public MyAVL4StringsImpl(){
		root = null;
	}
	
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
	
	// to handle when the node is null.
	private int height(AVLNode t) {
		return t==null ? -1 : t.height;
	}
	

	//left-left single rotation.
	private AVLNode rotateWithLeftChild(AVLNode node) {
		AVLNode leftChild = node.left;
		node.left = leftChild.right;
		leftChild.right = node;
		
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		leftChild.height = Math.max(height(leftChild.left), node.height) +1;
		
		
		return leftChild;
	}
	
	//right-right single rotation
	private AVLNode rotateWithRightChild(AVLNode node) {
		AVLNode RightChild = node.right;
		node.right = RightChild.left;
		RightChild.left = node;
		
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		RightChild.height = Math.max(node.height, height(RightChild.right)) +1;

		
		return RightChild;
	}
	
	//double rotation : left child.
	private AVLNode doubleWithLeftChild(AVLNode node) {
		
		node.left = rotateWithRightChild(node.left);
		return rotateWithLeftChild(node);
	}
	
	//double rotation : right child.
	private AVLNode doubleWithRightChild(AVLNode node) {
		
		node.right = rotateWithLeftChild(node.right);
		return rotateWithRightChild(node);
	}
	
	//balance the Avl tree after an insertion assuming node is either balanced or within one of being balanced.
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
	
	//inserts elm into tree after finding a suitable place and balances the tree afterwards.
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
	
	//prints out the whole tree using inorder traversal.
	private void printInOrder(AVLNode root) {
		if(root != null) {
			printInOrder(root.left);
			System.out.println(root.element);
			printInOrder(root.right);
		}
	}
	
	//prints out the whole tree using postorder traversal.
	private void printPostOrder(AVLNode root) {
		if(root != null) {
			printInOrder(root.left);
			printInOrder(root.right);
			System.out.println(root.element);
		}
	}
	
	//printing out the tree using the specified traversal.
	public void printTree() {
		printInOrder(root);
		//printPostOrder(root);
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
    				searchMinMax(minMax, right.right, null, beginning);
    				
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
    
    private void iterateSubtree(AVLNode Node, MyList<MyList<String>> listOflists, int depthOflevel) {
    	
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
    
    
    @Override
    public MyList<MyList<String>> LevelByLevelLists() {
	// TODO Auto-generated method stub
    	
    	int num = height(this.root) +1;
    	MyList<MyList<String>> listOflists = new MyListImpl<MyList<String>>();
    	
    	//forcing Java to do what I want.
    	for(int i = 0; i < num; i++) {
    		
    		String[] arr = new String[capacity];
    		
    		MyList<String> list = new MyListImpl<String>(arr, 0);
    		
    		listOflists.add(list);
    	}
    	
    	AVLNode treeIter = this.root;
    	
    	//System.out.println("The root is  : " + treeIter.element);
    	
    	int depthOflevel = 0;
    	
    	if(treeIter != null) {
    		
    		//System.out.println("treeIter is now on: " + treeIter.element);
    		
    		listOflists.get(depthOflevel).add(treeIter.element);
    		
    		if(treeIter.left != null) {
    			iterateSubtree(treeIter.left, listOflists, depthOflevel + 1);
    		}
    		
    		if(treeIter.right != null) {
    			iterateSubtree(treeIter.right, listOflists, depthOflevel + 1);
    		}
    		
    		
    	}
    	
    	
    	
	return listOflists;
    }
}
