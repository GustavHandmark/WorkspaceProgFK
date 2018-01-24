package dequeue;

import singleLinkedList.SingleLinkedList;

public class Dequeue<E> {
	private Node<E> first; // reference to the first element
	private Node<E> last; // reference to the last element

	/** Creates an empty dequeue. */
	public Dequeue() {
		first = last = null;
	}

	/** Inserts the specified element at the beginning of this dequeue. */
	public void addFirst(E x) {
		Node<E> n = new Node<E>(x);
		if (first == null) {
			first = last = n;
		} else {
			n.next = first;
			first = n;
		}

	}

	/** Inserts the specified element at the end of this dequeue. */
	public void addLast(E x) {
		Node<E> n = new Node<E>(x);
		if (last == null) {
			first = last = n;
		} else {
			last.next=n;
			last = n;
		}
	}

	/**
	 * Removes and returns the first element in this dequeue. Returns null if
	 * this dequeue is empty.
	 */
	public E removeFirst() {
		if(first == null ){
			return null;
		} else {
			E temp = first.element;
			first = first.next;
			if (first == null){
				last = null;
			}
			return temp;
		}
		
	}

	/**
	 * Removes and returns the last element in this dequeue. Returns null if
	 * this dequeue is empty.
	 */
	public E removeLast() {
		if(first==null){
			return null;
		} else {
			E temp = last.element;
			if(first==last){
				first = last = null;
			} else {
				Node<E> p = first;
				while(p.next != last){
					p=p.next;
				}
				last = p;
				p.next=null;
			}
			return temp;
		}
	}

	private static class Node<E> {
		private E element;
		private Node<E> next;

		private Node(E element) {
			this.element = element;
			next = null;
		}
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		Node<E> n = first;
		while (n != null) {
			b.append(n.element);
			b.append(' ');
			n = n.next;
		}
		return b.toString();
	}

	public static void main(String[] args) {
		Dequeue<Integer> q = new Dequeue<Integer>();
		// Fyll i egen kod här
		System.out.println(q);
	}

}
