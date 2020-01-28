package dataStructures;

public class QueueInList<E extends Comparable<E>> implements Queue<E> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected List<E> elements;
	
	public QueueInList(){
		elements= new DoublyLinkedList<E>();
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public int size() {
		return this.elements.size();
	}

	@Override
	public void enqueue(E element) {
		elements.addLast(element);
	}

	@Override
	public E dequeue() throws NoElementException {
		if (isEmpty())
			throw new NoElementException("Queue is empty.");
		return elements.removeFirst();
	}

	@Override
	public void remove(E element) {
		this.elements.remove(elements.find(element));
	}
	
}
