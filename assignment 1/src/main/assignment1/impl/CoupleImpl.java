package main.assignment1.impl;

import main.assignment1.Couple;

public class CoupleImpl<E> implements Couple<E> {

    private E first;
    private E last;
    
    
    /* (non-Javadoc)
     * @see main.assignment1.impl.DupleIface#getFirst()
     */
    @Override
    public E getFirst() {
        return first;
    }
    /* (non-Javadoc)
     * @see main.assignment1.impl.DupleIface#setFirst(E)
     */
    @Override
    public void setFirst(E first) {
        this.first = first;
    }
    /* (non-Javadoc)
     * @see main.assignment1.impl.DupleIface#getLast()
     */
    @Override
    public E getLast() {
        return last;
    }
    /* (non-Javadoc)
     * @see main.assignment1.impl.DupleIface#setLast(E)
     */
    @Override
    public void setLast(E last) {
        this.last = last;
    }

}
