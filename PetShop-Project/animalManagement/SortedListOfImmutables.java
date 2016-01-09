package animalManagement;

/**
 * A SortedListOfImmutables represents a sorted collection of immutable objects 
 * that implement the Listable interface.  
 * 
 * An array of references to Listable objects is used internally to represent 
 * the list.  
 * 
 * The items in the list are always kept in alphabetical order based on the 
 * names of the items.  When a new item is added into the list, it is inserted 
 * into the correct position so that the list stays ordered alphabetically
 * by name.
 */
public class SortedListOfImmutables {

	/*
	 * STUDENTS:  You may NOT add any other instance variables to this class!
	*/
	private Listable[] items;

	/**
	 * This constructor creates an empty list by creating an internal array
	 * of size 0.  (Note that this is NOT the same thing as setting the internal
	 * instance variable to null.) 
	 */
	public SortedListOfImmutables() {
		items = new Listable[0];
	}

	/**
	 *  Copy constructor.  The current object will become a copy of the
	 *  list that the parameter refers to.  
	 *  
	 *  The copy must be made in such a way that future changes to
	 *  either of these two lists will not affect the other. In other words, 
	 *  after this constructor runs, adding or removing things from one of 
	 *  the lists must not have any effect on the other list.
	 *  
	 *  @param other the list that is to be copied
	 */
	public SortedListOfImmutables(SortedListOfImmutables other) {
		Listable[] temp = new Listable[other.items.length];
		for (int index = 0; index < other.items.length; index++) {
			temp[index] = other.items[index]; // objects are immutable so shallow copy is safe
		}
		this.items = temp;
	}

	/**
	 * Returns the number of items in the list.
	 * @return number of items in the list
	 */
	public int getSize() {
		return this.items.length;
	}
	
	/**
	 * Returns a reference to the item in the ith position in the list.  (Indexing
	 * is 0-based, so the first element is element 0).
	 * 
	 * @param i index of item requested
	 * @return reference to the ith item in the list
	 */
	public Listable get(int i) {
		return this.items[i];
	}
	
	/**
	 * Adds an item to the list.  This method assumes that the list is already
	 * sorted in alphabetical order based on the names of the items in the list.
	 * 
	 * The new item will be inserted into the list in the appropriate place so
	 * that the list will remain alphabetized by names.
	 * 
	 * In order to accommodate the new item, the internal array must be re-sized 
	 * so that it is one unit larger than it was before the call to this method.
	 *  
	 * @param itemToAdd refers to a Listable item to be added to this list
	 */
	public void add(Listable itemToAdd) {
		//add the item
		if (this.items.length == 0) { // if the array started at size zero
			Listable[] temp = new Listable[this.items.length + 1];
			temp[0] = itemToAdd;
			this.items = temp;
		} else {
			Listable[] tempList = new Listable[this.items.length + 1];
			for (int index = 0; index < this.items.length; index++) {
				tempList[index] = this.items[index];
			}
			tempList[this.items.length] = itemToAdd;
			this.items = tempList;
			for (int i = 0; i < this.items.length - 1; i++) {
				for (int j = 1 + i; j < this.items.length; j++) {
					if (this.items[i].getName().compareToIgnoreCase(this.items[j].getName()) > 0) {
						Listable temp = this.items[j];
						this.items[j] = this.items[i];
						this.items[i] = temp;
					}
				}
			}
		}
	}

	/**
	 * Adds an entire list of items to the current list, maintaining the 
	 * alphabetical ordering of the list by the names of the items.
	 * 
	 * @param listToAdd a list of items that are to be added to the current object
	 */
	public void add(SortedListOfImmutables listToAdd) {
		Listable[] tempArray = new Listable[this.items.length + listToAdd.items.length];
		for (int index = 0; index < this.items.length; index++) { //puts the original array listables into the temporary array
			tempArray[index] = this.items[index];
		}
		for (int index = 0; index < listToAdd.items.length; index++) { //adds the new array listables to the temporary array
			tempArray[index + this.items.length] = listToAdd.items[index];
		}
		this.items = tempArray;
		for (int i = 0; i < this.items.length - 1; i++) {
			for (int j = 1 + i; j < this.items.length; j++) {
				if (this.items[i].getName().compareToIgnoreCase(this.items[j].getName()) > 0) {
					Listable temp = this.items[j];
					this.items[j] = this.items[i];
					this.items[i] = temp;
				}
			}
		}		
		
		
	}
	
