import static org.junit.Assert.*;

import org.junit.Test;


public class DotTest {

	/** New Game Test Methods */

	@Test
	public void testingDotCreation() {
		
		/**
		Dot dotTester = new Dot(); 
		Dot dotTester2 = new Dot(); 
		Dot dotTester3 = new Dot(); 
		Dot dotTester4 = new Dot(); 
		Dot dotTester5 = new Dot();
		Dot dotTester6 = new Dot(); 
		*/
	}
	
	@Test 
	public void testingGetColor1() {
		Dot dotTester = new Dot(); 
		int randomColor = dotTester.getColor();
		assertTrue(randomColor > 0 && randomColor < 6);
	}
	
	@Test 
	public void testingGetColor2() {
		Dot dotTester = new Dot(2); 
		assertTrue(dotTester.getColor() == 2);
	}

	@Test 
	public void testingGetColor3() {
		Dot dotTester = new Dot(2); 
		assertFalse(dotTester.getColor() == 3);
	}
	
	
}
