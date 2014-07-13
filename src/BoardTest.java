import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Point;
import java.util.ArrayList;

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
	public void testIsGameOver1() {
		Board testBoard = new Board(5);
		testBoard.movesMade = 5;
		assertTrue(testBoard.isGameOver());
	}
	
	@Test
	public void testIsGameOver2() {
		Board testBoard = new Board(5);
		testBoard.movesMade = 3;
		assertFalse(testBoard.isGameOver());
	}
	
	@Test
	public void testGetScore1() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		chekeredBoard[0][1] = new Dot(2);
		assertTrue(testBoard.getScore() == 0);
		testBoard.selectDot(0,0);
		testBoard.selectDot(0,1);
		testBoard.removeSingleDot(0,0);
		testBoard.removeSingleDot(0,1);
		testBoard.dropRemainingDots();
		testBoard.fillRemovedDots();
		assertTrue(testBoard.getScore() == 2);
		
	}
	
	@Test
	public void testGetScore2() {
		Board testBoard = new Board(4);
		assertTrue(testBoard.getScore() == 0);
	}
	
		
	@Test
	public void testBoardConstructor() {
		Board testBoard = new Board(5);
		assertTrue(testBoard.getBoard() != null);
	}
	
	
	/** 
	 * Select/Deselect Dot Test Methods
	 **/
	
	@Test
	public void testCanDeselect1() {
		// This method assumes a point as been chosen already 
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.canDeselect(0,0));
	}
	@Test
	public void testCanDeselect2() {
		Board testBoard = new Board(4);
		testBoard.selectDot(1, 0);
		assertFalse(testBoard.canDeselect(0,0));
	}
	
	@Test
	public void testDeselectDot1() {
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.selectedPoints.size()==1 && testBoard.selectedDots.size()==1);
		testBoard.deselectDot(0, 0);
		assertTrue(testBoard.selectedPoints.size()==0 && testBoard.selectedDots.size()==0);
		
	}
	
	@Test
	public void testDeselectDot1() {
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.selectedPoints.size()==1 && testBoard.selectedDots.size()==1);
		testBoard.deselectDot(0, 0);
		assertTrue(testBoard.selectedPoints.size()==0 && testBoard.selectedDots.size()==0);
		
	}
	
	@Test
	public void testDeselectDot2() {
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.selectedPoints.size()==1 && testBoard.selectedDots.size()==1);
		testBoard.selectDot(1, 0);
		assertTrue(testBoard.selectedPoints.size()==2 && testBoard.selectedDots.size()==2);
		testBoard.deselectDot(1, 0);
		assertTrue(testBoard.selectedPoints.size()==1 && testBoard.selectedDots.size()==1);
		
	}
		
	@Test
	public void testCanMakeMove1() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(1);
		chekeredBoard[0][1] = new Dot(2);
		chekeredBoard[0][2] = new Dot(1);
		chekeredBoard[0][3] = new Dot(2);
		
		chekeredBoard[1][0] = new Dot(2);
		chekeredBoard[1][1] = new Dot(1);
		chekeredBoard[1][2] = new Dot(2);
		chekeredBoard[1][3] = new Dot(1);
		
		chekeredBoard[2][0] = new Dot(1);
		chekeredBoard[2][1] = new Dot(2);
		chekeredBoard[2][2] = new Dot(1);
		chekeredBoard[2][3] = new Dot(2);
		
		chekeredBoard[3][0] = new Dot(2);
		chekeredBoard[3][1] = new Dot(1);
		chekeredBoard[3][2] = new Dot(2);
		chekeredBoard[3][3] = new Dot(1);
		
		assertFalse(testBoard.canMakeMove()); 
		
	}
	
	@Test
	public void testCanMakeMove2() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		chekeredBoard[0][1] = new Dot(2);
		chekeredBoard[0][2] = new Dot(1);
		chekeredBoard[0][3] = new Dot(2);
		
		chekeredBoard[1][0] = new Dot(2);
		chekeredBoard[1][1] = new Dot(1);
		chekeredBoard[1][2] = new Dot(2);
		chekeredBoard[1][3] = new Dot(1);
		
		chekeredBoard[2][0] = new Dot(1);
		chekeredBoard[2][1] = new Dot(2);
		chekeredBoard[2][2] = new Dot(1);
		chekeredBoard[2][3] = new Dot(2);
		
		chekeredBoard[3][0] = new Dot(2);
		chekeredBoard[3][1] = new Dot(1);
		chekeredBoard[3][2] = new Dot(2);
		chekeredBoard[3][3] = new Dot(1);
		
		assertTrue(testBoard.canMakeMove()); 
		
	}
	
	
	@Test
	public void testCanSelect1() {
		Board testBoard = new Board(4);
		// Make sure start with 5 moves. 
		assertTrue(testBoard.getMovesLeft() == 5);
		System.out.println("size of Selected Point list is " + testBoard.selectedPoints.size()); 
		
		// Make sure canSelect returns true when No previous Dots have been selected
		assertTrue(testBoard.selectedPoints.size() == 0);
		assertTrue(testBoard.canSelect(0, 0));
	}
	
	
	@Test
	public void testCanSelect2() {
		Board testBoard = new Board(4);
		testBoard.movesMade = 5; 
		assertFalse(testBoard.canSelect(0, 0));
	}
	
	
	@Test
	public void testCanSelect3() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		chekeredBoard[0][1] = new Dot(1);
		testBoard.selectDot(0, 0);
		assertFalse(testBoard.canSelect(0,1));
	}
	
	@Test
	public void testCanSelect4() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		chekeredBoard[0][1] = new Dot(2);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.canSelect(0,1));
	}
	
	@Test
	public void testCanSelect5() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		testBoard.selectDot(0, 0);
		assertFalse(testBoard.canSelect(0,0));
	}
	@Test
	public void testCanSelect6() {
		Board testBoard = new Board(4);
		assertTrue(testBoard.canSelect(0,0));
	}
	
	@Test
	public void testSelectDot() {
		Board testBoard = new Board(4);
		assertTrue(testBoard.selectedPoints.size() == 0);
		assertTrue(testBoard.selectedDots.size() == 0);
		testBoard.selectDot(0, 0);
		assertTrue(testBoard.selectedPoints.size() == 1);
		assertTrue(testBoard.selectedDots.size() == 1);
	}
	
	
	/** 
	 * Remove Test Methods 
	 */
	
	
	@Test
	public void testRemoveSelectedDots1() {
	// Case where Removed button is clicked when < 2 Dots selected
		Board testBoard = new Board(4);
		testBoard.selectDot(0,0);
		// CantRemoveException expect = new CantRemoveException("You must select another Dot to click Remove");
		try {
			testBoard.removeSelectedDots();
		} catch (Board.CantRemoveException e) {
			System.out.println("Caught CantRemoveException when needed");
		}
	}
	

	@Test
	public void testRemoveSelectedDots2() {
	// Case where Removed button and there is Closed Shape
		Board testBoard = new Board(4);
		testBoard.selectDot(0,1);
		testBoard.selectDot(1,1);
		testBoard.selectDot(2,1);
		testBoard.selectDot(2,0);
		testBoard.selectDot(1,0);
		try {
			testBoard.removeSelectedDots();
			System.out.println("Successfully removed Closed Shape");
		} catch (Board.CantRemoveException e) {
			System.out.println("Caught Exception when not supposed to");
		}
	}
	
	@Test
	public void testRemoveSelectedDots3() {
	// Case where Removed button and there is NOT a Closed Shape
		Board testBoard = new Board(4);
		testBoard.selectDot(1, 0);
		testBoard.selectDot(2,0);
		testBoard.selectDot(2,1);
		testBoard.selectDot(1,1);
		testBoard.selectDot(0,1);
		try {
			testBoard.removeSelectedDots();
			System.out.println("Successfully removed Shape that is NOT CLOSED");
		} catch (Board.CantRemoveException e) {
			System.out.println("Caught Exception when not supposed to");
		}
	}
	
	/**
	@Test
	public void testDropRemainingDots() {
		Board testBoard = new Board(4);
		System.out.println("testing drop dots and remove");
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = new Dot(1);
		testDot[0][1] = new Dot(1);
		testBoard.selectDot(0, 0);
		testBoard.selectDot(0,1);
		testDot[0][0].removeDot();
		testDot[0][1].removeDot();
		Dot shouldBe1 = testDot[0][0];
		Dot shouldBe2 = testDot[0][1];
		testBoard.dropRemainingDots();
		testDot[0][2].assertEquals(shouldBe1);
		testDot[0][2].assertEquals(shouldBe2);
	}
	*/
	
	@Test
	public void testDropRemainingDots1() {
		Board testBoard = new Board(4);
		System.out.println("testing drop dots and remove");
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = new Dot(1);
		testDot[0][1] = new Dot(1);
		testDot[0][2].removeDot();
		testDot[0][3].removeDot();
		assertTrue(testBoard.needsShuffle(0, 0));
	}
	
	@Test
	public void testDropRemainingDots2() {
		Board testBoard = new Board(4);
		System.out.println("testing drop dots and remove");
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = new Dot(1);
		testDot[0][1] = new Dot(1);
		testDot[0][2].removeDot();
		testDot[0][3].removeDot();
		assertFalse(testBoard.needsShuffle(0, 2));
	}
	
	@Test
	public void testDropRemainingDots3() {
		Board testBoard = new Board(4);
		System.out.println("testing drop dots and remove");
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = new Dot(1);
		testDot[0][1] = new Dot(1);
		testDot[0][2].removeDot();
		testDot[0][3].removeDot();
		
		testBoard.selectDot(0,2);
		testBoard.selectDot(0,2);
		
		assertFalse(testBoard.needsShuffle(0, 2));
		testBoard.dropRemainingDots(); 
		
		assertTrue(testDot[0][2] == null);
		assertTrue(testDot[0][3] == null);
	}
	
	@Test
	public void testDropRemainingDots4() {
		Board testBoard = new Board(4);
		System.out.println("testing drop dots and remove");
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = new Dot(1);
		testDot[0][1] = new Dot(2);
		testDot[0][2] = new Dot(2);
		testDot[0][3] = new Dot(1);
		
		testBoard.selectDot(0,1);
		testBoard.selectDot(0,2);
		testDot[0][1].removeDot();
		testDot[0][2].removeDot();
		
		
		testBoard.dropRemainingDots(); 
		
		assertTrue(testDot[0][2] == null);
		assertTrue(testDot[0][3] == null);
	}
	
	
	
	
	
	@Test
	public void testFillRemovedDots1() {
		Board testBoard = new Board(4);
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = null;
		assertTrue(testDot[0][0] == null);
		testBoard.fillRemovedDots(); 
		assertTrue(testDot[0][0] != null);
		
	}
	
	@Test
	public void testFillRemovedDots2() {
		Board testBoard = new Board(4);
		Dot[][] testDot = testBoard.getBoard();
		testDot[0][0] = new Dot(1);
		testDot[0][1] = new Dot(1);
		testBoard.selectDot(0, 0);
		testBoard.selectDot(0, 1);
		testBoard.removeSingleDot(0,0);
		testBoard.removeSingleDot(0,1);
		
		testBoard.dropRemainingDots(); 
		assertTrue(testBoard.getScore() == 0);
		assertTrue(testBoard.getMovesLeft() == 5);
		testBoard.fillRemovedDots();
		assertTrue(testBoard.getScore() == 2);
		assertTrue(testBoard.getMovesLeft() == 4);
	}
	
	@Test
	public void testIsClosedShape1() {
		// Case where selected Dots DO make a closed shape
		Board testBoard = new Board(4);
		testBoard.selectDot(0, 0);
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
		testBoard.selectDot(0,1);
		testBoard.selectDot(1,1);
		testBoard.selectDot(2,1);
		testBoard.selectDot(2,0);
		testBoard.selectDot(1,0);
		assertTrue(testBoard.isClosedShape());
	}
	
	/**
	 *  Testing AI methods
	 */
	@Test
	public void testFindBestSquare1() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(1);
		chekeredBoard[0][1] = new Dot(2);
		chekeredBoard[0][2] = new Dot(1);
		chekeredBoard[0][3] = new Dot(2);
		
		chekeredBoard[1][0] = new Dot(2);
		chekeredBoard[1][1] = new Dot(1);
		chekeredBoard[1][2] = new Dot(2);
		chekeredBoard[1][3] = new Dot(1);
		
		chekeredBoard[2][0] = new Dot(1);
		chekeredBoard[2][1] = new Dot(2);
		chekeredBoard[2][2] = new Dot(1);
		chekeredBoard[2][3] = new Dot(2);
		
		chekeredBoard[3][0] = new Dot(2);
		chekeredBoard[3][1] = new Dot(1);
		chekeredBoard[3][2] = new Dot(2);
		chekeredBoard[3][3] = new Dot(1);
		
		assertNull(testBoard.findBestSquare());
		
	}
	@Test
	public void testFindBestSquare2() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		chekeredBoard[0][1] = new Dot(2);
		chekeredBoard[0][2] = new Dot(1);
		chekeredBoard[0][3] = new Dot(2);
		
		chekeredBoard[1][0] = new Dot(2);
		chekeredBoard[1][1] = new Dot(2);
		chekeredBoard[1][2] = new Dot(2);
		chekeredBoard[1][3] = new Dot(1);
		
		chekeredBoard[2][0] = new Dot(1);
		chekeredBoard[2][1] = new Dot(2);
		chekeredBoard[2][2] = new Dot(1);
		chekeredBoard[2][3] = new Dot(2);
		
		chekeredBoard[3][0] = new Dot(2);
		chekeredBoard[3][1] = new Dot(1);
		chekeredBoard[3][2] = new Dot(2);
		chekeredBoard[3][3] = new Dot(1);
		
		assertTrue(testBoard.findBestSquare() != null);
		
	}
	
	@Test
	public void testFindBestSquare3() {
		Board testBoard = new Board(4);
		Dot[][] chekeredBoard = testBoard.getBoard();
		chekeredBoard[0][0] = new Dot(2);
		chekeredBoard[0][1] = new Dot(2);
		chekeredBoard[0][2] = new Dot(2);
		chekeredBoard[0][3] = new Dot(3);
		
		chekeredBoard[1][0] = new Dot(2);
		chekeredBoard[1][1] = new Dot(2);
		chekeredBoard[1][2] = new Dot(2);
		chekeredBoard[1][3] = new Dot(3);
		
		chekeredBoard[2][0] = new Dot(2);
		chekeredBoard[2][1] = new Dot(3);
		chekeredBoard[2][2] = new Dot(1);
		chekeredBoard[2][3] = new Dot(1);
		
		chekeredBoard[3][0] = new Dot(3);
		chekeredBoard[3][1] = new Dot(2);
		chekeredBoard[3][2] = new Dot(1);
		chekeredBoard[3][3] = new Dot(1);
		
		ArrayList<Point> expect = new ArrayList<Point>();
		expect.add(new Point(0,0));
		expect.add(new Point(0,1));
		expect.add(new Point(1,1));
		expect.add(new Point(1,0));
		assertEquals(testBoard.findBestSquare(), expect);
	}
	
	@Test
	public void testnumberSelected1() {
		Board testBoard = new Board(4);
		assertTrue(testBoard.numberSelected() == 0);
	}
	
	public void testnumberSelected2() {
		Board testBoard = new Board(4);
		testBoard.selectDot(1, 0);
		testBoard.selectDot(2,0);
		assertTrue(testBoard.numberSelected() == 2);
	}
}
