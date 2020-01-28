package dataStructures;

import dataStructures.BSTNode;

public class BSTOrderIterator<K,V> implements Iterator<Entry<K,V>> {

	static final long serialVersionUID = 0L;
	private BSTNode<Entry<K,V>> current;
	private Stack<BSTNode<Entry<K,V>>> stack;

	public BSTOrderIterator(BSTNode<Entry<K, V>> root) {
		current = root;
		stack = new StackInList<>();
	}

	@Override
	public boolean hasNext() {
		return (!stack.isEmpty() || current != null);
	}

	@Override
	public Entry<K,V> next() throws NoSuchElementException {
		while (current != null) {
			stack.push(current);
			current = current.left;	
		}
		current = stack.pop();
		Entry<K,V> node = current.element;
		current = current.right;
		return node;
	}

	@Override
	public void rewind() {
		// TODO Auto-generated method stub
		
	}

}
