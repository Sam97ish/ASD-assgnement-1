package main.assignment1.impl;

public class main {

	public static void main(String[] args) {
		MyAVL4StringsImpl AVLtree = new MyAVL4StringsImpl();
		
		//testing the insert functionality.
		/*
		System.out.println("this should perform left double rotation: expected inorder traversal : B C D E S");
		AVLtree.insert("E"); // should be root
		AVLtree.insert("S"); //should be right
		AVLtree.insert("D"); //should be left
		AVLtree.insert("B"); //should be left
		AVLtree.insert("C"); //should be left
		
		AVLtree.printTree();
		*/
		
		/*
		System.out.println("this should perform left single rotation: expected inorder traversal : A B D E S");
		AVLtree.insert("E"); // should be root
		AVLtree.insert("S"); //should be right
		AVLtree.insert("D"); //should be left
		AVLtree.insert("B"); //should be left
		AVLtree.insert("A"); //should be left
		
		AVLtree.printTree();
		*/
		
		/*
		System.out.println("this should perform right single rotation: expected inorder traversal : V W X Y Z");
		AVLtree.insert("W"); // should be root
		AVLtree.insert("V"); //should be left
		AVLtree.insert("X"); //should be right
		AVLtree.insert("Y"); //should be right
		AVLtree.insert("Z"); //should be right
		
		AVLtree.printTree();
		*/
		
		/*
		System.out.println("this should perform right double rotation: expected inorder traversal : S T W X Y");
		AVLtree.insert("T"); // should be root
		AVLtree.insert("S"); //should be left
		AVLtree.insert("W"); //should be right
		AVLtree.insert("Y"); //should be right
		AVLtree.insert("X"); //should be right
		
		AVLtree.printTree();
		*/
		
		/*
		//testing the list implementation.
		MyListImpl<String> mylist = new MyListImpl<String>();
		
		mylist.add("hi");
		System.out.println(mylist.get(0));
		
		mylist.add("hello there");
		System.out.println(mylist.size());
		
		System.out.println(mylist.get(mylist.size()-1));
		
		MyListImpl<MyListImpl<String>> mylistlist = new MyListImpl<MyListImpl<String>>();
		mylistlist.add(mylist);
		System.out.println(mylistlist.get(0).get(0));
		*/
	}

}
