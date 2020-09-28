package main.assignment1.impl;

import org.junit.jupiter.api.Assertions;

import main.assignment1.*;
import test.assignment1.*;

public class main {

	public static void main(String[] args) {
		
		MyAVL4StringsImpl AVLtree = new MyAVL4StringsImpl();
		
		
		//testing the insert functionality.
		/*
		System.out.println("this should perform left double rotation: expected inorder traversal : B C D E S");
		AVLtree.insert("E"); //should be root
		AVLtree.insert("S"); //should be right
		//AVLtree.insert("G"); //this will change the tree dramatically. it becomes // B C D E G S
		AVLtree.insert("D"); //should be left
		AVLtree.insert("B"); //should be left
		AVLtree.insert("C"); //should be right (of B)
		
		//AVLtree.insert("A");
		//AVLtree.insert("H");
		//AVLtree.insert("Z");
		//AVLtree.insert("Y");
		//AVLtree.insert("X");
		//AVLtree.insert("W");
		//AVLtree.insert("I");
		
		AVLtree.printTree();
		
				
		MyList<MyList<String>> mylistlist = AVLtree.LevelByLevelLists();
		
		for(int i = 0; i < mylistlist.size(); i++) {
			
			MyList<String> mylisty = mylistlist.get(i);
			
			System.out.println("\n");
			System.out.println("The Elements of level  " + i + " are : ");
			
			for(int k = 0; k < mylisty.size(); k++) {
			
				System.out.println("Element at index  " + k + " is : " + mylisty.get(k) + " and has depth of " + i);
			
			}
			
		}
		
		*/
		
		/*
		System.out.println("this should perform left single rotation: expected inorder traversal : A B D E S");
		AVLtree.insert("E"); //should be root
		AVLtree.insert("S"); //should be right
		AVLtree.insert("D"); //should be left
		AVLtree.insert("B"); //should be left
		AVLtree.insert("A"); //should be left
		
		AVLtree.printTree();
		
		
		MyList<MyList<String>> mylistlist = AVLtree.LevelByLevelLists();
		
		for(int i = 0; i < mylistlist.size(); i++) {
			
			MyList<String> mylisty = mylistlist.get(i);
			
			System.out.println("\n");
			System.out.println("The Elements of level  " + i + " are : ");
			
			for(int k = 0; k < mylisty.size(); k++) {
			
				System.out.println("Element at index  " + k + " is : " + mylisty.get(k) + " and has depth of " + i);
			
			}
			
		}
		*/
		
		/*
		System.out.println("this should perform right single rotation: expected inorder traversal : V W X Y Z");
		AVLtree.insert("W"); //should be root
		AVLtree.insert("V"); //should be left
		AVLtree.insert("X"); //should be right
		AVLtree.insert("Y"); //should be right
		AVLtree.insert("Z"); //should be right
		
		AVLtree.printTree();
		
		
		MyList<MyList<String>> mylistlist = AVLtree.LevelByLevelLists();
		
		for(int i = 0; i < mylistlist.size(); i++) {
			
			MyList<String> mylisty = mylistlist.get(i);
			
			System.out.println("\n");
			System.out.println("The Elements of level  " + i + " are : ");
			
			for(int k = 0; k < mylisty.size(); k++) {
			
				System.out.println("Element at index  " + k + " is : " + mylisty.get(k) + " and has depth of " + i);
			
			}
			
		}
		
		*/
		
		/*
		System.out.println("this should perform right double rotation: expected inorder traversal : S T W X Y");
		AVLtree.insert("T"); //should be root
		AVLtree.insert("S"); //should be left
		AVLtree.insert("W"); //should be right
		AVLtree.insert("Y"); //should be right
		AVLtree.insert("X"); //should be left (of Y)
		
		
		AVLtree.printTree();
		
		
		MyList<MyList<String>> mylistlist = AVLtree.LevelByLevelLists();
		
		for(int i = 0; i < mylistlist.size(); i++) {
			
			MyList<String> mylisty = mylistlist.get(i);
			
			System.out.println("\n");
			System.out.println("The Elements of level  " + i + " are : ");
			
			for(int k = 0; k < mylisty.size(); k++) {
			
				System.out.println("Element at index  " + k + " is : " + mylisty.get(k) + " and has depth of " + i);
			
			}
			
		}
		*/
		
		
		//testing the list implementation.
		/*
		MyList<String> mylist = new MyListImpl<String>();
		
		mylist.add("hi");
		System.out.println(mylist.get(0));
		
		mylist.add("hello there");
		System.out.println(mylist.size());
		
		System.out.println(mylist.get(mylist.size()-1));
		
		MyList<MyList<String>> mylistlist = new MyListImpl<MyList<String>>();
		mylistlist.add(mylist);
		mylistlist.get(0).add("will this work?");
		System.out.println(mylistlist.get(0).get(2));
		*/
		
		//String a = "Jonna"; //node
		//String b = "Jon";
		
		//System.out.println(a.compareTo(b));
		//System.out.println(a.startsWith("Jon"));
		
		
		MyAVL4StringsImpl tree = new MyAVL4StringsImpl();
		// Be careful, this tree contains the same element but it does not have the same
		// structure as the tree in the figure!
		tree.insert("Alice");
		tree.insert("Bob");
		tree.insert("Jon");
		tree.insert("Jonatan");
		tree.insert("Jonna");
		tree.insert("Jones");
		tree.insert("Jonny");
		tree.insert("Jonty");
		tree.insert("Xabier");
		
		System.out.println("Printing tree inorder traversal : ");
		tree.printTree();
		
				
		MyList<MyList<String>> mylistlist = tree.LevelByLevelLists();
		
		for(int i = 0; i < mylistlist.size(); i++) {
			
			MyList<String> mylisty = mylistlist.get(i);
			
			System.out.println("\n");
			System.out.println("The Elements of level  " + i + " are : ");
			
			for(int k = 0; k < mylisty.size(); k++) {
			
				System.out.println("Element at index  " + k + " is : " + mylisty.get(k) + " and has depth of " + i);
			
			}
			
		}
		
		String begin = "Jon";
		
		System.out.println("\nThe min, max of " + begin + " is : ");
		Couple<String> duple = tree.partialSearch(begin);
		
		System.out.println(duple.getFirst());
		System.out.println(duple.getLast());
		
	}

}
