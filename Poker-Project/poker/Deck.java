package poker;

public class Deck {

	private Card[] cards;

	//Constructs a new Deck of 52 Cards using an array of card objects
	public Deck() {
		cards = new Card[52];
		int suitVal = 0; // spades
		for (int valueVal = 1; valueVal <= 13; valueVal++) {
			cards[valueVal - 1] = new Card(valueVal, suitVal); // cards 1 to 13
		}
		int suitValTwo = 1; // hearts
		for (int valueVal = 1; valueVal <= 13; valueVal++) {
			cards[(valueVal - 1) + 13] = new Card(valueVal, suitValTwo); // cards 14 to 26
		}
		int suitValThree = 2; // clubs
		for (int valueVal = 1; valueVal <= 13; valueVal++) {
			cards[((valueVal - 1) + 13) + 13] = new Card(valueVal, suitValThree); // cards 27 to 39
		}
		int suitValFour = 3; // diamonds
		for (int valueVal = 1; valueVal <= 13; valueVal++) {
			cards[(((valueVal - 1) + 13) + 13) + 13] = new Card(valueVal, suitValFour); // cards 40 to 52
		}
	}

	//copy constructor that creates a shallow copy of the current deck
	public Deck(Deck other) {
		Card[] cardsCopy = new Card[other.cards.length];
		for (int i = 0; i < other.cards.length; i++) {
			cardsCopy[i] = other.cards[i];
		}
		this.cards = cardsCopy;
	}

	// returns the card at the given position
	public Card getCardAt(int position) {
		return this.cards[position];
	}

	//returns the current object cards array length
	public int getNumCards() {
		return this.cards.length;
	}

	/* hint: even and odd */
	/*
	 * Takes the deck and splits it half and takes the top card of the top half and alternates it 
	 * with the top card on the bottom half, effectively shuffling the deck.
	 */
	public void shuffle() {
		Card[] topCards; // top half of deck
		Card[] bottomCards; // bottom half of deck
		Card[] shuffleDeck = new Card[this.cards.length];
		if (this.getNumCards() % 2 == 0) { // if the deck is even
			topCards = new Card[this.cards.length/2];
			bottomCards = new Card[this.cards.length/2];
			for (int index = 0; index < topCards.length; index++) { //copy top half of deck into topCards
				topCards[index] = this.cards[index];
			}
			for (int index = 0; index < bottomCards.length; index++) {
				bottomCards[index] = this.cards[(this.cards.length/2) + index]; // copy bottom half into bottomCards
			}
			
			for (int index = 0; index < shuffleDeck.length; index++) { //fills the entire shuffled array
				if (index % 2 == 0) {
					shuffleDeck[index] = topCards[index/2]; // sets these cards into all the even indexes
				} else {
					shuffleDeck[index] = bottomCards[index/2]; // sets these cards into all the odd indexes
				}
			}
		} else { // if the deck is odd
			topCards = new Card[this.cards.length/2 + 1];
			bottomCards = new Card[this.cards.length/2];
			for (int index = 0; index < topCards.length; index++) {
				topCards[index] = this.cards[index];
			}
			for (int index = 0; index < bottomCards.length; index++) {
				bottomCards[index] = this.cards[((this.cards.length/2) + 1) + index];
			}
			for (int index = 0; index < shuffleDeck.length; index++) {
				if (index % 2 == 0) {
					shuffleDeck[index] = topCards[index/2];
				} else {
					shuffleDeck[index] = bottomCards[index/2];
				}
			}

		}
		this.cards = shuffleDeck;
	}

	// takes the deck and cuts it into two halves and takes the bottom half and puts it on the top half
	public void cut(int position) {
		Card[] topCut = new Card[position];
		Card[] bottomCut = new Card[this.cards.length - position];
		for (int index = 0; index < topCut.length; index++) { //sets an array with all the cards up to position
			topCut[index] = this.cards[index];
		}
		for (int index = 0; index < bottomCut.length; index++) { //sets an array with all cards from position to the end
			bottomCut[index] = this.cards[index + position];
		}
		
		Card[] cutDeck = new Card[this.cards.length]; //a new array for the cut deck
		for (int index = 0; index < bottomCut.length; index++) { //takes the bottom cards and places them in the cut Deck starting from index zero
			cutDeck[index] = bottomCut[index];
		}
		for (int index = 0; index < topCut.length; index++) { //takes the top cards and adds it to the cut Deck from where the last bottom card was until the entire deck is filled
			cutDeck[index + bottomCut.length] = topCut[index];
		}
		this.cards = cutDeck;
	}

	// deals cards and returns an array of the dealt cards and sets the current cards array to the resized deck
	public Card[] deal(int numCards) {
		Card[] smaller = new Card[this.cards.length - numCards];
		Card[] dealt = new Card[numCards];
		for(int index = 0; index < smaller.length; index++) {
			smaller[index] = this.cards[index + numCards];
		}
		for(int index = 0; index < dealt.length; index++) {
			dealt[index] = this.cards[index];
		}
		this.cards = smaller;
		return dealt;
	}
 
}
