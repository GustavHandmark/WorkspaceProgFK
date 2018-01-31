package bisection;

public class SingleLinkedList<E> {
	private ListNode<E> first;

	public SingleLinkedList() {
		first = null;
	}

	private static class ListNode<E> {
		private E element;
		private ListNode<E> next;

		private ListNode(E e) {
			element = e;
			next = null;
		}
	}

	public void add(E x) {
		if (first == null) {
			first = new ListNode<E>(x);
		} else {
			add(first, x);
		}
	}

	private void add(ListNode<E> node, E x) {
		if (node.next == null) {
			node.next = new ListNode<E>(x);
		} else {
			add(node.next, x);
		}
	}

}