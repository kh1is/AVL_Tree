package eg.edu.alexu.csd.filestructure.avl.cs23;

import eg.edu.alexu.csd.filestructure.avl.IAVLTree;
import eg.edu.alexu.csd.filestructure.avl.INode;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {

	public static final int MAX_HEIGHT_DIFF = 1;
	public Node<T> root = Node.NULL;
	int size = 0;

	@Override
	public void insert(T key) {
		// TODO Auto-generated method stub
		Node<T> node = new Node<T>(key);
		insert(this.root, node);
		this.balanceTree(node);
	}

	public void insert(Node<T> root, Node<T> node) {
		if (root == Node.NULL) {
			this.root = node;
			size++;
			return;
		}
		if (root.getValue().compareTo(node.getValue()) < 0) {
			if (root.hasRightChild())
				insert((Node<T>) root.getRightChild(), node);
			else {
				root.setRightChild(node);
				node.setParent(root);
				size++;
				return;
			}
		} else if (root.getValue().compareTo(node.getValue()) > 0) {
			if (root.hasLeftChild()) {
				insert((Node<T>) root.getLeftChild(), node);
			} else {
				root.setLeftChild(node);
				node.setParent(root);
				size++;
				return;
			}
		}
		// else return;

	}

	@Override
	public boolean delete(T key) {
		// TODO Auto-generated method stub

		//searching for the node to delete
        Node<T> currentNode = (Node<T>) this.getFromSearch(this.root, key);
        //if the node wasn't found
        if (currentNode == Node.NULL) {
            return false;
        }
        //if the tree has only the root
        if (this.size == 1) {
            this.root = Node.NULL;
        }
        else{
            //refrence to the parent
            Node<T> parentNode = currentNode.getParent();
            if (currentNode.isLeaf()) {
                if (parentNode.getLeftChild() == currentNode) {
                    parentNode.setLeftChild(Node.NULL);
                    if(parentNode.getRightChild() == Node.NULL){
                        parentNode.setHeight(0);
                        this.refreshHeight(parentNode);
                    }
                } else {
                    parentNode.setRightChild(Node.NULL);
                    if(parentNode.getLeftChild() == Node.NULL){
                        parentNode.setHeight(0);
                        this.refreshHeight(parentNode);
                    }
                }
                currentNode = Node.NULL;
                //balancing the tree after deleting
                this.balanceTree(parentNode);
            }
            else{
                Node maxNode = (Node) this.getMaxLeft(currentNode);
                T temp = currentNode.getValue();
                currentNode.setValue((T) maxNode.getValue());
                maxNode.setValue(temp);
                this.delete(temp);
               
            }
        }
        this.size--;
        return true;
	}

	private void refreshHeight(Node<T> node){
        if (node != this.root){
            Node<T> parentNode = node.getParent();
            Node<T> rightChild = (Node<T>) parentNode.getRightChild();
            Node<T> leftChild = (Node<T>) parentNode.getLeftChild();
            if(rightChild.getHeight() >= leftChild.getHeight()){
                if(rightChild.getHeight()+1 != parentNode.getHeight()){
                    parentNode.setHeight(rightChild.getHeight()+1);
                    refreshHeight(parentNode);
                }
            }else{
                if(leftChild.getHeight()+1 != parentNode.getHeight()){
                    parentNode.setHeight(leftChild.getHeight()+1);
                    refreshHeight(parentNode);
                }
            }
        }
    }
	
	private INode<T> getMaxLeft(Node<T> node) {
		Node<T> maxNode = (Node<T>) node.getLeftChild();
		if (maxNode != Node.NULL) {
			while (maxNode.hasRightChild()) {
				maxNode = (Node<T>) maxNode.getRightChild();
			}
			
		}
		else {
			maxNode = (Node<T>) node.getRightChild();
			
		}
		return maxNode;
	}

	@Override
	public boolean search(T key) {
		if (getFromSearch((Node<T>) root, key) == Node.NULL) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public int height() {
		return root.getHeight();
	}

	@Override
	public INode<T> getTree() {
		return root;
	}

	private Node<T> getFromSearch(Node<T> node, T key) {

		Node<T> temp = Node.NULL;

		if (node != Node.NULL) {

			if (key.compareTo(node.getValue()) > 0) {
				getFromSearch((Node<T>) node.getRightChild(), key);
			} else if (key.compareTo(node.getValue()) < 0) {
				getFromSearch((Node<T>) node.getLeftChild(), key);
			} else {
				temp = node;
			}
		}
		return temp;
	}

	void printPreorder(Node<T> node) {
		if (node == Node.NULL)
			return;
		System.out.print(node.getValue() + " ");
		printPreorder((Node<T>) node.getLeftChild());
		printPreorder((Node<T>) node.getRightChild());
	}
	
	public void balanceTree(Node<T> node){
		
		if(node == Node.NULL){
			return;
		}
		else{
			
			if(node.right.getHeight()  - node.left.getHeight() > MAX_HEIGHT_DIFF){
				
				if(node.right.right.getHeight() >= node.right.left.getHeight())
					rotateLeft(node);
				else
					rotateRightLeft(node);
			}
			else if(node.left.getHeight() - node.right.getHeight() > MAX_HEIGHT_DIFF){
				
				if(node.left.left.getHeight() >= node.left.right.getHeight()){
					rotateRight(node);
				}
				else{
					
					rotateLeftRight(node);
				}
				
			}
			
			else balanceTree(node.parent);
			
		}
		
	}
		
	private void rotateRight(Node<T> node){
		boolean flag = false;
		if(node.parent == Node.NULL){
			flag = true;
		}
		
		Node<T> temp = node.left;
		temp.parent = node.parent;
		if(temp.parent != Node.NULL){
			if(node.parent.left == node)
				node.parent.left = temp;
			else if(node.parent.right == node)
				node.parent.right = temp;
		}
		node.left = temp.right;
		if(node.left != Node.NULL)
			node.left.parent = node;
		temp.right = node;
		node.parent = temp;

		if(flag){
			this.root = temp;

		}
	}
	private void rotateLeft(Node<T> node){
		
		Node<T> grandParent = node.parent;
		boolean flag = false;
		if(node.parent == Node.NULL){
			flag = true;
		}
	
		Node<T> temp = node.right;
		temp.parent = node.parent;
		if(temp.parent != Node.NULL){
			if(node.parent.left == node)
				node.parent.left = temp;
			else if(node.parent.right == node)
				node.parent.right = temp;
		}
		node.right = temp.left;
		if(node.right  != Node.NULL)
			node.right.parent = node;
		temp.left = node;
		node.parent = temp;
		
		if(flag){
			this.root = temp;
		}
	}
	private void rotateLeftRight(Node<T> node){
		rotateLeft(node.left);
		rotateRight(node);
	}
	private void rotateRightLeft(Node<T> node){
		rotateRight(node.right);
		rotateLeft(node);
	}

}
