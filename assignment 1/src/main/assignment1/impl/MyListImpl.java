package main.assignment1.impl;

import main.assignment1.*;

public class MyListImpl<E> implements MyList<E>,MyAddList<E> {
	private Cell first;
	private Cell last;
	private int size;
	
    private class Cell {
        private E data;
        private Cell next;

        Cell (E data) {
            this.data = data;
        }
        
        public E getdata() {return this.data;}
        public Cell getNext() {return this.next;}
        public void setData(E dat) {this.data = dat;}
        public void setNext(Cell nxt) {this.next = nxt;}
    }
    
    MyListImpl(){
    	first = last = null;
    	size = 0;
    }

	@Override
	public void add(E elm) {
		// TODO Auto-generated method stub
		Cell c = new Cell(elm);
		
		if(first == null) {
			first = c;
		}
		
		if(last != null) { //if there are elements in list.
			last.setNext(c);
		}
		last= c; //new last elemnt.
		size++;
	}

	@Override
	public E get(int index) {
		// TODO Auto-generated method stub
		if(index > size-1 || index < 0) {
			throw new IndexOutOfBoundsException();
		}
		
		Cell itr = first;
		int currentIndex= 0;
		E item = null;
		
		while(currentIndex < index + 1) {
			
			if(currentIndex==index) {
				item = itr.getdata();
			}
			
			itr = itr.getNext();
			currentIndex++;

		}
		return item;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return this.size;
	}

}
