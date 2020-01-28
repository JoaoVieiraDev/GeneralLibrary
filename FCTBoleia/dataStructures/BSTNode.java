package dataStructures;

import java.io.Serializable;

public class BSTNode<E> implements Serializable{
	 
	static final long serialVersionUID = 0L;
	
	protected BSTNode<E> parent;
	protected BSTNode<E> left;
	protected BSTNode<E> right;
	protected E element;

	
	BSTNode(E elem,BSTNode<E> parent,BSTNode<E> left,BSTNode<E> right){
		this.parent=parent;
		this.left=left;
		this.right=right;
		element=elem;
	}
	
	
	BSTNode(E elem){
		this(elem,null,null,null);
	}
	
	E getElement() {
		return element;
	}
	
	BSTNode<E> getLeft() {
		return left;
	}
	
	BSTNode<E> getRight() {
		return right;
	}

	BSTNode<E> getParent() {
		return parent;
	}
	boolean isInternal() {
		return true;
	}
}