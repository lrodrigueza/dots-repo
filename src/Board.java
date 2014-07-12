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
    private int movesMade;
    protected ArrayList<Point> selectedPoints;
    protected ArrayList<Dot> selectedDots; 
  
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
    		// Initialize instance variables
    		myBoard = new Dot[size][size];
    		selectedPoints = new ArrayList<Point>();
    		selectedDots = new ArrayList<Dot>();
    		// Loop to add a Dot at each index in the 2D Array
    		for (int i = 0; i < size; i++) {			
    			for (int j = 0; j < size; j++) {		
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
    		// Initialize instance variables
    		myBoard = new Dot[preset.length][preset.length];  			
    		selectedPoints = new ArrayList<Point>();
    		selectedDots = new ArrayList<Dot>();
    		// Loop to add a Dot at each index in the 2D Array
    		for (int i = 0; i < preset.length; i++) {			
    			for (int j = 0; j < preset.length; j++) {		
    				myBoard [i][j] = new Dot(preset[i][j]); 
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
     * Implement only at start of game and in remove method 
     */
    public boolean canMakeMove() {	
    	// Checking legal moves Upwards 
    	for (int i = 0; i < myBoard.length; i++) {						
    		for (int j = 0; j < myBoard.length-1 ; j++) {
    			if (myBoard[i][j].isSameColor(myBoard[i][j+1])) {
    				return true;
    			}
    		}
    	}
    	// Checking legal moves Sideways
    	for (int i = 0; i < myBoard.length-1; i++) {						
    		for (int j = 0; j < myBoard.length; j++) {
    			if (myBoard[i][j].isSameColor(myBoard[i+1][j])) {
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
    	if (!isGameOver()) {					
    		// Case where selecting first Dot 
    		if (selectedPoints.size() == 0) {			
        		System.out.println("first dot selected");
    			return true;							
        	} 
    		// Case where you are trying to select a Dot that has already been selected 
    		Point newPoint = new Point(x,y); 
    		if (selectedPoints.contains(newPoint)) {
    				return false; 
    		}
    		// Case where previous Dots already selected, and want to select a new Dot
        	Point lastPoint = recentPoint(); 				
        	Dot lastDot = recentDot(); 						
        	Dot desiredDot = myBoard[x][y];					
        	if (lastDot.isSameColor(desiredDot) && this.adjacentCheck(x, y, lastPoint)) {	
        		System.out.println("same color and adjacency check");
        		return true; 							 
        	} else return false;  
    	}
    	// Case where no moves left/Game over
    	return false;									
    }
    
   
    /** ADDED METHOD
     *  Checks if a potential move is legal
     */
    public boolean isLegal(int x, int y) {								
    	if(x < 0 || x >= myBoard.length || y < 0 || y >= myBoard.length){
    		return false;
    	}
    	return true;
    }
    	
    /** ADDED METHOD
     *  Returns whether or not point to be selected (x,y) is
     *  adjacent to the lastPoint selected. 
     */
    public boolean adjacentCheck(int x, int y, Point lastPoint) {		
    	int lastPointX = lastPoint.x;									
    	int lastPointY = lastPoint.y;									
    	
    	if	(x == (lastPointX + 1) && y == lastPointY) {
    		return true;
    	} else if (x == (lastPointX - 1) && y == lastPointY) {
    		return true;
    	} else if (x == lastPointX && y == (lastPointY + 1)) {
    		return true;
    	} else if (x == lastPointX && y == (lastPointY - 1)){
    		return true;
    	}
    	return false;													
    }
    	
    	
    /**
     * Is called when a dot located at myBoard[X][Y] is selected on the GUI.
     */
    public void selectDot(int x, int y) {						
    	// YOUR CODE HERE
    	Point P = new Point(x,y); 
    	selectedPoints.add(P);
    	Dot D = myBoard[x][y];
    	selectedDots.add(D);
    	return; 
    } 
   
    /**
     * Checks if you are allowed to deselect the chosen point.
     * Assumes at least one point has been selected already.
     * You can only deselect the most recent point you have selected.
     * (You can select 3 dots and deselect them in reverse order.)
     */
    public boolean canDeselect(int x, int y) {
    	// YOUR CODE HERE 
    	
    	Point mostRecentPoint = recentPoint(); 		  					
    	if (mostRecentPoint.x == x && mostRecentPoint.y == y) {        
    		return true; 
    	}
    	return false; 
    }
    	
    /**Is called when a dot located at myBoard[X][Y] is deselected on the GUI. */
    public void deselectDot(int x, int y) {
        // YOUR CODE HERE
    	selectedPoints.remove(this.recentPoint());  	
    	selectedDots.remove(this.recentDot());
            	
    }

    
    /** ADDED METHOD
     *  Helper method to keep track of most recently selected point 
     *  */
    public Point recentPoint() {
    	// OUR CODE HERE
    	if (selectedPoints.size() == 0) {  		
    		throw new IllegalArgumentException("no point recently selected");
    	}
    	Point recentPoint; 												
    	recentPoint = (Point) selectedPoints.get(selectedPoints.size() - 1);	
    	return recentPoint; 												
    }
    
    /** ADDED METHOD
     * Helper method to grab recently selected Dot object
     *  */
    public Dot recentDot() {
    	// OUR CODE HERE
    	if (selectedDots.size() == 0) {  		
    		throw new IllegalArgumentException("no point recently selected");
    	}
    	return (Dot) selectedDots.get(selectedDots.size() - 1);												
    }
    
    
    /**Returns the number of currently selected dots */
    public int numberSelected() {
    	return (selectedPoints.size());
    }

    /**
     * Is called when the "Remove" button is clicked. Puts all selected dots in
     * a "removed" state. If no dots should be removed, throw a CantRemoveException. 
     * You must also create your own Exception Class named CantRemoveException.
     * If selected dots form a closed shape, remove all dots on the board that have
     * the same color as the selected dots.
     * 
     */
    public void removeSelectedDots() throws CantRemoveException { 		 
    	// YOUR CODE HERE
    	if (selectedPoints.size() < 2) {													
    		throw new CantRemoveException("You must select another Dot to click Remove"); 
    	} 
    	if (isClosedShape()) {									
    		// Helper method to update removeStatus of ALL same color Dots
    		System.out.println("removing shape that iS CLOSED");
    		removeSameColor(); 
    	}
    	if (!this.isClosedShape()) {
    		// Update removeStatus ONLY of selected Dots
    		System.out.println("removing shape that iS NOT CLOSED");
    		for (Point pt : selectedPoints) {
    			myBoard[pt.x][pt.y].removeDot();
    		}
    	}
    	// Set all Dots with removeStatus to null
		setDotsNull();
		// Update ArrayList of Points and Dots to null
		resetSelected();
    }

    
    /** ADDED METHOD
     * Helper method is called after all Dots in myBoard  to be removed
     * have had their removeStatus updated to == TRUE.
     * This method sets all Dots with removeStatus == TRUE to null. 
     */
    public void setDotsNull() {
    	for (int i = 0; i < myBoard.length; i++) {			
			for (int j = 0; j < myBoard.length; j++) {		
				if (myBoard[i][j].removeStatus()) { 	
					myBoard[i][j] = null; 				
				}
			}
    	}
    }
    
    /** ADDED METHOD
     *  This helper method Resets arrays holding Points and Dots to null after 
     *  a move is made, to start keeping track of Dots in the next move. 
     */
    public void resetSelected() {
    	this.selectedDots = null;
    	this.selectedPoints = null;
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
     * 
     * Most recently selected Dot must be adjacent to at least 2 other selected dots
     */
    public boolean isClosedShape() {  			
    	// YOUR CODE HERE
    	Point lastPoint = recentPoint(); 
    	int tracker = 0; 
    	// Loop to compare last Point to other selected Points
    	for (Point aPoint : this.selectedPoints) {
    		if (lastPoint.equals(aPoint)) {
    			continue; 
    		} else if (adjacentCheck(aPoint.x, aPoint.y, lastPoint)) {
    			tracker++;
    		}
    	}
    	// Tracker will determine if last Point adjacent to 2+ points
    	if (tracker >= 2) {
    		return true ;
    	}
    	// Not enought adjacency determined
    	return false;
    }

    /**
     * Removes all dots of the same color of the dots on the currently selected
     * dots. Assume it is confirmed that a closed shape has been formed from the
     * selected dots.
     */
    public void removeSameColor() {				
    	Dot lastDot = recentDot(); 
    	// For loop to update all Dots of same color to removeStatus
    	for (int i = 0; i < myBoard.length; i++) {
    		for (int j = 0; j < myBoard.length; j++) {		
				if (lastDot.isSameColor(myBoard[i][j])) { 	
					myBoard[i][j].removeDot();					
				}
			}	
    	}
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
    	// no points are null, only remove status set to true
    	// to check remove status: Dot.removeStatus()
    	// if the Dot Object remove is true, contain it in temp and add it to the end of arrayList.
    	// Adding it to end will automatically shift the array items down.
    	// Do not use other methods. Just THIS, AND THE BOARD.
		
    	for (int i = 0; i < myBoard.length; i++) {			// start at row 0, i = columns
    		for (int j = 0; j < myBoard.length-1 ; j++) {	// iterate across the row, j = rows
    			if (myBoard[i][j].removeStatus()) {
    				Dot temp = myBoard[i][j];
    	        	for (int k = j; k < myBoard.length-1; k++ ) {	// shift Dots down
    	        		myBoard[i][k] = myBoard[i][k+1];
    	            }
    	        	myBoard[i][myBoard.length-1] = temp;			// add Dot temp to the end
    			}
    		}
    	}
    }

    /**
     * After removing all dots that were meant to be removed, replace any
     * removed dot with a new dot of a random color.
     */
    public void fillRemovedDots() {   				
    	// Loop to fill null indices in myBoard with a new Dot, with a random Dot. 
    	for (int i = 0; i < myBoard.length; i++) {			
			for (int j = 0; j < myBoard.length; j++) {		
				if (myBoard[i][j] == null) { 	
					myBoard[i][j] = new Dot(); 				
				}
			}
    	}
    }

    /**
     * Return the current score, which is called by the GUI when it needs to
     * update the display of the score. Remember to update the score in your 
     * other methods.
     */
    public int getScore() {  		
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
    	// Method 
    	public CantRemoveException(String message) {
    		super(message);
    	}
    }
}
