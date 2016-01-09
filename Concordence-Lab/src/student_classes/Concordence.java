package student_classes;

// imports should go here ... depending upon your approach ...
// I strongly advise java.util.Scanner, java.util.regex, and most likely
// a dictionary class, such as HashTable.

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
import java.util.Hashtable;

/**
 * @author UMD CS Department:
 * A <code>Concordence</code> is an object that embodies the association of tokens (words taken from a text) 
 * to their "context." This class defines "context"  as the the number of
 * times that a particular token (word) occurs in a document (which is a text file). 
 * <P>
 * Clients of this class provide a complete pathname to a text file (<file>.txt) and
 * a boolean variable <code>is_case_sensitive</code> that determines whether tokens are stored in
 * the original case or are converted to lower case during the construction of the 
 * associations as well as during the retrieval of counts associated with particular
 * tokens. </P>
 * <P>Clients may
 * use the various methods on the <code>Concordence</code> object to retrieve information such as how
 * many times a particular word occurred or lists of words that occurred a number of times.
 * </P>
 * <P>
 * <strong>
 * Special characters, such as syntax marks, are ignored. It is possible, therefore, that hyphenated
 * words or contractions will register incorrectly: For example: "can't" might be "can" "t." Certainly,
 * developers of this class are encouraged to explore Java's regular expressions package to 
 * improve this implementation!</strong>
 * </P>
 *
 */
public class Concordence {
// Properties:
	private Hashtable<Integer, String> tokenCount;
	private Scanner scanner;
	private boolean caseSense;
//	Constructor(s):
	/**
	 * Default ctor: sets up internal tables ... not usefully called by 
	 * anyone outside of this class.
	 */
	protected Concordence( ) {
		tokenCount = new Hashtable<Integer, String>(9000);
	}
	/**
	 * Main Constructor: requires two parameters: 
	 * <P>
	 * (1) <code>pathName</code> is a <code>String</code> representing a valid pathname, i.e., a pathname
	 * whose last component is the name of a "text file." Text files comprise normal characters and are
	 * assumed to have the suffix "<filename>.txt".
	 * <P>
	 * (2) <code> is_case_sensitive</code>, which is a <code>boolean</code>
	 * that determines if the capitalization of tokens matters. If the client specifies that
	 * <code>is_case_sensitive</code> is <code>True</code>, then the original case of all tokens will be preserved during the
	 * construction of the tables (associations) as well as during the retrieval of data that involves
	 * the comparison of tokens by the various public methods exposed by the Concordence object.</item>
	 * </P>
	 * @param pathName (String)
	 * @param is_case_sensitive (boolean)
	 */
	public Concordence( String pathName, boolean is_case_sensitive ) {
		tokenCount = new Hashtable<Integer, String>(9000);
		try {
			scanner = new Scanner(new File(pathName));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		caseSense = is_case_sensitive;
	}
// 	Public Methods:
	/**<P>
	 * Given a (String) token, return how many times it occurred in the text.</P>
	 * <P>Preconditions: The constructor has been successfully called.</P>
	 * <P>Postconditions: a counting number is returned.</P>
	 * <P> Note: this method is sensitive to 
	 * the value of the <code>is_case_sensitive</code> parameter that the user specified during the 
	 * construction of this instance. If the user specified that case was to be ignored,
	 * then all tokens have been installed and will be compared in lower case; otherwise, 
	 * the original case of the token(s) as they were found in the document will be used.
	 */
	public int getTokenCount( String for_token ) {
		int count = 0;
		if (caseSense == true) {
			while(scanner.hasNext()) {
				String temp = scanner.next();
				if (temp.equals(for_token)) {
					count++;
				} 
			}
		} else {
			while(scanner.hasNext()) {
				String temp = scanner.next();
				if (temp.equalsIgnoreCase(for_token)) {
					count++;
				} 
			}
		}
		tokenCount.put(count, for_token);
		return count;
	}
	/**
	 * <P>Preconditions: The Constructor has successfully been called. Note: the <code>by_count</code>
	 * argument must be an integer greater than 0 or an Illegal Argument exception is thrown.</P>
	 * <P>
	 * Postcondition: An <code>Iterable(String)</code> object is returned that contains an unordered
	 * list of tokens (which are unique) whose counts equal <code>by_count</code> Note: this list
	 * could be empty, but should not be under ordinary circumstances.</P>
	 * @param by_count <code>(int > 0)</code>
	 * @return <code>Iterable(String)</code> An Iterable<String> object (which may be empty) that contains the tokens whose counts
	 * equal the <code>by_count</code> (int) parameter.
	 */
	public Iterable<String> getTokensByCount( int by_count ) {
		ArrayList<String> temp = new ArrayList<String>();
		temp.add(tokenCount.remove(by_count));
		return temp;
	}
	/**
	 * <P>Preconditions: The Constructor for this class has been successfully called.
	 * </P>
	 * <P>Postconditions: The current size, which is the number of entries, in the Concordence table is returned.
	 * @return<code> int>= 0</code>
	 */
	public final int size() {
		return tokenCount.size();
	}
	
//	Overrides ...
	/**
	 * Returns a String that identifies this object and provides a little detail ...
	 */
	public String toString() { 
		String explain = "This Concordance object reads through the inputted files for certain contexts, which is how many"
				+ " times a certain String token occurs inside a text file. It then stores this value into a table that" +
				" can be used for various concordance functions";
		return explain;
	}
	
}
