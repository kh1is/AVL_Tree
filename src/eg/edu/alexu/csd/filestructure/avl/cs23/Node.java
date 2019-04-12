package eg.edu.alexu.csd.filestructure.avl.cs23;

import eg.edu.alexu.csd.filestructure.avl.INode;

public class Node<T extends Comparable<T>> implements INode<T> {
	public Node<T> left = NULL;
	public Node<T> right = NULL;
	public Node<T> parent = NULL;
	private T value;
	private int height;
	public static final NullNode NULL = new NullNode();

	
	public Node(){
		
	}
	public Node(T value) {
		this.value = value;
	}

	public Node(Node<T> parent, Node<T> left, Node<T> right, T value) {
		this.parent = parent;
		this.left = left;
		this.right = right;
		this.value = value;
	}

	@Override
	public INode<T> getLeftChild() {
		// TODO Auto-generated method stub
		if (left == NULL) {
			return NULL;
		} else
			return left;
	}

	@Override
	public INode<T> getRightChild() {
		// TODO Auto-generated method stub
		return right;

	}

	@Override
	public T getValue() {
		// TODO Auto-generated method stub
		return this.value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;
	}

	public void setLeftChild(Node<T> left) {
		this.left = left;
	}

	public void setRightChild(Node<T> right) {
		this.right = right;
	}

	public void setParent(Node<T> parent) {
		this.parent = parent;
	}

	public Node<T> getParent() {
		return this.parent;
	}

	/*
	public int getHeight() {
		
				this.height = 1 + Integer.max(
						((Node<T>) this.getRightChild()).getHeight(),
						((Node<T>) this.getLeftChild()).getHeight());
				return this.height;
		
	}

	public void setHeight(int height) {
		this.height = height;
	}
*/
	
	public int getHeight() {
        return this.height;
    }
	
    public void setHeight(int height) {
        this.height = height;
    }
    
	public boolean hasLeftChild() {
		if (left == NULL)
			return false;
		else
			return true;
	}

	public boolean hasRightChild() {
		if (right == NULL)
			return false;
		else
			return true;
	}

	public boolean hasParent() {
		if (parent == NULL)
			return false;
		else
			return true;
	}

	public boolean isLeaf() {
		if (right == NULL && left == NULL) {
			return true;
		} else
			return false;
	}

	@Override
	public String toString() {
		return this.getValue() + "";
	}

	public void nullifyParent() {

		this.parent = NULL;
	}
}