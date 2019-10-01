package iterator;

import java.util.Iterator;

class Node<T> {
	public T value;
	public Node<T> left, right, parent;

	public Node(T value) {
		this.value = value;
	}

	public Node(T value, Node<T> left, Node<T> right) {
		this.value = value;
		this.left = left;
		this.right = right;

		left.parent = right.parent = this;
	}
	
	public Iterator<Node<T>> preOrder()
	  {
		Iterator<Node<T>> iterator = new PreOrderIterator(this);
		
		return iterator;
	  }
}

class PreOrderIterator<T> implements Iterator<T> {
	private Node<T> current, root;
	private boolean yieldedStart;

	public PreOrderIterator(Node<T> root) {
		this.root = current = root;
	}

	private boolean hasRightmostParent(Node<T> node) {
		if (node.parent == null)
			return false;
		else {
			return (node == node.parent.left) || hasRightmostParent(node.parent);
		}
	}

	@Override
	public boolean hasNext() {
		return current.left != null || current.right != null || hasRightmostParent(current);
	}

	@Override
	public T next() {
		return this.getNextNode().value;
	}
	
	private Node<T> getNextNode() {
		if (!yieldedStart) {
			yieldedStart = true;
			return current;
		}

		if (current.left != null) {
			current = current.left;
			return current;
			
		} else if (current.right != null) {
			current = current.right;
			return current;
			
		} else {
			Node<T> parent = current.parent;
			while (parent.right == null || current == parent.right) {
				current = parent;
				parent = current.parent;
			}
			
			current = parent.right;
			
			return current;
		}
	}
}

class Demo {
	public static void main(String[] args) {
		//    a
		//   / \
		//  b  c
		// Must be a,b,c : PreOrder Tree Traversal
		Node<Character> root = new Node<>(
				'a',
				new Node<>('b'),
				new Node<>('c')
		);
		
		// https://en.wikipedia.org/wiki/Tree_traversal#Pre-order
//		Node<Character> root_complex = new Node<>(
//				'F',
//				new Node<>('B', new Node<>('A'), new Node<>('D', new Node<>('C'), new Node<>('E'))),
//				new Node<>('G', null, new Node<>('I', new Node<>('H'), null))
//		);

		Iterator<Node<Character>> iterator = root.preOrder();
		
		while (iterator.hasNext()) {
			System.out.print("" + iterator.next() + ",");
		}
	}
}
