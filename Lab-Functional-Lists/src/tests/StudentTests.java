package tests;
/*
 * Under NO circumstances should you mess with these package definitions
 * or the following import statements. Leave them alone!
 */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import student_classes.ListFunctions;
import utilities.LList;
import static utilities.LList.*;
import static student_classes.ListFunctions.*;
/* 
 * Define your student tests here. I've provided a simple
 * function as a template, to get you started.
 * 
 */
public class StudentTests {

	@Test
	public void testReverse() {
		LList myList = LList();
		for( int index=0; index < 10; index++ ) 
			myList = cons( index, myList );
		System.out.println("Created " + myList );
		assertEquals( length( myList ), 10 );
		myList = reverse( myList );
		System.out.println("Reversed it is " + myList );
		assertEquals( length( myList ), 10 );
		
	}
	
	@Test
	public void testList() {
		List<Integer> ints = genInts( 5 );
		LList myList = LList.list( ints );
		System.out.println( myList );
	}
	
	private static ArrayList<Integer> genInts( int howMany ) {
		int counter = 0;
		ArrayList<Integer> rgsInts = new ArrayList<Integer>();
		for( ; howMany > 0; howMany-- )
			rgsInts.add( counter++ );
		return rgsInts;
	}
	
	@Test
	public void testFirstN() {
		LList myList = LList();
		for (int index = 10; index > 0; index--) { //list from 1 to 10
			myList = cons(index, myList);
		}
		System.out.println(myList);
		LList myTemp = firstn(3, myList);
		System.out.println("FirstN:" + myTemp);
		
		LList myList2 = LList();
		for (int index = 10; index > 0; index--) { //list from 1 to 10
			myList2 = cons(index, myList2);
		}
		System.out.println(myList2);
		LList myTemp2 = firstn(2, myList2);
		System.out.println("FirstN:" + myTemp2);
		
		LList myList3 = LList();
		for (int index = 10; index > 0; index--) { //list from 1 to 10
			myList3 = cons(index, myList3);
		}
		System.out.println(myList3);
		LList myTemp3 = firstn(1, myList3);
		System.out.println("FirstN:" + myTemp3);
		
		LList myList4 = LList();
		for (int index = 10; index > 0; index--) { //list from 1 to 10
			myList4 = cons(index, myList4);
		}
		System.out.println(myList3);
		LList myTemp4 = firstn(0, myList4);
		System.out.println("FirstN:" + myTemp4);
		
		LList myTemp5 = firstn(1, myList4);
		System.out.println("FirstN:" + myTemp5);
	}
	
	@Test
	public void testNthTail() {
		LList myList = LList();
		for (int index = 10; index > 0; index--) { //list from 1 to 10
			myList = cons(index, myList);
		}
		System.out.println(myList);
		LList myTemp = nthTail(2, myList);
		System.out.println("nthTail:" + myTemp);
	}
	
	@Test
	public void msortTest() {
		ArrayList<Integer> tempEven = new ArrayList<Integer>();
		ArrayList<Integer> tempOdd = new ArrayList<Integer>();
		tempEven.add(3);
		tempEven.add(4);
		tempEven.add(1);
		tempEven.add(5);
		tempEven.add(6);
		tempEven.add(2);
		
		tempOdd.add(2);
		tempOdd.add(5);
		tempOdd.add(1);
		tempOdd.add(3);
		tempOdd.add(4);
		
		LList<Integer> listEvens = LList.list(tempEven);
		LList<Integer> listOdds = LList.list(tempOdd);
		
		LList<Integer> sortEvens = ListFunctions.msort(listEvens);
		LList<Integer> sortOdds = ListFunctions.msort(listOdds);
		
		System.out.println("Even sorted List:" + sortEvens);
		System.out.println("Odd sorted List" + sortOdds);
	}
	
	@Test
	public void firstNTestAgain() {
		LList<Integer> myList = LList();
		for (int index = 5; index > 0; index--) { //list from 1 to 10
			myList = cons(index, myList);
		}
		System.out.println(myList);
		LList myTemp = firstn(0, myList);
		System.out.println("FirstN:" + "[0]: "+ myTemp);
		
		System.out.println(myList);
		LList myTemp2 = firstn(1, myList);
		System.out.println("FirstN:" + "[1]: "+ myTemp2);
		
		System.out.println(myList);
		LList myTemp3 = firstn(2, myList);
		System.out.println("FirstN:" + "[2]: "+ myTemp3);
		
		System.out.println(myList);
		LList myTemp4 = firstn(3, myList);
		System.out.println("FirstN:" + "[3]: "+ myTemp4);

		System.out.println(myList);
		LList myTemp5 = firstn(4, myList);
		System.out.println("FirstN:" + "[4]: "+ myTemp5);
		
		System.out.println(myList);
		LList myTemp6 = firstn(5, myList);
		System.out.println("FirstN:" + "[5]: "+ myTemp6);
		
		System.out.println(myList);
		LList myTemp7 = firstn(6, myList);
		System.out.println("FirstN:" + "[6]: "+ myTemp7);
		
		System.out.println(myList);
		LList myTemp8 = firstn(-1, myList);
		System.out.println("FirstN:" + "[neg]: "+ myTemp8);
		
		LList empty = LList();
		System.out.println(empty);
		LList myTempEmpty = firstn(0, empty);
		System.out.println("FirstN:" + "[0 E]: "+ myTempEmpty);
		
		System.out.println(myList);
		LList myTempEmpty2 = firstn(1, empty);
		System.out.println("FirstN:" + "[1 E]: "+ myTempEmpty2);
	}
	
	@Test
	public void testRemoveLast() {
		LList myList = LList();
		for (int index = 5; index > 0; index--) { //list from 1 to 5
			myList = cons(index, myList);
		}
		LList myTemp = removeLast(myList);
		System.out.println("remove Last:" + myTemp);
	}
	
	@Test
	public void testFirstNC() {
		LList myList = LList();
		for (int index = 5; index > 0; index--) { //list from 1 to 5
			myList = cons(index, myList);
		}
		LList myTemp = firstNC(3, myList);
		System.out.println("first NCCC:" + myTemp);
	}

}
