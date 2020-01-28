package dataStructures;

public class BST<K extends Comparable<K>,V> implements SortedMap<K,V> {

	private static final long serialVersionUID = 1L;

	//The root
	protected BSTNode<Entry<K,V>> root;
		
	//Number of elements
	protected int currentSize;
	
	@Override
	public Iterator<Entry<K,V>> iterator() {
		return new BSTOrderIterator<K,V>(root);
	}
	
	@Override
	public Iterator<K> keys() throws NoElementException {
		return null;
	}
	@Override
	public Iterator<V> values() throws NoElementException {
		return null;
	}
	
	protected BSTNode<Entry<K,V>> findNode(BSTNode<Entry<K,V>> n, K key) {
		BSTNode<Entry<K,V>> res=null;
		if (n!=null) {
			int num= key.compareTo(n.getElement().getKey());	
			if (num==0)
				res=n;
			else if (num<0)
				res=findNode(n.getLeft(),key);
			else
				res=findNode(n.getRight(),key);
		}
		return res;
	}
	

	@Override
	public V find(K key) {
		BSTNode<Entry<K,V>> res=findNode(root,key);
		if (res==null)
			return null;
		return res.getElement().getValue();
	}
	@Override
	public V insert(K key, V value) {
		Entry<K,V> aux = new EntryClass<K, V>(key, value);
		if (root == null) {
			root = new BSTNode<Entry<K,V>>(aux);
		}
		else {
			BSTNode<Entry<K,V>> prev = null, curr = root;
			boolean lastLeft = false;
			while (curr != null) {
				int comparison = key.compareTo(curr.getElement().getKey());
				prev = curr;
				if (comparison < 0) {
					curr = curr.getLeft();
					lastLeft = true;
				}
				else {
					curr = curr.getRight();
					lastLeft = false;
				}
			}
			if (lastLeft) {
				prev.left = new BSTNode<Entry<K,V>>(aux);
				prev.left.parent = prev;
			}
			else {
				prev.right = new BSTNode<Entry<K,V>>(aux);
				prev.right.parent = prev;
			}
		}
		currentSize++;
		return null;
	}
	@Override
	public V remove(K key) {
		BSTNode<Entry<K,V>> aux = findNode(root, key);
		BSTNode<Entry<K,V>> toReturn = aux;
		if (aux.getLeft() == null && aux.getRight() == null) {
			if (aux == root)
				root = null;
			else {
				if (aux.parent.left == aux)
					aux.parent.left = null;
				else
					aux.parent.right = null;
			}
		}
		if (aux.getLeft() != null && aux.getRight() != null) {
			BSTNode<Entry<K,V>> next = aux.getLeft();
			while (next.right != null)
				next = next.right;
			aux.element = next.element;
			remove(next.getElement().getKey());
		}
		else if (aux.getLeft() != null) {
			aux.left.parent = aux.parent;
			if (aux.parent.left == aux)
				aux.parent.left = aux.left;
			else
				aux.parent.right = aux.left;
		} else if (aux.getRight() != null) {
			aux.right.parent = aux.parent;
			if (aux.parent.left == aux)
				aux.parent.left = aux.right;
			else
				aux.parent.right = aux.right;
		}
		currentSize--;
		return toReturn.element.getValue();
	}
	@Override
	public Entry<K, V> minEntry() throws NoElementException {
		if ( this.isEmpty() )
			throw new NoElementException();
		return this.minNode(root).getElement();
	}
	@Override
	public Entry<K, V> maxEntry() throws NoElementException {
		if ( this.isEmpty() )
			throw new NoElementException();
		return this.maxNode(root).getElement();
	}

	protected BSTNode<Entry<K,V>> maxNode( BSTNode<Entry<K,V>> node ){
		if ( node.getRight() == null )
			return node;
		return this.maxNode(node.getRight());
	}
	
	protected BSTNode<Entry<K, V>> minNode(BSTNode<Entry<K,V>> node ) {
		if (node.getLeft() == null)
			return node;
		return this.minNode(node.getLeft());
	}

	@Override
	public boolean isEmpty() {
		return currentSize==0;
	}

	@Override
	public int size() {
		return currentSize;
	}

	@Override
	public V removeWithCustomComparator(K key) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
