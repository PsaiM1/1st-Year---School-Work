package tests;

import static org.junit.Assert.*;

import org.junit.Test;

import student_classes.Card;
import student_classes.Deck;
import student_classes.Numerals;
import student_classes.Suits;

public class StudentTests {
	//Card Tests
	@Test
	public void CardConstructTest() {
		Card one = new Card(Suits.diamonds, Numerals.eight);
		Card two = new Card(Suits.clubs, Numerals.ace);
		Card three = new Card(Suits.hearts, Numerals.jack);
	}
	
	@Test
	public void CardCopyConstructTest() {
		Card one = new Card(Suits.diamonds, Numerals.eight);
		Card two = new Card(Suits.clubs, Numerals.ace);
		Card three = new Card(Suits.hearts, Numerals.jack);
		Card four = new Card(one);
		Card five = new Card(two);
		Card six = new Card(three);
	}
	
	@Test
	public void CardCompareToTest() {
		Card one = new Card(Suits.diamonds, Numerals.eight);
		Card two = new Card(Suits.clubs, Numerals.ace);
		Card three = new Card(Suits.hearts, Numerals.jack);
		Card four = new Card(one);
		assertTrue(one.compareTo(two) == -1);
		assertTrue(two.compareTo(three) == 1);
		assertTrue(one.compareTo(four) == 0);
	}
	
	@Test
	public void CardEqualsandGettersTest() {
		Card one = new Card(Suits.diamonds, Numerals.eight);
		Card two = new Card(Suits.clubs, Numerals.ace);
		Card three = new Card(Suits.hearts, Numerals.jack);
		Card four = new Card(one);
		assertTrue(one.equals(four));
		assertFalse(two.equals(three));
		assertFalse(one.equals(three));
		assertEquals(one.get_numeral(), Numerals.eight);
		assertEquals(one.get_suit(), Suits.diamonds);
		assertEquals(two.get_numeral(), Numerals.ace);
		assertEquals(two.get_suit(), Suits.clubs);
		assertEquals(three.get_numeral(), Numerals.jack);
		assertEquals(three.get_suit(), Suits.hearts);
		assertEquals(four.get_numeral(), Numerals.eight);
		assertEquals(four.get_suit(), Suits.diamonds);
	}

	//Deck Tests
	
	@Test
	public void DeckConstructor() {
		Deck testDeck = new Deck();
	}
	
	@Test
	public void DeckCopyConstructor() {
		Deck testDeck = new Deck();
		Deck copyTestDeck = new Deck(testDeck);
	}
	
	@Test
	public void DeckCompareTest() {
		Card one = new Card(Suits.diamonds, Numerals.eight);
		Card two = new Card(Suits.clubs, Numerals.ace);
		Card three = new Card(Suits.hearts, Numerals.jack);
		Card four = new Card(one);
		
		Deck testDeck = new Deck();
		assertTrue(testDeck.compare(one, two) == 1);
		assertTrue(testDeck.compare(two, three) == -1);
		assertTrue(testDeck.compare(one, four) == 0);
	}
	
	@Test
	public void DeckBooleanTest() {
		Deck testDeckOne = new Deck();
		Deck testDeckTwo = new Deck();
		assertTrue(testDeckOne.equals(testDeckTwo));
		testDeckTwo.shuffle();
		assertFalse(testDeckOne.equals(testDeckTwo));
	}
	
	@Test
	public void DeckShuffleandSort() {
		Deck test = new Deck();
		Deck testTwo = new Deck(test);
		assertTrue(test.equals(testTwo));
		testTwo.shuffle();
		assertFalse(test.equals(testTwo));
		testTwo.sort();
		assertTrue(test.equals(testTwo));
	}
}
