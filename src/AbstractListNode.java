import java.util.NoSuchElementException;

abstract public class AbstractListNode {

    abstract public Comparable item();
    abstract public AbstractListNode next();
    abstract public boolean isEmpty();
    abstract public Comparable get(int index);
    abstract public Comparable getHelper(int index, int curr); 
    abstract public Comparable smallestHelper(Comparable i);
 
    abstract public AbstractListNode add(Comparable i); 
    abstract public AbstractListNode append(AbstractListNode list);
    abstract public AbstractListNode appendHelper(AbstractListNode list);
    abstract public AbstractListNode reverse (); 
    abstract public AbstractListNode appendInPlace(AbstractListNode list2);
    
    	
    
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
			return result + ")";
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
	

    public int size() {
    	int i = 0; 
    	AbstractListNode temp = this;
    	while (!temp.isEmpty()) {
    		i++;
    		temp = temp.next();
    	}
    	return i; 
    }
	public void setNext(AbstractListNode b) {
		// TODO Auto-generated method stub
		
	}
    	
	public static AbstractListNode merge( AbstractListNode a, AbstractListNode b) {
		if (a.isEmpty()) {
			return b;
		} else if (b.isEmpty()) {
			return a;
		} 
		
		int totalSize = a.size() + b.size();
		NonemptyListNode aTemp = (NonemptyListNode) a;
		NonemptyListNode bTemp = (NonemptyListNode) b;
		NonemptyListNode rest; 
		
		
		
		
		
		for (int i = 0; i < totalSize; i++) {
			
			if (aTemp.isEmpty()) {
				break;
			}
			
			if (bTemp.isEmpty()) {
				break; 
			}
			
			System.out.println(i + " bfore iter a  " +  a);
			System.out.println(i + " before iter b  " +  b);
			if (aTemp.item().compareTo(bTemp.item()) < 0) {
				rest = (NonemptyListNode) aTemp.next();
				aTemp.setNext(bTemp);
				bTemp = rest; 
				aTemp = (NonemptyListNode) aTemp.next();
		
				System.out.println(i + " iter atemp  " +  aTemp);
				System.out.println(i + " iter btemp " + bTemp);
				System.out.println(i + " after iter a  " +  a);
				continue;
			
			// Case where larger or the same
			} else {
				rest = (NonemptyListNode) bTemp.next();
				bTemp.setNext(aTemp);
				aTemp = rest; 
				bTemp = (NonemptyListNode) bTemp.next();
				continue;
			}
		}	
		if (a.isEmpty()) {
			return b;
		}	
		return a; 
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
    
    public Comparable get(int index) {
    	if (index < 0 || index > this.size()) {
    		throw new IllegalArgumentException("index " + index + " out of range");
    	} 
    	int curr = 0;
    	AbstractListNode i = this;
    	while (curr < this.size()) {
    		if (curr == index) {
    			return i.item();
    		}
    		i = i.next();
    		
    		curr++;
    	}
    	return i.item();
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
		return this.reverseHelper(this.size()-1);


	}
	
	public AbstractListNode reverseHelper(int index) {
		if (index < 0) {
			return new EmptyListNode(); 
		}
		return new NonemptyListNode (this.get(index), this.reverseHelper(index - 1));
	}
	
	
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		if (this.isEmpty()) {
			return list2;
			
		} else if (list2.isEmpty()) {
			return this;
		}
		int i = 0;
		NonemptyListNode temp = this; 
		while (i < this.size()){
			if (i == this.size()-1) {
				temp.setNext(list2);
			}
			
			i++;
			temp = (NonemptyListNode) this.next(); 
		}
		
		return this; 
	}

	@Override
	public Comparable getHelper(int index, int curr) {
		// TODO Auto-generated method stub
		return null;
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

	@Override
	public AbstractListNode appendInPlace(AbstractListNode list2) {
		return list2;
	}
	

	
}