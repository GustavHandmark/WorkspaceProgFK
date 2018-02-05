package queue_singlelinkedlist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null;
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e
	 *            the element to insert
	 * @return true if it was possible to add the element to this queue, else
	 *         false
	 */
	public boolean offer(E e) {
		QueueNode<E> n = new QueueNode<E>(e);
		if (last == null) {
			last = n;
			last.next = last;
			size++;
			return true;

			// Room for an else if (limited queues etc.)

		} else {
			n.next = last.next;
			last.next = n;
			last = n;
			size++;
			return true;
		}

	}

	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (last == null) {
			return null;
		} else {
			return last.next.element;
		}
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is
	 * empty. post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (last == null) {
			return null;
		} else if (size == 1) {
			// unsure if this is the best way, could it be implemented in the
			// else{ ?
			E temp = last.next.element; //same a last.element
			last = null;
			size--;
			return temp;
		} else {
			E temp = last.next.element;
			last.next = last.next.next;
			size--;
			return temp;
		}
	}

	public void append(FifoQueue<E> q) {
		if (q.last != null) { // Shouldn't be possible to append an empty queue
			if (this == q) { 
				throw new IllegalArgumentException();
			} else if (last == null) {// should be possible to append a
										// queue(q1) to an empty queue (q2)
				last = q.last;
				last.next = q.last.next;
				size = size + q.size;
				q.size = 0;
				q.last=null;
				
			} else {
				QueueNode<E> temp = q.last.next;
				q.last.next = last.next;
				last.next = temp;
				last = q.last;
				size = size + q.size;
				q.size=0;
				q.last=null;
			}
		}
	}

	/**
	 * Returns an iterator over the elements in this queue
	 * 
	 * @return an iterator over the elements in this queue
	 */
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private class QueueIterator implements Iterator<E> {
		private QueueNode<E> pos;

		private QueueIterator() {
			if (last == null) {
				pos = null;
			} else {
				pos = last.next;
			}
		}

		public boolean hasNext() {
			return pos != null;
		}

		public E next() {
			if (pos == null) {
				throw new NoSuchElementException();
			} else if (pos.next == last.next) {
				// maybe a private boolean?
				// which checks whether or not the iteration is completed.

				E temp = pos.element;
				// Is it possible to circumvent the temp var?
				pos = null;
				return temp;
			}

			else {
				E temp = pos.element;
				pos = pos.next;
				return temp;
			}
		}
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	public static void main(String[] args) {
		FifoQueue<Integer> a = new FifoQueue<Integer>();
		FifoQueue<Integer> b = new FifoQueue<Integer>();
		FifoQueue<Integer> d = new FifoQueue<Integer>();

		a.offer(new Integer(1));
		a.offer(new Integer(2));
		a.offer(new Integer(3));
		a.offer(new Integer(4));

		b.offer(new Integer(5));
		b.offer(new Integer(6));
		b.offer(new Integer(7));
		b.offer(new Integer(8));

		a.append(b);
		d.append(a);
		System.out.println("aodkw");

		Iterator<Integer> itr = a.iterator();
		Iterator<Integer> itrb = b.iterator();
		Iterator<Integer> itrd = d.iterator();
		System.out.println(a.size());
		System.out.println(b.size());
		System.out.println(d.size());


		while (itr.hasNext()) {
			System.out.println(itr.next());

		}
		while (itrb.hasNext()) {
			System.out.println(itrb.next());
		}
		System.out.println(itrd.hasNext());
		while (itrd.hasNext()) {
			System.out.println(itrd.next());

		}
	}

}
