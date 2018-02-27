package bst;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Tests {
	private BinarySearchTree<Integer> intTree;
	
	
	@Before
	public void setUp() throws Exception {
		intTree = new BinarySearchTree<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		intTree = null;
	}

	@Test
	public void test() {
	}
	@Test
	public final void testAdd(){
		intTree.add(20);
		intTree.add(10);
		intTree.add(47);
		intTree.add(30);
		intTree.add(12);
		intTree.printTree();
	
	}
	@Test
	public final void testDuplicate(){
		intTree.add(10);
		intTree.add(10);
		assertTrue(intTree.size() == 1);
		assertFalse(intTree.add(10));
	}
	@Test
	public final void testHeight(){
		intTree.add(20);
		intTree.add(10);
		intTree.add(47);
		intTree.add(30);
		assertTrue(intTree.height() == 3);
	}
	@Test
	public final void testEmptyHeight(){
		assertTrue(intTree.height() == 0);
	}
	@Test
	public final void testSize(){
		intTree.add(20);
		intTree.add(10);
		intTree.add(47);
		intTree.add(30);
		assertTrue(intTree.size()==4);
	}
	@Test
	public final void testEmptySize(){
		assertTrue(intTree.size() == 0);
	}
	@Test
	public final void newTreeSize(){
		assertTrue(intTree.size()==0);
	}
}
