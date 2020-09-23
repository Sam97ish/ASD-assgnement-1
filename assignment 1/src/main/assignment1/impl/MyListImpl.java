package main.assignment1.impl;

import main.assignment1.MyList;

public class MyListImpl<E> implements MyList<E> {
	private E[] array;
	private int size;
	private int capacity;
	
	
	public MyListImpl() {
		super();
		this.capacity = 50;
		this.array = (E[]) new Object[this.capacity]; //Java is being java here.
		this.size = 0;
	}
	
	public MyListImpl(E[] array, int size, int capacity) {
		super();
		this.array = array;
		this.size = size;
		this.capacity = capacity;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		return array[index];
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}
	
	public void add(E elm) {
		this.array[this.size] = elm;
		size++;
	}

}
