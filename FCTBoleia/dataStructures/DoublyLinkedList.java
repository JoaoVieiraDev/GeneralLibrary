package dataStructures;

public class DoublyLinkedList<E> implements List<E>
{   

    static final long serialVersionUID = 0L;
    protected DListNode<E> head;
    protected DListNode<E> tail;
    protected int currentSize;
    public DoublyLinkedList()
    {
        head = null;
        tail = null;
        currentSize = 0;
    }


    @Override
    public boolean isEmpty()
    {  
        return currentSize == 0;
    }


    @Override
    public int size()
    {
        return currentSize;
    }


    @Override
    public Iterator<E> iterator()
    {
        return new DoublyLLIterator<E>(head, tail);
    }


    @Override
    public E getFirst() throws EmptyListException
    {  
        if (this.isEmpty())
            throw new EmptyListException();

        return head.getElement();
    }


    @Override
    public E getLast() throws EmptyListException
    {  
        if (this.isEmpty())
            throw new EmptyListException();

        return tail.getElement();
    }

    protected DListNode<E> getNode(int position) 
    {
        DListNode<E> node;

        if (position <= (currentSize-1)/2)
        {
            node = head;
            for (int i = 0; i < position; i++)
                node = node.getNext();
        }
        else
        {
            node = tail;
            for (int i = currentSize - 1; i > position; i--)
                node = node.getPrevious();

        }
        return node;
    }


    @Override    
    public E get(int position) throws InvalidPositionException
    {
        if (position < 0 || position >= currentSize)
            throw new InvalidPositionException();

        return this.getNode(position).getElement();
    }


    @Override
    public int find(E element)
    {
        DListNode<E> node = head;
        int position = 0;
        while (node != null && !node.getElement().equals(element))
        {
            node = node.getNext();
            position++;
        }
        if (node == null)
            return -1;
        else
            return position;
    }


    @Override
    public void addFirst( E element )
    {
        DListNode<E> newNode = new DListNode<E>(element, null, head);
        if ( this.isEmpty() )
            tail = newNode;
        else
            head.setPrevious(newNode);
        head = newNode;
        currentSize++;
    }


    @Override
    public void addLast(E element)
    {
        DListNode<E> newNode = new DListNode<E>(element, tail, null);
        if (this.isEmpty())
            head = newNode;
        else
            tail.setNext(newNode);
        tail = newNode;
        currentSize++;
    }

    protected void addMiddle(int position, E element)
    {
        DListNode<E> prevNode = this.getNode(position - 1);
        DListNode<E> nextNode = prevNode.getNext();
        DListNode<E> newNode = new DListNode<E>(element, prevNode, nextNode);
        prevNode.setNext(newNode);            
        nextNode.setPrevious(newNode);            
        currentSize++;
    }


    @Override
    public void add(int position, E element) throws InvalidPositionException
    {
        if (position < 0 || position > currentSize)
            throw new InvalidPositionException();

        if (position == 0)
            this.addFirst(element);
        else if (position == currentSize)
            this.addLast(element);
        else
            this.addMiddle(position, element);
    }

    protected void removeFirstNode()
    {
        head = head.getNext();
        if ( head == null )
            tail = null;
        else
            head.setPrevious(null);
        currentSize--;
    }


    @Override
    public E removeFirst() throws EmptyListException
    {
        if (this.isEmpty())
            throw new EmptyListException();

        E element = head.getElement();
        this.removeFirstNode();
        return element;
    }


    protected void removeLastNode()
    {
        tail = tail.getPrevious();
        if (tail == null)
            head = null;
        else
            tail.setNext(null);
        currentSize--;
    }


    @Override
    public E removeLast() throws EmptyListException
    {
        if (this.isEmpty())
            throw new EmptyListException();

        E element = tail.getElement();
        this.removeLastNode();
        return element;
    }

    protected void removeMiddleNode(DListNode<E> node)
    {
        DListNode<E> prevNode = node.getPrevious();
        DListNode<E> nextNode = node.getNext();
        prevNode.setNext(nextNode);            
        nextNode.setPrevious(prevNode);            
        currentSize--;
    }


    @Override
    public E remove(int position) throws InvalidPositionException
    {
        if (position < 0 || position >= currentSize)
            throw new InvalidPositionException();

        if (position == 0)
            return this.removeFirst();
        else if (position == currentSize - 1)
            return this.removeLast();
        else 
        {
            DListNode<E> nodeToRemove = this.getNode(position);
            this.removeMiddleNode(nodeToRemove);
            return nodeToRemove.getElement();
        }
    }


    protected DListNode<E> findNode(E element)
    {
        DListNode<E> node = head;
        while (node != null && !node.getElement().equals(element))
            node = node.getNext();
        return node;
    }


    @Override
    public boolean remove(E element)
    {
        DListNode<E> node = this.findNode(element);
        if (node == null)
            return false;
        else
        {
            if (node == head)
                this.removeFirstNode();
            else if (node == tail)
                this.removeLastNode();
            else
                this.removeMiddleNode(node);
            return true;
        }
    }

}