	/**
	 * Removes an item from the list.
	 * 
	 * If the list contains the same item that the parameter refers to, it will be 
	 * removed from the list.  
	 * 
	 * If the item appears in the list more than once, just one instance will be
	 * removed.
	 * 
	 * If the item does not appear on the list, then this method does nothing.
	 * 
	 * @param itemToRemove refers to the item that is to be removed from the list
	 */
	public void remove(Listable itemToRemove) {
		int indexRem;
		for (int index = 0; index < this.items.length; index++) {
			if (items[index].getName().equals(itemToRemove.getName())) {
				indexRem = index;
				Listable[] temp = new Listable[this.items.length - 1];
				for (int i = 0; i < indexRem; i++) {
					temp[i] = this.items[i];
				}
				for (int j = indexRem + 1; j < this.items.length; j++) {
					temp[j - 1] = this.items[j];
				}
				this.items = temp;
				break;
			}
		}
	}

	/**
	 * Removes an entire list of items from the current list.  Any items in the
	 * parameter that appear in the current list are removed; any items in the
	 * parameter that do not appear in the current list are ignored.
	 * 
	 * @param listToRemove list of items that are to be removed from this list
	 */
	public void remove(SortedListOfImmutables listToRemove) {
		for (int i = 0; i < this.items.length; i++) {
			for(int j = 0; j < listToRemove.items.length; j++) {
				if (this.items[i].getName().equals(listToRemove.items[j].getName())) { // only uses the items that stay
					this.remove(listToRemove.items[j]);
				}	
			}
		}
	}

	/**
	 * Returns the sum of the wholesale costs of all items in the list.
	 * 
	 * @return sum of the wholesale costs of all items in the list
	 */
	public int getWholesaleCost() {
		int sum = 0;
		for (int index = 0; index < this.items.length; index++) {
			sum = sum + this.items[index].getWholesaleCost();
		}
		return sum;
	}

	/**
	 * Returns the sum of the retail values of all items in the list.
	 * 
	 * @return sum of the retail values of all items in the list
	 */
	public int getRetailValue() {
		int sum = 0;
		for (int index = 0; index < this.items.length; index++) {
			sum = sum + this.items[index].getRetailValue();
		}
		return sum;
	}

	/**
	 * Checks to see if a particular item is in the list.
	 * 
	 * @param itemToFind item to look for
	 * @return true if the item is found in the list, false otherwise
	 */
	public boolean checkAvailability(Listable itemToFind) {
		boolean foundItem = false;
		for (int index = 0; index < this.items.length; index++) {
			if (this.items[index].getName().equalsIgnoreCase(itemToFind.getName())) { 
				foundItem = true;
			}
		}
		return foundItem;
	}

	/**
	 * Checks if a list of items is contained in the current list.
	 *  If the list of items has duplicates then the current list must 
	 *  have that many of the item as well. 
	 * (In other words, this method will return true if ALL of the items in 
	 * the parameter are contained in the current list.  If anything is missing,
	 * then the return value will be false.)
	 * 
	 * @param listToCheck list of items that may or may not be a subset of the
	 * current list
	 * @return true if the parameter is a subset of the current list; false 
	 * otherwise
	 */
	public boolean checkAvailability(SortedListOfImmutables listToCheck) {
		boolean foundList = false;
		SortedListOfImmutables tempCompare = new SortedListOfImmutables(listToCheck); // copy so it doesn't affect the values
		if (this.items.length > tempCompare.items.length || (this.items.length == 0 && listToCheck.items.length != 0)) { //checks if the given list is smaller than the current list
			foundList = false;
		} else {
			int count = 0;
			for (int index = 0; index < this.items.length; index++) { // checks each items in each list against each other
				for (int j = 0; j < tempCompare.items.length; j++) {
					if (this.items[index].getName().equalsIgnoreCase(tempCompare.items[j].getName())) {
						count++;
						tempCompare.remove(tempCompare.items[j]); //resets and resizes the list so that it only checks for single copies and doesn't influence count
						break;
					}
				}
			}
			if (count == this.items.length) {
				foundList = true;
			}
		}
		return foundList;
	}

	/*
	 * Do not modify this method or you will fail our tests!
	 */
	public String toString() {
		String retValue = "[ ";
		for (int i = 0; i < items.length; i++) {
			if (i != 0) {
				retValue += ", ";
			}
			retValue += items[i];
		}
		retValue += " ]";
		return retValue;
	}
}
