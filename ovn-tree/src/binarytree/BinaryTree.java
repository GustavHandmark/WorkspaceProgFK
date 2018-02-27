package binarytree;

import java.util.ArrayList;

public class BinaryTree<E> {
	private Node<E> root;
	// private int sum;

	/** Skapar ett tomt träd. */
	public BinaryTree() {
		root = null;
	}

	/**
	 * Skapar ett binärt träd med innehållet data i roten och med leftTree som
	 * vänster underträd och rightTree som höger underträd.
	 */
	public BinaryTree(E data, BinaryTree<E> leftTree, BinaryTree<E> rightTree) {
		root = new Node<E>(data);
		if (leftTree != null) {
			root.left = leftTree.root;
		} else {
			root.left = null;
		}
		if (rightTree != null) {
			root.right = rightTree.root;
		} else {
			root.right = null;
		}
	}

	/** Skriver ut trädets noder i inorder. */
	public void printInorder() {
		printInorder(root);
	}

	private void printInorder(Node<E> n) {
		if (n != null) {
			printInorder(n.left);
			printInorder(n.right);
			System.out.println(n.element);
		}
	}

	public int nbrLeaves() {
		if(root == null){
			return 0;
		} else {
			return root.nbrLeaves();
		}
	}

	private int nbrLeaves(Node<E> n) {
		if (n == null) {
			return 0;
		} else if (n.left == null && n.right == null) {
			return 1;
		} else {
			return nbrLeaves(n.left) + nbrLeaves(n.right);
		}
	}
	public String toString(){
		StringBuilder sb = new StringBuilder();
		recInOrder(root,sb);
		return sb.toString();
	}
	
	public void recInOrder(Node<E> n, StringBuilder sb){
		if (n!=null){
			recInOrder(n.left,sb);
			sb.append(n.element.toString());
			sb.append("\n");
			recInOrder(n.right,sb);
		}
		
		
	}
	public void printPart(E min, E max){
		ArrayList<Node<E>> nodelist = new ArrayList<Node<E>>();
		recPart(root,min,max,nodelist);
		nodelist.forEach(n -> System.out.println(n.element.toString()));
		
	}
	private void recPart(Node n,E min, E max, ArrayList<Node<E>> a){
		if(n!= null){
			if(n.element.compareTo(min)>0){
				recPart(n.left,min,max,a);
			}
			if(n.element.compareTo(min)>=0 && n.element.compareTo(max)<=0){
				a.add(n);
			}
			if (n.element.compareTo(max)<0){
				recPart(n.right,min,max,a);
			}
		}	
		
		
	}

	private static class Node<E> {
		private E element;
		private Node<E> left;
		private Node<E> right;

		public Node(E data) {
			this.element = data;
			left = right = null;
		}
		private int nbrLeaves(){
			if (left == null && right == null){
				return 1;
				
			}else {
				int nbr = 0;
				if (left != null){
					nbr += left.nbrLeaves();
				}
				if(right!=null){
					nbr += right.nbrLeaves();
				}
				return nbr;
			}	
		}
		
	}
	

	public static void main(String[] args) {
		BinaryTree<String> empty = new BinaryTree<String>();
		empty.printInorder();

		BinaryTree<String> tmp = new BinaryTree<String>("d", null, null);
		BinaryTree<String> left = new BinaryTree<String>("b", null, tmp);
		tmp = new BinaryTree<String>("e", null, null);
		BinaryTree<String> right = new BinaryTree<String>("c", tmp, null);
		BinaryTree<String> tree = new BinaryTree<String>("a", left, right);
//		tree.printInorder();
		System.out.println(tree.toString());
	}

}
