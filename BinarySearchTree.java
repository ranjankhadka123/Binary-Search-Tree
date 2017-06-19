
package lab5;
import java.util.ArrayList;

public class BinarySearchTree {

	private class Node {
	// The value stored in this tree node.
		private int value;

	// The left child of this node. Can be null if there is none.
		private Node leftChild;

	// The right child of this node. Can be null if there is none.
		private Node rightChild;

	// Create a node with given value and no children.
		private Node(int value) {
			this.value = value;
			this.leftChild = null;
			this.rightChild = null;
		}

		private int getValue() {
			return this.value;
		}

		private void setValue(int value) {
			this.value = value;
		}

		private Node getLeftChild() {
			return this.leftChild;
		}

		private Node getRightChild() {
			return this.rightChild;
		}

		private void setLeftChild(Node child) {
			this.leftChild = child;
		}

		private void setRightChild(Node child) {
			this.rightChild = child;
		}

		private void insertNode(Node node) {
			if (node.value < this.value) {
		// Node value is less than this, so insert
		// the node in the left subtree.
				if (this.leftChild == null) {
		    // No left child, so make node left child.
					this.leftChild = node;
				} else {
		    // Recurse into left subtree.
					this.leftChild.insertNode(node);
				}
			} else if (node.value > this.value) {
		// Node value is greater than this, so insert
		// the node in the right subtree.
				if (this.rightChild == null) {
		    // No right child, so make node right child.
					this.rightChild = node;
				} else {
		    // Recurse into right subtree.
					this.rightChild.insertNode(node);
				}
			}
		}
	}

	private Node root;
	ArrayList<Integer> array = new ArrayList<Integer>();

    // Constructor for an empty tree.
	public BinarySearchTree() {
		this.root = null;
	}

    // Inserts the given value in this tree.
    // Inserting a value more than once is treated as a no-op.
	public void insert(int value) {
		Node newNode = new Node(value);
		if (this.root == null) {
	    // BinarySearchTree empty, so set the new node as root node.
			this.root = newNode;
		} else {
			this.root.insertNode(newNode);
		}
	}

    // Checks if this BinarySearchTree contains the given value.
    // Returns true iff the value is present in this BinarySearchTree.
	public boolean find(int value) {
		Node temp = root;
		if(temp != null) {
			while(temp.value != value) {
				if(value > temp.value) {
					temp = temp.rightChild;
				} else {
					temp = temp.leftChild;
				}

			}
			if (temp.value == value) {
				return true;
			}
		}	
		return false;
	}


	public void inorder(Node root) {
		if (root != null) {
			inorder(root.getLeftChild());
			array.add(root.getValue());
			inorder(root.getRightChild());
		}
	}
    // Returns the number of entries in this BinarySearchTree that are
    // less than value.
	public int numLessThan(int value) {
		int total_minimum_number = 0;
		for (int i = 0; i < array.size(); i++) {
			if (value < array.get(i)) {
				// System.out.println("Check");
				total_minimum_number += 1;
			}
		}
		return total_minimum_number;

	// TODO: You should implement numLessThan() here.
	// Note that you will likely want to implement a helper
	// method in the Node class above.
	} 


    // Delete the given value in this tree if present
    // Returns true if the item was present and deleted.

	public boolean delete(int value) {
		Node temp = root;
		if (temp == null) {
			return false;
		} else if (!find(value)) {
			return false;
		} else {
			temp = delete(root, value);
			return true;
		}

	}

	public Node delete(Node root, int value) {
		Node temp;
		if (root.getValue() == value) {
			if((root.getRightChild() == null) && (root.getLeftChild()== null)){
				return null;
			} else if (root.getRightChild() == null) {

				temp = root.getLeftChild();
				return temp;
			} else if (root.getLeftChild() == null) {
				temp = root.getRightChild();
				return temp;
			} else {
				temp = root.getRightChild();
				while (temp.getLeftChild() != null) {
					temp = temp.getLeftChild();
				}
				temp.setLeftChild(root.getLeftChild());
				return temp;
			}
		}
		if (value < root.getValue()) {
			Node temp2 = delete(root.getLeftChild(), value);
			root.setLeftChild(temp2);
		} else {
			Node temp2 = delete(root.getRightChild(), value);
			root.setRightChild(temp2);
		}
		return root;
	}
}
