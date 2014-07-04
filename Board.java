import java.awt.Point;
import java.util.ArrayList;

public class Board {

    // Our representation of the board, where myBoard[0][0] represents 
    // the bottom left dot.
    private Dot[][] myBoard;
    // Total number of moves allowed for a single game session.
    private static int movesAllowed = 5;

    // DO NOT MODIFY
    public static final int MINSIZE = 4;
    public static final int MAXSIZE = 10;

    /**
     * Sets up the board's data and starts up the GUI. N is side length of the
     * board. (Number of dots per side) N will default to 0 if a non-integer is
     * entered as an argument. If there are no arguments, N will default to 10;
     */
    public static void main(String[] args) {
        int n = 0;
        try {
            n = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            n = 0;
        } catch (IndexOutOfBoundsException e) {
            // This line is run if no command line arguments are given.
            // If you wish to modify this line to test, remember to change it back to 
            // n = 10;
            n = 10;
        }
        GUI.initGUI(n);
    }

    /**
     * When the New Game button is clicked, a new randomized board is constructed.
     * Sets up the board with input SIZE, such that the board's side length is SIZE.
     * Note: The Board is always a square, so SIZE is both the length and the width.
     * Generate a random board such that each entry in board is a random color. 
     * (represented by an int). Should print and error and System.exit if the size 
     * is not within the MINSIZE and MAXSIZE. This constructor will only be called 
     * once per game session. Initialize any variables if needed here.
     */
    public Board(int size) {
    	// YOUR CODE HERE
    }
    
    /**
     * This constructor takes in a 2D int array of colors and generates a preset board
     * with each dot matching the color of the corresponding entry in the int[][] 
     * arguement. This constructor can be used for predetermined tests.
     * You may assume that the input is valid (between MINSIZE and MAXSIZE etc.) 
     * since this is for your own testing.
     */
    public Board(int[][] preset) {
    	// YOUR CODE HERE
    }
    
    /**
     * Returns the array representation of the board. (Data is used by GUI).
     */
    public Dot[][] getBoard() {
    	return myBoard;
    }

    /**
     * Returns the number of moves allowed per game. This value should not
     * change during a game session.
     */
    public static int getMovesAllowed() {
        // YOUR CODE HERE
        return 0;
        }

    /**
     * Change the number of moves allowed per game. This method can be used for 
     * testing.
     */
    public static void setMovesAllowed(int n) {
        // YOUR CODE HERE
    }

    /** Returns the number of moves left. */
    public int getMovesLeft() {
        // YOUR CODE HERE
    	return 0;
    }

    /**
     * Return whether or not it is possible to make a Move. (ie, there exists
     * two adjacent dots of the same color.) If false, the GUI will report a
     * game over.
     */
    public boolean canMakeMove() {
    	// YOUR CODE HERE
        return false;
    }

    /**
     * Returns if the board is in a state of game over. The game is over if there
     * are no possible moves left or if the player has used up the maximum
     * allowed moves.
     */
    public boolean isGameOver() {
    	// YOUR CODE HERE
        return false;
    }

    /**
     * Returns whether or not you are allowed to select a dot at X, Y at the
     * moment. Remember, if the game is over, you cannot select any dots.
     */
    public boolean canSelect(int x, int y) {
        // YOUR CODE HERE
        return false;
    }
     
    /**
     * Is called when a dot located at myBoard[X][Y] is selected on the GUI.
     */
    public void selectDot(int x, int y) {
        // YOUR CODE HERE
    }

    /**
     * Checks if you are allowed to deselect the chosen point.
     * Assumes at least one point has been selected already.
     * You can only deselect the most recent point you have selected.
     * (You can select 3 dots and deselect them in reverse order.)
     */
    public boolean canDeselect(int x, int y) {
    	return false;
    }
    	
    /**Is called when a dot located at myBoard[X][Y] is deselected on the GUI. */
    public void deselectDot(int x, int y) {
        // YOUR CODE HERE
    }

    /**Returns the number of currently selected dots */
    public int numberSelected() {
    	// YOUR CODE HERE
    	return 0;
    }

    /**
     * Is called when the "Remove" button is clicked. Puts all selected dots in
     * a "removed" state. If no dots should be removed, throw a CantRemoveException. 
     * You must also create your own Exception Class named CantRemoveException.
     * If selected dots form a closed shape, remove all dots on the board that have
     * the same color as the selected dots.
     */
    public void removeSelectedDots() throws CantRemoveException {
    	// YOUR CODE HERE
    }

    /**
     * Puts the dot at X, Y in a removed state. Later all dots above a
     * removed dot will drop.
     */
    public void removeSingleDot(int x, int y) {
        // OPTIONAL
    }

    /**
     * Return whether or not the selected dots form a closed shape. Refer to
     * diagram for what a closed shape looks like.
     */
    public boolean isClosedShape() {
    	// YOUR CODE HERE
    	return false;
    }

    /**
     * Removes all dots of the same color of the dots on the currently selected
     * dots. Assume it is confirmed that a closed shape has been formed from the
     * selected dots.
     */
    public void removeSameColor() {
    	// OPTIONAL
    }

    /**
     * Once the dots are removed. Rearrange the board to simulate the dropping of
     * all of the dots above the removed dots. Refer to diagram in the spec for clarity.
     * After dropping the dots, there should exist some "bad" dots at the top. 
     * (These are the blank dots on the 4-stage diagram.)
     * fillRemovedDots will be called immediately after this by the GUI so that random 
     * dots replace these bad dots with new ones that have a randomly generated color.
     */
    public void dropRemainingDots() {
    	// YOUR CODE HERE
    }

    /**
     * After removing all dots that were meant to be removed, replace any
     * removed dot with a new dot of a random color.
     */
    public void fillRemovedDots() {
        // YOUR CODE HERE
    }

    /**
     * Return the current score, which is called by the GUI when it needs to
     * update the display of the score. Remember to update the score in your 
     * other methods.
     */
    public int getScore() {
    	// YOUR CODE HERE
        return 0;
    }

    /**
     * Search the board for a sequence of 4 consecutive points which form a
     * square such that out of all possible 2x2 squares, selecting this one 
     * yields the most points.
     */
    public ArrayList<Point> findBestSquare() {
        // YOUR CODE HERE
        return null;
    }

    /**Prints the the board any way you like for testing purposes. */
    public void printBoard() {
        // OPTIONAL
    }

    public void printBoard(String msg) {
    	System.out.println(msg);
    	printBoard();
    }
}
