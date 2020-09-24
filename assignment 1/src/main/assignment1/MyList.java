package main.assignment1;

public interface MyList<E> {

    public E get(int index); //returns the element in index position. The first element is in position 0.
    
    public int size(); //returns the number of elements in the list
    
    public void add(E elm); //adds the element to the end of the list.
}
