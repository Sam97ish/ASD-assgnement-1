package main.assignment1.impl;

import main.assignment1.myqueue;


public class myqueueImpl<E> implements myqueue<E> {
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
    
	@Override
	public void enqueue(E elm) {
		// TODO Auto-generated method stub
        Cell c = new Cell(elm);
        
        if (first == null) {//queue is empty.
            first = c;
        }
        
        if(last != null) {//queue is not empty.
            last.next = c;
        }
        
        last = c; //new last element.

	}

	@Override
	public E dequeue() {
		// TODO Auto-generated method stub
		E elm = first.getdata();
		first = first.getNext();
		
		if(first == null) { //no more elements in queue.
			last = null;
		}
		
		return elm;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return first == null;
	}

}
