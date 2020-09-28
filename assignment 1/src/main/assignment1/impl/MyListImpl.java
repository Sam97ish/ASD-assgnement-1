package main.assignment1.impl;

import main.assignment1.MyList;

public class MyListImpl<E> implements MyList<E> {
	private E[] array;
	private int size;
	private static final int capacity = 500;
	
	
	public MyListImpl() {
		super();
		this.array = (E[]) new Object[this.capacity]; //Java is being Java here, no wonder C++ is superior when it comes to efficiency smh.
		this.size = 0;
	}
	
	

	public MyListImpl(E[] array, int size) {
		super();
		this.array = array;
		this.size = size;
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
	
	@Override
	public void add(E elm) {
		this.array[this.size] = elm;
		size++;
	}

}
