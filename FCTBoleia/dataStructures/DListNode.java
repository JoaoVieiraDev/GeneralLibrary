package dataStructures;

import java.io.Serializable;

class DListNode<E> implements Serializable
{

    static final long serialVersionUID = 0L;

    private E element;

    private DListNode<E> previous;

    private DListNode<E> next;

    public DListNode( E theElement, DListNode<E> thePrevious, 
                      DListNode<E> theNext )
    {
        element = theElement;
        previous = thePrevious;
        next = theNext;
    }

    public DListNode( E theElement )
    {
        this(theElement, null, null);
    }

    public E getElement( )
    {
        return element;
    }

    public DListNode<E> getPrevious( )
    {
        return previous;
    }

    public DListNode<E> getNext( )
    {
        return next;
    }


    /**
     * 
     * @param newElement - New element to replace the current element
     */
    public void setElement( E newElement )
    {
        element = newElement;
    }

    public void setPrevious( DListNode<E> newPrevious )
    {
        previous = newPrevious;
    }


    public void setNext( DListNode<E> newNext )
    {
        next = newNext;
    }


}
