import java.awt.Point;
import java.util.ArrayList;

public class Board {

    // Our representation of the board, where myBoard[0][0] represents 
    // the bottom left dot.
    private Dot[][] myBoard;
    // Total number of moves allowed for a single game session.
    private static int movesAllowed = 5;  // describes how  many moves are in a single game 
    
    // Added instance variables
    private int score; 
    private int currentX;
    private int currentY;
    private int movesMade;
    private ArrayList<Point> selectedPoints;
    private ArrayList<Dot> selectedDots; 
  
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
    	if (size < MINSIZE || size > MAXSIZE ) {
    		System.err.println("Size is outside of constraints");
    		throw new IllegalArgumentException();
    	} else {
    		myBoard = new Dot[size][size];  			// initialize board with correct size
    		for (int i = 0; i < size; i++) {			// start at row 0, i = columns
    			for (int j = 0; j < size; j++) {		// iterate across the row, j = rows
    				//System.out.println(i+ " "+j );
    				myBoard [i][j] = new Dot(); 
    			}
    		}
    		
    	}
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
    	if (preset.length < MINSIZE || preset.length > MAXSIZE ) {
    		System.err.println("Size is outside of constraints");
    		throw new IllegalArgumentException();
    	} else {
    		myBoard = new Dot[preset.length][preset.length];  			// initialize board with correct size
    		for (int i = 0; i < preset.length; i++) {			// start at row 0, i = columns
    			for (int j = 0; j < preset.length; j++) {		// iterate across the row, j = rows
    				myBoard [i][j] = new Dot(preset[i][j]); 
    				System.out.println(myBoard[i][j].getColor());
    			}
    		}
    		
    	}
    	getBoard();
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
    	return movesAllowed;
        }

    /**
     * Change the number of moves allowed per game. This method can be used for 
     * testing.
     */
    public static void setMovesAllowed(int n) {
       movesAllowed = n; 
    }

    /** Returns the number of moves left. */
    public int getMovesLeft() {
    	return movesAllowed - movesMade;
    }

    /**
     * Return whether or not it is possible to make a Move. (ie, there exists
     * two adjacent dots of the same color.) If false, the GUI will report a
     * game over.
     * 
     * 
     * 
     * Implement only at start of game and in remove method 
     */
    public boolean canMakeMove() {	
    	for (int i = 0; i < myBoard.length; i++) {						// checking upwards
    		for (int j = 0; j < myBoard.length-1 ; j++) {
    			if (myBoard[i][j].isSameColor(myBoard[i][j+1])) {
    				System.out.println("found a doable move");
    				return true;
    			}
    		}
    	}
    	for (int i = 0; i < myBoard.length-1; i++) {						// checking side ways
    		for (int j = 0; j < myBoard.length; j++) {
    			if (myBoard[i][j].isSameColor(myBoard[i+1][j])) {
    				System.out.println("found a doable move");
    				return true;
    			}
    		}
    	}
    	return false;
    }

    /**
     * Returns if the board is in a state of game over. The game is over if there
     * are no possible moves left or if the player has used up the maximum
     * allowed moves.
     */
    public boolean isGameOver() {
    	if (!this.canMakeMove() || this.getMovesLeft() == 0) {
    		return true; 
    	}
    	return false; 
    }

    /**
     * Returns whether or not you are allowed to select a dot at X, Y at the
     * moment. Remember, if the game is over, you cannot select any dots.
     */
    public boolean canSelect(int x, int y) {
    	// YOUR CODE HERE
    	
    	if (this.getMovesLeft()>0) {					// if you have moves left 
    		if (selectedPoints.size() == 0) {			// and if no points are selected
        		return true;							// then any move is legal
        	} 
        	// Case where Dots already selected
        	Point lastPoint = recentPoint(); 				// grab most recent Point object
        	Dot lastDot = recentDot(); 						// grab most recent Dot object
        	Dot desiredDot = myBoard[x][y];					// grab desired Dot from myBoard
        	if (lastDot.isSameColor(desiredDot) && this.adjacentCheck(x, y, lastPoint)) {	// checking adjacency and colors
        		return true; 							// return true if same COLOR and ADJACENT 
        	} else return false;  
    	}
    	// Case where no moves left
    	return false;									
    }
    
    public boolean isLegal(int x, int y) {								// checks if potential (x,y) is legal, i.e. on myBoard
    	if(x < 0 || x >= myBoard.length || y < 0 || y >= myBoard.length){
    		return false;
    	}
    	return true;
    }
    	
    public boolean adjacentCheck(int x, int y, Point lastPoint) {		// checks if (x,y) is adjacent to lastPoint 
    	int lastPointX = lastPoint.x;									// grab x-value of lastPoint
    	int lastPointY = lastPoint.y;									// grab y-value of lastPoint
    	
    	if	(x == (lastPointX + 1) && y == lastPointY) {
    		return true;
    	} else if (x == (lastPointX - 1) && y == lastPointY) {
    		return true;
    	} else if (x == lastPointX && y == (lastPointY + 1)) {
    		return true;
    	} else if (x == lastPointX && y == (lastPointY - 1)){
    		return true;
    	}
    	return false;													// return false if x,y is NOT adjacent to lastPoint
    }
    	
    	
    /**
     * Is called when a dot located at myBoard[X][Y] is selected on the GUI.
     * 
     */
    
    public void selectDot(int x, int y) {							// call canDeselect();  
    	// YOUR CODE HERE
    	if (canSelect(x,y)) {										// if canSelect Dot, add Dot to selectedPoints and selectedDots
    		Point P = new Point(x,y); 
    		selectedPoints.add(P);
    		Dot D = myBoard[x][y];
    		selectedDots.add(D); 
    	}
    	if (this.canDeselect(x,y)) {								// if canDeselect Dot, call deSelect() method 
    		this.deselectDot(x, y);
    	} 
    } 
    

    /**
     * Checks if you are allowed to deselect the chosen point.
     * Assumes at least one point has been selected already.
     * You can only deselect the most recent point you have selected.
     * (You can select 3 dots and deselect them in reverse order.)
     */
    public boolean canDeselect(int x, int y) {
    	// YOUR CODE HERE 
    	Point mostRecentPoint = recentPoint(); 		  					// grabs most recently selected dot
    	if (mostRecentPoint.x == x && mostRecentPoint.y == y) {         // checks if input x, y match the selected dot 
    		return true; 
    	}
    	return false; 
    }
    	
    /**Is called when a dot located at myBoard[X][Y] is deselected on the GUI. */
    public void deselectDot(int x, int y) {
        // YOUR CODE HERE
    	
    	if (canDeselect(x,y)) {                 			// if can deselect dot
        	selectedPoints.remove(this.recentPoint());  	// remove Point from the arrayList selectedPoints
        	selectedDots.remove(this.recentDot());
        }
    	System.out.println("cannot deselect point");    	// else print out error message because dot can't be removed  
    }

    
    /** ADDED CODE: helper method to keep track of most recently selected point */
    public Point recentPoint() {
    	// OUR CODE HERE
    	if (selectedPoints.size() == 0) {  		// make sure that there is something in the selected Dots array.
    		throw new IllegalArgumentException("no point recently selected");
    	}
    	Point recentPoint; 												// declare reference variable for point
    	recentPoint = (Point) selectedPoints.get(selectedPoints.size() - 1);	// grab last selected dot from array list 
    	return recentPoint; 												// return the most recently selected Dot
    }
    
    /** ADDED CODE: helper method to grab recently selected Dot object */
    public Dot recentDot() {
    	// OUR CODE HERE
    	if (selectedDots.size() == 0) {  		// make sure that there is something in the selected Dots array.
    		throw new IllegalArgumentException("no point recently selected");
    	}
    	return (Dot) selectedDots.get(selectedDots.size() - 1);												// return the most recently selected Dot
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
     * 
     */
    public void removeselectedDots() throws CantRemoveException { 		 
    	// YOUR CODE HERE
    	if (selectedPoints.size() < 2) {													
    		throw new CantRemoveException("You must select another Dot tobe able to remove"); 
    	} 
    	// grabs the last Dot selected, need this to compare color against entire board
    	Dot dotToRemove = recentDot(); 							
    	
    	if (this.isClosedShape()) {									
	    	// Update removeStatus of all Dots of same color
    		for (int i = 0; i < myBoard.length; i++) {		
				for (int j = 0; j < myBoard.length; j++) {		
					if (dotToRemove.isSameColor(myBoard[i][j])) { 	
						myBoard[i][j].removeDot();					
					}
				}		
	    	}
    	}
    	// If Dots to be removed do not form closed shape: 
    	if (!this.isClosedShape()) {
    		for (Point pt : selectedPoints) {
    			myBoard[pt.x][pt.y].removeDot();
    		}
    	}
    	// Set all Dots with remove state to null
		this.setDotsNull();
		// Update ArrayList of Points and Dots to null
		this.resetSelected();
    }

    
    /** ADDED METHOD
     * Is called after Dots in myBoard have updated removeDot states. 
     */
    public void setDotsNull() {
    	for (int i = 0; i < myBoard.length; i++) {			// go through array set all Dots of Same color to removed State
			for (int j = 0; j < myBoard.length; j++) {		
				if (myBoard[i][j].removeStatus()) { 	// call isSameColor on dotToRemove and each Dot in the array 
					myBoard[i][j] = null; 				// update the remove status of the Dot in the Array
				}
			}
    	}
    }
    
    /**
     * ADDED METHOD
     * Resets selected arrays, selectedPoints and selectedDots to null.
     */
    public void resetSelected() {
    	this.selectedDots = null;
    	this.selectedPoints = null;
    }
    
    
    
    
    
    /**
     * Puts the dot at X, Y in a removed state. Later all dots above a
     * removed dot will drop.
     */
    public void removeSingleDot(int x, int y) {  						// set specific dot to null 
        // OPTIONAL
    }

    /**
     * Return whether or not the selected dots form a closed shape. Refer to
     * diagram for what a closed shape looks like.
     */
    public boolean isClosedShape() {  			// most recently selected dot must be adjacent to at least 2 other selected dots 
    	// YOUR CODE HERE
    	return false;
    }

    /**
     * Removes all dots of the same color of the dots on the currently selected
     * dots. Assume it is confirmed that a closed shape has been formed from the
     * selected dots.
     */
    public void removeSameColor() {				// call only when isClosedShape returns true 
    	// OPTIONAL								// this removes all Dots of the same color, set locations in array to null  
    }

    /**
     * Once the dots are removed. Rearrange the board to simulate the dropping of
     * all of the dots above the removed dots. Refer to diagram in the spec for clarity.
     * After dropping the dots, there should exist some "bad" dots at the top. 
     * (These are the blank dots on the 4-stage diagram.)
     * fillRemovedDots will be called immediately after this by the GUI so that random 
     * dots replace these bad dots with new ones that have a randomly generated color.
     */
    public void dropRemainingDots() {  				// calls remove method
    	// YOUR CODE HERE
    }

    /**
     * After removing all dots that were meant to be removed, replace any
     * removed dot with a new dot of a random color.
     */
    public void fillRemovedDots() {   				// iterates of the array, looking for null spots, fills with random dots
        // YOUR CODE HERE
    }

    /**
     * Return the current score, which is called by the GUI when it needs to
     * update the display of the score. Remember to update the score in your 
     * other methods.
     */
    public int getScore() {  		// add to score when you remove dots correctly, AFTER REMOVE
        return score;
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
    
    public static class CantRemoveException extends Exception {
    	public CantRemoveException(String message) {
    		super(message);
    	}
    }
}
