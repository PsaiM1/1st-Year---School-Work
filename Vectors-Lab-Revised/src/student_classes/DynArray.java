package student_classes;

import java.util.*;

/**
 * Your implementation goes here ...
 * @author CMSC Student
 *
 * @param <T>
 */
public class DynArray<T> implements RandomAccess, Iterable<T> {
	private T[] dynArray;
	private boolean allowInputNull;
	private int capacity;
	private int filledCapacity;
	private final int quanta = 4;
	/**
	 * Creates an Array of default size quanta and allows the user to determine
	 * whether nulls should be allowed in the dynamic array via the use of the
	 * allowNulls flag. 
	 * @param allowNulls .
	 */
	public DynArray( boolean allowNulls ) {
		dynArray = (T[]) new Object[quanta];
		allowInputNull = allowNulls;
		capacity = dynArray.length;
		filledCapacity = 0;
	}
	/**
	 * Standard constructor, creates a DynamicArray of default size quanta
	 * that allows nulls
	 */
	public DynArray() {
		dynArray = (T[]) new Object[quanta];
		allowInputNull = true;
		capacity = dynArray.length;
		filledCapacity = 0;
	}
	/**
	 * Creates an array that grants the user full control over the options for
	 * DynArray. The user can determine the capacity that is needed and whether or
	 * not nulls are allowed to be kept.
	 * @param ensureCapacity
	 * @param allow_nulls
	 */
	public DynArray( int ensureCapacity, boolean allow_nulls ) {
		dynArray = (T[]) new Object[ensureCapacity + quanta];
		allowInputNull = allow_nulls;
		capacity = dynArray.length;
		filledCapacity = 0;
	}
	
	/**
	 * Copy Constructor: Creates a new DynArray with all the same elements
	 * and properties preserved.
	 */
	public DynArray(DynArray array) {
		dynArray = (T[]) new Object[quanta];
		this.allowInputNull = true ; //preserves null option
		this.capacity = dynArray.length;
		for (Object element : array.dynArray) {
			this.add((T) element);
		}
		/* resets these parameters to preserve the qualities of the array copied */
		this.allowInputNull = array.allowInputNull;
		this.filledCapacity = array.filledCapacity;
	}
	
	/**
	 * Adds the given object to the end of the array:
	 * Depending on if the allowInputNull flag is true then the user
	 * can add nulls and if its false then the user cannot add nulls
	 * @param 
	 */
	public void add( T ele ) {
		if (allowInputNull == false && ele == null) { // no nulls allowed
			throw new NullPointerException("no nulls");
		} else {
			if (filledCapacity >= capacity) {
				T[] tempArray = (T[]) new Object[dynArray.length + quanta];
				int counter = 0;
				for (T element : dynArray) {
					tempArray[counter] = element;
					counter++;
				}
				/*for (int index = 0; index < dynArray.length; index++) {
					tempArray[index] = dynArray[index];
				}*/
				tempArray[dynArray.length] = ele;
				dynArray = tempArray;
				capacity = dynArray.length;
				filledCapacity++;
			} else {
				dynArray[filledCapacity] = ele;
				filledCapacity++; // increase the number of indexable objects
			}
		}
	}
	/**
	 * Removes the object at the given index and reduces the size of the array
	 * while maintaining the capacity.
	 * @param 
	 * @return
	 */
	public T remove( int atIndex ) {
		T removed = dynArray[atIndex];
		if (atIndex >= filledCapacity || filledCapacity == 0) { // if the index is bigger or of the array has size 0
			throw new ArrayIndexOutOfBoundsException();
		} else {
			T[] temp = (T[]) new Object[this.dynArray.length - 1];
			for (int i = 0; i < atIndex; i++) {
				temp[i] = dynArray[i];
			}
			
			for (int j = atIndex + 1; j < this.dynArray.length; j++) {
				temp[j - 1] = dynArray[j];
			}
			this.dynArray = temp;
			filledCapacity--; // reduce the number of indexable objects
		}
		return removed;
	}
	/**
	 * Getter: Returns the Object at the desired index. 
	 * @param 
	 * @return
	 */
	public T get( int index ) {
		if (filledCapacity == 0 || index >= dynArray.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			return dynArray[index];
		}
	}
	/**
	 * Setter: Allows the user to replace the current Object at the desired index
	 * with another inputted Object
	 * @param 
	 * @param object
	 */
	public void set( int index, T object ) {
		if(allowInputNull == false && object == null) { // no nulls allowed
			throw new IllegalStateException("no nulls");
		} else if (index >= dynArray.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (index < filledCapacity) {
				dynArray[index] = object;
			} else {
				dynArray[index] = object;
				filledCapacity++;
			}
		}
	}
	/**
	* Returns the size of the array
	 * @return
	 */
	public int size() {
		return filledCapacity;
	}
	/**
	 * ToString method: Delegates to Arrays.toString() method
	 */
	public String toString() {
		return Arrays.toString(dynArray);
	}
	/**
	 * Equals method: checks if two DynArrays are equals
	 * The object other is equal to this dynArray iff other is an instance of DynArray,
	 * if other has the same delegated class at runtime as this dynArray, if their size
	 * are the same and if every element in each array is the same.
	 */
	public boolean equals( Object other ) {
		int counter = 0;
		if (other instanceof DynArray) {
			if (other.getClass().equals(this.getClass())) {
				if (((DynArray) other).size() == this.size()) {
					for (T element : dynArray) {
						if (((DynArray) other).dynArray[counter].equals(element) && counter < this.size() - 1) {
							counter++;
						} else {
							break;
						}
					}
				}
			}
		}
		if(counter + 1 == this.size()) {
			return true;
		} else {
			return false;
		}
	}
	
	@Override
	public Iterator<T> iterator() {
		return new DynArrayIterator();
	}
	
	public class DynArrayIterator implements Iterator<T> {
		private int indexCounter = 0;
		@Override
		public boolean hasNext() {
			return indexCounter < filledCapacity;
		}

		@Override
		public T next() {
			return dynArray[indexCounter++];
		}

	}

}
