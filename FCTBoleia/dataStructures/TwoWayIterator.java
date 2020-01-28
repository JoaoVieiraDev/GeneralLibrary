package dataStructures;

public interface TwoWayIterator<E> extends Iterator<E>
{

    boolean hasPrevious();

    E previous() throws NoSuchElementException;
    
    void fullForward();

}
