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
		Board board1 = new Board(5);
		board1.getBoard();
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
