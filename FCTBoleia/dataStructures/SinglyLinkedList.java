package dataStructures;

public class SinglyLinkedList<E> implements List<E> {

	private static final long serialVersionUID = 1L;

	// Node at the head of the list.
	protected SListNode<E> head;

	// Node at the tail of the list.
	protected SListNode<E> tail;

	// Number of elements in the list.
	protected int currentSize;
		

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return currentSize == 0;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return currentSize;
	}

	@Override
	public Iterator<E> iterator() throws NoElementException {
		// TODO Auto-generated method stub
		if (currentSize==0) throw new NoElementException("List is empty.");
		return new SinglyLLIterator<E>(head);
	}

	@Override
	public int find(E element) {
		// TODO Auto-generated method stub
		int pos = 0;
		SListNode<E> auxNo = head;
		boolean found = false;
		while (auxNo != null && !found) {
			if (auxNo.getElement().equals(element))
				found = true;
			else {
				pos++;
				auxNo = auxNo.getNext();
			}
		}
		if (found)
			return pos;
		else
			return -1;
	}

	@Override
	public E getFirst() throws NoElementException {
		// TODO Auto-generated method stub
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		return head.getElement();
	}

	@Override
	public E getLast() throws NoElementException {
		// TODO Auto-generated method stub
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		return tail.getElement();
		}

	@Override
	public E get(int position) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (currentSize==0 || position > currentSize) 
			throw new InvalidPositionException("Invalid position.");
		SListNode<E> aux= head;
		for(int i=1;i<=position;i++)
			aux=aux.getNext();
		return aux.getElement();
	}

	@Override
	public void addFirst(E element) {
		// TODO Auto-generated method stub
		SListNode<E> aux = new SListNode<E>(element, head);
		head = aux;
		if (isEmpty())
			tail = aux;
		currentSize++;
	}

	@Override
	public void addLast(E element) {
		// TODO Auto-generated method stub
		SListNode<E> aux = new SListNode<E> (element, null);
		if (!isEmpty())
			tail.next = aux;
		tail = aux;
		if (isEmpty())
			head = aux;
		currentSize++;
	}

	@Override
	public void add(int position, E element) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (position < 0 || position > currentSize)
			throw new InvalidPositionException("Invalid Position.");
		if (position == 0)
			addFirst(element);
		else
			if (position == currentSize)
				addLast(element);
			else {
				SListNode<E> aux = head;
				for (int i = 1; i <= position; i++)
					aux = aux.getNext();
				SListNode<E> newNo = new SListNode<E> (element, aux.getNext());
				aux.setNext(newNo);
			}
		currentSize++;
	}

	@Override
	public E removeFirst() throws NoElementException {
		// TODO Auto-generated method stub
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		if (currentSize==1) {
			head = null;
			tail = null;
		}
		else {
			head = head.getNext();
		}
		currentSize--;
		return null;
	}

	@Override
	public E removeLast() throws NoElementException {
		// TODO Auto-generated method stub
		if (currentSize==0) 
			throw new NoElementException("No such element.");
		SListNode<E> aux = head;
		if (currentSize==1) {
			head = null;
			tail = null;
		}
		else {
			for(int i=1 ;i < currentSize ;i++)
				aux=aux.getNext();
			tail = aux;
			tail.next = null;
		}
		currentSize--;
		return null;
	}

	@Override
	public E remove(int position) throws InvalidPositionException {
		// TODO Auto-generated method stub
		if (position<0 || position > currentSize)
			throw new InvalidPositionException("Invalid Position.");
		if (position==0)
			removeFirst();
		else
			if (position == currentSize - 1)
				removeLast();
			else {
				SListNode<E> aux = head;
				for (int i = 1; i < position; i++)
					aux = aux.getNext();
				aux.setNext(aux.getNext().getNext());
			}
		currentSize--;
		return null;
	}

	@Override
	public boolean remove(E element) {
		SListNode<E> aux = head;
		for (int i = 0; i < this.size()-1; i++) {
			if(aux.getElement().equals(element)) {
				this.remove(i);
				return true;
			}
			aux = aux.getNext();
		}
		return false;
	}

}
