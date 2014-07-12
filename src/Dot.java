
public class Dot {

    // DO NOT MODIFY
    public static final int COLOR_BLUE = 1;
    public static final int COLOR_RED = 2;
    public static final int COLOR_GREEN = 3;
    public static final int COLOR_YELLOW = 4;
    public static final int COLOR_PURPLE = 5;
    public static final int NUM_COLORS = 5;
    
    private int myColor;
    protected boolean beRemoved; 

    /**
     * Generates a dot with a random color attribute.
     * Note: There is a variable defined as NUM_COLORS which should be used 
     * for generating random colors (ints). You random number generator 
     * should return an integer from 1 to NUM_COLORS inclusive, (not 1 - 5).
     */
    public Dot() {
    	 // YOUR CODE HERE
    	int i = (int) (Math.random() * NUM_COLORS + 1);  // generates a random prime number between 1 and NUM_COLORSxs
    	
    	myColor = i;
    }
    
    /**Generates a dot with an input color. */
    public Dot(int color) {
    //	YOUR CODE HERE
    	if (color < 1 || color > NUM_COLORS){
    		System.out.println("color int out of bounds");
    		return;
    	}
    	myColor = color;
    }
    
    /**Returns the integer representation of a dot's color (myColor). */
    public int getColor() {
    	return myColor; 
    }
    
    /**Returns whether or not this dot is the same color as otherDot. */
    public boolean isSameColor(Dot otherDot){
    	// YOUR CODE HERE
    	return otherDot.getColor()==this.getColor();
    }
    
        /**
     * Returns whether or not this dot is the same color 
     * as the argument, which is also an integer representation.
     */
    public boolean isColor(int color) {
    	// YOUR CODE HERE
    	return false;
    }
    
    /**
     * ADDED METHOD
     * Updates the beRemoved state variable to true.
     */
    public void removeDot() {
    	// OUR CODE HERE 
    	beRemoved = true; 
    }
    /**
     * ADDED METHOD
     * Returns the beRemoved state variable 
     */
    public boolean removeStatus() {
    	// OUR CODE HERE 
    	return beRemoved;
    }
    
     
}
