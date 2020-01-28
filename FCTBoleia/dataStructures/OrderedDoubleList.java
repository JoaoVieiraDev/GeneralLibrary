package dataStructures;


public class OrderedDoubleList<K extends Comparable<K>,V> implements SortedMap<K,V> {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     *  Node at the head of the list.
     */
    protected DListNode<Entry<K,V>> head;

    /**
     * Node at the tail of the list.
     */
    protected DListNode<Entry<K,V>> tail;

    /**
     * Number of elements in the list.
     */
    protected int currentSize;



    public OrderedDoubleList(){
        head = null;
        tail = null;
        currentSize = 0;

    }


    @Override
    public Entry<K, V> minEntry() throws EmptyBSTException {

        if(currentSize ==0) {throw new EmptyBSTException();}
       return head.getElement();
    }

    @Override
    public Entry<K, V> maxEntry() throws EmptyBSTException {

        if(currentSize ==0) {throw new EmptyBSTException();}

        return tail.getElement();
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public V find(K key) {
        if (isEmpty()){
            return null;
        }
        DListNode<Entry<K,V>> current = tail;
        for (int i = 0; i<currentSize; i++){
            if (current.getElement().getKey().equals(key)){
                return current.getElement().getValue();
            }
            current = current.getPrevious();
        }
        return null;
    }

    @Override
    public V insert(K key, V value) {

        DListNode<Entry<K,V>> node = findNode(key);
        V ret = null;
        if (node == null){
            DListNode<Entry<K,V>> newNode = new DListNode<Entry<K, V>>(new EntryClass<>(key, value),null,null);
            if (isEmpty()){
                head = newNode;
                tail = newNode;
                currentSize++;
            }else {
                newNode.setNext(head);
                head.setPrevious(newNode);
                head = newNode;
                currentSize++;
            }
            return null;
        }
        if (node.getElement().getKey().equals(key)){
            ret = node.getElement().getValue();
            node.setElement(new EntryClass<>(key, value));
        }else{
            DListNode<Entry<K, V>> newNode = new DListNode<Entry<K, V>>(new EntryClass<>(key, value), node, node.getNext());
            if (tail.getElement().getKey().equals(node.getElement().getKey())){
                tail.setNext(newNode);
                tail = newNode;
                currentSize++;
            }else {
                node.getNext().setPrevious(newNode);
                node.setNext(newNode);
                currentSize++;
            }
        }

        return ret;

    }

    @Override
    public V remove(K key) {
        DListNode<Entry<K,V>> node = findNode(key);
        V ret = null;
        if (node == null || !node.getElement().getKey().equals(key)){
            return null;
        }else{
            ret = node.getElement().getValue();
            if (currentSize == 1){
                head = null;
                tail = null;
                currentSize = 0;
            }else if (head.getElement().getKey().equals(key)){
                head.getNext().setPrevious(null);
                head = head.getNext();
                currentSize--;
            }else if (tail.getElement().getKey().equals(key)){
                tail.getPrevious().setNext(null);
                tail = tail.getPrevious();
                currentSize--;
            }else{
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                currentSize--;
            }
        }
        return ret;
    }
    
    @Override
    public V removeWithCustomComparator(K key) {
        
    	DListNode<Entry<K,V>> current = tail;
    	DListNode<Entry<K,V>> node = null;
        for (int i = 0; i<currentSize; i++){
            if (current.getElement().getKey().compareTo(key) == 0){
                node = current;
                break;
            }
            current = current.getPrevious();
        }
    	
        V ret = null;
        if (node == null || !(node.getElement().getKey().compareTo((key)) == 0)){
            return null;
        }else{
            ret = node.getElement().getValue();
            if (currentSize == 1){
                head = null;
                tail = null;
                currentSize = 0;
            }else if (head.getElement().getKey().compareTo((key)) == 0){
                head.getNext().setPrevious(null);
                head = head.getNext();
                currentSize--;
            }else if (tail.getElement().getKey().compareTo((key)) == 0){
                tail.getPrevious().setNext(null);
                tail = tail.getPrevious();
                currentSize--;
            }else{
                node.getPrevious().setNext(node.getNext());
                node.getNext().setPrevious(node.getPrevious());
                currentSize--;
            }
        }
        return ret;
    }

    @Override
    public Iterator<Entry<K,V>> iterator( ) {
        return new DoublyLLIterator<>(head,tail);
    }

    private DListNode<Entry<K,V>> findNode(K key){
        if (isEmpty()){
            return null;
        }
        DListNode<Entry<K,V>> current = tail;
        for (int i = 0; i<currentSize; i++){
            if (current.getElement().getKey().compareTo(key)<=0){
                return current;
            }
            current = current.getPrevious();
        }
        return null;
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
}
