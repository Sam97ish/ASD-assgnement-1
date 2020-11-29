package main.assignment1;

public interface myqueue<E> {
	public void enqueue(E elm);
	public E dequeue();
	public boolean isEmpty();
}
