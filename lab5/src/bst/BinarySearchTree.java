package bst;

import java.util.ArrayList;

public class BinarySearchTree<E extends Comparable<? super E>> {
	BinaryNode<E> root;
	int size;

	/**
	 * Constructs an empty binary searchtree.
	 */
	public BinarySearchTree() {
		size = 0;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x
	 *            element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else {
			return add(x, root);
		}
	}

	private boolean add(E x, BinaryNode<E> n) {
		if (x.compareTo(n.element) < 0) {
			if (n.left == null) {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(x, n.left);
			}
		} else if (x.compareTo(n.element) > 0) {
			if (n.right == null) {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			} else {
				return add(x, n.right);
			}
		} else { // Element already exists
			return false;
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);

	}

	// Not really necessary to use a private helpmethod.
	private int height(BinaryNode<E> n) {
		if (n == null) {
			return 0;
		} else {
			return Math.max(height(n.left), height(n.right)) + 1;
		}
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> n) {
		if (n != null) {
			printTree(n.left);
			System.out.println(n.element);
			printTree(n.right);
		}
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		E[] a = (E[]) new Comparable[size];
		toArray(root, a, 0);
		root = buildTree(a,0,size-1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the array a starting at a[index]. Returns the index of the last inserted element + 1 (the first empty position in
	 * a).
	 */
	private int toArray(BinaryNode<E> n, E[] a, int index) {
		if (n.left != null)
			index = toArray(n.left, a, index); // Traverse to the leftest node,return index

		a[index] = n.element; // add element to array
		index++; // increase index count

		if (n.right != null)
			index = toArray(n.right, a, index); // Traverse one step right.

		return index;
	}

	/*
	 * Builds a complete tree from the elements a[first]..a[last]. Elements in the array a are assumed to be in ascending order. Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(E[] a, int first, int last) {
		if(first <= last){
			int mid = (first+last)/2;
			BinaryNode<E> newRoot = new BinaryNode<E>(a[mid]);
			newRoot.left = buildTree(a,first,mid-1);
			newRoot.right = buildTree(a,mid+1,last);
			return newRoot;
		}
		else {
			return null;
		}
			
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

	public static void main(String[] args) {
		BinarySearchTree<Integer> intTree = new BinarySearchTree<Integer>();
		intTree.add(7);
		intTree.add(3);
		intTree.add(5);
		intTree.add(1);
		intTree.add(11);
		intTree.add(13);
		intTree.add(9);
		

		BinarySearchTree<Integer> bst = new BinarySearchTree<Integer>();
		BSTVisualizer draw = new BSTVisualizer("Tree", 500, 500);
		BSTVisualizer draw2 = new BSTVisualizer("Tree", 500, 500);
		for(int i = 1; i<=31; i++)
			bst.add(i);
		draw2.drawTree(bst);
		bst.rebuild();

		BinarySearchTree<String> bst2 = new BinarySearchTree<String>();
		bst2.add("Gustav");
		bst2.add("Kasper");
		bst2.add("Johan");
		bst2.add("Rand");
		bst2.add("Lovisa");

		draw.drawTree(bst);
		// draw.drawTree(bst);
		// draw.drawTree(intTree);
		// draw.drawTree(bst2);

	}

}

/*
 * Varför behövs bägge metoderna?
 * Vi vill hålla reda på vår root och i vissa fall ha denna oförändrad,
 * med en hjälpmetod kan vi alltså skicka in vår root som parameter utan att vi ändrar denna.
 * Dessutom kommer vi åt privata attribut i vår helperfunction (när vi initialiserar denna med
 * vår publika metod. Vår rekusiva metod är heller inte publik, d.v.s. syns ej för användaren.
 * 
 * Eftersom vi har gjort ett binärt sökträd så måste det finnas ett sätt att bevara någon form 
 * av ordning i trädet. D.v.s. det måste gå att jämföra våra olika element.
 * Försöker vi skapa ett träd av objekt som inte går att jämför kommer vårt träd inte att fungera,
 * och vi ställer krav på att den ska implementera comparable så vet vi också att en 
 * compareTo() metod kommer att finnas som returnerar -1,0,1.
 * 
 * TreeSet anger i konsturktorn att interfacet Comparable måste implementeras (och vara 
 * konsistent med equals). Alternativt så kan man ange en Comparator som en parameter.
 * 
 * 
 */




