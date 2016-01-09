package student_classes;

import java.util.HashSet;
import java.util.Set;

public class MySet<T> {

	/* YOU WRITE THIS CLASS */
	
	private Set<T> currSet = new HashSet<T>(); //wrapped hashset
	
	/**
	 * return the size of this set
	 * @return
	 */
	synchronized public int size() {
		return currSet.size();
	}
	
	/**
	 * clears this set
	 */
	synchronized public void clear() {
		currSet.clear();
	}

	/**
	 * removes the specified object from this set
	 * @param o
	 * @return
	 */
	synchronized public boolean remove(T o) {
		if (currSet.remove(o)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * adds the specified object to this set
	 * @param o
	 * @return
	 */
	synchronized public boolean add(T o) {
		if (currSet.add(o)) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * checks to see if the specified object is in this set
	 * @param o
	 * @return
	 */
	synchronized public boolean contains(T o) {
		if (currSet.contains(o)) {
			return true;
		} else {
			return false;
		}
	}
}