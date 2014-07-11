import static org.junit.Assert.*;

import org.junit.Test;


public class BoardTest {

	/** New Game Test methos */
	
	@Test
	public void testGetMovesLeft() {
		Board board1 = new Board(5);
		System.out.println(board1.getMovesLeft());
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
				System.out.println("[" + i + ", " + j + ", " + k + "]");
			}
		}
		System.out.println(testBArray.toString());
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
	
	/** Select/Deselect Dot Test Methods */
	
	
	@Test
	public void testCanDeselect() {
		// code here
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
		// code here
	}
	
	
	
	

}
