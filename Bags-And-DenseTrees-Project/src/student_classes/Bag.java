package student_classes;

import java.util.*;
import java.util.function.Consumer;


/**
 * A "bag" is a data-structure that implements a <em>multiset</em>, which is
 * a set that allows multiple (duplicates). The standard operators on such
 * a data-structure include:
 * 
 * 	Bag() --creates an empty bag (optionally, perhaps a default size?)
 * 
 * 	add( Type ele ) --adds an element of Type to the bag
 * 
 * 	remove( Type ele ) --removes an element from the bag; may throw IllegalStateException
 * 					   --in the event that ele is NOT in the Bag.
 * 
 * 	contains( Type ele ) --returns True if ele is in bag.
 * 
 * 	count( Type ele ) --returns the number of occurrences of ele in set. 
 *                 This is called the "multiplicity" of the element.
 * 
 * 	asSet() --returns a collection of the elements in this bag as set.
 * 
 *  size() --returns the number of elements in this bag taking into account
 *           their multiplicities. For example: if the Bag contains the following
 *           elements with multiplicities:
 *           
 *           Key    Count
 *           ------------
 *           "A"      2
 *           "B"      3
 *           "C"      1
 *           
 *           size() => 6.
 * 
 *  iterator() --returns an Iterator that also takes into account the multiplicities
 *           of each key. So, an iterator() should iterate over exactly the same number
 *           of elements as size().
 * Important reminder: You will most likely replace references to <code>Object</code>
 * that appear in parameters lists with your generic variable type(s).
 * 
 * @author UMD CS Department.
 *
 * @param
 */
public class Bag<T> implements Iterable<T> { // note you will need to parameterize this class and
	                                   // most likely the implements declaration.
	/* Instance variables */
	private ArrayList<T> keys;
	private ArrayList<Integer> count;
	/**
	 * Default (sole) constructor. Sets up internal data structures.
	 */
	public Bag() {
		keys = new ArrayList<T>();
		count = new ArrayList<Integer>();
	}
	
	/* public interface */
	/**
	 * Adds ele (Type) to the bag. If ele was already in the Bag, then its
	 * internal count is incremented by 1; otherwise a new entry is made, 
	 * indicating that at least 1 ele exists in this Bag.
	 * @param ele
	 */
	public void add( T ele ) { // replace Object with your Type variable
		if (keys.contains(ele)) {
			int index = keys.indexOf(ele);
			count.set(index, count.get(index) + 1);
		} else {
			keys.add(ele);
			count.add(1);
		}
	}
	/**
	 * Consider several possibilities here:
	 * If the <code>ele</code> isn't in the Bag, throw an Illegal State Exception.
	 * If only one <code>ele</code> exists in the Bag, then remove it.
	 * If more than one <code>ele</code> exists in the Bag, make whatever changes are
	 * necessary to indicate that one fewer <code>ele</code> now exists in the Bag.
	 * Note, how you do this will depend upon your internal data-structure.
	 * @param ele
	 */
	public void remove( T ele ) { //replace Object with your Type
		if (this.contains(ele) == false) {
			throw new IllegalStateException();
		}else if (this.count(ele) > 1) {
			int index = keys.indexOf(ele);
			count.set(index, count.get(index) - 1);
		} else {
			int index = keys.indexOf(ele);
			count.remove(index);
			keys.remove(ele);
		}
	}
	/**
	 * Returns true if this Bag contains at least one copy of the <code>key</code>.
	 * @param ele
	 * @return
	 */
	public boolean contains( T ele ) { // replace Object with your Type 
		return keys.contains(ele);
	}
	/**
	 * Returns the number of occurrences of <code>ele</code> in the Bag; 
	 * returns 0 in the event that <code>ele</code> is not in Bag.
	 * @param ele
	 * @return
	 */
	public int count( T ele ) { //replace Object with your Type
		if (!keys.contains(ele)) {
			return 0;
		} else {
			int index = keys.indexOf(ele);
			return count.get(index);
		}
	}
	/**
	 * Return the keys as a Set---i.e., no duplicates, order-unimportant.
	 * Use this function to determine the number of unique entries in the
	 * Bag!
	 * @return
	 */
	public Set<T> asSet() { // parameterize Set<> with your type
		Set<T> bagSet = new HashSet<T>();
		for (T item : this.keys) {
			bagSet.add(item);
		}
		return bagSet;
	}
	/**
	 * The "size" of a bag is the cardinality of the multiset that it
	 * embodies. In English, that means that the size of your bag 
	 * must accumulate the multiplicities of each element. For
	 * example: suppose your Bag contains 
	 * 
	 * Key    Count
	 * ------------
	 * "A"     2
	 * "B"     3
	 * "C"     1
	 * 
	 * Then its size is 6.
	 * 
	 * @return
	 */
	public int size() {
		int size = 0;
		for (Integer item : count) {
			size += item;
		}
		return size;
	}
	/**
	 * You will most likely return an instance of an inner class that you design
	 * to manage an <em>immutable</em> Iteration of keys over this bag.
	 * @return
	 */
	public Iterator<T> iterator() {
		return new bagIterator();
	}
	
	public class bagIterator implements Iterator<T> {
		ArrayList<T> keys = Bag.this.keys;
		ArrayList<Integer> count = Bag.this.count;
		T[] keysArr = (T[]) new Object[keys.size()];
		Integer[] countArr = new Integer[count.size()];
		private int pos;
		
		public bagIterator() {
			keys.toArray(keysArr);
			count.toArray(countArr);
			pos = 0;
		}
		
		@Override
		public boolean hasNext() {
			return pos < keysArr.length;
		}

		@Override
		public T next() {
			if (countArr[pos] > 1) {
				countArr[pos] = countArr[pos] - 1;
				return keysArr[pos];
			} else {
				return keysArr[pos++];
			}
		}
	}
}



