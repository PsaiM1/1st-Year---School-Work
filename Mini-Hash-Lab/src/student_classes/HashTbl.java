package student_classes;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
/**
 * This is a truly minimal implementation of the well-known HashTable 
 * class that is still defined in Java (qv). Essentially, a HashTable
 * allows users to associate values with keys in O(1) time (amortized
 * over the life of the running application). 
 * 
 * Note: this implementation throws NullPointerExceptions if <code>put</code>
 * is called with either a null key or a null value.
 * 
 * Moreover, instead of returning Enumerations (old school), this version
 * returns Iterators for its keys and values.
 * 
 * You could theoretically use objects of this class as a hash table, but
 * too much would still need to be done, for real applications.
 * 
 * @author UMD CS Department.
 *
 * @param <E> ///> Keys type
 * @param <V> ///> Values type.
 */
public class HashTbl<E, V> {
	/* define your properties here */
	private final int defaultSize=64;
	private Object buckets[] = new Object[ defaultSize ];
	private ArrayList<E> keyList = new ArrayList<E>();
	private ArrayList<V> valList = new ArrayList<V>();
	/** Only one public constructor is provided ... in reality, we'd
	 * probably like a few more that allowed us to control growth rate,
	 * initial size, etc.
	 */
	public HashTbl() { 
		//constructs with default settings
	}
	/**
	 * Installs the <code>value</code> on the <code>key</code> in this
	 * table. Note, if either parameter is <code>null</code> a
	 * <code>NullPointerException</code> is signaled. 
	 * @param key
	 * @param value
	 */
	public void put( E key, V value ) {
		if (key == null || value == null) {
			throw new NullPointerException();
		} else {
			int hash = key.hashCode() % 64; //mod fits it into the buckets
			buckets[hash] = value; //store in the buckets
			keyList.add(key);
			valList.add(value);
		}
	}
	
	/**
	 * Returns the value associated with <code>key</code>. Because this is a table,
	 * nulls are not allowed, therefore if a <code>null</code> is returned ... we
	 * know that the key wasn't found.
	 * @param key
	 * @return
	 */
	public V get( E key ) {
		int hash = key.hashCode() % 64; //mod 64 makes it fit into the buckets
		return (V) buckets[hash];
	}
	/**
	 * Returns an Iterator over the <code>key</code>s in this table; note, no particular
	 * order is specified here.
	 * @return an Iterator over Keys.
	 */
	public Iterator<E> keys() {
		return new keyIterator();
	}
	
	public class keyIterator implements Iterator<E> {
		HashTbl<E, V> thisTable = HashTbl.this;
		ArrayList<E> keyListitr;
		E[] keyArr;
		int pos;
		public keyIterator() {
			keyListitr = thisTable.keyList;
			keyArr = (E[]) keyListitr.toArray();
			pos = 0;
		}
		@Override
		public boolean hasNext() {
			return pos < keyArr.length;
		}

		@Override
		public E next() {
			return keyArr[pos++];
		}
		
	}
	/**
	 * Returns an Iterator over the <code>value</code>s in the table; note, no
	 * particular order is assumed.
	 * @return
	 */
	public Iterator<V> values() {
		return new valIterator();
	}
	
	public class valIterator implements Iterator<V> {

		HashTbl<E, V> thisTable = HashTbl.this;
		ArrayList<V> valListitr;
		V[] valArr;
		int pos;
		public valIterator() {
			valListitr = thisTable.valList;
			valArr = (V[]) valListitr.toArray();
			pos = 0;
		}
		@Override
		public boolean hasNext() {
			return pos < valArr.length;
		}

		@Override
		public V next() {
			return valArr[pos++];
		}		
	}

}
