package main.assignment1.impl;

import main.assignment1.Couple;
import main.assignment1.MyAVL4Strings;
import main.assignment1.MyList;

public class MyAVL4StringsImpl implements MyAVL4Strings {
	
	private static class AVLNode{
		
		String element; // the data.
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
	
	private int height(AVLNode t) {// to handle when the node is null.
		return t==null ? -1 : t.height;
	}
	
    @Override
    public void insert(String element) {
	// TODO Auto-generated method stub

    }

    @Override
    public Couple<String> partialSearch(String beginning) {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public MyList<MyList<String>> LevelByLevelLists() {
	// TODO Auto-generated method stub
	return null;
    }
}
