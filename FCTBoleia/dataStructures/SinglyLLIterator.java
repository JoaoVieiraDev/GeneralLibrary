package dataStructures;

public class SinglyLLIterator<E> implements Iterator<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private SListNode<E> firstNode;
	private SListNode<E> nextToReturn;
	
	public SinglyLLIterator(SListNode<E> head) {
		firstNode = head;
		rewind();
	}
	
	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return nextToReturn != null;
	}

	@Override
	public E next() throws NoSuchElementException {
		// TODO Auto-generated method stub
		if (!this.hasNext())
			throw new NoSuchElementException("No such element.");
		E element = nextToReturn.getElement();
		nextToReturn = nextToReturn.getNext();
		return element;
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		nextToReturn = firstNode;
	}

}
