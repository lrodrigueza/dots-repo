


import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractListNodeTest {
	
	@Test
	public void testGet() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		NonemptyListNode testNode1 = new NonemptyListNode(3, testNode);
		NonemptyListNode testNode2 = new NonemptyListNode(4, testNode1);
		
		System.out.println(testNode2.get(0));
	}
	
	
	
	
	
	@Test
	public void testSize1() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		assertTrue(1 == testNode.size());
	}
	
	public void testSize2() {
		EmptyListNode testNode = new EmptyListNode();
		assertTrue(0 == testNode.size());
	}
	
	@Test
	public void testEquals() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		NonemptyListNode beginNode = new NonemptyListNode(1, testNode);
		
		NonemptyListNode testNode1 = new NonemptyListNode(2);
		NonemptyListNode compareNode = new NonemptyListNode(1, testNode1);
		assertTrue(beginNode.equals(compareNode));
	}
	
	
	@Test
	public void testToString() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		NonemptyListNode beginNode = new NonemptyListNode(1, testNode);
		
		String i = "( 1 2 )";
		assertEquals(i, beginNode.toString());
	}
	
	
	@Test
	public void testSmallest1() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		NonemptyListNode beginNode = new NonemptyListNode(1, testNode);
		Comparable correct = 1; 
		assertTrue(beginNode.smallest() == correct);
	}



	@Test 
	public void testSmallest2() {
		NonemptyListNode emptyTest = new NonemptyListNode(2);
		assertEquals(emptyTest.smallest(), 2);
	}
	
	
	@Test 
	public void testAdd() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		NonemptyListNode beginNode = new NonemptyListNode(1, testNode);
		
		AbstractListNode i = testNode.add(1);
		assertEquals(i,beginNode);
	}
	
	@Test
	public void testAddEmpty() {
		EmptyListNode tester = new EmptyListNode(); 
		NonemptyListNode expect = new NonemptyListNode(1);
		assertEquals(tester.add(1), expect);
		assertEquals(tester.add(1).size(), expect.size());
	}
	
	@Test
	// tests if argument is empty
	public void testAppend1() {
		EmptyListNode empty = new EmptyListNode(); 
		NonemptyListNode notEmpty = new NonemptyListNode(2); 
		
		assertEquals(notEmpty.append(empty), notEmpty);
	}
	
	@Test
	// tests if this list is empty
		public void testAppend2() {
			EmptyListNode empty = new EmptyListNode(); 
			NonemptyListNode notEmpty = new NonemptyListNode(2); 
			
			assertEquals(empty.append(notEmpty), notEmpty);
		}
	
	@Test
	// tests if neither are empty
		public void testAppend3() {
			
			NonemptyListNode notEmpty1 = new NonemptyListNode(2); 
			NonemptyListNode notEmpty2 = new NonemptyListNode(3);
			NonemptyListNode expect = new NonemptyListNode(3, notEmpty1);
			assertEquals(notEmpty2.append(notEmpty1), expect);
		}
	
	@Test
	public void testAppend4() {
	// 	tests if the argument and the list remain the same 
			NonemptyListNode notEmpty1 = new NonemptyListNode(2); 
			NonemptyListNode notEmpty2 = new NonemptyListNode(3);
			NonemptyListNode expect = new NonemptyListNode(3, notEmpty1);
			assertEquals(notEmpty2.append(notEmpty1), expect);
			
			NonemptyListNode notEmpty1b = new NonemptyListNode(2); 
			NonemptyListNode notEmpty2b = new NonemptyListNode(3);
			assertEquals(notEmpty1, notEmpty1b);
			assertEquals(notEmpty2, notEmpty2b);
	}
}
