package dataStructures;

public class MapWithSinglyLinkedList<K, V> implements Map<K, V> {

	static final long serialVersionUID = 0L;
	
	protected SListNode<Entry<K,V>> head;
	
	protected SListNode<Entry<K,V>> tail;
	
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
	public Iterator<K> keys() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<V> values() throws NoElementException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public V find(K key) {
		// TODO Auto-generated method stub
		SListNode<Entry<K,V>> auxNo = head;
		boolean found = false;
		while (auxNo != null && !found) {
			if (auxNo.getElement().getKey().equals(key))
				found = true;
			else
				auxNo = auxNo.getNext();
		}
		if (found)
			return auxNo.getElement().getValue();
		else
			return null;
	}

	@Override
	public V insert(K key, V value) {
		// TODO Auto-generated method stub
		V old = find(key);
		if (old != null)
			return old;
		else {
		Entry<K,V> node = new EntryClass<K, V>(key, value);
		SListNode<Entry<K,V>> auxNo = new SListNode<Entry<K,V>>(node, head);
		head = auxNo;
		if (isEmpty())
			tail = auxNo;
		currentSize++;
		return null;
		}
		
	}

	@Override
	public V remove(K key) {
		// TODO Auto-generated method stub
		V old = find(key);
		if (old == null)
			return null;
		else {
			SListNode<Entry<K,V>> aux = head;
			while (!aux.getNext().getElement().getValue().equals(old))
				aux = aux.getNext();
			aux.setNext(aux.getNext().getNext());
			return old;
		}
	}

	@Override
	public Iterator<Entry<K, V>> iterator() throws NoElementException {
		// TODO Auto-generated method stub
		if (currentSize==0) throw new NoElementException("List is empty.");
		return new SinglyLLIterator<Entry<K,V>>(head);
	}

}
