package dataStructures;

public class StackInList<E> implements Stack<E>
{

    static final long serialVersionUID = 0L;
    protected List<E> list;                     


    public StackInList()
    {     
        list = new DoublyLinkedList<E>();
    }

    public boolean isEmpty()
    {     
        return list.isEmpty();
    }

    public int size()
    {     
        return list.size();
    }

    public E top() throws EmptyStackException 
    {     
        if (list.isEmpty())
            throw new EmptyStackException("Stack is empty.");
        
        return list.getFirst();
    }

    public void push(E element)
    { 
        list.addFirst(element);
    }

    public E pop() throws EmptyStackException 
    {     
        if (list.isEmpty())
            throw new EmptyStackException("Stack is empty.");

        return list.removeFirst();
    }

}
