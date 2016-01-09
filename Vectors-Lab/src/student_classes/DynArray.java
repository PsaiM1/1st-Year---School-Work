package student_classes;

import java.util.*; // uses various classes from this package--see documentation.

/**
 * A Dynamic Array that allows a user to add, remove, set, and get objects of a given
 * type into the array and the array will count its capacity and will resize as necessary.
 * The user can also determine whether or not they would allow nulls to be stored
 * in the dynamic array by setting the allowNulls flag to true.
 * @author PrasannaSai Meruga
 *
 * @param <T>
 */
public class DynArray<T> implements RandomAccess{
	
	private T[] dynArray;
	private boolean allowInputNull;
	private int capacity;
	private int filledCapacity;
	private final int quanta = 4;
	/**
	 * Creates an Array with the default size
	 * and allows the user to determine if nulls can be stored
	 * in the array
	 * if allowNulls = false, then nulls cannot be stored
	 * @param allowNulls .
	 */
	public DynArray( boolean allowNulls ) {
		dynArray = (T[]) new Object[quanta];
		allowInputNull = allowNulls;
		capacity = dynArray.length;
		filledCapacity = 0;
	}
	/**
	 * Default Constructor: Creates an Array with default 4 indexes and is 
	 * allowed to hold nulls
	 */
	public DynArray() {
		dynArray = (T[]) new Object[quanta];
		allowInputNull = true;
		capacity = dynArray.length;
		filledCapacity = 0;
	}
	/**
	 * Creates an Array whose size and capability to hold nulls is determined by the
	 * user. The array will be the size determined by the user plus some quanta in
	 * order to ensureCapacity
	 * @param 
	 * @param 
	 */
	public DynArray( int ensureCapacity, boolean allow_nulls ) {
		dynArray = (T[]) new Object[ensureCapacity + quanta];
		allowInputNull = allow_nulls;
		capacity = dynArray.length;
		filledCapacity = 0;
	}
	/**
	 * Adds the given Object to the end of the Array:
	 * Depending if allowNulls is true then the array will be able
	 * to add null values, if its false then it won't be able to add null values
	 * @param 
	 */
	public void add( T ele ) {
		if (allowInputNull == false && ele == null) { // no nulls allowed
			throw new NullPointerException("no nulls");
		} else {
			if (filledCapacity >= capacity) {
				T[] tempArray = (T[]) new Object[dynArray.length + quanta];
				for (int index = 0; index < dynArray.length; index++) {
					tempArray[index] = dynArray[index];
				}
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
	 * Returns and removes the Object at the desired index
	 * @param 
	 * @return
	 */
	public T remove( int atIndex ) {
		T removed = dynArray[atIndex];
		if (atIndex >= filledCapacity || filledCapacity == 0) { // if the index is bigger or of the array has size 0
			throw new ArrayIndexOutOfBoundsException();
		} else {
			for (int index = 0; index < filledCapacity; index++) {
				if (index >= atIndex) {
					this.dynArray[index] = this.dynArray[index + 1];
				}
			}
			filledCapacity--; // reduce the number of indexable objects
		}
		return removed;
	}
	/**
	 * Returns the Object at the desired index
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
	 * Replaces the Object at the index with the given object. If allowNulls is false and 
	 * the inputted object is a null then the method will throw a NullPointerException
	 * @param 
	 * @param object
	 */
	public void set( int index, T object ) {
		if(allowInputNull == false && object == null) { // no nulls allowed
			throw new NullPointerException("no nulls");
		} else if (index >= dynArray.length) {
			throw new ArrayIndexOutOfBoundsException();
		} else {
			if (index <= filledCapacity) {
				dynArray[index] = object;
				filledCapacity++;
			} else {
				dynArray[index] = object;
			}
		}
	}
	/**
	* Returns the number of objects that are stored within the dynamic array
	 * @return
	 */
	public int size() {
		return filledCapacity; 
	}
	/**
	 * To String method that defaults to the Arrays' toString method.
	 */
	public String toString() {
		return Arrays.toString(dynArray);
	}
	/**
	 * Equals method that defaults to the Arrays' equals method
	 */
	public boolean equals( Object other ) {
		return Arrays.equals(dynArray, (Object[]) other);
	}

}
