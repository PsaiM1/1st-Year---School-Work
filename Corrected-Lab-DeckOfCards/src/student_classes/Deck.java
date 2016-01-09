package student_classes;

import java.util.*;

public class Deck implements Iterable<Card>, Comparator<Card> {
	
	private ArrayList<Card> cards;
	
	/**
	 * Standard constructor: constructs a sorted deck from clubs to spades with the numerals
	 * in order
	 */
	public Deck() {
		cards = new ArrayList<Card>();
		for(Suits suit : Suits.values()) {
			for (Numerals numer : Numerals.values()) {
				cards.add(new Card(suit, numer));
			}
		}
		
	}
	
	/**
	 * Copy constructor: Does a deep copy down to the individual cards of the deck
	 * @param otherDeck
	 */
	public Deck(Deck otherDeck) {
		cards = new ArrayList<Card>();
		for (Card card: otherDeck.cards) 
			this.cards.add(new Card(card));
	}
	
	/**
	 * Compare method: A more advanced comparison method that compares two cards
	 * by comparing both their suits and their numerals and returning which one is 
	 * bigger. It checks for the higher suit first and if the suits are equals then it 
	 * checks for the higher numeral
	 */
	public int compare(Card card1, Card card2) {
		int compareVal = 0;
		if (card1.get_suit().ordinal() == card2.get_suit().ordinal()) {
			if (card1.get_numeral().ordinal() == card2.get_numeral().ordinal()) {
				compareVal = 0;
			} else if (card1.get_numeral().ordinal() < card2.get_numeral().ordinal()) {
				compareVal = -1;
			} else if (card1.get_numeral().ordinal() > card2.get_numeral().ordinal()) {
				compareVal = 1;
			}
		} else if (card1.get_suit().ordinal() < card2.get_suit().ordinal()) {
			compareVal = -1;
		} else if (card1.get_suit().ordinal() > card2.get_suit().ordinal()) {
			compareVal = 1;
		}
		return compareVal;
	}
	
	/**
	 * Equals method: compares every card in this deck and the other deck and counts how many
	 * are equal. If all 52 cards are equal then the method returns true. If not then
	 * it returns false.
	 */
	public boolean equals(Object other) {
		int counter = 0;
		int index = 0;
		if(other instanceof Deck) {
			for (Card card1 : this.cards) { // allows you to only worry about one for loop
				if (this.compare(card1, ((Deck) other).cards.get(index)) == 0) {
					counter++;
					index++;
				}
			}
		}
		if (counter == 52) {
			return true;
		} else {
			return false;
		}
		
	}

	/**
	 * Iterator method: as inherited from Iterable, this method returns an iterator
	 * of type Card
	 */
	public Iterator<Card> iterator() {
		Iterator<Card> i = cards.iterator();
		return i;
	}
	
	/**
	 * Shuffle method: delegates to Collections.shuffle method
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	/**
	 * Getter for the size of the deck.
	 * @return
	 */
	public int size() {
		return cards.size();
	}
	
	/**
	 * Sort method: Delegates to Collections.sort method using the cards
	 * arrayList as the List and using this class's compare method for the 
	 * comparator.
	 */
	public void sort() {
		Collections.sort(cards, this);
	}
	
	/*public String toString() {
		// only when necessary
	}*/
}

