package dataStructures;

class DoublyLLIterator<E> implements TwoWayIterator<E>
{

    static final long serialVersionUID = 0L;
    protected DListNode<E> firstNode;
    protected DListNode<E> lastNode;
    protected DListNode<E> nextToReturn;
    protected DListNode<E> prevToReturn;


    public DoublyLLIterator(DListNode<E> first, DListNode<E> last)
    {
        firstNode = first;
        lastNode = last;
        this.rewind();
    }      


    @Override
    public void rewind()
    {
        nextToReturn = firstNode;
        prevToReturn = null;
    }


    @Override
    public void fullForward()
    {
        prevToReturn = lastNode;
        nextToReturn = null;
    }


    @Override
    public boolean hasNext()
    {
        return nextToReturn != null;
    }


    @Override
    public boolean hasPrevious()
    {
        return prevToReturn != null;
    }


    @Override
    public E next() throws NoSuchElementException
    {
        if (!this.hasNext())
            throw new NoSuchElementException();

        E element = nextToReturn.getElement();
        prevToReturn = nextToReturn.getPrevious();
        nextToReturn = nextToReturn.getNext();
        return element;
    }


    @Override
    public E previous() throws NoSuchElementException
    {
        if (!this.hasPrevious())
            throw new NoSuchElementException();

        E element = prevToReturn.getElement();
        nextToReturn = prevToReturn.getNext();
        prevToReturn = prevToReturn.getPrevious();
        return element;
    }


}
