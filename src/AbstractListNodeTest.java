import static org.junit.Assert.*;

import org.junit.Test;

public class AbstractListNodeTest {

	@Test
	public void testGet() {
		NonemptyListNode testNode = new NonemptyListNode(1);
		NonemptyListNode testNode1 = new NonemptyListNode(3, testNode);
		NonemptyListNode testNode2 = new NonemptyListNode(4, testNode1);

		assertEquals(testNode2.get(0), 4);
		assertEquals(testNode2.get(1), 3);

		assertEquals(testNode2.get(2), 1);
	}





	@Test
	public void testSize1() {
		NonemptyListNode testNode = new NonemptyListNode(2);
		assertTrue(1 == testNode.size());
	}
	
	@Test
	public void testSize2() {
		EmptyListNode testNode = new EmptyListNode();
		assertTrue(0 == testNode.size());
	}
	
	@Test
	public void testSize3() {
		NonemptyListNode testNode = new NonemptyListNode(1);
		NonemptyListNode testNode1 = new NonemptyListNode(3, testNode);
		NonemptyListNode testNode2 = new NonemptyListNode(4, testNode1);
		
		assertEquals(testNode2.size(), 3);
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
	
	@Test
	public void testReverse1() {
		// test if this is empty
		EmptyListNode empty1 = new EmptyListNode();
		AbstractListNode empty2 = empty1.reverse();
//		System.out.println(empty1.reverse());
		assertEquals(empty2, empty1);
	}
	
	@Test
	public void testReverse2() {
		// test if this is not empty
		NonemptyListNode reverse1 = new NonemptyListNode(3);
		NonemptyListNode reverse2 = new NonemptyListNode(4, reverse1);
		NonemptyListNode reverse3 = new NonemptyListNode(5, reverse2);

		AbstractListNode reversed = reverse3.reverse();
		System.out.println(reversed);
		//String i = "( 3 4 5 )";
		//assertEquals(reversed, i);
	}
	
	@Test 
	// Test if this list is empty, return list2
	public void testAppendInPlace() {
		EmptyListNode empty = new EmptyListNode();
		NonemptyListNode notEmpty = new NonemptyListNode(3);
		//System.out.println(empty.appendInPlace(notEmpty));
		
		assertEquals(empty.appendInPlace(notEmpty), notEmpty);
	}
	
	@Test 
	// If this isn't empty, the myNext in its last NonemptyListNode
	// is replaced by list2
	public void testAppendInPlace2() {
		NonemptyListNode notEmpty2 = new NonemptyListNode(2);
		NonemptyListNode notEmpty1 = new NonemptyListNode(1, notEmpty2);
		
		NonemptyListNode notEmpty4 = new NonemptyListNode(4);
		NonemptyListNode notEmpty3 = new NonemptyListNode(3, notEmpty4);
		
		NonemptyListNode resultAppend1 = new NonemptyListNode(4);
		NonemptyListNode resultAppend2 = new NonemptyListNode(3, resultAppend1);
		NonemptyListNode resultAppend3 = new NonemptyListNode(2, resultAppend2);
		NonemptyListNode resultAppend4 = new NonemptyListNode(1, resultAppend3);
		
		//System.out.println("calling on notEMpty1 "+ notEmpty1.appendInPlace(notEmpty3));
		//System.out.println("calling resultAppend4 " + resultAppend4);
		assertEquals(notEmpty1.appendInPlace(notEmpty3), resultAppend4);
	}
	@Test
	public void testStraightforward() {
		AbstractListNode empty1 = new EmptyListNode();
		AbstractListNode empty2 = new EmptyListNode();
		empty1 = empty1.appendInPlace (empty2);
//		System.out.println(empty1.appendInPlace(empty2));
//		System.out.println("( )");
		EmptyListNode test = new EmptyListNode();
//		System.out.println(test);
		assertEquals ("( )", empty1.toString());
		assertEquals ("( )", empty2.toString());
		AbstractListNode a = new NonemptyListNode ("a");
		a = a.appendInPlace (empty1);
//		System.out.println(a);
		assertEquals ("( a )", a.toString());
		assertEquals ("( )", empty1.toString());
		AbstractListNode b = new NonemptyListNode ("b");
		AbstractListNode c = new NonemptyListNode ("c");
		b = b.appendInPlace (c);
		assertEquals ("( b c )", b.toString());
		assertEquals ("( c )", c.toString());
		}
	
	@Test
	public void testMerge() {
		NonemptyListNode notEmpty2 = new NonemptyListNode(3);
		NonemptyListNode notEmpty1 = new NonemptyListNode(1, notEmpty2);
		
		NonemptyListNode notEmpty4 = new NonemptyListNode(6);
		NonemptyListNode notEmpty3 = new NonemptyListNode(4, notEmpty4);
		NonemptyListNode notEmpty5 = new NonemptyListNode(2, notEmpty3);
		
		System.out.println(AbstractListNode.merge(notEmpty1, notEmpty5));
	}
	
}