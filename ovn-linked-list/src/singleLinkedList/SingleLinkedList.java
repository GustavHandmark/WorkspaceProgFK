package singleLinkedList;

import java.util.*;

public class SingleLinkedList<E> {
	ListNode<E> first;

	/** Creates an empty lis.t */
	public SingleLinkedList() {
		first = null;
	}

	/** Inserts the specified element at the beginning of this list. */
	public void addFirst(E e) {
		ListNode<E> n = new ListNode<E>(e);
		n.next = first;
		first = n;
	}

	/**
	 * Returns the first element in this list. Throws NoSuchElementException if
	 * this list is empty
	 */
	public E getFirst() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		return first.element;
	}

	/**
	 * Returns the last element in this list. Throws NoSuchElementException if
	 * this list is empty
	 */
	public E getLast() {
		if (first == null) {
			throw new NoSuchElementException();
		}
		ListNode<E> n = first;
		while (n.next != null) {
			n = n.next;
		}
		return n.element;
	}

	/** Returns true if this collection contains the specified element. */
	public boolean contains(Object x) {
		ListNode<E> n = first;
		while (n != null) {
			if (n.element.equals(x)) {
				return true;
			}
			n = n.next;
		}
		return false;
	}

	/**
	 * Removes the first occurrence of the specified element from this list, if
	 * it is present. If this list does not contain the element, it is
	 * unchanged. Returns true if this list contained the specified element (or
	 * equivalently, if this list changed as a result of the call).
	 */
	boolean remove(Object x) {
		if (first == null) {
			return false;
		}
		if (first.element.equals(x)) {
			first = first.next;
			return true;
		}

		ListNode<E> prev = first;
		ListNode<E> n = first.next;
		while (n != null) {
			if (n.element.equals(x)) {
				prev.next = n.next;
				return true;
			}
			prev = n;
			n = n.next;
		}
		return false;
	}

	public String toString() {
		StringBuilder b = new StringBuilder();
		ListNode<E> n = first;
		while (n != null) {
			b.append(n.element);
			b.append(' ');
			n = n.next;
		}
		return b.toString();
	}

	/* Nested class. Represents a node which contains an element of type E. */
	private static class ListNode<E> {
		private E element;
		private ListNode<E> next;

		/* Creates a listnode which contains x. */
		private ListNode(E e) {
			element = e;
			next = null;
		}
	}

	public static void main(String[] args) {
		SingleLinkedList<Integer> list = new SingleLinkedList<Integer>();
		for (int i = 3; i > 0; i--) {
			list.addFirst(i);
		}
		System.out.println(list);
	}

}