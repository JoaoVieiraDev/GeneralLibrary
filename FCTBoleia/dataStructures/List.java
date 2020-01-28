package dataStructures;

import java.io.Serializable;

public interface List<E> extends Serializable
{

    boolean isEmpty();

    int size();

    Iterator<E> iterator();

    E getFirst() throws EmptyListException;

    E getLast() throws EmptyListException;
 
    E get(int position) throws InvalidPositionException;

  
    int find(E element);

    void addFirst(E element);

    void addLast(E element);

    void add(int position, E element) throws InvalidPositionException;

    E removeFirst() throws EmptyListException;

    E removeLast() throws EmptyListException;

    E remove(int position) throws InvalidPositionException;

    boolean remove(E element);

}   

