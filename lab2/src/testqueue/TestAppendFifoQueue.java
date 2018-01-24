package testqueue;

import static org.junit.Assert.*;
import static org.junit.Assert.assertArrayEquals;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import queue_singlelinkedlist.FifoQueue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class TestAppendFifoQueue {
	private FifoQueue<Integer> myIntQueue1;
	private FifoQueue<Integer> myIntQueue2;

	@Before
	public void setUp() throws Exception {
		myIntQueue1 = new queue_singlelinkedlist.FifoQueue<Integer>();
		myIntQueue2 = new queue_singlelinkedlist.FifoQueue<Integer>();

	}

	@After
	public void tearDown() throws Exception {
		myIntQueue1 = null;
		myIntQueue2 = null;

	}

	@Test
	public final void testDoubleEmpty() {
		assertTrue("Before: Wrong size of queue 1", myIntQueue1.size() == 0);
		assertTrue("Before: Wrong size of queue 2", myIntQueue1.size() == 0);
		myIntQueue1.append(myIntQueue2);

		assertTrue("After: Wrong size of queue 1", myIntQueue1.size() == 0);
		assertTrue("After: Wrong size of queue 2", myIntQueue1.size() == 0);

		myIntQueue2.append(myIntQueue1);
		assertTrue("After: Wrong size of queue 1", myIntQueue1.size() == 0);
		assertTrue("After: Wrong size of queue 2", myIntQueue1.size() == 0);
	}

	@Test
	public final void testEmptyToNonEmpty() {
		myIntQueue2.offer(1);
		myIntQueue2.offer(2);
		myIntQueue2.offer(3);
		FifoQueue<Integer> tempqueue = new FifoQueue<Integer>(); // expected
																	// order
		tempqueue.offer(1);
		tempqueue.offer(2);
		tempqueue.offer(3);

		assertTrue("Before: Wrong size of queue 2", myIntQueue1.size() == 0);
		assertTrue("Before: Wrong size of queue 2", myIntQueue2.size() == 3);

		myIntQueue2.append(myIntQueue1);
		assertTrue("After: Wrong size of queue 2", myIntQueue2.size() == 3);
		assertTrue("After: wrong size of queue 2", myIntQueue1.size() == 0);

		Iterator<Integer> itr2 = myIntQueue2.iterator();
		Iterator<Integer> itrtemp = tempqueue.iterator();

		while (itr2.hasNext() && itrtemp.hasNext()) {
			assertEquals("Order of elements is not maintained", itr2.next(), itrtemp.next());
		}
	}

	@Test
	public final void testNonEmptyToEmpty() {
		myIntQueue2.offer(1);
		myIntQueue2.offer(2);
		myIntQueue2.offer(3);
		FifoQueue<Integer> tempqueue = new FifoQueue<Integer>(); // expected
																	// order
		tempqueue.offer(1);
		tempqueue.offer(2);
		tempqueue.offer(3);

		assertTrue("Before: Wrong size of queue 1", myIntQueue1.size() == 0);
		assertTrue("Before: Wrong size of queue 2", myIntQueue2.size() == 3);

		myIntQueue1.append(myIntQueue2);

		assertTrue("After: wrong size of queue 1", myIntQueue1.size() == 3);
		assertTrue("After: Wrong size of queue 2", myIntQueue2.size() == 0);

		Iterator<Integer> itr1 = myIntQueue1.iterator();
		Iterator<Integer> itrtemp = tempqueue.iterator();

		while (itr1.hasNext() && itrtemp.hasNext()) {
			assertEquals("Order of elements is not maintained", itr1.next(), itrtemp.next());
		}
	}

	@Test
	public final void testRegular() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);

		myIntQueue2.offer(4);
		myIntQueue2.offer(5);
		myIntQueue2.offer(6);

		// Expected order
		FifoQueue<Integer> tempqueue = new FifoQueue<Integer>();
		tempqueue.offer(1);
		tempqueue.offer(2);
		tempqueue.offer(3);
		tempqueue.offer(4);
		tempqueue.offer(5);
		tempqueue.offer(6);

		assertTrue("Before: Wrong size of queue 1", myIntQueue1.size() == 3);
		assertTrue("Before: Wrong size of queue 2", myIntQueue2.size() == 3);

		myIntQueue1.append(myIntQueue2);

		assertTrue("After: wrong size of queue 1", myIntQueue1.size() == 6);
		assertTrue("After: Wrong size of queue 2", myIntQueue2.size() == 0);

		Iterator<Integer> itr1 = myIntQueue1.iterator();
		Iterator<Integer> itrtemp = tempqueue.iterator();

		while (itr1.hasNext() && itrtemp.hasNext()) {
			Integer x = itr1.next();
			Integer y = itrtemp.next();
			assertEquals("Order of elements is not maintained", x, y);
		}

	}
	@Rule
	public final ExpectedException exception = ExpectedException.none();
	
	@Test
	public final void testIdenticalConcatenation() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		exception.expect(IllegalArgumentException.class);
		myIntQueue1.append(myIntQueue1);
	}

	/* D7:
	 * Ja det väl möjligt att använda en ArrayList, men här är det fortfarande mer
	 * effektivt att använda en linkedlist, (eftersom den ska användas som FiFo-kö)
	 * d.v.s, vi har O(1) för att plocka elementen i början/slutet, jämfört med arraylist
	 * som måste flytta elementen om det inte plockas från slutet (och eventuellt resizea)
	 * 
	 * Genom att delegera överlåter man all implementation till en färdigskriven klass.
	 * Huruvida denna klassen hanterar det bättre eller ej vet vi inte, däremot kan det
	 * när vi överlåter implementationen finnas onödiga metoder -> onödiga minnesplatser
	 * som vi inte behöver för att lösa vårt problem. Dessutom är vi mer medvetna om 
	 * vår klass, hur den fungerar (givet kommentarer och beskrivningar), och vilka begränsningar
	 * som finns.
	 * 
	 * Se svaret ovan, men framförallt om man har ett specifikt problem som inte kan lösas genom att
	 * använda en befintlig klass (ex en map som kan ha både Integers och Strings som values).
	 * Eller om man arbetat med stora datamängder, där varje byte räknas, då kan det vara värt att 
	 * implementera en mer minnessnål applikation (ex använder linkedlist i collections
	 * doubly linked lists, d.v.s. ytterligare en pekare(bakåt) som då tar upp mer plats i minnet).
	 * I vanliga fall är det bättre att använda en vältestad befintlig klass. 
	 * 
	 * 
	 * Nej, det enda vi kan vara säkra på är att just de testfallen vi har prövat kommer att fungera,
	 * och utifrån vilka assertions vi har gjort, vi kanske testar fel villkor, false istället för true
	 * på ett ställe. Om man då utgår från att det inte är några problem kan detta få konsekvenser i framtiden.
	 * Dessutom bör man testa igenom om man fortsätter att implementera.
	 * 
	 */
}
