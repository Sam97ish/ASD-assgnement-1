package main.assignment1.impl;

import main.assignment1.MyList;

public class MyListImpl<E> implements MyList<E> {
	private E[] array;
	private int size;
	private static final int capacity = 20000;
	//AUGMENT CAPACITY HERE AND IN MyAVL4StringsImpl IN CASE OF BOUNDS ERRORS DURING TESTING.
	
	//Constructor
	public MyListImpl() {
		super();
		this.array = (E[]) new Object[this.capacity]; //prime reason why this course should use C++.
		this.size = 0;
	}
	
	
	//Constructor
	public MyListImpl(E[] array, int size) {
		super();
		this.array = array;
		this.size = size;
	}
	
	/**
	 * @role: returns the element at index given
	 * @complexity O(1).
	 */
	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return array[index];
	}
	
	/**
	 * @role: returns the size of the list.
	 * @complexity : O(1).
	 */
	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	/**
	 * @role : adds element to the end of the list.
	 * @complexity : O(1).
	 */
	@Override
	public void add(E elm) {
		this.array[this.size] = elm;
		size++;
	}

}
