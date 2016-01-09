package poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class StudentTests {

	@Test
	public void testSingleTest() {
		Card[] testHand = new Card[5];
		testHand[0] = new Card(1,1);
		testHand[1] = new Card(2,1);
		testHand[2] = new Card(3,1);
		testHand[3] = new Card(4,1);
		testHand[4] = new Card(5,1);
		assertFalse(PokerHandEvaluator.hasPair(testHand));
		assertTrue(PokerHandEvaluator.hasStraight(testHand));
		assertTrue(PokerHandEvaluator.hasStraightFlush(testHand));
		
		Card[] testPairHand = new Card[5];
		testPairHand[0] = new Card(1,1);
		testPairHand[1] = new Card(2,1);
		testPairHand[2] = new Card(3,1);
		testPairHand[3] = new Card(4,1);
		testPairHand[4] = new Card(1,1);
		assertTrue(PokerHandEvaluator.hasPair(testPairHand));
		
		Card[] testTwoPair = new Card[5];
		testTwoPair[0] = new Card(1,1);
		testTwoPair[1] = new Card(2,1);
		testTwoPair[2] = new Card(3,1);
		testTwoPair[3] = new Card(2,1);
		testTwoPair[4] = new Card(1,1);
		assertTrue(PokerHandEvaluator.hasTwoPair(testTwoPair));
		
		Card[] testThreeKind = new Card[5];
		testThreeKind[0] = new Card(1,1);
		testThreeKind[1] = new Card(2,1);
		testThreeKind[2] = new Card(3,1);
		testThreeKind[3] = new Card(1,1);
		testThreeKind[4] = new Card(1,1);
		assertTrue(PokerHandEvaluator.hasThreeOfAKind(testThreeKind));
		
		Card[] testFourKind = new Card[5];
		testFourKind[0] = new Card(1,1);
		testFourKind[1] = new Card(2,1);
		testFourKind[2] = new Card(1,1);
		testFourKind[3] = new Card(1,1);
		testFourKind[4] = new Card(1,1);
		assertTrue(PokerHandEvaluator.hasFourOfAKind(testFourKind));
		
		Card[] testFullHouse = new Card[5];
		testFullHouse[0] = new Card(1,1);
		testFullHouse[1] = new Card(2,1);
		testFullHouse[2] = new Card(2,1);
		testFullHouse[3] = new Card(1,1);
		testFullHouse[4] = new Card(1,1);
		assertTrue(PokerHandEvaluator.hasFullHouse(testFullHouse));
		
		Card[] testFlush = new Card[5];
		testFlush[0] = new Card(1,1);
		testFlush[1] = new Card(2,1);
		testFlush[2] = new Card(3,1);
		testFlush[3] = new Card(4,1);
		testFlush[4] = new Card(5,1);
		assertTrue(PokerHandEvaluator.hasFlush(testFlush));
		
		Card[] testStraightandFlush = new Card[5];
		testStraightandFlush[0] = new Card(2,1);
		testStraightandFlush[1] = new Card(3,1);
		testStraightandFlush[2] = new Card(4,1);
		testStraightandFlush[3] = new Card(5,1);
		testStraightandFlush[4] = new Card(1,1);
		assertTrue(PokerHandEvaluator.hasStraight(testStraightandFlush));
		assertTrue(PokerHandEvaluator.hasStraightFlush(testStraightandFlush));
		
		Card[] testStraight = new Card[5];
		testStraight[0] = new Card(3,1);
		testStraight[1] = new Card(2,1);
		testStraight[2] = new Card(4,1);
		testStraight[3] = new Card(1,1);
		testStraight[4] = new Card(5,1);
		assertFalse(PokerHandEvaluator.hasStraight(testStraight));
		assertFalse(PokerHandEvaluator.hasStraightFlush(testStraight));

	}
	
	@Test
	public void testGetNumCards() {
		Deck deck = new Deck();
		assertEquals(52, deck.getNumCards());
		deck.deal(2);
		assertEquals(50, deck.getNumCards());
	
	}
	
	@Test
	public void testCopyConstruct() {
		Deck deck = new Deck();
		Deck copyDeck = new Deck(deck);
		assertEquals(1, deck.getCardAt(0).getValue());
		assertEquals(0, deck.getCardAt(0).getSuit());
		assertEquals(1, copyDeck.getCardAt(0).getValue());
		assertEquals(0, copyDeck.getCardAt(0).getSuit());
		assertEquals(13, deck.getCardAt(51).getValue());
		assertEquals(3, deck.getCardAt(51).getSuit());
		assertEquals(13, copyDeck.getCardAt(51).getValue());
		assertEquals(3, copyDeck.getCardAt(51).getSuit());
	}
	
	@Test
	public void testDeal() {
		Deck deck = new Deck();
		assertEquals(4, deck.deal(4).length);
		assertEquals(48, deck.getNumCards());
		assertEquals(8, deck.deal(8).length);
		assertEquals(40, deck.getNumCards());
	}
	
	@Test
	public void testCut() {
		Deck deck = new Deck();
		deck.cut(4);
		assertEquals(5, deck.getCardAt(0).getValue());
		assertEquals(0, deck.getCardAt(0).getSuit());
	}
	
	@Test
	public void testShuffle() {
		Deck deck = new Deck();
		deck.shuffle(); //base shuffle
		assertEquals(1, deck.getCardAt(1).getValue());
		assertEquals(2, deck.getCardAt(1).getSuit());
		Deck dealedDeck = new Deck();
		dealedDeck.deal(40); // newly dealed but even deck
		assertEquals(12, dealedDeck.getNumCards());
		dealedDeck.shuffle();
		assertEquals(2, dealedDeck.getCardAt(0).getValue());
		assertEquals(3, dealedDeck.getCardAt(0).getSuit());
		assertEquals(8, dealedDeck.getCardAt(1).getValue());
		assertEquals(3, dealedDeck.getCardAt(1).getSuit());
		Deck oddDeck = new Deck();
		oddDeck.deal(41); // now an odd deck
		assertEquals(11, oddDeck.getNumCards());
		oddDeck.shuffle();
		assertEquals(3, oddDeck.getCardAt(0).getValue());
		assertEquals(3, oddDeck.getCardAt(0).getSuit());
		assertEquals(9, oddDeck.getCardAt(1).getValue());
		assertEquals(3, oddDeck.getCardAt(1).getSuit());
		assertEquals(4, oddDeck.getCardAt(2).getValue());
		assertEquals(3, oddDeck.getCardAt(2).getSuit());
		assertEquals(10, oddDeck.getCardAt(3).getValue());
		assertEquals(3, oddDeck.getCardAt(3).getSuit());
	}

}
