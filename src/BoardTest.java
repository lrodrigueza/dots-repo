import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

	/** New Game Test methods */
	
	public static void main(String[] args) {
		int [][] intArray = new int [5][5];
		for (int i = 0; i < 5; i++) {			// start at row 0, i = columns
			for (int j = 0; j < 5; j++) {		// iterate across the row, j = rows
				//System.out.println(i+ " "+j );
				int k = (int) (Math.random() * Dot.NUM_COLORS + 1);
				intArray [i][j] = k;
				// System.out.println("[" + i + ", " + j + ", " + k + "]");
			}
		}
		Board testBoard = new Board(intArray);
	}
	
	@Test
	public void testGetMovesLeft() {
		Board board1 = new Board(5);
		// System.out.println(board1.getMovesLeft());
		assertTrue(board1.getMovesLeft() == 5);
	}
	
	@Test 
	public void testGetBoard() {
		// Board board1 = new Board(5);
		int [][] testBArray = new int [5][5];
		for (int i = 0; i < 5; i++) {			// start at row 0, i = columns
			for (int j = 0; j < 5; j++) {		// iterate across the row, j = rows
				//System.out.println(i+ " "+j );
				int k = (int) (Math.random() * Dot.NUM_COLORS + 1);
				testBArray [i][j] = k;
				// System.out.println("[" + i + ", " + j + ", " + k + "]");
			}
		}
		// System.out.println(testBArray.toString());
		Board board2 = new Board(testBArray);
		assertTrue(board2.canMakeMove());
	}
	
	@Test
	public void testIsGameOver() {
		// code here
	}
	
	@Test
	public void testBoardConstructor() {
		//Board test = new Board(5);
	}
	
	/** 
	 * Select/Deselect Dot Test Methods
	 **/
	
	
	@Test
	public void testCanDeselect() {
		// This method assumes a point as been chosen already 
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.canDeselect(0,0));
	}
	
	@Test
	public void testDeselectDot() {
		// code here
	}
	
	@Test
	public void testCanMakeMove() {
		// code here
	}
	
	@Test
	public void testCanSelect() {
		Board testBoard = new Board(4);
		// Make sure start with 5 moves. 
		assertTrue(testBoard.getMovesLeft() == 5);
		System.out.println("size of Selected Point list is " + testBoard.selectedPoints.size()); 
		
		// Make sure canSelect returns true when No previous Dots have been selected
		assertTrue(testBoard.selectedPoints.size() == 0);
		assertTrue(testBoard.canSelect(0, 0));
	}
	
	/** 
	 * Remove Test Methods 
	 */
	
	public void testRemoveSelectedDots() {
		
	}
	
	
	public void testDropRemainingDots() {
		
	}
	
	
	public void testFillRemainingDots() {
		
	}
	
	@Test
	public void testIsClosedShape1() {
		// Case where selected Dots DO make a closed shape
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
		System.out.println("inside isClosedShape");
		assertFalse(testBoard.isClosedShape());
		testBoard.selectDot(1, 0);
		assertFalse(testBoard.isClosedShape());
		testBoard.selectDot(1,1);
		assertFalse(testBoard.isClosedShape());
		testBoard.selectDot(0,1);
		assertTrue(testBoard.isClosedShape());
	}
	@Test
	public void testIsClosedShape2() {
		// Case where selected Dots DONT make a closed shape
		Board testBoard = new Board(4);
		System.out.println("inside isClosedShape");
		testBoard.selectDot(1, 0);
		testBoard.selectDot(2,0);
		testBoard.selectDot(2,1);
		testBoard.selectDot(1,1);
		testBoard.selectDot(0,1);
		assertFalse(testBoard.isClosedShape());
		
	}
	@Test
	public void testIsClosedShape3() {
		// Case where selected Dots DO make a closed shape
		Board testBoard = new Board(4);
		System.out.println("inside isClosedShape");
		testBoard.selectDot(0,1);
		testBoard.selectDot(1,1);
		testBoard.selectDot(2,1);
		testBoard.selectDot(2,0);
		testBoard.selectDot(1,0);
		assertTrue(testBoard.isClosedShape());
		
	}
	

}
