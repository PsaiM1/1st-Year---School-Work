package student_classes;
import static utilities.LList.*;
import utilities.*;
/*
 * Under NO circumstances should you mess with the import statements
 * directly above!
 */

/**
 * Define all of your methods in this class, using the static imports from the
 * LListLib.jar file, which is attached to the project. 
 * 
 * @author UMD CS Dept.
 *
 */
public class ListFunctions {
	
	/**
	 * An example of using the LList library to define a common list function:
	 * this one reverses the elements of a LList.
	 * @param list
	 * @return
	 */
	
	public static <T> LList<T> reverse( LList<T> list ) {
		if( isEmpty(list) ) return list;
		else return reverse_aux( list, LList());
	}
	/*
	 * Note the "pattern here." Normally, you define the "top level" function
	 * as we did for "reverse" --above. Then we "delegate" to a private 
	 * auxiliary to do the work. Sometimes you can skip the aux and implement
	 * the function directly, using only the primitives from the library.
	 * In this case, however, because cons only "goes one way," we had to 
	 * resort to this.
	 */
	private static <T> LList<T> reverse_aux( LList<T> list, LList reversed_list ) {
		if( isEmpty( list ) ) return reversed_list;
		else return reverse_aux( rest( list ), cons( first( list ), reversed_list ) );
	}
	/**
	 * Performs the standard "merge sort" on two lists.
	 * @param list
	 * @return
	 */
	public static <T extends Comparable<T> > LList<T>  msort( LList<T> list ) {
		LList<T> tempList = LList.copy(list);
		if (LList.length(tempList) <= 1) { //base case, single node or nothing
			return list;
		} else {
			LList<T> left = LList();
			LList<T> right = LList();
			//splitting the list into left and right
			int midList = (LList.length(tempList) / 2);
			int countLeft = 0;
			while (countLeft < midList) {
				left = LList.cons(LList.first(tempList), left);
				tempList = LList.rest(tempList);
				countLeft++;
			}
			int countRight = midList;
			int listlength = LList.length(list);
			while (countRight < listlength) {
				right = LList.cons(LList.first(tempList), right);
				tempList = LList.rest(tempList);
				countRight++;
			}
			left = ListFunctions.reverse(left);
			right = ListFunctions.reverse(right);
			//recurse and sort
			left = msort(left);
			right = msort(right);
			return merge(left, right);
		}
	}
	
	/**
	 * Given two semi-sorted lists, merge them into a new list by comparing the
	 * head of each list and taking the appropriate actions.
	 * @param list1
	 * @param list2
	 * @return
	 */
	public static <T extends Comparable<T> > LList<T> merge( LList<T> list1, LList<T> list2 ) {
		LList<T> combine = LList();
		//created copies of the lists so it doesn't affect it
		LList<T> left = LList.copy(list1);
		LList<T> right = LList.copy(list2);
		while (!LList.isEmpty(left) && !LList.isEmpty(right)) {
			if (LList.first(left).compareTo(LList.first(right)) <= 0) {
				combine = LList.cons(LList.first(left), combine); //add to the combine list
				left = LList.rest(left);
			} else {
				combine = LList.cons(LList.first(right), combine);
				right = LList.rest(right);
			}
		}
		
		while (!LList.isEmpty(left)) {
			combine = LList.cons(LList.first(left), combine);
			left = LList.rest(left);
		}
		while (!LList.isEmpty(right)) {
			combine = LList.cons(LList.first(right), combine);
			right = LList.rest(right);
		}
		
		combine = ListFunctions.reverse(combine);
		return combine;
	}
	/**
	 * Returns the first "n" elements from list:
	 * 
	 * firstn( 0, [1,2,3] ) => []
	 * firstn( 1, [1,2,3] ) => [1]
	 * firstn( 2, [1,2,3] ) => [1, 2], etc.
	 * 
	 * Observe that firstn(0, [1,2,3]) returns null (that is, the empty
	 * list). 
	 * @param n
	 * @param list
	 * @return
	 */
	public static <T> LList<T> firstn( int n, LList<T> list ) {
		LList<T> tempList = LList();
		LList<T> listHolder = LList.copy(list);
		if (n > LList.length(listHolder)) {
			n = LList.length(listHolder);
		} else if (n < 0) {
			n = 0;
		}
		return firstn_aux(n, tempList, listHolder);
	}
	
	private static <T> LList<T> firstn_aux(int n, LList<T> templist, LList<T> orgList) {
		int counter = n;
		if (counter == 0) {
			return null;
		} else if (counter == 1) {
			templist = LList.cons(LList.first(orgList), templist);
			templist = ListFunctions.reverse(templist);
			return templist;
		} else {
			counter--;
			templist = LList.cons(LList.first(orgList), templist);
			return firstn_aux(counter, templist, LList.rest(orgList));
		}
	}
	/**
	 * Returns the last "n" elements from list:
	 * 
	 * nthTail( 0, [1,2,3] ) => [1,2,3]
	 * nthTail( 1, [1,2,3] ) => [2,3]
	 * nthTail( 2, [1,2,3] ) => [3]
	 * 
	 * @param n
	 * @param list
	 * @return
	 */
	public static <T> LList<T> nthTail( int n, LList<T> list ) {
		int counter = n;
		if (counter == 0) {
			return list;
		} else {
			counter--;
			return nthTail(counter, LList.rest(list));
		}
	}
	
	public static<T> LList<T> removeLast(LList<T> list) {
		if (LList.isEmpty(list)) { //empty list
			return list;
		} else if (LList.isEmpty(LList.rest(list))) { //single node list, basically remove itself
			return LList.rest(list);
		} else {
			return LList.cons(LList.first(list), removeLast(LList.rest(list))); //recursive step
		}
	}
	
	public static<T> LList<T> firstNC(int n, LList<T> list) {
		if (n == 0 || LList.isEmpty(list)) {
			return null;
		} else if (n == 1) {
			return LList.cons(LList.first(list), LList());
		} else {
			n--;
			return LList.cons(LList.first(list), firstNC(n, LList.rest(list)));
		}
	}

}
