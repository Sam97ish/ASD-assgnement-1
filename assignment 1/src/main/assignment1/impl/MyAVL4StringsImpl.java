package main.assignment1.impl;

import main.assignment1.Couple;
import main.assignment1.MyAVL4Strings;
import main.assignment1.MyList;

public class MyAVL4StringsImpl implements MyAVL4Strings {
	
	private static final int ALLOWED_IMBALANCE = 1;
	private AVLNode root;
	
	private AVLNode leftMostNode;
	private boolean leftmostsubtree;//this is used to make sure that right double rotation doesnt mess up leftmostnode. 
	
	//Constructor
	public MyAVL4StringsImpl(){
		root = null; leftMostNode = null; leftmostsubtree = true;
	}
	
	private static class AVLNode{
		
		String element; //the data.
		AVLNode left; //left subtree.
		AVLNode right; //right subtree.
		AVLNode parent; //this ref helps to keep the last method at O(n).
		int height; //height of a node.
		
		//Constructor
		AVLNode(String elm){
			this(elm, null, null);
		}
		
		//Constructor
		AVLNode(String elm, AVLNode lt, AVLNode rt){
			element = elm; left = lt; right = rt; height = 0; parent = null;
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
		
		leftChild.parent = node.parent;
		node.parent = leftChild;
		
		node.height = Math.max(height(node.left), height(node.right)) + 1;
		leftChild.height = Math.max(height(leftChild.left), node.height) +1;
		
		
		return leftChild;
	}
	
	//right-right single rotation
	private AVLNode rotateWithRightChild(AVLNode node) {
		AVLNode RightChild = node.right;
		node.right = RightChild.left;
		RightChild.left = node;
		
		RightChild.parent = node.parent;
		node.parent = RightChild;
		
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
			
			node.left.parent = node;
			
			
			if(leftmostsubtree && node.left.left == null && node.left.right == null) {//this is the leftmostNode
				
				this.leftMostNode = node.left;
			}
			
		}else if(compareResult > 0) {
			leftmostsubtree = false;
			node.right = insert(elm,node.right);
			
			node.right.parent = node;
			

		}else {
			
			; //duplicate do nothing.
			
		}
		leftmostsubtree = true;
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
	
	//returns the leftmostnode for debugging purposes.
	public String getLeftMostNode() {
		if(this.leftMostNode != null) {
			return this.leftMostNode.element;
		}
		return null;
	}
	
	public int getHeightLeftMostNode() {
		if(this.leftMostNode != null) {
			return this.leftMostNode.height;
		}
		return -1;
	}
	
	public String getParentLeftMostNode() {
		String parents = "";
		AVLNode tmp = this.leftMostNode.parent;
		
		while(tmp != null) {
			parents += tmp.element;
			tmp = tmp.parent;
		}
		
		return parents;
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

    @Override
    public Couple<String> partialSearch(String beginning) {
	// TODO Auto-generated method stub
	return null;
    }
    
    private void iterateSubtree(AVLNode Node, MyList<MyList<String>> listOflists, int depthOflevel) {
    	
    	if(Node != null) {
    		
    		//System.out.println("about to add the right subtree : " + Node.element);
    		
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
    	
    	for(int i = 0; i < num; i++) {
    		
    		String[] arr = new String[50];
    		
    		MyList<String> list = new MyListImpl<String>(arr, 0, 50);
    		
    		listOflists.add(list);
    	}
    	
    	AVLNode treeIter = this.root;
    	//root.parent = null;
    	
    	System.out.println("The root is  : " + treeIter.element);
    	
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
