package student_classes;

public class Card implements Comparable<Card>{
	
	private Suits suit;
	private Numerals numeral;
	/**
	 * Standard Constructor for the object Card, no default constructor is used as 
	 * there as no such thing as a default suit or numeral that would make up
	 * a "default card"
	 * @param aSuit
	 * @param aNumeral
	 */
	public Card(Suits aSuit, Numerals aNumeral) {
		this.suit = aSuit;
		this.numeral = aNumeral;
	}
	
	/**
	 * Copy constructor, takes the parameters of the card to be copied and
	 * sets them to the current card's parameters
	 * @param aCard
	 */
	public Card(Card aCard) {
		this.suit = aCard.suit;
		this.numeral = aCard.numeral;
	}
	
	/**
	 * Comparison method brought in by implementation of the Comparable interface.
	 * This compares the numerals between two cards.
	 * @param otherCard
	 */
	public int compareTo(Card otherCard) {
		int compareVal = 0;
		if (this.numeral.ordinal() == otherCard.numeral.ordinal()) { //if equal
			compareVal = 0;
		} else if (this.numeral.ordinal() < otherCard.numeral.ordinal()) { // if less than
			compareVal = -1;
		} else if (this.numeral.ordinal() > otherCard.numeral.ordinal()) { // if greater than
			compareVal = 1;
		}
		return compareVal;
	}
	
	/**
	 * Equals method: checks if the Object other is an instance of the class Card
	 * if it is, it will compare it to each other and if the value comes out as true
	 * then the method returns true
	 */
	public boolean equals(Object other) {
		boolean equalVal = false;
		if (other instanceof Card) {
			if (this.compareTo((Card) other) == 0) {
				equalVal = true;
			}
		} else {
			equalVal = false;
		}
		return equalVal;
	}

	/**
	 * Getter for the current card's numerals
	 * @return
	 */
	public Numerals get_numeral() {
		return this.numeral;
	}
	
	/**
	 * Getter for the current card's suit
	 * @return
	 */
	public Suits get_suit() {
		return this.suit;
	}
	
	/**
	 * toString method: prints out the suit and numeral of this card.
	 */
	public String toString() {
		String cardString = "Suit:" + this.suit.toString() + " Numerals:" + this.numeral.toString();
		return cardString;
	}
}
