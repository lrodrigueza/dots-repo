

import java.util.NoSuchElementException;

abstract public class AbstractListNode {
	
    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public int size(); 
    abstract public Comparable get(int index);
    abstract public Comparable getHelper(int index, int curr); 
    abstract public Comparable smallestHelper(Comparable i);
    
    abstract public AbstractListNode add(Comparable i); 
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode appendHelper(AbstractListNode list);
    abstract public AbstractListNode reverse (); 

    // Every other list-processing method goes here.
    /**
     * Added basic methods
     */
    public boolean equals(Object o) {
    	
    	boolean result = false; 
    	
    	if (!(o instanceof AbstractListNode && (this.size() == ((AbstractListNode) o).size()) )) {
    		return false; 
    	}
    	
    	return helperEquals((AbstractListNode) o);
    		
    }
	    
	public boolean helperEquals(AbstractListNode o) {
		if (this.isEmpty()) {
			return true; 
		}
		return this.item().equals(o.item()) && this.next().helperEquals(o.next());
	}
	
	public String toString() {
		String result = "( ";
		if (this.size() == 0) { 
			return result + " )";
		}
		return this.toStringHelper(result);
		
	}
	
	
	public String toStringHelper(String rest) {
		if (this.isEmpty()) {
			 return rest + ")";
		 }
		rest += this.item() + " ";
		return this.next().toStringHelper(rest);
	}
	
	

	
	    	
}

class NonemptyListNode extends AbstractListNode {

    private Comparable myItem;
    private AbstractListNode myNext;

    // sets myItem = first comparable, sets myNext = rest of comparables
    public NonemptyListNode (Comparable item, AbstractListNode next) {
        myItem = item;
        if (next == null) {
            myNext = new EmptyListNode();
        } else {
            myNext = next;
        }
    }

    // one element list
    public NonemptyListNode (Comparable item) {
        this (item, new EmptyListNode());
    }

    public Comparable item() {
        return myItem;
    }

    public AbstractListNode next() {
        return myNext;
    }
    
    public boolean isEmpty() {
        return false;
    }
    
    /**
     * Added get, Smallest, setItem, setNext, add, append, reverse, appendInPlace, merge, mergeAll
     */
    
    public Comparable get (int index) {
    	if (index < 0 || index > this.size() - 1) {
    		throw new IllegalArgumentException("index out of range");
    	} 
    	
    	return this.getHelper(index,0);
    }
    
    public Comparable getHelper(int index, int curr) {
    	if (index == curr) {
    		return this.item();
    	} 
    	curr++;
    	return this.next().getHelper(index, curr);
    }
    
    
    public Comparable smallest() {
    	if (isEmpty()) {
    		throw new NoSuchElementException ("can't find smallest in empty list");
    	}
    	return this.smallestHelper(item());
    }
    
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	// Basecase: next Node is empty
    	if (next().isEmpty()) {
    		return smallestSoFar;
    	} else {
    		Comparable i = min(smallestSoFar, this.next().item());
    		return this.next().smallestHelper(i);
    	}
   	}
    
    public static Comparable min (Comparable c1, Comparable c2) {
    	if (c1.compareTo (c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }
  
    public AbstractListNode add(Comparable o){
    	NonemptyListNode i = new NonemptyListNode(o);
    	i.myNext = this; 
    	return i; 
    	}
    
   
    
    public void setItem(Comparable toBe) {
    	this.myItem = toBe; 
    }
    
  
    public void setNext(AbstractListNode toBeNext) {
    	this.myNext = toBeNext; 
    }
    

    
    public int size() {
    	int result = 0;
    	if (this.next().isEmpty()) {
    		result++;
    	} else {
    		result++;
    		this.next().size(); 
    	}
    	return result; 
    }
    
	public AbstractListNode append(AbstractListNode list) {
		if (this.isEmpty()) {
			return list.appendHelper(new EmptyListNode());
		} else if (list.isEmpty()) {
			return this.appendHelper(new EmptyListNode());
		} 
		
		return this.appendHelper(list);
	}
	
	public AbstractListNode appendHelper(AbstractListNode list) {
		if (this.isEmpty() && list.isEmpty()) {
			return new EmptyListNode(); 
		}
		
		if (this.isEmpty()) {
			list.appendHelper(new EmptyListNode());
		}
		return new NonemptyListNode(this.item(), this.next().appendHelper(list));
   
    
	}
	
	public AbstractListNode reverse() {
		if (this.isEmpty()) {
			return new EmptyListNode(); 
		}
		return myNext;
		
		
	}
	
	public AbstractListNode reverseHelper() {
		return myNext;
		
	}
	
}	

class EmptyListNode extends AbstractListNode {
    
    public EmptyListNode() {
        
    }
    
    public Comparable item() {
        throw new IllegalArgumentException ("There is no 'item' value stored in an EmptyListNode.");
    }
    
    public AbstractListNode next() {
        throw new IllegalArgumentException ("No elements follow an EmptyListNode.");
    }
    
    public boolean isEmpty() {
        return true;
    }
    

    
    /**
     * Update these abstract methods for the EmptyListNode class
     */
    public Comparable smallestHelper(Comparable smallestSoFar) {
    	// Basecase: next Node is empty
    	if (next().isEmpty()) {
    		return smallestSoFar;
    	} else {
    		Comparable i = min(smallestSoFar, this.next().item());
    		return next().smallestHelper(i);
    	}
   	}
    
    
    public static Comparable min (Comparable c1, Comparable c2) {
    	if (c1.compareTo (c2) < 0) {
    		return c1;
    	} else {
    		return c2;
    	}
    }


	@Override
	public AbstractListNode add(Comparable i) {
		
		NonemptyListNode temp = new NonemptyListNode(i);
    	temp.setNext(this); 
    	return temp; 
	}

	@Override
	public AbstractListNode append(AbstractListNode list) {
		if (list.isEmpty()) {
			return new EmptyListNode(); 
		}
		return this.appendHelper(list);
	}

	@Override
	public AbstractListNode appendHelper(AbstractListNode list) {

		if (list.isEmpty()) {
			return new EmptyListNode(); 
		}
		return new NonemptyListNode(list.item(), this.appendHelper(list.next()));
   
		
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public Comparable get(int index) {
		throw new IllegalArgumentException("can't call indices on empty Node lists");
		
	}

	@Override
	public Comparable getHelper(int index, int curr) {
		throw new IllegalArgumentException("can't call indices on empty Node lists");
	}

	@Override
	public AbstractListNode reverse() {
		// TODO Auto-generated method stub
		return null;
	}

}
